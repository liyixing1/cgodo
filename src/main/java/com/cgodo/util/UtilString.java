package com.cgodo.util;

import java.util.Collection;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;

/**
 * 
 * 
 * 描述:字符串工具
 * 
 * @author liyixing
 * @version 1.0
 * @since 2014-4-20 下午6:31:31
 */
public final class UtilString {
	/**
	 * 
	 * 描述:检查字符串是否在指定的集合中
	 * 
	 * @param s
	 * @param strings
	 * @return
	 * @author liyixing 2014-11-20 下午7:28:24
	 */
	public static final boolean isIn(String s, Collection<String> strings) {
		if (CollectionUtils.isEmpty(strings)) {
			return false;
		}

		return strings.contains(s);
	}

	/**
	 * 
	 * 描述:检查字符串是否在指定的数组中
	 * 
	 * @param s
	 * @param strings
	 * @return
	 * @author liyixing 2014-11-20 下午7:28:24
	 */
	public static final boolean isIn(String s, String... strings) {
		if (ArrayUtils.isEmpty(strings)) {
			return false;
		}

		return ArrayUtils.contains(strings, s);
	}

	/**
	 * 
	 * 描述:unicode转化成字符串，比如输入\u4e07,转化成万
	 * 
	 * @param ascii
	 * @return
	 * @author liyixing 2016年12月11日 上午2:30:38
	 */
	public static String ascii2native(String ascii) {
		int n = ascii.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = ascii.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}
}
