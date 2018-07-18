package com.jusfoun.web.controller.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "product")
@XmlType(name = "Product", propOrder = { //
		"id", //
		"name", //
		"price", //
		"desc" //
})
@ApiModel
public class Product {

	@ApiModelProperty("ID")
	@XmlElement(name = "id")
	private long id;

	@ApiModelProperty("名称")
	@XmlElement(name = "name")
	private String name;

	@ApiModelProperty("价格")
	@XmlElement(name = "price")
	private double price;

	@ApiModelProperty("描述")
	@XmlElement(name = "desc")
	private String desc;

	public Product() {
	}

	public Product(long id, String name, double price, String desc) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.desc = desc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
