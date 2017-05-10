package com.cgodo.component.model;

import java.util.Map;

import com.cgodo.component.entity.WechatNotifyEntity;
import com.cgodo.util.UtilDom4j;
import com.cgodo.util.UtilLog;

/**
 * 
 * 
 * 描述:微信回调通知
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月8日 下午1:43:06
 */
public class WechatNotifyModel extends WechatNotifyEntity {

	/**
	 * 描述：
	 */

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> paramsMap;
	
	private WechatModel wechatModel;

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	@Override
	public void setParams(String params) {
        super.setParams(params);
        
        try {
			paramsMap = UtilDom4j.xmlBody2map(params, "xml");
			wechatModel= new WechatModel();
			org.apache.commons.beanutils.BeanUtils.populate(wechatModel,paramsMap);
		} catch (Exception e) {
			UtilLog.error("微信 回调，xml转化成 map失败", e);
			
			throw new RuntimeException(e);
		}
    }

	public WechatModel getWechatModel() {
		return wechatModel;
	}

	public void setWechatModel(WechatModel wechatModel) {
		this.wechatModel = wechatModel;
	}
}
