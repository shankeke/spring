package com.jusfoun.common.mybatis.typehandler.blobtype;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述 : Blob转JsonObject数组TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:43:50
 */
public class BlobVsJsonObjectTypeHandler extends AbstractBlobTypeHandler<JSONObject> {

	@Override
	public String translate2Str(JSONObject t) {
		return JSON.toJSONString(t);
	}

	@Override
	public JSONObject translate2Bean(String result) {
		return JSON.parseObject(result);
	}
}
