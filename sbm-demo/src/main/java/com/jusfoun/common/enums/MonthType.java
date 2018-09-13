package com.jusfoun.common.enums;

import com.jusfoun.common.mybatis.typehandler.enumtype.IntegerValuable;

/**
 * 描述 : 月份枚举类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月12日 上午9:55:31
 */
public enum MonthType implements IntegerValuable{
	JAN(1, "一月", "January", QuarterType.SPRING), //
	FEB(2, "二月", "February", QuarterType.SPRING), //
	MAR(3, "三月", "March", QuarterType.SPRING), //
	APR(4, "四月", "April", QuarterType.SUMMER), //
	MAY(5, "五月", "May", QuarterType.SUMMER), //
	JUNE(6, "六月", "June", QuarterType.SUMMER), //
	JULY(7, "七月", "July", QuarterType.AUTUMN), //
	AUG(8, "八月", "Aguest", QuarterType.AUTUMN), //
	SEPT(9, "九月", "September", QuarterType.AUTUMN), //
	OCT(10, "十月", "October", QuarterType.WINTER), //
	NOV(11, "十一月", "November", QuarterType.WINTER), //
	DEC(12, "十二月", "December", QuarterType.WINTER);//

	private final Integer value;// 数字值
	private final String label_cn;// 中文名称
	private final String label_en;// 英文名称
	private final QuarterType quarterType;// 季度

	private MonthType(Integer value, String label_cn, String label_en, QuarterType quarterType) {
		this.value = value;
		this.label_cn = label_cn;
		this.label_en = label_en;
		this.quarterType = quarterType;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public String getLabel_cn() {
		return label_cn;
	}

	public String getLabel_en() {
		return label_en;
	}

	public QuarterType getQuarterType() {
		return quarterType;
	}

	public static MonthType valueOf(Integer value) {
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
