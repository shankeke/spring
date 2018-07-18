package com.shanke.utils.jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

/**
 * 描述 : jaxb处理工具. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-5-13 下午4:25:59
 */
public class JaxbUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(JaxbUtil.class);

	/**
	 * 描述 : java对象序列化为xml数据流，通过参数设置xml是否格式化和省略头信息. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-13 下午4:26:16
	 * @param out
	 *            xml输出流
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化xml流，默认false
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz,
			boolean format, boolean fragment) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.setProperty(Marshaller.JAXB_FRAGMENT, fragment);// 是否省略xm头声明信息
			ms.marshal(t, out);
		} catch (PropertyException e) {
			e.printStackTrace();
			logger.error("jaxb解析属性错误", e);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("jaxb转化异常", e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("jaxb转化异常", e);
				}
			}
		}
	}

	/**
	 * 描述 : java对象序列化为xml数据流，默认不格式化. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-13 下午4:43:14
	 * @param out
	 *            输出流
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz,
			boolean fragment) {
		java2xml(out, t, clazz, false, fragment);
	}

	/**
	 * 描述 :java对象序列化为xml数据流，默认不格式化xml，并且省略头信息. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午8:02:31
	 * @param out
	 *            输出流
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 */
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz) {
		java2xml(out, t, clazz, true);
	}

	/**
	 * 描述 : java对象序列化为xml数据流.<br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-13 下午4:44:05
	 * @param writer
	 *            xml输出
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz,
			boolean format, boolean fragment) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.setProperty(Marshaller.JAXB_FRAGMENT, fragment);// 是否省略xm头声明信息
			ms.marshal(t, writer);
		} catch (PropertyException e) {
			e.printStackTrace();
			logger.error("jaxb解析属性错误", e);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("jaxb转化异常", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO异常", e);
				}
			}
		}
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:51:58
	 * @param writer
	 *            xml输出
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz,
			boolean fragment) {
		java2xml(writer, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:51:58
	 * @param writer
	 *            xml输出
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 */
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz) {
		java2xml(writer, t, clazz, true);
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml字符串
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:51:58
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化xml文档，true-是，false-否
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 * @return 返回转化xml字符串
	 */
	public static <T> String java2xml(T t, Class<T> clazz, boolean format,
			boolean fragment) {
		StringWriter stringWriter = new StringWriter();
		java2xml(stringWriter, t, clazz, format, fragment);
		return stringWriter.toString();
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml字符串
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:51:58
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 * @return 返回转化xml字符串
	 */
	public static <T> String java2xml(T t, Class<T> clazz, boolean fragment) {
		return java2xml(t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml字符串
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:51:58
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @return 返回转化xml字符串
	 */
	public static <T> String java2xml(T t, Class<T> clazz) {
		return java2xml(t, clazz, true);
	}

	/**
	 * <pre>
	 * Description: 
	 *       java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:41:54
	 * @param file
	 *            xml数据存储目标文件
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化xml文档，true-是，false-否
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(File file, T t, Class<T> clazz,
			boolean format, boolean fragment) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			java2xml(out, t, clazz, format, fragment);
		} catch (FileNotFoundException e) {
			logger.error("xml文件不存在", e);
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("IO异常", e);
				}
			}
		}
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午8:11:11
	 * @param file
	 *            xml数据存储目标文件
	 * @param t
	 *            java对象实例
	 * @param clazz
	 *            java类
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(File file, T t, Class<T> clazz,
			boolean fragment) {
		java2xml(file, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description: 
	 *        java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午8:11:11
	 * @param file
	 *            xml数据存储目标文件
	 * @param t
	 *            java对象实例
	 * @param clazz
	 *            java类
	 */
	public static <T> void java2xml(File file, T t, Class<T> clazz) {
		java2xml(file, t, clazz, true);
	}

	/**
	 * <pre>
	 * Description: 
	 *       java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:24:20
	 * @param node
	 *            xml DOM树
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化xml流，默认false
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(Node node, T t, Class<T> clazz,
			boolean format, boolean fragment) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
			ms.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			ms.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
			ms.marshal(t, node);
		} catch (PropertyException e) {
			e.printStackTrace();
			logger.error("jaxb解析属性错误", e);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("jaxb转化异常", e);
		}
	}

	/**
	 * <pre>
	 * Description: 
	 *       java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:24:20
	 * @param node
	 *            xml DOM树
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(Node node, T t, Class<T> clazz,
			boolean fragment) {
		java2xml(node, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description: 
	 *       java对象序列化为xml数据流
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:24:20
	 * @param node
	 *            xml DOM树
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 */
	public static <T> void java2xml(Node node, T t, Class<T> clazz) {
		java2xml(node, t, clazz, true);
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param xml
	 *            xml数据串
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(String xml, Class<T> clazz) {
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param in
	 *            xml数据输入流
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(InputStream in, Class<T> clazz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(in);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param file
	 *            xml数据文件
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(File file, Class<T> clazz) {
		JAXBContext jc = null;
		try {
			jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param reader
	 *            xml数据输入流
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(Reader reader, Class<T> clazz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param source
	 *            xml数据源
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(Source source, Class<T> clazz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(source);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param url
	 *            xml数据网络路径
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(URL url, Class<T> clazz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(url);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	/**
	 * <pre>
	 * Description: 
	 *         xml数据转化为java对象
	 * </pre>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年1月7日 下午7:26:57
	 * @param node
	 *            xml DOM树数据
	 * @param clazz
	 *            java类
	 * @return java对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2java(Node node, Class<T> clazz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller unmar = jc.createUnmarshaller();
			return (T) unmar.unmarshal(node);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("解析xml数据失败", e);
		}
		return null;
	}

	// public static void main(String[] args) {
	// Data data = new Data();
	// data.setContent("weweweew");
	// DataDescription dataDescription = new DataDescription();
	// dataDescription.setCodeType("0");
	// dataDescription.setEncryptCode(0);
	// dataDescription.setZipCode(0);
	// data.setDataDescription(dataDescription);
	// JaxbUtil.java2xml(System.out, data, Data.class, true, true);
	// }
}