package com.jusfoun.client;

import com.alibaba.fastjson.JSONObject;
/**
 * 描述 : 定义一个请求残烛的包装类. <br>
 * @author yjw@jusfoun.com
 * @date 2017年9月21日 下午1:01:59
 */
public class PageVo extends JSONObject {

	private static final long serialVersionUID = 5443732442149660906L;

	public PageVo() {
	}

	public PageVo(Integer pageNum, Integer pageSize) {
		put("pageNum", pageNum);
		put("pageSize", pageSize);
	}
}
