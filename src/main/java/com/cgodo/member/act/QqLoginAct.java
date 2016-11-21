package com.cgodo.member.act;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.util.UtilEncrypt;
import com.cgodo.util.UtilHttpRequest;
import com.cgodo.util.UtilUrl;

/**
 * QQ登陆相关服务
 * 
 * @author liyixing-pc
 *
 */
@Controller
public class QqLoginAct {
	/**
	 * 授权地址
	 */
	private static final String AUTHORIZE_URL_PC = "https://graph.qq.com/oauth2.0/authorize";
	/**
	 * 手机端授权
	 */
	private static final String AUTHORIZE_URL_MOBILE = "https://graph.z.qq.com/moc2/authorize";

	/**
	 * 登录操作，进入返回授权拼接参数后的页面
	 * 
	 * @param code
	 * @param state
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/qq_login.html")
	public String login(HttpServletRequest request)
			throws UnsupportedEncodingException {
		// 添加?
		String returnUrl = UtilUrl.addParameterStartCharacter(UtilHttpRequest.isMobile(request) ?  UtilUrl.addParameterStartCharacter(AUTHORIZE_URL_MOBILE) : AUTHORIZE_URL_PC);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("response_type", "code");
		params.put("client_id", appId);
		params.put("redirect_uri", "http://" + domain
				+ "/qq_authorize_redirect.html");
		params.put("state", UtilEncrypt.encode(new Date().getTime() + "cgodo"));
		params.put("scope", "get_user_info");

		returnUrl += UtilUrl.mapToUrl(params, "utf-8");

		return "redirect:" + returnUrl;
	}

	@Value("${qq.appId}")
	private String appId = "";
	@Value("${qq.appkey}")
	private String accessKey = "";
	/** 授权对应的域名只有改域名才可以参与returnUri*/
	@Value("${qq.domain}")
	private String domain = "";
}
