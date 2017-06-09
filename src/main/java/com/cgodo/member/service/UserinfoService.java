package com.cgodo.member.service;

import java.util.List;

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
	

	/**
	 * 查询
	 * @return
	 */
	public List<UserinfoModel> find(UserinfoModel userinfoModel);
	

	
	/**
	 * 禁言
	 * @return
	 */
	public void gag(UserinfoModel userinfoModel);
	
	/**
	 * 解禁
	 * @return
	 */
	public void removeGag(UserinfoModel userinfoModel);
	
	/**
	 * 根据主键获取
	 * @param userinfoModel
	 * @return
	 */
	public UserinfoModel get(String id);
	
	/**
	 * 修改昵称
	 * @param userinfoModel
	 */
	public void updateNickName(UserinfoModel userinfoModel);
	
	/**
	 * 修改头像
	 * @param userinfoModel
	 */
	public void updateImgUrl(UserinfoModel userinfoModel);
	
	/**
	 * 修改密码
	 * @param userinfoModel
	 */
	public void updatePassword(UserinfoModel userinfoModel);
}
