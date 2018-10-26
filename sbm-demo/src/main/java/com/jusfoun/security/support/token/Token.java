package com.jusfoun.security.support.token;

import java.io.Serializable;

/**
 * 说明： 令牌信息接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:30:28
 */
public interface Token extends Serializable {

	/**
	 * 说明：获取客户端ID. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午11:31:06
	 * @return client ID
	 */
	String getClientId();

	/**
	 * 说明：获取用户名. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午11:30:47
	 * @return 用户名
	 */
	String getSubject();

	/**
	 * 说明：获取token字串. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午11:31:31
	 * @return token字串
	 */
	String getToken();

}
