package com.cgodo.member.act;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.util.UtilUrl;

/**
 * 新浪登陆相关服务
 * 
 * @author liyixing-pc
 *
 */
@Controller
public class SinaLoginAct {
	/**
	 * 授权地址
	 */
	private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize";

	/**
	 * 登录操作，进入返回授权拼接参数后的页面
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/sina_login.html")
	public String login()
			throws UnsupportedEncodingException {
		// 添加
		String returnUrl = UtilUrl.addParameterStartCharacter(AUTHORIZE_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("client_id", appKey);
		params.put("response_type", "code");
		params.put("redirect_uri", "http://" + domain
				+ "/sina_authorize_redirect.html");
		
		returnUrl += UtilUrl.mapToUrl(params, "utf-8");

		return "redirect:" + returnUrl;
	}

	@Value("${sina.appKey}")
	private String appKey = "";
	@Value("${sina.appSercet}")
	private String accessKeySecret = "";
	/** 授权对应的域名只有改域名才可以参与returnUri*/
	@Value("${sina.domain}")
	private String domain = "";
}
