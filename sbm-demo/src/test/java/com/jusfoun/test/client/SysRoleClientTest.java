package com.jusfoun.test.client;

import java.util.HashSet;
import java.util.Set;

import com.jusfoun.entity.SysPrivs;
import com.jusfoun.entity.SysRole;

/**
 * 说明：用户角色管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午4:48:56
 */
public class SysRoleClientTest extends BaseClient<SysRole> {

	@Override
	public void save() {
		t.setName("管理1");
		t.setRemark("生产管理");

		Set<SysPrivs> list = new HashSet<SysPrivs>();
		SysPrivs t1 = null;
		for (long i = 1; i <= 6; i++) {
			t1 = new SysPrivs();
			t1.setId(i);
			list.add(t1);
		}
		t.setSysPrivss(list);
		json("/sysrole/save", t);
	}

	@Override
	public void updateById() {
		t.setId(2L);
		Set<SysPrivs> list = new HashSet<SysPrivs>();
		SysPrivs t1 = null;
		for (long i = 7; i <= 12; i++) {
			t1 = new SysPrivs();
			t1.setId(i);
			list.add(t1);
		}
		t.setSysPrivss(list);
		json("/sysrole/update", t);
	}

	@Override
	public void deleteById() {
		t.setId(1L);
		get("/sysrole/deleteById", t);
	}

	@Override
	public void list() {
		t.setPageable(true);
		t.setPageNum(2);
		t.setPageSize(10);
		json("/sysrole/list", t);
	}

	@Override
	public void listPage() {
		t.setPageable(true);
		t.setPageNum(2);
		t.setPageSize(10);
		json("/sysrole/listPage", t);
	}

	@Override
	public void infoById() {
		t.setId(11L);
		form("/sysrole/infoById", t);
	}

}
