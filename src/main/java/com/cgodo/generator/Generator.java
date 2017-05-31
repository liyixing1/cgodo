package com.cgodo.generator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.cgodo.jdbc.Jdbc;
import com.cgodo.jdbc.Jdbc.ColumnModel;
import com.cgodo.jdbc.Jdbc.TableModel;
import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilModel;
import com.cgodo.util.UtilString;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

public class Generator {
	/**
	 * 
	 * 描述:处理生成
	 * 
	 * @param makeInfo
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @author liyixing 2017年5月22日 下午8:15:12
	 */
	private void make(MakeInfo makeInfo, boolean isWeb) throws MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		// 基础生成器
		Make make = new Make();
		// model生成器
		ModelMake modelMake = new ModelMake();
		// service生成器
		ServiceMake serviceMake = new ServiceMake();
		// service impl生成器
		ServiceImplMake serviceImplMake = new ServiceImplMake();
		make.setMakeInfo(makeInfo);
		make.setNext(modelMake);
		modelMake.setNext(serviceMake);
		serviceMake.setNext(serviceImplMake);
		

		if(isWeb) {
			FormMake formMake = new FormMake();
			AdminActMake adminActMake = new AdminActMake();
			ActMake actMake = new ActMake();
			AdminBaseMake adminBaseMake = new AdminBaseMake();
			ListMake listMake = new ListMake();
			SaveMake saveMake = new SaveMake();
			EditMake editMake = new EditMake();
			JsMake jsMake = new JsMake();
			serviceImplMake.setNext(formMake);
			formMake.setNext(adminActMake);
			adminActMake.setNext(actMake);
			actMake.setNext(adminBaseMake);
			adminBaseMake.setNext(listMake);
			listMake.setNext(saveMake);
			saveMake.setNext(editMake);
			editMake.setNext(jsMake);
		}
		
		make.make(GeneratorApplication.makeCommonParams());
	}

	private MakeInfo getMakeInfo(List<ColumnModel> columnModels, TableModel table) {
		MakeInfo makeInfo = new MakeInfo();
		// 表明转化成java名称 如 ab_cd = AbCd
		String tableJavaName = UtilModel.tableNameToJavaName(table.getTableName());
		String javaVar = UtilString.firstToLowerCase(tableJavaName);

		makeInfo.setBasePackage(GeneratorApplication.basePackage);
		makeInfo.setJavaName(tableJavaName);
		makeInfo.setJavaVarName(javaVar);
		makeInfo.setColumnModels(columnModels);
		makeInfo.setEntityConditionName(tableJavaName + "EntityCondition");
		makeInfo.setEntityConditionVariableName(javaVar + "EntityCondition");
		makeInfo.setEntityName(tableJavaName + "Entity");
		makeInfo.setEntityVariableName(javaVar + "Entity");
		makeInfo.setModelName(tableJavaName + "Model");
		makeInfo.setModelVariableName(javaVar + "Model");
		makeInfo.setTableName(table.getTableName());
		makeInfo.setTableRemark(table.getRemark());
		makeInfo.setEntityMapperName(tableJavaName + "EntityMapper");
		makeInfo.setEntityMapperVariableName(javaVar + "EntityMapper");

		return makeInfo;
	}

	public void generator(Map<TableModel, List<ColumnModel>> databaseinfo, boolean isWeb)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		GeneratorApplication.database = new HashMap<Jdbc.TableModel, List<ColumnModel>>();
		
		// 开始生成
		for (Map.Entry<TableModel, List<ColumnModel>> entry : databaseinfo
				.entrySet()) {
			if (GeneratorApplication.IGNORE_TABLES.contains(entry.getKey().getTableName())) {
				continue;
			}

			GeneratorApplication.database.put(entry.getKey(), entry.getValue());
			
			List<ColumnModel> columnModels = new ArrayList<ColumnModel>(
					entry.getValue());

			// 清理字段
			for (ColumnModel columnModel : entry.getValue()) {
				if (GeneratorApplication.IGNORE_FIELDS.contains(columnModel
						.getName())) {
					columnModels.remove(columnModel);
					continue;
				}
			}

			// 初始化需要生成的信息
			MakeInfo makeInfo = getMakeInfo(columnModels, entry.getKey());

			replaceType(columnModels, makeInfo);

			make(makeInfo, isWeb);
		}
	}

	/**
	 * 
	 * 描述:使用entity中的类型替换
	 * 
	 * @param columnModels
	 * @param makeInfo
	 * @author liyixing 2017年5月22日 下午8:47:40
	 */
	private void replaceType(List<ColumnModel> columnModels, MakeInfo makeInfo) {
		for (ColumnModel columnModel : columnModels) {
			// 替换类型
			// 如果生成工具在类中存在 entity
			try {
				Field field = FieldUtils.getField(
						Class.forName(GeneratorApplication.basePackage
								+ ".entity." + makeInfo.getEntityName()),
						columnModel.getProperty(),true);

				columnModel.setValueType(field.getType().getName());
			} catch (Exception e) {
				// 读取entity失败
				UtilLog.debug("读取entity失败", e);
			}
		}
	}
}
