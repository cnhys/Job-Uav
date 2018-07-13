package com.vt.fencing.model;

public class AaHeightScopeDict {
	
	private Integer maxHeightId;
	private Integer maxHeightName;
	private String maxHeightDesc;
	public AaHeightScopeDict() {
		super();
	}
	public AaHeightScopeDict(Integer maxHeightId, Integer maxHeightName, String maxHeightDesc) {
		super();
		this.maxHeightId = maxHeightId;
		this.maxHeightName = maxHeightName;
		this.maxHeightDesc = maxHeightDesc;
	}
	public Integer getMaxHeightId() {
		return maxHeightId;
	}
	public void setMaxHeightId(Integer maxHeightId) {
		this.maxHeightId = maxHeightId;
	}
	public Integer getMaxHeightName() {
		return maxHeightName;
	}
	public void setMaxHeightName(Integer maxHeightName) {
		this.maxHeightName = maxHeightName;
	}
	public String getMaxHeightDesc() {
		return maxHeightDesc;
	}
	public void setMaxHeightDesc(String maxHeightDesc) {
		this.maxHeightDesc = maxHeightDesc;
	}
	
}
