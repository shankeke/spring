package com.jusfoun.common.mybatis.handler;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述: Varchar转Double数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsDoubleArrayTypeHandler extends AbstractVarcharTypeHandler<Double[]> {

	@Override
	public String translate2Str(Double[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Double[] translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).map(t -> {
			return Double.valueOf(t);
		}).toArray(Double[]::new);
	}
}
