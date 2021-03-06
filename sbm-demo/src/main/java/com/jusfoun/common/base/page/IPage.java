package com.jusfoun.common.base.page;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 分页对象. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:34
 */
@ApiModel
public class IPage<T> extends IPageable {
	private static final long serialVersionUID = -4026620219669217389L;

	/**
	 * 总条数
	 */
	@ApiModelProperty(value = "数据总条数")
	private int totalCount;

	/**
	 * 总页数
	 */
	@ApiModelProperty("数据总页数")
	private int totalPage;

	/**
	 * 当前页数据
	 */
	@ApiModelProperty("当前页数据集合")
	private List<T> list;

	/**
	 * 描述： 空参构造函数,默认会根据默认页码和默认页长构造IPage对象。<br/>
	 */
	public IPage() {
		super(DEFAULT_PAGENUM, DEFAULT_PAGESIZE);
	}

	/**
	 * 描述： 空参构造函数,默认会根据默认页码和默认页长构造IPage对象。<br/>
	 * 
	 * @param orderByClause
	 *            排序
	 */
	public IPage(String orderByClause) {
		super(DEFAULT_PAGENUM, DEFAULT_PAGESIZE, orderByClause);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造IPage对象。<br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 */
	public IPage(int pageNum, int pageSize) {
		super(true, pageNum, pageSize);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造IPage对象。<br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param orderByClause
	 *            排序
	 */
	public IPage(int pageNum, int pageSize, String orderByClause) {
		super(true, pageNum, pageSize, orderByClause);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造IPage对象。<br/>
	 * 
	 * @param pageable
	 *            是否分页，默认true
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 */
	public IPage(boolean pageable, int pageNum, int pageSize) {
		super(pageable, pageNum, pageSize, null);
	}

	/**
	 * 描述： 构造函数,根据页码和页长构造IPage对象。<br/>
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
	public IPage(boolean pageable, int pageNum, int pageSize, String orderByClause) {
		super(pageable, pageNum, pageSize, orderByClause);
	}

	/**
	 * 描述： 构造函数,根据IPageable实例构造IPage对象。<br/>
	 * 
	 * @param pageable
	 *            IPageable实例
	 */
	public IPage(IPageable pageable) {
		super(true, pageable.getPageNum(), pageable.getPageSize(), pageable.getOrderByClause());
	}

	/**
	 * 描述： 构造函数 <br/>
	 * 
	 * @param pageable
	 *            是否分页，默认true
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 * @param list
	 *            当前页数据集合
	 * @param orderByClause
	 *            排序
	 */
	public IPage(boolean pageable, int pageNum, int pageSize, int totalCount, List<T> list, String orderByClause) {
		super(pageable, pageNum, pageSize, orderByClause);
		this.totalCount = totalCount;
		this.totalPage = getTotalPage(pageSize, totalCount);
		setPageNum(pageNum > this.totalPage ? this.totalPage : pageNum);
		getNextPage(pageNum, totalPage);
		getPrevPage(pageNum, totalPage);
		this.list = list;
	}

	/**
	 * 描述：构造函数。<br/>
	 * 
	 * @param pageable
	 *            是否分页，默认true
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 * @param list
	 *            当前页数据集合
	 */
	public IPage(boolean pageable, int pageNum, int pageSize, int totalCount, List<T> list) {
		this(pageable, pageNum, pageSize, totalCount, list, null);
	}

	/**
	 * 描述： 构造函数 <br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 * @param list
	 *            当前页数据集
	 * @param orderByClause
	 *            排序
	 */
	public IPage(int pageNum, int pageSize, int totalCount, List<T> list, String orderByClause) {
		this(true, pageNum, pageSize, totalCount, list, orderByClause);
	}

	/**
	 * 描述： 构造函数 <br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 * @param list
	 *            当前页数据集合
	 */
	public IPage(int pageNum, int pageSize, int totalCount, List<T> list) {
		this(true, pageNum, pageSize, totalCount, list);
	}

	/**
	 * * 描述： 构造函数 <br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 * @param orderByClause
	 *            排序
	 */
	public IPage(int pageNum, int pageSize, int totalCount, String orderByClause) {
		this(true, pageNum, pageSize, totalCount, null, orderByClause);
	}

	/**
	 * 描述： 构造函数 <br/>
	 * 
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 */
	public IPage(int pageNum, int pageSize, int totalCount) {
		this(true, pageNum, pageSize, totalCount, null);
	}

	/**
	 * 描述： 构造函数 <br/>
	 * 
	 * @param pageable
	 *            是否分页，分页-true，不分页-false
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            数据总条数
	 */
	public IPage(boolean pageable, int pageNum, int pageSize, int totalCount) {
		this(pageable, pageNum, pageSize, totalCount, null);
	}

	/**
	 * 描述：构造函数,根据page、totalCount、list构造IPage对象，其中page对象必须含有pageNum,pageSize，其他的属性将会被此构造函数替换
	 * <br/>
	 * 
	 * @param page
	 *            已存在分页
	 * @param totalCount
	 *            总条数
	 * @param list
	 *            当前页数据
	 */
	public IPage(IPage<?> page, int totalCount, List<T> list) {
		this(page.isPageable(), page.getPageNum(), page.getPageSize(), totalCount, list);
	}

	/**
	 * 说明： 是否有下一页，是否当前页码小于总的页码数. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:15:51
	 * @param pageNum
	 *            页码
	 * @param totalPage
	 *            总页码
	 * @return 是否有下一页
	 */
	private boolean getNextPage(int pageNum, int totalPage) {
		return pageNum < getTotalPage();
	}

	/**
	 * 说明： 是否有上一页，是否当前页码大于1. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:16:27
	 * @param pageNum
	 *            页码
	 * @param totalPage
	 *            总页码
	 * @return 是否有上一页
	 */
	private boolean getPrevPage(int pageNum, int totalPage) {
		return pageNum > 1;
	}

	/**
	 * 说明： 根据总数据量和每页数据条数计算总的页数. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:16:58
	 * @param pageSize
	 *            页长
	 * @param totalCount
	 *            总条数
	 * @return 总页数
	 */
	private int getTotalPage(int pageSize, int totalCount) {
		return (int) Math.ceil((double) totalCount / pageSize);
	}

	public int getTotalPage() {
		return getTotalPage(getPageSize(), totalCount);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 说明：是否有下一页. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:22:26
	 * @return 有下一页-true，没有下一页（即当前页是最后一页）-false
	 */
	@ApiModelProperty("是否有下一页")
	public boolean isNextPage() {
		return getNextPage(getPageNum(), getTotalPage());
	}

	/**
	 * 说明：是否有上一页. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:22:26
	 * @return 有上一页-true，没有上一页（即当前页是第一页）-false
	 */
	@ApiModelProperty("是否有上一页")
	public boolean isPrevPage() {
		return getPrevPage(getPageNum(), getTotalPage());
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String initOrderByClause() {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + totalCount;
		result = prime * result + totalPage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPage<?> other = (IPage<?>) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (totalCount != other.totalCount)
			return false;
		if (totalPage != other.totalPage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IPage [totalCount=" + totalCount + ", totalPage=" + totalPage + ", list=" + list + "]";
	}

	/*
	 * public static void main(String[] args) { IPage<TTest> page = new
	 * IPage<TTest>(1, 12); page.setTotalCount(27);
	 * System.out.println(page.getTotalPage()); }
	 */

}
