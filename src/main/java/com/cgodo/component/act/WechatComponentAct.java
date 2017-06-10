package com.cgodo.component.act;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.component.WechatComponent;
import com.cgodo.component.model.WechatNotifyModel;
import com.cgodo.constant.EnumSpeakingStatus;
import com.cgodo.constant.EnumWechatNotifyType;
import com.cgodo.counter.service.CounterService;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.model.WechatUserinfoModel;
import com.cgodo.member.service.UserinfoService;
import com.cgodo.member.service.WechatUserinfoService;
import com.cgodo.member.shiro.UserTypeTUsernamePasswordToken;
import com.cgodo.util.UtilEncrypt;
import com.cgodo.util.UtilHttpClient;
import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilObject;
import com.cgodo.util.UtilUrl;

@Component
@RequestMapping("/wechat_component")
public class WechatComponentAct {
	/**
	 * 授权地址
	 */
	private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";
	/**
	 * openid获取地址,获取access_token的时候就会同时返回openid
	 * 
	 */
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
	/**
	 * 拉取用户信息
	 */
	private static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo";
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private static final String DEFAULT_USER_TYPE = "前端用户";

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
	
	/**
	 * 
	 * 描述:获取jsconfig
	 * 
	 * @param url
	 * @author liyixing 2017年1月6日 下午6:31:02
	 * @throws Exception 
	 */
	@RequestMapping("/config.html")
	public void config(String url,ModelMap modelMap) throws Exception {
		modelMap.put("wechatConfig", wechatComponent.getConfig(url));
	}
	
