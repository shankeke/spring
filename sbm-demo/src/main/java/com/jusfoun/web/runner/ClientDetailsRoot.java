package com.jusfoun.web.runner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.jusfoun.entity.TokenClientDetails;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "clientDetailss")
@XmlType(name = "ClientDetailsRoot", propOrder = {"list"})
public class ClientDetailsRoot {

	// @XmlElementWrapper(name = "elements")
	@XmlElement(name = "clientDetails")
	private List<TokenClientDetails> list;

	public List<TokenClientDetails> getList() {
		return list;
	}

	public void setList(List<TokenClientDetails> list) {
		this.list = list;
	}

	/**
	 * 描述 : 初始化系统客户端信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月6日 下午5:13:11
	 */
	@Deprecated
	public static ClientDetailsRoot initClients() {

		List<TokenClientDetails> list = new ArrayList<>();

		// 初始化app客户端信息
		String clientId = ClientType.APP_CLIENT.getClientId();
		TokenClientDetails t = new TokenClientDetails();
		t.setClientId(clientId);
		t.setClientSecret("123456");
		t.setAccessTokenValidity(3600 * 24 * 10);
		t.setRefreshTokenValidity(3600 * 24 * 10);
		t.setGrantTypes(new String[]{"password", "refresh_token"});
		t.setResourceIds(new String[]{clientId});
		t.setScopes(new String[]{"read", "write", "trust"});
		list.add(t);

		// 初始化web客户端信息
		clientId = ClientType.WEB_CLIENT.getClientId();
		t = new TokenClientDetails();
		t.setClientId(clientId);
		t.setClientSecret("123456");
		t.setAccessTokenValidity(7200);
		t.setRefreshTokenValidity(7200);
		t.setGrantTypes(new String[]{"password", "refresh_token"});
		t.setResourceIds(new String[]{clientId});
		t.setScopes(new String[]{"read", "write", "trust"});
		list.add(t);

		// 初始化bigdata客户端信息
		clientId = ClientType.BIGDATA_CLIENT.getClientId();
		t = new TokenClientDetails();
		t.setClientId(clientId);
		t.setClientSecret("123456");
		t.setAccessTokenValidity(3600 * 4);
		t.setRefreshTokenValidity(3600 * 4);
		t.setGrantTypes(new String[]{"password", "refresh_token"});
		t.setResourceIds(new String[]{clientId});
		t.setScopes(new String[]{"read", "write", "trust"});
		list.add(t);

		ClientDetailsRoot root = new ClientDetailsRoot();
		root.setList(list);
		return root;
	}

	/*
	 * public static void main(String[] args) { ClientDetailsRoot root =
	 * initClients(); JaxbUtil.java2xml(new File("ClientDetails.xml"), root,
	 * ClientDetailsRoot.class, true, false); }
	 */
}
