package com.cgodo.generator.database;

import java.io.IOException;
import java.util.Map;

import com.cgodo.freemarker.TemplateHandler;
import com.cgodo.generator.GeneratorApplication;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:model生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class ModelMake extends Make{
	public void hander(Map<String, Object> dataMap) throws MalformedTemplateNameException, ParseException,
			IOException, TemplateException {
		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("model.ftl");
		handler.setSavePath(GeneratorApplication.getSaveJavaBasePath() + "/model/");
		handler.setSaveFileName(getMakeInfo().getModelName() + ".java");
		handler.hander(dataMap);
	}
}
