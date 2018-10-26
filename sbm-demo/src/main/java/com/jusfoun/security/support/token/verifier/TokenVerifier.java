package com.jusfoun.security.support.token.verifier;

import com.jusfoun.security.exceptions.TokenInvalidException;

/**
 * 说明：令牌检验器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:02:38
 */
public interface TokenVerifier {

	/**
	 * 说明：检验令牌格式是否正确. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月12日 下午1:56:05
	 * @param token
	 *            令牌载体
	 * @return 校验结果
	 * @throws TokenInvalidException
	 */
	boolean verify(String token) throws TokenInvalidException;

}
