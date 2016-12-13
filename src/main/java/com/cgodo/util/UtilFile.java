package com.cgodo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UtilFile {
	/**
	 * 
	 * 描述: 读取文本文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @author liyixing 2016年12月12日 下午5:49:21
	 */
	public static final String readFileToString(File file) throws IOException {
		String encoding = "utf-8";
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream in = new FileInputStream(file);
		in.read(filecontent);
		in.close();

		return new String(filecontent, encoding);
	}
}
