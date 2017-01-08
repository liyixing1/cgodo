package com.cgodo.member.service;

import com.cgodo.member.model.UserinfoModel;

/**
 * 用户相关操作
 * @author liyixing-pc
 *
 */
public interface UserinfoService {
	/**
	 * 用户名获取
	 * @param name
	 * @return
	 */
	public UserinfoModel getByUserName(String name);
	
	/**
	 * id获取
	 */
	public UserinfoModel getById(String id);
	
	/**
	 * 保存
	 * @param userinfoModel
	 * @return
	 */
	public void save(UserinfoModel userinfoModel);
	
	/**
	 * 修改
	 * @param userinfoModel
	 * @return
	 */
	public void update(UserinfoModel userinfoModel);
}
