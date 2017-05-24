package com.cgodo.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

/**
 * 加密器
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-7-18 下午3:45:51
 */
public final class UtilEncrypt {
	/**
	 * 
	 * 描述:md5加密
	 * 
	 * @return
	 * @author liyixing 2012-7-23 下午5:11:06
	 */
	public static final String encode(String s) {
		if (s == null) {
			return null;
		}
		return DigestUtils.md5Hex(s);
	}
	
	/**
	 * 
	 * 描述:md5加密
	 * 
	 * @return
	 * @author liyixing 2012-7-23 下午5:11:06
	 * @throws IOException 
	 */
	public static final String encodeFile(String path) throws IOException {
		File file = new File(path);
		String s = FileUtils.readFileToString(file);
		
		if (s == null) {
			return null;
		}
		
		return DigestUtils.md5Hex(s);
	}
	
	public static final String sha1(String s) {
		if (s == null) {
			return null;
		}
		
		return DigestUtils.sha1Hex(s);
	}
}
