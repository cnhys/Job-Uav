package com.vt.fencing.model.vo;

import java.util.Date;

public class ShenFeiResult {
	private String iamgePath;
	private String brandName;
	private String uavType;
	private String unitName;
	private Date startTime;
	private Date endTime;
	private int maxHeightName;
	private String statusName;
	
	public ShenFeiResult() {
		super();
	}
	public ShenFeiResult(String iamgePath, String brandName, String uavType, String unitName, Date startTime,
			Date endTime, int maxHeightName, String statusName) {
		super();
		this.iamgePath = iamgePath;
		this.brandName = brandName;
		this.uavType = uavType;
		this.unitName = unitName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxHeightName = maxHeightName;
		this.statusName = statusName;
	}



	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getMaxHeightName() {
		return maxHeightName;
	}
	public void setMaxHeightName(int maxHeightName) {
		this.maxHeightName = maxHeightName;
	}
	public String getIamgePath() {
		return iamgePath;
	}

	public void setIamgePath(String iamgePath) {
		this.iamgePath = iamgePath;
	}
	public String getUavType() {
		return uavType;
	}

	public void setUavType(String uavType) {
		this.uavType = uavType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}
