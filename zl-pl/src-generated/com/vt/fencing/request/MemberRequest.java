package com.vt.fencing.request;

import com.vt.base.StatefullRequest;

public class MemberRequest extends StatefullRequest {
	
	private Integer memberId;
	private String memberCode;//会员ID(CRM会员编号)
	private String memberName;//会员姓名
	private String mobile;//手机号码
	private String password;//密码
	private String activateCode;//激活码
	private String origPassword;//原密码
	private String memberImage;//会员头像路径
	private Integer loginEmpId;//登录用户Id
	private String memberNickname;
	private String email;
	private String memberStatus;
    private String crmStadiumCode;
    private String stadiumCode;//场馆编号
    private Integer roleId;//角色iD
	
	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the memberCode
	 */
	public String getMemberCode() {
		return memberCode;
	}
	/**
	 * @param memberCode the memberCode to set
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}
	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	 * @return the activateCode
	 */
	public String getActivateCode() {
		return activateCode;
	}
	/**
	 * @param activateCode the activateCode to set
	 */
	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
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
	 * @return the memberImage
	 */
	public String getMemberImage() {
		return memberImage;
	}
	/**
	 * @param memberImage the memberImage to set
	 */
	public void setMemberImage(String memberImage) {
		this.memberImage = memberImage;
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
	 * @return the memberNickname
	 */
	public String getMemberNickname() {
		return memberNickname;
	}
	/**
	 * @param memberNickname the memberNickname to set
	 */
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
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
	 * @return the memberStatus
	 */
    public String getMemberStatus() {
        return memberStatus;
    }
    /**
	 * @param memberStatus the memberStatus to set
	 */
    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus == null ? null : memberStatus.trim();
    }
	/**
	 * @return the crmStadiumCode
	 */
	public String getCrmStadiumCode() {
		return crmStadiumCode;
	}
	/**
	 * @param crmStadiumCode the crmStadiumCode to set
	 */
	public void setCrmStadiumCode(String crmStadiumCode) {
		this.crmStadiumCode = crmStadiumCode;
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
    
}
