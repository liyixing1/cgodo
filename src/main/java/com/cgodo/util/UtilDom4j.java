package com.cgodo.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * 
 * 描述:xml处理
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月7日 下午4:34:10
 */
public final class UtilDom4j {
	/**
	 * Map 转 XML
	 * 
	 * @param map
	 * @return
	 */
	public static final String map2XmlBody(Map<String, Object> vo,
			String rootElement) {
		org.dom4j.Document doc = DocumentHelper.createDocument();
		Element body = DocumentHelper.createElement(rootElement);
		doc.add(body);
		buildMap2xmlBody(body, vo);
		return doc.asXML();
	}

	/**
	 * Map 转 XML,追加到body
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final void buildMap2xmlBody(Element body,
			Map<String, Object> vo) {
		if (vo != null) {
			Iterator<String> it = vo.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (StringUtils.isNotEmpty(key)) {
					Object obj = vo.get(key);
					if (obj != null) {
						// 基础类型
						if (obj instanceof java.lang.String) {
							Element element = DocumentHelper.createElement(key);
							element.setText((String) obj);
							body.add(element);
						} else {
							// map
							if (obj instanceof java.lang.Character
									|| obj instanceof java.lang.Boolean
									|| obj instanceof java.lang.Number
									|| obj instanceof java.math.BigInteger
									|| obj instanceof java.math.BigDecimal) {
								// org.dom4j.Attribute attr = DocumentHelper
								// .createAttribute(element, "type", obj
								// .getClass().getCanonicalName());
								// element.add(attr);
								Element element = DocumentHelper
										.createElement(key);
								body.add(element);
								element.setText(String.valueOf(obj));
							} else if (obj instanceof java.util.Map) {
								// map类型
								// org.dom4j.Attribute attr = DocumentHelper
								// .createAttribute(element, "type",
								// java.util.Map.class
								// .getCanonicalName());
								// element.add(attr);
								Element element = DocumentHelper
										.createElement(key);
								body.add(element);
								buildMap2xmlBody(element,
										(Map<String, Object>) obj);
							} else if (obj instanceof Collection<?>) {
								Collection<?> objs = (Collection<?>) obj;

								for (Object tm : objs) {
									if (tm instanceof java.util.Map) {
										Element element = DocumentHelper
												.createElement(key);
										body.add(element);
										buildMap2xmlBody(element,
												(Map<String, Object>) tm);
									} else {
										Element element = DocumentHelper
												.createElement(key);
										body.add(element);
										element.setText(String.valueOf(tm));
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 根据xml消息体转化为Map
	 * 
	 * @param xml
	 * @param rootElement
	 * @return
	 * @throws DocumentException
	 */
	public static final Map<String, Object> xmlBody2map(String xml,
			String rootElement) throws DocumentException {
		org.dom4j.Document doc = DocumentHelper.parseText(xml);
		Element body = (Element) doc.getRootElement();
		Map<String, Object> vo = buildXmlBody2map(body);
		return vo;
	}

	/**
	 * 根据xml消息体转化为Map
	 * 
	 * @param xml
	 * @param rootElement
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final Map<String, Object> buildXmlBody2map(Element body) {
		Map<String, Object> vo = new TreeMap<String, Object>();
		if (body != null) {
			List<Element> elements = body.elements();

			for (Element element : elements) {
				String key = element.getName();
				if (StringUtils.isNotBlank(key)) {
					Object value = null;

					if (element.elements().size() > 0) {
						value = buildXmlBody2map(element);
					} else {
						// 基础类型
						String text = element.getTextTrim();

						if (StringUtils.isNotBlank(text)) {
							if (StringUtils.equals(text.toLowerCase(), "true")
									|| StringUtils.equals(text.toLowerCase(),
											"false"))
								value = Boolean.valueOf(text);

							if (value == null) {
								try {
									value = Integer.valueOf(text);
								} catch (Exception e) {

								}
							}

							if (value == null) {
								try {
									value = Long.valueOf(text);
								} catch (Exception e) {

								}
							}

							if (value == null) {
								try {
									BigDecimal valueBigDecimal = new BigDecimal(
											text);
									valueBigDecimal.setScale(2);
									value = valueBigDecimal;
								} catch (Exception e) {

								}
							}
							
							if (value == null) {
								value=text;
							}
						}
					}

					if (value != null) {
						// 是否集合
						Object tmp = vo.get(key);

						if (tmp == null) {
							vo.put(key, value);
						} else if (tmp instanceof Collection<?>) {
							Collection c = (Collection<?>) tmp;
							c.add(value);
						} else {
							Collection c = new ArrayList();
							c.add(value);
							c.add(tmp);
							vo.put(key, c);
						}
					}
				}
			}
		}
		return vo;
	}

	public static void main(String[] args) throws DocumentException {
		Map<String, Object> o = UtilMisc.toMap("name", "liyixing", "sex",
				"man", "names", UtilMisc.toList("l1", "l2"), "sexs",
				UtilMisc.toMap("sex1", "男", "sex2", "女"));

		System.out.println(map2XmlBody(o, "xml"));

		System.out.println(xmlBody2map(map2XmlBody(o, "xml"), "xml"));
	}
}
