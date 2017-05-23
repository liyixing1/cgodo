package com.cgodo.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 
 * 
 * 描述:模板处理器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午4:06:17
 */
public class TemplateHandler {
	public TemplateHandler() {

	}

	/**
	 * 
	 * 描述:处理
	 * 
	 * @param dataMap
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @author liyixing 2017年5月22日 下午4:23:35
	 */
	public void hander(Map<String, Object> dataMap)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		File file = new File(savePath);

		if (!file.isDirectory()) {
			file.mkdirs();
		}

		File outFile = new File(savePath + "/" + saveFileName);
		outFile.getAbsoluteFile();
		hander(dataMap, new FileOutputStream(outFile));
	}

	/**
	 * 
	 * 描述:处理
	 * 
	 * @param dataMap
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @author liyixing 2017年5月22日 下午4:23:35
	 */
	public void hander(Map<String, Object> dataMap, OutputStream o)
			throws IOException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		//
		Configuration configure = new Configuration(
				Configuration.VERSION_2_3_23);
		configure.setDefaultEncoding("utf-8");
		configure.setNumberFormat("0.######");

		// 加载需要装填的模板
		freemarker.template.Template template = null;
		// 设置模板装置方法和路径，FreeMarker支持多种模板装载方法。可以重servlet，classpath,数据库装载。
		configure.setDirectoryForTemplateLoading(new File(basePath));
		// 设置对象包装器
		// configure.setObjectWrapper(new DefaultObjectWrapper());
		// 设置异常处理器
		// configure
		// .setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		// 定义Template对象，注意模板类型名字与downloadType要一致
		// pos
		Writer out = new BufferedWriter(new OutputStreamWriter(o, "utf-8"));
		template = configure.getTemplate(fileName);
		template.process(dataMap, out);
		out.close();
	}

	/**
	 * 保存文件名
	 */
	private String saveFileName;
	/**
	 * 模板文件名
	 */
	private String fileName;
	/**
	 * 输出目录
	 */
	private String savePath;
	/**
	 * 
	 * 描述:模板基础目录
	 * 
	 * @return
	 * @author liyixing 2017年5月22日 下午4:13:37
	 */
	private String basePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
