package com.vt.user.vo;

import java.util.Date;

/**
 * <p> Title:用户及机构信息VO</p>
 * <p> Description:对外提供用户及机构信息非持久化实体对象</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public class OrgAndOperatorVO {

    private Integer operatorId;//操作员编号
    private String userName;//用户名称
    private String fullName;//全名
    private String status;//状态(正常，挂起，注销，锁定)
    private Date startDate;//用户有效开始日期
    private Date endDate;//用户有效截止日期
    private String department;//所属部门

    private Integer orgId;//机构ID
    private String orgCode;//机构编码
    private String orgName;//机构名称
    private String orgLevel;//机构级别
    private Integer parentOrgId;//父机构ID
    private String orgType;//机构类型
    private String orgAddress;//机构地址
    private String orgState;//机构状态
    private String region;//所属地域
    private Date effectDate;//机构生效日期
    private Date invalidDate;//机构失效日期


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
     * @return the orgid
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * @param orgid the orgid to set
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the orgLevel
     */
    public String getOrgLevel() {
        return orgLevel;
    }

    /**
     * @param orgLevel the orgLevel to set
     */
    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    /**
     * @return the parentOrgId
     */
    public Integer getParentOrgId() {
        return parentOrgId;
    }

    /**
     * @param parentOrgId the parentOrgId to set
     */
    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the orgAddress
     */
    public String getOrgAddress() {
        return orgAddress;
    }

    /**
     * @param orgAddress the orgAddress to set
     */
    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    /**
     * @return the orgState
     */
    public String getOrgState() {
        return orgState;
    }

    /**
     * @param orgState the orgState to set
     */
    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the effectDate
     */
    public Date getEffectDate() {
        return effectDate;
    }

    /**
     * @param effectDate the effectDate to set
     */
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    /**
     * @return the invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }

    /**
     * @param invalidDate the invalidDate to set
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
