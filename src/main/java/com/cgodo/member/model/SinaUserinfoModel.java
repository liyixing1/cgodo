package com.cgodo.member.model;

import com.cgodo.member.entity.SinaUserinfoEntity;

/**
 * 微信用户
 * @author liyixing-pc
 *
 */
public class SinaUserinfoModel extends SinaUserinfoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserinfoModel userinfoModel;

	public UserinfoModel getUserinfoModel() {
		return userinfoModel;
	}

	public void setUserinfoModel(UserinfoModel userinfoModel) {
		this.userinfoModel = userinfoModel;
	}

}
