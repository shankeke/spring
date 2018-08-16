package com.jusfoun.common.mybatis.typehandler.varchartype;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述 : Varchar转JSONObject TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsJsonObjectTypeHandler extends AbstractVarcharTypeHandler<JSONObject> {

	@Override
	public String translate2Str(JSONObject t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public JSONObject translate2Bean(String result) {
		return JSON.parseObject(result);
	}
}
