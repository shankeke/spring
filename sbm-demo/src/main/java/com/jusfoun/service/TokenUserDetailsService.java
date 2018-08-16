package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseIdableWithAssociateService;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;

/**
 * 描述: 系统用户鉴权信息业务处理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午5:54:20
 */
public interface TokenUserDetailsService extends BaseIdableWithAssociateService<TokenUserDetails> {

	/**
	 * 描述 : 保存并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月11日 下午12:16:17
	 * @param record
	 *            保存对象
	 * @return 缓存对象
	 */
	TokenUserDetails insertAndCache(TokenUserDetails record);

	/**
	 * 描述 :修改并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月11日 下午12:15:47
	 * @param record
	 *            修改对象
	 * @return 缓存对象
	 */
	TokenUserDetails updateAndCache(TokenUserDetails record);

	/**
	 * 描述 : 根据用户名和客户端ID删除数据库记录和缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月11日 下午12:15:18
	 * @param username
	 *            用户名
	 * @param clientId
	 *            客户端ID
	 * @return 删除结果
	 */
	int deleteWithCacheByUsernameAndClientId(String username, String clientId);

	/**
	 * 描述 : 根据用户名和客户端ID查询用户的授权信息并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月9日 上午10:43:23
	 * @param username
	 *            用户名称
	 * @return 用户授权信息
	 */
	TokenUserDetails findAndCacheByUsernameAndClientId(String username, String clientId);

	/**
	 * 描述 :根据AccessToken查找授权信息并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月11日 下午12:13:51
	 * @param token
	 *            AccessToken
	 * @return 缓存的授权信息
	 */
	TokenUserDetails findAndCacheByAccessToken(AccessToken token);

	/**
	 * 描述 :根据RefreshToken查找授权信息并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月11日 下午12:13:51
	 * @param token
	 *            RefreshToken
	 * @return 缓存的授权信息
	 */
	TokenUserDetails findAndCacheByRefreshToken(RefreshToken token);

}
