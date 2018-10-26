package com.jusfoun.common.mybatis.typehandler.varchartype;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明： Varchar转Short数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsShortArrayTypeHandler extends AbstractVarcharTypeHandler<Short[]> {

	@Override
	public String translate2Str(Short[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Short[] translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).map(Short::valueOf).toArray(Short[]::new);
	}
}
