package com.jusfoun.security;

import com.jusfoun.security.exceptions.ClientIdNotFoundException;

/**
 * 说明： 客户端信息加载. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月1日 上午11:38:57
 */
public interface ClientDetailsService {

	/**
	 * 说明： 根据客户端ID查询客户端信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月5日 下午3:27:39
	 * @param clientId
	 *            客户端ID
	 * @return 客户端信息
	 * @throws ClientIdNotFoundException
	 */
	ClientDetails loadClientByClientId(String clientId) throws ClientIdNotFoundException;

}
