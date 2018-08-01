package com.jusfoun.security.support.token.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 描述 : 令牌信息抽取处理器适配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
public interface TokenExtractorAdapter {

	/**
	 * 描述: 从编码字符串的载体中抽取令牌的有效信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:39:29
	 * @param payload
	 *            令牌编码信息
	 * @return 有效的的令牌编码信息
	 * @throws AuthenticationServiceException
	 */
	String excute(String payload) throws TokenInvalidException;

	/**
	 * 描述:添加一个处理器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月1日 上午9:54:04
	 * @param tokenExtractor
	 *            处理器实例
	 */
	void add(TokenExtractor tokenExtractor);

}
