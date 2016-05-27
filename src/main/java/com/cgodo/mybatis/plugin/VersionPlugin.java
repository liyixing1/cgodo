package com.cgodo.mybatis.plugin;

import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.cgodo.util.UtilLog;

/**
 * 版本插件
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-11-8 下午11:48:14
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class VersionPlugin implements Interceptor {

	/**
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		// if (args[1] != null) {
		MappedStatement mappedStatement = (MappedStatement) args[0];
		String id = mappedStatement.getId();
		int lastDot = id.lastIndexOf('.');
		String lastId = id.substring(lastDot + 1);
		lastId = lastId.trim().toLowerCase();

		if (lastId.startsWith("update")) {
			addVersion(args[1]);
		} else if(lastId.startsWith("insert")) {
			initVersion(args[1]);
		}
		// }

		Object ret = invocation.proceed();

		return ret;
	}
	
	/**
	 * 
	 * 描述:修改语句，添加条件
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void initVersion(Object o) {
		try {
			PropertyUtils.setProperty(o, "version", 1l);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.debug("设置version失败！");
		}
	}

	/**
	 * 
	 * 描述:修改语句，添加条件
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void addVersion(Object o) {
		try {
			Long version = (Long) PropertyUtils.getProperty(o, "version");
			PropertyUtils.setProperty(o, "version", version+1);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.debug("设置version失败！");
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}
}
