package com.jusfoun.security.support.token.extractor;

import java.util.Base64;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 从Basic类型的头信息抽取令牌的处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:21
 */
public class BasicHeaderTokenExtractor extends AbstractTokenExtractor {

	public BasicHeaderTokenExtractor() {
		super("Basic ");
	}

	@Override
	public String extract(String payload) throws TokenInvalidException {
		String prefix = getPrefix();
		if (payload.length() <= prefix.length()) {
			throw new TokenInvalidException(String.format("Invalid token '%s' that the length is too short, do not match the prefix '%s'! ", payload, prefix));
		}
		try {
			return new String(Base64.getDecoder().decode(payload.substring(prefix.length(), payload.length())));
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenInvalidException(String.format("Invalid token '%s'!", payload), e);
		}
	}
}
