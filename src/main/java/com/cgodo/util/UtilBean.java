package com.cgodo.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

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
	public static final <E> List<E> copysProperties(Collection<?> srcList,
			Class<E> targetType, String... ignoreProperties) {
		List<E> targetList = new ArrayList<E>();

		for (Object src : srcList) {// 复制
			E newBean;

			try {
				newBean = targetType.newInstance();
				BeanUtils.copyProperties(src, newBean, ignoreProperties);
				targetList.add(newBean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return targetList;
	}

	/**
	 * 
	 * 描述:复制集合，包含属性
	 * 
	 * @param srcList
	 * @param targetType
	 * @param containProperties
	 * @return
	 * @author liyixing 2015年12月19日 下午3:42:20
	 */
	public static final <E> List<E> copysContainProperties(
			Collection<?> srcList, Class<E> targetType,
			String... containProperties) {
		List<E> targetList = new ArrayList<E>();

		for (Object src : srcList) {// 复制
			E newBean;

			try {
				newBean = targetType.newInstance();
				copyContainProperties(src, newBean, containProperties);
				targetList.add(newBean);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return targetList;
	}

	/**
	 * 
	 * 描述:包含拷贝
	 * 
	 * @param src
	 * @param target
	 * @param containProperties
	 * @return
	 * @author liyixing 2015年12月19日 下午3:46:37
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E copyContainProperties(Object src,
			Class<?> targetType, String... containProperties)
			throws InstantiationException, IllegalAccessException {
		Object target = targetType.newInstance();

		copyContainProperties(src, target, containProperties);

		return (E) target;
	}

	/**
	 * 
	 * 描述:包含拷贝
	 * 
	 * @param src
	 * @param target
	 * @param containProperties
	 * @return
	 * @author liyixing 2015年12月19日 下午3:46:37
	 */
	public static final void copyContainProperties(Object src, Object target,
			String... containProperties) {
		copyContainProperties(src, target, null, containProperties);
	}

	/**
	 * 
	 * 描述:包含拷贝
	 * 
	 * @param src
	 * @param target
	 * @param containProperties
	 * @return
	 * @author liyixing 2015年12月19日 下午3:46:37
	 */
	public static final void copyContainProperties(Object src, Object target,
			Class<?> editable, String... containProperties) {
		Assert.notNull(src, "原始对象不能为空");
		Assert.notNull(target, "目标对象不能为空");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class ["
						+ target.getClass().getName()
						+ "] not assignable to Editable class ["
						+ editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = BeanUtils
				.getPropertyDescriptors(actualEditable);
		List<String> containList = (containProperties != null ? Arrays
				.asList(containProperties) : null);

		// 拷贝目标属性
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			// 包含该属性
			if (writeMethod != null
					&& (containList == null || containList.contains(targetPd
							.getName()))) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(
						src.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null
							&& ClassUtils.isAssignable(
									writeMethod.getParameterTypes()[0],
									readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod
									.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(src);
							if (!Modifier.isPublic(writeMethod
									.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						} catch (Throwable ex) {
							throw new FatalBeanException(targetPd.getName()
									+ " 无法从原始对象拷贝到目标对象中from source to target",
									ex);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * 描述:按照类型拷贝
	 * 
	 * @param src
	 * @param targetType
	 * @param ignoreProperties
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @author liyixing 2015年12月19日 下午3:46:15
	 */
	@SuppressWarnings("unchecked")
	public static final <E> E copyProperties(Object src, Class<E> targetType,
			String... ignoreProperties) throws InstantiationException,
			IllegalAccessException {
		Object target = targetType.newInstance();

		BeanUtils.copyProperties(src, target, ignoreProperties);

		return (E) target;
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
	public static final <K, V> Map<K, V> beansGroupByPkField(
			Collection<V> beans, String... fields)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<K, V> beansGroupByPkField = new HashMap<K, V>();

		for (V bean : beans) {
			K key = null;

			try {
				key = (K) getNestedProperty(bean, fields);// 取值
			} catch (NestedNullException e) {// 空值
				key = null;
				UtilLog.info("获取属性" + fields
						+ "失败，可能是层次属性中有一个属性是null，该行数据key=null。");
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
	public static final <K, V> Map<K, List<V>> beansGroupByNotPkField(
			Collection<V> beans, String... fields)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<K, List<V>> beanGroupByNotPkField = new HashMap<K, List<V>>();

		for (V bean : beans) {
			K key = null;

			try {
				key = (K) getNestedProperty(bean, fields);// 取值
			} catch (NestedNullException e) {// 空值
				key = null;
				UtilLog.info("获取属性" + fields
						+ "失败，可能是层次属性中有一个属性是null，该行数据key=null。");
			}

			List<V> thisKeyBeans = beanGroupByNotPkField.get(key); // 检查是否已经存在这个list

			if (thisKeyBeans == null) {// 不存在
				thisKeyBeans = new ArrayList<V>();
				beanGroupByNotPkField.put(key, thisKeyBeans);
			}

			thisKeyBeans.add(bean);// 存放map
		}

		return beanGroupByNotPkField;
	}

	/**
	 * 
	 * 描述:从多个bean中取出某个属性，组合成新的list，list中的元素就是bean中的field对应的值
	 * 
	 * 如果bean中属性值为null，那么会忽略该bean
	 * 
	 * @param beans
	 *            要读取的beans
	 * @param fieldNames
	 *            要获取的fieldNames
	 * @author liyixing 2011-12-28 上午10:58:50
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static final <E> List<E> beansFieldTolist(Collection<?> beans,
			String... fieldNames) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List<E> fields = new ArrayList<E>();

		for (Object bean : beans) {
			try {
				E value = (E) getNestedProperty(bean, fieldNames);// 取值

				fields.add(value);// 放值
			} catch (NestedNullException e) {// 空值
				fields.add(null);
				UtilLog.info("获取属性" + fieldNames
						+ "失败，可能是层次属性中有一个属性是null，该行数据自动添加null。");

				continue;
			}
		}

		return fields;
	}

	/**
	 * 
	 * 描述:获取对象的属性值，如果是多个属性，会进行拼接，如<br>
	 * id=1 <br>
	 * name=2<br>
	 * 那么传入id,name，得到的结果是1_2<br>
	 * 如果某个字段属性值是null，那么会忽略该字段
	 * 
	 * @param fieldName
	 * @return
	 * @author liyixing 2015年12月18日 下午2:28:36
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static final Object getNestedProperty(Object bean,
			String... fieldNames) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		boolean isFirst = true;
		String result = null;

		if (fieldNames == null || fieldNames.length == 0) {
			return null;
		}

		if (fieldNames.length == 1) {
			return PropertyUtils.getNestedProperty(bean, fieldNames[0]);
		}

		for (String fieldName : fieldNames) {
			try {
				Object value = PropertyUtils.getNestedProperty(bean, fieldName);// 取值

				if (value == null) {
					// 空值
					UtilLog.warn("获取属性" + fieldName + "值是null，忽略该字段数据。");

					continue;
				}

				if (!isFirst) {
					result += "_";
				} else {
					result = "";
				}

				result += value.toString();
				isFirst = false;
			} catch (NestedNullException e) {
				// 空值
				UtilLog.warn("获取属性" + fieldName
						+ "失败，可能是层次属性中有一个属性是null，忽略该字段数据。");

				continue;
			}
		}

		return result.length() == 0 ? null : result;
	}
}
