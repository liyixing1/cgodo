package com.cgodo.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

/**
 * 通过请求地址传送的参数处理工具
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-11-28 下午1:21:19
 */
public final class UtilUrl {
	/**
	 * 参数以?开头
	 */
	public static final String PARAMETER_START = "?";
	/**
	 * 参数分隔符
	 */
	public static final String PARAMETER_SEPARATION = "&";
	/**
	 * 默认编码
	 */
	public static final String DEFAULT_ENCODING = "utf-8";

	public static final PathMatcher PATH_MATCHER = new AntPathMatcher();

	/**
	 * 
	 * 描述:为url添加参数开始符号?
	 * 
	 * 如果符号已经存在就不添加
	 * 
	 * @param url
	 *            要处理的url
	 * @return
	 * @author liyixing 2012-11-30 上午9:15:50
	 */
	public static final String addParameterStartCharacter(String url) {
		if (StringUtils.isBlank(url)) {
			return "?";
		}

		if (url.lastIndexOf('?') >= 0) {
			return url;
		} else {
			return url += '?';
		}
	}

	/**
	 * 
	 * 描述:计算参数开始符号?所在位置
	 * 
	 * @param url
	 *            链接
	 * @return
	 * @author liyixing 2012-11-28 下午1:30:25
	 */
	public static final int getParameterStartIndex(String url) {
		Assert.notNull(url);

		return url.lastIndexOf(PARAMETER_START);
	}

	/**
	 * 
	 * 描述:计算参数开始符号?是否存在
	 * 
	 * ?的坐标在>0并且小于url长度-1
	 * 
	 * @param url
	 *            链接
	 * @return true表示存在
	 * @author liyixing 2012-11-28 下午1:31:50
	 */
	public static final boolean hasParameterStart(String url) {
		Assert.notNull(url);

		int index = getParameterStartIndex(url);

		return index >= 0 && index < url.length() - 1;
	}

	/**
	 * 将Bean转换成请求的url，不带?符号
	 * 
	 * 某如某个属性值为空，则依然转化，只是值不存在，如name=
	 * 
	 * @param bean
	 *            需要转化的bean
	 * @param encoding
	 *            参数使用的编码
	 * @param ignores
	 *            要忽略的字段
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static final String beanToUrl(Object bean, String encoding,
			String... ignores) throws UnsupportedEncodingException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<String, Object> mapOfBean = PropertyUtils.describe(bean);

		return mapToUrl(mapOfBean, encoding, ignores);
	}

	/**
	 * 将Map转换成请求的url，不带?符号
	 * 
	 * 某如某个参数值为空，则依然转化，只是值不存在，如name=
	 * 
	 * @param params
	 *            需要转化的参数
	 * @param encoding
	 *            参数使用的编码
	 * @param ignores
	 *            要忽略的字段
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String mapToUrl(Map<String, Object> params,
			String encoding, String... ignores)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (ArrayUtils.contains(ignores, entry.getKey())) {
				continue;
			}

			Object value = entry.getValue();

			if (value == null) {
				value = "";
			}

			if (value instanceof Class<?>) {
				continue;
			}

			if (isFirst) {
				sb.append(entry.getKey());
				sb.append('=');
				sb.append(URLEncoder.encode(value.toString(), encoding));
				isFirst = false;
			} else {
				sb.append(PARAMETER_SEPARATION);
				sb.append(entry.getKey());
				sb.append('=');
				sb.append(URLEncoder.encode(value.toString(), encoding));
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 * 描述:追加参数
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @author liyixing 2017年5月9日 下午9:55:19
	 */
	public static final String addParam(String url, String name, String value) {
		url = addParameterStartCharacter(url);

		if (url.endsWith("?")) {
			// 没有参数
			url = url + name + "=" + value;
		} else {
			// 有参数
			url = url + "&" + name + "=" + value;
		}
		
		return url;
	}

	/**
	 * 将Map转换成请求的url，不带?符号
	 * 
	 * 某如某个参数值为空，则依然转化，只是值不存在，如name= <br>
	 * 不进行url encode
	 * 
	 * @param params
	 *            需要转化的参数
	 * @param encoding
	 *            参数使用的编码
	 * @param ignores
	 *            要忽略的字段
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String mapToUrlNoEncode(Map<String, Object> params,
			String encoding, String... ignores) {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (ArrayUtils.contains(ignores, entry.getKey())) {
				continue;
			}

			Object value = entry.getValue();

			if (value == null) {
				value = "";
			}

			if (value instanceof Class<?>) {
				continue;
			}

			if (isFirst) {
				sb.append(entry.getKey());
				sb.append('=');
				sb.append(value.toString());
				isFirst = false;
			} else {
				sb.append(PARAMETER_SEPARATION);
				sb.append(entry.getKey());
				sb.append('=');
				sb.append(value.toString());
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 * 描述:得到url参数部分
	 * 
	 * @param url
	 *            要获取的url
	 * @param encoding
	 *            编码
	 * @author liyixing 2012-11-28 下午1:48:20
	 * @throws UnsupportedEncodingException
	 */
	public static final String getParameterAsString(String url, String encoding)
			throws UnsupportedEncodingException {
		Assert.notNull(url);
		url = URLDecoder.decode(url, encoding);
		url = url.trim();

		// 有问号
		if (hasParameterStart(url)) {
			int parameterStartIndex = getParameterStartIndex(url);

			return url.substring(parameterStartIndex + 1);
		} else {
			// 没有问号
			return url;
		}
	}

