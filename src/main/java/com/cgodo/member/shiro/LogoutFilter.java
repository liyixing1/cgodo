package com.cgodo.member.shiro;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

/**
 * CmsUserFilter
 */
public class LogoutFilter extends
		org.apache.shiro.web.filter.authc.LogoutFilter {
	protected String getRedirectUrl(ServletRequest req, ServletResponse resp,
			Subject subject) {
		HttpServletRequest request = (HttpServletRequest) req;
		// 是否指定前缀的跳转，比如/admin开头的跳转到/admin/login.html
		String key = getKey(request);
		String redirectUrl = prefixRedirectUrlMaps.get(key);
		if (redirectUrl == null) {
			// 默认地址
			redirectUrl = DEFAULT_REDIRECT_URL;
		}

		return redirectUrl;
	}
	
	private String getKey(HttpServletRequest request) {
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		for(String key : prefixRedirectUrlMaps.keySet()) {
			if(url.startsWith(key)) {
				return key;
			}
		}
		
		return null;
	}

	private static final String DEFAULT_REDIRECT_URL = "/index.html";
	/**
	 * 指定前缀跳转地址
	 */
	private Map<String, String> prefixRedirectUrlMaps;

	public Map<String, String> getPrefixRedirectUrlMaps() {
		return prefixRedirectUrlMaps;
	}

	public void setPrefixRedirectUrlMaps(
			Map<String, String> prefixRedirectUrlMaps) {
		this.prefixRedirectUrlMaps = prefixRedirectUrlMaps;
	}
}
