package com.vt.post.model;

import java.math.BigDecimal;
import java.util.Date;

public class OperPostRel {

    private BigDecimal postId;
    private String postType;
    private BigDecimal orgId;
    private BigDecimal operId;
    private Date effectDate;
    private Date invalidDate;
    private BigDecimal field1;
    private BigDecimal field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;

    public BigDecimal getPostId() {
        return postId;
    }

    public void setPostId(BigDecimal postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    public BigDecimal getOperId() {
        return operId;
    }

    public void setOperId(BigDecimal operId) {
        this.operId = operId;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public BigDecimal getField1() {
        return field1;
    }

    public void setField1(BigDecimal field1) {
        this.field1 = field1;
    }

    public BigDecimal getField2() {
        return field2;
    }

    public void setField2(BigDecimal field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
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
}