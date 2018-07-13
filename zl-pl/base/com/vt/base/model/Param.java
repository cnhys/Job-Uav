package com.vt.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.vt.base.annotation.PrimaryKey;

public class Param implements Serializable {


    private static final long serialVersionUID = -1758182673578340661L;
    private String paramName;
    private String paramValue;
    private String paramDesc;
    @PrimaryKey
    private BigDecimal serialno;
    private String status;
    private String paramType;
    private String remark;
    private BigDecimal field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public BigDecimal getSerialno() {
        return serialno;
    }

    public void setSerialno(BigDecimal serialno) {
        this.serialno = serialno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getField1() {
        return field1;
    }

    public void setField1(BigDecimal field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
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