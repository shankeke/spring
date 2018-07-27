package com.jusfoun.common.utils.jaxb;

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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 描述 : jaxb处理工具. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月11日 上午10:34:08
 */
public class JaxbUtil {

	private final static Logger logger = LoggerFactory.getLogger(JaxbUtil.class);

	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 描述 :创建上下文对象. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2016-9-8 上午11:04:43
	 * @param clazz
	 *            转换类型
	 * @param format
	 *            是否格式化
	 * @param fragment
	 *            是否省略头信息
	 * @param encoding
	 *            编码
	 * @param schema_location
	 * @param no_namespace_schema_location
	 * @return
	 * @throws JAXBException
	 */
	private static <T> Marshaller createMarshaller(Class<T> clazz, boolean format, boolean fragment, String encoding,
			String schema_location, String no_namespace_schema_location) throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller ms = jc.createMarshaller();
			ms.setProperty(Marshaller.JAXB_ENCODING, StringUtils.defaultIfEmpty(encoding, DEFAULT_ENCODING));// 默认编码UTF-8
			ms.setProperty(Marshaller.JAXB_FRAGMENT, fragment);// 是否省略xml头声明信息
			// 用来指定是否使用换行和缩排对已编组XML数据进行格式化的属性名称
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);

			if (StringUtils.isNotEmpty(schema_location)) {
				// 用来指定将放置在已编组 XML 输出中的 xsi:schemaLocation 属性值的属性名称
				ms.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schema_location);
			}
			if (StringUtils.isNotEmpty(no_namespace_schema_location)) {
				// 用来指定将放置在已编组 XML 输出中的 xsi:noNamespaceSchemaLocation 属性值的属性名称
				ms.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, no_namespace_schema_location);
			}
			return ms;
		} catch (PropertyException e) {
			e.printStackTrace();
			logger.error("jaxb解析属性错误", e);
			throw new PropertyException("jaxb解析属性错误");
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error("jaxb转化异常", e);
			throw new JAXBException("jaxb转化异常");
		}
	}

	/**
	 * 描述 : java对象序列化为xml数据流，通过参数设置xml是否格式化和省略头信息. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	 * @param encoding
	 *            xml编码
	 * @param schema_location
	 *            The name of the property used to specify the
	 *            xsi:schemaLocation attribute value to place in the marshalled
	 *            XML output.
	 * @param no_namespace_schema_location
	 *            The name of the property used to specify the
	 *            xsi:noNamespaceSchemaLocation attribute value to place in the
	 *            marshalled XML output.
	 */
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz, boolean format, boolean fragment,
			String encoding, String schema_location, String no_namespace_schema_location) {
		try {
			Marshaller ms = createMarshaller(clazz, format, fragment, encoding, schema_location,
					no_namespace_schema_location);
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
	 * 描述 : java对象序列化为xml数据流，通过参数设置xml是否格式化和省略头信息. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz, boolean format, boolean fragment,
			String schema_location, String no_namespace_schema_location) {
		java2xml(out, t, clazz, format, fragment, DEFAULT_ENCODING, schema_location, no_namespace_schema_location);
	}

	/**
	 * 描述 : java对象序列化为xml数据流，通过参数设置xml是否格式化和省略头信息. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz, boolean format, boolean fragment) {
		java2xml(out, t, clazz, format, fragment, null, null);
	}

	/**
	 * 描述 : java对象序列化为xml数据流，默认不格式化. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(OutputStream out, T t, Class<T> clazz, boolean fragment) {
		java2xml(out, t, clazz, false, fragment);
	}

	/**
	 * 描述 :java对象序列化为xml数据流，默认不格式化xml，并且省略头信息. <br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz, boolean format, boolean fragment,
			String encoding, String schema_location, String no_namespace_schema_location) {
		try {
			Marshaller ms = createMarshaller(clazz, format, fragment, encoding, schema_location,
					no_namespace_schema_location);
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
	 * 描述 : java对象序列化为xml数据流.<br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz, boolean format, boolean fragment,
			String encoding) {
		java2xml(writer, t, clazz, format, fragment, encoding, null, null);
	}

	/**
	 * 描述 : java对象序列化为xml数据流.<br>
	 * <p>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz, boolean format, boolean fragment) {
		java2xml(writer, t, clazz, format, fragment, DEFAULT_ENCODING);
	}

	/**
	 * <pre>
	 * Description:
	 *        java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Writer writer, T t, Class<T> clazz, boolean fragment) {
		java2xml(writer, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description:
	 *        java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	public static <T> String java2xml(T t, Class<T> clazz, boolean format, boolean fragment, String encoding,
			String schema_location, String no_namespace_schema_location) {
		StringWriter stringWriter = new StringWriter();
		java2xml(stringWriter, t, clazz, format, fragment, encoding, schema_location, no_namespace_schema_location);
		return stringWriter.toString();
	}

	/**
	 * <pre>
	 * Description:
	 *        java对象序列化为xml字符串
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> String java2xml(T t, Class<T> clazz, boolean format, boolean fragment, String encoding) {
		StringWriter stringWriter = new StringWriter();
		java2xml(stringWriter, t, clazz, format, fragment, encoding);
		return stringWriter.toString();
	}

	/**
	 * <pre>
	 * Description:
	 *        java对象序列化为xml字符串
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> String java2xml(T t, Class<T> clazz, boolean format, boolean fragment) {
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(File file, T t, Class<T> clazz, boolean format, boolean fragment, String encoding,
			String schema_location, String no_namespace_schema_location) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			java2xml(out, t, clazz, format, fragment, encoding, schema_location, no_namespace_schema_location);
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
	 *       java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(File file, T t, Class<T> clazz, boolean format, boolean fragment, String encoding) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			java2xml(out, t, clazz, format, fragment, encoding, null, null);
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
	 *       java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(File file, T t, Class<T> clazz, boolean format, boolean fragment) {
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
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(File file, T t, Class<T> clazz, boolean fragment) {
		java2xml(file, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description:
	 *        java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
	 * @date 2016年1月7日 下午7:24:20
	 * @param node
	 *            xml DOM
	 * @param t
	 *            java对象
	 * @param clazz
	 *            java类
	 * @param format
	 *            是否格式化xml流，默认false
	 * @param fragment
	 *            是否省略头信息，true-是，false-否
	 */
	public static <T> void java2xml(Node node, T t, Class<T> clazz, boolean format, boolean fragment, String encoding,
			String schema_location, String no_namespace_schema_location) {
		try {
			Marshaller ms = createMarshaller(clazz, format, fragment, encoding, schema_location,
					no_namespace_schema_location);
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
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Node node, T t, Class<T> clazz, boolean format, boolean fragment, String encoding) {
		java2xml(node, t, clazz, format, fragment, encoding, null, null);
	}

	/**
	 * <pre>
	 * Description:
	 *       java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Node node, T t, Class<T> clazz, boolean format, boolean fragment) {
		java2xml(node, t, clazz, format, fragment, DEFAULT_ENCODING);
	}

	/**
	 * <pre>
	 * Description:
	 *       java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	public static <T> void java2xml(Node node, T t, Class<T> clazz, boolean fragment) {
		java2xml(node, t, clazz, false, fragment);
	}

	/**
	 * <pre>
	 * Description:
	 *       java对象序列化为xml数据流
	 * </pre>
	 *
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
			StringReader reader = new StringReader(xml);

			SAXParserFactory sax = SAXParserFactory.newInstance();
			sax.setNamespaceAware(false);
			XMLReader xmlReader = sax.newSAXParser().getXMLReader();
			Source source = new SAXSource(xmlReader, new InputSource(reader));
			return (T) unmar.unmarshal(source);
		} catch (JAXBException | SAXException | ParserConfigurationException e) {
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
	 * @author yjw@jusfoun.com
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
}
