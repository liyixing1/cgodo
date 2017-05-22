package com.cgodo.springmvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 禁止 传入的字段
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-6 下午09:34:24
 */
@Aspect
public class NoFieldAspect {
	/**
	 * 描述:拦截所有的条件查询
	 * 
	 * @param pjp
	 * @return
	 * @author liyixing 2011-12-6 下午09:47:50
	 * @throws Throwable
	 */
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object doMapping(ProceedingJoinPoint pjp) throws Throwable {
		ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) FieldUtils
				.getField(pjp.getClass(), "methodInvocation", true).get(pjp);
		Method sourceMethod = reflectiveMethodInvocation.getMethod();
		
		for (int parameterIndex = 0; parameterIndex < pjp.getArgs().length; parameterIndex++) {
			//获取该参数的注解
			MethodParameter methodParameter = new MethodParameter(sourceMethod,
					parameterIndex);
			Annotation[] annotations = methodParameter
					.getParameterAnnotations();

			for (Annotation ann : annotations) {
				//获取该参数的注解中是否有NoField
				NoField noField = AnnotationUtils.getAnnotation(ann,
						NoField.class);
				if (noField != null) {
					if (pjp.getArgs().length > parameterIndex + 1
							&& pjp.getArgs()[parameterIndex + 1] instanceof BindingResult) {
						//检查字段是否存在值
						Object value = PropertyUtils.getProperty(pjp.getArgs()[parameterIndex], noField.field());
						
						if(value != null) {
							BindingResult bindingResult = (BindingResult) pjp.getArgs()[parameterIndex + 1];
							
							if(value instanceof String && StringUtils.isNotBlank((String) value)) {
								bindingResult.addError(new ObjectError(noField.field(), noField.message()));
							} else {
								bindingResult.addError(new ObjectError(noField.field(), noField.message()));
							}
						}
					}
				}
			}
		}

		return pjp.proceed(pjp.getArgs());
	}
}
