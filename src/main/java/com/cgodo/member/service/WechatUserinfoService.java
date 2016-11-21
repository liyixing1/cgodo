package com.cgodo.member.service;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.model.WechatUserinfoModel;

/**
 * 微信用户相关操作
 * @author liyixing-pc
 *
 */
public interface WechatUserinfoService {
	/**
	 * 用户名获取
	 * @param name
	 * @return
	 */
	public WechatUserinfoModel getByOpenId(String openId);
	
	/**
	 * 添加微信用户
	 */
	public void add(WechatUserinfoModel wechatUserinfoModel);	/**
	 * 创建并绑定微信用户
	 * @param userinfoModel
	 */
	public void createAndBindWechat(UserinfoModel userinfoModel,WechatUserinfoModel wechatUserinfoModel);
}
