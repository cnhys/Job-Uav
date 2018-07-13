package com.vt.fencing.model;

import java.util.Date;

public class AaRouteLog {
	private Integer routeId;
	private Integer uavId;
	private Integer userId;
	private Date startTime;
	private Date endTime;
	private String startGps;
	private String endGps;
	private Integer maxHeightId;
	private Integer routeTypeId;
	private Integer approvalStatus;
	private String routeDetail;
	private Integer status;
	private Date addTime;
	private String recordFilePath;
	private Integer unitId;
	private String areaTypeCode;
	private String graph;
	private String routeUavUniqueId;
	private Integer AlertLevel;
	
	private AaUav uav;
	private AaHeightScopeDict heightScopeDict;
	
	private String flyerName;
	
	
	
	
	public String getFlyerName() {
		return flyerName;
	}
	public void setFlyerName(String flyerName) {
		this.flyerName = flyerName;
	}
	public AaUav getUav() {
		return uav;
	}
	public void setUav(AaUav uav) {
		this.uav = uav;
	}
	public AaHeightScopeDict getHeightScopeDict() {
		return heightScopeDict;
	}
	public void setHeightScopeDict(AaHeightScopeDict heightScopeDict) {
		this.heightScopeDict = heightScopeDict;
	}
	
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public Integer getUavId() {
		return uavId;
	}
	public void setUavId(Integer uavId) {
		this.uavId = uavId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getStartGps() {
		return startGps;
	}
	public void setStartGps(String startGps) {
		this.startGps = startGps;
	}
	public String getEndGps() {
		return endGps;
	}
	public void setEndGps(String endGps) {
		this.endGps = endGps;
	}
	public Integer getMaxHeightId() {
		return maxHeightId;
	}
	public void setMaxHeightId(Integer maxHeightId) {
		this.maxHeightId = maxHeightId;
	}
	public Integer getRouteTypeId() {
		return routeTypeId;
	}
	public void setRouteTypeId(Integer routeTypeId) {
		this.routeTypeId = routeTypeId;
	}
	public Integer getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getRecordFilePath() {
		return recordFilePath;
	}
	public void setRecordFilePath(String recordFilePath) {
		this.recordFilePath = recordFilePath;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getAreaTypeCode() {
		return areaTypeCode;
	}
	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public String getRouteUavUniqueId() {
		return routeUavUniqueId;
	}
	public void setRouteUavUniqueId(String routeUavUniqueId) {
		this.routeUavUniqueId = routeUavUniqueId;
	}
	public Integer getAlertLevel() {
		return AlertLevel;
	}
	public void setAlertLevel(Integer alertLevel) {
		AlertLevel = alertLevel;
	}
	
	
}
