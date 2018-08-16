package com.jusfoun.security.support.token.parser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.ClientException;
import com.jusfoun.security.exceptions.TokenCreateException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.TokenType;
import com.jusfoun.security.support.token.extract.adapter.TokenExtractAdapter;
import com.jusfoun.security.support.token.verifier.TokenVerifier;

/**
 * 描述 : 抽象令牌解析器，模板模式公共部分逻辑，子类去实现具体的解析逻辑. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:02:30
 */
public abstract class AbstractTokenParser implements TokenParser {
	/**
	 * 默认令牌属性拼接符
	 */
	public static final String DEFAULT_DELIMITER = ":";

	/**
	 * 令牌有效信息抽取器
	 */
	private final TokenExtractAdapter tokenExtractAdapter;

	/**
	 * 令牌检验器
	 */
	private final TokenVerifier tokenVerifier;

	public AbstractTokenParser(TokenExtractAdapter tokenExtractAdapter, TokenVerifier tokenVerifier) {
		this.tokenExtractAdapter = tokenExtractAdapter;
		this.tokenVerifier = tokenVerifier;
	}

	@Override
	public Token createToken(ClientDetails clientDetails, UserDetails userDetails, TokenType type) throws TokenCreateException {
		if (StringUtils.isEmpty(clientDetails.getClientId())) {
			throw new ClientException("Invalid clientId: property value 'clientId' is empty !");
		}
		if (StringUtils.isEmpty(userDetails.getUsername())) {
			throw new ClientException("Invalid subject: property value 'subject' is empty !");
		}
		return create(clientDetails, userDetails, type);
	}

	/**
	 * 描述:创建令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:36:19
	 * @param clientDetails
	 *            客户端信息
	 * @param userDetails
	 *            用户信息
	 * @param type
	 *            令牌类型
	 * @return 令牌实体
	 */
	protected abstract Token create(ClientDetails clientDetails, UserDetails userDetails, TokenType type);

	@Override
	public Token parseToken(String token, TokenType type) throws TokenInvalidException {
		if (verify(token)) {
			return parse(extract(token), type);
		}
		throw new TokenInvalidException(String.format("Invalid Token: %s", token), token);
	}

	/**
	 * 描述:解析令牌. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月9日 上午9:36:59
	 * @param token
	 *            编码的令牌载体
	 * @param type
	 *            令牌类型
	 * @return 解析后的令牌实体
	 * @throws TokenInvalidException
	 */
	protected abstract Token parse(String token, TokenType type) throws TokenInvalidException;

	@Override
	public boolean verify(String token) throws TokenInvalidException {
		if (tokenVerifier == null) {
			return true;
		}
		return tokenVerifier.verify(token);
	}

	@Override
	public String extract(String payload) throws TokenInvalidException {
		return tokenExtractAdapter.handle(payload);
	}

}
