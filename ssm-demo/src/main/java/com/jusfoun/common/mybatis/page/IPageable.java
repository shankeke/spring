package com.jusfoun.common.mybatis.page;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 : 分页参数包装. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:34
 */
@ApiModel
public class IPageable implements Serializable {
	private static final long serialVersionUID = -4026620219669217389L;

	/**
	 * 默认页码
	 */
	public static final int DEFAULT_PAGENUM = 1;

	/**
	 * 默认页长
	 */
	public static final int DEFAULT_PAGESIZE = 10;

	/**
	 * 是否分页，默认分页
	 */
	@ApiModelProperty(value = "是否分页：分页-true，不分页-false，默认true", allowableValues = "true,false")
	private boolean pageable = true;

	/**
	 * 页码
	 */
	@ApiModelProperty(value = "页码，默认1")
	private int pageNum;

	/**
	 * 页长
	 */
	@ApiModelProperty(value = "页长，默认10")
	private int pageSize;

	public IPageable() {
		this(DEFAULT_PAGENUM, DEFAULT_PAGESIZE);
	}

	public IPageable(int pageNum, int pageSize) {
		this(true, pageNum, pageSize);
	}

	public IPageable(boolean pageable, int pageNum, int pageSize) {
		this.pageable = pageable;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public boolean isPageable() {
		return pageable;
	}

	public void setPageable(boolean pageable) {
		this.pageable = pageable;
	}

	public int getPageNum() {
		return pageNum <= 0 ? DEFAULT_PAGENUM : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize <= 0 ? DEFAULT_PAGESIZE : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 描述: 根据一个page对象获取生成一个mybatis的分页边界对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:24:40
	 * @param pageable
	 *            分页信息
	 * @return mybatis分页边界对象
	 */
	public static RowBounds getRowBounds(IPageable pageable) {
		return new RowBounds((pageable.getPageNum() - 1) * pageable.getPageSize(), pageable.getPageSize());
	}
}
