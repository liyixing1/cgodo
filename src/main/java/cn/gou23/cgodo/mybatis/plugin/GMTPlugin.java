package cn.gou23.cgodo.mybatis.plugin;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import cn.gou23.cgodo.util.UtilDateTime;
import cn.gou23.cgodo.util.UtilLog;

/**
 * GMT插件，在修改的时候，写入GMT_UPDATED字段当插入数据的时候，写入GMT_CREATED字段
 * 
 * 要使用该插件，必须保证SQL ID命名符合CGODO代码规范
 * 
 * 拦截执行器Executor
 * 
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-11-8 下午11:48:14
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = {
		MappedStatement.class, Object.class }) })
public class GMTPlugin implements Interceptor {
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

		// if (args[1] != null) {
		// 第一个参数是MappedStatement，第二个参数是传递过来的要插入或者要修改的对象
		MappedStatement mappedStatement = (MappedStatement) args[0];
		// 取得要执行的映射文件中的SQL 语句 ID，包含命名空间
		String id = mappedStatement.getId();
		// 取得ID号
		int lastDot = id.lastIndexOf('.');
		String lastId = id.substring(lastDot + 1);
		lastId = lastId.trim().toLowerCase();

		if (lastId.startsWith("insert")) {
			insert(args[1], lastId);
		} else if (lastId.startsWith("update")) {
			update(args[1], lastId);
		}
		// }

		Object ret = invocation.proceed();

		// if (gmtCreated != null) {
		// if (args[1] instanceof Map<?, ?>) {
		// Map<?, ?> argsMap = (Map<?, ?>) args[1];
		//
		// ReflectUtil.setFieldValue(argsMap.get("record"), "gmtCreated",
		// gmtCreated);
		// } else {
		// ReflectUtil.setFieldValue(args[1], "gmtCreated", gmtCreated);
		// }
		// }

		return ret;
	}

	/**
	 * 
	 * 描述:修改
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void update(Object updateObject, String lastId) {
		Date now = cn.gou23.cgodo.util.UtilDateTime.getNowDate();

		// 条件修改
		if (updateObject instanceof Map<?, ?>) {
			Map<?, ?> argsMap = (Map<?, ?>) updateObject;

			updateObject = argsMap.get("record");
		}
		// 当只插入有值的字段
		// if (lastId.endsWith("selective")) {
		// try {
		// gmtCreated = (Date) ReflectUtil.getFieldValue(o, "gmtCreated");
		// // 确保创建时间不被修改
		// ReflectUtil.setFieldValue(o, "gmtCreated", null);
		// } catch (Exception e) {
		// // 该对象可能无法设置时间
		// LOG.warn(
		// "protected Date update(Object o, String lastId);设置gmtCreated失败！",
		// e);
		// }
		// }

		try {
			PropertyUtils.setProperty(updateObject, "gmtUpdated", now);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.warn("该对象可能无法设置时间gmtUpdated！");
		}
	}

	/**
	 * 
	 * 描述:插入
	 * 
	 * @author liyixing 2011-11-8 下午11:48:08
	 */
	protected void insert(Object insertObject, String lastId) {
		Date now = UtilDateTime.getNowDate();

		try {
			PropertyUtils.setProperty(insertObject, "gmtCreated", now);
			PropertyUtils.setProperty(insertObject, "gmtUpdated", now);
		} catch (Exception e) {
			// 该对象可能无法设置时间
			UtilLog.warn("在添加插入时间和修改时间时，发生错误！");
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
