package cn.gou23.cgodo.server.service;

import cn.gou23.cgodo.server.model.ClientRequestModel;
import cn.gou23.cgodo.server.model.ClientRequestSummaryModel;

/**
 * 
 * 
 * 描述:客户端请求处理器
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月3日 上午1:06:08
 */
public interface ClientRequestService {
	/**
	 * 
	 * 描述:增加请求
	 * 
	 * @param clientRequestModel
	 * @author liyixing 2015年8月3日 上午1:06:42
	 */
	public void addClientRequest(ClientRequestModel clientRequestModel);

	/**
	 * 
	 * 描述:获取最后一次汇总
	 * 
	 * @author liyixing 2015年8月3日 上午1:08:12
	 */
	public ClientRequestSummaryModel getLastSummary();

	/**
	 * 
	 * 描述:获取当天汇总
	 * 
	 * @author liyixing 2015年8月3日 上午1:08:12
	 */
	public ClientRequestSummaryModel getCurrentSummary();
}
