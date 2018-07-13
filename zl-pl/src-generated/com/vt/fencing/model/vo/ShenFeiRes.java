package com.vt.fencing.model.vo;

public class ShenFeiRes {
	private String iamgePath;
	private String brandName;
	private String uavType;
	private String unitName;
	private String startTime;
	private int maxHeightName;
	private String statusName;
	private String flyTime;
	
	public ShenFeiRes() {
		super();
	}
	public ShenFeiRes(String iamgePath, String brandName, String uavType, String unitName, String startTime,
			int maxHeightName, String statusName, String flyTime) {
		super();
		this.iamgePath = iamgePath;
		this.brandName = brandName;
		this.uavType = uavType;
		this.unitName = unitName;
		this.startTime = startTime;
		this.maxHeightName = maxHeightName;
		this.statusName = statusName;
		this.flyTime = flyTime;
	}



	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getStartTime() {
		return startTime;
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
	public String getFlyTime() {
		return flyTime;
	}
	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}
