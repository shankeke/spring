package com.jusfoun.security.support.token.parser;

import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.TokenCreateException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.TokenType;

/**
 * 说明： 令牌解析器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:02:30
 */
public interface TokenParser {

	/**
	 * 说明： 创建令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:33:24
	 * @param clientDetails
	 *            客户端信息
	 * @param userDetails
	 *            用户信息
	 * @param type
	 *            令牌类型
	 * @return 令牌实体
	 * @throws TokenCreateException
	 */
	Token createToken(ClientDetails clientDetails, UserDetails userDetails, TokenType type) throws TokenCreateException;

	/**
	 * 说明：令牌解析. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:33:57
	 * @param token
	 *            编码的令牌载体
	 * @param type
	 *            令牌类型
	 * @return 解析后的令牌实体
	 * @throws TokenInvalidException
	 */
	Token parseToken(String token, TokenType type) throws TokenInvalidException;

	/**
	 * 说明： 令牌格式检验. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:34:40
	 * @param token
	 *            编码的令牌载体
	 * @return 检验结果
	 * @throws TokenInvalidException
	 */
	boolean verify(String token) throws TokenInvalidException;

	/**
	 * 说明：令牌有效信息提取器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月16日 下午3:13:05
	 * @param payload
	 *            令牌
	 * @return 有效信息
	 * @throws TokenInvalidException
	 */
	String extract(String payload) throws TokenInvalidException;

}
