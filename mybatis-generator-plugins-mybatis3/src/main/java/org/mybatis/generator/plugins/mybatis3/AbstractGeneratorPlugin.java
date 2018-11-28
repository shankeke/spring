package org.mybatis.generator.plugins.mybatis3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 说明： Service和Controller底层代码生成抽象. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018-11-28 18:04:17
 */
public abstract class AbstractGeneratorPlugin extends PluginAdapter {

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

	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		if (!enable) {
			return null;
		}

		String javaRepositoryPackage = this.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
		String javaMapperType = introspectedTable.getMyBatis3JavaMapperType();
		String topPackage = javaRepositoryPackage.substring(0, javaRepositoryPackage.lastIndexOf('.'));
		String javaClassName = javaMapperType.substring(javaMapperType.lastIndexOf('.') + 1, javaMapperType.length()).replace("Mapper", "");

		Map<String, String> root = new HashMap<String, String>();
		root.put("topPackage", topPackage);
		root.put("entityPackage", this.getContext().getJavaModelGeneratorConfiguration().getTargetPackage());
		root.put("injectPackage", javaRepositoryPackage);
		root.put("targetPackage", targetPackage);
		root.put("EntityName", javaClassName);
		root.put("entityName", new StringBuilder().append(Character.toLowerCase(javaClassName.charAt(0))).append(javaClassName.substring(1)).toString());
		generate(topPackage, targetProject, javaClassName, root);
		return null;
	}

	/**
	 * 说明： 留给子类去实现具体的生成逻辑. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月20日 上午9:32:30
	 * @param topPackage
	 *            Mapper层包路径
	 * @param targetProject
	 *            配置文件配置的包路径
	 * @param javaClassName
	 *            model的名称
	 * @param root
	 *            传入模板的的参数
	 */
	public void generate(String topPackage, String targetProject, String javaClassName, Map<String, String> root) {
	}

	protected void generate(String targetProject, String targetPackage, String javaClassName, String fileSuffix, String templateName, Map<String, String> root) {
		String dirPath = targetProject + "/" + targetPackage.replaceAll("\\.", "/");
		String filePath = targetProject + "/" + targetPackage.replaceAll("\\.", "/") + "/" + javaClassName + fileSuffix;
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTargetProject() {
		return targetProject;
	}

	public void setTargetProject(String targetProject) {
		this.targetProject = targetProject;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
}
