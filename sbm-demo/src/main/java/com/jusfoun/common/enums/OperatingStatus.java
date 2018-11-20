package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：经营状态枚举类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年11月14日 上午9:58:30
 */
public enum OperatingStatus implements ByteValuable {

	NORMAL((byte) 0, "正常"), //
	SHUTDOWN((byte) 1, "停业"), //
	CLOSED((byte) 2, "关闭"), //
	TO_BUILD((byte) 3, "筹建"), //
	OTHER((byte) 4, "其他");//

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private OperatingStatus(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static OperatingStatus valueOf(Byte value) {
		if (value != null) {
			for (OperatingStatus type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return OTHER;
	}
}
