package com.jusfoun.test.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.jusfoun.common.utils.net.HttpUtils;

/**
 * 说明： HttpUtils工具类测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月27日 上午8:59:00
 */
public class HttpUtilsTest {

	@Test
	public void test() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("secretkey", "8a8a8a285e9ef1a7015f338e421f0003");
		map.put("pageNum", "1");
		map.put("pageSize", "20");
		map.put("publishTime", "20171018190000-20171025190000");
		map.put("merge", false);
		map.put("queryKeys", "大数据");
		map.put("noQueryKeys", "");
		map.put("mediaTypes", "1");
		map.put("sentiments", "1");
		map.put("sort", "time");
		map.put("province", "");
		map.put("city", "");
		map.put("county", "");
		map.put("crawlTime", false);
		map.put("red", true);
		String httpPostJson = HttpUtils.httpPost("http://yuqingapi.9cfcf.com/manage/api/search.do", map,
				new HashMap<String, String>() {
					private static final long serialVersionUID = -3545770096432453261L;
					{
						put("secretkey", "8a8a8a285e9ef1a7015f338e421f0003");
					}
				});
		System.out.println(httpPostJson);
	}

}
