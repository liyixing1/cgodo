package com.cgodo.member.service;

import java.util.List;

import com.cgodo.member.model.RoleModel;

/**
 * 
 * 
 * 描述:角色
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
public interface RoleService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public RoleModel getById(String id);
	
	/**
	 * 
	 * 描述:根据角色名称获取
	 * 
	 * @param name
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public RoleModel getByName(String name);
	
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> getsById(String id);
	
	/**
	 * 
	 * 描述:根据角色名称获取
	 * 
	 * @param name
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> getsByName(String name);
	
	
	/**
	 * 
	 * 描述:根据ID s in 获取
	 * 
	 * @param ids
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> getsInIds(List<String> ids);
	
	/**
	 * 
	 * 描述:根据角色名称 s in 获取
	 * 
	 * @param names
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> getsInNames(List<String> names);

	/**
	 * 
	 * 描述:find
	 * 
	 * @param roleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> find(RoleModel roleModel);
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param roleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RoleModel> findNoPage(RoleModel roleModel);

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param roleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void save(RoleModel roleModel);

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param roleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void update(RoleModel roleModel);

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void delete(String id);
}
