package com.cgodo.member.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;

/**
 * 自定义DB Realm
 * 
 */
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {

	/**
	 * 登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UserTypeTUsernamePasswordToken token = (UserTypeTUsernamePasswordToken) authcToken;
		UserinfoModel user = userinfoService.getByUserName(token.getUsername());
		
		if (user != null && user.getUserType().equals(token.getUserType())) {
				return new SimpleAuthenticationInfo(user.getId(), user.getPassword(),
						getName());
		} else {
			return null;
		}
	}
	
	/**
     * 校验密码，第一次验证md5，第二次直接验证
     */
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        CredentialsMatcher cm = getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
            	//直接对比
            	if(!String.valueOf((char[])token.getCredentials()).toString().equals(info.getCredentials())) {
	                //not successful - throw an exception to indicate this:
	                String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
	                throw new IncorrectCredentialsException(msg);
            	}
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify " +
                    "credentials during authentication.  If you do not wish for credentials to be examined, you " +
                    "can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }
    }

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		Long userId =  (Long) principals.getPrimaryPrincipal();
//		UnifiedUser user = unifiedUserMng.findById(userId);
//		Manager manager = New.get(ManagerMng.class).getByUser(user.getId());
//		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
//		if (manager != null && manager.getRoleId() != null) {
//			// 这里需要获取所有权限列表
//			Set<String> perms = roleMng.getPerms(manager.getRoleId());
//			if (!CollectionUtils.isEmpty(perms)) {
//				// 权限加入AuthorizationInfo认证对象
//				auth.setStringPermissions(perms);
//			}
//		}
//		return auth;
		return new SimpleAuthorizationInfo();
	}

	public void removeUserAuthorizationInfoCache(Long userId) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userId, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}
	
	@Autowired
	protected UserinfoService userinfoService;
}
