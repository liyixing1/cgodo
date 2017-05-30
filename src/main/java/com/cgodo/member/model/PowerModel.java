/**
 * 
 */
package com.cgodo.member.model;

import java.util.List;

import com.cgodo.member.entity.PowerEntity;

/**
 * 
 * 权限
 * @author liyixing-pc
 *
 */
public class PowerModel extends PowerEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID集合
	 */
	private List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
}
