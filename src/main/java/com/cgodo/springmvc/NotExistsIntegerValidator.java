package com.cgodo.springmvc;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilString;

/**
 * 
 * 
 * 描述:必须不存在
 *
 * @author liyixing
 * @version 1.0
 * @since 2016年12月10日 下午2:05:19
 */
public class NotExistsIntegerValidator extends ApplicationObjectSupport implements
		ConstraintValidator<NotExists, Integer> {

	@Override
	public void initialize(NotExists constraintAnnotation) {
		this.notExists = constraintAnnotation;
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		try {
			ApplicationContext applicationContext = getApplicationContext();
			Object mapper = applicationContext.getBean(notExists.dataMapper());
			Object condition = notExists.conditionClass().newInstance();
			Object criteria = MethodUtils.invokeMethod(condition, "createCriteria");
			MethodUtils.invokeMethod(criteria, "and"+UtilString.firstToUpperCase(notExists.field())+"EqualTo",value);
			List<?> o = (List<?>) MethodUtils.invokeMethod(mapper, "selectByExample",
					condition);
			
			//不存在
			if (o .size() == 0) {
				return true;
			}

			return false;
		} catch (Exception e1) {
			UtilLog.error("验证notExists失败！", e1);
			throw new RuntimeException("验证notExists失败！",e1);
		} 
	}

	private NotExists notExists;
}
