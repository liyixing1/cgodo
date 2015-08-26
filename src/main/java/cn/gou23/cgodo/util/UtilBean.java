package cn.gou23.cgodo.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

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

	/**
	 * 
	 * 描述:将bean集合按照指定的一个字段，该字段的值具有唯一性，如ID，分组排列组合成一个Map，map的key就是该字段的值
	 * 
	 * 如果传入的field不具有唯一性，那么可能丢失部分bean
	 * 
	 * @param beans
	 *            要排列的对象
	 * @param fields
	 *            要按照哪个字段排列，支持多个字段组合，直接使用+操作符 field1.toString +
	 *            field2.toString.... <br>
	 *            如果fiedl1 = null，那么忽略改字段 ，如果fields.length大于0，那么key必须是string类型
	 * @author liyixing 2011-12-28 上午10:20:27
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> beansGroupByPkField(Collection<V> beans,
			String... fields) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Map<K, V> beansGroupByPkField = new HashMap<K, V>();

		for (V bean : beans) {
			K key = null;
			for (String field : fields) {
				try {
					K keyTemp = (K) PropertyUtils
							.getNestedProperty(bean, field); // 取出字段值

					if (key == null) {
						key = keyTemp;
					} else {
						key = (K) (key.toString() + (keyTemp == null ? ""
								: keyTemp));
					}
				} catch (NestedNullException e) {
					// 空值
					UtilLog.warn("获取属性" + field
							+ "失败，可能是层次属性中有一个属性是null，忽略该字段数据。");

					continue;
				}
			}

			if (key != null) {
				beansGroupByPkField.put(key, bean);// 存放map
			}
		}

		return beansGroupByPkField;
	}

	/**
	 * 
	 * 描述:将bean集合按照指定的一个字段，如name，分组排列组合成一个Map，map的key就是该字段的值
	 * 
	 * Map中的value是个list，用于存放多个相同field值的bean
	 * 
	 * @param beans
	 * @param fields
	 *            要按照哪个字段排列，支持多个字段组合，直接使用+操作符 field1.toString +
	 *            field2.toString.... <br>
	 *            如果fiedl1 = null，那么忽略改字段，如果fields.length大于0，那么key必须是string类型
	 * @author liyixing 2011-12-28 上午10:20:27
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, List<V>> beansGroupByNotPkField(
			Collection<V> beans, String... fields)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<K, List<V>> beanGroupByNotPkField = new HashMap<K, List<V>>();

		for (V bean : beans) {
			K key = null;

			for (String field : fields) {
				try {
					K keyTemp = (K) PropertyUtils
							.getNestedProperty(bean, field); // 取出字段值

					if (key == null) {
						key = keyTemp;
					} else {
						key = (K) (key.toString() + (keyTemp == null ? ""
								: keyTemp));
					}
				} catch (NestedNullException e) {
					// 空值
					UtilLog.warn("获取属性" + field
							+ "失败，可能是层次属性中有一个属性是null，忽略该字段数据。");

					continue;
				}
			}

			if (key != null) {
				List<V> thisKeyBeans = beanGroupByNotPkField.get(key); // 检查是否已经存在这个list

				if (thisKeyBeans == null) {// 不存在
					thisKeyBeans = new ArrayList<V>();

					beanGroupByNotPkField.put(key, thisKeyBeans);
				}

				thisKeyBeans.add(bean);// 存放map
			}
		}

		return beanGroupByNotPkField;
	}
}
