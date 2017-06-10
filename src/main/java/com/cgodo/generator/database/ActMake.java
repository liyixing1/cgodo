package com.cgodo.generator.database;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.cgodo.freemarker.TemplateHandler;
import com.cgodo.generator.GeneratorApplication;
import com.cgodo.util.UtilMisc;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:act生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class ActMake extends Make {
	public void initData(Map<String, Object> dataMap) {
		// import
		Set<String> imports = UtilMisc.toSet(getMakeInfo().getBasePackage()
				+ ".model." + getMakeInfo().getModelName());

//		for (ColumnModel columnModel : getMakeInfo().getColumnModels()) {
//			if (columnModel.getValueType().startsWith("java.lang.")) {
//				continue;
//			} else if (columnModel.getValueType().startsWith("java.util.Date")) {
//				imports.add(columnModel.getValueType());
//				imports.add(UtilDateTime.class.getName());
//			} else {
//				imports.add(columnModel.getValueType());
//			}
//		}

		imports.add(getMakeInfo().getBasePackage() + ".service."
				+ getMakeInfo().getJavaName() + "Service");
		imports.add(getMakeInfo().getBasePackage() + ".act.form."
				+ getMakeInfo().getJavaName() + "Form");
		dataMap.put("imports", imports);
	}

	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {

		TemplateHandler handler = new TemplateHandler();
		handler.setBasePath(GeneratorApplication.TEMPLATE_BASE_PATH);
		handler.setFileName("act.ftl");
		handler.setSavePath(GeneratorApplication.getSaveJavaBasePath() + "/act/");
		handler.setSaveFileName(getMakeInfo().getJavaName() + "Act.java");
		handler.hander(dataMap);
	}
}
