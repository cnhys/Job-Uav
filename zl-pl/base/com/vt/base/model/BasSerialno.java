package com.vt.base.model;

import java.util.Date;

import com.vt.base.annotation.PrimaryKey;

public class BasSerialno {
    @PrimaryKey
    private Long id;

    private String serialnoCode;

    private Date bussDate;

    private Long currentValue;

    private String serialNo;

    private Long serialValue;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Long getSerialValue() {
        return serialValue;
    }

    public void setSerialValue(Long serialValue) {
        this.serialValue = serialValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialnoCode() {
        return serialnoCode;
    }

    public void setSerialnoCode(String serialnoCode) {
        this.serialnoCode = serialnoCode;
    }

    public Date getBussDate() {
        return bussDate;
    }

    public void setBussDate(Date bussDate) {
        this.bussDate = bussDate;
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }
}