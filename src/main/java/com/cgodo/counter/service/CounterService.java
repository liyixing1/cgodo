package com.cgodo.counter.service;

import com.cgodo.counter.model.CounterModel;

/**
 * 计数器
 * @author liyixing-pc
 *
 */
public interface CounterService {
	/**
	 * 读取，不改变值
	 * @param name
	 * @return
	 */
	public CounterModel read(String type,String name);
	
	/**
	 * 获取数据，同时会让值增加<br>
	 * 处理过并发处理
	 * @param type
	 * @param name
	 * @param init 初始值
	 * @param inc 递增值
	 * @return
	 */
	public CounterModel readAndInc(String type,String name);
	
	/**
	 * 获取数据，同时会让值增加
	 * <br>
	 * 未处理过并发处理
	 * @param type
	 * @param name
	 * @param init 初始值
	 * @param inc 递增值
	 * @return
	 */
	public CounterModel readAndInc(String type,String name,long init, long inc);
}
