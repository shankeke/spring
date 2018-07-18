package com.jusfoun.security.support.token.verifier;

import org.apache.commons.lang3.StringUtils;

import com.jusfoun.common.util.regex.RegexUtil;
import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 简单令牌检验器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:02:15
 */
public class SimpleTokenVerifier implements TokenVerifier {
	/**
	 * 匹配规则
	 */
	private static final String TOKEN_DEF_REGEX = "[0-9a-zA-Z-_.\\s]{32,}";

	@Override
	public boolean verify(String tokenPayload) throws TokenInvalidException {
		if (StringUtils.isEmpty(tokenPayload) || !RegexUtil.check(TOKEN_DEF_REGEX, tokenPayload)) {
			throw new TokenInvalidException("Invalid token:" + tokenPayload);
		}
		return true;
	}
}
