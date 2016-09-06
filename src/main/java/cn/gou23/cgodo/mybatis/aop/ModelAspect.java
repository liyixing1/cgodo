package cn.gou23.cgodo.mybatis.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;

import cn.gou23.cgodo.util.UtilBean;
import cn.gou23.cgodo.util.UtilModel;

/**
 * Model拦截器，负责将结果的Entity转化成Model
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-6 下午09:34:24
 */
@Deprecated
@Aspect
public class ModelAspect {
	/**
	 * 描述:拦截所有的条件查询
	 * 
	 * @param pjp
	 * @return
	 * @author liyixing 2011-12-6 下午09:47:50
	 * @throws Throwable
	 */
	@Around("execution(* selectByExampleWithRowbounds(..)) || execution(* selectByPrimaryKey(..)) || execution(* selectByExample(..))"
			+ "|| execution(* selectByExampleWithBLOBsWithRowbounds(..))"
			+ "|| execution(* selectByExampleWithBLOBs(..))")
	public Object select(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Object ret = pjp.proceed(args);

		if (ret instanceof List<?>) {
			// 返回集合
			List<?> list = (List<?>) ret;

			if (list.size() > 0) {
				// Model类型
				Object entity = list.get(0);
				Class<?> modelClass = UtilModel
						.entityToModel(entity.getClass());
				List<?> modelsList = UtilBean.copysProperties(list, modelClass);

				return modelsList;
			}
		} else {
			if (ret != null) {
				Class<?> modelClass = UtilModel.entityToModel(ret.getClass());
				Object model = modelClass.newInstance();

				BeanUtils.copyProperties(ret, model);

				return model;
			}
		}
		return ret;
	}
}
