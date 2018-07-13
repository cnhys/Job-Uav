package com.vt.fencing.model;

import java.util.Date;

public class AaUavMessage {
	private Integer uavId;
	private Integer uavClassId;
	private Integer uavBrand;
	private Integer statusId;
	private String uavType;
	private Integer uavAxleDistance;
	private Integer uavPropellerLength;
	private Integer workingLoadId;
	private Date madeDate;
	private Date validDate;
	private String uavUniqueId;
	private String imagePath;
	private Integer enabled;
	private Integer userId;
	private String imei;
	private Date addTime;
	
	private String uavApplicatton;
	private String uavStructure;
	private String uavClassDesc;
	
	private String workingLoadName;
	private String workingLoadDesc;
	
	
	
	public String getUavApplicatton() {
		return uavApplicatton;
	}
	public void setUavApplicatton(String uavApplicatton) {
		this.uavApplicatton = uavApplicatton;
	}
	public String getUavStructure() {
		return uavStructure;
	}
	public void setUavStructure(String uavStructure) {
		this.uavStructure = uavStructure;
	}
	public String getUavClassDesc() {
		return uavClassDesc;
	}
	public void setUavClassDesc(String uavClassDesc) {
		this.uavClassDesc = uavClassDesc;
	}
	public String getWorkingLoadName() {
		return workingLoadName;
	}
	public void setWorkingLoadName(String workingLoadName) {
		this.workingLoadName = workingLoadName;
	}
	public String getWorkingLoadDesc() {
		return workingLoadDesc;
	}
	public void setWorkingLoadDesc(String workingLoadDesc) {
		this.workingLoadDesc = workingLoadDesc;
	}
	public AaUavMessage() {
		super();
	}
	public AaUavMessage(Integer uavId, Integer uavClassId, Integer uavBrand, Integer statusId, String uavType,
			Integer uavAxleDistance, Integer uavPropellerLength, Integer workingLoadId, Date madeDate, Date validDate,
			String uavUniqueId, String imagePath, Integer enabled, Integer userId, String imei, Date addTime) {
		super();
		this.uavId = uavId;
		this.uavClassId = uavClassId;
		this.uavBrand = uavBrand;
		this.statusId = statusId;
		this.uavType = uavType;
		this.uavAxleDistance = uavAxleDistance;
		this.uavPropellerLength = uavPropellerLength;
		this.workingLoadId = workingLoadId;
		this.madeDate = madeDate;
		this.validDate = validDate;
		this.uavUniqueId = uavUniqueId;
		this.imagePath = imagePath;
		this.enabled = enabled;
		this.userId = userId;
		this.imei = imei;
		this.addTime = addTime;
	}
	public Integer getUavId() {
		return uavId;
	}
	public void setUavId(Integer uavId) {
		this.uavId = uavId;
	}
	public Integer getUavClassId() {
		return uavClassId;
	}
	public void setUavClassId(Integer uavClassId) {
		this.uavClassId = uavClassId;
	}
	public Integer getUavBrand() {
		return uavBrand;
	}
	public void setUavBrand(Integer uavBrand) {
		this.uavBrand = uavBrand;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getUavType() {
		return uavType;
	}
	public void setUavType(String uavType) {
		this.uavType = uavType;
	}
	public Integer getUavAxleDistance() {
		return uavAxleDistance;
	}
	public void setUavAxleDistance(Integer uavAxleDistance) {
		this.uavAxleDistance = uavAxleDistance;
	}
	public Integer getUavPropellerLength() {
		return uavPropellerLength;
	}
	public void setUavPropellerLength(Integer uavPropellerLength) {
		this.uavPropellerLength = uavPropellerLength;
	}
	public Integer getWorkingLoadId() {
		return workingLoadId;
	}
	public void setWorkingLoadId(Integer workingLoadId) {
		this.workingLoadId = workingLoadId;
	}
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public String getUavUniqueId() {
		return uavUniqueId;
	}
	public void setUavUniqueId(String uavUniqueId) {
		this.uavUniqueId = uavUniqueId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
