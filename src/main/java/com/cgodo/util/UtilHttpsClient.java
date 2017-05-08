package com.cgodo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * https请求工具
 * 
 * @author liyixing-pc
 *
 */
@SuppressWarnings("deprecation")
public class UtilHttpsClient {
	// 连接超时时间，默认10秒
	private static final int SOCKET_TIME_OUT = 10000;
	// 传输超时时间，默认30秒
	private static final int CONNECT_TIME_OUT = 30000;

	/**
	 * 
	 * 描述:初始化https加密证书
	 * 
	 * @param certPath
	 *            本地加密文件
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @author liyixing 2017年5月7日 下午9:48:52
	 * @return
	 */
	private static final CloseableHttpClient init(String certPath,
			String password) throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyManagementException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(
				UtilHttpsClient.class.getResource(certPath).getFile()));// 加载本地的证书进行https加密传输

		try {
			keyStore.load(instream, password.toCharArray());// 设置证书密码
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			instream.close();
		}

		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, password.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, new String[] {},
				new BrowserCompatHostnameVerifier());

		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();

		return httpClient;
	}

	/**
	 * post方式请求，发送xml数据
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static final String httpRequestPostXML(String certPath,
			String password, String url, String xml) throws Exception {
		String result = null;
		HttpClient httpClient = init(certPath, password);
		HttpPost httpPost = new HttpPost(url);
		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		StringEntity postEntity = new StringEntity(xml, "UTF-8");

		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);

		// 请求器的配置
		// 根据默认超时限制初始化requestConfig
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SOCKET_TIME_OUT)
				.setConnectTimeout(CONNECT_TIME_OUT).build();

		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		UtilLog.debug("executing request" + httpPost.getRequestLine());
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		result = EntityUtils.toString(entity, "UTF-8");

		return result;
	}
}
