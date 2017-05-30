package com.cgodo.member.service;

import java.util.List;

import com.cgodo.member.model.UserRoleModel;

/**
 * 
 * 
 * 描述:用户角色
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
public interface UserRoleService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public UserRoleModel getById(String id);
	
	/**
	 * 
	 * 描述:根据用户ID获取
	 * 
	 * @param userId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public UserRoleModel getByUserId(String userId);
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public UserRoleModel getByRoleId(String roleId);
	
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsById(String id);
	
	/**
	 * 
	 * 描述:根据用户ID获取
	 * 
	 * @param userId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsByUserId(String userId);
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsByRoleId(String roleId);
	
	
	/**
	 * 
	 * 描述:根据ID s in 获取
	 * 
	 * @param ids
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsInIds(List<String> ids);
	
	/**
	 * 
	 * 描述:根据用户ID s in 获取
	 * 
	 * @param userIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsInUserIds(List<String> userIds);
	
	/**
	 * 
	 * 描述:根据角色ID s in 获取
	 * 
	 * @param roleIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> getsInRoleIds(List<String> roleIds);

	/**
	 * 
	 * 描述:find
	 * 
	 * @param userRoleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> find(UserRoleModel userRoleModel);
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param userRoleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<UserRoleModel> findNoPage(UserRoleModel userRoleModel);

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param userRoleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void save(UserRoleModel userRoleModel);

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param userRoleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void update(UserRoleModel userRoleModel);

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void delete(String id);
}
