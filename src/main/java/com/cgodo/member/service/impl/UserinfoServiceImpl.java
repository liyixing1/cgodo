package com.cgodo.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumSpeakingStatus;
import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.UserinfoEntityMapper;
import com.cgodo.member.entity.UserinfoEntityCondition;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UserinfoService;
import com.cgodo.notify.Event;
import com.cgodo.page.PageContext;

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

	@Event(name="添加用户")
	@Override
	public void save(UserinfoModel userinfoModel) {
		userinfoEntityMapper.insert(userinfoModel);
	}

	@Override
	public void update(UserinfoModel userinfoModel) {
		userinfoEntityMapper.updateByPrimaryKey(userinfoModel);
	}

	@Override
	public UserinfoModel get(String id) {
		UserinfoModel userinfoModel = userinfoEntityMapper.selectByPrimaryKey(id);
		
		if(userinfoModel != null && userinfoModel.getStatus()!=EnumStatus.删除) {
			return userinfoModel;
		}
		
		return userinfoModel;
	}
	
	@Override
	public List<UserinfoModel> find(UserinfoModel userinfoModel) {
		UserinfoEntityCondition userinfoEntityCondition = new UserinfoEntityCondition();
		UserinfoEntityCondition.Criteria criteria = userinfoEntityCondition.createCriteria();
		
		criteria.andStatusNotEqualTo(EnumStatus.删除);
		userinfoEntityCondition.setOrderByClause("  GMT_CREATED ASC, ID ASC");
		
		if(StringUtils.isNotBlank(userinfoModel.getUserType())) {
			criteria.andUserTypeEqualTo(userinfoModel.getUserType());
		}
		
		//昵称
		if(StringUtils.isNotBlank(userinfoModel.getNickName())) {
			criteria.andNickNameLike("%"+userinfoModel.getNickName() + "%");
		}
		
		//指定用户类型
		if(userinfoModel.getUserType() != null) {
			criteria.andUserTypeEqualTo(userinfoModel.getUserType());
		}
		
		if(userinfoModel.getSpeakingStatus() != null) {
			criteria.andSpeakingStatusEqualTo(userinfoModel.getSpeakingStatus());
		}
		
		return userinfoEntityMapper.selectByExampleWithRowbounds(userinfoEntityCondition, PageContext.get());
	}

	@Override
	public void gag(UserinfoModel userinfoModel) {
		//防止修改到不必要的数据
		userinfoModel = get(userinfoModel.getId());
		
		if(userinfoModel!=null) {
			userinfoModel.setSpeakingStatus(EnumSpeakingStatus.已禁言);
			userinfoEntityMapper.updateByPrimaryKey(userinfoModel);
		}
	}

	@Override
	public void removeGag(UserinfoModel userinfoModel) {
		userinfoModel = get(userinfoModel.getId());

		//防止修改到不必要的数据
		if(userinfoModel!=null) {
			userinfoModel.setSpeakingStatus(EnumSpeakingStatus.未禁言);
			userinfoEntityMapper.updateByPrimaryKey(userinfoModel);
		}
	}

	@Override
	public void updateNickName(UserinfoModel userinfoModel) {
		UserinfoModel userinfoModelOld = get(userinfoModel.getId());
		userinfoModelOld.setNickName(userinfoModel.getNickName());
		userinfoEntityMapper.updateByPrimaryKey(userinfoModelOld);
	}
	
	@Override
	public void updateImgUrl(UserinfoModel userinfoModel) {
		UserinfoModel userinfoModelOld = get(userinfoModel.getId());
		userinfoModelOld.setImgUrl(userinfoModel.getImgUrl());
		userinfoEntityMapper.updateByPrimaryKey(userinfoModelOld);
	}
	
	@Override
	public void updatePassword(UserinfoModel userinfoModel) {
		UserinfoModel userinfoModelOld = getById(userinfoModel.getId());
		
		userinfoModelOld.setPassword(userinfoModel.getPassword());
		
		userinfoEntityMapper.updateByPrimaryKey(userinfoModelOld);
	}

	@Autowired
	private UserinfoEntityMapper userinfoEntityMapper;
}
