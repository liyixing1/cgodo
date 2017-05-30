package com.cgodo.springmvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.cgodo.util.UtilLog;

public class ExceptionHandler extends SimpleMappingExceptionResolver{
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if(ex instanceof UnauthorizedException || ex instanceof UnauthenticatedException) {
			String permission = ex.getMessage().replace("Subject does not have permission ", "");
			UtilLog.debug("没有权限【"+permission+"】");
			ModelAndView model = new ModelAndView("", new ModelMap());
			List<String> errors = new ArrayList<String>();
			errors.add("没有权限【"+permission+"】");
			model.getModelMap().put("errors", errors);
			return model;
		} else {
			UtilLog.error("统一异常处理modelview", ex);
			ModelAndView model = new ModelAndView("/ticket/error", new ModelMap());
			List<String> errors = new ArrayList<String>();
			errors.add("系统繁忙，请重试！");
			model.getModelMap().put("errors", errors);
			return model;
		}
	}
}
