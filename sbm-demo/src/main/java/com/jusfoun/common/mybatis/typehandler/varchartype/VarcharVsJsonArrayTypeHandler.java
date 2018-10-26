package com.jusfoun.common.mybatis.typehandler.varchartype;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 说明： Varchar转JSONArray TypeHandler. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月10日 下午12:44:32
 */
public class VarcharVsJsonArrayTypeHandler extends AbstractVarcharTypeHandler<JSONArray> {

	@Override
	public String translate2Str(JSONArray t) {
		return StringUtils.join(t, DEFAULT_REGEX);
	}

	@Override
	public JSONArray translate2Bean(String result) {
		return JSON.parseArray(result);
	}
}
