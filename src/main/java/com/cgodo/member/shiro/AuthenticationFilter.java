package com.cgodo.member.shiro;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.cgodo.member.shiro.UserTypeTUsernamePasswordToken;
import com.cgodo.util.UtilHttpResponse;

/**
 * 自定义登录认证filter<br>
 * 默认用户字段username <br>
 * 默认密码字段password <br>
 * 默认记住我rememberMe
 */
public class AuthenticationFilter extends FormAuthenticationFilter {
	/**
	 * 判断是否登录地址，如果是登录地址，excuteLogin才会执行
	 */
	protected boolean isLoginRequest(ServletRequest request,
			ServletResponse response) {
		boolean isLoginRequest = super.isLoginRequest(request, response);

		if (!isLoginRequest) {
			for (String loginUrl : loginUrls) {
				if (pathsMatch(loginUrl, request)) {
					return true;
				}
			}
		}

		return isLoginRequest;
	}

	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
//		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		UserTypeTUsernamePasswordToken userTypeTUsernamePasswordToken = new UserTypeTUsernamePasswordToken(
				username, password, true, host);
		String url = getPathWithinApplication(request);

		userTypeTUsernamePasswordToken.setUserType(loginUrlOfUserType.get(url));

		return userTypeTUsernamePasswordToken;
	}

	/**
	 * 返回URL
	 */
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);

		if (token == null) {
			String msg = "create AuthenticationToken error";
			throw new IllegalStateException(msg);
		}

		try {
			Subject subject = getSubject(request, response);
			// 登录
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	/**
	 * 成功登录
	 */
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = getPathWithinApplication(request);
		String successUrl = successUrlOfLoginUrl.get(url);
		String contextpath = httpServletRequest.getContextPath();
		
		if(StringUtils.isBlank(contextpath)) {
			contextpath = "/";
		}
		
		if(!contextpath.endsWith("/")) {
			contextpath = contextpath + '/';
		}
		
		HttpServletResponse res = (HttpServletResponse) response;
		WebUtils.getAndClearSavedRequest(request);
		UtilHttpResponse.renderJson(res,
				"{\"returnUrl\":\"[]\"}".replace("[]", contextpath+successUrl));
		return false;
	}

	/**
	 * 判断用户时候已经登录过了<br>
	 * 改写成已经登录则替换<br>
	 * 源代码<br>
	 * Subject subject = getSubject(request, response);<br>
	 * return subject.isAuthenticated(); <br>
	 * 返回false表示未登录
	 */
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		return false;
	}

	/**
	 * 登录失败
	 */
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;
		UtilHttpResponse.renderJson(res, "{\"errors\":\"用户名或密码错误\"}");
		return false;
	}

	/**
	 * 默认登录地址之外的登录地址
	 */
	private Set<String> loginUrls;

	/**
	 * 登录地址与用户类型的关系
	 */
	private Map<String, String> loginUrlOfUserType;

	/**
	 * 登录地址与成功返回页面的关系
	 */
	private Map<String, String> successUrlOfLoginUrl;

	public Set<String> getLoginUrls() {
		return loginUrls;
	}

	public void setLoginUrls(Set<String> loginUrls) {
		this.loginUrls = loginUrls;
	}

	public Map<String, String> getLoginUrlOfUserType() {
		return loginUrlOfUserType;
	}

	public void setLoginUrlOfUserType(Map<String, String> loginUrlOfUserType) {
		this.loginUrlOfUserType = loginUrlOfUserType;
	}

	public Map<String, String> getSuccessUrlOfLoginUrl() {
		return successUrlOfLoginUrl;
	}

	public void setSuccessUrlOfLoginUrl(Map<String, String> successUrlOfLoginUrl) {
		this.successUrlOfLoginUrl = successUrlOfLoginUrl;
	}
}
