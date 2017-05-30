package com.cgodo.member.service;

import java.util.List;

import com.cgodo.member.model.PowerModel;

/**
 * 权限
 * 
 * @author liyixing-pc
 *
 */
public interface PowerService {
	/**
	 * 
	 * 描述: 查询权限
	 * 
	 * @param powerModel
	 * @param page
	 * @return
	 * @author liyixing 2017年5月30日 下午9:29:38
	 */
	public List<PowerModel> find(PowerModel powerModel, boolean page);

	/**
	 * 
	 * 描述:获取所有的分类
	 * 
	 * @return
	 * @author liyixing 2017年5月30日 下午9:44:25
	 */
	public List<String> getTypes();
}
