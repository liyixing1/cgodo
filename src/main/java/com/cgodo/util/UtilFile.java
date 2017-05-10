package com.cgodo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

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

	/**
	 * 
	 * 描述:
	 * 
	 * @param savePath 新文件路径 
	 * @param relativePath 新文件相对路径
	 * @param oldFile
	 * @return
	 * @author liyixing 2017年5月9日 下午5:12:46
	 * @throws IOException 
	 */
	public static final String copyAsNew(String savePath, String relativePath,
			InputStream old, String  oldFileName) throws IOException {
		// 文件保存目录路径
		// String savePath = pageContext.getServletContext().getRealPath("/") +
		// "attached/";
		// 文件保存目录URL
		// String saveUrl = request.getContextPath() + "/attached/";

		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			throw new IOException("改目录不可写");
		}

		int exeIndex = oldFileName.lastIndexOf(".");
		String key =  UUID.randomUUID().toString().replace("-", "")
				+ (exeIndex>0?oldFileName.substring(exeIndex):".jpg");
		File newFile = new File(savePath+key);
		newFile.createNewFile();
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		IOUtils.copy(old, fileOutputStream);
		fileOutputStream.close();
		
		//用户目录追加一份备份
		String bakPath = System.getProperty("user.home")+"/upload_bak/";
		
		File bakFile = new File(bakPath);
		
		if(!bakFile.isDirectory()) {
			bakFile.mkdirs();
		}
		
		File bakNewFile = new File(bakPath +key);
		bakNewFile.createNewFile();
		FileUtils.copyFile(newFile, bakNewFile);
		
		return relativePath + key;
	}
}
