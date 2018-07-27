package com.jusfoun.entity.vo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.beust.jcommander.internal.Lists;
import com.jusfoun.common.utils.list.IListUtil;
import com.jusfoun.entity.TCountry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述: 国家信息包装模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午10:50:03
 */
@ApiModel(description = "国家信息包装模型")
public class TCountryVo implements Serializable {
	private static final long serialVersionUID = -2884160987483196325L;

	@ApiModelProperty("首字母")
	private String alpha;

	@ApiModelProperty("国家信息集合")
	private List<TCountry> list;

	@ApiModelProperty("数据总行数")
	private int rowCount;

	public TCountryVo() {
	}

	public TCountryVo(String alpha, List<TCountry> list) {
		this.alpha = alpha;
		this.list = list;
	}

	public List<TCountry> getList() {
		if (!IListUtil.hasData(list)) {
			list = Lists.newArrayList();
		} else {
			list = list.stream()//
					.sorted(Comparator.comparing(TCountry::getZhName))//
					.collect(Collectors.toList());
		}
		return list;
	}

	public void setList(List<TCountry> list) {
		this.list = list;
	}

	public int getRowCount() {
		if (IListUtil.hasData(list)) {
			rowCount = list.size();
		}
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

}
