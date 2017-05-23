package com.cgodo.generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cgodo.freemarker.TemplateHandler;
import com.cgodo.jdbc.Jdbc.ColumnModel;
import com.cgodo.util.UtilMisc;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:服务接口生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class ServiceMake extends Make {
	public void initData(Map<String, Object> dataMap) {
		// import
		Set<String> imports = UtilMisc.toSet(List.class.getName(),
				getMakeInfo().getBasePackage() + ".model."
						+ getMakeInfo().getModelName());

		for(ColumnModel columnModel : getMakeInfo().getColumnModels()) {
			if(columnModel.getValueType().startsWith("java.lang.")) {
				continue;
			} else if(columnModel.getValueType().startsWith("java.util.Date")) {
				imports.add(columnModel.getValueType());
			} else {
				imports.add(columnModel.getValueType());
			}
		}
		
		dataMap.put("imports", imports);
	}

	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("service.ftl");
		handler.setSavePath(GeneratorApplication.SAVE_BASE_PATH + "/service/");
		handler.setSaveFileName(getMakeInfo().getJavaName() + "Service.java");
		handler.hander(dataMap);
	}
}
