package com.jusfoun.common.base.page;

import java.io.Serializable;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 : 分页参数包装. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:34
 */
@ApiModel
public abstract class IPageable implements Serializable {
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
	@Transient
	private Boolean pageable = true;

	/**
	 * 页码
	 */
	@ApiModelProperty(value = "页码，默认1")
	@Transient
	private Integer pageNum;

	/**
	 * 页长
	 */
	@ApiModelProperty(value = "页长，默认10")
	@Transient
	private Integer pageSize;

	/**
	 * 排序条件，多个条件用“,”分开，如：id asc,name desc（字段名称取数据库中字段名而非实体属性名）
	 */
	@ApiModelProperty(value = "排序条件，多个条件用“,”分开，如：id asc,name desc（字段名称取数据库中字段名而非实体属性名）", hidden = true)
	@JsonIgnore
	@JSONField(serialize = false)
	@Transient
	private String orderByClause;

	/**
	 * 描述： 空参构造函数，默认会根据默认页码和默认页长构造分页对象。<br/>
	 */
	public IPageable() {
		this(DEFAULT_PAGENUM, DEFAULT_PAGESIZE);
	}

	/**
	 * 描述： 构造函数，根据页码和页长构造分页对象。<br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 */
	public IPageable(int pageNum, int pageSize) {
		this(true, pageNum, pageSize);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造分页对象。<br/>
	 * 
	 * @param pageable
	 *            是否分页，默认true
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 */
	public IPageable(boolean pageable, int pageNum, int pageSize) {
		this(pageable, pageNum, pageSize, null);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造分页对象。<br/>
	 * 
	 * @param pageable
	 *            是否分页，默认true
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param orderByClause
	 *            排序
	 */
	public IPageable(boolean pageable, int pageNum, int pageSize, String orderByClause) {
		if (pageable) {
			if (pageSize <= 0) {
				pageSize = DEFAULT_PAGESIZE;
			} else {
				this.pageSize = pageSize;
			}
		} else {
			pageSize = Integer.MAX_VALUE;
		}

		if (pageNum < 0) {
			pageNum = DEFAULT_PAGENUM;
		} else {
			this.pageNum = pageNum;
		}
		this.orderByClause = orderByClause;
	}

	/**
	 * 描述：构造函数， 根据排序信息构造分页对象。<br/>
	 * 
	 * @param orderByClause
	 *            排序
	 */
	public IPageable(String orderByClause) {
		this(DEFAULT_PAGENUM, DEFAULT_PAGESIZE, orderByClause);
	}

	/**
	 * 描述： 构造函数，根据页码和页长构造分页对象。<br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param orderByClause
	 *            排序
	 */
	public IPageable(int pageNum, int pageSize, String orderByClause) {
		this(true, pageNum, pageSize, orderByClause);
	}

	public Boolean isPageable() {
		return pageable;
	}

	public void setPageable(Boolean pageable) {
		this.pageable = pageable;
	}

	public Integer getPageNum() {
		return pageNum <= 0 ? DEFAULT_PAGENUM : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize <= 0 ? DEFAULT_PAGESIZE : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByClause() {
		if (StringUtils.isEmpty(orderByClause)) {
			orderByClause = initOrderByClause();
		}
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * 描述: 获取默认的排序字段信息，继承该类需要实现该方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月25日 下午2:14:18
	 * @return 默认的排序字段信息
	 */
	public abstract String initOrderByClause();

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderByClause == null) ? 0 : orderByClause.hashCode());
		result = prime * result + ((pageNum == null) ? 0 : pageNum.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
		result = prime * result + ((pageable == null) ? 0 : pageable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPageable other = (IPageable) obj;
		if (orderByClause == null) {
			if (other.orderByClause != null)
				return false;
		} else if (!orderByClause.equals(other.orderByClause))
			return false;
		if (pageNum == null) {
			if (other.pageNum != null)
				return false;
		} else if (!pageNum.equals(other.pageNum))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		if (pageable == null) {
			if (other.pageable != null)
				return false;
		} else if (!pageable.equals(other.pageable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IPageable [pageable=" + pageable + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderByClause=" + orderByClause + "]";
	}
}
