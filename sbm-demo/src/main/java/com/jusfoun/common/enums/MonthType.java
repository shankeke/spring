package com.jusfoun.common.enums;

import com.jusfoun.common.enums.valuable.ByteValuable;

/**
 * 说明： 月份枚举类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月12日 上午9:55:31
 */
public enum MonthType implements ByteValuable {

	JAN((byte) 1, "一月", "January", QuarterType.SPRING), //
	FEB((byte) 2, "二月", "February", QuarterType.SPRING), //
	MAR((byte) 3, "三月", "March", QuarterType.SPRING), //
	APR((byte) 4, "四月", "April", QuarterType.SUMMER), //
	MAY((byte) 5, "五月", "May", QuarterType.SUMMER), //
	JUNE((byte) 6, "六月", "June", QuarterType.SUMMER), //
	JULY((byte) 7, "七月", "July", QuarterType.AUTUMN), //
	AUG((byte) 8, "八月", "Aguest", QuarterType.AUTUMN), //
	SEPT((byte) 9, "九月", "September", QuarterType.AUTUMN), //
	OCT((byte) 10, "十月", "October", QuarterType.WINTER), //
	NOV((byte) 11, "十一月", "November", QuarterType.WINTER), //
	DEC((byte) 12, "十二月", "December", QuarterType.WINTER);//

	private final Byte value;// 数字值
	private final String label;// 中文名称
	private final String label_en;// 英文名称
	private final QuarterType quarterType;// 季度

	private MonthType(Byte value, String label, String label_en, QuarterType quarterType) {
		this.value = value;
		this.label = label;
		this.label_en = label_en;
		this.quarterType = quarterType;
	}

	@Override
	public Byte getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public String getLabel_en() {
		return label_en;
	}

	public QuarterType getQuarterType() {
		return quarterType;
	}

	public static MonthType valueOf(Byte value) {
		if (value != null) {
			for (MonthType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return null;
	}
}
