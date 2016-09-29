package com.cgodo.springmvc;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import com.cgodo.constant.EnumStatus;
import com.cgodo.util.UtilLog;

public class ExistsValidator extends ApplicationObjectSupport implements
		ConstraintValidator<Exists, String> {

	@Override
	public void initialize(Exists constraintAnnotation) {
		this.exists = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null) {
			return true;
		}
		
		ApplicationContext applicationContext = getApplicationContext();
		Object mapper = applicationContext.getBean(exists.dataMapper());
		try {
			Object o = MethodUtils.invokeMethod(mapper, "selectByPrimaryKey",
					value);

			if (o == null) {
				return false;
			}

			EnumStatus status = (EnumStatus) PropertyUtils.getProperty(o,
					"status");

			if (status == EnumStatus.删除) {
				return false;
			}

			return true;
		} catch (Exception e) {
			UtilLog.error("exists验证失败！", e);
			throw new RuntimeException(e);
		}
	}

	private Exists exists;
}
