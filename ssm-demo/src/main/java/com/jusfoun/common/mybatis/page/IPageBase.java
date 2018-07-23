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
public class IPageBase implements Serializable {
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
	@ApiModelProperty("是否分页，默认true")
	private boolean pageable = true;

	/**
	 * 页码
	 */
	@ApiModelProperty("页码")
	private int pageNum;

	/**
	 * 页长
	 */
	@ApiModelProperty("页长")
	private int pageSize;

	public IPageBase() {
	}

	public IPageBase(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public IPageBase(boolean pageable, int pageNum, int pageSize) {
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
	 * @param page
	 *            分页信息
	 * @return mybatis分页边界对象
	 */
	public static RowBounds getRowBounds(IPageBase page) {
		return new RowBounds((page.getPageNum() - 1) * page.getPageSize(), page.getPageSize());
	}
}
