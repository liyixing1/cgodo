package com.cgodo.member.model;

import java.util.List;

import com.cgodo.member.entity.RolePowerEntity;


/**
		
 * 描述:角色权限
 *
 * @author cgodo generator
 * @since 2017-05-30 22:28:31
 */
public class RolePowerModel extends RolePowerEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * 描述:ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	private List<String> ids;

	/**
	 * 
	 * 描述:角色ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	private List<String> roleIds;

	/**
	 * 
	 * 描述:权限ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	private List<String> powerIds;
	
	/**
	 * 
	 * 描述:ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
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
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	/**
	 * 
	 * 描述:角色ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
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
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	/**
	 * 
	 * 描述:权限ID s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public List<String> getPowerIds() {
		return powerIds;
	}

	/**
	 * 
	 * 描述:权限ID s
	 * 
	 * @param powerId
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public void setPowerIds(List<String> powerIds) {
		this.powerIds = powerIds;
	}
}
