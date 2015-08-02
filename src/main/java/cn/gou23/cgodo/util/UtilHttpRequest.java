package cn.gou23.cgodo.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * 描述:客户端请求帮助类
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月2日 下午11:24:09
 */
public class UtilHttpRequest {
	/**
	 * 
	 * 描述:获取客户真实的ip
	 * 
	 * @param request
	 * @return
	 * @author liyixing 2015年8月2日 下午11:24:39
	 */
	public static final String getIpAddr(HttpServletRequest request) {
		// 处理转发请求
		String ip = request.getHeader("x-forwarded-for");

		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			// 代理
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			// WL转发
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		// 处理多级转发
		if (StringUtils.isNotBlank(ip)) {
			String[] ips = ip.split(",");

			for (String ipTemp : ips) {
				if (StringUtils.isNotBlank(ip)
						&& !"unknown".equalsIgnoreCase(ip)) {
					ip = ipTemp;

					return ip;
				}
			}
		}

		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;

	}
}
