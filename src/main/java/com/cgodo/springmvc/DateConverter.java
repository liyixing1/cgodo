package com.cgodo.springmvc;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import com.cgodo.util.UtilDateTime;

/**
 * 
 * 
 * 描述:时间格式转化器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年1月2日 下午7:46:44
 */
public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		if(StringUtils.isBlank(source)) {
			return null;
		}
		
		//格式从长到短
		try{
			Date time = UtilDateTime.parse(source, UtilDateTime.YYYY_MM_DD_HH_MM_SS);
			
			return time;
		}catch(ParseException e) {
			
		}
		
		try{
			Date time = UtilDateTime.parse(source, UtilDateTime.YYYY_MM_DD);
			
			return time;
		}catch(ParseException e) {
			
		}
		
		throw new RuntimeException("string to date失败！");
	}
}
