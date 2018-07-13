package com.vt.fencing.user.util;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// 当前页数
	private int page;
	// 每页显示多少条数据
	private int size;
	// 总记录数
	private int totalCount;
	// 总页数
	private int totalPage;
	// 每次查询的起始位置
	private int start;
	// 是否有上一页
	private boolean hasPrev;
	// 上一页
	private int prevPage;
	// 是否有下一页
	private boolean hasNext;
	// 下一页
	private int nextPage;
	// 首页
	private int firstPage;
	// 尾页
	private int lastPage;
	// 分页导航块的个数
	private int navCount;
	// 导航的起始位置
	private int navBegin;
	// 导航的结束位置
	private int navEnd;
	// 查询结果对应的list集合
	private List<T> list;

	public Pagination(String page, String size, int totalCount) {
		// 当前页数
		this.page = null != page ? Integer.parseInt(page) : 1;
		// 每页显示多少条数据
		this.size = null != size ? Integer.parseInt(size) : 10;
		// 总记录数
		this.totalCount = totalCount;
		// 总页数
		this.totalPage = (int) Math.ceil(this.totalCount * 1.0 / this.size);
		// 当前页数不能大于总页数
		if(this.page > this.totalPage && this.totalPage > 0) {
			this.page = this.totalPage;
		}
		// 每次查询的起始位置
		this.start = (this.page - 1) * this.size;
		if (this.page > 1) {
			// 是否有上一页
			this.hasPrev = true;
			// 上一页
			this.prevPage = this.page - 1;
		}
		if (this.page < this.totalPage) {
			// 是否有下一页
			this.hasNext = true;
			// 下一页
			this.nextPage = this.page + 1;
		}
		// 首页
		this.firstPage = 1;
		// 尾页
		this.lastPage = this.totalPage;
		// 分页导航块的个数
		this.navCount = 10;
		// 导航的起始位置
		// if (this.page > this.totalPage - this.navCount) {
			// this.navBegin = this.totalPage - this.navCount + 1;
		// } else {
		this.navBegin = this.page - this.navCount / 2 < 1 ? 1 : this.page - this.navCount / 2;
		// }
		// 导航的结束位置
		this.navEnd = this.navBegin + this.navCount - 1 > this.totalPage ? this.totalPage
				: this.navBegin + this.navCount - 1;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public boolean isHasPrev() {
		return hasPrev;
	}

	public void setHasPrev(boolean hasPrev) {
		this.hasPrev = hasPrev;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getNavCount() {
		return navCount;
	}

	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}

	public int getNavBegin() {
		return navBegin;
	}

	public void setNavBegin(int navBegin) {
		this.navBegin = navBegin;
	}

	public int getNavEnd() {
		return navEnd;
	}

	public void setNavEnd(int navEnd) {
		this.navEnd = navEnd;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
