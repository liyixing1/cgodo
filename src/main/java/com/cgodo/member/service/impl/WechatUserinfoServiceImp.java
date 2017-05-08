package com.cgodo.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.WechatUserinfoEntityMapper;
import com.cgodo.member.entity.WechatUserinfoEntityCondition;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.model.WechatUserinfoModel;
import com.cgodo.member.service.UserinfoService;
import com.cgodo.member.service.WechatUserinfoService;

/**
 * 用户相关
 * 
 * @author liyixing-pc
 *
 */
@Service
@Transactional(isolation=Isolation.REPEATABLE_READ)
public class WechatUserinfoServiceImp implements WechatUserinfoService {
	@Override
	public WechatUserinfoModel getByOpenId(String openId) {
		WechatUserinfoEntityCondition wechatUserinfoEntityCondition = new WechatUserinfoEntityCondition();

		wechatUserinfoEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andOpenIdEqualTo(openId);

		List<WechatUserinfoModel> wechatUserinfoModels = wechatUserinfoEntityMapper
				.selectByExample(wechatUserinfoEntityCondition);

		return wechatUserinfoModels.size() > 0 ? wechatUserinfoModels.get(0) : null;
	}
	
	@Override
	public WechatUserinfoModel getByUserId(String userId) {
		WechatUserinfoEntityCondition wechatUserinfoEntityCondition = new WechatUserinfoEntityCondition();

		wechatUserinfoEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andUserIdEqualTo(userId);

		List<WechatUserinfoModel> wechatUserinfoModels = wechatUserinfoEntityMapper
				.selectByExample(wechatUserinfoEntityCondition);

		return wechatUserinfoModels.size() > 0 ? wechatUserinfoModels.get(0) : null;
	}

	@Override
	public void add(WechatUserinfoModel wechatUserinfoModel) {
		wechatUserinfoEntityMapper.insert(wechatUserinfoModel);
	}
	
	@Override
	public void createAndBindWechat(UserinfoModel userinfoModel,
			WechatUserinfoModel wechatUserinfoModel) {
		userinfoService.save(userinfoModel);
		//保存微信用户
		wechatUserinfoModel.setUserId(userinfoModel.getId());
		wechatUserinfoEntityMapper.insert(wechatUserinfoModel);
	}

	@Autowired
	private WechatUserinfoEntityMapper wechatUserinfoEntityMapper;
	@Autowired
	private UserinfoService userinfoService;
}
