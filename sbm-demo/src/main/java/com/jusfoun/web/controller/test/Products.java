package com.jusfoun.web.controller.test;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
@XmlType(name = "Products", propOrder = { //
		"products"//
})
@ApiModel
public class Products {

	@ApiModelProperty("产品列表")
	@XmlElement(name = "product")
	private List<Product> products;

	public Products() {
	}

	public Products(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}