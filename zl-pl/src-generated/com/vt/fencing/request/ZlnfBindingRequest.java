package com.vt.fencing.request;

import java.util.Date;

public class ZlnfBindingRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.ID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.BINDINGCODE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String bindingcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.USERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.BINDINGNUM
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String bindingnum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.BINDINGTYPE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String bindingtype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.DEFAULTORDER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String defaultorder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.CREATOR
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.CREATORID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String creatorid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.CREATEDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.MODIFIER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.MODIFIERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String modifierid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.MODIFYDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private Date modifydate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.ISDELETE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String isdelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.REQUESTID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String requestid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.IDDENTITYID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String iddentityid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.ISBINDCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String isbindcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.BINDID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String bindid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.NICKNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.PHOTOPATH
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String photopath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.UNIONID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String unionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.BANKCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String bankcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.AUTHNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String authname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_BINDING.PHNOE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    private String phnoe;
    
    /**
     * 手机号
     */
    private String phone;

    /**
     * 终端IP
     */
    private String userip;

    /**
     * 姓名
     */
    private String username;

    /**
     * 身份证号
     */
    private String idcardno;

    /**
     * 设备号
     */
    private String terminalid;

    /**
     * 短信验证码
     */
    private String validatecode;
    /*
     * 用户编号
     */
    private String usercode;
    /**
     * 支付密码
     */
    private  String paypassword;
    
    public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.ID
     *
     * @return the value of ZLNF_BINDING.ID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.ID
     *
     * @param id the value for ZLNF_BINDING.ID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.BINDINGCODE
     *
     * @return the value of ZLNF_BINDING.BINDINGCODE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getBindingcode() {
        return bindingcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.BINDINGCODE
     *
     * @param bindingcode the value for ZLNF_BINDING.BINDINGCODE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setBindingcode(String bindingcode) {
        this.bindingcode = bindingcode == null ? null : bindingcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.USERID
     *
     * @return the value of ZLNF_BINDING.USERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.USERID
     *
     * @param userid the value for ZLNF_BINDING.USERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.BINDINGNUM
     *
     * @return the value of ZLNF_BINDING.BINDINGNUM
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getBindingnum() {
        return bindingnum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.BINDINGNUM
     *
     * @param bindingnum the value for ZLNF_BINDING.BINDINGNUM
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setBindingnum(String bindingnum) {
        this.bindingnum = bindingnum == null ? null : bindingnum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.BINDINGTYPE
     *
     * @return the value of ZLNF_BINDING.BINDINGTYPE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getBindingtype() {
        return bindingtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.BINDINGTYPE
     *
     * @param bindingtype the value for ZLNF_BINDING.BINDINGTYPE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setBindingtype(String bindingtype) {
        this.bindingtype = bindingtype == null ? null : bindingtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.DEFAULTORDER
     *
     * @return the value of ZLNF_BINDING.DEFAULTORDER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getDefaultorder() {
        return defaultorder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.DEFAULTORDER
     *
     * @param defaultorder the value for ZLNF_BINDING.DEFAULTORDER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setDefaultorder(String defaultorder) {
        this.defaultorder = defaultorder == null ? null : defaultorder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.CREATOR
     *
     * @return the value of ZLNF_BINDING.CREATOR
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.CREATOR
     *
     * @param creator the value for ZLNF_BINDING.CREATOR
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.CREATORID
     *
     * @return the value of ZLNF_BINDING.CREATORID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getCreatorid() {
        return creatorid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.CREATORID
     *
     * @param creatorid the value for ZLNF_BINDING.CREATORID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.CREATEDATE
     *
     * @return the value of ZLNF_BINDING.CREATEDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.CREATEDATE
     *
     * @param createdate the value for ZLNF_BINDING.CREATEDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.MODIFIER
     *
     * @return the value of ZLNF_BINDING.MODIFIER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.MODIFIER
     *
     * @param modifier the value for ZLNF_BINDING.MODIFIER
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.MODIFIERID
     *
     * @return the value of ZLNF_BINDING.MODIFIERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getModifierid() {
        return modifierid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.MODIFIERID
     *
     * @param modifierid the value for ZLNF_BINDING.MODIFIERID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setModifierid(String modifierid) {
        this.modifierid = modifierid == null ? null : modifierid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.MODIFYDATE
     *
     * @return the value of ZLNF_BINDING.MODIFYDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public Date getModifydate() {
        return modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.MODIFYDATE
     *
     * @param modifydate the value for ZLNF_BINDING.MODIFYDATE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.ISDELETE
     *
     * @return the value of ZLNF_BINDING.ISDELETE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.ISDELETE
     *
     * @param isdelete the value for ZLNF_BINDING.ISDELETE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.REQUESTID
     *
     * @return the value of ZLNF_BINDING.REQUESTID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getRequestid() {
        return requestid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.REQUESTID
     *
     * @param requestid the value for ZLNF_BINDING.REQUESTID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.IDDENTITYID
     *
     * @return the value of ZLNF_BINDING.IDDENTITYID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getIddentityid() {
        return iddentityid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.IDDENTITYID
     *
     * @param iddentityid the value for ZLNF_BINDING.IDDENTITYID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setIddentityid(String iddentityid) {
        this.iddentityid = iddentityid == null ? null : iddentityid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.ISBINDCARD
     *
     * @return the value of ZLNF_BINDING.ISBINDCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getIsbindcard() {
        return isbindcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.ISBINDCARD
     *
     * @param isbindcard the value for ZLNF_BINDING.ISBINDCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setIsbindcard(String isbindcard) {
        this.isbindcard = isbindcard == null ? null : isbindcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.BINDID
     *
     * @return the value of ZLNF_BINDING.BINDID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getBindid() {
        return bindid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.BINDID
     *
     * @param bindid the value for ZLNF_BINDING.BINDID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setBindid(String bindid) {
        this.bindid = bindid == null ? null : bindid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.NICKNAME
     *
     * @return the value of ZLNF_BINDING.NICKNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.NICKNAME
     *
     * @param nickname the value for ZLNF_BINDING.NICKNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.PHOTOPATH
     *
     * @return the value of ZLNF_BINDING.PHOTOPATH
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getPhotopath() {
        return photopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.PHOTOPATH
     *
     * @param photopath the value for ZLNF_BINDING.PHOTOPATH
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setPhotopath(String photopath) {
        this.photopath = photopath == null ? null : photopath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.UNIONID
     *
     * @return the value of ZLNF_BINDING.UNIONID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.UNIONID
     *
     * @param unionid the value for ZLNF_BINDING.UNIONID
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.BANKCARD
     *
     * @return the value of ZLNF_BINDING.BANKCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getBankcard() {
        return bankcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.BANKCARD
     *
     * @param bankcard the value for ZLNF_BINDING.BANKCARD
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setBankcard(String bankcard) {
        this.bankcard = bankcard == null ? null : bankcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.AUTHNAME
     *
     * @return the value of ZLNF_BINDING.AUTHNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getAuthname() {
        return authname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.AUTHNAME
     *
     * @param authname the value for ZLNF_BINDING.AUTHNAME
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setAuthname(String authname) {
        this.authname = authname == null ? null : authname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_BINDING.PHNOE
     *
     * @return the value of ZLNF_BINDING.PHNOE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public String getPhnoe() {
        return phnoe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_BINDING.PHNOE
     *
     * @param phnoe the value for ZLNF_BINDING.PHNOE
     *
     * @mbggenerated Thu Oct 19 11:46:10 CST 2017
     */
    public void setPhnoe(String phnoe) {
        this.phnoe = phnoe == null ? null : phnoe.trim();
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}
}