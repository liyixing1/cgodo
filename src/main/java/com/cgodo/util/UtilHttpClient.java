package com.cgodo.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具
 * 
 * @author liyixing-pc
 *
 */
public class UtilHttpClient {
	/**
	 * get方式请求
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static final String httpRequestGet(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}
	
	/**
	 * post方式请求
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static final String httpRequestPost(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity);
	}
	
	/**
	 * post方式请求，发送xml数据
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static final String httpRequestPostXML(String url,String xml) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		StringEntity postEntity = new StringEntity(xml, "UTF-8");
		
		postEntity.setContentType("text/xml");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);
        
		CloseableHttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String result= EntityUtils.toString(entity,"utf-8"); 
		return result;
	}
}
