package com.cgodo.springmvc;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 必须不存在，需要注意的是，只有当value！=null，验证才会开启
 * 
 * @author Emmanuel Bernard
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { NotExistsValidator.class })
public @interface NotExists {

	String message() default "{已存在的数据}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 
	 * 描述:dao
	 * 
	 * @return
	 * @author liyixing 2016年12月10日 下午2:09:57
	 */
	Class<?> dataMapper();

	/**
	 * 
	 * 描述:字段
	 * 
	 * @return
	 * @author liyixing 2016年12月10日 下午2:10:06
	 */
	public String field();

	/**
	 * 
	 * 描述:true表示如果id!=本身的id，才算匹配到存在
	 * 
	 * @return
	 * @author liyixing 2016年12月10日 下午2:19:41
	 */
	public boolean isNotMySelf() default false;

	/**
	 * 
	 * 描述:条件类
	 * 
	 * @return
	 * @author liyixing 2016年12月10日 下午2:10:35
	 */
	public Class<?> conditionClass();
}
