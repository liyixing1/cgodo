package cn.gou23.cgodo.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * mybatis生成器<br>
 * 从数据库中复制一份注释
 * 
 * @author liyixing
 *
 */
public class CommentFromDatabaseGenerator extends DefaultCommentGenerator {
	public CommentFromDatabaseGenerator() {
		super();
	}

	/**
	 * 字段注释
	 * 
	 * @see org.mybatis.generator.internal.DefaultCommentGenerator#addFieldComment(org.mybatis.generator.api.dom.java.Field,
	 *      org.mybatis.generator.api.IntrospectedTable,
	 *      org.mybatis.generator.api.IntrospectedColumn)
	 */
	@Override
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null
				&& !introspectedColumn.getRemarks().equals("")) {
			field.addJavaDocLine("/**");
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			addJavadocTag(field, true);
			field.addJavaDocLine(" */");
		}
	}

	/**
	 * get方法注释
	 * 
	 * @see org.mybatis.generator.internal.DefaultCommentGenerator#addGetterComment(org.mybatis.generator.api.dom.java.Method,
	 *      org.mybatis.generator.api.IntrospectedTable,
	 *      org.mybatis.generator.api.IntrospectedColumn)
	 */
	public void addGetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null
				&& !introspectedColumn.getRemarks().equals("")) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			addJavadocTag(method, true);
			method.addJavaDocLine(" */");
		}
	}

	/**
	 * set方法注释
	 * 
	 * @see org.mybatis.generator.internal.DefaultCommentGenerator#addSetterComment(org.mybatis.generator.api.dom.java.Method,
	 *      org.mybatis.generator.api.IntrospectedTable,
	 *      org.mybatis.generator.api.IntrospectedColumn)
	 */
	public void addSetterComment(Method method,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null
				&& !introspectedColumn.getRemarks().equals("")) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			addJavadocTag(method, true);
			method.addJavaDocLine(" */");
		}
	}

	/**
	 * 
	 * 描述:类注释，条件类的内部类注释
	 * 
	 * @param innerClass
	 * @param introspectedTable
	 * @param markAsDoNotDelete
	 * @param comment
	 * @author liyixing 2015年8月6日 上午11:46:49
	 */
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		// String comment = (String) introspectedTable.getAttribute("REMARKS");
		innerClass.addJavaDocLine("/**");

		// if (comment != null && !comment.equals("")) {
		// innerClass.addJavaDocLine(" * " + comment);
		// }

		innerClass.addJavaDocLine(" * <ul>");
		if (introspectedTable.getAllColumns() != null) {
			for (int index = 0; index < introspectedTable.getAllColumns()
					.size(); index++) {
				IntrospectedColumn introspectedColumn = introspectedTable
						.getAllColumns().get(index);
				if (introspectedColumn.getRemarks() != null
						&& !introspectedColumn.getRemarks().equals("")) {
					innerClass.addJavaDocLine(" * <li>"
							+ introspectedColumn.getJavaProperty() + " "
							+ introspectedColumn.getRemarks() + "</li>");

				}
			}
		}

		innerClass.addJavaDocLine(" * </ul>");
		addJavadocTag(innerClass, markAsDoNotDelete);
		innerClass.addJavaDocLine(" */");
	}

	/**
	 * * 描述:类注释，条件类的内部类注释
	 * 
	 * @see org.mybatis.generator.internal.DefaultCommentGenerator#addClassComment(org.mybatis.generator.api.dom.java.InnerClass,
	 *      org.mybatis.generator.api.IntrospectedTable)
	 */
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable) {
		// String comment = (String) introspectedTable.getAttribute("REMARKS");
		innerClass.addJavaDocLine("/**");

		// if (comment != null && !comment.equals("")) {
		// innerClass.addJavaDocLine(" * " + comment);
		// }

		innerClass.addJavaDocLine(" * <ul>");
		if (introspectedTable.getAllColumns() != null) {
			for (int index = 0; index < introspectedTable.getAllColumns()
					.size(); index++) {
				IntrospectedColumn introspectedColumn = introspectedTable
						.getAllColumns().get(index);
				if (introspectedColumn.getRemarks() != null
						&& !introspectedColumn.getRemarks().equals("")) {
					innerClass.addJavaDocLine(" * <li>"
							+ introspectedColumn.getJavaProperty() + " "
							+ introspectedColumn.getRemarks() + "</li>");

				}
			}
		}

		innerClass.addJavaDocLine(" * </ul>");
		addJavadocTag(innerClass, false);
		innerClass.addJavaDocLine(" */");
	}
}
