package cn.gou23.cgodo.mybatis.plugin;

import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import cn.gou23.cgodo.page.Page;

/**
 * 结果集插件，防止分页情况下，mybatis再次做假分页
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-6 下午08:28:12
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PageResultSetHandlerInterceptor implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		RowBounds rowBounds = (RowBounds) FieldUtils.getField(
				target.getClass(), "rowBounds", true).get(target);
		//
		// // 非mybatis默认分页信息，就设置为mybatis默认分页，这样mybatis就不会做list的假分页了
		if (rowBounds instanceof Page<?>) {
			FieldUtils.getField(target.getClass(), "rowBounds", true).set(
					target, RowBounds.DEFAULT);
		}

		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}
}
