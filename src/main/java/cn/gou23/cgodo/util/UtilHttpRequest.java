package cn.gou23.cgodo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * 描述:客户端请求帮助类<br>
 * 
 * <ul>
 * <li>获取客户IP</li>
 * <li>是否PC</li>
 * <li>是否微信</li>
 * <li>是否手机</li>
 * </ul>
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月2日 下午11:24:09
 */
public class UtilHttpRequest {
	/**
	 * 移动设备正则匹配：手机端，用于判断浏览器信息<br>
	 * \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),<br>
	 * 字符串在编译时会被转码一次,所以是<br>
	 * "\\b" \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
	 * <br>
	 * CASE_INSENSITIVE不区分大小写
	 */
	private static final Pattern PAR_PATTERN = Pattern.compile(
			"\\b(ip(hone|od)|android|opera m(ob|in)i"
					+ "|windows (phone|ce)|blackberry"
					+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
					+ "|laystation portable)|nokia|fennec|htc[-_]"
					+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b",
			Pattern.CASE_INSENSITIVE);

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

	/**
	 * 是否微信
	 * 
	 * @param request
	 * @return
	 */
	public static final boolean isWechat(HttpServletRequest request) {
		String ua = getUseragent(request);

		if (StringUtils.isBlank(ua)) {
			return false;
		}
		return ua.toLowerCase().indexOf("micromessenger") > 0;
	}

	/**
	 * 是否手机
	 * 
	 * @param request
	 * @return
	 */
	public static final boolean isMobile(HttpServletRequest request) {
		String ua = getUseragent(request);

		if (StringUtils.isBlank(ua)) {
			return false;
		}

		// 匹配
		Matcher matcherPhone = PAR_PATTERN.matcher(ua);

		if (matcherPhone.find()) {
			return true;
		}

		return false;
	}

	/**
	 * 获取浏览器的user-agent（浏览器信息）
	 * 
	 * @param request
	 * @return
	 */
	public static final String getUseragent(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}
}
