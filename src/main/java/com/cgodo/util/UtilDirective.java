package com.cgodo.util;

import static org.springframework.web.servlet.view.AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.RequestContext;

import freemarker.core.Environment;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;

/**
 * Freemarker工具
 * 
 * <br>
 * 原型来自jeecms的DirectiveUtils，主要是因为它集成了一些异常处理功能， <br>
 * 所有的get方法是负责取出变量，但是判断是否null等处理
 */
public final class UtilDirective {

	/**
	 * 将params的值复制到环境变量中，并且把呗替换的数据返回
	 * 
	 * @param env
	 * @param params
	 * @return 原Variable中的值
	 * @throws TemplateException
	 */
	public static final Map<String, TemplateModel> addParamsToVariable(
			Environment env, Map<String, TemplateModel> params)
			throws TemplateException {
		Map<String, TemplateModel> origMap = new HashMap<String, TemplateModel>();
		if (params.size() <= 0) {
			return origMap;
		}
		Set<Map.Entry<String, TemplateModel>> entrySet = params.entrySet();
		String key;
		TemplateModel value;
		for (Map.Entry<String, TemplateModel> entry : entrySet) {
			key = entry.getKey();
			value = env.getVariable(key);
			if (value != null) {
				origMap.put(key, value);
			}
			env.setVariable(key, entry.getValue());
		}
		return origMap;
	}

	/**
	 * 将variable中的params值移除
	 * 
	 * @param env
	 * @param params
	 * @param origMap
	 * @throws TemplateException
	 */
	public static final void removeParamsFromVariable(Environment env,
			Map<String, TemplateModel> params,
			Map<String, TemplateModel> origMap) throws TemplateException {
		if (params.size() <= 0) {
			return;
		}
		for (String key : params.keySet()) {
			env.setVariable(key, origMap.get(key));
		}
	}

	/**
	 * 获得RequestContext
	 * 
	 * ViewResolver中的exposeSpringMacroHelpers必须为true
	 * 
	 * @param env
	 * @return
	 * @throws TemplateException
	 */
	public static final RequestContext getContext(Environment env)
			throws TemplateException {
		TemplateModel ctx = env
				.getGlobalVariable(SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		if (ctx instanceof AdapterTemplateModel) {
			return (RequestContext) ((AdapterTemplateModel) ctx)
					.getAdaptedObject(RequestContext.class);
		} else {
			throw new TemplateModelException("RequestContext '"
					+ SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE
					+ "' not found in DataModel.");
		}
	}

	public static final String getString(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		} else {
			throw new IllegalStateException("field is type not string");
		}
	}

	public static final String getString(TemplateModel model)
			throws TemplateException {
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else if ((model instanceof TemplateNumberModel)) {
			return ((TemplateNumberModel) model).getAsNumber().toString();
		} else {
			throw new IllegalStateException("field is type not string");
		}
	}

	public static final Long getLong(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s)) {
				return null;
			}
			try {
				return Long.parseLong(s);
			} catch (NumberFormatException e) {
				throw new IllegalStateException("field is type not long");
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().longValue();
		} else {
			throw new IllegalStateException("field is type not long");
		}
	}

	public static final Integer getInt(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			if (StringUtils.isBlank(s)) {
				return null;
			}
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				throw new IllegalStateException("field is type not integer");
			}
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().intValue();
		} else {
			throw new IllegalStateException("field is type not integer");
		}
	}

	public static final Integer[] getIntOfArray(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		String str = getString(name, params);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] arr = StringUtils.split(str, ',');
		Integer[] ids = new Integer[arr.length];
		int i = 0;
		try {
			for (String s : arr) {
				ids[i++] = Integer.valueOf(s);
			}
			return ids;
		} catch (NumberFormatException e) {
			throw new IllegalStateException("field is type not int array");
		}
	}

	public static final Long[] getLongOfArray(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		String str = getString(name, params);
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] arr = StringUtils.split(str, ',');
		Long[] ids = new Long[arr.length];
		int i = 0;
		try {
			for (String s : arr) {
				ids[i++] = Long.valueOf(s);
			}
			return ids;
		} catch (NumberFormatException e) {
			throw new IllegalStateException("field is type not int array");
		}
	}

	public static final Boolean getBool(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateBooleanModel) {
			return ((TemplateBooleanModel) model).getAsBoolean();
		} else if (model instanceof TemplateNumberModel) {
			return !(((TemplateNumberModel) model).getAsNumber().intValue() == 0);
		} else if (model instanceof TemplateScalarModel) {
			String s = ((TemplateScalarModel) model).getAsString();
			// 空串应该返回null还是true呢？
			if (!StringUtils.isBlank(s)) {
				return !(s.equals("0") || s.equalsIgnoreCase("false") || s
						.equalsIgnoreCase("f"));
			} else {
				return null;
			}
		} else {
			throw new IllegalStateException("field is type not bool");
		}
	}

	public static final Date getDate(String name,
			Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		if (model instanceof TemplateDateModel) {
			return ((TemplateDateModel) model).getAsDate();
		} else {
			throw new IllegalStateException("field is type not date");
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T getValue(Environment env, String key)
			throws TemplateModelException {
		return (T) env.__getitem__(key);
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> T getValue(Map env, String key)
			throws TemplateModelException {
		return (T) env.get(key);
	}

	public static final void setValue(Environment env, String key, Object value)
			throws TemplateException {
		env.__setitem__(key, value);
	}
}
