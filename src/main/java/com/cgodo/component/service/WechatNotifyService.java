package com.cgodo.component.service;

import com.cgodo.component.model.WechatNotifyModel;

/**
 * 
 * 
 * 描述:微信通知记录
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月7日 下午5:37:21
 */
public interface WechatNotifyService {
	/**
	 * 
	 * 描述:添加记录
	 * 
	 * @param wechatCallModel
	 * @author liyixing 2017年5月7日 下午5:38:16
	 */
	public void add(WechatNotifyModel wechatNotifyModel);
}
