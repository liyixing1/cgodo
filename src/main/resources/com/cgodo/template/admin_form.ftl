package ${basePackage}.admin.act.form;

import com.cgodo.springmvc.Exists;
<#list imports as import>
import ${import};
</#list>

/**

 * 描述:${remark}
 *
 * @author cgodo generator
 * @since ${datetime}
 */
public class ${javaName}Form {
	/**
	 * id
	 */
	@Exists(dataMapper = ${javaName}EntityMapper.class, message = "无效的id")
	private String id;
	<#list columnModels as columnModel>
	<#if columnModel.property != 'id'>
	
	/**
	 * 
	 * 描述:${columnModel.remark}
	 * 
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	private ${columnModel.simplTypeName} ${columnModel.property};
	</#if>
	</#list>
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	<#list columnModels as columnModel>
	<#if columnModel.property != 'id'>
	
	/**
	 * 
	 * 描述:${columnModel.remark}
	 * 
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public ${columnModel.simplTypeName} get${columnModel.nameMethod}() {
		return ${columnModel.property};
	}

	/**
	 * 
	 * 描述:${columnModel.remark}
	 * 
	 * @param ${columnModel.property}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public void set${columnModel.nameMethod}(${columnModel.simplTypeName} ${columnModel.property}) {
		this.${columnModel.property} = ${columnModel.property};
	}
	</#if>
	</#list>
}
