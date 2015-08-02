package cn.gou23.cgodo.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.gou23.cgodo.util.UtilDateTime;
import cn.gou23.cgodo.util.UtilHttpRequest;
import cn.gou23.cgodo.util.UtilLog;

/**
 * 
 * 
 * 描述:服务请求过滤器，负责登记访问的IP信息<br>
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年7月30日 下午6:08:43
 */
public class ServerRequestFilter extends ApplicationObjectSupport implements
		javax.servlet.Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 从QueryURL获取参数，防止调用request.getParamater或造成上传文件失败
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURL().toString();
		String ip = UtilHttpRequest.getIpAddr(httpServletRequest);
		Date start = UtilDateTime.getNowDate();

		UtilLog.debug("开始处理请求{}，请求ip{}，开始时间{}", url, ip, start.getTime());
		ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request
				.getServletContext());
		try {
			chain.doFilter(request, response);
		} finally {
			Date end = UtilDateTime.getNowDate();

			UtilLog.debug("处理请求{}结束，请求ip{}，结束时间{}，耗时{}毫秒", url, ip,
					end.getTime(), end.getTime() - start.getTime());
		}
	}

	@Override
	public void destroy() {

	}
}
