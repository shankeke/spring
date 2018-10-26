package com.jusfoun.security.support.token.extract.extractor;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明： 抽象令牌信息抽取处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
public abstract class AbstractTokenExtractor implements TokenExtractor {

	/**
	 * 报文前缀
	 */
	private String prefix;

	public AbstractTokenExtractor() {
	}

	public AbstractTokenExtractor(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean supports(String payload) {
		if (StringUtils.isEmpty(payload)) {
			return false;
		}

		if (StringUtils.isEmpty(prefix)) {
			return true;
		}

		return payload.toLowerCase().startsWith(prefix.toLowerCase());
	}

	public String getPrefix() {
		if (StringUtils.isEmpty(prefix)) {
			prefix = "";
		}
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
