package com.cgodo.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgodo.constant.EnumStatus;
import com.cgodo.member.dao.QqUserinfoEntityMapper;
import com.cgodo.member.entity.QqUserinfoEntityCondition;
import com.cgodo.member.model.QqUserinfoModel;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.QqUserinfoService;
import com.cgodo.member.service.UserinfoService;

/**
 * QQ用户相关
 * 
 * @author liyixing-pc
 *
 */
@Service
public class QqUserinfoServiceImp implements QqUserinfoService {
	@Override
	public QqUserinfoModel getByOpenId(String openId) {
		QqUserinfoEntityCondition qqUserinfoEntityCondition = new QqUserinfoEntityCondition();

		qqUserinfoEntityCondition.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).andOpenIdEqualTo(openId);

		List<QqUserinfoModel> qqUserinfoModels = qqUserinfoEntityMapper
				.selectByExample(qqUserinfoEntityCondition);

		return qqUserinfoModels.size() > 0 ? qqUserinfoModels.get(0) : null;
	}

	@Override
	public void add(QqUserinfoModel qqUserinfoModel) {
		qqUserinfoEntityMapper.insert(qqUserinfoModel);
	}
	
	@Override
	public void createAndBindQq(UserinfoModel userinfoModel,
			QqUserinfoModel qqUserinfoModel) {
		userinfoService.save(userinfoModel);
		//保存微信用户
		qqUserinfoModel.setUserId(userinfoModel.getId());
		qqUserinfoEntityMapper.insert(qqUserinfoModel);
	}

	@Autowired
	private QqUserinfoEntityMapper qqUserinfoEntityMapper;
	@Autowired
	private UserinfoService userinfoService;
}
