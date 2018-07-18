package com.shanke.common.type;

/**
 * 描述 : 是否类型，0-否，1-是. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月2日 下午8:05:56
 */
public enum YesNoType {
	NO(0, "否"), // 否
	YES(1, "是");// 是

	private final String label;
	private final int value;

	private YesNoType(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public int getValue() {
		return value;
	}

	/**
	 * 描述 : 根据数值获取一个类型. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年8月2日 下午8:10:51
	 * @param value
	 *            数值
	 * @return 类型
	 */
	public YesNoType valueOf(int value) {
		return value == 1 ? YES : NO;
	}
}
