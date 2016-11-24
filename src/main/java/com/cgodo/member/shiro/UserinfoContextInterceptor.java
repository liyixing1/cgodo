package com.cgodo.member.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;

/**
 * 用户拦截器，用来把用户信息放到request等
 * @author liyixing-pc
 *
 */
public class UserinfoContextInterceptor extends HandlerInterceptorAdapter {

	private final static String USER_KEY = "user";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		
		if (subject == null || subject.getPrincipal() == null) {
			return true;
		}
		
		UserinfoModel userinfoModel = userinfoService.getById(String.valueOf(subject.getPrincipal()));

		if(userinfoModel != null) {
			//隱藏密码
			userinfoModel.setPassword("********");
			request.setAttribute(USER_KEY, userinfoModel);
		} else {
			subject.logout();
		}
		
		return super.preHandle(request, response, handler);
	}

	@Autowired
	private UserinfoService userinfoService;
}
