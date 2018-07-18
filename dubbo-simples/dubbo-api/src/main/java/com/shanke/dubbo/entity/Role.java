package com.shanke.dubbo.entity;

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

import com.shanke.dubbo.utils.list.IListUtil;

@Table(name = "t_role")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "role")
@XmlType(name = "role", propOrder = { "id", "name", "resume" })
public class Role extends BaseEntity {
	private static final long serialVersionUID = 2228695046704842148L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;// 角色名称

	private String resume;// 角色摘要

	@Transient
	private List<User> users;// 一个角色对应多个用户

	@Transient
	private List<Right> rights;// 一个角色对应多个权限

	public Role() {
	}

	public Role(String name, String resume) {
		this.name = name;
		this.resume = resume;
	}

	/**
	 * 描述 : 获取所有的权限名称. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年7月31日 下午1:27:22
	 * @return
	 */
	@Transient
	public Set<String> getRightNames() {
		Set<String> set = new HashSet<String>();
		List<Right> rights = getRights();
		if (IListUtil.hasData(rights)) {
			for (Right r : rights) {
				set.add(r.getName());
			}
		}
		return set;
	}

	/**
	 * 描述 : 获取所有的权限路径. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年7月31日 下午1:27:22
	 * @return
	 */
	@Transient
	public Set<String> getRightUrls() {
		Set<String> set = new HashSet<String>();
		List<Right> rights = getRights();
		if (IListUtil.hasData(rights)) {
			for (Right r : rights) {
				set.add(r.getUrl());
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((resume == null) ? 0 : resume.hashCode());
		result = prime * result + ((rights == null) ? 0 : rights.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (resume == null) {
			if (other.resume != null)
				return false;
		} else if (!resume.equals(other.resume))
			return false;
		if (rights == null) {
			if (other.rights != null)
				return false;
		} else if (!rights.equals(other.rights))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", resume=" + resume + "]";
	}
}
