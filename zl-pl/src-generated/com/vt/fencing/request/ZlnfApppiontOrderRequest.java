package com.vt.fencing.request;

import java.util.Date;

public class ZlnfApppiontOrderRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ID
     *
     * @mbggenerated Fri Sep 08 09:55:26 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ORDERCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String ordercode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.STATE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODKIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodkind;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODUNIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodunit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.APPIONTTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private Date appionttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ORDERLEVEL
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String orderlevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.PRODUCTNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String productname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.REMAINTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private Date remaintime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.COOPCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String coopcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.CREATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private Date createdonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.CREATOR
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ISAUDIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String isaudit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ISDELETED
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.MODIFIER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.UPDATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private Date updatedonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.CREATORDEPTCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String creatordeptcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.CREATORCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String creatorcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.WATER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String water;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODKINDCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodkindcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.FOODLEVELCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String foodlevelcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_APPPIONTORDER.ISREMIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    private String isremind;
    
    /**
     * 有效时长
     */
    private int appiontdateInt;
    
    /**
     * 修改类型：1为正常修改，2为继续提醒
     */
    private String type;
    
    /**
     * 页数
     */
    private String number;

    /**
     * 角色类型
     */
    private String roletype;
    
    /**
     * 选择弹框提醒状态
     */
    private String status;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ID
     *
     * @return the value of ZLNF_APPPIONTORDER.ID
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ID
     *
     * @param id the value for ZLNF_APPPIONTORDER.ID
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ORDERCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.ORDERCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getOrdercode() {
        return ordercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ORDERCODE
     *
     * @param ordercode the value for ZLNF_APPPIONTORDER.ORDERCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodcode() {
        return foodcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODCODE
     *
     * @param foodcode the value for ZLNF_APPPIONTORDER.FOODCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodcode(String foodcode) {
        this.foodcode = foodcode == null ? null : foodcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.STATE
     *
     * @return the value of ZLNF_APPPIONTORDER.STATE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.STATE
     *
     * @param state the value for ZLNF_APPPIONTORDER.STATE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODKIND
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODKIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodkind() {
        return foodkind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODKIND
     *
     * @param foodkind the value for ZLNF_APPPIONTORDER.FOODKIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodkind(String foodkind) {
        this.foodkind = foodkind == null ? null : foodkind.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODUNIT
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODUNIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodunit() {
        return foodunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODUNIT
     *
     * @param foodunit the value for ZLNF_APPPIONTORDER.FOODUNIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodunit(String foodunit) {
        this.foodunit = foodunit == null ? null : foodunit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.APPIONTTIME
     *
     * @return the value of ZLNF_APPPIONTORDER.APPIONTTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public Date getAppionttime() {
        return appionttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.APPIONTTIME
     *
     * @param appionttime the value for ZLNF_APPPIONTORDER.APPIONTTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setAppionttime(Date appionttime) {
        this.appionttime = appionttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ORDERLEVEL
     *
     * @return the value of ZLNF_APPPIONTORDER.ORDERLEVEL
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getOrderlevel() {
        return orderlevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ORDERLEVEL
     *
     * @param orderlevel the value for ZLNF_APPPIONTORDER.ORDERLEVEL
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setOrderlevel(String orderlevel) {
        this.orderlevel = orderlevel == null ? null : orderlevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.PRODUCTNAME
     *
     * @return the value of ZLNF_APPPIONTORDER.PRODUCTNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getProductname() {
        return productname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.PRODUCTNAME
     *
     * @param productname the value for ZLNF_APPPIONTORDER.PRODUCTNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODNAME
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodname() {
        return foodname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODNAME
     *
     * @param foodname the value for ZLNF_APPPIONTORDER.FOODNAME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.REMAINTIME
     *
     * @return the value of ZLNF_APPPIONTORDER.REMAINTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public Date getRemaintime() {
        return remaintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.REMAINTIME
     *
     * @param remaintime the value for ZLNF_APPPIONTORDER.REMAINTIME
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setRemaintime(Date remaintime) {
        this.remaintime = remaintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.COOPCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.COOPCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getCoopcode() {
        return coopcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.COOPCODE
     *
     * @param coopcode the value for ZLNF_APPPIONTORDER.COOPCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setCoopcode(String coopcode) {
        this.coopcode = coopcode == null ? null : coopcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.CREATEDONUTC
     *
     * @return the value of ZLNF_APPPIONTORDER.CREATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public Date getCreatedonutc() {
        return createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.CREATEDONUTC
     *
     * @param createdonutc the value for ZLNF_APPPIONTORDER.CREATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setCreatedonutc(Date createdonutc) {
        this.createdonutc = createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.CREATOR
     *
     * @return the value of ZLNF_APPPIONTORDER.CREATOR
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.CREATOR
     *
     * @param creator the value for ZLNF_APPPIONTORDER.CREATOR
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ISAUDIT
     *
     * @return the value of ZLNF_APPPIONTORDER.ISAUDIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getIsaudit() {
        return isaudit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ISAUDIT
     *
     * @param isaudit the value for ZLNF_APPPIONTORDER.ISAUDIT
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit == null ? null : isaudit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ISDELETED
     *
     * @return the value of ZLNF_APPPIONTORDER.ISDELETED
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ISDELETED
     *
     * @param isdeleted the value for ZLNF_APPPIONTORDER.ISDELETED
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.MODIFIER
     *
     * @return the value of ZLNF_APPPIONTORDER.MODIFIER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.MODIFIER
     *
     * @param modifier the value for ZLNF_APPPIONTORDER.MODIFIER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.UPDATEDONUTC
     *
     * @return the value of ZLNF_APPPIONTORDER.UPDATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public Date getUpdatedonutc() {
        return updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.UPDATEDONUTC
     *
     * @param updatedonutc the value for ZLNF_APPPIONTORDER.UPDATEDONUTC
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setUpdatedonutc(Date updatedonutc) {
        this.updatedonutc = updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.CREATORDEPTCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.CREATORDEPTCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getCreatordeptcode() {
        return creatordeptcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.CREATORDEPTCODE
     *
     * @param creatordeptcode the value for ZLNF_APPPIONTORDER.CREATORDEPTCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setCreatordeptcode(String creatordeptcode) {
        this.creatordeptcode = creatordeptcode == null ? null : creatordeptcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.CREATORCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.CREATORCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getCreatorcode() {
        return creatorcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.CREATORCODE
     *
     * @param creatorcode the value for ZLNF_APPPIONTORDER.CREATORCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setCreatorcode(String creatorcode) {
        this.creatorcode = creatorcode == null ? null : creatorcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.WATER
     *
     * @return the value of ZLNF_APPPIONTORDER.WATER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getWater() {
        return water;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.WATER
     *
     * @param water the value for ZLNF_APPPIONTORDER.WATER
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setWater(String water) {
        this.water = water == null ? null : water.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODKINDCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODKINDCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodkindcode() {
        return foodkindcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODKINDCODE
     *
     * @param foodkindcode the value for ZLNF_APPPIONTORDER.FOODKINDCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodkindcode(String foodkindcode) {
        this.foodkindcode = foodkindcode == null ? null : foodkindcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.FOODLEVELCODE
     *
     * @return the value of ZLNF_APPPIONTORDER.FOODLEVELCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getFoodlevelcode() {
        return foodlevelcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.FOODLEVELCODE
     *
     * @param foodlevelcode the value for ZLNF_APPPIONTORDER.FOODLEVELCODE
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setFoodlevelcode(String foodlevelcode) {
        this.foodlevelcode = foodlevelcode == null ? null : foodlevelcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_APPPIONTORDER.ISREMIND
     *
     * @return the value of ZLNF_APPPIONTORDER.ISREMIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public String getIsremind() {
        return isremind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_APPPIONTORDER.ISREMIND
     *
     * @param isremind the value for ZLNF_APPPIONTORDER.ISREMIND
     *
     * @mbggenerated Fri Sep 08 09:55:27 CST 2017
     */
    public void setIsremind(String isremind) {
        this.isremind = isremind == null ? null : isremind.trim();
    }

	public int getAppiontdateInt() {
		return appiontdateInt;
	}

	public void setAppiontdateInt(int appiontdateInt) {
		this.appiontdateInt = appiontdateInt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}