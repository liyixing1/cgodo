package com.cgodo.generator.project;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.cgodo.generator.GeneratorApplication;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;

public class Generator {
	private Handler handler = new DefaultHandler();

	public void generator(boolean isWeb) throws MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		File dir = null;

		if (!isWeb) {
			throw new RuntimeException("暂时只支持web");
		}

		if (isWeb) {
			// 拷贝基础项目
			dir = new File(GeneratorApplication.PROJECT_SRC_PATH);
		}

		FileUtils.copyDirectory(dir, new File(GeneratorApplication.BASE_PATH
				+ "/" + GeneratorApplication.APPLICATION_NAME));
		handlerContent(new File(GeneratorApplication.BASE_PATH + "/"
				+ GeneratorApplication.APPLICATION_NAME));
		handlerReName(new File(GeneratorApplication.BASE_PATH + "/"
				+ GeneratorApplication.APPLICATION_NAME));
	}

	private void handlerContent(File file) throws IOException,
			TemplateException {
		File[] files = file.listFiles();

		for (File f : files) {
			if (f.isDirectory()) {
				handlerContent(f);
			} else {
				if (handler.isHandlerContent(f)) {
					handler.makeNewFileContent(f);
				}
			}
		}
	}

	private void handlerReName(File file) throws IOException, TemplateException {
		File[] files = file.listFiles();

		for (File f : files) {
			if (handler.isHandlerFileName(f)) {
				handler.makeNewFile(f);
			}

			if (f.isDirectory()) {
				handlerReName(f);
			}
		}
	}

	public static void main(String[] args)
			throws MalformedTemplateNameException, ParseException, IOException,
			TemplateException {
		GeneratorApplication.BASE_PATH = "C:/Users/liyixing-pc/git";
		GeneratorApplication.APPLICATION_NAME = "test";
		GeneratorApplication.APPLICATION_DESCRIPTION = "测试";
		GeneratorApplication.PROJECT_SRC_PATH = "C:/Users/liyixing-pc/git/project/web";
		Generator generator = new Generator();

		generator.generator(true);
	}
}
