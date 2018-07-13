package com.vt.base.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.vt.base.annotation.PrimaryKey;

public class DictItem implements Serializable {

    private static final long serialVersionUID = -1436136440277404585L;
    @PrimaryKey
    private BigDecimal itemId;

    private String dictTypeCode;

    private String dictItemValue;

    private String dictItemName;

    private String status;

    private BigDecimal serialno;

    private BigDecimal dictLevel;

    private BigDecimal parentId;

    private String seqNo;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String field6;

    public BigDecimal getItemId() {
        return itemId;
    }

    public void setItemId(BigDecimal itemId) {
        this.itemId = itemId;
    }

    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }

    public String getDictItemValue() {
        return dictItemValue;
    }

    public void setDictItemValue(String dictItemValue) {
        this.dictItemValue = dictItemValue;
    }

    public String getDictItemName() {
        return dictItemName;
    }

    public void setDictItemName(String dictItemName) {
        this.dictItemName = dictItemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSerialno() {
        return serialno;
    }

    public void setSerialno(BigDecimal serialno) {
        this.serialno = serialno;
    }

    public BigDecimal getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(BigDecimal dictLevel) {
        this.dictLevel = dictLevel;
    }

    public BigDecimal getParentId() {
        return parentId;
    }

    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
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