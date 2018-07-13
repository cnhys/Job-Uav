package com.vt.fencing.model.vo;

import java.io.Serializable;

public class PagingParameters implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page;
	private Integer rows;
	private Integer userId;
	public PagingParameters() {
		super();
	}
	public PagingParameters(Integer page, Integer rows, Integer userId) {
		super();
		this.page = page;
		this.rows = rows;
		this.userId = userId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
