package com.cgodo.generator.database;

import java.io.IOException;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:基础生成器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class Make {
	public void initData(Map<String, Object> dataMap) {
		dataMap.put("modelName", makeInfo.getModelName());
		dataMap.put("modelVariableName", makeInfo.getModelVariableName());
		dataMap.put("entityName", makeInfo.getEntityName());
		dataMap.put("entityVariableName", makeInfo.getEntityVariableName());
		dataMap.put("entityConditionName", makeInfo.getEntityConditionName());
		dataMap.put("entityConditionVariableName",
				makeInfo.getEntityConditionVariableName());
		dataMap.put("tableName", makeInfo.getTableName());
		dataMap.put("remark", makeInfo.getTableRemark());
		dataMap.put("columnModels", makeInfo.getColumnModels());
		dataMap.put("javaName", makeInfo.getJavaName());
		dataMap.put("javaVarName", makeInfo.getJavaVarName());
		dataMap.put("entityMapperName", makeInfo.getEntityMapperName());
		dataMap.put("entityMapperVariableName", makeInfo.getEntityMapperVariableName());
	}

	/**
	 * 
	 * 描述:生成器
	 * 
	 * @param dataMap
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @author liyixing 2017年5月22日 下午7:07:15
	 */
	public void make(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		initData(dataMap);
		hander(dataMap);

		if (next == null) {
			return;
		}

		next.setMakeInfo(getMakeInfo());
		next.make(dataMap);
	}

	/**
	 * 
	 * 描述:基础生成器，只初始化常用变量信息
	 * 
	 * @param dataMap
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @author liyixing 2017年5月22日 下午7:07:15
	 */
	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
	}

	/**
	 * 下一个生成器
	 */
	private Make next;
	/**
	 * 生成信息
	 */
	private MakeInfo makeInfo;

	public Make getNext() {
		return next;
	}

	public void setNext(Make next) {
		this.next = next;
	}

	public MakeInfo getMakeInfo() {
		return makeInfo;
	}

	public void setMakeInfo(MakeInfo makeInfo) {
		this.makeInfo = makeInfo;
	}
}
