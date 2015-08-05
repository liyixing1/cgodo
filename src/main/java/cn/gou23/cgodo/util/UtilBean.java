package cn.gou23.cgodo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Bean操作工具类
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-7-18 下午3:45:51
 */
public final class UtilBean {
	/**
	 * 
	 * 描述:从一个类型的list复制到另外一个类型的list
	 * 
	 * @param srcList
	 *            要复制的原始集合
	 * @param targetType
	 *            要复制的目标类型
	 * @param ignoreProperties
	 *            要忽略的属性
	 * @return
	 * @author liyixing 2012-7-23 下午5:11:06
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <E> List<E> copyProperties(Collection<?> srcList,
			Class<E> targetType, String... ignoreProperties) {
		List<E> targetList = new ArrayList<E>();

		for (Object src : srcList) {// 复制
			E newBean;
			
			try {
				newBean = targetType.newInstance();
				org.springframework.beans.BeanUtils.copyProperties(src,
						newBean, ignoreProperties);
				targetList.add(newBean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return targetList;
	}
}
