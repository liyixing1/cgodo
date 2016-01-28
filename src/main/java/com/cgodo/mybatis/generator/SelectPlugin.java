/**
 * select返回值修改
 */
package com.cgodo.mybatis.generator;

import java.lang.reflect.Field;
import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import com.cgodo.util.UtilModel;

/**
 * select 查询结果返回类型修改
 * 
 * @author Jeff Butler
 */
public class SelectPlugin extends PluginAdapter {
	public SelectPlugin() {
	}

	public boolean validate(List<String> warnings) {
		return true;
	}

	// @Override
	// public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method
	// method,
	// Interface interfaze, IntrospectedTable introspectedTable) {
	// if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
	// updateMethodReturn(method, interfaze);
	// }
	// return true;
	// }

	/**
	 * 结果集
	 * 
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapResultMapWithoutBLOBsElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement,
	 *      org.mybatis.generator.api.IntrospectedTable)
	 */
	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		// 修改成Model
		List<Attribute> attributes = element.getAttributes();
		Attribute attributeTypeModel = null;
		Attribute attributeTypeEntity = null;

		for (Attribute attribute : attributes) {
			if ("type".equals(attribute.getName())) {
				attributeTypeEntity = attribute;
				// 找到需要修改的Entity
				attributeTypeModel = new Attribute("type",
						UtilModel.entityToModelName(attribute.getValue()));
				break;
			}
		}

		if (attributeTypeModel != null) {
			attributes.remove(attributeTypeEntity);
			attributes.add(attributeTypeModel);
		}
		return true;
	}

	/**
	 * 结果集
	 * 
	 * @see org.mybatis.generator.api.PluginAdapter#sqlMapResultMapWithoutBLOBsElementGenerated(org.mybatis.generator.api.dom.xml.XmlElement,
	 *      org.mybatis.generator.api.IntrospectedTable)
	 */
	public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		return sqlMapResultMapWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
			updateMethodReturn(method, interfaze);
		}
		return true;
	}

	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
			updateMethodReturn(method, interfaze);
		}
		return true;
	}

	// @Override
	// public boolean sqlMapDocumentGenerated(Document document,
	// IntrospectedTable introspectedTable) {
	// introspectedTable.setBaseRecordType(UtilModel
	// .entityToModelName(introspectedTable.getBaseRecordType()));
	// return true;
	// }
	//
	// public boolean sqlMapResultMapWithoutBLOBsElementGenerated(
	// XmlElement element, IntrospectedTable introspectedTable) {
	// introspectedTable.setBaseRecordType(UtilModel
	// .entityToModelName(introspectedTable.getBaseRecordType()));
	// return true;
	// }
	//
	// public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement
	// element,
	// IntrospectedTable introspectedTable) {
	// introspectedTable.setBaseRecordType(UtilModel
	// .entityToModelName(introspectedTable.getBaseRecordType()));
	// return true;
	// }

	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
			updateMethodReturn(method, interfaze);
		}
		return true;
	}

	/**
	 * 修改方法返回值
	 * 
	 * @param fullyQualifiedTable
	 * @param method
	 */
	@SuppressWarnings("unchecked")
	private void updateMethodReturn(Method method, Interface interfaze) {
		// 集合
		// 集合返回类型
		try {
			FullyQualifiedJavaType fullyQualifiedJavaType = method
					.getReturnType();
			String fullyName = fullyQualifiedJavaType.getFullyQualifiedName();
			if (fullyName.startsWith("java.util.List<")) {
				// 从集合中找到返回的entity类型
				Field typeArgumentsField = FullyQualifiedJavaType.class
						.getDeclaredField("typeArguments");
				typeArgumentsField.setAccessible(true);
				List<FullyQualifiedJavaType> typeArguments = (List<FullyQualifiedJavaType>) typeArgumentsField
						.get(fullyQualifiedJavaType);
				String entityName = typeArguments.get(0)
						.getFullyQualifiedName();
				String modelClass = UtilModel.entityToModelName(entityName);
				FullyQualifiedJavaType newReturn = new FullyQualifiedJavaType(
						"java.util.List<" + modelClass + ">");
				interfaze
						.addImportedType(new FullyQualifiedJavaType(modelClass));
				method.setReturnType(newReturn);

			} else {
				// 修改查询单个数据的返回类型
				String entityName = fullyQualifiedJavaType
						.getFullyQualifiedName();
				String modelClass = UtilModel.entityToModelName(entityName);
				FullyQualifiedJavaType newReturn = new FullyQualifiedJavaType(
						modelClass);
				method.setReturnType(newReturn);
			}
		} catch (Exception e) {
			// UtilLog.error("", e);
			throw new RuntimeException(e);
		}
	}
}
