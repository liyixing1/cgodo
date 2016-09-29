package com.cgodo.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgodo.constant.EnumStatus;
import com.cgodo.page.Page;
import com.cgodo.server.dao.ClientRequestEntityMapper;
import com.cgodo.server.dao.ClientRequestSummaryEntityMapper;
import com.cgodo.server.entity.ClientRequestEntityCondition;
import com.cgodo.server.entity.ClientRequestSummaryEntityCondition;
import com.cgodo.server.model.ClientRequestModel;
import com.cgodo.server.model.ClientRequestSummaryModel;
import com.cgodo.server.service.ClientRequestService;
import com.cgodo.util.UtilDateTime;

@Service
public class ClientRequestServiceImpl implements ClientRequestService {
	@Autowired
	private ClientRequestEntityMapper clientRequestEntityMapper;
	@Autowired
	private ClientRequestSummaryEntityMapper clientRequestSummaryEntityMapper;

	@Override
	public int countByIp(ClientRequestModel clientRequestModel) {
		Date now = UtilDateTime.getNowDate();
		ClientRequestEntityCondition clientRequestEntityCondition = new ClientRequestEntityCondition();

		clientRequestEntityCondition
				.createCriteria()
				.andIpEqualTo(clientRequestModel.getIp())
				.andRequestTimeBetween(UtilDateTime.setDateToFirstTime(now),
						UtilDateTime.setDateToLastTime(now))
				.andStatusNotEqualTo(EnumStatus.删除);

		return clientRequestEntityMapper
				.countByExample(clientRequestEntityCondition);
	}

	@Override
	public void addClientRequest(ClientRequestModel clientRequestModel) {
		// 汇总
		ClientRequestSummaryModel clientRequestSummaryModel = getCurrentSummary();

		clientRequestEntityMapper.insert(clientRequestModel);
		// 当天加1
		// 总数加1
		clientRequestSummaryModel.setClientNumber(clientRequestSummaryModel
				.getClientNumber() + 1);
		clientRequestSummaryModel.setCurrentDayNumber(clientRequestSummaryModel
				.getCurrentDayNumber() + 1);

		// 查看该UV是否被统计过
		if (countByIp(clientRequestModel) < 2) {
			clientRequestSummaryModel
					.setUv(clientRequestSummaryModel.getUv() + 1);
		}

		clientRequestSummaryEntityMapper
				.updateByPrimaryKey(clientRequestSummaryModel);
	}

	@Override
	public ClientRequestSummaryModel getLastSummary() {
		// 最后一天
		ClientRequestSummaryEntityCondition clientRequestSummaryEntityCondition = new ClientRequestSummaryEntityCondition();

		clientRequestSummaryEntityCondition.createCriteria().andStatusNotEqualTo(EnumStatus.删除);
		clientRequestSummaryEntityCondition
				.setOrderByClause("CURRENT_DATE DESC, CLIENT_NUMBER DESC");

		List<ClientRequestSummaryModel> clientRequestSummaryEntities = clientRequestSummaryEntityMapper
				.selectByExampleWithRowbounds(
						clientRequestSummaryEntityCondition,
						Page.getPageSizeIsOne());

		// 不存在记录
		if (clientRequestSummaryEntities.size() < 1) {
			return new ClientRequestSummaryModel();
		}

		// 存在记录
		ClientRequestSummaryModel clientRequestSummaryModel = new ClientRequestSummaryModel();

		BeanUtils.copyProperties(clientRequestSummaryEntities.get(0),
				clientRequestSummaryModel);

		return clientRequestSummaryModel;
	}

	@Override
	public ClientRequestSummaryModel getCurrentSummary() {
		ClientRequestSummaryModel clientRequestSummaryModel = getLastSummary();
		Date now = UtilDateTime.getNowDate();

		if (!UtilDateTime.isEqualDay(
				clientRequestSummaryModel.getSummaryTime(), now)) {
			// 不是同一天
			clientRequestSummaryModel.setSummaryTime(now);
			//
			clientRequestSummaryModel.setId(null);
			// 重新保存
			clientRequestSummaryModel.setCurrentDayNumber(0);
			// 当天UV
			clientRequestSummaryModel.setUv(0);
			clientRequestSummaryEntityMapper.insert(clientRequestSummaryModel);
		}

		return clientRequestSummaryModel;
	}
}
