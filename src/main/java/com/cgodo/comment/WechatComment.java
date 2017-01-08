package com.cgodo.comment;

import java.util.Date;

import com.cgodo.util.UtilDateTime;

/**
 * 微信组件
 * 
 * @author liyixing-pc
 *
 */
public interface WechatComment {
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
		 * 是否过期,追加5分钟缓冲期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			long expiresIn = getDateTime.getTime() + this.expiresIn * 1000 - 300000;
			
			if(UtilDateTime.getNowDate().getTime() >= expiresIn) {
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
		 * 是否过期,追加5分钟缓冲期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			long expiresIn = getDateTime.getTime() + this.expiresIn * 1000 - 300000;
			
			if(UtilDateTime.getNowDate().getTime() >= expiresIn) {
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
