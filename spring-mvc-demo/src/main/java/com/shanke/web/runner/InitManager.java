package com.shanke.web.runner;

import java.io.File;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.shanke.entity.Right;
import com.shanke.entity.Role;
import com.shanke.entity.User;
import com.shanke.utils.jaxb.JaxbUtil;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
@XmlType(name = "data", propOrder = { "users", "roles", "rights" })
public class InitManager {

	@XmlElement(name = "users")
	private List<User> users;

	@XmlElement(name = "roles")
	private List<Role> roles;

	@XmlElement(name = "rights")
	private List<Right> rights;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	public static void main(String[] args) {
		InitManager data = JaxbUtil.xml2java(new File("src/main/resources/init/data.xml"), InitManager.class);
		System.out.println(data.toString());
	}
}
