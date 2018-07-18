package com.jusfoun.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.security.exceptions.ClientIdNotFoundException;
import com.jusfoun.service.TokenClientDetailsService;

/**
 * 描述 : 实现客户端信息加载方法. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午8:58:36
 */
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private TokenClientDetailsService tokenClientDetailsService;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientIdNotFoundException {
		if (StringUtils.isEmpty(clientId)) {
			throw new ClientIdNotFoundException("ClientId cound not null or empty !");
		}
		TokenClientDetails clientDetails = tokenClientDetailsService.findAndCacheByClientId(clientId);
		if (clientDetails == null) {
			throw new ClientIdNotFoundException("ClientId not found !");
		}
		return clientDetails;
	}

}
