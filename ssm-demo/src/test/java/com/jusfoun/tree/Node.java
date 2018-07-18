package com.jusfoun.tree;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Node {

	private String name;

	@JSONField(serialize = false)
	private Integer id;

	@JSONField(serialize = false)
	private Integer pid;

	@JSONField(serialize = false)
	private boolean isLast = false;

	@JsonInclude(value = Include.NON_EMPTY)
	public List<Node> subs = new ArrayList<Node>();

	public Node(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getSubs() {
		return subs;
	}

	public void setSubs(List<Node> subs) {
		this.subs = subs;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
