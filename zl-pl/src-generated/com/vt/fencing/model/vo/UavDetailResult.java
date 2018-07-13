package com.vt.fencing.model.vo;

import java.util.Date;

public class UavDetailResult {
	private String uavType;
	private int uavAxleDistance;
	private int uavPropellerLength;
	private int workingLoadId;
	private Date madeDate;
	private String uavUniqueId;
	private String uavApplicatton;
	
	private String imagePath;
	private String statusName;
	private String uavStructure;
	private String uavName;
	private String brandName;
	private String workingLoadDesc;
	
	
	public String getWorkingLoadDesc() {
		return workingLoadDesc;
	}

	public void setWorkingLoadDesc(String workingLoadDesc) {
		this.workingLoadDesc = workingLoadDesc;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUavStructure() {
		return uavStructure;
	}

	public void setUavStructure(String uavStructure) {
		this.uavStructure = uavStructure;
	}

	public String getUavName() {
		return uavName;
	}

	public void setUavName(String uavName) {
		this.uavName = uavName;
	}

	public String getUavType() {
		return uavType;
	}

	public void setUavType(String uavType) {
		this.uavType = uavType;
	}

	public int getUavAxleDistance() {
		return uavAxleDistance;
	}

	public void setUavAxleDistance(int uavAxleDistance) {
		this.uavAxleDistance = uavAxleDistance;
	}

	public int getUavPropellerLength() {
		return uavPropellerLength;
	}

	public void setUavPropellerLength(int uavPropellerLength) {
		this.uavPropellerLength = uavPropellerLength;
	}

	public int getWorkingLoadId() {
		return workingLoadId;
	}

	public void setWorkingLoadId(int workingLoadId) {
		this.workingLoadId = workingLoadId;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getUavUniqueId() {
		return uavUniqueId;
	}

	public void setUavUniqueId(String uavUniqueId) {
		this.uavUniqueId = uavUniqueId;
	}

	public String getUavApplicatton() {
		return uavApplicatton;
	}

	public void setUavApplicatton(String uavApplicatton) {
		this.uavApplicatton = uavApplicatton;
	}
	
	
}
