package com.cgodo.springmvc;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 不存续存在的字段
 * 
 * @author Emmanuel Bernard
 */
@Target({ PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface NoField {
	String message() default "不允许传入id";

	/**
	 * 
	 * 描述:字段
	 * 
	 * @return
	 * @author liyixing 2016年12月10日 下午2:10:06
	 */
	public String field() default "id";
}
