package org.mybatis.generator.plugins.mybatis3.bean;

import java.io.Serializable;

public class MyField implements Serializable {
	private static final long serialVersionUID = -4922954496549402053L;

	private String propertyName;
	private String comment;

	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
