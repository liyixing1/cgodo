package com.cgodo.generator;

import java.io.IOException;
import java.util.Map;

import com.cgodo.freemarker.TemplateHandler;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:save页面生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class SaveMake extends Make {
	public void initData(Map<String, Object> dataMap) {
		// import
	}

	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("save.ftl");
		handler.setSavePath(GeneratorApplication.SAVE_HTML_BASE_PATH + "/admin/"+getMakeInfo().getTableName()+"/");
		handler.setSaveFileName("save.html");
		handler.hander(dataMap);
	}
}
