package com.cgodo.member.shiro;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;

/**
 * UserFilter
 */
public class UserFilter extends org.apache.shiro.web.filter.authc.UserFilter {
	// 未登录重定向到登陆页
	protected void redirectToLogin(ServletRequest req, ServletResponse resp)
			throws IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//是否指定前缀的跳转，比如/admin开头的跳转到/admin/login.html
		String loginUrl = getLoginUrl(request);
		String format = request.getParameter("format");
		
		if(StringUtils.isNotBlank(format)){
			loginUrl += "?format="+format;
		}

		WebUtils.issueRedirect(request, response, loginUrl);
	}
	
	/**
     * 前置处理，判断用户是否登陆
     */
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    	if(isAccessAllowed(request, response, mappedValue) ) {
    		//已经登陆
    		//如果用户类型合当前前缀不匹配，也需要登陆
    		String userType = getUserType((HttpServletRequest) request);
    		Subject subject = SecurityUtils.getSubject();
    		UserinfoModel userinfoModel = userinfoService.getById(String.valueOf(subject.getPrincipal()));
    		
    		if(userinfoModel.getUserType().equals(userType)) {
    			return true;
    		} else {
    			//未登录，进行登陆操作
        		return onAccessDenied(request, response, mappedValue);
    		}
    	} else {
    		//未登录，进行登陆操作
    		return onAccessDenied(request, response, mappedValue);
    	}
    }
    
    /**
     * 获取登陆地址
     * @param request
     * @return
     */
	private String getLoginUrl(HttpServletRequest request) {
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		for(String key : prefixLoginUrlMaps.keySet()) {
			if(url.startsWith(key)) {
				return prefixLoginUrlMaps.get(key);
			}
		}
		
		return getLoginUrl();
	}
	
	/**
	 * 获取用户类型
	 * @param request
	 * @return
	 */
	private String getUserType(HttpServletRequest request) {
		String url = WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
		for(String key : prefixOfUserTypes.keySet()) {
			if(url.startsWith(key)) {
				return prefixOfUserTypes.get(key);
			}
		}
		
		return defaultUserType;
	}
	
	@Autowired
	private UserinfoService userinfoService;

	
	/**
	 * 指定前缀跳转地址
	 */
	private Map<String,String> prefixLoginUrlMaps;
	
	/**
	 * 前缀与用户类型的关系
	 */
	private Map<String, String> prefixOfUserTypes;
	
	/**
	 * 默认用户类型，前缀无法匹配时使用该用户类型
	 */
	private String defaultUserType;

	public Map<String, String> getPrefixLoginUrlMaps() {
		return prefixLoginUrlMaps;
	}

	public void setPrefixLoginUrlMaps(Map<String, String> prefixLoginUrlMaps) {
		this.prefixLoginUrlMaps = prefixLoginUrlMaps;
	}

	public Map<String, String> getPrefixOfUserTypes() {
		return prefixOfUserTypes;
	}

	public void setPrefixOfUserTypes(Map<String, String> prefixOfUserTypes) {
		this.prefixOfUserTypes = prefixOfUserTypes;
	}

	public String getDefaultUserType() {
		return defaultUserType;
	}

	public void setDefaultUserType(String defaultUserType) {
		this.defaultUserType = defaultUserType;
	}
}
