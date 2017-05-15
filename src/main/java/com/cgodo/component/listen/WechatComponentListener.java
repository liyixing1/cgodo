package com.cgodo.component.listen;

import com.cgodo.component.model.WechatNotifyModel;

/**
 * 
 * 
 * 描述:微信组件监听器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月7日 下午11:58:30
 */
public interface WechatComponentListener {
	/**
	 * 
	 * 描述:统一下单回调通知
	 * 
	 * @param params
	 * @author liyixing 2017年5月8日 下午1:33:35
	 * @throws Exception 
	 */
	public void onUnifiedOrderNotify(WechatNotifyModel wechatNotifyModel) throws Exception;
}
