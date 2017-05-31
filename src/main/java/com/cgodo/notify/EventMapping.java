package com.cgodo.notify;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 
 * 
 * 描述:消息映射关系
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月31日 上午12:20:20
 */
@Aspect
public class EventMapping implements ApplicationContextAware {
	Map<String, List<Handler>> handlersMap = new HashMap<String, List<Handler>>();

	@Around("@annotation(com.cgodo.notify.Event)")
	public Object handleEvent(ProceedingJoinPoint pjp) throws Throwable {
		ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation) FieldUtils
				.getField(pjp.getClass(), "methodInvocation", true).get(pjp);
		Method sourceMethod = MethodUtils.getAccessibleMethod(pjp.getTarget()
				.getClass(), reflectiveMethodInvocation.getMethod().getName(),
				reflectiveMethodInvocation.getMethod().getParameterTypes());
		Event event = AnnotationUtils.getAnnotation(sourceMethod, Event.class);
		String eventName = event.name();
		List<Handler> handlerList = handlersMap.get(eventName);

		// 前置
		if (handlerList != null) {
			for (Handler handler : handlerList) {
				if(handler.isBefore()) {
					handler.getMethod().invoke(handler.getObj(), pjp.getArgs());
				}
			}
		}
		
		Object obj = pjp.proceed(pjp.getArgs());

		//后置
		if (handlerList != null) {
			for (Handler handler : handlerList) {
				if(!handler.isBefore()) {
					handler.getMethod().invoke(handler.getObj(), pjp.getArgs());
				}
			}
		}
		
		return obj;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		// 所有包含事件处理器bean
		Map<String, Object> handlers = applicationContext
				.getBeansWithAnnotation(EventHandler.class);

		for (Map.Entry<String, Object> entry : handlers.entrySet()) {
			// 所有包含事件处理器的方法
			Method[] // jdk代理过，直接entry.getValue().getClass
						// 是无法获取该方法的注解的，需要通过AopUtils.getTargetClass
			methods = MethodUtils.getMethodsWithAnnotation(
					AopUtils.getTargetClass(entry.getValue()),
					EventHandler.class);

			for (Method method : methods) {
				EventHandler eventHandler = AnnotationUtils.getAnnotation(
						method, EventHandler.class);
				List<Handler> handlerList = handlersMap
						.get(eventHandler.name());

				if (handlerList == null) {
					handlerList = new ArrayList<EventMapping.Handler>();
					handlersMap.put(eventHandler.name(), handlerList);
				}

				Handler handler = new Handler();

				handler.setEventName(eventHandler.name());
				handler.setObj(entry.getValue());
				handler.setMethod(MethodUtils.getAccessibleMethod(
						entry.getValue().getClass(),
						method.getName(), method.getParameterTypes()));
				handler.setBefore(eventHandler.before());
				handlerList.add(handler);
			}
		}
	}

	/**
	 * 
	 * 
	 * 描述:事件处理器
	 *
	 * @author liyixing
	 * @version 1.0
	 * @since 2017年5月31日 上午12:33:44
	 */
	public class Handler {
		private Object obj;
		private Method method;
		private String eventName;
		private boolean before;

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			this.eventName = eventName;
		}

		public boolean isBefore() {
			return before;
		}

		public void setBefore(boolean before) {
			this.before = before;
		}
	}
}
