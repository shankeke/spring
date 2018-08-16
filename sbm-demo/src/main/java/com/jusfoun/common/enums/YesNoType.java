package com.jusfoun.common.enums;

import com.jusfoun.common.mybatis.typehandler.enumtype.IntegerValueEnum;

/**
 * 描述 :真假枚举==> 0-否,1-是. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 下午4:18:34
 */
public enum YesNoType implements IntegerValueEnum {
	NO(0, "否", false), //
	YES(1, "是", true); //

	private final Integer value;// 数字值
	private final String label;// 类型名称
	private final Boolean result;// 是否为真

	private YesNoType(Integer value, String label, boolean result) {
		this.value = value;
		this.label = label;
		this.result = result;
	}

	public String getLabel() {
		return label;
	}

	public Integer getValue() {
		return value;
	}

	public Boolean getResult() {
		return result;
	}

	public static YesNoType valueOf(Integer value) {
		switch (value) {
			case 1 :
				return YesNoType.YES;
			default :
				return YesNoType.NO;
		}
	}

	public static YesNoType valueOf(boolean result) {
		return result ? YesNoType.YES : YesNoType.NO;
	}

}
