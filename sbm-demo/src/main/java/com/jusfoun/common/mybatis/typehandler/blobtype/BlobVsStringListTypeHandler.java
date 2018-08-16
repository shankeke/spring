package com.jusfoun.common.mybatis.typehandler.blobtype;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述 : 自定义TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class BlobVsStringListTypeHandler extends AbstractBlobTypeHandler<List<String>> {

	@Override
	public String translate2Str(List<String> t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public List<String> translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).collect(Collectors.toList());
	}
}
