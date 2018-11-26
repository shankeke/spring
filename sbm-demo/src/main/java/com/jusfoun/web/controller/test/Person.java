package com.jusfoun.web.controller.test;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：人员信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年11月21日 上午10:54:16
 */
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((iq == null) ? 0 : iq.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((wealth == null) ? 0 : wealth.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Person other = (Person) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (iq == null) {
			if (other.iq != null)
				return false;
		} else if (!iq.equals(other.iq))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (wealth == null) {
			if (other.wealth != null)
				return false;
		} else if (!wealth.equals(other.wealth))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", birth=" + birth + ", iq=" + iq + ", height=" + height + ", weight=" + weight + ", wealth=" + wealth
				+ ", intro=" + intro + "]";
	}

}
