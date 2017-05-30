package ${basePackage}.model;

import java.util.List;
import ${basePackage}.entity.${entityName};


/**
		
 * 描述:${remark}
 *
 * @author cgodo generator
 * @since ${datetime}
 */
public class ${modelName} extends ${entityName} {
	private static final long serialVersionUID = 1L;
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>

	/**
	 * 
	 * 描述:${columnModel.remark} s
	 * 
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	private List<${columnModel.simplTypeName}> <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>;
	</#if>
	</#list>
	
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * 
	 * 描述:${columnModel.remark} s
	 * 
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public List<${columnModel.simplTypeName}> get<#if columnModel.nameMethod?ends_with("s")>${columnModel.nameMethod}es<#else>${columnModel.nameMethod}s</#if>() {
		return <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>;
	}

	/**
	 * 
	 * 描述:${columnModel.remark} s
	 * 
	 * @param ${columnModel.property}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public void set<#if columnModel.nameMethod?ends_with("s")>${columnModel.nameMethod}es<#else>${columnModel.nameMethod}s</#if>(List<${columnModel.simplTypeName}> <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>) {
		this.<#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if> = <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>;
	}
	</#if>
	</#list>
}
