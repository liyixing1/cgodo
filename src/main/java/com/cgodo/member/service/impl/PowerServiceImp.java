package com.cgodo.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.PowerEntityMapper;
import com.cgodo.member.entity.PowerEntityCondition;
import com.cgodo.member.model.PowerModel;
import com.cgodo.member.service.PowerService;
import com.cgodo.page.PageContext;

/**
 * 角色
 * 
 * @author liyixing-pc
 *
 */
@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PowerServiceImp implements PowerService {
	@Override
	public List<PowerModel> find(PowerModel powerModel, boolean page) {
		PowerEntityCondition powerEntityCondition = new PowerEntityCondition();
		PowerEntityCondition.Criteria criteria = powerEntityCondition
				.createCriteria().andStatusNotEqualTo(EnumStatus.删除);

		powerEntityCondition.setOrderByClause("TYPE DESC, NAME DESC");

		if (powerModel.getIds() != null) {
			if (powerModel.getIds().size() == 0) {
				return new ArrayList<PowerModel>();
			}

			criteria.andIdIn(powerModel.getIds());
		}

		if (StringUtils.isNotBlank(powerModel.getType())) {
			criteria.andTypeEqualTo(powerModel.getType());
		}

		return page ? powerEntityMapper.selectByExampleWithRowbounds(
				powerEntityCondition, PageContext.get()) : powerEntityMapper
				.selectByExample(powerEntityCondition);
	}

	@Override
	public List<String> getTypes() {
		List<PowerModel> powerModels = find(new PowerModel(), false);
		List<String> types = new ArrayList<String>();
		
		for(PowerModel powerModel : powerModels) {
			if(!types.contains(powerModel.getType())) {
				types.add(powerModel.getName());
			}
		}
		
		return types;
	}

	@Autowired
	private PowerEntityMapper powerEntityMapper;
}
