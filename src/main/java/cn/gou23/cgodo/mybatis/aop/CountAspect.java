package cn.gou23.cgodo.mybatis.aop;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import cn.gou23.cgodo.page.Page;
import cn.gou23.cgodo.page.PageContext;
import cn.gou23.cgodo.util.UtilLog;

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
	@Around("execution(* selectByExampleWithRowbounds(.., org.apache.ibatis.session.RowBounds))")
	public Object count(ProceedingJoinPoint pjp) throws Throwable {
		ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) FieldUtils
				.getField(pjp.getClass(), "methodInvocation", true).get(pjp);
		Object[] args = pjp.getArgs();
		RowBounds rowBounds = (RowBounds) args[1];
		Method sourceMethod = reflectiveMethodInvocation.getMethod();
		Page<?> page = PageContext.get();

		// 分页查询，必须存在rowBounds
		if (rowBounds == null) {
			rowBounds = RowBounds.DEFAULT;
			args[1] = rowBounds;
		}

		Object ret = pjp.proceed(args);

		try {
			// 需要分页
			if (rowBounds == page) {
				Object target = pjp.getTarget();
				int count = (Integer) MethodUtils.getAccessibleMethod(
						target.getClass(), "countByExample",
						sourceMethod.getParameterTypes()[0]).invoke(target,
						args[0]);

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
