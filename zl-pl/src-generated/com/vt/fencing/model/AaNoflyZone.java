package com.vt.fencing.model;

import java.util.Date;

public class AaNoflyZone {
	private Integer zoneId;
	private Integer unitId;
	private String zoneDetail;
	private Date startTime;
	private Date endTime;
	private Integer enabled;
	private Integer areaType;
	private String graph;
	private Integer zoneLevel;
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getZoneDetail() {
		return zoneDetail;
	}
	public void setZoneDetail(String zoneDetail) {
		this.zoneDetail = zoneDetail;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public Integer getZoneLevel() {
		return zoneLevel;
	}
	public void setZoneLevel(Integer zoneLevel) {
		this.zoneLevel = zoneLevel;
	}
	
}
