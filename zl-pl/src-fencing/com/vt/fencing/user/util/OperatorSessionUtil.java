package com.vt.fencing.user.util;

import java.io.Serializable;

/**
 * 后台管理用户
 * Created by zhangtao on 15/12/30.
 */
public class OperatorSessionUtil implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 608638557710587645L;
	Integer operatorId ;    //1.用户ID
	String userName;    //2.姓名（若实名认证）
	String mobile;  //3.手机号
	String email;   //4.邮箱（若已绑定）
	String password;//5.密码
	String userId;  //6.用户id
	String secret;  //7.秘钥
	Integer roleId;//角色ID
	String stadiumCode;//场馆编号
	
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the stadiumCode
	 */
	public String getStadiumCode() {
		return stadiumCode;
	}
	/**
	 * @param stadiumCode the stadiumCode to set
	 */
	public void setStadiumCode(String stadiumCode) {
		this.stadiumCode = stadiumCode;
	}


}
