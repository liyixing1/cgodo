/**
 *

 * @author liyixing
 * @version 1.0
 * @since 2015年9月1日 上午11:26:33
 */

package cn.gou23.cgodo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 描述:对象工具，包括对象锁等功能
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年9月1日 上午11:26:33
 */

public class UtilObject {
	/**
	 * 全局锁对象 <br>
	 * 比如订单，所有针对该的增删改操作，只能同时进行一个，那么可以通过对订单ID生成一个唯一的锁
	 */
	private static final Map<Object, Object> LOCK_MAP = new HashMap<Object, Object>();

	/**
	 * 获取全局锁对象 <br>
	 * 比如订单，所有针对该的增删改操作，只能同时进行一个，那么可以通过对订单ID生成一个唯一的锁
	 */
	public static final synchronized Object getLockObject(Object key) {
		Object lock = LOCK_MAP.get(key);
		
		if(lock == null) {
			lock = new Object();
			LOCK_MAP.put(key, lock);
		}
		
		return lock;
	}
}
