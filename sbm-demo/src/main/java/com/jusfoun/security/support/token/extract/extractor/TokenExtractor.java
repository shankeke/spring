package com.jusfoun.security.support.token.extract.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 说明： 令牌信息抽取处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
public interface TokenExtractor {

	/**
	 * 说明： 从编码字符串的载体中抽取令牌的有效信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:39:29
	 * @param payload
	 *            令牌编码信息
	 * @return 有效的的令牌编码信息
	 * @throws AuthenticationServiceException
	 */
	String extract(String payload) throws TokenInvalidException;

	/**
	 * 说明：是否支持. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月1日 上午9:15:18
	 * @param payload
	 *            令牌编码信息
	 * @return 是否支持改类型解析
	 */
	boolean supports(String payload);

}
