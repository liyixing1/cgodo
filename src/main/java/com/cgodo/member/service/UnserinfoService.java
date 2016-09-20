package com.cgodo.member.service;

import com.cgodo.member.model.UserinfoModel;

/**
 * 用户相关操作
 * @author liyixing-pc
 *
 */
public interface UnserinfoService {
	public UserinfoModel getByUserName(String name);
}
