package com.cgodo.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.component.dao.WechatNotifyEntityMapper;
import com.cgodo.component.model.WechatNotifyModel;
import com.cgodo.component.service.WechatNotifyService;

@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class WechatNotifyServiceImpl implements WechatNotifyService {
	@Override
	public void add(WechatNotifyModel wechatNotifyModel) {
		wechatNotifyEntityMapper.insert(wechatNotifyModel);
	}
	
	@Autowired
	private WechatNotifyEntityMapper wechatNotifyEntityMapper;
}
