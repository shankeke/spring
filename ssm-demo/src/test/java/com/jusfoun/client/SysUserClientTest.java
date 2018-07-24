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
		t.setStatus(UsingStatus.Enable.getValue());
		t.setGender(2);
		t.setMobile("13460398985");
		t.setEmail("156@163.com");
		t.setGovId(3L);
		t.setRemark("他是王五，不是李四");
		rest("/sysuser/save", t);
	}

	@Override
	public void update() {
		t.setId(3L);
		t.setStatus(UsingStatus.Disable.getValue());
		rest("/sysuser/update", t);
	}

	@Override
	public void delete() {
		t.setId(3L);
		rest("/sysuser/delete", t);
	}

	@Override
	public void list() {
		PageVo pageVo = new PageVo(1, 50);
		// pageVo.put("govId", 3);
		rest("/sysuser/list", pageVo);
	}

	@Override
	public void info() {
		t.setId(2L);
		rest("/sysuser/info", t);
	}

	@Test
	public void resetPass() {
		t.setId(2L);
		rest("/sysuser/resetPass", t);
	}

	@Test
	public void modifyPass() {
		// t.setId(2);
		JSONObject params = new JSONObject();
		params.put("oldPassword", "admin");
		params.put("password", "admin");
		rest("/sysuser/modifyPass", params);
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
		rest("/sysuser/modifyRoles", t);
	}

}
