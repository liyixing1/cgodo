package cn.gou23.cgodo.page.springmvc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;

/**
 * 
 * 
 * 描述:分页处理拦截器，负责初始化page对象，和清理page对象
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年7月30日 下午6:08:43
 */
public class PageHandlerFilter implements javax.servlet.Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Logger logger ;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
