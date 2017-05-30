package com.cgodo.member.model;

import java.util.List;

import com.cgodo.member.entity.UserRoleEntity;


/**
		
 * 描述:用户角色
 *
 * @author cgodo generator
 * @since 2017-05-30 22:28:32
 */
public class UserRoleModel extends UserRoleEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 描述:ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	private List<String> ids;

	/**
	 * 
	 * 描述:用户ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	private List<String> userIds;

	/**
	 * 
	 * 描述:角色ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	private List<String> roleIds;
	
	/**
	 * 
	 * 描述:ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * 
	 * 描述:ID s
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	/**
	 * 
	 * 描述:用户ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * 
	 * 描述:用户ID s
	 * 
	 * @param userId
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}
	/**
	 * 
	 * 描述:角色ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public List<String> getRoleIds() {
		return roleIds;
	}

	/**
	 * 
	 * 描述:角色ID s
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:32
	 */
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
}
