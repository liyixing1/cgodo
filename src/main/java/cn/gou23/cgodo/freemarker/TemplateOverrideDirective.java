package cn.gou23.cgodo.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.gou23.cgodo.util.UtilDirective;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * 
 * 描述:freemarker重写机制
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月7日 下午9:45:10
 */
public class TemplateOverrideDirective implements
		freemarker.template.TemplateDirectiveModel {

	private final static String PREFIX = "_ftl_", VIEW_NAME = "baseView",
			NAME = "overrideName";

	/**
	 * 自行自定义的标签
	 * 
	 * @param body
	 *            body
	 *            用于处理自定义标签中的内容，如<@myDirective>将要被处理的内容</@myDirective>；当标签是<@myDirective
	 *            />格式时，body=null
	 *
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment,
	 *      java.util.Map, freemarker.template.TemplateModel[],
	 *      freemarker.template.TemplateDirectiveBody)
	 */
	public void execute(freemarker.core.Environment env, Map params,
			TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String currentViewName = env.getCurrentTemplate().getName();// 当前视图名称
		// 获取要重写的上级页面名称
		String beseViewName = UtilDirective.getValue(env, VIEW_NAME);// 当前父视图模版文件名称

		// viewName 路径包含解析的视图路径说明是模板页面
		if (beseViewName != null && !"".equals(currentViewName)
				&& beseViewName.contains(currentViewName)) {
			templateViewMode(env, params, body);
		}
		// 解析当前视图
		else {
			simpleViewMode(env, params, body);
		}
		/**
		 * viewName 解析嵌入的模板视图，此段代码会在所有视图解析完成后执行
		 */
		beseViewName = UtilDirective.getString(VIEW_NAME, params);
		if (beseViewName != null
				&& (!beseViewName.contains(currentViewName) || ""
						.equals(currentViewName))) {
			env.include(beseViewName, "UTF-8", true);
		}
	}

	/**
	 * 模板视图解析模式
	 * 
	 * @param env
	 * @param params
	 * @param body
	 * @throws TemplateException
	 * @throws IOException
	 */
	protected void templateViewMode(Environment env, Map params,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String value = UtilDirective.getValue(env, getName(params));
		if (StringUtils.isNotBlank(value)) {
			env.getOut().append(value);
		}
		/**
		 * value == null 说明模版文件没有被重写
		 */
		else if (body != null) {
			Writer write = new StringWriter();
			body.render(write);
			env.getOut().append(write.toString());
		}
	}

	protected String getName(Map params) throws TemplateException {
		return new StringBuffer(PREFIX).append(
				UtilDirective.getString(NAME, params)).toString();
	}

	/**
	 * 简单视图模式
	 * 
	 * @param env
	 * @param params
	 * @param body
	 * @throws TemplateException
	 * @throws IOException
	 */
	protected void simpleViewMode(Environment env, Map params,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 设置传递的所有参数
		UtilDirective.addParamsToVariable(env, params);
		Writer write = new StringWriter();
		body.render(write);
		UtilDirective.setValue(env, getName(params), write.toString());
	}
}
