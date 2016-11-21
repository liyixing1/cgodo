package com.cgodo.member.model;

import com.cgodo.member.entity.QqUserinfoEntity;

/**
 * QQ用户
 * @author liyixing-pc
 *
 */
public class QqUserinfoModel extends QqUserinfoEntity {

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
