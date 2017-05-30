package com.cgodo.member.service;

import java.util.List;

import com.cgodo.member.model.RolePowerModel;

/**
 * 
 * 
 * 描述:角色权限
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
public interface RolePowerService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public RolePowerModel getById(String id);
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public RolePowerModel getByRoleId(String roleId);
	
	/**
	 * 
	 * 描述:根据权限ID获取
	 * 
	 * @param powerId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public RolePowerModel getByPowerId(String powerId);
	
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsById(String id);
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsByRoleId(String roleId);
	
	/**
	 * 
	 * 描述:根据权限ID获取
	 * 
	 * @param powerId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsByPowerId(String powerId);
	
	
	/**
	 * 
	 * 描述:根据ID s in 获取
	 * 
	 * @param ids
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsInIds(List<String> ids);
	
	/**
	 * 
	 * 描述:根据角色ID s in 获取
	 * 
	 * @param roleIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsInRoleIds(List<String> roleIds);
	
	/**
	 * 
	 * 描述:根据权限ID s in 获取
	 * 
	 * @param powerIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> getsInPowerIds(List<String> powerIds);

	/**
	 * 
	 * 描述:find
	 * 
	 * @param rolePowerModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> find(RolePowerModel rolePowerModel);
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param rolePowerModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public List<RolePowerModel> findNoPage(RolePowerModel rolePowerModel);

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param rolePowerModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void save(RolePowerModel rolePowerModel);

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param rolePowerModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void update(RolePowerModel rolePowerModel);

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	public void delete(String id);
}
