package com.vt.fencing.model;

/**
 * 印章表Entity
 * @author hys
 * @version 2018-06-20
 */
public class SealInfo {
	
	private Integer id;   //id
	private Integer sealIdentification;		// 印章标识
	private Integer sealId;		// 印章ID
	private String sealName;		// 印章名称
	private String sealPicture;		// 印章图片
	private String remakes;		// 备注
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSealName() {
		return sealName;
	}

	public void setSealName(String sealName) {
		this.sealName = sealName;
	}

	public String getRemakes() {
		return remakes;
	}

	public void setRemakes(String remakes) {
		this.remakes = remakes;
	}

	public Integer getSealIdentification() {
		return sealIdentification;
	}

	public void setSealIdentification(Integer sealIdentification) {
		this.sealIdentification = sealIdentification;
	}
	
	public Integer getSealId() {
		return sealId;
	}

	public void setSealId(Integer sealId) {
		this.sealId = sealId;
	}
	
	
	public String getSealPicture() {
		return sealPicture;
	}

	public void setSealPicture(String sealPicture) {
		this.sealPicture = sealPicture;
	}

	@Override
	public String toString() {
		return "SealInfo [id=" + id + ", sealIdentification=" + sealIdentification + ", sealId=" + sealId
				+ ", sealName=" + sealName + ", sealPicture=" + sealPicture + ", remakes=" + remakes + "]";
	}
	
	
}