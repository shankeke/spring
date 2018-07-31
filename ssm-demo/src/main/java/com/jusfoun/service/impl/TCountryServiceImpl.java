package com.jusfoun.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extend.BaseWithAssociateSelectMapper;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.entity.TCountry;
import com.jusfoun.entity.vo.TCountryTotalVo;
import com.jusfoun.entity.vo.TCountryVo;
import com.jusfoun.mapper.ds1.TCountryMapper;
import com.jusfoun.service.TCountryService;

/**
 * 描述: 国家信息维护业务接口实现. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:47:08
 */
@Service
public class TCountryServiceImpl implements TCountryService {

	@Autowired
	private TCountryMapper tCountryMapper;

	@Override
	public BaseWithAssociateSelectMapper<TCountry> getBaseWithAssociateSelectMapper() {
		return tCountryMapper;
	}

	@Override
	public MyIdableMapper<TCountry> getMyIdableMapper() {
		return tCountryMapper;
	}

	@Override
	public MyBaseMapper<TCountry> getMyBaseMapper() {
		return tCountryMapper;
	}

	@Override
	public TCountryTotalVo selectCounties() throws ServiceException {
		List<TCountry> list = selectAll();
		TCountryTotalVo total = new TCountryTotalVo();
		if (ICollections.hasData(list)) {
			Map<String, List<TCountry>> alphaGroup = list.stream().collect(Collectors.groupingBy(TCountry::getAlpha));
			List<TCountryVo> tCountryVos = total.getList();
			alphaGroup.forEach((k, v) -> {
				tCountryVos.add(new TCountryVo(k, v));
			});
		}
		return total;
	}

}
