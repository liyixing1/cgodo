package com.cgodo.comment.impl;

import com.cgodo.comment.WechatComment;

/**
 * 微信组件
 * @author liyixing-pc
 *
 */
public class WechatCommentImpl implements WechatComment{
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
	
	/**
	 * 获取token
	 * @param appid
	 * @param secret
	 * @return
	 */
	public AccessToken getAccessToken(String appid,String secret) {
		TOKEN_URL.toCharArray();
		return null;
	}
	
	/**
	 * 获取票据
	 * @param appid
	 * @param secret
	 * @return
	 */
	public Ticket getTicket(String appid,String secret) {
		return null;
	}
}
