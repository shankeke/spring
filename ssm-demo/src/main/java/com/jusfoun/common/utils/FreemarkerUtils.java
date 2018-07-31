package com.jusfoun.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 描述:Freemarker Template 解析器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年3月7日 上午10:13:15
 */
public class FreemarkerUtils {

	private static final String DEFAUT_CHARSET = "UTF-8";

	private static final String DEFAUT_NUMBER_FORMAT = "#.##";

	/**
	 * 描述:初始化模板. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月7日 上午10:53:03
	 * @param templateDir
	 *            模板目录
	 * @param templateName
	 *            模板名称
	 * @return 模板
	 * @throws IOException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 */
	private static Template initTemplate(String templateDir, String templateName) throws IOException, TemplateNotFoundException, MalformedTemplateNameException, ParseException {
		// 初始化工作
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
		// 设置默认编码格式为UTF-8
		cfg.setDefaultEncoding(DEFAUT_CHARSET);
		// 全局数字格式
		cfg.setNumberFormat(DEFAUT_NUMBER_FORMAT);
		// 设置模板文件位置
		// cfg.setDirectoryForTemplateLoading(templateDirFile);
		cfg.setClassForTemplateLoading(FreemarkerUtils.class, templateDir);
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));
		// 加载模板
		Template template = cfg.getTemplate(templateName, DEFAUT_CHARSET);
		return template;
	}

	/**
	 * 描述:解析模板渲染数据生成输出. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月7日 上午10:30:00
	 * @param templateDir
	 *            模板文件夹
	 * @param templateName
	 *            模板名称
	 * @param out
	 *            输出流
	 * @param data
	 *            渲染数据
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void process(String templateDir, String templateName, OutputStream out, Map<String, Object> data) throws IOException, TemplateException {
		Template template = initTemplate(templateDir, templateName);
		OutputStreamWriter writer = null;
		try {
			// 填充数据至Excel
			writer = new OutputStreamWriter(out, DEFAUT_CHARSET);
			template.process(data, writer);
			writer.flush();
		} finally {
			writer.close();
		}
	}

	/**
	 * 描述:解析模板渲染数据生成输出文件. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月7日 上午10:34:04
	 * @param templateDir
	 *            模板目录
	 * @param templateName
	 *            模板名称
	 * @param filePath
	 *            生成的文件路径
	 * @param data
	 *            渲染数据
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void process(String templateDir, String templateName, String filePath, Map<String, Object> data) throws IOException, TemplateException {
		process(templateDir, templateName, filePath, data);
	}

	/**
	 * 描述:解析模板返回字节数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月7日 上午10:56:56
	 * @param templateDir
	 *            模板目录
	 * @param templateName
	 *            模板名称
	 * @param data
	 *            渲染数据
	 * @return 渲染后数据流字节
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static byte[] process(String templateDir, String templateName, Map<String, Object> data) throws TemplateException, IOException {
		Template template = initTemplate(templateDir, templateName);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Writer out = new OutputStreamWriter(outStream, DEFAUT_CHARSET);
		template.process(data, out);
		return outStream.toByteArray();
	}

	/**
	 * 描述:自定义模板字符串解析. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月7日 上午10:57:48
	 * @param templateStr
	 *            模板目录
	 * @param data
	 *            渲染数据
	 * @return 渲染后数据字符串
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String process(String templateStr, Map<String, Object> data) throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
		cfg.setNumberFormat(DEFAUT_NUMBER_FORMAT);
		// 设置装载模板
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("myTemplate", templateStr);
		cfg.setTemplateLoader(stringLoader);
		// 加载装载的模板
		Template temp = cfg.getTemplate("myTemplate", DEFAUT_CHARSET);
		Writer out = new StringWriter();
		temp.process(data, out);
		return out.toString();
	}
}
