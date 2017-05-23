package ${basePackage}.admin.act;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import com.cgodo.springmvc.NoField;

<#list imports as import>
import ${import};
</#list>

/**

 * 描述:${remark} admin act
 *
 * @author cgodo generator
 * @since ${datetime}
 */

@RequestMapping(value = "/${tableName}")
@Controller
public class ${javaName}Act {
	/**
	 * ${remark}列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list.html")
	public String list(@ModelAttribute("form") ${javaName}Form ${javaVarName}form,
			ModelMap modelMap) {
		${modelName} ${modelVariableName} = new ${modelName}();
		
		List<${modelName}> ${modelVariableName}s = ${javaVarName}Service
				.find(${modelVariableName});
		
		modelMap.addAttribute("${modelVariableName}s", ${modelVariableName}s);

		return "/admin/${tableName}/list";
	}

	/**
	 * 添加${remark}
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save.html")
	public String save(@ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			ModelMap modelMap) {
		return "/admin/${tableName}/save";
	}
	

	/**
	 * 添加商家
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save.jhtml")
	public void save(@NoField @Validated @ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			return;
		}

		${modelName} ${modelVariableName} = new ${modelName}();
		
		${javaVarName}Service.save(${modelVariableName});
	}

	/**
	 * 修改${remark}
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.html")
	public String edit(@ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			ModelMap modelMap) {
		${modelName} ${modelVariableName} = ${javaVarName}Service.getById(${javaVarName}Form
				.getId());

		modelMap.put("${modelVariableName}", ${modelVariableName});
		
		return "/admin/${tableName}/edit";
	}

	/**
	 * 修改${remark}
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit.jhtml")
	public void edit(
			@Validated @ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			BindingResult bindingResult, ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			return;
		}

		${modelName} ${modelVariableName} = ${javaVarName}Service.getById(${javaVarName}Form
				.getId());
		
		${javaVarName}Service.update(${modelVariableName});
	}
	
	/**
	 * 删除${remark}
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete.jhtml")
	public void delete(String id,ModelMap modelMap) {
		${javaVarName}Service.delete(id);
	}

	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * ${columnModel.remark}查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/get_by_${columnModel.name?lower_case}.html")
	public void getBy${columnModel.nameMethod}(@ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			ModelMap modelMap) {
		${modelName} ${modelVariableName} = ${javaVarName}Service
				.getBy${columnModel.nameMethod}(${javaVarName}Form.get${columnModel.nameMethod}());

		modelMap.put("${modelVariableName}", ${modelVariableName});
		
		return;
	}
	</#if>
	</#list>
	
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * ${columnModel.remark}查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "/gets_by_${columnModel.name?lower_case}.html")
	public void getsBy${columnModel.nameMethod}(@ModelAttribute("form") ${javaName}Form ${javaVarName}Form,
			ModelMap modelMap) {
		List<${modelName}> ${modelVariableName}s = ${javaVarName}Service
				.getsBy${columnModel.nameMethod}(${javaVarName}Form.get${columnModel.nameMethod}());

		modelMap.put("${modelVariableName}s", ${modelVariableName}s);
		
		return;
	}
	</#if>
	</#list>

	@Autowired
	private ${javaName}Service ${javaVarName}Service;
}
