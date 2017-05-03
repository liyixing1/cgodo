package com.cgodo.member.shiro;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SubjectContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.service.UserinfoService;

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
	
	@Autowired
	private UserinfoService userinfoService;
}
