package com.jusfoun.json;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {
	@Test
	public void test() {
		String text = "{\r\n" + "	\"list\": [\r\n" + "		{\r\n" + "			\"points\": [\r\n"
				+ "				{\r\n" + "					\"count\": 0,\r\n"
				+ "					\"time\": \"20160721\"\r\n" + "				},\r\n" + "				{\r\n"
				+ "					\"count\": 1,\r\n" + "					\"time\": \"20160722\"\r\n"
				+ "				},\r\n" + "				{\r\n" + "					\"count\": 2,\r\n"
				+ "					\"time\": \"20160723\"\r\n" + "				}\r\n" + "			]\r\n"
				+ "		},\r\n" + "		{\r\n" + "			\"points\": [\r\n" + "				{\r\n"
				+ "					\"count\": 0,\r\n" + "		s			\"time\": \"20160821\"\r\n"
				+ "				},\r\n" + "				{\r\n" + "					\"count\": 1,\r\n"
				+ "					\"time\": \"20160822\"\r\n" + "				},\r\n" + "				{\r\n"
				+ "					\"count\": 2,\r\n" + "					\"time\": \"20160823\"\r\n"
				+ "				}\r\n" + "			]\r\n" + "		}\r\n" + "	],\r\n" + "	\"useTime\": 0\r\n"
				+ "}\r\n" + "";
		JSONObject json = JSON.parseObject(text);
		JSONArray jsonArray = json.getJSONArray("list");
		if (jsonArray != null && jsonArray.size() > 0) {
			JSONObject obj = null;
			JSONArray json1 = null;
			for (Object o : jsonArray) {
				obj = (JSONObject) o;
				json1 = obj.getJSONArray("points");
				if (json1 != null) {

				}
			}
		}
	}
}
