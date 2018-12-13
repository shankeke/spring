package org.mybatis.generator.plugins.mybatis3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.plugins.mybatis3.bean.MyField;
import org.mybatis.generator.plugins.mybatis3.bean.MyTable;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * This plugin adds the com.jusfoun.common.base.id.Idable marker interface to
 * all generated model objects.
 * 
 * <p>
 * This plugin demonstrates adding capabilities to generated Java artifacts, and
 * shows the proper way to add imports to a compilation unit.
 * 
 * <p>
 * Important: This is a simplistic implementation of serializable and does not
 * attempt to do any versioning of classes.
 * 
 * @author yjw@jusfoun.com
 * 
 */
public class ResourcesGeneratorPlugin extends PluginAdapter {

	public ResourcesGeneratorPlugin() {
		super();
	}

	/**
	 * 是否开启文件生成，生成为true，否则false
	 */
	protected boolean enable = true;

	/**
	 * 生成项目根目录
	 */
	protected String targetProject;

	/**
	 * java类生成包路径
	 */
	protected String targetPackage;

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);

		String enable = this.properties.getProperty("enable");
		if (StringUtility.stringHasValue(enable)) {
			this.enable = enable.equalsIgnoreCase("TRUE");
		}
		String targetProject = this.properties.getProperty("targetProject");
		if (StringUtility.stringHasValue(targetProject)) {
			this.targetProject = targetProject;
		} else {
			this.targetProject = this.getContext().getJavaClientGeneratorConfiguration().getTargetProject();
		}

		String targetPackage = this.properties.getProperty("targetPackage");
		if (StringUtility.stringHasValue(targetPackage)) {
			this.targetPackage = targetPackage;
		}

	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeIdable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeIdable(topLevelClass, introspectedTable);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeIdable(topLevelClass, introspectedTable);
		return true;
	}

	protected void makeIdable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		List<IntrospectedColumn> columns = introspectedTable.getAllColumns();

		MyTable table = new MyTable();
		if (columns != null && columns.size() > 0) {
			List<MyField> myFields = new ArrayList<MyField>();
			MyField myField = null;
			for (IntrospectedColumn column : columns) {
				myField = new MyField();
				myField.setPropertyName(column.getJavaProperty());
				myField.setComment(column.getRemarks());
				myFields.add(myField);
			}
			table.setFields(myFields);
			table.setName(topLevelClass.getType().getShortName().toLowerCase());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("table", table);
		generate(targetProject, targetPackage, table.getName(), "index.vue", "listvue.vue.ftl", map);
	}

	protected void generate(String targetProject, String targetPackage, String fileDir, String filename, String templateName, Map<String, Object> root) {
		String dirPath = targetProject + "/" + targetPackage.replaceAll("\\.", "/") + "/" + fileDir;
		String filePath = targetProject + "/" + targetPackage.replaceAll("\\.", "/") + "/" + fileDir + "/" + filename;
		File dir = new File(dirPath);
		File file = new File(filePath);
		if (file.exists()) {
			System.err.println(file + " already exists, it was skipped.");
			return;
		} else {
			try {
				dir.mkdirs();
				file.createNewFile();
				System.out.println("[INFO] Saving file " + file.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Configuration cfg = new Configuration(Configuration.VERSION_2_3_0);
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_0));

		try {
			Template temp = cfg.getTemplate(templateName);
			Writer out = new OutputStreamWriter(new FileOutputStream(file));
			temp.process(root, out);
			out.flush();
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
