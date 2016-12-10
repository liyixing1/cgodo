package com.cgodo.springmvc;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilModel;

/**
 * 
 * 
 * 描述:必须不存在
 *
 * @author liyixing
 * @version 1.0
 * @since 2016年12月10日 下午2:05:19
 */
public class NotExistsValidator extends ApplicationObjectSupport implements
		ConstraintValidator<NotExists, String> {

	@Override
	public void initialize(NotExists constraintAnnotation) {
		this.notExists = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		}


		try {
			ApplicationContext applicationContext = getApplicationContext();
			Object mapper = applicationContext.getBean(notExists.dataMapper());
			Object condition = notExists.conditionClass().newInstance();
			Object criteria = MethodUtils.invokeMethod(condition, "createCriteria");
			MethodUtils.invokeMethod(criteria, "and"+UtilModel.firstToUpperCase(notExists.field())+"EqualTo",value);
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
