package com.cgodo.springmvc;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 必须存在，需要注意的是，只有当value！=null，验证才会开启
 * @author Emmanuel Bernard
 */
@Target({FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistsValidator.class })
public @interface Exists {

	String message() default "{不存在的数据}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	Class<?> dataMapper();
	
	String field() default "id";
}

