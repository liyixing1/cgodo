package com.cgodo.component.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cgodo.component.WechatComponent;
import com.cgodo.component.listen.WechatComponentListener;
import com.cgodo.component.model.WechatCallModel;
import com.cgodo.component.model.WechatNotifyModel;
import com.cgodo.component.service.WechatCallService;
import com.cgodo.component.service.WechatNotifyService;
import com.cgodo.constant.EnumWechatInterfaceType;
import com.cgodo.constant.EnumWechatNotifyType;
import com.cgodo.util.UtilDateTime;
import com.cgodo.util.UtilDom4j;
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
public class WechatComponentImpl implements WechatComponent {
	/**
	 * 令牌地址
	 */
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	/**
	 * 统一下单地址
	 */
	private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 查询订单地址
	 */
	private static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 关闭订单地址
	 */
	private static final String CLOSEORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder ";
	/**
	 * 票据地址
	 */
	private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	private AccessToken accessToken;
	private Ticket ticket;

	/**
	 * 微信公众号支付签名算法(详见:http://pay.weixin.qq.com/wiki/doc/api/index.php?chapter=
	 * 4_3)
	 * 
	 * @param packageParams
	 *            原始参数
	 * @param signKey
	 *            加密Key(即 商户Key)
	 * @param charset
	 *            编码
	 * @return 签名字符串
	 */
	public String createSign(String paramsUrl) {
		String sign = DigestUtils.md5Hex(paramsUrl + "&key=" + password)
				.toUpperCase();
		return sign;
	}

	@Override
	public boolean validationSign(Map<String, Object> paramsMap) {
		String sign = createSign(UtilUrl.mapToUrlNoEncode(paramsMap, "utf-8", "sign"));
		return paramsMap.get("sign").toString().equals(sign);
	}

