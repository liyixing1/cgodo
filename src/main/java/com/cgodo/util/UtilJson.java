package com.cgodo.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

/**
 * 
 * 
 * 描述:Json处理
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月5日 下午10:29:09
 */
public class UtilJson {
	private static final com.fasterxml.jackson.databind.ObjectMapper MAPPER = new com.fasterxml.jackson.databind.ObjectMapper();

	/**
	 * 将对象转换成JSON字符串
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static final String toJson(Object obj) {
		try {
			return MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	 * 描述:
	 * 
	 * @param str
	 * @return
	 * @author huangpengpeng2013年9月9日 下午3:04:12
	 */
	// @SuppressWarnings("unchecked")
	// public final static Map<String, Object> toMap(String str) {
	// try {
	// return MAPPER.readValue(str, Map.class);
	// } catch (JsonParseException e) {
	// handerException(e);
	// } catch (JsonMappingException e) {
	// handerException(e);
	// } catch (IOException e) {
	// handerException(e);
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// public final static List<Object> toList(String str) {
	// try {
	// return MAPPER.readValue(str, List.class);
	// } catch (JsonParseException e) {
	// handerException(e);
	// } catch (JsonMappingException e) {
	// handerException(e);
	// } catch (IOException e) {
	// handerException(e);
	// }
	// }
	//
	// /**
	// * JSON字符串异常处理
	// *
	// * @param ex
	// */
	// private final static void handerException(Throwable ex) {
	// throw new IllegalStateException(ex.getMessage(), ex);
	// }
}
