package com.cgodo.component.act;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cgodo.component.WechatComponent;
import com.cgodo.component.model.WechatNotifyModel;
import com.cgodo.constant.EnumWechatNotifyType;
import com.cgodo.util.UtilLog;

@Component
@RequestMapping("/wechat_component")
public class WechatComponentAct {
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
		modelMap.put("wechatConfig", wechatComponent.getConfig(url));
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
	public void pay_notify(@RequestBody String xml,ModelMap modelMap,HttpServletResponse response) throws Exception {
		UtilLog.debug("回调xml", xml);
		WechatNotifyModel wechatNotifyModel = new WechatNotifyModel();
		
		wechatNotifyModel.setParams(xml);
		wechatNotifyModel.setType(EnumWechatNotifyType.统一下单);
		wechatComponent.doNotify(wechatNotifyModel);
		send(response, "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
	}
	
	public void send(HttpServletResponse response,String xml) throws IOException {
		response.getWriter().write(xml);
	}
	
	@Autowired
	private WechatComponent wechatComponent;
}
