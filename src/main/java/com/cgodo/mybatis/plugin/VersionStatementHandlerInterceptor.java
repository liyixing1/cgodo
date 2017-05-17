package com.cgodo.mybatis.plugin;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 修改插件
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2013-1-4 下午5:20:01
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class VersionStatementHandlerInterceptor implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		// 获取执行器
		Object statement = invocation.getTarget();
		Plugin plugin = (Plugin) FieldUtils.getField(statement.getClass(), "h",
				true).get(statement);
		RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) FieldUtils
				.getField(Plugin.class, "target", true).get(plugin);
		// 获取执行处理器
		ParameterHandler handler = routingStatementHandler
				.getParameterHandler();
		// 读取SQL语句
		BoundSql boundSql = (BoundSql) FieldUtils.getField(handler.getClass(),
				"boundSql", true).get(handler);
		String sql = boundSql.getSql();

		if (sql.startsWith("update") || sql.startsWith("UPDATE")) {
			Object model = boundSql.getParameterObject();
			
			if(model instanceof Map<?, ?>){
				return invocation.proceed();
			}
			
			Long version = (Long) PropertyUtils.getProperty(model, "version");
			
			if(version == null) {
				return invocation.proceed();
			}
			
			version = version - 1;

			// 增加条件
			int whereIndex = sql.indexOf("where ");

			// 添加版本条件
			if (whereIndex > 1) {
				sql = sql.replace("where", "where VERSION = " + version + " and ");
			} else {
				whereIndex = sql.indexOf("WHERE ");

				// 添加版本条件
				if (whereIndex > 1) {
					sql = sql.replace("WHERE ", "where VERSION = " + version + " and");
				} else {
					sql = sql + "where VERSION = " + version + " ";
				}
			}

			FieldUtils.getField(BoundSql.class, "sql", true).set(boundSql, sql);
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
