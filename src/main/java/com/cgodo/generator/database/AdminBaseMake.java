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
 * 描述:服务实现生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class AdminBaseMake extends Make {
	public void initData(Map<String, Object> dataMap) {
		// import
	}

	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("admin_base.ftl");
		handler.setSavePath(GeneratorApplication.getSaveHtmlBasePath() + "/common/");
		handler.setSaveFileName("admin_base.html");
		handler.hander(dataMap);
	}
}
