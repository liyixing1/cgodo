package com.cgodo.component.listen.impl;

import org.springframework.stereotype.Component;

import com.cgodo.component.listen.WechatComponentListener;
import com.cgodo.component.model.WechatNotifyModel;

/**
 * 
 * 
 * 描述:默认通知实现
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月8日 下午4:55:26
 */
@Component
public class WechatComponentListenerDefaultImpl implements WechatComponentListener {
	@Override
	public void onUnifiedOrderNotify(WechatNotifyModel wechatNotifyModel) {
		
	}
}
