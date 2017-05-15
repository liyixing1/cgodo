package com.cgodo.component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.cgodo.component.model.WechatCallModel;
import com.cgodo.component.model.WechatNotifyModel;
import com.cgodo.util.UtilDateTime;

/**
 * 微信组件
 * 
 * @author liyixing-pc
 *
 */
public interface WechatComponent {
	/**
	 * 获取token,token有效期是2小时
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public AccessToken getAccessToken() throws Exception;

	/**
	 * 获取票据,有效期2小时
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public Ticket getTicket(String access_token) throws Exception;

	/**
	 * 
	 * 描述:微信配置信息
	 * 
	 * @return
	 * @author liyixing 2017年1月6日 下午6:13:17
	 * @throws Exception
	 */
	public Config getConfig(String url) throws Exception;

	/**
	 * 
	 * 描述:下单接口
	 * 
	 * @param ordersId
	 * @param body
	 *            商品简单描述
	 * @param totalPrice
	 * @param ip
	 * @param userinfoModel
	 * @author liyixing 2017年5月7日 下午5:45:34
	 * @return
	 * @throws Exception
	 */
	public WechatCallModel unifiedorder(String orderId, String body,
			BigDecimal totalPrice, String ip, String openId, String userId)
			throws Exception;

	/**
	 * 
	 * 描述:关闭 订单
	 * 
	 * @param ordersId
	 * @param body
	 *            商品简单描述
	 * @param totalPrice
	 * @param ip
	 * @param userinfoModel
	 * @author liyixing 2017年5月7日 下午5:45:34
	 * @return
	 * @throws Exception
	 */
	public WechatCallModel closeorder(String orderId, String ip,
			String userId) throws Exception;

	/**
	 * 
	 * 描述:查询 订单
	 * 
	 * @param orderId
	 * @param transactionId
	 *            微信订单号
	 * @param ip
	 * @param userId
	 * @return
	 * @throws Exception
	 * @author liyixing 2017年5月8日 下午4:43:58
	 */
	public WechatCallModel orderquery(String orderId, String transactionId,
			String ip, String userId) throws Exception;

	/**
	 * 
	 * 描述:通知
	 * 
	 * @param wechatNotifyModel
	 * @author liyixing 2017年5月8日 下午4:50:00
	 */
	public void doNotify(WechatNotifyModel wechatNotifyModel);

	/**
	 * 
	 * 描述:生成签名
	 * 
	 * @param paramsUrl
	 * @return
	 * @author liyixing 2017年5月8日 下午8:06:00
	 */
	public String createSign(String paramsUrl);
	
	/**
	 * 
	 * 描述:验证签名
	 * 
	 * @param paramsUrl
	 * @return
	 * @author liyixing 2017年5月8日 下午8:06:00
	 * @throws UnsupportedEncodingException 
	 */
	public boolean validationSign(Map<String, Object> paramsMap) throws UnsupportedEncodingException;

	/**
	 * 服务号所有者的临时票据
	 * 
	 * @author liyixing-pc
	 *
	 */
	public static class Ticket {
		private String ticket;
		/**
		 * 获取到的时间
		 */
		private Date getDateTime;
		/**
		 * 过期时间
		 */
		private int expiresIn;

		public String getTicket() {
			return ticket;
		}

		public void setTicket(String ticket) {
			this.ticket = ticket;
		}

		/**
		 * 获取到的时间
		 */
		public Date getGetDateTime() {
			return getDateTime;
		}

		/**
		 * 获取到的时间
		 */
		public void setGetDateTime(Date getDateTime) {
			this.getDateTime = getDateTime;
		}

		/**
		 * 过期时间
		 */
		public int getExpiresIn() {
			return expiresIn;
		}

		/**
		 * 过期时间
		 */
		public void setExpiresIn(int expiresIn) {
			this.expiresIn = expiresIn;
		}

		/**
		 * 是否过期,追加1分钟缓冲期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			long expiresIn = getDateTime.getTime() + this.expiresIn * 1000
					- 60000;

			if (UtilDateTime.getNowDate().getTime() >= expiresIn) {
				return true;
			}

			return false;
		}
	}

	/**
	 * 服务号所有者的token，处理过期机制
	 * 
	 * @author liyixing-pc
	 *
	 */
	public static class AccessToken {
		private String access_token;
		/**
		 * 获取到的时间
		 */
		private Date getDateTime;
		/**
		 * 过期时间
		 */
		private int expiresIn;

		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		/**
		 * 获取到的时间
		 */
		public Date getGetDateTime() {
			return getDateTime;
		}

		/**
		 * 获取到的时间
		 */
		public void setGetDateTime(Date getDateTime) {
			this.getDateTime = getDateTime;
		}

		/**
		 * 过期时间
		 */
		public int getExpiresIn() {
			return expiresIn;
		}

		/**
		 * 过期时间
		 */
		public void setExpiresIn(int expiresIn) {
			this.expiresIn = expiresIn;
		}

		/**
		 * 是否过期,追加1分钟缓冲期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			long expiresIn = getDateTime.getTime() + this.expiresIn * 1000
					- 60000;

			if (UtilDateTime.getNowDate().getTime() >= expiresIn) {
				return true;
			}

			return false;
		}
	}

	/**
	 * 
	 * 
	 * 描述:config
	 *
	 * @author liyixing
	 * @version 1.0
	 * @since 2017年1月6日 下午6:09:13
	 */
	public static class Config {
		private String appId;
		private long timestamp;
		private String noncestr;
		private String url;
		private String signature;

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public String getNoncestr() {
			return noncestr;
		}

		public void setNoncestr(String noncestr) {
			this.noncestr = noncestr;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}
	}
}
