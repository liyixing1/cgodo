package com.cgodo.member.act;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.util.UtilEncrypt;
import com.cgodo.util.UtilUrl;

/**
 * 微信登陆相关服务
 * 
 * @author liyixing-pc
 *
 */
@Controller
public class WechatLoginAct {
	/**
	 * 授权地址
	 */
	private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

	/**
	 * 登录操作，进入返回授权拼接参数后的页面
	 * 
	 * 
	 * @param code
	 * @param state
	 * @param realurl 由于微信不支持子域名跳转，因此添加该参数，如果传入的该参数，会在生成的redirect_uri中添加该参数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/wechat_login.html")
	public String login(String realurl,Boolean userinfo)
			throws UnsupportedEncodingException {
		// 添加?
		String returnUrl = UtilUrl.addParameterStartCharacter(AUTHORIZE_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		String  redirect_uri = "http://" + domain
				+ "/wechat_authorize_redirect.html" + (StringUtils.isBlank(realurl) ? "" : "?realurl="+realurl);
		
		if(userinfo == null || !userinfo) {
			redirect_uri = UtilUrl.addParam(redirect_uri, "scope", "snsapi_base");
		} else {
			redirect_uri = UtilUrl.addParam(redirect_uri, "scope", "snsapi_userinfo");
		}
		
		params.put("redirect_uri", redirect_uri);
		params.put("response_type", "code");
		if(userinfo == null || !userinfo) {
			params.put("scope", "snsapi_base");
		} else {
			params.put("scope", "snsapi_userinfo");
		}
		params.put("state", UtilEncrypt.encode(new Date().getTime() + "cgodo"));

		returnUrl += UtilUrl.mapToUrl(params, "utf-8") + "#wechat_redirect";

		return "redirect:" + returnUrl;
	}

	@Value("${weixin.appId}")
	private String appId = "";
	@Value("${weixin.appSecret}")
	private String accessKeySecret = "";
	/** 授权对应的域名只有改域名才可以参与returnUri*/
	@Value("${weixin.domain}")
	private String domain = "";
}
