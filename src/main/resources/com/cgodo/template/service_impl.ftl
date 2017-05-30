package ${basePackage}.service.impl;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgodo.constant.EnumStatus;
import com.cgodo.page.PageContext;

<#list imports as import>
import ${import};
</#list>

/**
 * 
 * 
 * 描述:${remark} service impl
 *
 * @author cgodo generator
 * @since ${datetime}
 */
@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
public class ${javaName}ServiceImpl implements ${javaName}Service {
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
	@Override
	public ${modelName} getBy${columnModel.nameMethod}(${columnModel.simplTypeName} ${columnModel.property}) {
		${entityConditionName} ${entityConditionVariableName} = new ${entityConditionName}();

		${entityConditionVariableName}.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).and${columnModel.nameMethod}EqualTo(${columnModel.property});

		List<${modelName}> ${modelVariableName}s = ${entityMapperVariableName}
				.selectByExample(${entityConditionVariableName});

		return ${modelVariableName}s.size() == 0 ? null : ${modelVariableName}s.get(0);
	}
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
	@Override
	public List<${modelName}> getsBy${columnModel.nameMethod}(${columnModel.simplTypeName} ${columnModel.property}) {
		${entityConditionName} ${entityConditionVariableName} = new ${entityConditionName}();

		${entityConditionVariableName}.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).and${columnModel.nameMethod}EqualTo(${columnModel.property});

		List<${modelName}> ${modelVariableName}s = ${entityMapperVariableName}
				.selectByExample(${entityConditionVariableName});

		return ${modelVariableName}s;
	}
	</#if>
	</#list>
	
	<#list columnModels as columnModel>
	<#if columnModel.typeName != 'TEXT'>
	
	/**
	 * 
	 * 描述:根据${columnModel.remark} s in 获取
	 * 
	 * @param <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	@Override
	public List<${modelName}> getsIn<#if columnModel.nameMethod?ends_with("s")>${columnModel.nameMethod}es<#else>${columnModel.nameMethod}s</#if>(List<${columnModel.simplTypeName}> <#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>) {
		${entityConditionName} ${entityConditionVariableName} = new ${entityConditionName}();

		${entityConditionVariableName}.createCriteria()
				.andStatusNotEqualTo(EnumStatus.删除).and${columnModel.nameMethod}In(<#if columnModel.property?ends_with("s")>${columnModel.property}es<#else>${columnModel.property}s</#if>);

		List<${modelName}> ${modelVariableName}s = ${entityMapperVariableName}
				.selectByExample(${entityConditionVariableName});

		return ${modelVariableName}s;
	}
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
	@Override
	public List<${modelName}> find(${modelName} ${modelVariableName}) {
		${entityConditionName} ${entityConditionVariableName} = new ${entityConditionName}();
		${entityConditionName}.Criteria criteria = ${entityConditionVariableName}
				.createCriteria();
		
		${entityConditionVariableName}.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		<#list columnModels as columnModel>
		<#if columnModel.typeName != 'TEXT'>
		<#if columnModel.valueType == "java.lang.String">
		if (StringUtils.isNotBlank(${modelVariableName}.get${columnModel.nameMethod}())) {
			criteria.and${columnModel.nameMethod}Like("%" + ${modelVariableName}.get${columnModel.nameMethod}() + "%");
		}
		<#elseif columnModel.valueType == "java.util.Date">
		if (${modelVariableName}.get${columnModel.nameMethod}() != null) {
			criteria.and${columnModel.nameMethod}Between(UtilDateTime.setDateToFirstTime(${modelVariableName}.get${columnModel.nameMethod}()), UtilDateTime.setDateToLastTime(${modelVariableName}.get${columnModel.nameMethod}()));
		}
		<#else>
		if (${modelVariableName}.get${columnModel.nameMethod}() != null) {
			criteria.and${columnModel.nameMethod}EqualTo(${modelVariableName}.get${columnModel.nameMethod}());
		}
		</#if>
		</#if>
		</#list>

		return ${entityMapperVariableName}.selectByExampleWithRowbounds(${entityConditionVariableName}, PageContext.get());
	}
	
	/**
	 * 
	 * 描述:不使用分页
	 * 
	 * @param ${modelVariableName}
	 * @return
	 * @author cgodo generator ${datetime}
	 */
	@Override
	public List<${modelName}> findNoPage(${modelName} ${modelVariableName}) {
		${entityConditionName} ${entityConditionVariableName} = new ${entityConditionName}();
		${entityConditionName}.Criteria criteria = ${entityConditionVariableName}
				.createCriteria();
		
		${entityConditionVariableName}.setOrderByClause("GMT_CREATED DESC, ID DESC");
		criteria.andStatusNotEqualTo(EnumStatus.删除);

		<#list columnModels as columnModel>
		<#if columnModel.typeName != 'TEXT'>
		<#if columnModel.valueType == "java.lang.String">
		if (StringUtils.isNotBlank(${modelVariableName}.get${columnModel.nameMethod}())) {
			criteria.and${columnModel.nameMethod}Like("%" + ${modelVariableName}.get${columnModel.nameMethod}() + "%");
		}
		<#elseif columnModel.valueType == "java.util.Date">
		if (${modelVariableName}.get${columnModel.nameMethod}() != null) {
			criteria.and${columnModel.nameMethod}Between(UtilDateTime.setDateToFirstTime(${modelVariableName}.get${columnModel.nameMethod}()), UtilDateTime.setDateToLastTime(${modelVariableName}.get${columnModel.nameMethod}()));
		}
		<#else>
		if (${modelVariableName}.get${columnModel.nameMethod}() != null) {
			criteria.and${columnModel.nameMethod}EqualTo(${modelVariableName}.get${columnModel.nameMethod}());
		}
		</#if>
		</#if>
		</#list>

		return ${entityMapperVariableName}.selectByExample(${entityConditionVariableName});
	}

	/**
	 * 
	 * 描述:添加
	 * 
	 * @param ${modelVariableName}
	 * @author cgodo generator ${datetime}
	 */
	@Override
	public void save(${modelName} ${modelVariableName}) {
		${entityMapperVariableName}.insert(${modelVariableName});
	}

	/**
	 * 
	 * 描述:修改
	 * 
	 * @param ${modelVariableName}
	 * @author cgodo generator ${datetime}
	 */
	@Override
	public void update(${modelName} ${modelVariableName}) {
		${entityMapperVariableName}.updateByPrimaryKey(${modelVariableName});
	}

	/**
	 * 
	 * 描述:删除
	 * 
	 * @param id
	 * @author cgodo generator ${datetime}
	 */
	@Override
	public void delete(String id) {
		${modelName} ${modelVariableName} = getById(id);
		
		${modelVariableName}.setStatus(EnumStatus.删除);
		update(${modelVariableName});
	}
	
	@Autowired
	private ${entityMapperName} ${entityMapperVariableName};
}
