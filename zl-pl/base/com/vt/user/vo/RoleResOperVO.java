package com.vt.user.vo;

import java.util.Date;

/**
 * <p> Title:用户角色资源信息VO</p>
 * <p> Description:对外提供用户及角色、资源信息非持久化实体对象</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public class RoleResOperVO {

    private Integer operatorId;//操作员编号
    private String userId;//用户ID
    private String userName;//用户名称
    private String status;//状态(正常，挂起，注销，锁定)
    private Date startDate;//用户有效开始日期
    private Date endDate;//用户有效截止日期
    private String department;//所属部门

    private Integer roleId;//角色编号
    private String roleCode;//角色代码
    private String roleName;//角色名称
    private String roleStatus;//角色状态

    private Integer resId;//资源编号
    private String resCode;//资源代码
    private String resName;//资源名称
    private String funcName;//功能名称
    private String resSeq;//序号
    private Integer parentId;//父节点编号
    private String isLeaf;//是否子节点(0:否,1:是)
    private Integer resLevel;//菜单层次
    private String resPath;//资源路径
    private String resStatus;//资源状态(0:失效,1:生效)

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
     * @return the roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode the roleCode to set
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the roleStatus
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * @param roleStatus the roleStatus to set
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * @return the resId
     */
    public Integer getResId() {
        return resId;
    }

    /**
     * @param resId the resId to set
     */
    public void setResId(Integer resId) {
        this.resId = resId;
    }

    /**
     * @return the resCode
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * @param resCode the resCode to set
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /**
     * @return the resName
     */
    public String getResName() {
        return resName;
    }

    /**
     * @param resName the resName to set
     */
    public void setResName(String resName) {
        this.resName = resName;
    }

    /**
     * @return the funcName
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * @param funcName the funcName to set
     */
    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    /**
     * @return the parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @return the resSeq
     */
    public String getResSeq() {
        return resSeq;
    }

    /**
     * @param resSeq the resSeq to set
     */
    public void setResSeq(String resSeq) {
        this.resSeq = resSeq;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the isLeaf
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf the isLeaf to set
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * @return the resLevel
     */
    public Integer getResLevel() {
        return resLevel;
    }

    /**
     * @param resLevel the resLevel to set
     */
    public void setResLevel(Integer resLevel) {
        this.resLevel = resLevel;
    }

    /**
     * @return the resPath
     */
    public String getResPath() {
        return resPath;
    }

    /**
     * @param resPath the resPath to set
     */
    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    /**
     * @return the resStatus
     */
    public String getResStatus() {
        return resStatus;
    }

    /**
     * @param resStatus the resStatus to set
     */
    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
    }

}
