package com.cgodo.component.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.component.WechatComment;
import com.cgodo.component.model.WechatNotifyModel;

@Component
@RequestMapping("/wechat_comment")
public class WechatCommentAct {
	/**
	 * 
	 * 描述:获取jsconfig
	 * 
	 * @param url
	 * @author liyixing 2017年1月6日 下午6:31:02
	 * @throws Exception 
	 */
	@RequestMapping("/config.html")
	public void config(String url,ModelMap modelMap) throws Exception {
		modelMap.put("wechatConfig", wechatComment.getConfig(url));
	}
	
	/**
	 * 
	 * 描述:支付通知
	 * 
	 * @param url
	 * @author liyixing 2017年1月6日 下午6:31:02
	 * @throws Exception 
	 */
	@RequestMapping("/pay_notify.jhtml")
	public void pay_notify(@RequestBody String xml,ModelMap modelMap) throws Exception {
		WechatNotifyModel wechatNotifyModel = new WechatNotifyModel();
		
		wechatNotifyModel.setParams(xml);
		wechatComment.doNotify(wechatNotifyModel);
	}
	
	@Autowired
	private WechatComment wechatComment;
}
