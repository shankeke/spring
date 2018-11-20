package com.jusfoun.web.controller.test;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Person implements Serializable {
	private static final long serialVersionUID = -3485111498150496858L;

	@ApiModelProperty("ID")
	private Long id;

	@ApiModelProperty("姓名")
	private String name;

	@ApiModelProperty("年龄")
	private Byte age;

	@ApiModelProperty("出生日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birth;

	@ApiModelProperty("智商")
	private Integer iq;

	@ApiModelProperty("身高")
	private Float height;

	@ApiModelProperty("体重")
	private Float weight;

	@ApiModelProperty("财富")
	private Double wealth;

	@ApiModelProperty("简介")
	private String intro;

	public Person(Long id, String name, Byte age, Date birth, Integer iq, Float height, Float weight, Double wealth) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
		this.iq = iq;
		this.height = height;
		this.weight = weight;
		this.wealth = wealth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getIq() {
		return iq;
	}

	public void setIq(Integer iq) {
		this.iq = iq;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Double getWealth() {
		return wealth;
	}

	public void setWealth(Double wealth) {
		this.wealth = wealth;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + ", iq=" + iq + ", height=" + height + ", weight=" + weight + ", wealth=" + wealth
				+ ", intro=" + intro + "]";
	}

}
