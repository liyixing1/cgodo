package cn.gou23.cgodo.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gou23.cgodo.page.Page;
import cn.gou23.cgodo.server.dao.ClientRequestEntityMapper;
import cn.gou23.cgodo.server.dao.ClientRequestSummaryEntityMapper;
import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntity;
import cn.gou23.cgodo.server.entity.ClientRequestSummaryEntityCondition;
import cn.gou23.cgodo.server.model.ClientRequestModel;
import cn.gou23.cgodo.server.model.ClientRequestSummaryModel;
import cn.gou23.cgodo.server.service.ClientRequestService;
import cn.gou23.cgodo.util.UtilDateTime;

@Service
public class ClientRequestServiceImpl implements ClientRequestService {
	@Autowired
	private ClientRequestEntityMapper clientRequestEntityMapper;
	@Autowired
	private ClientRequestSummaryEntityMapper clientRequestSummaryEntityMapper;

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
		clientRequestSummaryEntityMapper.updateByPrimaryKey(clientRequestSummaryModel);
	}

	@Override
	public ClientRequestSummaryModel getLastSummary() {
		// 最后一天
		ClientRequestSummaryEntityCondition clientRequestSummaryEntityCondition = new ClientRequestSummaryEntityCondition();

		clientRequestSummaryEntityCondition
				.setOrderByClause("CURRENT_DATE DESC, CLIENT_NUMBER DESC");

		List<ClientRequestSummaryEntity> clientRequestSummaryEntities = clientRequestSummaryEntityMapper
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
			clientRequestSummaryEntityMapper.insert(clientRequestSummaryModel);
		}

		return clientRequestSummaryModel;
	}

}
