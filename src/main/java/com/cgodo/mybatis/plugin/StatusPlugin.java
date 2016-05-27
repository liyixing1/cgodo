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

import com.cgodo.constant.EnumStatus;
import com.cgodo.util.UtilLog;

/**
 * 状态插件
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-11-8 下午11:48:14
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class StatusPlugin implements Interceptor {

	/**
	 * 拦截，读取执行sqlid，如果是执行的插入语句，才会执行初始化status状态，修改怎变成修改状态
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();

		// 第一个参数是MappedStatement，第二个参数是传递过来的要插入或者要修改的对象
		// if (args[1] != null) {
		MappedStatement mappedStatement = (MappedStatement) args[0];
		String id = mappedStatement.getId();
		int lastDot = id.lastIndexOf('.');
		String lastId = id.substring(lastDot + 1);
		lastId = lastId.trim().toLowerCase();

		if (lastId.startsWith("insert")) {
			initStatus(args[1]);
		} else if (lastId.startsWith("update")) {
			updateStatus(args[1]);
		}
		// }

		Object ret = invocation.proceed();

		return ret;
	}

	/**
	 * 
	 * 描述:初始化状态
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void initStatus(Object o) {
		try {
			PropertyUtils.setProperty(o, "dataStatus", EnumStatus.创建);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.debug("设置dataStatus失败！");
		}
	}

	/**
	 * 
	 * 描述:初始化状态
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void updateStatus(Object o) {
		try {
			PropertyUtils.setProperty(o, "dataStatus", EnumStatus.修改);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.debug("设置dataStatus失败！");
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
