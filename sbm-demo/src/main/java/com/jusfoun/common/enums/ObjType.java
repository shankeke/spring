package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：投入品类型枚举. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum ObjType implements ByteValuable {

	OTHER((byte) 0, "其他"), //
	SEEDLING((byte) 1, "种苗"), //
	FERTILIZER((byte) 2, "肥料"), //
	PESTICIDES((byte) 3, "农药"); //

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private ObjType(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static ObjType valueOf(Byte value) {
		if (value != null) {
			for (ObjType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return OTHER;
	}
}
