package com.cgodo.tcp.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 
 * 描述:https代理
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年9月21日 上午10:28:15
 */
@Controller
public class HttpsProxy {
	/**
	 * https代理访问资源
	 * 
	 * @throws IOException
	 */
	@RequestMapping("https_proxy")
	public void proxy(HttpServletRequest request, HttpServletResponse response,
			@RequestHeader HttpHeaders headers,
			ServletOutputStream servletOutputStream, String url)
			throws IOException {
		if (url.startsWith("//")) {
			url = "http:" + url;
		}

		List<Header> headersSend = new ArrayList<Header>();

		for (Entry<String, List<String>> e : headers.entrySet()) {
			if (e.getKey().equals("host")) {
				// 获取完整的域名
				Pattern p = Pattern.compile(
						"[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)",
						Pattern.CASE_INSENSITIVE);
				Matcher matcher = p.matcher(url);
				matcher.find();
				Header header = new BasicHeader(e.getKey(), matcher.group());
				headersSend.add(header);
			} else if (e.getKey().equals("referer")) {
				String v = e.getValue().get(0);
				int i = v.indexOf("https_proxy.html?url=")
						+ "https_proxy.html?url=".length();
				Header header = new BasicHeader(e.getKey(), v.substring(i));

				headersSend.add(header);

			} else {
				List<String> v = e.getValue();
				Header header = new BasicHeader(e.getKey(), v.get(0));

				headersSend.add(header);
			}
		}

		CookieStore cookieStore = new BasicCookieStore();
		// JSESSIONID
		javax.servlet.http.Cookie[] cookies = request.getCookies();

		for (javax.servlet.http.Cookie cookie : cookies) {
			// 新建一个Cookie
			BasicClientCookie cookie1 = new BasicClientCookie(cookie.getName(),
					cookie.getValue());
			// cookie1.setVersion(0);
			// cookie1.setDomain("127.0.0.1");
			cookie1.setPath("/");
			cookieStore.addCookie(cookie1);
		}

		// String content = HttpsProxy.send(null, url, null);
		CloseableHttpResponse httpResponse = HttpsProxy.send(
				headersSend.toArray(new Header[headersSend.size()]), url, null,
				cookieStore);
		HttpEntity entity = httpResponse.getEntity();

		if (entity == null) {
			Header[] headersResponse = httpResponse.getAllHeaders();

			for (Header header : headersResponse) {
				response.setHeader(header.getName(), header.getName());
			}

			response.setStatus(httpResponse.getStatusLine().getStatusCode());
			httpResponse.close();
			servletOutputStream.close();

			return;
		}

		String type = entity.getContentType().getValue();
		response.setHeader("content-type", type);
		if (type.indexOf("text/html") >= 0) {
			// 修改链接
			String content = proxyHref(EntityUtils.toString(entity));

			if (type.indexOf("GBK") >= 0 || type.indexOf("gbk") >= 0
					|| type.indexOf("GB2312") >= 0
					|| type.indexOf("gb2312") >= 0) {
				servletOutputStream.write(content.getBytes("gbk"));
			} else {
				servletOutputStream.write(content.getBytes());
			}
		} else {
			InputStream in = entity.getContent();
			byte[] bytes = new byte[1024];
			int i = in.read(bytes);
			while (i > 0) {
				servletOutputStream.write(bytes, 0, i);
				i = in.read(bytes);
			}
		}

		httpResponse.close();
	}

	/**
	 * 
	 * 描述:修改href
	 * 
	 * @param text
	 * @return
	 * @author liyixing 2015年9月21日 上午11:39:14
	 */
	public static final String proxyHref(String text) {
		// String[] ts = text.split("href");
		// StringBuffer sb = new StringBuffer();
		//
		// for (int i = 0; i < ts.length; i++) {
		// String t = ts[i];
		// // if (i % 2 != 0) {
		// if (t.startsWith("=\"")) {
		// sb.append("href");
		// sb.append("=\"https_proxy.html?url=").append(t.substring(2));
		// } else if (t.startsWith("='")) {
		// sb.append("href");
		// sb.append("='https_proxy.html?url=").append(t.substring(2));
		// } else if (t.startsWith("=")) {
		// sb.append("href");
		// sb.append("=https_proxy.html?url=").append(t.substring(1));
		// } else {
		// // }
		// sb.append(t);
		// }
		// }
		//
		// return sb.toString();

		text = text
				+ "<script>"
				+ "window.onload=function(){var as = document.getElementsByTagName('a');for(i=0;i<as.length;i++){var a = as[i];a.href='https_proxy.html?url='+a.href}};"
				+ "</script>";

		return text;
	}

	public String sendData(String url, String data) {
		String receivedData = null;
		try {
			Map<String, String> paramsData = new HashMap<String, String>();
			paramsData.put("data", data);
			// receivedData = send(null, url, paramsData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receivedData;
	}

	/**
	 * 
	 * 描述:发送请求
	 * 
	 * @param url
	 * @param paramsMap
	 * @return
	 * @throws IOException
	 * @author liyixing 2015年9月21日 上午10:46:25
	 * @param cookieStore
	 */
	public static final CloseableHttpResponse send(Header[] headers,
			String url, Map<String, String> paramsMap, CookieStore cookieStore)
			throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		// httpGet.setHeader(
		// "user-agent",
		// "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.125 Safari/537.36");
		// httpGet.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// httpGet.setHeader("Upgrade-Insecure-Requests", "1");

		if (ArrayUtils.isNotEmpty(headers)) {
			httpGet.setHeaders(headers);
		}

		try {
			// httpPost.set
			CloseableHttpResponse response = httpclient.execute(httpGet);
			// response.getHeaders("");
			// do something useful with the response body
			// and ensure it is fully consumed
			return response;
		} catch (Exception e) {
			return null;
		} finally {
			// response.close();
		}
	}

	public static void main(String[] args) throws IOException {
		HttpsProxy.send(null, "https://www.taobao.com", null, null);
	}
}
