package com.jusfoun.client;

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
		t.setParentId(2L);
		t.setFullName("散打部");
		t.setRemark("主要负责河蟹销售");
		excute("/sysgov/save", t);
	}

	@Override
	public void update() {
		t.setId(1L);
		excute("/sysgov/update", t);
	}

	@Override
	public void delete() {
		t.setId(1L);
		excute("/sysgov/delete", t);
	}

	@Override
	public void list() {
		t.setParentId(0L);
		excute("/sysgov/list", t);
	}

	@Override
	public void info() {
		t.setId(1L);
		excute("/sysgov/info", t);
	}

}
