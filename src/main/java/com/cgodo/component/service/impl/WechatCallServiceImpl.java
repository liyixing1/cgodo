package com.cgodo.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.component.dao.WechatCallEntityMapper;
import com.cgodo.component.model.WechatCallModel;
import com.cgodo.component.service.WechatCallService;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class WechatCallServiceImpl implements WechatCallService {
	@Override
	public void add(WechatCallModel wechatCallModel) {
		wechatCallEntityMapper.insert(wechatCallModel);
	}

	@Autowired
	private WechatCallEntityMapper wechatCallEntityMapper;
}
