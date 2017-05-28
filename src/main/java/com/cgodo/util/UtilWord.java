package com.cgodo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UtilWord {
	/**
	 * 
	 * 描述:word转图片
	 * 
	 * @param file
	 * @author liyixing 2017年5月28日 下午1:04:42
	 * @throws Exception 
	 */
	public static final List<String> word2Img(File file) throws Exception {
		List<String> results = new ArrayList<String>();
		String url = "http://www.docpe.com/word/word-to-image.aspx";
		Map<String, Object> params = UtilMisc
				.toMap("__VIEWSTATE",
						"/wEPDwULLTE3MTExODk1MTkPZBYCZg9kFgICAw8WAh4HZW5jdHlwZQUTbXVsdGlwYXJ0L2Zvcm0tZGF0YWRkgz013LYu/ivQbeCJHdF5rL7djqk8weXauWmo0Ohccyc=",
						"__VIEWSTATEGENERATOR",
						"64DAF92B",
						"__EVENTVALIDATION",
						"/wEdAAmhyUgD9FTUMeQJH4N5MdCpCG9mt+G5mKxkJmbtRkfMQTyR1R+fT/IjuebvQlAfObN9iXMNWwR7DLE2yWIT3HqM3XZxIK/NvYMvtFx3AW3s1je5gij1i1GPrY+QGn/6BFUCVVfbMNCH7DygaimgizggFgTjtK7hK11bry/4HktSMDyLlmRoMzte3Qd9tvkDZDpjukRZZop55XYJRyAGuVdBuLlAUrqnbhfR0IqmaBiOBw==",
						"ctl00$content$FileUpload1", file,
						"ctl00$content$txtResolution", "150",
						"ctl00$content$ddlImageType", "png",
						"ctl00$content$cmdSaveAttachment", "转换");
		String result = UtilHttpClient.httpRequestPostFile(url, params);
		Document doc = Jsoup.parse(result);
		Elements elements = doc.getElementById("ctl00_content_lblNew").getElementsByTag("a");
		
		if(elements.size() > 1) {
			for(int i = 1; i< elements.size(); i++) {
				Element e = elements.get(i);
				
				results.add("http://www.docpe.com/"+e.attr("href").replace("../../", ""));
			}
		}
		
		return results;
	}
	
	public static void main(String[] args) throws Exception {
		word2Img(new File("D:\\22.doc"));
	}
}
