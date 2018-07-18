package com.jusfoun.common.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 : 分页对象. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:34
 */
@ApiModel
public class IPage<T> implements Serializable {
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

	/**
	 * 总条数
	 */
	@ApiModelProperty("总条数")
	private int totalCount;

	/**
	 * 总页数
	 */
	@ApiModelProperty("总页数")
	private int totalPage;

	/**
	 * 当前页数据
	 */
	@ApiModelProperty("当前页数据")
	private List<T> list;

	/**
	 * 排序条件，多个条件用“,”分开，如：id asc,name desc（字段名称取数据库中字段名而非实体属性名）
	 */
	@ApiModelProperty(value = "排序条件，多个条件用“,”分开，如：id asc,name desc（字段名称取数据库中字段名而非实体属性名）", hidden = true)
	@JsonIgnore
	@JSONField(serialize = false)
	private String orderByClause;

	/**
	 * 描述： 空参构造函数,默认会根据默认页码和默认页长构造IPage对象。<br/>
	 */
	public IPage() {
		this(DEFAULT_PAGENUM, DEFAULT_PAGESIZE);
	}

	/**
	 * 描述： 空参构造函数,默认会根据默认页码和默认页长构造IPage对象。<br/>
	 * 
	 * @param orderByClause
	 *            排序
	 */
	public IPage(String orderByClause) {
		this(DEFAULT_PAGENUM, DEFAULT_PAGESIZE, orderByClause);
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
		this(true, pageNum, pageSize, orderByClause);
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
		if (pageable) {
			if (pageSize <= 0) {
				pageSize = DEFAULT_PAGESIZE;
			}
		} else {
			pageSize = Integer.MAX_VALUE;
		}

		if (pageNum < 0) {
			pageNum = DEFAULT_PAGENUM;
		}

		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.orderByClause = orderByClause;
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
		this(pageable, pageNum, pageSize, null);
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
		this(true, pageNum, pageSize);
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
		this(pageable, pageNum, pageSize, orderByClause);
		this.totalCount = totalCount;
		this.totalPage = getTotalPage(pageSize, totalCount);
		if (pageNum > this.totalPage) {
			this.pageNum = this.totalPage;
		} else {
			this.pageNum = pageNum;
		}
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
	 * 描述: 是否有下一页，是否当前页码小于总的页码数. <br>
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
	 * 描述: 是否有上一页，是否当前页码大于1. <br>
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
	 * 描述: 根据总数据量和每页数据条数计算总的页数. <br>
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
		int totalPage = 0;
		pageSize = pageSize <= 0 ? DEFAULT_PAGESIZE : pageSize;
		if (totalCount % pageSize != 0) {
			totalPage = (totalCount / pageSize) + 1;
		} else {
			totalPage = (totalCount / pageSize);
		}
		return totalPage;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return getTotalPage(pageSize, totalCount);
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 描述:是否有下一页. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:22:26
	 * @return 有下一页-true，没有下一页（即当前页是最后一页）-false
	 */
	@ApiModelProperty("是否有下一页")
	public boolean isNextPage() {
		return getNextPage(pageNum, getTotalPage());
	}

	/**
	 * 描述:是否有上一页. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:22:26
	 * @return 有上一页-true，没有上一页（即当前页是第一页）-false
	 */
	@ApiModelProperty("是否有上一页")
	public boolean isPrevPage() {
		return getPrevPage(pageNum, getTotalPage());
	}

	public boolean isPageable() {
		return pageable;
	}

	public void setPageable(boolean pageable) {
		this.pageable = pageable;
	}

	/**
	 * 描述: 根据一个json对象获取分页信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 上午10:27:02
	 * @param json
	 *            json对象
	 * @return 分页信息
	 */
	public static <T> IPage<T> getPage(JSONObject json) {
		Integer pageNum = json.getInteger("pageNum");
		Integer pageSize = json.getInteger("pageSize");
		if (null == json || (null == pageNum && null == pageSize)) {
			return new IPage<T>(DEFAULT_PAGENUM, Integer.MAX_VALUE);
		}
		return new IPage<T>(pageNum, pageSize);
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
	public static RowBounds getRowBounds(IPage<?> page) {
		return new RowBounds((page.getPageNum() - 1) * page.getPageSize(), page.getPageSize());
	}
}
