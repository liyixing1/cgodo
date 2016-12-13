package com.cgodo.mybatis.generator;

import java.io.File;
import java.io.IOException;

import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.cgodo.util.UtilFile;

public class CgodoShellCallback extends DefaultShellCallback {

	public CgodoShellCallback(boolean overwrite) {
		super(overwrite);
	}

	public boolean isMergeSupported() {
		return true;
	}

	/**
	 * 合并java文件，处理时只删除@
	 * 
	 * @see org.mybatis.generator.internal.DefaultShellCallback#mergeJavaFile(java.lang.String,
	 *      java.lang.String, java.lang.String[], java.lang.String)
	 */
	public String mergeJavaFile(String newFileSource,
			String existingFileFullPath, String[] javadocTags,
			String fileEncoding) throws ShellException {
		System.out.println("开始合并java文件");

		if (!existingFileFullPath.endsWith("Mapper.java")) {
			System.out.println("暂时只能处理dao");

			return newFileSource;
		}

		try {
			String existsFileContent = UtilFile.readFileToString(new File(
					existingFileFullPath));

			// 找到最后一个mybatis生成的注释
			int index = existsFileContent
					.lastIndexOf(MergeConstants.NEW_ELEMENT_TAG);

			if (index < 1) {
				index = existsFileContent
						.lastIndexOf(MergeConstants.OLD_ELEMENT_TAGS[0]);
			}

			if (index < 1) {
				index = existsFileContent
						.lastIndexOf(MergeConstants.OLD_ELEMENT_TAGS[1]);
			}

			if (index < 1) {
				index = existsFileContent
						.lastIndexOf(MergeConstants.OLD_ELEMENT_TAGS[2]);
			}

			if (index < 1) {
				index = existsFileContent
						.lastIndexOf(MergeConstants.OLD_ELEMENT_TAGS[3]);
			}

			if (index < 1) {
				System.out.println("没有需要合并的内容");
				return newFileSource;
			}

			// 查询改字符之后的);号
			int startIndex = existsFileContent.indexOf(");", index);

			if (startIndex > index) {
				// 查询类结束到最后一个生成的代码之间的内容
				int endIndex = existsFileContent.lastIndexOf("}");

				if (endIndex > startIndex) {
					// 找到结束符
					String mergeContent = existsFileContent.substring(
							startIndex+2, endIndex);
					
					if(mergeContent != null && !mergeContent.trim().equals("")) {
						StringBuffer newFileSourceBf = new StringBuffer(newFileSource);
	
						newFileSourceBf.insert(newFileSource.lastIndexOf("}")-1, mergeContent);
						return newFileSourceBf.toString();
					} else {
						return newFileSource;
					}
				} else {
					System.out.println("没有需要合并的内容");
					return newFileSource;
				}
			} else {
				System.out.println("没有需要合并的内容");
				return newFileSource;
			}
		} catch (IOException e) {
			throw new RuntimeException("已存在文件读取失败！", e);
		}
	}
}
