package cn.gou23.cgodo.page.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.gou23.cgodo.page.Page;
import cn.gou23.cgodo.page.PageContext;
import cn.gou23.cgodo.util.UtilLog;
import cn.gou23.cgodo.util.UtilUrl;

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
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 从QueryURL获取参数，防止调用request.getParamater或造成上传文件失败
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		UtilLog.debug("开始解析请求链接，查找分页参数{}", httpServletRequest.getQueryString());
		Map<String, String> map = UtilUrl.urlToMap(
				httpServletRequest.getQueryString(), "utf-8");
		Page<Object> page = new Page<Object>();
		String pageNo = map.get("pageNo");
		String pageSize = map.get("pageSize");

		PageContext.set(page);
		request.setAttribute("page", page);

		// 解析参数
		if (StringUtils.isNotBlank(pageNo)) {
			page.setPageNo(pageNo);
		}

		if (StringUtils.isNotBlank(pageSize)) {
			page.setPageSize(pageSize);
		}

		UtilLog.debug("分页大小{}，当前第{}页", page.getPageSize(), page.getPageNo());

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			PageContext.clear();
		}
	}

	@Override
	public void destroy() {

	}
}
