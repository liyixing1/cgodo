package com.cgodo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 魔法util,用户生成map,list等等
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-29 下午2:10:06
 */
public final class UtilMisc {
	/**
	 * 
	 * 描述:根据数组生成list
	 * 
	 * @param es
	 *            数组元素
	 * @return
	 * @author liyixing 2011-12-29 下午2:13:42
	 */
	public static final <E> List<E> toList(E... es) {
		List<E> list = new ArrayList<E>(es.length);

		for (E e : es) {
			list.add(e);
		}

		return list;
	}

	/**
	 * 
	 * 描述:根据数组生成Set
	 * 
	 * @param es
	 *            数组元素
	 * @return
	 * @author liyixing 2011-12-29 下午2:13:42
	 */
	public static final <E> Set<E> toSet(E... es) {
		Set<E> set = new HashSet<E>(es.length);

		for (E e : es) {
			set.add(e);
		}

		return set;
	}

	/**
	 * 
	 * 描述:生成map
	 * 
	 * 将会自动把datas下标为偶数的作为key，下标为技术的作为value
	 * 
	 * @param datas
	 * @return
	 * @author liyixing 2012-11-26 上午12:14:49
	 */
	@SuppressWarnings("unchecked")
	public static final <K, V> Map<K, V> toMap(Object... datas) {
		Map<K, V> map = new HashMap<K, V>();

		if (datas.length % 2 != 0) {
			throw new IllegalArgumentException("无效的参数数量!必须保证datas的length为偶数。");
		}

		for (int i = 1; i < datas.length; i = i + 2) {
			map.put((K) datas[i - 1], (V) datas[i]);
		}

		return map;
	}
}
