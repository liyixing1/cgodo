package com.cgodo.generator;

import java.util.List;

import com.cgodo.jdbc.Jdbc.ColumnModel;

/**
 * 
 * 
 * 描述:需要生成的信息
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:05:49
 */
public class MakeInfo {
	/**
	 * java变量名称  abc_de = abcDe
	 */
	private String javaVarName;
	/**
	 * java名称 abc_de = AbcDe
	 */
	private String javaName;
	/**
	 * model名称
	 */
	private String modelName;
	/**
	 * entity名称
	 */
	private String entityName;
	
	/**
	 * entity变量名
	 */
	private String entityVariableName;
	
	/**
	 * 条件名称
	 */
	private String entityConditionName;
	
	/**
	 * 条件变量名称
	 */
	private String entityConditionVariableName;
	
	/**
	 * model变量名
	 */
	private String modelVariableName;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 注释
	 */
	private String tableRemark;
	/**
	 * 列信息
	 */
	private List<ColumnModel> columnModels;
	/**
	 * 基础包名
	 */
	private String basePackage;
	/**
	 * mapper
	 */
	private String entityMapperName;
	/**
	 * mapper变量名称
	 */
	private String entityMapperVariableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableRemark() {
		return tableRemark;
	}

	public void setTableRemark(String tableRemark) {
		this.tableRemark = tableRemark;
	}

	public List<ColumnModel> getColumnModels() {
		return columnModels;
	}

	public void setColumnModels(List<ColumnModel> columnModels) {
		this.columnModels = columnModels;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityVariableName() {
		return entityVariableName;
	}

	public void setEntityVariableName(String entityVariableName) {
		this.entityVariableName = entityVariableName;
	}

	public String getEntityConditionName() {
		return entityConditionName;
	}

	public void setEntityConditionName(String entityConditionName) {
		this.entityConditionName = entityConditionName;
	}

	public String getEntityConditionVariableName() {
		return entityConditionVariableName;
	}

	public void setEntityConditionVariableName(String entityConditionVariableName) {
		this.entityConditionVariableName = entityConditionVariableName;
	}

	public String getModelVariableName() {
		return modelVariableName;
	}

	public void setModelVariableName(String modelVariableName) {
		this.modelVariableName = modelVariableName;
	}

	public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public String getEntityMapperName() {
		return entityMapperName;
	}

	public void setEntityMapperName(String entityMapperName) {
		this.entityMapperName = entityMapperName;
	}

	public String getEntityMapperVariableName() {
		return entityMapperVariableName;
	}

	public void setEntityMapperVariableName(String entityMapperVariableName) {
		this.entityMapperVariableName = entityMapperVariableName;
	}

	public String getJavaVarName() {
		return javaVarName;
	}

	public void setJavaVarName(String javaVarName) {
		this.javaVarName = javaVarName;
	}
}
