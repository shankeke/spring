package com.shanke.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackSonDemo {
	public static void main(String[] args) {
		// writeJsonObject();
		// readJsonObject();
	}

	// 直接写入一个对象(所谓序列化)
	@Test
	public static void writeJsonObject() {
		ObjectMapper mapper = new ObjectMapper();
		Person person = new Person("nomouse", 25, true, new Date(), "程序员", 2500.0);
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

	// 直接将一个json转化为对象（所谓反序列化）
	@Test
	public static void readJsonObject() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Person person = mapper.readValue(new File("D:/person.json"), Person.class);
			System.out.println(person.toString());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
