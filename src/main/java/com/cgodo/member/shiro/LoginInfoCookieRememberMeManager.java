package com.cgodo.member.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.service.UserinfoService;
import com.cgodo.util.UtilLog;

/**
 * 
 * 
 * 描述:记住我功能，同时记录用户信息
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年1月6日 下午4:27:21
 */
public class LoginInfoCookieRememberMeManager extends
		org.apache.shiro.web.mgt.CookieRememberMeManager {
	public PrincipalCollection getRememberedPrincipals(
			SubjectContext subjectContext) {
		PrincipalCollection principals = super.getRememberedPrincipals(subjectContext);
		
//		if(principals != null && StringUtils.isNotBlank((String)principals.getPrimaryPrincipal())){
//			UserinfoModel userinfoModel = userinfoService.getById((String)principals.getPrimaryPrincipal());
//			
//			userinfoModel.setLastTime(UtilDateTime.getNowDate());
//			userinfoService.update(userinfoModel);
//		}
		
		return principals;
	}
	
	/**
     * Base64-encodes the specified serialized byte array and sets that base64-encoded String as the cookie value.
     * <p/>
     * The {@code subject} instance is expected to be a {@link WebSubject} instance with an HTTP Request/Response pair
     * so an HTTP cookie can be set on the outgoing response.  If it is not a {@code WebSubject} or that
     * {@code WebSubject} does not have an HTTP Request/Response pair, this implementation does nothing.
     *
     * @param subject    the Subject for which the identity is being serialized.
     * @param serialized the serialized bytes to be persisted.
     */
    protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {

        if (!WebUtils.isHttp(subject)) {
        	String msg = "Subject argument is not an HTTP-aware instance.  This is required to obtain a servlet " +
                    "request and response in order to set the rememberMe cookie. Returning immediately and " +
                    "ignoring rememberMe operation.";
        	
        	UtilLog.debug(msg);
            return;
        }


        HttpServletRequest request = WebUtils.getHttpRequest(subject);
        HttpServletResponse response = WebUtils.getHttpResponse(subject);

        //base 64 encode it and store as a cookie:
        String base64 = Base64.encodeToString(serialized);

        Cookie template = getCookie(); //the class attribute is really a template for the outgoing cookies
        Cookie cookie = new SimpleCookie(template);
        cookie.setValue(base64);
        cookie.setDomain(domain);
        cookie.saveTo(request, response);
    }


	@Autowired
	private UserinfoService userinfoService;

	private String domain;
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
