package cn.gou23.cgodo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * 描述:html解析工具
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年7月28日 下午7:52:24
 */
public class UtilHtml {

	/**
	 * 
	 * 描述:根据指定开始获取值，并认为"是结尾<br>
	 * 
	 * 常见的html数据，如
	 * 
	 * <div name="myname"/>
	 * 
	 * 想要获取name的值，那么传入
	 * <div name="即可<br>
	 * 或者json数据，如
	 * {"name":"name"}
	 * 那么想要获取name值，需要传入 "name":"即可
	 * 
	 * @return
	 * @author liyixing 2015-7-24 下午9:11:09
	 */
	public static final String parseAsStart(String value, String start) {
		if(StringUtils.isBlank(value)) {
			return null;
		}
		
		if(StringUtils.isBlank(start)) {
			return null;
		}
		
		// 找到开始位置
		int startIndex = value.indexOf(start);
		// 往后推start.length
		startIndex += start.length();
		int endIndex = value.indexOf("\"", startIndex);

		return value.substring(startIndex, endIndex);
	}
}
