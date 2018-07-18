package com.jusfoun.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.util.net.IpUtil;
import com.jusfoun.service.IpService;

/**
 * 描述 : IP地址服务. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月13日 下午1:42:29
 */
@Service("ipService")
public class IpServiceImpl implements IpService {

	@Cacheable(value = CacheConsts.CACHE_TEMP/*, unless = "#result == null"*/)
	@Override
	public String getAreaByIP(String IP) throws ServiceException {
		try {
			return IpUtil.getAreaByIp(IP);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
