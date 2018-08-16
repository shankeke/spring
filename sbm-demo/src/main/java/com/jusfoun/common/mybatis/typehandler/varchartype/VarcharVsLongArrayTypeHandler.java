package com.jusfoun.common.mybatis.typehandler.varchartype;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述 : Varchar转Long数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsLongArrayTypeHandler extends AbstractVarcharTypeHandler<Long[]> {

	@Override
	public String translate2Str(Long[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Long[] translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).map(t -> {
			return Long.valueOf(t);
		}).toArray(Long[]::new);
	}
}
