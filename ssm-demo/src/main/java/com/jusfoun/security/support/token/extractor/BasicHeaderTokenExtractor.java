package com.jusfoun.security.support.token.extractor;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 从Basic类型的头信息抽取令牌的处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:21
 */
@Component
public class BasicHeaderTokenExtractor implements TokenExtractor {
	/**
	 * 前缀信息
	 */
	public static final String HEADER_PREFIX = "Basic ";

	@Override
	public String extract(String header) throws AuthenticationServiceException {
		if (StringUtils.isBlank(header)) {
			throw new AuthenticationServiceException("Authorization header cannot be blank !");
		}
		if (header.length() < HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("Invalid authorization header size.");
		}
		try {
			if (header.toLowerCase().startsWith(HEADER_PREFIX.toLowerCase())) {
				header = header.substring(HEADER_PREFIX.length(), header.length());
			}
			return new String(Base64.getDecoder().decode(header));
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenInvalidException("Invalid token: " + header);
		}
	}
}
