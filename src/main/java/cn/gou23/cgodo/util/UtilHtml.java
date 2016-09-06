package cn.gou23.cgodo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * 想要获取name的值，那么传入 <div name="即可<br>
	 * 或者json数据，如 {"name":"name"} 那么想要获取name值，需要传入 "name":"即可
	 * 
	 * @return
	 * @author liyixing 2015-7-24 下午9:11:09
	 */
	public static final String parseAsStart(String value, String start) {
		if (StringUtils.isBlank(value)) {
			return null;
		}

		if (StringUtils.isBlank(start)) {
			return null;
		}

		// 找到开始位置
		int startIndex = value.indexOf(start);
		// 往后推start.length
		startIndex += start.length();
		int endIndex = value.indexOf("\"", startIndex);

		return value.substring(startIndex, endIndex);
	}
	
	/**
	 * 
	 * 描述:获取指定元素数据，正则方式
	 * 
	 * @param text
	 * @author liyixing 2015年9月11日 上午10:02:13
	 * @return
	 */
	public static final List<String> parseTextWithPattern(String text,
			String rep) {
		Pattern pattern = Pattern.compile(rep);
		Matcher matcher = pattern.matcher(text);
		List<String> results = new ArrayList<String>();

		while (matcher.find()) {
			String txt = matcher.group(1);
			results.add(txt);
		}

		return results;
	}
	
	/**
	 * 
	 * 描述:获取指定元素数据，正则方式，只获取第一个
	 * 
	 * @param text
	 * @author liyixing 2015年9月11日 上午10:02:13
	 * @return
	 */
	public static final String parseTextWithPatternHtml(String text, String rep) {
		Pattern pattern = Pattern.compile(rep);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find() && matcher.groupCount() > 0) {
			String txt = matcher.group(1);

			return txt;
		}
		
		return "";
	}

	/**
	 * 描述:http请求，并获取结果的html
	 * 
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @author liyixing 2015年8月16日 下午3:52:35
	 */

	public static final String requestHttp(String url_)
			throws MalformedURLException, IOException {
		String content;
		StringBuffer html = new StringBuffer();
		URL url = new URL(url_);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String temp;
		while ((temp = br.readLine()) != null) {
			html.append(temp).append("\n");
		}
		br.close();
		isr.close();
		content = html.toString();
		return content;
	}
}
