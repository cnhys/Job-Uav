package com.vt.fencing.request;

public class OperatorRequest{
	
	private Integer operatorId;//操作用户Id
	private Integer loginEmpId;//登录用户Id
	private String userId;//操作用户名称
	private String userName;//操作用户姓名
	private String mobile;//手机号码
	private String password;//密码
	private String origPassword;//原密码
	private String secret;//秘钥
	private String status;//状态：01-正常，02-已锁定
	private String roleLike;
	private String field1;//场馆编号

	/**
	 * @return the operatorId
	 */
	public Integer getOperatorId() {
		return operatorId;
	}
	/**
	 * @param operatorId the operatorId to set
	 */
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	
	/**
	 * @return the loginEmpId
	 */
	public Integer getLoginEmpId() {
		return loginEmpId;
	}
	/**
	 * @param loginEmpId the loginEmpId to set
	 */
	public void setLoginEmpId(Integer loginEmpId) {
		this.loginEmpId = loginEmpId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the origPassword
	 */
	public String getOrigPassword() {
		return origPassword;
	}
	/**
	 * @param origPassword the origPassword to set
	 */
	public void setOrigPassword(String origPassword) {
		this.origPassword = origPassword;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the roleLike
	 */
	public String getRoleLike() {
		return roleLike;
	}
	/**
	 * @param roleLike the roleLike to set
	 */
	public void setRoleLike(String roleLike) {
		this.roleLike = roleLike;
	}
	/**
	 * @return the field1
	 */
	public String getField1() {
		return field1;
	}
	/**
	 * @param field1 the field1 to set
	 */
	public void setField1(String field1) {
		this.field1 = field1;
	}
	
	
}
