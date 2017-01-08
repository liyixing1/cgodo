package com.cgodo.comment.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cgodo.comment.WechatComment;
import com.cgodo.util.UtilDateTime;
import com.cgodo.util.UtilEncrypt;
import com.cgodo.util.UtilHttpClient;
import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilUrl;

/**
 * 微信组件
 * 
 * @author liyixing-pc
 *
 */
@Component
public class WechatCommentImpl implements WechatComment {
	/**
	 * 令牌地址
	 */
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	/**
	 * 票据地址
	 */
	private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	private AccessToken accessToken;
	private Ticket ticket;

	/**
	 * 
	 * 描述:定时获取微信配置,每隔5秒执行一次
	 * 
	 * @author liyixing 2017年1月6日 下午6:04:02
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 5000)
	public void getWechatTokenAndTicket() throws Exception {
		if (accessToken == null
				|| accessToken.isExpires() || ticket == null || ticket.isExpires()) {
			accessToken = getAccessToken();
			ticket = getTicket(accessToken.getAccess_token());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public AccessToken getAccessToken() throws Exception {
		// 添加?
		String url = UtilUrl.addParameterStartCharacter(TOKEN_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("grant_type", "client_credential");
		params.put("appid", appId);
		params.put("secret", accessKeySecret);
		url += UtilUrl.mapToUrl(params, "utf-8");
		String result = UtilHttpClient.httpRequestGet(url);
		// 解析json
		Map<String, Object> map = OBJECTMAPPER.readValue(result, Map.class);
		Integer errcode = (Integer) map.get("errcode");

		if (errcode != null && errcode != 0) {
			throw new RuntimeException("微信token获取失败，错误码：" + errcode + ","
					+ map.get("errmsg"));
		}

		String access_token = (String) map.get("access_token");
		Integer expires_in = (Integer) map.get("expires_in");
		AccessToken accessToken = new AccessToken();

		accessToken.setAccess_token(access_token);
		accessToken.setGetDateTime(UtilDateTime.getNowDate());
		accessToken.setExpiresIn(expires_in);

		return accessToken;
	}

	@Override
	public Ticket getTicket(String access_token) throws Exception {
		// 添加?
		String url = UtilUrl.addParameterStartCharacter(TICKET_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("access_token", access_token);
		params.put("type", "jsapi");
		url += UtilUrl.mapToUrl(params, "utf-8");
		String result = UtilHttpClient.httpRequestGet(url);
		// 解析json
		@SuppressWarnings("unchecked")
		Map<String, Object> map = OBJECTMAPPER.readValue(result, Map.class);
		Integer errcode = (Integer) map.get("errcode");

		if (errcode != null && errcode != 0) {
			throw new RuntimeException("微信ticket获取失败，错误码：" + errcode + ","
					+ map.get("errmsg"));
		}

		String jticket = (String) map.get("ticket");
		Integer expires_in = (Integer) map.get("expires_in");
		WechatComment.Ticket ticket = new Ticket();

		ticket.setTicket(jticket);
		ticket.setGetDateTime(UtilDateTime.getNowDate());
		ticket.setExpiresIn(expires_in);

		return ticket;
	}

	@Override
	public Config getConfig(String url) throws Exception {
		if (ticket == null) {
			UtilLog.debug("票据正在获取，稍后再试");

			return null;
		}

		String jsapi_ticket = ticket.getTicket();
		String noncestr = UtilEncrypt.encode(UUID.randomUUID().toString());
		long timestamp = UtilDateTime.getNowDate().getTime()/1000;

		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("noncestr", noncestr);
		params.put("timestamp", timestamp);
		params.put("url", url);

		String s = UtilUrl.mapToUrlNoEncode(params, "utf-8");
		String signature = UtilEncrypt.sha1(s);

		Config config = new Config();

		config.setAppId(appId);
		config.setNoncestr(noncestr);
		config.setSignature(signature);
		config.setTimestamp(timestamp);
		config.setUrl(url);

		return config;
	}

	@Value("${weixin.appId}")
	private String appId = "";
	@Value("${weixin.appSecret}")
	private String accessKeySecret = "";
}
