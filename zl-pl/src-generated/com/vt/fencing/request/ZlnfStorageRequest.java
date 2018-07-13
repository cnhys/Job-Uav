package com.vt.fencing.request;

import java.util.Date;

public class ZlnfStorageRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.ID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.RKID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String rkid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.HTID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String htid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.XYID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String xyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.CREATEDATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.CLNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String clname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.USERID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.PHONE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.IDNUMBER
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String idnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.LIBNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String libname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.LIBID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String libid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.VARIETY
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String variety;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.HTLEVEL
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String htlevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.GLSHUIFEN
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String glshuifen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.CNT
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String cnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.PRICE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.ACTIONSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String actionstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.RETURNSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String returnstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.SETTLEMENTSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String settlementstate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.VARIETYCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String varietycode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.HTLEVELCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String htlevelcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_STORAGE.ISDELETE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    private String isdelete;
    
    /**
     * 页数
     */
     private String number;    

     /**
      * 支付密码
      */
     private String paypassword;    
     
     
     /**
      *剩余粮食数量
      */
     private  String surplus;
     
    public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.ID
     *
     * @return the value of ZLNF_STORAGE.ID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.ID
     *
     * @param id the value for ZLNF_STORAGE.ID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.RKID
     *
     * @return the value of ZLNF_STORAGE.RKID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getRkid() {
        return rkid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.RKID
     *
     * @param rkid the value for ZLNF_STORAGE.RKID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setRkid(String rkid) {
        this.rkid = rkid == null ? null : rkid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.HTID
     *
     * @return the value of ZLNF_STORAGE.HTID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getHtid() {
        return htid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.HTID
     *
     * @param htid the value for ZLNF_STORAGE.HTID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setHtid(String htid) {
        this.htid = htid == null ? null : htid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.XYID
     *
     * @return the value of ZLNF_STORAGE.XYID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getXyid() {
        return xyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.XYID
     *
     * @param xyid the value for ZLNF_STORAGE.XYID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setXyid(String xyid) {
        this.xyid = xyid == null ? null : xyid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.CREATEDATE
     *
     * @return the value of ZLNF_STORAGE.CREATEDATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.CREATEDATE
     *
     * @param createdate the value for ZLNF_STORAGE.CREATEDATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.CLNAME
     *
     * @return the value of ZLNF_STORAGE.CLNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getClname() {
        return clname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.CLNAME
     *
     * @param clname the value for ZLNF_STORAGE.CLNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setClname(String clname) {
        this.clname = clname == null ? null : clname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.USERID
     *
     * @return the value of ZLNF_STORAGE.USERID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.USERID
     *
     * @param userid the value for ZLNF_STORAGE.USERID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.PHONE
     *
     * @return the value of ZLNF_STORAGE.PHONE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.PHONE
     *
     * @param phone the value for ZLNF_STORAGE.PHONE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.IDNUMBER
     *
     * @return the value of ZLNF_STORAGE.IDNUMBER
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getIdnumber() {
        return idnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.IDNUMBER
     *
     * @param idnumber the value for ZLNF_STORAGE.IDNUMBER
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.LIBNAME
     *
     * @return the value of ZLNF_STORAGE.LIBNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getLibname() {
        return libname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.LIBNAME
     *
     * @param libname the value for ZLNF_STORAGE.LIBNAME
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setLibname(String libname) {
        this.libname = libname == null ? null : libname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.LIBID
     *
     * @return the value of ZLNF_STORAGE.LIBID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getLibid() {
        return libid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.LIBID
     *
     * @param libid the value for ZLNF_STORAGE.LIBID
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setLibid(String libid) {
        this.libid = libid == null ? null : libid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.VARIETY
     *
     * @return the value of ZLNF_STORAGE.VARIETY
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getVariety() {
        return variety;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.VARIETY
     *
     * @param variety the value for ZLNF_STORAGE.VARIETY
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setVariety(String variety) {
        this.variety = variety == null ? null : variety.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.HTLEVEL
     *
     * @return the value of ZLNF_STORAGE.HTLEVEL
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getHtlevel() {
        return htlevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.HTLEVEL
     *
     * @param htlevel the value for ZLNF_STORAGE.HTLEVEL
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setHtlevel(String htlevel) {
        this.htlevel = htlevel == null ? null : htlevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.GLSHUIFEN
     *
     * @return the value of ZLNF_STORAGE.GLSHUIFEN
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getGlshuifen() {
        return glshuifen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.GLSHUIFEN
     *
     * @param glshuifen the value for ZLNF_STORAGE.GLSHUIFEN
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setGlshuifen(String glshuifen) {
        this.glshuifen = glshuifen == null ? null : glshuifen.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.CNT
     *
     * @return the value of ZLNF_STORAGE.CNT
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getCnt() {
        return cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.CNT
     *
     * @param cnt the value for ZLNF_STORAGE.CNT
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setCnt(String cnt) {
        this.cnt = cnt == null ? null : cnt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.PRICE
     *
     * @return the value of ZLNF_STORAGE.PRICE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.PRICE
     *
     * @param price the value for ZLNF_STORAGE.PRICE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.ACTIONSTATE
     *
     * @return the value of ZLNF_STORAGE.ACTIONSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getActionstate() {
        return actionstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.ACTIONSTATE
     *
     * @param actionstate the value for ZLNF_STORAGE.ACTIONSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setActionstate(String actionstate) {
        this.actionstate = actionstate == null ? null : actionstate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.RETURNSTATE
     *
     * @return the value of ZLNF_STORAGE.RETURNSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getReturnstate() {
        return returnstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.RETURNSTATE
     *
     * @param returnstate the value for ZLNF_STORAGE.RETURNSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setReturnstate(String returnstate) {
        this.returnstate = returnstate == null ? null : returnstate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.SETTLEMENTSTATE
     *
     * @return the value of ZLNF_STORAGE.SETTLEMENTSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getSettlementstate() {
        return settlementstate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.SETTLEMENTSTATE
     *
     * @param settlementstate the value for ZLNF_STORAGE.SETTLEMENTSTATE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setSettlementstate(String settlementstate) {
        this.settlementstate = settlementstate == null ? null : settlementstate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.VARIETYCODE
     *
     * @return the value of ZLNF_STORAGE.VARIETYCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getVarietycode() {
        return varietycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.VARIETYCODE
     *
     * @param varietycode the value for ZLNF_STORAGE.VARIETYCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setVarietycode(String varietycode) {
        this.varietycode = varietycode == null ? null : varietycode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.HTLEVELCODE
     *
     * @return the value of ZLNF_STORAGE.HTLEVELCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getHtlevelcode() {
        return htlevelcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.HTLEVELCODE
     *
     * @param htlevelcode the value for ZLNF_STORAGE.HTLEVELCODE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setHtlevelcode(String htlevelcode) {
        this.htlevelcode = htlevelcode == null ? null : htlevelcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_STORAGE.ISDELETE
     *
     * @return the value of ZLNF_STORAGE.ISDELETE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_STORAGE.ISDELETE
     *
     * @param isdelete the value for ZLNF_STORAGE.ISDELETE
     *
     * @mbggenerated Tue Sep 26 16:54:24 CST 2017
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}
}