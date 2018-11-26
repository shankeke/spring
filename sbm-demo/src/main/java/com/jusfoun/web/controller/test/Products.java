package com.jusfoun.web.controller.test;

import java.util.ArrayList;
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

	public static List<Product> getProducts(int n) {
		List<Product> products = new ArrayList<Product>();
		Product p = null;
		for (int j = 0; j < n; j++) {
			p = new Product();
			p.setId(j);
			p.setName("产品" + j);
			p.setPrice(0.5 * j);
			p.setDesc("Product:" + p.getName() + "单价是" + p.getPrice() + "元。");
			products.add(p);
		}
		return products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Products [products=" + products + "]";
	}

}
