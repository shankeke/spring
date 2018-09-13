package com.jusfoun.common.enums;

import com.jusfoun.common.mybatis.typehandler.enumtype.IntegerValuable;

/**
 * 描述 : 项目执行阶段枚举类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月12日 上午9:55:31
 */
public enum QuarterType implements IntegerValuable {
	SPRING(1, "第一季度"), // 春季
	SUMMER(2, "第二季度"), // 夏季
	AUTUMN(3, "第三季度"), // 秋季
	WINTER(4, "第四季度"); // 冬季

	private final String label;// 类型名称
	private final Integer value;// 数字值

	private QuarterType(Integer value, String label) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	/**
	 * 描述 : 根据ID获取名称. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月16日 上午9:59:03
	 * @param value
	 *            ID
	 * @return 名称
	 */
	public static String getLabelByValue(Integer value) {
		QuarterType type = valueOf(value);
		if (type != null) {
			return type.label;
		}
		return null;
	}

	/**
	 * 描述 :根据ID值获取类型. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月16日 上午9:58:12
	 * @param value
	 *            ID
	 * @return 枚举类型
	 */
	public static QuarterType valueOf(Integer value) {
		if (value != null) {
			for (QuarterType type : values()) {
				if (type.equalsTo(value)) {
					return type;
				}
			}
		}
		return null;
	}
}
