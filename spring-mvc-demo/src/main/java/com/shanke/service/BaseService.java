package com.shanke.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shanke.common.MyMapper;
import com.shanke.common.vo.Page;

@Service
public interface BaseService<T> extends MyMapper<T> {

	/**
	 * 描述 : 分页查询. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-10-26 下午4:52:12
	 * @param s
	 * @param page
	 * @return
	 */
	List<T> selectAll(T s, Page page);
}
