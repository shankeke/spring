package com.jusfoun.mapper.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.mybatis.mapper.BaseWithAssociateMapper;
import com.jusfoun.entity.TArea;

@Mapper
public interface TAreaMapper extends BaseWithAssociateMapper<TArea> {

	/**
	 * 描述: 根据ID查询地区及所有子孙节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 上午11:08:54
	 * @param id
	 *            根节点ID
	 * @return 根节点对象
	 */
	TArea selectTreeRoot(Long id);
}