	/**
	 * 
	 * 描述:定时获取微信配置,每隔5秒执行一次
	 * 
	 * @author liyixing 2017年1月6日 下午6:04:02
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 5000)
	public void getWechatTokenAndTicket() throws Exception {
		if (accessToken == null || accessToken.isExpires() || ticket == null
				|| ticket.isExpires()) {
			accessToken = getAccessToken();
			ticket = getTicket(accessToken.getAccess_token());
		}
	}

	@Override
	public AccessToken getAccessToken() throws Exception {
		WechatCallModel wechatCallModel = new WechatCallModel();

		// 添加?
		String url = UtilUrl.addParameterStartCharacter(TOKEN_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("grant_type", "client_credential");
		params.put("appid", appId);
		params.put("secret", accessKeySecret);
		String paramsUrl = UtilUrl.mapToUrl(params, "utf-8");
		url += paramsUrl;
		wechatCallModel.setParams(paramsUrl);
		wechatCallModel.setType(EnumWechatInterfaceType.获取令牌);
		String result = UtilHttpClient.httpRequestGet(url);
		wechatCallModel.setResultJson(result);
		wechatCallService.add(wechatCallModel);
		// 解析json
		Integer errcode = wechatCallModel.getWechatModel().getErrcode();

		if (errcode != null && errcode != 0) {
			throw new RuntimeException("微信token获取失败，错误码：" + errcode + ","
					+ wechatCallModel.getWechatModel().getErrmsg());
		}

		String access_token = wechatCallModel.getWechatModel().getAccess_token();
		Integer expires_in = wechatCallModel.getWechatModel().getExpires_in();
		AccessToken accessToken = new AccessToken();

		accessToken.setAccess_token(access_token);
		accessToken.setGetDateTime(UtilDateTime.getNowDate());
		accessToken.setExpiresIn(expires_in);

		return accessToken;
	}

	@Override
	public Ticket getTicket(String access_token) throws Exception {
		WechatCallModel wechatCallModel = new WechatCallModel();
		// 添加?
		String url = UtilUrl.addParameterStartCharacter(TICKET_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("access_token", access_token);
		params.put("type", "jsapi");
		String paramsUrl = UtilUrl.mapToUrl(params, "utf-8");
		url += paramsUrl;
		wechatCallModel.setParams(paramsUrl);
		wechatCallModel.setType(EnumWechatInterfaceType.获取票据);
		String result = UtilHttpClient.httpRequestGet(url);
		wechatCallModel.setResultJson(result);
		wechatCallService.add(wechatCallModel);
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
		WechatComponent.Ticket ticket = new Ticket();

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
		long timestamp = UtilDateTime.getNowDate().getTime() / 1000;

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

	@Override
	public WechatCallModel unifiedorder(String orderId, String body,
			BigDecimal totalPrice, String ip, String openId, String userId)
			throws Exception {
		WechatCallModel wechatCallModel = new WechatCallModel();
		// 保证顺序，否则出错
		// 参数名ASCII码从小到大排序（字典序）；
		Map<String, Object> params = new TreeMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("device_info", "WEB");
		params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		params.put("body", body);
		params.put("openid", openId);
		params.put("out_trade_no", orderId);
		// total_fee订单总金额，单位为分，只能为整数
		params.put("total_fee", totalPrice.multiply(BigDecimal.valueOf(100))
				.intValue());
		params.put("spbill_create_ip", ip);
		params.put("notify_url", unifiedorderNotifyUrl);
		params.put("trade_type", "JSAPI");
		String paramsUrl = UtilUrl.mapToUrlNoEncode(params, "utf-8");
		String sign = createSign(paramsUrl);
		params.put("sign", sign);

		String xml = UtilDom4j.map2XmlBody(params, "xml");
		wechatCallModel.setParams(xml);
		String result = UtilHttpClient
				.httpRequestPostXML(UNIFIEDORDER_URL, xml);
		wechatCallModel.setType(EnumWechatInterfaceType.统一下单);
		wechatCallModel.setExternalNo(orderId);
		wechatCallModel.setResult(result);
		wechatCallModel.setUserId(userId);
		wechatCallService.add(wechatCallModel);

		return wechatCallModel;
	}

	@Override
	public WechatCallModel closeorder(String orderId, String ip, String userId)
			throws Exception {
		WechatCallModel wechatCallModel = new WechatCallModel();
		// 保证顺序，否则出错
		// 参数名ASCII码从小到大排序（字典序）；
		Map<String, Object> params = new TreeMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		params.put("out_trade_no", orderId);
		String paramsUrl = UtilUrl.mapToUrlNoEncode(params, "utf-8");
		String sign = createSign(paramsUrl);
		params.put("sign", sign);

		String xml = UtilDom4j.map2XmlBody(params, "xml");
		wechatCallModel.setParams(xml);
		String result = UtilHttpClient.httpRequestPostXML(CLOSEORDER_URL, xml);
		wechatCallModel.setType(EnumWechatInterfaceType.关闭订单);
		wechatCallModel.setExternalNo(orderId);
		wechatCallModel.setResult(result);
		wechatCallModel.setUserId(userId);
		wechatCallService.add(wechatCallModel);

		return wechatCallModel;
	}

	@Override
	public WechatCallModel orderquery(String orderId, String transactionId,
			String ip, String userId) throws Exception {
		WechatCallModel wechatCallModel = new WechatCallModel();
		// 保证顺序，否则出错
		// 参数名ASCII码从小到大排序（字典序）；
		Map<String, Object> params = new TreeMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		params.put("mch_id", mchId);
		params.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));

		if (StringUtils.isNotBlank(orderId)) {
			params.put("out_trade_no", orderId);
		} else {
			params.put("transaction_id", transactionId);
		}

		String paramsUrl = UtilUrl.mapToUrlNoEncode(params, "utf-8");
		String sign = createSign(paramsUrl);
		params.put("sign", sign);

		String xml = UtilDom4j.map2XmlBody(params, "xml");
		wechatCallModel.setParams(xml);
		String result = UtilHttpClient.httpRequestPostXML(ORDERQUERY_URL, xml);
		wechatCallModel.setType(EnumWechatInterfaceType.查询订单);
		wechatCallModel.setExternalNo(orderId);
		wechatCallModel.setResult(result);
		wechatCallModel.setUserId(userId);
		wechatCallService.add(wechatCallModel);

		return wechatCallModel;
	}

	@Override
	public void doNotify(WechatNotifyModel wechatNotifyModel) {
		// 校验签名
		if (!validationSign(wechatNotifyModel.getParamsMap())) {
			wechatNotifyModel.setResult("FAIL:SIGN_ERROR");
			wechatNotifyService.add(wechatNotifyModel);
			
			return;
		}

		try {
			for (WechatComponentListener wechatCommonListener : wechatCommonListeners) {
				if (wechatNotifyModel.getType() == EnumWechatNotifyType.统一下单) {
					wechatCommonListener
							.onUnifiedOrderNotify(wechatNotifyModel);
				}
			}
		} catch (Exception e) {
			UtilLog.error("微信回调处理失败", e);
			wechatNotifyModel.setResult("ERROR:" + e.getMessage());
			wechatNotifyService.add(wechatNotifyModel);
			throw new RuntimeException(e);
		}

		wechatNotifyModel.setResult("SUCCESS");
		wechatNotifyService.add(wechatNotifyModel);
	}

	public static void main(String[] args) throws Exception {
		String result = UtilHttpClient
				.httpRequestPostXML(
						"http://yiliao.nalanyuan.com.cn/wechat_component/pay_notify.jhtml",
						"<xml> <appid><![CDATA[wxbdfa4fa5f118146b]]></appid> <attach><![CDATA[支付测试]]></attach> <bank_type><![CDATA[CFT]]></bank_type> <fee_type><![CDATA[CNY]]></fee_type> <is_subscribe><![CDATA[Y]]></is_subscribe> <mch_id><![CDATA[10000100]]></mch_id> <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str> <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid> <out_trade_no><![CDATA[1409811653]]></out_trade_no> <result_code><![CDATA[SUCCESS]]></result_code> <return_code><![CDATA[SUCCESS]]></return_code> <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign> <sub_mch_id><![CDATA[10000100]]></sub_mch_id> <time_end><![CDATA[20140903131540]]></time_end> <total_fee>1</total_fee> <trade_type><![CDATA[JSAPI]]></trade_type> <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id> </xml>");
		
		System.out.println(result);
	}

	@Value("${weixin.appId}")
	private String appId = "";
	@Value("${weixin.appSecret}")
	private String accessKeySecret = "";
	@Value("${weixin.mchId}")
	private String mchId = "";
	@Value("${weixin.certPath}")
	private String certPath;
	@Value("${weixin.certPassword}")
	private String password;
	// 统一下单通知地址
	@Value("${weixin.unifiedorderNotifyUrl}")
	private String unifiedorderNotifyUrl;
	@Autowired
	private WechatCallService wechatCallService;
	@Autowired
	private List<WechatComponentListener> wechatCommonListeners;
	@Autowired
	private WechatNotifyService wechatNotifyService;
}
