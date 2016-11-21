package com.cgodo.member.service;

import com.cgodo.member.model.QqUserinfoModel;
import com.cgodo.member.model.UserinfoModel;

/**
 * QQ用户相关操作
 * @author liyixing-pc
 *
 */
public interface QqUserinfoService {
	/**
	 * 用户名获取
	 * @param name
	 * @return
	 */
	public QqUserinfoModel getByOpenId(String uid);
	
	/**
	 * 添加QQ用户
	 */
	public void add(QqUserinfoModel qqUserinfoModel);	/**
	 * 创建并绑定QQ用户
	 * @param userinfoModel
	 */
	public void createAndBindQq(UserinfoModel userinfoModel,QqUserinfoModel qqUserinfoModel);
}
