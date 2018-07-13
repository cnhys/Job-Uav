package com.vt.post.model;

import java.util.Date;

import com.vt.base.annotation.PrimaryKey;

public class Post {
    @PrimaryKey
    private Integer postId;
    private String postCode;
    private String postName;
    private String postLevel;
    private Integer higherPostId;
    private Integer busOrgCode;
    private Integer underOrgCode;
    private Integer postSerialno;
    private String postType;
    private Date postStartDate;
    private Date postEndDate;
    private String postState;
    private String leafFlag;
    private Integer leafCount;
    private Date createTime;
    private Date lastModfiyTime;
    private String lastModifyUser;
    private Integer field1;
    private Integer field2;
    private Integer field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(String postLevel) {
        this.postLevel = postLevel;
    }

    public Integer getHigherPostId() {
        return higherPostId;
    }

    public void setHigherPostId(Integer higherPostId) {
        this.higherPostId = higherPostId;
    }

    public Integer getBusOrgCode() {
        return busOrgCode;
    }

    public void setBusOrgCode(Integer busOrgCode) {
        this.busOrgCode = busOrgCode;
    }

    public Integer getUnderOrgCode() {
        return underOrgCode;
    }

    public void setUnderOrgCode(Integer underOrgCode) {
        this.underOrgCode = underOrgCode;
    }

    public Integer getPostSerialno() {
        return postSerialno;
    }

    public void setPostSerialno(Integer postSerialno) {
        this.postSerialno = postSerialno;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Date getPostStartDate() {
        return postStartDate;
    }

    public void setPostStartDate(Date postStartDate) {
        this.postStartDate = postStartDate;
    }

    public Date getPostEndDate() {
        return postEndDate;
    }

    public void setPostEndDate(Date postEndDate) {
        this.postEndDate = postEndDate;
    }

    public String getPostState() {
        return postState;
    }

    public void setPostState(String postState) {
        this.postState = postState;
    }

    public String getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(String leafFlag) {
        this.leafFlag = leafFlag;
    }

    public Integer getLeafCount() {
        return leafCount;
    }

    public void setLeafCount(Integer leafCount) {
        this.leafCount = leafCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModfiyTime() {
        return lastModfiyTime;
    }

    public void setLastModfiyTime(Date lastModfiyTime) {
        this.lastModfiyTime = lastModfiyTime;
    }

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public Integer getField1() {
        return field1;
    }

    public void setField1(Integer field1) {
        this.field1 = field1;
    }

    public Integer getField2() {
        return field2;
    }

    public void setField2(Integer field2) {
        this.field2 = field2;
    }

    public Integer getField3() {
        return field3;
    }

    public void setField3(Integer field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }
}