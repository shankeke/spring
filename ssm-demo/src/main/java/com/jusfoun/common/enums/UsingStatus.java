package com.jusfoun.common.enums;

import com.jusfoun.common.mybatis.typehandler.enumtype.IntegerValueEnum;

/**
 * 描述 : 启用状态==>0-未启用、1-启用、-1-停用. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:30:48
 */
public enum UsingStatus implements IntegerValueEnum {
	NotEnabled(0, "未启用"), //
	Enable(1, "启用"), //
	Disable(-1, "停用"); //

	private final String label;// 类型名称
	private final Integer value;// 类型值

	private UsingStatus(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public Integer getValue() {
		return value;
	}

}