	/**
	 * 
	 * 描述:得到url参数部分
	 * 
	 * @param url
	 *            要获取的url
	 * @param encoding
	 *            编码
	 * @author liyixing 2012-11-28 下午1:48:20
	 * @throws UnsupportedEncodingException
	 */
	public static final String getParameterAsString(String url)
			throws UnsupportedEncodingException {
		Assert.notNull(url);
		url = url.trim();

		// 有问号
		if (hasParameterStart(url)) {
			int parameterStartIndex = getParameterStartIndex(url);

			return url.substring(parameterStartIndex + 1);
		} else {
			// 没有问号
			return url;
		}
	}

	/**
	 * url转换成map
	 * 
	 * 如果格式不符合url协议,那么可能丢失部分信息
	 * 
	 * @param url
	 *            地址
	 * @param encoding
	 *            编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final Map<String, String> urlToMap(String url,
			String encoding, String... ignores)
			throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();

		if (StringUtils.isBlank(url)) {
			return map;
		}

		String paramAsString = getParameterAsString(url, encoding);

		if (StringUtils.isEmpty(paramAsString)) {
			return map;
		}

		String allParamsAsString[] = paramAsString.split(PARAMETER_SEPARATION); // 分割参数

		for (String oneParamAsString : allParamsAsString) {
			if (StringUtils.isBlank(oneParamAsString)) {
				continue;
			}

			// 分离参数名和值
			String[] paramKeyAndValue = oneParamAsString.split("=");

			// 无效格式
			if (paramKeyAndValue.length != 2) {
				continue;
			}

			String name = paramKeyAndValue[0];

			// 无效名称,或者该名称会被忽略
			if (StringUtils.isBlank(name) || ArrayUtils.contains(ignores, name)) {
				continue;
			}

			String value = paramKeyAndValue[1];

			map.put(name, value);
		}

		return map;
	}

	/**
	 * url转换成bean
	 * 
	 * 如果格式不符合url协议,那么可能丢失部分信息
	 * 
	 * @param url
	 *            地址
	 * @param encoding
	 *            编码
	 * @param beanClass
	 *            要转化的类型
	 * @param ignores
	 *            要忽略的字段，注意该方法会调用com.cgodo.util.UtilUrl.urlToMap方法和com.cgodo.
	 *            util.UtilBean.copyMapToBean，该参数同时对urlToMap和copyMapToBean有效
	 * @return
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ParseException
	 */
	// public static final <B> B urlToBean(String url, String encoding,
	// Class<B> beanClass, String... ignores)
	// throws UnsupportedEncodingException, InstantiationException,
	// IllegalAccessException, InvocationTargetException,
	// NoSuchMethodException, ParseException {
	// return UtilBean.copyMapToBean(urlToMap(url, encoding, ignores),
	// beanClass, ignores);
	// }

	/**
	 * 
	 * 描述:URL映射<br>
	 * 将制定的url，从mappings中取出一个最接近的值br>
	 * 
	 * @param url
	 * @param mappings
	 *            key 是ant path的映射路径
	 * @param defaultValue
	 * @return
	 * @author liyixing 2013-12-23 下午9:10:07
	 */
	// public static final String urlMapping(String url,
	// Map<String, String> mappings, String defaultValue) {
	// // 查找用户设置的符合的key
	// List<String> matchingPatterns = new ArrayList<String>();
	//
	// // 如/user/test地址同时会符合/user和/*
	// for (String registeredPattern : mappings.keySet()) {
	// if (PATH_MATCHER.match(registeredPattern, url)) {
	// matchingPatterns.add(registeredPattern);
	// }
	// }
	//
	// // 没有找到
	// if (CollectionUtils.isEmpty(matchingPatterns)) {
	// return defaultValue;
	// }
	//
	// // 按照ant的地址匹配模式排序，越精确的越靠前
	// String bestPatternMatch = null; // 最精确的匹配
	// Comparator<String> patternComparator = PATH_MATCHER
	// .getPatternComparator(url);
	//
	// Collections.sort(matchingPatterns, patternComparator);
	// UtilLog4j.debug("ant最精确的匹配模式存在！");
	// bestPatternMatch = matchingPatterns.get(0);
	//
	// return mappings.get(bestPatternMatch);
	// }
}
