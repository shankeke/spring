package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseWithAssociateService;
import com.jusfoun.entity.TokenClientDetails;

/**
 * 描述 : 客户端信息业务处理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月30日 下午4:08:10
 */
public interface TokenClientDetailsService extends BaseWithAssociateService<TokenClientDetails> {

	/**
	 * 描述 :保存并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月30日 下午4:08:24
	 * @param record
	 *            保存对象
	 * @return 缓存对象
	 */
	TokenClientDetails insertAndCache(TokenClientDetails record);

	/**
	 * 描述 :替换并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月30日 下午4:08:24
	 * @param record
	 *            替换对象
	 * @return 缓存对象
	 */
	TokenClientDetails replaceAndCache(TokenClientDetails record);

	/**
	 * 描述 : 删除数据库记录和缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月30日 下午4:08:36
	 * @param record
	 *            删除对象
	 * @return 删除结果
	 */
	int deleteWithCache(TokenClientDetails record);

	/**
	 * 描述 : 修改并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月30日 下午4:08:48
	 * @param record
	 *            修改对象
	 * @return 缓存对象
	 */
	TokenClientDetails updateAndCache(TokenClientDetails record);

	/**
	 * 描述 : 根据clientI查询并缓存. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月30日 下午4:09:28
	 * @param clientId
	 *            客户端ID
	 * @return 缓存对象
	 */
	TokenClientDetails findAndCacheByClientId(String clientId);

}
