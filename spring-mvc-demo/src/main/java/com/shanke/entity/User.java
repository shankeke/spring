package com.shanke.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.shanke.utils.list.IListUtil;

@Table(name = "t_user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
@XmlType(name = "user", propOrder = { "id", "username", "password", "phone",
		"age", "email" })
public class User extends BaseEntity {
	private static final long serialVersionUID = 5735876922028357583L;

	/**
	 * <pre>
	 * Bean Validation 中内置的 constraint       
	 * &#64;Null   被注释的元素必须为 null       
	 * &#64;NotNull    被注释的元素必须不为 null       
	 * &#64;AssertTrue     被注释的元素必须为 true       
	 * &#64;AssertFalse    被注释的元素必须为 false       
	 * &#64;Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值       
	 * &#64;Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值       
	 * &#64;DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值       
	 * &#64;DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值       
	 * &#64;Size(max=, min=)   被注释的元素的大小必须在指定的范围内       
	 * &#64;Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内       
	 * &#64;Past   被注释的元素必须是一个过去的日期       
	 * &#64;Future     被注释的元素必须是一个将来的日期       
	 * &#64;Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式     
	 *   
	 * Hibernate Validator 附加的 constraint       
	 * &#64;NotBlank(message =)   验证字符串非null，且长度必须大于0       
	 * &#64;Email  被注释的元素必须是电子邮箱地址       
	 * &#64;Length(min=,max=)  被注释的字符串的大小必须在指定的范围内       
	 * &#64;NotEmpty   被注释的字符串的必须非空       
	 * &#64;Range(min=,max=,message=)  被注释的元素必须在合适的范围内
	 * </pre>
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private String phone;

	private Integer age;

	private String email;

	private Boolean locked;

	@Transient
	private List<Role> roles;// 一个用户具有多个角色

	public User() {
	}

	public User(String username, String password, String phone, Integer age,
			String email) {
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.age = age;
		this.email = email;
	}

	/**
	 * 描述 : 获取角色名称列表. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年8月4日 下午9:57:44
	 * @return
	 */
	@Transient
	public Set<String> getRoleNames() {
		List<Role> roles = getRoles();
		Set<String> set = new HashSet<String>();
		if (IListUtil.hasData(roles)) {
			for (Role role : roles) {
				set.add(role.getName());
			}
		}
		return set;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", phone=" + phone + ", age=" + age + ", email="
				+ email + "]";
	}

}