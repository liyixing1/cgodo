package com.cgodo.notify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 描述:事件处理器注解
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月31日 上午12:22:11
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {
	/**
	 * 事件名称
	 * @returne
	 */
	String name() default "";
}
