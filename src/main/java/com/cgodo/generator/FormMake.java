package com.cgodo.generator;

import java.io.IOException;
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
 * 描述:Form生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class FormMake extends Make {
	public void initData(Map<String, Object> dataMap) {
		// import
		Set<String> imports = UtilMisc.toSet();

		for(ColumnModel columnModel : getMakeInfo().getColumnModels()) {
			if(columnModel.getValueType().startsWith("java.lang.")) {
				continue;
			} else if(columnModel.getValueType().startsWith("java.util.Date")) {
				imports.add(columnModel.getValueType());
			} else {
				imports.add(columnModel.getValueType());
			}
		}
		
		imports.add(getMakeInfo().getBasePackage() + ".dao." + getMakeInfo().getEntityMapperName());
		
		dataMap.put("imports", imports);
	}

	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("admin_form.ftl");
		handler.setSavePath(GeneratorApplication.SAVE_BASE_PATH + "/admin/act/form/");
		handler.setSaveFileName(getMakeInfo().getJavaName() + "Form.java");
		handler.hander(dataMap);
		
		handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("form.ftl");
		handler.setSavePath(GeneratorApplication.SAVE_BASE_PATH + "/act/form/");
		handler.setSaveFileName(getMakeInfo().getJavaName() + "Form.java");
		handler.hander(dataMap);
	}
}
