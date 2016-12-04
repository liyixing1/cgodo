package com.cgodo.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.UserinfoEntityMapper;
import com.cgodo.member.entity.UserinfoEntityCondition;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;

/**
 * 用户相关
 * @author liyixing-pc
 *
 */
@Service
@Transactional(isolation=Isolation.REPEATABLE_READ)
public class UserinfoServiceImpl implements UserinfoService {

	@Override
	public UserinfoModel getByUserName(String name) {
		UserinfoEntityCondition userinfoEntityCondition = new UserinfoEntityCondition();
		
		userinfoEntityCondition.createCriteria().andStatusNotEqualTo(EnumStatus.删除).andUserNameEqualTo(name);
		
		List<UserinfoModel> userinfoModels = userinfoEntityMapper.selectByExample(userinfoEntityCondition);
		
		return userinfoModels.size() > 0 ? userinfoModels.get(0) : null;
	}

	@Override
	public UserinfoModel getById(String id) {
		return userinfoEntityMapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(UserinfoModel userinfoModel) {
		userinfoEntityMapper.insert(userinfoModel);
	}

	@Autowired
	private UserinfoEntityMapper userinfoEntityMapper;
}
