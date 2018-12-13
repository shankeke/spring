package org.mybatis.generator.plugins.mybatis3.bean;

import java.io.Serializable;
import java.util.List;

public class MyTable implements Serializable {
	private static final long serialVersionUID = 9171088395493810179L;

	private String name;
	private List<MyField> fields;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MyField> getFields() {
		return fields;
	}
	public void setFields(List<MyField> fields) {
		this.fields = fields;
	}

}