	/**
	 * 
	 * 描述:支付通知
	 * 
	 * @param url
	 * @author liyixing 2017年1月6日 下午6:31:02
	 * @throws Exception 
	 */
	@RequestMapping("/pay_notify.jhtml")
	public void pay_notify(@RequestBody String xml,ModelMap modelMap,HttpServletResponse response) throws Exception {
		UtilLog.debug("回调xml", xml);
		WechatNotifyModel wechatNotifyModel = new WechatNotifyModel();
		
		wechatNotifyModel.setParams(xml);
		wechatNotifyModel.setType(EnumWechatNotifyType.统一下单);
		wechatComponent.doNotify(wechatNotifyModel);
		send(response, "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
	}
	
	public void send(HttpServletResponse response,String xml) throws IOException {
		response.getWriter().write(xml);
	}
	


	@RequestMapping("/wechat_authorize_redirect.html")
	public String wechatAuthorizeRedirect(HttpServletRequest request,
			String code, String state, String realurl, String scope)
			throws Exception {
		if (StringUtils.isNotBlank(realurl)) {
			return "redirect:" + realurl + "?code=" + code + "&state=" + state
					+ "&scope=" + scope;
		}

		Map<String, Object> map = getBase(code);
		Map<String, Object> mapUserInfo = null;
		String openId = (String) map.get("openid");
		Integer errcode = (Integer) map.get("errcode");

		if (errcode != null && errcode != 0) {
			throw new RuntimeException("微信登陆失败，错误码：" + errcode + ","
					+ map.get("errmsg"));
		}
		//
		if (StringUtils.equals("snsapi_userinfo", scope)) {
			mapUserInfo = getUserInfo(openId, (String) map.get("access_token"));
		}

		errcode = (Integer) mapUserInfo.get("errcode");

		if (errcode != null && errcode != 0) {
			throw new RuntimeException("微信登陆失败，错误码：" + errcode + ","
					+ map.get("errmsg"));
		}

		// 加锁，防止重复创建
		synchronized (UtilObject.getLockObject(openId)) {
			WechatUserinfoModel wechatUserinfoModel = wechatUserinfoService
					.getByOpenId(openId);
			UserinfoModel userinfoModel;

			if (wechatUserinfoModel == null) {
				// 创建用户并创建微信用户
				userinfoModel = new UserinfoModel();
				wechatUserinfoModel = new WechatUserinfoModel();

				userinfoModel.setFirstTime(new Date());
				userinfoModel.setImgUrl(avatar_default);
				userinfoModel.setLastTime(new Date());
				userinfoModel.setNickName("匿名用户");
				userinfoModel.setPassword(UtilEncrypt.encode("cgodo"));
				userinfoModel.setSpeakingStatus(EnumSpeakingStatus.未禁言);
				userinfoModel.setUserName("wechat_" + openId);
				userinfoModel.setUserType(DEFAULT_USER_TYPE);
				wechatUserinfoModel.setOpenId(openId);

				if (StringUtils.equals("snsapi_userinfo", scope)) {
					userinfoModel.setNickName((String) mapUserInfo
							.get("nickname"));
					userinfoModel.setImgUrl((String) mapUserInfo
							.get("headimgurl"));
				}

				wechatUserinfoService.createAndBindWechat(userinfoModel,
						wechatUserinfoModel);
			} else {
				userinfoModel = userinfoService.getById(wechatUserinfoModel
						.getUserId());
			}

			// 登陆
			String host = request.getRemoteHost();
			UserTypeTUsernamePasswordToken userTypeTUsernamePasswordToken = new UserTypeTUsernamePasswordToken(
					userinfoModel.getUserName(), userinfoModel.getPassword(),
					true, host);
			org.apache.shiro.subject.Subject subject = SecurityUtils
					.getSubject();

			userTypeTUsernamePasswordToken.setUserType(DEFAULT_USER_TYPE);
			subject.login(userTypeTUsernamePasswordToken);
		}

		return "redirect:/index.html";
	}

	/**
	 * 拉取用户信息
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getUserInfo(String openId, String accessToken)
			throws UnsupportedEncodingException, Exception, IOException,
			JsonParseException, JsonMappingException {
		// 添加?
		String url = UtilUrl.addParameterStartCharacter(USERINFO_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		params.put("secret", accessKeySecret);
		params.put("access_token", accessToken);
		params.put("openid", openId);
		params.put("lang", "zh_CN");
		url += UtilUrl.mapToUrl(params, "utf-8");
		String result = UtilHttpClient.httpRequestGet(url);
		// 解析json
		Map<String, Object> map = OBJECTMAPPER.readValue(result, Map.class);
		return map;
	}

	/**
	 * 获取微信基本 数据
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getBase(String code)
			throws UnsupportedEncodingException, Exception, IOException,
			JsonParseException, JsonMappingException {
		// 添加?
		String url = UtilUrl.addParameterStartCharacter(ACCESS_TOKEN_URL);
		// 保证顺序，否则出错
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("appid", appId);
		params.put("secret", accessKeySecret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		url += UtilUrl.mapToUrl(params, "utf-8");
		String result = UtilHttpClient.httpRequestGet(url);
		// 解析json
		Map<String, Object> map = OBJECTMAPPER.readValue(result, Map.class);
		return map;
	}

	/**
	 * 前端需要的配置信息
	 * 
	 * @param request
	 * @param code
	 * @param state
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wechat_config.html")
	public void wechatConfig(ModelMap modelMap) throws Exception {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		// 添加appid
		params.put("debug", false);
		params.put("appId", appId);
		params.put("timestamp", new Date().getTime());
		params.put("nonceStr",
				UtilEncrypt.encode(new Date().getTime() + "cogodo"));

		modelMap.put("config", params);
	}
	
	@Autowired
	private WechatComponent wechatComponent;
	@Value("${weixin.appId}")
	private String appId = "";
	@Value("${weixin.appSecret}")
	private String accessKeySecret = "";
	/** 授权对应的域名只有改域名才可以参与returnUri*/
	@Value("${weixin.domain}")
	private String domain = "";
	/**
	 * 默认头像
	 */
	@Value("${avatar_default.png}")
	private String avatar_default = "";
	@Autowired
	private WechatUserinfoService wechatUserinfoService;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private CounterService counterService;
}
