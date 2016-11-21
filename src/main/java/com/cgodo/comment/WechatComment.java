package com.cgodo.comment;

import java.util.Date;

/**
 * 微信组件
 * 
 * @author liyixing-pc
 *
 */
public interface WechatComment {
	/**
	 * 获取token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public AccessToken getAccessToken(String appid, String secret);

	/**
	 * 获取票据
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	public Ticket getTicket(String appid, String secret);

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
		 * 是否过期,追加一秒缓存期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			return new Date().getTime() > getDateTime.getTime() + expiresIn
					- 1000;
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
		 * 是否过期,追加一秒缓存期
		 * 
		 * @return
		 */
		public boolean isExpires() {
			return new Date().getTime() > getDateTime.getTime() + expiresIn
					- 1000;
		}
	}
}
