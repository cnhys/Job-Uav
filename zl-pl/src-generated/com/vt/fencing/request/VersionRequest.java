package com.vt.fencing.request;



public class VersionRequest {
	
	private Integer loginEmpId;//登录用户Id
	
	private String secret;//秘钥
	
	private String versionId;//版本ID
	private String versionCode;//版本编号
	private String platform;//所属系统
	private String path;//版本路径
	private String versionNum;//
	private String versionDetail;//版本详情
	

	public Integer getLoginEmpId() {
		return loginEmpId;
	}

	public void setLoginEmpId(Integer loginEmpId) {
		this.loginEmpId = loginEmpId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getVersionDetail() {
		return versionDetail;
	}

	public void setVersionDetail(String versionDetail) {
		this.versionDetail = versionDetail;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	

	
	
	
	
	
	
	
	
	
}
