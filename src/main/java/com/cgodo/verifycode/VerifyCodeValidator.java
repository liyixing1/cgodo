package com.cgodo.verifycode;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.cgodo.verifycode.model.VerifyCodeModel;

/**
 * 
 * 
 * 描述:验证码验证器
 *
 * @author liyixing
 * @version 1.0
 * @since 2016年12月11日 上午2:39:42
 */
public class VerifyCodeValidator {
	/**
	 * 
	 * 描述:验证码校验，true正确，验证后清楚
	 * 
	 * @param code 需要严重的验证码
	 * @param session 
	 * @param key 必须具有，防止不同页面之间的验证码串联
	 * @return
	 * @author liyixing 2016年12月11日 上午2:40:24
	 */
	public static final boolean validation(String code, HttpSession session, String key) {
		VerifyCodeModel value = (VerifyCodeModel) session.getAttribute(key);

		clear(session, key);

		if (value == null) {
			return false;
		}

		return value.equals(code);
	}

	/**
	 * 
	 * 描述:验证码清除
	 * 
	 * @param code
	 * @param session
	 * @param key
	 * @return
	 * @author liyixing 2016年12月11日 上午2:40:24
	 */
	public static final void clear(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	/**
	 * 
	 * 描述:生成唯一key
	 * 
	 * @param session
	 * @return
	 * @author liyixing 2016年12月11日 上午2:43:46
	 */
	public static final String generateKey() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
