package com.cgodo.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.SinaUserinfoEntityMapper;
import com.cgodo.member.entity.SinaUserinfoEntityCondition;
import com.cgodo.member.model.SinaUserinfoModel;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.SinaUserinfoService;
import com.cgodo.member.service.UserinfoService;

/**
 * 用户相关
 * 
 * @author liyixing-pc
 *
 */
@Service
@Transactional(isolation=Isolation.REPEATABLE_READ)
public class SinaUserinfoServiceImp implements SinaUserinfoService {
	@Override
	public SinaUserinfoModel getByUid(String uid) {
		SinaUserinfoEntityCondition sinaUserinfoEntityCondition = new SinaUserinfoEntityCondition();

		sinaUserinfoEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andUidEqualTo(uid);

		List<SinaUserinfoModel> sinaUserinfoModels = sinaUserinfoEntityMapper
				.selectByExample(sinaUserinfoEntityCondition);

		return sinaUserinfoModels.size() > 0 ? sinaUserinfoModels.get(0) : null;
	}

	@Override
	public void add(SinaUserinfoModel sinaUserinfoModel) {
		sinaUserinfoEntityMapper.insert(sinaUserinfoModel);
	}
	
	@Override
	public void createAndBindSina(UserinfoModel userinfoModel,
			SinaUserinfoModel sinaUserinfoModel) {
		userinfoService.save(userinfoModel);
		//保存微信用户
		sinaUserinfoModel.setUserId(userinfoModel.getId());
		sinaUserinfoEntityMapper.insert(sinaUserinfoModel);
	}

	@Autowired
	private SinaUserinfoEntityMapper sinaUserinfoEntityMapper;
	@Autowired
	private UserinfoService userinfoService;
}
