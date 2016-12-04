package com.cgodo.counter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cgodo.constant.EnumStatus;
import com.cgodo.counter.dao.CounterEntityMapper;
import com.cgodo.counter.entity.CounterEntityCondition;
import com.cgodo.counter.model.CounterModel;
import com.cgodo.counter.service.CounterService;
import com.cgodo.util.UtilLog;

/**
 * 计数器
 * 
 * @author liyixing-pc
 *
 */
@Service
@Transactional
public class CounterServiceImpl implements CounterService {

	@Override
	public CounterModel read(String type, String name) {
		CounterEntityCondition condition = new CounterEntityCondition();

		condition.createCriteria().andStatusNotEqualTo(EnumStatus.删除)
				.andTypeEqualTo(type).andNameEqualTo(name);

		List<CounterModel> counterModels = counterEntityMapper
				.selectByExample(condition);

		return counterModels.size() > 0 ? counterModels.get(0) : null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	@Override
	public CounterModel readAndInc(String type, String name) {
		// 重复三次，失败可能的原因是因为索引重复type+name，该索引是属于防止重复的，
		// 同时请求修改，version检查时也会报错
		for (int i = 0; i < 10; i++) {
			try {
				return readAndInc(type, name, 1, 1);
			} catch (Exception e) {
				UtilLog.error("保存counter时失败，第{}次", e,i);

				if (i >= 10) {
					UtilLog.error("保存counter时失败，超过10次，退出尝试", e);
					throw new RuntimeException(e);
				}
			}
		}

		throw new RuntimeException("保存counter时失败，超过三次，退出尝试");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	@Override
	public CounterModel readAndInc(String type, String name, long init, long inc) {
		CounterModel counterModel = read(type, name);

		if (counterModel == null) {
			counterModel = new CounterModel();

			counterModel.setName(name);
			counterModel.setType(type);
			counterModel.setValue(init);
			counterEntityMapper.insert(counterModel);

			return counterModel;
		}

		counterModel.setValue(counterModel.getValue() + inc);
		counterEntityMapper.updateByPrimaryKey(counterModel);

		return counterModel;

	}

	@Autowired
	private CounterEntityMapper counterEntityMapper;
}
