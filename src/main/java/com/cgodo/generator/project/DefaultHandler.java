package com.cgodo.generator.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.util.AntPathMatcher;

import com.cgodo.freemarker.TemplateHandler;
import com.cgodo.generator.GeneratorApplication;
import com.cgodo.util.UtilMisc;
import com.cgodo.util.UtilString;

import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:项目生产处理器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年6月10日 下午3:31:27
 */
public class DefaultHandler implements Handler {
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	/**
	 * 需要处理的文件内容
	 */
	private List<String> contentFileNames = UtilMisc.toList(
			"org.eclipse.wst.common.component", "generatorConfig.xml",
			"run.bat", "*.java", "log4j.xml", "SqlMapConfig.xml",
			"applicationContext.xml", "admin-servlet.xml", "front-servlet.xml",
			"数据库设计.pdm", ".project", "pom.xml");
	
	/**
	 * 需要重命名
	 */
	private List<String> newFileNames = UtilMisc.toList(
			"**/src/main/java/com/lsiding/*","**/数据库设计.pdm","**/generatorConfig.xml");

	@Override
	public boolean isHandlerFileName(File file) {
		String name = file.getAbsolutePath().replace("\\", "/");

		for (String newFileName : newFileNames) {
			if (antPathMatcher.match(newFileName, name)) {
				// 需要变更文件名
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isHandlerContent(File file) {
		String name = file.getName();

		for (String contentFileName : contentFileNames) {
			if (antPathMatcher.match(contentFileName, name)) {
				// 需要替换内容
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeNewFile(File file) {
		if(file.isDirectory()) {
			file.renameTo(new File(file.getParent()+"/" + GeneratorApplication.APPLICATION_NAME));
		} else {
			String name = file.getName();
			
			if(name.equals("数据库设计.pdm")) {
				file.renameTo(new File(file.getParent()+"/" + GeneratorApplication.APPLICATION_DESCRIPTION+".pdm"));
			} else if(name.equals("generatorConfig.xml")) {
				file.renameTo(new File(file.getParent()+"/" + GeneratorApplication.APPLICATION_NAME+UtilString.firstToUpperCase(name)));
			}
		}
		
		return;
	}

	@Override
	public void makeNewFileContent(File file) throws IOException,
			TemplateException {
		TemplateHandler templateHandler = new TemplateHandler();
		templateHandler.setBasePath(file.getParent());
		templateHandler.setFileName(file.getName());
		templateHandler.setSavePath(file.getParent());
		templateHandler.setSaveFileName(file.getName());
		Map<String, Object> params = GeneratorApplication.makeCommonParams();
		File tmp = new File(file.getParent()+"/"+"tmp");
		templateHandler.hander(params,new FileOutputStream(tmp));
		FileUtils.copyFile(tmp, file);
		tmp.delete();
	}
}
