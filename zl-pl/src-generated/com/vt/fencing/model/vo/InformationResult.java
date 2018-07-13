package com.vt.fencing.model.vo;

import java.util.Date;

public class InformationResult {
	private String typeName;
	private Date addTime;
	private String infoContent;
	private Integer infoId;
	private Integer infoType;
	
	public InformationResult() {
		super();
	}
	public InformationResult(String typeName, Date addTime, String infoContent, Integer infoId, Integer infoType) {
		super();
		this.typeName = typeName;
		this.addTime = addTime;
		this.infoContent = infoContent;
		this.infoId = infoId;
		this.infoType = infoType;
	}
	
	
	public Integer getInfoType() {
		return infoType;
	}
	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	
}
