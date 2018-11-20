package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明：产地类型枚举. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum OriginType implements ByteValuable {

	OTHER((byte) 0, "其他"), //
	PADDY((byte) 1, "稻田"), //
	AQUATIC_CROPS((byte) 2, "水生作物地"), //
	PROTECTED((byte) 3, "保护地"), //
	OPEN_AIR((byte) 4, "露天");//

	private final Byte value;// 数字值
	private final String label;// 类型名称

	private OriginType(Byte value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Byte getValue() {
		return value;
	}

	public static OriginType valueOf(Byte value) {
		if (value != null) {
			for (OriginType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return OTHER;
	}
}
