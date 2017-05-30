package com.cgodo.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.UserRoleEntityMapper;
import com.cgodo.member.entity.UserRoleEntityCondition;
import com.cgodo.member.model.UserRoleModel;
import com.cgodo.member.service.UserRoleService;
import com.cgodo.page.PageContext;

/**
 * 
 * 
 * 描述:用户角色 service impl
 *
 * @author cgodo generator
 * @since 2017-05-30 22:33:02
 */
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	/**
	 * 
	 * 描述:根据ID获取
	 * 
	 * @param id
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public UserRoleModel getById(String id) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels.size() == 0 ? null : userRoleModels.get(0);
	}
	
	/**
	 * 
	 * 描述:根据用户ID获取
	 * 
	 * @param userId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public UserRoleModel getByUserId(String userId) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andUserIdEqualTo(userId);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels.size() == 0 ? null : userRoleModels.get(0);
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
	public UserRoleModel getByRoleId(String roleId) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdEqualTo(roleId);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels.size() == 0 ? null : userRoleModels.get(0);
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
	public List<UserRoleModel> getsById(String id) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdEqualTo(id);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
	}
	
	/**
	 * 
	 * 描述:根据用户ID获取
	 * 
	 * @param userId
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<UserRoleModel> getsByUserId(String userId) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andUserIdEqualTo(userId);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
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
	public List<UserRoleModel> getsByRoleId(String roleId) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdEqualTo(roleId);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
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
	public List<UserRoleModel> getsInIds(List<String> ids) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andIdIn(ids);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
	}
	
	/**
	 * 
	 * 描述:根据用户ID s in 获取
	 * 
	 * @param userIds
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<UserRoleModel> getsInUserIds(List<String> userIds) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andUserIdIn(userIds);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
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
	public List<UserRoleModel> getsInRoleIds(List<String> roleIds) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();

		userRoleEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andRoleIdIn(roleIds);

		List<UserRoleModel> userRoleModels = userRoleEntityMapper
				.selectByExample(userRoleEntityCondition);

		return userRoleModels;
	}

	/**
	 * 
	 * 描述:find
	 * 
	 * @param userRoleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<UserRoleModel> find(UserRoleModel userRoleModel) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();
		UserRoleEntityCondition.Criteria criteria = userRoleEntityCondition
				.createCriteria();
		
		userRoleEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(userRoleModel.getId())) {
			criteria.andIdLike("%" + userRoleModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(userRoleModel.getUserId())) {
			criteria.andUserIdLike("%" + userRoleModel.getUserId() + "%");
		}
		if (StringUtils.isNotBlank(userRoleModel.getRoleId())) {
			criteria.andRoleIdLike("%" + userRoleModel.getRoleId() + "%");
		}

		return userRoleEntityMapper.selectByExampleWithRowbounds(userRoleEntityCondition, PageContext.get());
	}
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param userRoleModel
	 * @return
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public List<UserRoleModel> findNoPage(UserRoleModel userRoleModel) {
		UserRoleEntityCondition userRoleEntityCondition = new UserRoleEntityCondition();
		UserRoleEntityCondition.Criteria criteria = userRoleEntityCondition
				.createCriteria();
		
		userRoleEntityCondition.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		if (StringUtils.isNotBlank(userRoleModel.getId())) {
			criteria.andIdLike("%" + userRoleModel.getId() + "%");
		}
		if (StringUtils.isNotBlank(userRoleModel.getUserId())) {
			criteria.andUserIdLike("%" + userRoleModel.getUserId() + "%");
		}
		if (StringUtils.isNotBlank(userRoleModel.getRoleId())) {
			criteria.andRoleIdLike("%" + userRoleModel.getRoleId() + "%");
		}

		return userRoleEntityMapper.selectByExample(userRoleEntityCondition);
	}

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param userRoleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void save(UserRoleModel userRoleModel) {
		userRoleEntityMapper.insert(userRoleModel);
	}

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param userRoleModel
	 * @author cgodo generator 2017-05-30 22:33:02
	 */
	@Override
	public void update(UserRoleModel userRoleModel) {
		userRoleEntityMapper.updateByPrimaryKey(userRoleModel);
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
		UserRoleModel userRoleModel = getById(id);
		
		userRoleModel.setStatus(EnumStatus.删除);
		update(userRoleModel);
	}
	
	@Autowired
	private UserRoleEntityMapper userRoleEntityMapper;
}
