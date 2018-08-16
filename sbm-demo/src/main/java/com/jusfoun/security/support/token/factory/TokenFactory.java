package com.jusfoun.security.support.token.factory;

import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.TokenException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;

/**
 * 描述: 令牌解析工厂. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年4月8日 下午5:15:25
 */
public interface TokenFactory {

	/**
	 * 描述: 创建访问令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:23:25
	 * @param clientDetails
	 *            客户端信息
	 * @param userDetails
	 *            用户信息
	 * @return 访问令牌
	 * @throws TokenException
	 */
	AccessToken createAccessToken(ClientDetails clientDetails, UserDetails userDetails) throws TokenException;

	/**
	 * 描述:创建刷新令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:23:54
	 * @param clientDetails
	 *            客户端信息
	 * @param userDetails
	 *            用户信息
	 * @return 刷新令牌
	 * @throws TokenException
	 */
	RefreshToken createRefreshToken(ClientDetails clientDetails, UserDetails userDetails) throws TokenException;

	/**
	 * 描述:解析访问令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:24:23
	 * @param token
	 *            编码处理的令牌信息
	 * @return 访问令牌实体
	 * @throws TokenInvalidException
	 */
	AccessToken parseAccessToken(String token) throws TokenInvalidException;

	/**
	 * 描述:解析刷新令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:25:18
	 * @param token
	 *            编码处理的令牌信息
	 * @return 刷新令牌实体
	 * @throws TokenInvalidException
	 */
	RefreshToken parseRefreshToken(String token) throws TokenInvalidException;

}
