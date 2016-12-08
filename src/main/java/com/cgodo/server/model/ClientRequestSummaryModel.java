package com.cgodo.server.model;

import com.cgodo.page.NoVersionCheck;
import com.cgodo.server.entity.ClientRequestSummaryEntity;

/**
 * 
 * 
 * 描述:请求汇总模型
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月3日 上午1:07:51
 */

@NoVersionCheck
public class ClientRequestSummaryModel extends ClientRequestSummaryEntity {

	/**
	 * 描述：
	 */

	private static final long serialVersionUID = 1L;

	public Integer getClientNumber() {
		return super.getClientNumber() == null ? 0 : super.getClientNumber();
	}

	public Integer getCurrentDayNumber() {
		return super.getCurrentDayNumber() == null ? 0 : super
				.getCurrentDayNumber();
	}
}
