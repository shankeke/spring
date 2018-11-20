package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：大棚类型枚举. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum GreenhouseType implements ByteValuable {

	OTHER((byte) 0, "其他"), //
	VEGETABLE_SHED((byte) 1, "菜棚"), //
	ARCHED_SHED((byte) 2, "拱棚"), //
	SMALL_ARCHED_SHED((byte) 3, "小拱棚"); //

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private GreenhouseType(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static GreenhouseType valueOf(Byte value) {
		if (value != null) {
			for (GreenhouseType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return OTHER;
	}
}
