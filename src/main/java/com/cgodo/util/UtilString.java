package com.cgodo.util;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
	 * 描述:检查字符串是否在指定的集合中
	 * 
	 * @param s
	 * @param strings
	 * @return
	 * @author liyixing 2014-11-20 下午7:28:24
	 */
	public static final boolean isAnyEeqals(String s, String... strings) {
		if (ArrayUtils.isEmpty(strings)) {
			return false;
		}

		for(String a : strings) {
			if(StringUtils.equals(s, a)) {
				return true;
			}
		}
		
		return false;
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
	public static final String ascii2native(String ascii) {
		int n = ascii.length() / 6;
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0, j = 2; i < n; i++, j += 6) {
			String code = ascii.substring(j, j + 4);
			char ch = (char) Integer.parseInt(code, 16);
			sb.append(ch);
		}
		return sb.toString();
	}

	/**
	 * 
	 * 描述:设置数字的分隔符
	 * 
	 * @param num
	 * @return
	 * @author liyixing 2017年1月3日 下午4:01:31
	 */
	public static final String formatNumber(Number num) {
		NumberFormat format = NumberFormat.getNumberInstance();

		return format.format(num);
	}

	/**
	 * 
	 * 描述:去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 * @author liyixing 2017年5月20日 下午10:38:39
	 */
	public static final String removeBlank(String str) {
		String dest = "";

		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}

		return dest;
	}

	/**
	 * 
	 * 描述:首字母大写
	 * 
	 * @param source
	 * @return
	 * @author liyixing 2016年12月10日 下午2:15:04
	 */
	public static final String firstToUpperCase(String source) {
		return source.substring(0, 1).toUpperCase() + source.substring(1);
	}
	
	/**
	 * 
	 * 描述:首字母小写
	 * 
	 * @param source
	 * @return
	 * @author liyixing 2016年12月10日 下午2:15:04
	 */
	public static final String firstToLowerCase(String source) {
		return source.substring(0, 1).toLowerCase() + source.substring(1);
	}

	public static void main(String[] args) {
		System.out.println(formatNumber(111111));
		System.out.println(formatNumber(111));
	}
}
