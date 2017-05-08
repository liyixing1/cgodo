package com.cgodo.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cgodo.util.UtilDateTime;

/**
 * 
 * 
 * 描述:初始化时间参数
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年7月30日 下午6:08:43
 */
public class DateFilter implements javax.servlet.Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Date now =  UtilDateTime.getNowDate();
		
		request.setAttribute("year",UtilDateTime.format(now, UtilDateTime.YYYY));
		request.setAttribute("month",UtilDateTime.format(now, UtilDateTime.YYYY_MM));
		request.setAttribute("day",UtilDateTime.format(now, UtilDateTime.YYYY_MM_DD));
		request.setAttribute("now",UtilDateTime.format(now, UtilDateTime.YYYY_MM_DD_HH_MM_SS));
		//时间戳
		request.setAttribute("timeStamp",now.getTime());
		//时间磋，秒
		request.setAttribute("timeStampSecond",now.getTime()/1000);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
