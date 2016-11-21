package com.cgodo.member.service;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.model.SinaUserinfoModel;

/**
 * 新浪用户相关操作
 * @author liyixing-pc
 *
 */
public interface SinaUserinfoService {
	/**
	 * 用户名获取
	 * @param name
	 * @return
	 */
	public SinaUserinfoModel getByUid(String uid);
	
	/**
	 * 添加新浪用户
	 */
	public void add(SinaUserinfoModel sinaUserinfoModel);	/**
	 * 创建并绑定新浪用户
	 * @param userinfoModel
	 */
	public void createAndBindSina(UserinfoModel userinfoModel,SinaUserinfoModel sinaUserinfoModel);
}
