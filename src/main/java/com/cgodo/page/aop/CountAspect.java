package com.cgodo.page.aop;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import com.cgodo.page.Page;
import com.cgodo.page.PageContext;
import com.cgodo.util.UtilLog;

/**
 * count拦截器，负责生成count
 * 
 * Aspect标记不会扫描进去，需要自己编写bean的定义
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-6 下午09:34:24
 */
@Aspect
public class CountAspect {
	/**
	 * 描述:在调用过查询方法之后，再调用count方法。
	 * 
	 * @param pjp
	 * @return
	 * @author liyixing 2011-12-6 下午09:47:50
	 * @throws Throwable
	 */
	@SuppressWarnings({ "rawtypes" })
	@Around("execution(public * *(.., org.apache.ibatis.session.RowBounds)) ")
	public Object count(ProceedingJoinPoint pjp) throws Throwable {
		ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) FieldUtils
				.getField(pjp.getClass(), "methodInvocation", true).get(pjp);
		Object[] args = pjp.getArgs();
		RowBounds rowBounds = null;
		int index = 0;

		for (int i = 0; i < args.length; i++) {
			Object arg = args[i];

			if (arg != null && arg instanceof RowBounds) {
				rowBounds = (RowBounds) arg;
				index = i;
				break;
			}
		}

		Method sourceMethod = reflectiveMethodInvocation.getMethod();
		String method = sourceMethod.getName();
		Page page = PageContext.get();

		// 分页查询，必须存在rowBounds
		if (rowBounds == null) {
			rowBounds = RowBounds.DEFAULT;
			args[index] = rowBounds;
		}

		Object ret = pjp.proceed(args);

		try {
			// 需要分页
			if (rowBounds == page) {
				Object target = pjp.getTarget();
				int count = 0;

				if (StringUtils.endsWith(method, "Condition")) {
					count = (Integer) MethodUtils.getAccessibleMethod(
							target.getClass(), "countByCondition",
							sourceMethod.getParameterTypes()[0]).invoke(target,
							args[0]);
				} else {
					count = (Integer) MethodUtils.getAccessibleMethod(
							target.getClass(), "countByExample",
							sourceMethod.getParameterTypes()[0]).invoke(target,
							args[0]);
				}
				page.setResults((List) ret);
				// 调用目标的count方法
				page.setTotalCount(count);
			}
		} catch (Exception e) {
			UtilLog.error("调用count方法失败！", e);

			throw e;
		}

		return ret;
	}
}
