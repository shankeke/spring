package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseIdableWithAssociateService;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.entity.TCountry;
import com.jusfoun.entity.vo.TCountryTotalVo;

/**
 * 描述: 国家信息维护业务接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:46:08
 */
public interface TCountryService extends BaseIdableWithAssociateService<TCountry> {

	/**
	 * 描述:查询国家列表并根据首字母分组>. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午6:37:36
	 * @return 国家信息数据列表
	 * @throws ServiceException
	 */
	TCountryTotalVo selectCounties() throws ServiceException;

}
