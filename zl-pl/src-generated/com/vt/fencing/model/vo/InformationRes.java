package com.vt.fencing.model.vo;

public class InformationRes {
	private String typeName;
	private String addTime;
	private String infoContent;
	private Integer infoId;
	private Integer infoType;
	
	public InformationRes() {
		super();
	}
	public InformationRes(String typeName, String addTime, String infoContent, Integer infoId, Integer infoType) {
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
