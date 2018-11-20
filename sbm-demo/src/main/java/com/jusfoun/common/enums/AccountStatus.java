package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：账户状态枚举==> 0-未启用,1-已启用,2-已锁定,3-已禁用. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum AccountStatus implements ByteValuable {

	UNKNOWN((byte) -1, "未知"), //
	NOT_ENABLED((byte) 0, "未启用"), //
	ENABLED((byte) 1, "已启用"), //
	LOCKED((byte) 2, "已锁定"), //
	DISABLED((byte) 3, "已禁用");//

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private AccountStatus(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static AccountStatus valueOf(Byte value) {
		if (value != null) {
			for (AccountStatus type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return UNKNOWN;
	}
}
