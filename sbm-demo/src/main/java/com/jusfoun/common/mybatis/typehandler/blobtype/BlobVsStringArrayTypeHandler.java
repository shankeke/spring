package com.jusfoun.common.mybatis.typehandler.blobtype;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述 : Blob转String数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class BlobVsStringArrayTypeHandler extends AbstractBlobTypeHandler<String[]> {

	@Override
	public String translate2Str(String[] t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public String[] translate2Bean(String result) {
		return result.split(DEFAULT_REGEX);
	}
}
