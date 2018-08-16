package com.jusfoun.service;

import com.jusfoun.common.message.exception.ServiceException;

/**
 * 描述 :IP地址服务. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月13日 下午1:41:08
 */
public interface IpService {

	/**
	 * 描述 :根据IP地址查询所属地区. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月13日 下午1:41:02
	 * @param IP
	 *            IP地址
	 * @return
	 * @throws ServiceException
	 */
	String getAreaByIP(String IP) throws ServiceException;

}
