package com.jusfoun.common.mybatis.typehandler.blobtype;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明： Blob转Boolean数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class BlobVsBooleanArrayTypeHandler extends AbstractBlobTypeHandler<Boolean[]> {

	@Override
	public String translate2Str(Boolean[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Boolean[] translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).map(Boolean::valueOf).toArray(Boolean[]::new);
	}

}
