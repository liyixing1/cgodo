package com.cgodo.member.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UnserinfoService;

/**
 * 根据不同的用户类型来获取用户信息
 * 
 * @author liyixing-pc
 *
 */
public class UserTypeRealm extends AuthorizingRealm {
	public String getName() {
		return "userTypeRealm";
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (token instanceof UserTypeTUsernamePasswordToken) {
			UserinfoModel userinfoModel = userinfoService.getByUserName(token
					.getUsername());
			
			if(userinfoModel == null) {
				return null;
			}
			
			return new SimpleAuthenticationInfo(userinfoModel.getId(),
					userinfoModel.getPassword(), getName());
		}

		return null;
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		
		return auth;
	}

	@Autowired
	private UnserinfoService userinfoService;
}
