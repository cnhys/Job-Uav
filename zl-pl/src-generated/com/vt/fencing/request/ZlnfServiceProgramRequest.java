package com.vt.fencing.request;

import java.util.Date;
import java.util.List;

import com.vt.fencing.model.ZlnfFarmServiceMachiney;

public class ZlnfServiceProgramRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.ID
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.SERVICECODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String servicecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.FOODKIND
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String foodkind;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.SERVICEPRICE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private Double serviceprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.CREATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private Date createdonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.CREATOR
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.ISAUDIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String isaudit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.ISDELETED
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.MODIFIER
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.UPDATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private Date updatedonutc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.CREATORDEPTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String creatordeptcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.MERCHANTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String merchantcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.SERROTYPEN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String serrotypen;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.SERROTYPENCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String serrotypencode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.AGMATYPRN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String agmatyprn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.AGMATYPRNCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String agmatyprncode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.DISTANCE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String distance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.SERVICEUNIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String serviceunit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_SERVICEPROGRAM.FOODKINDCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    private String foodkindcode;
    
    private List<ZlnfFarmServiceMachiney> machineyList;
    
    private String usercode;

    private String number;

    /**
     * 是否修改机械信息
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.FARMBRAND
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String farmbrand;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.FARMMODEL
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String farmmodel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.USERYEAR
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String useryear;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.ROWSPACING
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String rowspacing;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.FARMROWS
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String farmrows;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FARMSERVICEMACHINEY.FARMNUM
     *
     * @mbggenerated Mon Jul 31 11:24:03 CST 2017
     */
    private String farmnum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.ID
     *
     * @return the value of ZLNF_SERVICEPROGRAM.ID
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.ID
     *
     * @param id the value for ZLNF_SERVICEPROGRAM.ID
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.SERVICECODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.SERVICECODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getServicecode() {
        return servicecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.SERVICECODE
     *
     * @param servicecode the value for ZLNF_SERVICEPROGRAM.SERVICECODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setServicecode(String servicecode) {
        this.servicecode = servicecode == null ? null : servicecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.FOODKIND
     *
     * @return the value of ZLNF_SERVICEPROGRAM.FOODKIND
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getFoodkind() {
        return foodkind;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.FOODKIND
     *
     * @param foodkind the value for ZLNF_SERVICEPROGRAM.FOODKIND
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setFoodkind(String foodkind) {
        this.foodkind = foodkind == null ? null : foodkind.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.SERVICEPRICE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.SERVICEPRICE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public Double getServiceprice() {
        return serviceprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.SERVICEPRICE
     *
     * @param serviceprice the value for ZLNF_SERVICEPROGRAM.SERVICEPRICE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setServiceprice(Double serviceprice) {
        this.serviceprice = serviceprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.CREATEDONUTC
     *
     * @return the value of ZLNF_SERVICEPROGRAM.CREATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public Date getCreatedonutc() {
        return createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.CREATEDONUTC
     *
     * @param createdonutc the value for ZLNF_SERVICEPROGRAM.CREATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setCreatedonutc(Date createdonutc) {
        this.createdonutc = createdonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.CREATOR
     *
     * @return the value of ZLNF_SERVICEPROGRAM.CREATOR
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.CREATOR
     *
     * @param creator the value for ZLNF_SERVICEPROGRAM.CREATOR
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.ISAUDIT
     *
     * @return the value of ZLNF_SERVICEPROGRAM.ISAUDIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getIsaudit() {
        return isaudit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.ISAUDIT
     *
     * @param isaudit the value for ZLNF_SERVICEPROGRAM.ISAUDIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit == null ? null : isaudit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.ISDELETED
     *
     * @return the value of ZLNF_SERVICEPROGRAM.ISDELETED
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.ISDELETED
     *
     * @param isdeleted the value for ZLNF_SERVICEPROGRAM.ISDELETED
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.MODIFIER
     *
     * @return the value of ZLNF_SERVICEPROGRAM.MODIFIER
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.MODIFIER
     *
     * @param modifier the value for ZLNF_SERVICEPROGRAM.MODIFIER
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.UPDATEDONUTC
     *
     * @return the value of ZLNF_SERVICEPROGRAM.UPDATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public Date getUpdatedonutc() {
        return updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.UPDATEDONUTC
     *
     * @param updatedonutc the value for ZLNF_SERVICEPROGRAM.UPDATEDONUTC
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setUpdatedonutc(Date updatedonutc) {
        this.updatedonutc = updatedonutc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.CREATORDEPTCODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.CREATORDEPTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getCreatordeptcode() {
        return creatordeptcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.CREATORDEPTCODE
     *
     * @param creatordeptcode the value for ZLNF_SERVICEPROGRAM.CREATORDEPTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setCreatordeptcode(String creatordeptcode) {
        this.creatordeptcode = creatordeptcode == null ? null : creatordeptcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.MERCHANTCODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.MERCHANTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getMerchantcode() {
        return merchantcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.MERCHANTCODE
     *
     * @param merchantcode the value for ZLNF_SERVICEPROGRAM.MERCHANTCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setMerchantcode(String merchantcode) {
        this.merchantcode = merchantcode == null ? null : merchantcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.SERROTYPEN
     *
     * @return the value of ZLNF_SERVICEPROGRAM.SERROTYPEN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getSerrotypen() {
        return serrotypen;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.SERROTYPEN
     *
     * @param serrotypen the value for ZLNF_SERVICEPROGRAM.SERROTYPEN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setSerrotypen(String serrotypen) {
        this.serrotypen = serrotypen == null ? null : serrotypen.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.SERROTYPENCODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.SERROTYPENCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getSerrotypencode() {
        return serrotypencode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.SERROTYPENCODE
     *
     * @param serrotypencode the value for ZLNF_SERVICEPROGRAM.SERROTYPENCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setSerrotypencode(String serrotypencode) {
        this.serrotypencode = serrotypencode == null ? null : serrotypencode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.AGMATYPRN
     *
     * @return the value of ZLNF_SERVICEPROGRAM.AGMATYPRN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getAgmatyprn() {
        return agmatyprn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.AGMATYPRN
     *
     * @param agmatyprn the value for ZLNF_SERVICEPROGRAM.AGMATYPRN
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setAgmatyprn(String agmatyprn) {
        this.agmatyprn = agmatyprn == null ? null : agmatyprn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.AGMATYPRNCODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.AGMATYPRNCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getAgmatyprncode() {
        return agmatyprncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.AGMATYPRNCODE
     *
     * @param agmatyprncode the value for ZLNF_SERVICEPROGRAM.AGMATYPRNCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setAgmatyprncode(String agmatyprncode) {
        this.agmatyprncode = agmatyprncode == null ? null : agmatyprncode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.DISTANCE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.DISTANCE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getDistance() {
        return distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.DISTANCE
     *
     * @param distance the value for ZLNF_SERVICEPROGRAM.DISTANCE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.SERVICEUNIT
     *
     * @return the value of ZLNF_SERVICEPROGRAM.SERVICEUNIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getServiceunit() {
        return serviceunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.SERVICEUNIT
     *
     * @param serviceunit the value for ZLNF_SERVICEPROGRAM.SERVICEUNIT
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setServiceunit(String serviceunit) {
        this.serviceunit = serviceunit == null ? null : serviceunit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_SERVICEPROGRAM.FOODKINDCODE
     *
     * @return the value of ZLNF_SERVICEPROGRAM.FOODKINDCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public String getFoodkindcode() {
        return foodkindcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_SERVICEPROGRAM.FOODKINDCODE
     *
     * @param foodkindcode the value for ZLNF_SERVICEPROGRAM.FOODKINDCODE
     *
     * @mbggenerated Fri Aug 18 11:52:30 CST 2017
     */
    public void setFoodkindcode(String foodkindcode) {
        this.foodkindcode = foodkindcode == null ? null : foodkindcode.trim();
    }

	public List<ZlnfFarmServiceMachiney> getMachineyList() {
		return machineyList;
	}

	public void setMachineyList(List<ZlnfFarmServiceMachiney> machineyList) {
		this.machineyList = machineyList;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getFarmbrand() {
		return farmbrand;
	}

	public void setFarmbrand(String farmbrand) {
		this.farmbrand = farmbrand;
	}

	public String getFarmmodel() {
		return farmmodel;
	}

	public void setFarmmodel(String farmmodel) {
		this.farmmodel = farmmodel;
	}

	public String getUseryear() {
		return useryear;
	}

	public void setUseryear(String useryear) {
		this.useryear = useryear;
	}

	public String getRowspacing() {
		return rowspacing;
	}

	public void setRowspacing(String rowspacing) {
		this.rowspacing = rowspacing;
	}

	public String getFarmrows() {
		return farmrows;
	}

	public void setFarmrows(String farmrows) {
		this.farmrows = farmrows;
	}

	public String getFarmnum() {
		return farmnum;
	}

	public void setFarmnum(String farmnum) {
		this.farmnum = farmnum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}