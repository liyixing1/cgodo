package com.cgodo.member.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 具有用户类型的令牌
 * @author liyixing-pc
 *
 */
public class UserTypeTUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
