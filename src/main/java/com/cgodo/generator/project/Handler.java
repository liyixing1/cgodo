package com.cgodo.generator.project;

import java.io.File;
import java.io.IOException;

import freemarker.template.TemplateException;

/**
 * 
 * 
 * 描述:文件处理器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年6月10日 上午10:57:14
 */
public interface Handler {
	/**
	 * 
	 * 描述:是否需要处理
	 * 
	 * @return
	 * @author liyixing 2017年6月10日 上午10:57:42
	 */
	public boolean isHandlerFileName(File file);
	
	/**
	 * 
	 * 描述:是否需要处理
	 * 
	 * @return
	 * @author liyixing 2017年6月10日 上午10:57:42
	 */
	public boolean isHandlerContent(File file);

	/**
	 * 
	 * 描述:需要重命名的情况
	 * 
	 * @return
	 * @author liyixing 2017年6月10日 上午10:58:04
	 */
	public void makeNewFile(File file);

	/**
	 * 
	 * 描述:需要处理内容的情况
	 * 
	 * @return
	 * @author liyixing 2017年6月10日 上午10:58:04
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public void makeNewFileContent(File file) throws IOException, TemplateException;
}
