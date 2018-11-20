package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：设备类型枚举. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum EquipmentType implements ByteValuable {

	OTHER((byte) 0, "其他"), //
	SEEDLING((byte) 1, "录像机"), //
	FERTILIZER((byte) 2, "传感器"); //

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private EquipmentType(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static EquipmentType valueOf(Byte value) {
		if (value != null) {
			for (EquipmentType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return OTHER;
	}
}
