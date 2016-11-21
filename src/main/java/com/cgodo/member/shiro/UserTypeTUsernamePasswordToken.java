package com.cgodo.member.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.cgodo.util.UtilEncrypt;

/**
 * 具有用户类型的令牌
 * @author liyixing-pc
 *
 */
public class UserTypeTUsernamePasswordToken extends UsernamePasswordToken {

	public UserTypeTUsernamePasswordToken() {
		super();
	}

	public UserTypeTUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}

	public UserTypeTUsernamePasswordToken(String username, char[] password,
			boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public UserTypeTUsernamePasswordToken(String username, char[] password,
			String host) {
		super(username, password, host);
	}

	public UserTypeTUsernamePasswordToken(String username, char[] password) {
		super(username, password);
	}

	public UserTypeTUsernamePasswordToken(String username, String password,
			boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}

	public UserTypeTUsernamePasswordToken(String username, String password,
			boolean rememberMe) {
		super(username, password, rememberMe);
	}

	public UserTypeTUsernamePasswordToken(String username, String password,
			String host) {
		super(username, password, host);
	}

	public UserTypeTUsernamePasswordToken(String username, String password) {
		super(username, password);
	}

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

	public String getPasswordMd5() {
		return UtilEncrypt.encode(new String(getPassword()));
	}
}
