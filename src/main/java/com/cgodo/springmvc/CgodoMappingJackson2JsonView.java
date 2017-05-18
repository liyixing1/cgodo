package com.cgodo.springmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.cgodo.page.Page;
import com.cgodo.page.PageContext;
import com.cgodo.util.UtilLog;
import com.fasterxml.jackson.annotation.JsonView;

public class CgodoMappingJackson2JsonView extends MappingJackson2JsonView {
	@Override
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());
		Set<String> modelKeys = (!CollectionUtils.isEmpty(super.getModelKeys()) ? super
				.getModelKeys() : model.keySet());
		boolean extractValueFromSingleKeyModel;
		List<String> errors = new ArrayList<String>();

		try {
			extractValueFromSingleKeyModel = (Boolean) FieldUtils.readField(
					this, "extractValueFromSingleKeyModel", true);
			filterPage(result);

			for (Map.Entry<String, Object> entry : model.entrySet()) {
				if (!(entry.getValue() instanceof BindingResult)
						&& modelKeys.contains(entry.getKey())
						&& !entry.getKey().equals(JsonView.class.getName())) {
					result.put(entry.getKey(), entry.getValue());
				} else if (entry.getValue() instanceof BindingResult) {
					filterErrors((BindingResult) entry.getValue(), errors);
				}
			}
			
			if(StringUtils.isNotBlank((String)model.get("error_message"))){
				errors.add((String)model.get("error_message"));
			}
			
			if(errors.size() > 0) {
				result.put("errors", errors);
			}
			
			return (extractValueFromSingleKeyModel && result.size() == 1 ? result
					.values().iterator().next()
					: result);
		} catch (IllegalAccessException e) {
			UtilLog.error("获取extractValueFromSingleKeyModel字段失败", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 处理分页
	 * 
	 * @param result
	 */
	private void filterPage(Map<String, Object> result) {
		Page page = PageContext.get();
		result.put("page", PageContext.get());
		result.put("error_code", "1");
		result.put("items", page.getResults());
		result.put("message", "success");
		result.put("pageNo", page.getPageNo());
		result.put("pageSize", page.getPageSize());
		result.put("pageSize", page.getPageSize());
		result.put("totalCount", page.getTotalCount());
		result.put("totalPage", page.getTotalPage());
	}

	/**
	 * 处理错误信息
	 * 
	 * @param result
	 */
	private void filterErrors(BindingResult bindingResult, List<String> errors) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> objectErrors = bindingResult.getAllErrors();

			for (ObjectError objectError : objectErrors) {
				errors.add(objectError.getDefaultMessage());
			}
		}
	}
}
