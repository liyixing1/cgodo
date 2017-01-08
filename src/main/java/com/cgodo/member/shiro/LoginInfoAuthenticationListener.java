package com.cgodo.member.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;
import com.cgodo.util.UtilDateTime;
import com.cgodo.util.UtilLog;

/**
 * 
 * 
 * 描述:记录用户登陆信息
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年1月6日 下午3:55:59
 */
public class LoginInfoAuthenticationListener implements AuthenticationListener{

	@Override
	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		UtilLog.debug("登陆成功");
		UserinfoModel userinfoModel = userinfoService.getByUserName((String) token.getPrincipal());
		
		if(userinfoModel != null) {
			userinfoModel.setLastTime(UtilDateTime.getNowDate());
			userinfoService.update(userinfoModel);
		}
	}

	@Override
	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
		
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		
	}

	@Autowired
	private UserinfoService userinfoService;
}
