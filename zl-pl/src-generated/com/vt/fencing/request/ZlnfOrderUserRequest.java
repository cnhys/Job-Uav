package com.vt.fencing.request;

import java.util.Date;

public class ZlnfOrderUserRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.ID
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.ORDERUSERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String orderusercode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.ORDERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String ordercode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.CUSTCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String custcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.CUSTNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String custname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.SUCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String sucode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.SUNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String suname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.FMCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String fmcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.FMNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String fmname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.FINANCECODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String financecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.FINANCENAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String financename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.CREATOR
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.CREATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private Object createdonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.ISDELETED
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.MODIFIER
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.UPDATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private Date updatedonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_ORDERUSER.CUSTMOBILE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    private String custmobile;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.ID
     *
     * @return the value of ZLNF_ORDERUSER.ID
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.ID
     *
     * @param id the value for ZLNF_ORDERUSER.ID
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.ORDERUSERCODE
     *
     * @return the value of ZLNF_ORDERUSER.ORDERUSERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getOrderusercode() {
        return orderusercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.ORDERUSERCODE
     *
     * @param orderusercode the value for ZLNF_ORDERUSER.ORDERUSERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setOrderusercode(String orderusercode) {
        this.orderusercode = orderusercode == null ? null : orderusercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.ORDERCODE
     *
     * @return the value of ZLNF_ORDERUSER.ORDERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getOrdercode() {
        return ordercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.ORDERCODE
     *
     * @param ordercode the value for ZLNF_ORDERUSER.ORDERCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.CUSTCODE
     *
     * @return the value of ZLNF_ORDERUSER.CUSTCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getCustcode() {
        return custcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.CUSTCODE
     *
     * @param custcode the value for ZLNF_ORDERUSER.CUSTCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setCustcode(String custcode) {
        this.custcode = custcode == null ? null : custcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.CUSTNAME
     *
     * @return the value of ZLNF_ORDERUSER.CUSTNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getCustname() {
        return custname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.CUSTNAME
     *
     * @param custname the value for ZLNF_ORDERUSER.CUSTNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setCustname(String custname) {
        this.custname = custname == null ? null : custname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.SUCODE
     *
     * @return the value of ZLNF_ORDERUSER.SUCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getSucode() {
        return sucode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.SUCODE
     *
     * @param sucode the value for ZLNF_ORDERUSER.SUCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setSucode(String sucode) {
        this.sucode = sucode == null ? null : sucode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.SUNAME
     *
     * @return the value of ZLNF_ORDERUSER.SUNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getSuname() {
        return suname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.SUNAME
     *
     * @param suname the value for ZLNF_ORDERUSER.SUNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setSuname(String suname) {
        this.suname = suname == null ? null : suname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.FMCODE
     *
     * @return the value of ZLNF_ORDERUSER.FMCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getFmcode() {
        return fmcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.FMCODE
     *
     * @param fmcode the value for ZLNF_ORDERUSER.FMCODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setFmcode(String fmcode) {
        this.fmcode = fmcode == null ? null : fmcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.FMNAME
     *
     * @return the value of ZLNF_ORDERUSER.FMNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getFmname() {
        return fmname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.FMNAME
     *
     * @param fmname the value for ZLNF_ORDERUSER.FMNAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setFmname(String fmname) {
        this.fmname = fmname == null ? null : fmname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.FINANCECODE
     *
     * @return the value of ZLNF_ORDERUSER.FINANCECODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getFinancecode() {
        return financecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.FINANCECODE
     *
     * @param financecode the value for ZLNF_ORDERUSER.FINANCECODE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setFinancecode(String financecode) {
        this.financecode = financecode == null ? null : financecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.FINANCENAME
     *
     * @return the value of ZLNF_ORDERUSER.FINANCENAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getFinancename() {
        return financename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.FINANCENAME
     *
     * @param financename the value for ZLNF_ORDERUSER.FINANCENAME
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setFinancename(String financename) {
        this.financename = financename == null ? null : financename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.CREATOR
     *
     * @return the value of ZLNF_ORDERUSER.CREATOR
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.CREATOR
     *
     * @param creator the value for ZLNF_ORDERUSER.CREATOR
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.CREATEDONUTC
     *
     * @return the value of ZLNF_ORDERUSER.CREATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public Object getCreatedonutc() {
        return createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.CREATEDONUTC
     *
     * @param createdonutc the value for ZLNF_ORDERUSER.CREATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setCreatedonutc(Object createdonutc) {
        this.createdonutc = createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.ISDELETED
     *
     * @return the value of ZLNF_ORDERUSER.ISDELETED
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.ISDELETED
     *
     * @param isdeleted the value for ZLNF_ORDERUSER.ISDELETED
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.MODIFIER
     *
     * @return the value of ZLNF_ORDERUSER.MODIFIER
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.MODIFIER
     *
     * @param modifier the value for ZLNF_ORDERUSER.MODIFIER
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.UPDATEDONUTC
     *
     * @return the value of ZLNF_ORDERUSER.UPDATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public Date getUpdatedonutc() {
        return updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.UPDATEDONUTC
     *
     * @param updatedonutc the value for ZLNF_ORDERUSER.UPDATEDONUTC
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setUpdatedonutc(Date updatedonutc) {
        this.updatedonutc = updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_ORDERUSER.CUSTMOBILE
     *
     * @return the value of ZLNF_ORDERUSER.CUSTMOBILE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public String getCustmobile() {
        return custmobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_ORDERUSER.CUSTMOBILE
     *
     * @param custmobile the value for ZLNF_ORDERUSER.CUSTMOBILE
     *
     * @mbggenerated Thu Aug 31 15:31:06 CST 2017
     */
    public void setCustmobile(String custmobile) {
        this.custmobile = custmobile == null ? null : custmobile.trim();
    }
}