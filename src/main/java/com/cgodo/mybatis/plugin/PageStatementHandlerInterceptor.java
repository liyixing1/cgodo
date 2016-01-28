package com.cgodo.mybatis.plugin;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.cgodo.mybatis.dialect.IDialect;
import com.cgodo.page.Page;
import com.cgodo.util.UtilLog;

/**
 * 分页查询器插件
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2013-1-4 下午5:20:01
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageStatementHandlerInterceptor implements Interceptor {
	/**
	 * 默认方言
	 */
	private static final String DEFAULT_DIALECT = "com.cgodo.mybatis.dialect.MySQLDialect";
	/**
	 * 方言对象
	 */
	private IDialect dialect;

	public Object intercept(Invocation invocation) throws Throwable {
		// 获取执行器
		RoutingStatementHandler statement = (RoutingStatementHandler) invocation
				.getTarget();
		// 获取执行处理器
		PreparedStatementHandler handler = (PreparedStatementHandler) FieldUtils
				.getField(RoutingStatementHandler.class, "delegate", true).get(
						statement);
		// 从执行处理器中获取分页信息
		RowBounds rowBounds = (RowBounds) FieldUtils.getField(
				PreparedStatementHandler.class, "rowBounds", true).get(handler);

		if (rowBounds instanceof Page<?>) {
			// 读取SQL语句
			BoundSql boundSql = statement.getBoundSql();
			String sql = boundSql.getSql();

			sql = dialect.getPageString(sql, rowBounds.getOffset(),
					rowBounds.getLimit());
			FieldUtils.getField(BoundSql.class, "sql", true).set(boundSql, sql);
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		//初始化方言
		String dialectClassName = (String) properties.get("dialect");

		if (StringUtils.isNotBlank(dialectClassName)) {
			dialectClassName = dialectClassName.trim();
		} else {
			dialectClassName = DEFAULT_DIALECT;
		}

		try {
			dialect = (IDialect) Class.forName(dialectClassName).newInstance();
		} catch (Exception exception) {
			UtilLog.error("初始化方言:" + dialectClassName + "出错", exception);

			throw new RuntimeException(exception);
		}
	}
}
