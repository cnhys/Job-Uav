package com.vt.fencing.model.vo;

import java.util.Date;

public class LogPojo {
	private String imagePath;
	private Date startTime;
	private String graph;
	private Integer maxHeightName;
	private Date endTime;
	private Integer routeId;
	private String unitName;
	public LogPojo() {
		super();
	}
	public LogPojo(String imagePath, Date startTime, String graph, Integer maxHeightName, Date endTime, Integer routeId,
			String unitName) {
		super();
		this.imagePath = imagePath;
		this.startTime = startTime;
		this.graph = graph;
		this.maxHeightName = maxHeightName;
		this.endTime = endTime;
		this.routeId = routeId;
		this.unitName = unitName;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public Integer getMaxHeightName() {
		return maxHeightName;
	}
	public void setMaxHeightName(Integer maxHeightName) {
		this.maxHeightName = maxHeightName;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
