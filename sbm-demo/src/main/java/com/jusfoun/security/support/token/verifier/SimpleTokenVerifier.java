package com.jusfoun.security.support.token.verifier;

import org.apache.commons.lang3.StringUtils;

import com.jusfoun.common.utils.RegexUtils;
import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 说明： 简单令牌检验器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:02:15
 */
public class SimpleTokenVerifier implements TokenVerifier {
	/**
	 * 匹配规则
	 */
	private static final String TOKEN_DEF_REGEX = "[0-9a-zA-Z-_.\\s]{6,}";

	@Override
	public boolean verify(String tokenPayload) throws TokenInvalidException {
		if (StringUtils.isEmpty(tokenPayload) || !RegexUtils.check(TOKEN_DEF_REGEX, tokenPayload)) {
			throw new TokenInvalidException(String.format("Invalid token '%s' !", tokenPayload));
		}
		return true;
	}
}
