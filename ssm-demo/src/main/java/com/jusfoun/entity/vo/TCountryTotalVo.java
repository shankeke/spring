package com.jusfoun.entity.vo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.utils.ICollections;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:国家信息汇总包装模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午10:50:03
 */
@ApiModel(description = "国家信息汇总包装模型")
public class TCountryTotalVo implements Serializable {
	private static final long serialVersionUID = -5130630927261598236L;

	@ApiModelProperty("国家信息模型集合")
	private List<TCountryVo> list;

	@ApiModelProperty("数据总行数")
	private int rowCount;

	public TCountryTotalVo() {
	}

	public TCountryTotalVo(List<TCountryVo> list) {
		this.list = list;
	}

	public List<TCountryVo> getList() {
		if (!ICollections.hasElements(list)) {
			list = Lists.newArrayList();
		} else {
			list = list.stream()//
					.sorted(Comparator.comparing(TCountryVo::getAlpha))//
					.collect(Collectors.toList());
		}
		return list;
	}

	public void setList(List<TCountryVo> list) {
		this.list = list;
	}

	public int getRowCount() {
		if (ICollections.hasElements(list)) {
			rowCount = list.stream()//
					.mapToInt(t -> t.getRowCount())//
					.sum();
		}
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}
