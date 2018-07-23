package com.jusfoun.common.mybatis.typehandler.blobtype;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述 : Blob转字符串TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class BlobVsStringSetTypeHandler extends AbstractBlobTypeHandler<Set<String>> {

	@Override
	public String translate2Str(Set<String> t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public Set<String> translate2Bean(String result) {
		return Arrays.stream(result.split(DEFAULT_REGEX)).collect(Collectors.toSet());
	}
}
