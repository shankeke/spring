package com.jusfoun.client;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.jusfoun.entity.SysGov;

/**
 * 描述 : 组织机构管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午11:00:47
 */
public class SysGovClientTest extends BaseClient<SysGov> {

	@Override
	public void save() {
		/*
		 * t.setParentId(null); t.setFullName("河蟹集团");
		 * t.setRemark("主要负责河蟹生产、加工、销售");
		 */
		/* t.setParentId(1); t.setFullName("生产部"); t.setRemark("主要负责河蟹生产"); */
		/* t.setParentId(1); t.setFullName("加工部"); t.setRemark("主要负责河蟹加工"); */
		t.setParentId(0L);
		t.setFullName("柔道散打部");
		t.setShortName("散打部");
		t.setRemark("主要负责河蟹销售");
		json("/sysgov/save", t);
	}

	@Override
	public void updateById() {
		t.setId(1L);
		t.setAddress("河北省保定市");
		json("/sysgov/updateById", t);
	}

	@Test
	public void updateListById() {
		List<SysGov> list = Lists.newArrayList();
		t.setId(1L);
		t.setAddress("河北省保定市");
		list.add(t);
		json("/sysgov/updateListById", list);
	}

	@Override
	public void deleteById() {
		t.setId(1L);
		json("/sysgov/deleteById", t);
	}

	@Override
	public void list() {
		t.setParentId(0L);
		json("/sysgov/list", t);
	}

	@Override
	public void listPage() {
		t.setParentId(0L);
		json("/sysgov/listPage", t);
	}

	@Override
	public void infoById() {
		t.setId(1L);
		json("/sysgov/infoById", t);
	}

}
