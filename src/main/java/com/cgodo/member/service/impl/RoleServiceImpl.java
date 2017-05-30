package com.cgodo.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.RoleEntityMapper;
import com.cgodo.member.entity.RoleEntityCondition;
import com.cgodo.member.model.RoleModel;
import com.cgodo.member.service.RoleService;
import com.cgodo.page.PageContext;

/**
 * 
 * 
 * 描述:角色 service impl
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class RoleServiceImpl implements RoleService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public RoleModel getById(String id) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels.size() == 0 ? null : roleModels.get(0);
	}
	
	/**
	 * 
	 * 描述:根据角色名称获取
	 * 
	 * @param name
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public RoleModel getByName(String name) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andNameEqualTo(name);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels.size() == 0 ? null : roleModels.get(0);
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
	public List<RoleModel> getsById(String id) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels;
	}
	
	/**
	 * 
	 * 描述:根据角色名称获取
	 * 
	 * @param name
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RoleModel> getsByName(String name) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andNameEqualTo(name);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels;
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
	public List<RoleModel> getsInIds(List<String> ids) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdIn(ids);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels;
	}
	
	/**
	 * 
	 * 描述:根据角色名称 s in 获取
	 * 
	 * @param names
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RoleModel> getsInNames(List<String> names) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();

		roleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andNameIn(names);

		List<RoleModel> roleModels = roleEntityMapper
				.selectByExample(roleEntityCondition);

		return roleModels;
	}

	/**
	 * 
	 * 描述:find
	 * 
	 * @param roleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RoleModel> find(RoleModel roleModel) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();
		RoleEntityCondition.Criteria criteria = roleEntityCondition
				.createCriteria();
		
		roleEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(roleModel.getId())) {
			criteria.andIdLike("%" + roleModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(roleModel.getName())) {
			criteria.andNameLike("%" + roleModel.getName() + "%");
		}

		return roleEntityMapper.selectByExampleWithRowbounds(roleEntityCondition, PageContext.get());
	}
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param roleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<RoleModel> findNoPage(RoleModel roleModel) {
		RoleEntityCondition roleEntityCondition = new RoleEntityCondition();
		RoleEntityCondition.Criteria criteria = roleEntityCondition
				.createCriteria();
		
		roleEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(roleModel.getId())) {
			criteria.andIdLike("%" + roleModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(roleModel.getName())) {
			criteria.andNameLike("%" + roleModel.getName() + "%");
		}

		return roleEntityMapper.selectByExample(roleEntityCondition);
	}

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param roleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void save(RoleModel roleModel) {
		roleEntityMapper.insert(roleModel);
	}

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param roleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void update(RoleModel roleModel) {
		roleEntityMapper.updateByPrimaryKey(roleModel);
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
		RoleModel roleModel = getById(id);
		
		roleModel.setStatus(EnumStatus.删除);
		update(roleModel);
	}
	
	@Autowired
	private RoleEntityMapper roleEntityMapper;
}
