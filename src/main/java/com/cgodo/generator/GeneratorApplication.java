package com.cgodo.generator;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cgodo.jdbc.Jdbc;
import com.cgodo.jdbc.Jdbc.ColumnModel;
import com.cgodo.jdbc.Jdbc.TableModel;
import com.cgodo.util.UtilDateTime;
import com.cgodo.util.UtilMisc;

public class GeneratorApplication {
	public static String basePackage;
	/**
	 * 应用名称
	 */
	public static String applicationName;
	/**
	 * 模板基础目录
	 */
	public static final String TEMPLATE_BASE_PATH = ModelMake.class
			.getResource("/com/cgodo/template/").getFile() + "/";

	/**
	 * java文件保存目录
	 */
	public static String SAVE_BASE_PATH;
	/**
	 * html
	 */
	public static String SAVE_HTML_BASE_PATH;

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
			"sina_userinfo", "temp", "userinfo","user_role","wechat_userinfo","wechat_call", "wechat_notify");
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
		dataMap.put("basePackage", basePackage);
		dataMap.put("database", database);
		return dataMap;
	}

	public static void main(String[] args) throws SQLException {
		Object o = new Jdbc(
				"net.sf.log4jdbc.DriverSpy",
				"jdbc:log4jdbc:mysql://127.0.0.1:3306/ticket?useUnicode=false&autoReconnect=true&characterEncoding=utf-8",
				"root", "2722261").getDatabaseInfo();

		System.out.println(o);
	}
}
