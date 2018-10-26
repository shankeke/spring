package com.jusfoun.common.mybatis.typehandler.varchartype;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明： Varchar转Integer数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsIntegerArrayTypeHandler extends AbstractVarcharTypeHandler<Integer[]> {

	@Override
	public String translate2Str(Integer[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Integer[] translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).map(Integer::valueOf).toArray(Integer[]::new);
	}
}
