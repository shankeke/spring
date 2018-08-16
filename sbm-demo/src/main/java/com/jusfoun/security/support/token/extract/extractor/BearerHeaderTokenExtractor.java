package com.jusfoun.security.support.token.extract.extractor;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 从Bearer类型的头信息抽取令牌的处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:21
 */
public class BearerHeaderTokenExtractor extends AbstractTokenExtractor {

	public BearerHeaderTokenExtractor() {
		super("Bearer ");
	}

	@Override
	public String extract(String payload) throws TokenInvalidException {
		String prefix = getPrefix();
		if (payload.length() <= prefix.length()) {
			throw new TokenInvalidException(String.format("Invalid token '%s' that the length is too short, do not match the prefix '%s'! ", payload, prefix));
		}
		return payload.substring(getPrefix().length(), payload.length());
	}

}
