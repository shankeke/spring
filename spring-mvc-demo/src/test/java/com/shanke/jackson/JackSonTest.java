package com.shanke.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackSonTest {
	public static void main(String[] args) {

		// writeJsonObject();

		// readJsonObject();

		// readJsonMap();
	}

	// 直接写入一个对象
	public static void writeJsonObject() {
		ObjectMapper mapper = new ObjectMapper();
		Person person = new Person("nomouse", 25);
		try {
			mapper.writeValue(new File("D:/person.json"), person);
			// mapper.writeValue(System.out, person);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 直接将一个json转化为对象
	public static void readJsonObject() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			Person person = mapper.readValue(new File("D:/person.json"),
					Person.class);
			System.out.println(person.getName() + ":" + person.getAge());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 直接转化为map
	@SuppressWarnings("rawtypes")
	public static void readJsonMap() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			// 需要注意的是这里的Map实际为一个LikedHashMap，即链式哈希表，可以按照读入顺序遍历
			Map map = mapper.readValue(new File("D:/person.json"), Map.class);
			System.out.println(map.get("name") + ":" + map.get("age"));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
