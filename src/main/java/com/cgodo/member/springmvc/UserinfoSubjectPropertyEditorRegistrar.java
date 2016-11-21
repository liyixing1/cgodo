package com.cgodo.member.springmvc;

import java.beans.PropertyEditorSupport;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.member.model.UserinfoModel;
import com.cgodo.member.model.UserinfoSubject;
import com.cgodo.member.service.UserinfoService;

/**
 * 用户userinfo的类型编辑器
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-4 下午10:29:31
 */
public class UserinfoSubjectPropertyEditorRegistrar implements
		PropertyEditorRegistrar, InitializingBean {
	@Override
	public void registerCustomEditors(PropertyEditorRegistry arg0) {
		arg0.registerCustomEditor(UserinfoSubject.class,
				new UserinfoModelEditor());
	}

	/**
	 * 用户类型的数据组装器
	 * 
	 * @author liyixing liyixing1@yahoo.com.cn
	 * @version 1.0
	 * @since 2011-12-4 下午10:35:18
	 */
	public class UserinfoModelEditor extends PropertyEditorSupport {
		@Override
		public void setValue(Object value) {
			if (value.getClass().equals(UserinfoSubject.class)) {
				Subject subject = SecurityUtils.getSubject();
				String id = (String) subject.getPrincipal();
				UserinfoModel userinfoModel = userinfoService.getById(id);
				// 隐藏密码
				userinfoModel.setPassword("*********");
				UserinfoSubject userinfoSubject = new UserinfoSubject();
				
				BeanUtils.copyProperties(userinfoModel, userinfoSubject);
				super.setValue(userinfoSubject);
				firePropertyChange();
			} else {
				super.setValue(value);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Autowired
	private UserinfoService userinfoService;
}
