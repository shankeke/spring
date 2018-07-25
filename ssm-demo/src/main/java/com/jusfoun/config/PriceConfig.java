package com.jusfoun.config;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 : 价格数据地址配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月19日 下午5:35:42
 */
@Configuration
@ConfigurationProperties(prefix = PriceConfig.PREFIX)
public class PriceConfig {
	public static final String PREFIX = "system.price";

	private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyyMMdd";

	private String marketUrl;// 市场数据地址
	private String eCommerceUrl;// 电商数据地址
	private String forecastUrl;// 价格预测地址
	private int amHour;// 上午时间
	private int pmHour;// 下午时间

	public String getMarketUrl(DateTime dateTime, AmPmType type) {
		if (StringUtils.isNotEmpty(marketUrl)) {
			if (dateTime == null) {
				dateTime = DateTime.now();
			}
			String format = dateTime.toString(DEFAULT_DATE_FORMAT_PATTERN);
			switch (type) {
				case PM :
					return String.format(marketUrl, format, pmHour);
				default :
					return String.format(marketUrl, format, amHour);
			}
		}
		return marketUrl;
	}

	public String geteCommerceUrl(DateTime dateTime, AmPmType type) {
		if (StringUtils.isNotEmpty(eCommerceUrl)) {
			if (dateTime == null) {
				dateTime = DateTime.now();
			}
			String format = dateTime.toString(DEFAULT_DATE_FORMAT_PATTERN);
			switch (type) {
				case PM :
					return String.format(eCommerceUrl, format, pmHour);
				default :
					return String.format(eCommerceUrl, format, amHour);
			}
		}
		return eCommerceUrl;
	}

	public String getForecastUrl(DateTime dateTime, AmPmType type) {
		if (StringUtils.isNotEmpty(forecastUrl)) {
			if (dateTime == null) {
				dateTime = DateTime.now();
			}
			String format = dateTime.plusDays(1).toString(DEFAULT_DATE_FORMAT_PATTERN);
			switch (type) {
				case PM :
					return String.format(forecastUrl, format, pmHour);
				default :
					return String.format(forecastUrl, format, amHour);
			}
		}
		return forecastUrl;
	}

	public int getAmHour() {
		return amHour;
	}

	public int getPmHour() {
		return pmHour;
	}

	public void setMarketUrl(String marketUrl) {
		this.marketUrl = marketUrl;
	}

	public void seteCommerceUrl(String eCommerceUrl) {
		this.eCommerceUrl = eCommerceUrl;
	}

	public void setForecastUrl(String forecastUrl) {
		this.forecastUrl = forecastUrl;
	}

	public void setAmHour(int amHour) {
		this.amHour = amHour;
	}

	public void setPmHour(int pmHour) {
		this.pmHour = pmHour;
	}

	public enum AmPmType {
		AM("am"), PM("pm");
		private final String value;

		private AmPmType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}
}
