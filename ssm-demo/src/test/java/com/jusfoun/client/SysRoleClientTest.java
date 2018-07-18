package com.jusfoun.client;

import java.util.HashSet;
import java.util.Set;

import com.jusfoun.entity.SysModule;
import com.jusfoun.entity.SysRole;

/**
 * 描述 :用户角色管理接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午4:48:56
 */
public class SysRoleClientTest extends BaseClient<SysRole> {

	@Override
	public void save() {
		t.setName("管理1");
		t.setRemark("生产管理");

		Set<SysModule> list = new HashSet<SysModule>();
		SysModule t1 = null;
		for (long i = 1; i <= 6; i++) {
			t1 = new SysModule();
			t1.setId(i);
			list.add(t1);
		}
		t.setSysModules(list);
		excute("/sysrole/save", t);
	}

	@Override
	public void update() {
		t.setId(2L);
		Set<SysModule> list = new HashSet<SysModule>();
		SysModule t1 = null;
		for (long i = 7; i <= 12; i++) {
			t1 = new SysModule();
			t1.setId(i);
			list.add(t1);
		}
		t.setSysModules(list);
		excute("/sysrole/update", t);
	}

	@Override
	public void delete() {
		t.setId(1L);
		excute("/sysrole/delete", t);
	}

	@Override
	public void list() {
		PageVo pageVo = new PageVo(1, 10);
		excute("/sysrole/list", pageVo);
	}

	@Override
	public void info() {
		t.setId(11L);
		excute("/sysrole/info", t);
	}

}
