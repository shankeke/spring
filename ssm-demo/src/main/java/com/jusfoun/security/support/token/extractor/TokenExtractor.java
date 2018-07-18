package com.jusfoun.security.support.token.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 描述 : 令牌信息抽取处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:00:48
 */
public interface TokenExtractor {

	/**
	 * 描述: 从编码字符串的载体中抽取令牌的有效信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:39:29
	 * @param payload
	 *            包含编码令牌信息的请求头
	 * @return 有效的的令牌编码信息
	 * @throws AuthenticationServiceException
	 */
	String extract(String payload) throws AuthenticationServiceException;

}
