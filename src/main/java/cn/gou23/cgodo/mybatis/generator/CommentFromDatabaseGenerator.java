package cn.gou23.cgodo.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
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
			method.addJavaDocLine(" */");
		}
	}
}
