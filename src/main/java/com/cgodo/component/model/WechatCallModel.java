package com.cgodo.component.model;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.cgodo.component.entity.WechatCallEntity;
import com.cgodo.util.UtilDom4j;
import com.cgodo.util.UtilLog;

/**
 * 
 * 
 * 描述:微信调用记录
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月7日 下午5:33:55
 * @SuppressWarnings("serial")
 */
public class WechatCallModel extends WechatCallEntity {

	/**
	 * 描述：
	 */

	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
	private static final long serialVersionUID = 1L;

	private Map<String, Object> resultMap;

	private WechatModel wechatModel;

	/**
	 * 调用结果
	 *
	 * @mbggenerated do_not_delete_during_merge
	 */
	public void setResult(String result) {
		super.setResult(result);

		try {
			resultMap = UtilDom4j.xmlBody2map(result, "xml");
			wechatModel = new WechatModel();
			org.apache.commons.beanutils.BeanUtils.populate(wechatModel,
					resultMap);
		} catch (Exception e) {
			UtilLog.error("微信 回调，xml转化成 map失败", e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * 调用结果
	 *
	 * @mbggenerated do_not_delete_during_merge
	 */
	@SuppressWarnings("unchecked")
	public void setResultJson(String result) {
		super.setResult(result);

		try {
			resultMap = OBJECTMAPPER.readValue(result, Map.class);
			wechatModel = new WechatModel();
			org.apache.commons.beanutils.BeanUtils.populate(wechatModel,
					resultMap);
		} catch (Exception e) {
			UtilLog.error("微信 回调，json转化成 map失败", e);

			throw new RuntimeException(e);
		}
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public WechatModel getWechatModel() {
		return wechatModel;
	}

	public void setWechatModel(WechatModel wechatModel) {
		this.wechatModel = wechatModel;
	}
}
