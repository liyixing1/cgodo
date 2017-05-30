package com.cgodo.member.model;

import java.util.List;

import com.cgodo.member.entity.RoleEntity;


/**
		
 * 描述:角色
 *
 * @author cgodo generator
 * @since 2017-05-30 22:28:31
 */
public class RoleModel extends RoleEntity {
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
	 * 描述:角色名称 s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	private List<String> names;
	
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
	 * 描述:角色名称 s
	 * 
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public List<String> getNames() {
		return names;
	}

	/**
	 * 
	 * 描述:角色名称 s
	 * 
	 * @param name
	 * @return
	 * @author cgodo generator 2017-05-30 22:28:31
	 */
	public void setNames(List<String> names) {
		this.names = names;
	}
}
