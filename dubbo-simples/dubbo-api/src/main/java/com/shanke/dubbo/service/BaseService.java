package com.shanke.dubbo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shanke.dubbo.comm.MyMapper;
import com.shanke.dubbo.comm.vo.Page;

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
