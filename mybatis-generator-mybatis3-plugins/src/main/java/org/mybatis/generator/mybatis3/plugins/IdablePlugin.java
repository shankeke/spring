package org.mybatis.generator.mybatis3.plugins;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

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
public class IdablePlugin extends PluginAdapter {

	private boolean suppressJavaInterface;

	public IdablePlugin() {
		super();
	}

	@Override
	public boolean validate(List<String> warnings) {
		// this plugin is always valid
		return true;
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface")); //$NON-NLS-1$
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
		if (!suppressJavaInterface) {
			// 获取ID类型
			FullyQualifiedJavaType fullyQualifiedJavaType = null;
			List<Field> fields = topLevelClass.getFields();
			if (fields != null && fields.size() > 0) {
				List<String> annotations = null;
				OUT : for (Field field : fields) {
					annotations = field.getAnnotations();
					if (annotations != null && annotations.size() > 0) {
						for (String annotationName : annotations) {
							// 找到主键类型
							if ("@Id".equals(annotationName) || "@javax.persistence.Id".equals(annotationName)) {
								fullyQualifiedJavaType = field.getType();
								break OUT;// 跳出两层循环
							}
						}
					}
				}
			}

			// 如果存在@Id的注解则表示存在ID则添加Idable接口
			if (fullyQualifiedJavaType != null) {
				topLevelClass.addImportedType("com.jusfoun.common.base.id.Idable");
				FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType("Idable");
				superInterface.addTypeArgument(fullyQualifiedJavaType);
				topLevelClass.addSuperInterface(superInterface);
			} else {
				topLevelClass.addImportedType("java.io.Serializable");
				topLevelClass.addSuperInterface(new FullyQualifiedJavaType("Serializable"));
			}

			// 添加序列号属性
			Field field = new Field();
			field.setFinal(true);
			field.setInitializationString("1L");
			field.setName("serialVersionUID");
			field.setStatic(true);
			field.setType(new FullyQualifiedJavaType("long"));
			field.setVisibility(JavaVisibility.PRIVATE);
			context.getCommentGenerator().addFieldComment(field, introspectedTable);

			topLevelClass.addField(field);
		}
	}
}
