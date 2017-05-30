package com.cgodo.member.shiro;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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

import com.cgodo.member.model.PowerModel;
import com.cgodo.member.model.RolePowerModel;
import com.cgodo.member.model.UserRoleModel;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.PowerService;
import com.cgodo.member.service.RolePowerService;
import com.cgodo.member.service.UserRoleService;
import com.cgodo.member.service.UserinfoService;
import com.cgodo.util.UtilBean;

/**
 * 自定义DB Realm
 * 
 */
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {

	/**
	 * 登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UserTypeTUsernamePasswordToken token = (UserTypeTUsernamePasswordToken) authcToken;
		UserinfoModel user = userinfoService.getByUserName(token.getUsername());

		if (user != null && user.getUserType().equals(token.getUserType())) {
			return new SimpleAuthenticationInfo(user.getId(),
					user.getPassword(), getName());
		} else {
			return null;
		}
	}

	/**
	 * 校验密码，第一次验证md5，第二次直接验证
	 */
	protected void assertCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) throws AuthenticationException {
		CredentialsMatcher cm = getCredentialsMatcher();
		if (cm != null) {
			if (!cm.doCredentialsMatch(token, info)) {
				// 直接对比
				if (!String.valueOf((char[]) token.getCredentials()).toString()
						.equals(info.getCredentials())) {
					// not successful - throw an exception to indicate this:
					String msg = "Submitted credentials for token [" + token
							+ "] did not match the expected credentials.";
					throw new IncorrectCredentialsException(msg);
				}
			}
		} else {
			throw new AuthenticationException(
					"A CredentialsMatcher must be configured in order to verify "
							+ "credentials during authentication.  If you do not wish for credentials to be examined, you "
							+ "can configure an "
							+ AllowAllCredentialsMatcher.class.getName()
							+ " instance.");
		}
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userId = (String) principals.getPrimaryPrincipal();
		UserinfoModel userinfoModel = userinfoService.getById(userId);
		List<PowerModel> powerModels;

		if (StringUtils.equals(userinfoModel.getUserName(), "admin")) {
			powerModels = powerService.find(new PowerModel(), false);
		} else {
			powerModels = new ArrayList<PowerModel>();
			// 获取用户角色
			List<UserRoleModel> userRoleModels = userRoleService
					.getsByUserId(userId);
			try {
				// 用户存在角色
				if (userRoleModels.size() != 0) {
					List<String> roleIds = UtilBean.beansFieldTolist(
							userRoleModels, "roleId");

					List<RolePowerModel> rolePowerModels = rolePowerService
							.getsInIds(roleIds);

					// 角色存在权限
					if (rolePowerModels.size() != 0) {
						PowerModel powerModel = new PowerModel();

						List<String> powerIds = UtilBean.beansFieldTolist(rolePowerModels,
								"powerId");

						powerModel.setIds(powerIds);
						// 查询权限
						powerModels = powerService.find(powerModel, false);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		Set<String> stringPermissions = new LinkedHashSet<String>();

		for (PowerModel powerModel : powerModels) {
			stringPermissions.add(powerModel.getType() + ":"
					+ powerModel.getName());
		}

		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

		auth.setStringPermissions(stringPermissions);

		return auth;
	}

	public void removeUserAuthorizationInfoCache(Long userId) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(userId, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

	@Autowired
	protected UserinfoService userinfoService;
	@Autowired
	private PowerService powerService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePowerService rolePowerService;
}
