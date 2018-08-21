package com.jusfoun.client;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.entity.SysRole;
import com.jusfoun.entity.SysUser;

/**
 * 描述 : 系统用户信息管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午1:45:58
 */
public class SysUserClientTest extends BaseClient<SysUser> {

	@Override
	public void save() {
		t.setUsername("wangwu");
		t.setRealName("王五");
		t.setPassword("123456");
		t.setStatus(UsingStatus.ENABLE.getValue());
		t.setGender(2);
		t.setMobile("13460398985");
		t.setEmail("156@163.com");
		t.setGovId(3L);
		t.setRemark("他是王五，不是李四");
		json("/sysuser/save", t);
	}

	@Override
	public void updateById() {
		t.setId(3L);
		t.setStatus(UsingStatus.DISABLE.getValue());
		json("/sysuser/updateById", t);
	}

	@Override
	public void deleteById() {
		t.setId(3L);
		json("/sysuser/deleteById", t);
	}

	@Override
	public void list() {
		t.setPageable(true);
		t.setPageNum(2);
		t.setPageSize(10);
		json("/sysuser/list", t);
	}

	@Override
	public void listPage() {
		t.setPageable(true);
		t.setPageNum(2);
		t.setPageSize(10);
		json("/sysuser/listPage", t);
	}

	@Override
	public void infoById() {
		t.setId(2L);
		json("/sysuser/infoById", t);
	}

	@Test
	public void resetPass() {
		t.setId(2L);
		json("/sysuser/resetPass", t);
	}

	@Test
	public void modifyPass() {
		// t.setId(2);
		JSONObject params = new JSONObject();
		params.put("oldPassword", "admin");
		params.put("password", "admin");
		json("/sysuser/modifyPass", params);
	}

	@Test
	public void modifyRoles() {
		Set<SysRole> list = new HashSet<SysRole>();
		list.add(new SysRole(2L));
		list.add(new SysRole(3L));
		list.add(new SysRole(4L));
		list.add(new SysRole(5L));
		t.setId(2L);
		t.setSysRoles(list);
		json("/sysuser/modifyRoles", t);
	}

}
