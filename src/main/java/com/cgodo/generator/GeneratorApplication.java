package com.cgodo.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cgodo.generator.database.ModelMake;
import com.cgodo.jdbc.Jdbc.ColumnModel;
import com.cgodo.jdbc.Jdbc.TableModel;
import com.cgodo.util.UtilDateTime;
import com.cgodo.util.UtilMisc;

public class GeneratorApplication {
	/**
	 * 应用名称
	 */
	public static String APPLICATION_NAME;
	
	/**
	 * 应用描述
	 */
	public static String APPLICATION_DESCRIPTION;
	
	/**
	 * 生成项目时，项目模板来源
	 */
	public static String PROJECT_SRC_PATH;
	
	/**
	 * 模板基础目录
	 */
	public static final String TEMPLATE_BASE_PATH = ModelMake.class
			.getResource("/com/cgodo/template/").getFile() + "/";

	/**
	 * 项目所在目录
	 */
	public static String BASE_PATH;

	/**
	 * 需要忽略的字段
	 */
	public static final List<String> IGNORE_FIELDS = UtilMisc.toList("VERSION",
			"STATUS", "GMT_CREATED", "GMT_UPDATED");
	/**
	 * 需要忽略的表
	 */
	public static final List<String> IGNORE_TABLES = UtilMisc.toList(
			"client_request", "client_request_summary", "counter", "power",
			"qq_userinfo", "role", "role_power", "sina_userinfoModel",
			"sina_userinfo", "temp", "userinfo", "user_role",
			"wechat_userinfo", "wechat_call", "wechat_notify");
	public static Map<TableModel, List<ColumnModel>> database;

	/**
	 * 
	 * 描述:默认参数
	 * 
	 * @return
	 * @author liyixing 2017年5月22日 下午4:43:38
	 */
	public static final Map<String, Object> makeCommonParams() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("datetime", UtilDateTime.format(UtilDateTime.getNowDate(),
				UtilDateTime.YYYY_MM_DD_HH_MM_SS));
		dataMap.put("basePackage", getBasePackage());
		dataMap.put("database", database);
		dataMap.put("applicationDescription", APPLICATION_DESCRIPTION);
		dataMap.put("applicationName", APPLICATION_NAME);
		return dataMap;
	}

	public static String getBasePackage() {
		return "com.lsiding." + APPLICATION_NAME;
	}

	/**
	 * java文件保存目录
	 */
	public static String getSaveJavaBasePath() {
		return BASE_PATH + "/src/main/java/com/lsiding/"
				+ GeneratorApplication.APPLICATION_NAME;
	}

	/**
	 * html
	 */
	public static String getSaveHtmlBasePath() {
		return BASE_PATH + "/src/main/webapp/WEB-INF/html/";
	}

	/**
	 * js
	 */
	public static String getSaveJsBasePath() {
		return BASE_PATH + "/src/main/webapp/res/rms/js/";
	}
}
