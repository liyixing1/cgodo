package com.cgodo.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.RolePowerEntityMapper;
import com.cgodo.member.entity.RolePowerEntityCondition;
import com.cgodo.member.model.RolePowerModel;
import com.cgodo.member.service.RolePowerService;
import com.cgodo.page.PageContext;

/**
 * 
 * 
 * 描述:角色权限 service impl
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class RolePowerServiceImpl implements RolePowerService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public RolePowerModel getById(String id) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels.size() == 0 ? null : rolePowerModels.get(0);
	}
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public RolePowerModel getByRoleId(String roleId) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdEqualTo(roleId);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels.size() == 0 ? null : rolePowerModels.get(0);
	}
	
	/**
	 * 
	 * 描述:根据权限ID获取
	 * 
	 * @param powerId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public RolePowerModel getByPowerId(String powerId) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andPowerIdEqualTo(powerId);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels.size() == 0 ? null : rolePowerModels.get(0);
	}
	
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsById(String id) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}
	
	/**
	 * 
	 * 描述:根据角色ID获取
	 * 
	 * @param roleId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsByRoleId(String roleId) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdEqualTo(roleId);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}
	
	/**
	 * 
	 * 描述:根据权限ID获取
	 * 
	 * @param powerId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsByPowerId(String powerId) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andPowerIdEqualTo(powerId);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}
	
	
	/**
	 * 
	 * 描述:根据ID s in 获取
	 * 
	 * @param ids
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsInIds(List<String> ids) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdIn(ids);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}
	
	/**
	 * 
	 * 描述:根据角色ID s in 获取
	 * 
	 * @param roleIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsInRoleIds(List<String> roleIds) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdIn(roleIds);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}
	
	/**
	 * 
	 * 描述:根据权限ID s in 获取
	 * 
	 * @param powerIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> getsInPowerIds(List<String> powerIds) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();

		rolePowerEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andPowerIdIn(powerIds);

		List<RolePowerModel> rolePowerModels = rolePowerEntityMapper
				.selectByExample(rolePowerEntityCondition);

		return rolePowerModels;
	}

	/**
	 * 
	 * 描述:find
	 * 
	 * @param rolePowerModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> find(RolePowerModel rolePowerModel) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();
		RolePowerEntityCondition.Criteria criteria = rolePowerEntityCondition
				.createCriteria();
		
		rolePowerEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(rolePowerModel.getId())) {
			criteria.andIdLike("%" + rolePowerModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(rolePowerModel.getRoleId())) {
			criteria.andRoleIdLike("%" + rolePowerModel.getRoleId() + "%");
		}
		if (StringUtils.isNotBlank(rolePowerModel.getPowerId())) {
			criteria.andPowerIdLike("%" + rolePowerModel.getPowerId() + "%");
		}

		return rolePowerEntityMapper.selectByExampleWithRowbounds(rolePowerEntityCondition, PageContext.get());
	}
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param rolePowerModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RolePowerModel> findNoPage(RolePowerModel rolePowerModel) {
		RolePowerEntityCondition rolePowerEntityCondition = new RolePowerEntityCondition();
		RolePowerEntityCondition.Criteria criteria = rolePowerEntityCondition
				.createCriteria();
		
		rolePowerEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(rolePowerModel.getId())) {
			criteria.andIdLike("%" + rolePowerModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(rolePowerModel.getRoleId())) {
			criteria.andRoleIdLike("%" + rolePowerModel.getRoleId() + "%");
		}
		if (StringUtils.isNotBlank(rolePowerModel.getPowerId())) {
			criteria.andPowerIdLike("%" + rolePowerModel.getPowerId() + "%");
		}

		return rolePowerEntityMapper.selectByExample(rolePowerEntityCondition);
	}

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param rolePowerModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void save(RolePowerModel rolePowerModel) {
		rolePowerEntityMapper.insert(rolePowerModel);
	}

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param rolePowerModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void update(RolePowerModel rolePowerModel) {
		rolePowerEntityMapper.updateByPrimaryKey(rolePowerModel);
	}

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void delete(String id) {
		RolePowerModel rolePowerModel = getById(id);
		
		rolePowerModel.setStatus(EnumStatus.删除);
		update(rolePowerModel);
	}
	
	@Autowired
	private RolePowerEntityMapper rolePowerEntityMapper;
}
