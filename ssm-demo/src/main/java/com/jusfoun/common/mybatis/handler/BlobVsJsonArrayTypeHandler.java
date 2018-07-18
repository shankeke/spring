package com.jusfoun.common.mybatis.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 描述 : Blob转JsonArray数组ypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:43:50
 */
public class BlobVsJsonArrayTypeHandler extends AbstractBlobTypeHandler<JSONArray> {

	@Override
	public String translate2Str(JSONArray t) {
		return JSON.toJSONString(t);
	}

	@Override
	public JSONArray translate2Bean(String result) {
		return JSON.parseArray(result);
	}
}
