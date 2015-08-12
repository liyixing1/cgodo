package cn.gou23.cgodo.util;

/**
 * model，模型层操作工具类
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-7-18 下午3:45:51
 */
public final class UtilModel {
	/**
	 * 
	 * 描述:实体层的类转化成模型层
	 * 
	 * @param enitty
	 * @return
	 * @author liyixing 2015年8月12日 下午3:56:32
	 */
	public static final Class<?> entityToModel(Class<?> entity) {
		return entityToModel(entity.getName());
	}

	/**
	 * 
	 * 描述:实体层的类转化成模型层
	 * 
	 * @param enitty
	 * @return
	 * @author liyixing 2015年8月12日 下午3:56:32
	 */
	public static final Class<?> entityToModel(String entityName) {
		// 转化成模型层的类
		String modelClassName = entityToModelName(entityName);
		try {
			return Class.forName(modelClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static final String entityToModelName(String entityName) {
		// 转化成模型层的类
		return entityName.replace(".entity", ".model").replace("Entity",
				"Model");
	}
}
