package ${basePackage}.service;

<#list imports as import>
import ${import};
</#list>

/**
 * 
 * 
 * 描述:${remark}
 *
 * @author cgodo generator
 * @since ${datetime}
 */
public interface ${javaName}Service {
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * 
	 * 描述:根据${columnModel.remark}获取
	 * 
	 * @param ${columnModel.property}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public ${modelName} getBy${columnModel.nameMethod}(${columnModel.simplTypeName} ${columnModel.property});
	</#if>
	</#list>
	
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * 
	 * 描述:根据${columnModel.remark}获取
	 * 
	 * @param ${columnModel.property}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public List<${modelName}> getsBy${columnModel.nameMethod}(${columnModel.simplTypeName} ${columnModel.property});
	</#if>
	</#list>

	/**
	 * 
	 * 描述:find
	 * 
	 * @param ${modelVariableName}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public List<${modelName}> find(${modelName} ${modelVariableName});
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param ${modelVariableName}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	public List<${modelName}> findNoPage(${modelName} ${modelVariableName});

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param ${modelVariableName}
	 * @author cgodo generator ${datetime}
	 */
	public void save(${modelName} ${modelVariableName});

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param ${modelVariableName}
	 * @author cgodo generator ${datetime}
	 */
	public void update(${modelName} ${modelVariableName});

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator ${datetime}
	 */
	public void delete(String id);
}
