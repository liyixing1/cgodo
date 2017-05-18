package com.cgodo.mybatis.plugin;

import java.util.Properties;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * ID生成器
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2013-1-3 下午5:50:08
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class IdPlugin implements Interceptor {
	/**
	 * 拦截器
	 * 
	 * @param invocation调用者信息
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 取得所有参数
		Object[] args = invocation.getArgs();
		// 第一个参数是MappedStatement，第二个参数是传递过来的要插入或者要修改的对象
		MappedStatement mappedStatement = (MappedStatement) args[0];
		// 取得要执行的映射文件中的SQL 语句 ID，包含命名空间
		String id = mappedStatement.getId();
		// 取得ID号
		int lastDot = id.lastIndexOf('.');
		String lastId = id.substring(lastDot + 1);
		lastId = lastId.trim().toLowerCase();

		// insert操作
		if (lastId.startsWith("insert")) {
			PropertyUtils.setProperty(args[1], "id", UUID.randomUUID()
						.toString().replace("-", ""));
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}
}