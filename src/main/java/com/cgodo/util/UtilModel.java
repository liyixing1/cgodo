package com.cgodo.util;

import org.apache.commons.lang3.StringUtils;

/**
 * model，模型层操作工具类
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-7-18 下午3:45:51
 */
public final class UtilModel {
	/**
	 * 下划线
	 */
	private static final char UNDERLINE = '_';

	/**
	 * 
	 * 描述:实体层的类转化成模型层
	 * 
	 * @param enitty
	 * @return
	 * @author liyixing 2015年8月12日 下午3:56:32
	 */
	public static final Class<?> entityToModel(Class<?> entity) {
		return entityToModel(entity.getName());
	}

	/**
	 * 
	 * 描述:实体层的类转化成模型层
	 * 
	 * @param enitty
	 * @return
	 * @author liyixing 2015年8月12日 下午3:56:32
	 */
	public static final Class<?> entityToModel(String entityName) {
		// 转化成模型层的类
		String modelClassName = entityToModelName(entityName);
		try {
			return Class.forName(modelClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static final String entityToModelName(String entityName) {
		// 转化成模型层的类
		return entityName.replace(".entity", ".model").replace("Entity",
				"Model");
	}

	/**
	 * 
	 * 描述:实体字段转化成数据库字段
	 * 
	 * @param property
	 * @return
	 * @author liyixing 2017年5月4日 下午11:15:25
	 */
	public static final String propertyToDbFieldName(String property) {
		if (StringUtils.isBlank(property)) {
			return "";
		}

		int len = property.length();
		StringBuilder sb = new StringBuilder(len);

		for (int i = 0; i < len; i++) {
			char c = property.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toUpperCase(c));
			} else {
				sb.append(Character.toUpperCase(c));
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 描述:数据库字段转化成实体字段
	 * 
	 * @param property
	 * @return
	 * @author liyixing 2017年5月4日 下午11:15:25
	 */
	public static final String dbFieldNameToProperty(String fieldName) {
		if (StringUtils.isBlank(fieldName)) {
			return "";
		}

		int len = fieldName.length();
		StringBuilder sb = new StringBuilder(len);

		for (int i = 0; i < len; i++) {
			char c = fieldName.charAt(i);
			if (c == UNDERLINE && i!=0) {
				// 下划线不是结尾
				if (++i < len) {
					sb.append(Character.toUpperCase(fieldName.charAt(i)));
				}
			} else {
				sb.append(Character.toLowerCase(c));
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	}

	/**
	 * 
	 * 描述:首字母大写
	 * 
	 * @param source
	 * @return
	 * @author liyixing 2016年12月10日 下午2:15:04
	 */
	public static final String firstToUpperCase(String source) {
		return source.substring(0, 1).toUpperCase() + source.substring(1);
	}
}
