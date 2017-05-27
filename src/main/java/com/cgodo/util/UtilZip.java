/**
 * zip

 * @author liyixing
 * @version 1.0
 * @since 2017年5月18日 下午3:08:28
 */

package com.cgodo.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

/**
 * 
 * 描述:
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月18日 下午3:08:28
 */

public final class UtilZip {
	/**
	 * 
	 * 描述:生成zip
	 * 
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 * @author liyixing 2017年5月18日 下午3:13:37
	 */
	public static final void zip(String zipFileName, File... inputFiles)
			throws Exception {
		UtilLog.debug("开始压缩");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		
		for(File inputFile : inputFiles) {
			zip(out, inputFile, inputFile.getName(), bo);
		}
		
		bo.close();
		out.close(); // 输出流关闭
		UtilLog.debug("压缩完成");
	}
	
	/**
	 * 
	 * 描述:生成zip
	 * 
	 * @param zipFileName
	 * @param inputFile
	 * @throws Exception
	 * @author liyixing 2017年5月18日 下午3:13:37
	 */
	public static final void zip(String zipFileName, File inputFile)
			throws Exception {
		UtilLog.debug("开始压缩");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // 输出流关闭
		UtilLog.debug("压缩完成");
	}

	private static void zip(ZipOutputStream out, File f, String base,
			BufferedOutputStream bo) throws Exception { 
		//目录
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				//空目录只生成目录
				out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
				UtilLog.debug(base + "/");
			}
			
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
			}
		} else {
			//文件
			out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
			UtilLog.debug(base);
			FileInputStream in = new FileInputStream(f);
			IOUtils.copy(in, out);
			in.close(); // 输入流关闭
		}
	}
}
