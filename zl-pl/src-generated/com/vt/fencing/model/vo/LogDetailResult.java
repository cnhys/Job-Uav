package com.vt.fencing.model.vo;

import java.util.List;

public class LogDetailResult {
	private String imagePath;
	private String startTime;
	private Integer maxHeightName;
	private String endTime;
	private Integer routeId;
	private String unitName;
	private String flyTime;
	
	List<UavStatusHisResponse> flyTrails;
	
	
	
	public List<UavStatusHisResponse> getFlyTrails() {
		return flyTrails;
	}
	public void setFlyTrails(List<UavStatusHisResponse> flyTrails) {
		this.flyTrails = flyTrails;
	}
	public String getFlyTime() {
		return flyTime;
	}
	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getMaxHeightName() {
		return maxHeightName;
	}
	public void setMaxHeightName(Integer maxHeightName) {
		this.maxHeightName = maxHeightName;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	
	
}
