package com.jusfoun.config;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jusfoun.config.PriceConfig.AmPmType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceConfigTest {

	@Autowired
	private PriceConfig priceConfig;

	@Test
	public void test() {
		System.out.println(priceConfig.getMarketUrl(DateTime.now(), AmPmType.AM));
		System.out.println(priceConfig.geteCommerceUrl(DateTime.now(), AmPmType.AM));
	}
}
