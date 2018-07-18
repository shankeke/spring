package com.shanke.dubbo.comm.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public class Page implements Serializable {

	private static final long serialVersionUID = -7151350515884198850L;

	public static final int DEF_PAGENUM = 1;

	public static final int DEF_PAGESIZE = 10;

	private int pageNum;

	private int pageSize;

	private int totalCount;

	private int totalPage;

	private List<?> list;

	// 是否有下一页
	private boolean nextPage;

	// 是否有上一页
	private boolean prePage;

	public Page() {
		super();
	}

	public Page(int pageNum, int pageSize) {
		super();
		if (pageNum <= 0)
			pageNum = DEF_PAGENUM;
		if (pageSize <= 0)
			pageSize = DEF_PAGESIZE;

		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public Page(int pageNum, int pageSize, int totalCount) {
		if (pageNum <= 0)
			pageNum = DEF_PAGENUM;
		if (pageSize <= 0)
			pageSize = DEF_PAGESIZE;

		this.pageSize = pageSize;
		this.totalCount = totalCount;

		this.totalPage = getTotalPage(pageSize, totalCount);
		if (pageNum > this.totalPage)
			this.pageNum = this.totalPage;
		else
			this.pageNum = pageNum;

		this.nextPage = getNextPage(pageNum, totalPage);
		this.prePage = getPrePage(pageNum, totalPage);
	}

	public Page(int pageSize, int totalCount, List<?> list) {
		if (pageSize <= 0)
			pageSize = 10;

		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = getTotalPage(pageSize, totalCount);
		this.list = list;
	}

	public Page(int pageNum, int pageSize, int totalCount, int totalPage,
			List<?> list) {
		super();
		if (pageNum <= 0)
			pageNum = DEF_PAGENUM;

		if (pageSize <= 0)
			pageSize = DEF_PAGESIZE;

		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.list = list;
	}

	// 构造函数,根据currentPage、pageSize、totalCount、list构造IPage对象
	public Page(int pageNum, int pageSize, int totalCount, List<?> list) {
		if (pageSize <= 0)
			pageSize = 10;

		if (pageNum <= 0)
			pageNum = 1;

		if (totalCount <= 0)
			totalCount = 0;

		this.totalPage = getTotalPage(pageSize, totalCount);
		if (pageNum > totalPage)
			pageNum = totalPage;

		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.nextPage = getNextPage(pageNum, totalPage);
		this.prePage = getPrePage(pageNum, totalPage);
		this.list = list;
	}

	public Page(Page page, int totalCount, List<?> list) {
		int pageNum = page.getPageNum();
		int pageSize = page.getPageSize();
		if (pageSize <= 0)
			pageSize = DEF_PAGESIZE;
		if (pageNum < 0)
			pageNum = DEF_PAGENUM;

		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = getTotalPage(pageSize, totalCount);
		this.nextPage = getNextPage(pageNum, totalPage);
		this.prePage = getPrePage(pageNum, totalPage);
		this.list = list;
	}

	private boolean getNextPage(int currentPage, int totalPage) {
		boolean nextPage = false;
		if (currentPage < totalPage) {
			nextPage = true;
		}
		return nextPage;
	}

	private boolean getPrePage(int currentPage, int totalPage) {
		boolean prePage = false;
		if (pageNum > 1) {
			prePage = true;
		}
		return prePage;
	}

	private int getTotalPage(int pageSize, int totalCount) {
		int totalPage = 0;
		if (totalCount % pageSize != 0) {
			totalPage = (totalCount / pageSize) + 1;
		} else {
			totalPage = (totalCount / pageSize);
		}
		return totalPage;
	}

	public RowBounds getRowBounds() {
		if (pageNum <= 0)
			pageNum = DEF_PAGENUM;
		if (pageSize <= 0)
			pageSize = DEF_PAGESIZE;
		return new RowBounds((pageNum - 1) * pageSize, pageSize);
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public boolean isNextPage() {
		return nextPage;
	}

	public void setNextPage(boolean nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isPrePage() {
		return prePage;
	}

	public void setPrePage(boolean prePage) {
		this.prePage = prePage;
	}
}
