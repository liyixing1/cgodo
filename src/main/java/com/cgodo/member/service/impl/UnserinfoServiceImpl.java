package com.cgodo.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgodo.member.dao.UserinfoEntityMapper;
import com.cgodo.member.entity.UserinfoEntityCondition;
import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.service.UnserinfoService;

/**
 * 用户相关
 * @author liyixing-pc
 *
 */
@Service
public class UnserinfoServiceImpl implements UnserinfoService {

	@Override
	public UserinfoModel getByUserName(String name) {
		UserinfoEntityCondition userinfoEntityCondition = new UserinfoEntityCondition();
		
		userinfoEntityCondition.createCriteria().andUserNameEqualTo(name);
		
		List<UserinfoModel> userinfoModels = userinfoEntityMapper.selectByExample(userinfoEntityCondition);
		
		return userinfoModels.size() > 0 ? userinfoModels.get(0) : null;
	}

	@Autowired
	private UserinfoEntityMapper userinfoEntityMapper;
}
