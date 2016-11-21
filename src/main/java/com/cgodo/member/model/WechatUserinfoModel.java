package com.cgodo.member.model;

import com.cgodo.member.entity.WechatUserinfoEntity;

/**
 * 微信用户
 * @author liyixing-pc
 *
 */
public class WechatUserinfoModel extends WechatUserinfoEntity {

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
