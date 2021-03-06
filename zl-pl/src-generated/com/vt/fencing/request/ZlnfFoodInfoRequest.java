package com.vt.fencing.request;

public class ZlnfFoodInfoRequest {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.FMZT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String fmzt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.YORN
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String yorn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.FOODCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String foodcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.FOODNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String foodname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.FOODNAMEJC
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String foodnamejc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.LINKNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String linkname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.PHONE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.CJSJ
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String cjsj;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.ADDRESS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.NBBM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String nbbm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.LKZZJGDM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String lkzzjgdm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.LNG
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String lng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.LAT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.BUSYSTATE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String busystate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.ISDELETED
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.PROVICE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String provice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.PROVICECODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String provicecode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.CITY
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.CITYCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String citycode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ZLNF_FOODINFO.REMARKS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    private String remarks;
    
    /**
     * 用户编号
     */
    private String usercode;
   
    /**
     * 粮食水分
     */
    private Double water;

    /**
     * 粮食种类
     */
    private String foodkind;

    /**
     * 粮食等级
     */
    private String foodlevel;

    /**
     * 进入类型
     */
    private String type;

    /**
     * 合作社编号
     */
    private String coopcode;

    /**
     * 角色信息
     */
    private String roletype;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.FMZT
     *
     * @return the value of ZLNF_FOODINFO.FMZT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getFmzt() {
        return fmzt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.FMZT
     *
     * @param fmzt the value for ZLNF_FOODINFO.FMZT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setFmzt(String fmzt) {
        this.fmzt = fmzt == null ? null : fmzt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.YORN
     *
     * @return the value of ZLNF_FOODINFO.YORN
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getYorn() {
        return yorn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.YORN
     *
     * @param yorn the value for ZLNF_FOODINFO.YORN
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setYorn(String yorn) {
        this.yorn = yorn == null ? null : yorn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.FOODCODE
     *
     * @return the value of ZLNF_FOODINFO.FOODCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getFoodcode() {
        return foodcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.FOODCODE
     *
     * @param foodcode the value for ZLNF_FOODINFO.FOODCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setFoodcode(String foodcode) {
        this.foodcode = foodcode == null ? null : foodcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.FOODNAME
     *
     * @return the value of ZLNF_FOODINFO.FOODNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getFoodname() {
        return foodname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.FOODNAME
     *
     * @param foodname the value for ZLNF_FOODINFO.FOODNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.FOODNAMEJC
     *
     * @return the value of ZLNF_FOODINFO.FOODNAMEJC
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getFoodnamejc() {
        return foodnamejc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.FOODNAMEJC
     *
     * @param foodnamejc the value for ZLNF_FOODINFO.FOODNAMEJC
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setFoodnamejc(String foodnamejc) {
        this.foodnamejc = foodnamejc == null ? null : foodnamejc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.LINKNAME
     *
     * @return the value of ZLNF_FOODINFO.LINKNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getLinkname() {
        return linkname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.LINKNAME
     *
     * @param linkname the value for ZLNF_FOODINFO.LINKNAME
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setLinkname(String linkname) {
        this.linkname = linkname == null ? null : linkname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.PHONE
     *
     * @return the value of ZLNF_FOODINFO.PHONE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.PHONE
     *
     * @param phone the value for ZLNF_FOODINFO.PHONE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.CJSJ
     *
     * @return the value of ZLNF_FOODINFO.CJSJ
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getCjsj() {
        return cjsj;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.CJSJ
     *
     * @param cjsj the value for ZLNF_FOODINFO.CJSJ
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setCjsj(String cjsj) {
        this.cjsj = cjsj == null ? null : cjsj.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.ADDRESS
     *
     * @return the value of ZLNF_FOODINFO.ADDRESS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.ADDRESS
     *
     * @param address the value for ZLNF_FOODINFO.ADDRESS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.NBBM
     *
     * @return the value of ZLNF_FOODINFO.NBBM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getNbbm() {
        return nbbm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.NBBM
     *
     * @param nbbm the value for ZLNF_FOODINFO.NBBM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setNbbm(String nbbm) {
        this.nbbm = nbbm == null ? null : nbbm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.LKZZJGDM
     *
     * @return the value of ZLNF_FOODINFO.LKZZJGDM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getLkzzjgdm() {
        return lkzzjgdm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.LKZZJGDM
     *
     * @param lkzzjgdm the value for ZLNF_FOODINFO.LKZZJGDM
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setLkzzjgdm(String lkzzjgdm) {
        this.lkzzjgdm = lkzzjgdm == null ? null : lkzzjgdm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.LNG
     *
     * @return the value of ZLNF_FOODINFO.LNG
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getLng() {
        return lng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.LNG
     *
     * @param lng the value for ZLNF_FOODINFO.LNG
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.LAT
     *
     * @return the value of ZLNF_FOODINFO.LAT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.LAT
     *
     * @param lat the value for ZLNF_FOODINFO.LAT
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.BUSYSTATE
     *
     * @return the value of ZLNF_FOODINFO.BUSYSTATE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getBusystate() {
        return busystate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.BUSYSTATE
     *
     * @param busystate the value for ZLNF_FOODINFO.BUSYSTATE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setBusystate(String busystate) {
        this.busystate = busystate == null ? null : busystate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.ISDELETED
     *
     * @return the value of ZLNF_FOODINFO.ISDELETED
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.ISDELETED
     *
     * @param isdeleted the value for ZLNF_FOODINFO.ISDELETED
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.PROVICE
     *
     * @return the value of ZLNF_FOODINFO.PROVICE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getProvice() {
        return provice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.PROVICE
     *
     * @param provice the value for ZLNF_FOODINFO.PROVICE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setProvice(String provice) {
        this.provice = provice == null ? null : provice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.PROVICECODE
     *
     * @return the value of ZLNF_FOODINFO.PROVICECODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getProvicecode() {
        return provicecode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.PROVICECODE
     *
     * @param provicecode the value for ZLNF_FOODINFO.PROVICECODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setProvicecode(String provicecode) {
        this.provicecode = provicecode == null ? null : provicecode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.CITY
     *
     * @return the value of ZLNF_FOODINFO.CITY
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.CITY
     *
     * @param city the value for ZLNF_FOODINFO.CITY
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.CITYCODE
     *
     * @return the value of ZLNF_FOODINFO.CITYCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getCitycode() {
        return citycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.CITYCODE
     *
     * @param citycode the value for ZLNF_FOODINFO.CITYCODE
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ZLNF_FOODINFO.REMARKS
     *
     * @return the value of ZLNF_FOODINFO.REMARKS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ZLNF_FOODINFO.REMARKS
     *
     * @param remarks the value for ZLNF_FOODINFO.REMARKS
     *
     * @mbggenerated Wed Oct 18 17:09:31 CST 2017
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Double getWater() {
		return water;
	}

	public void setWater(Double water) {
		this.water = water;
	}

	public String getFoodkind() {
		return foodkind;
	}

	public void setFoodkind(String foodkind) {
		this.foodkind = foodkind;
	}

	public String getFoodlevel() {
		return foodlevel;
	}

	public void setFoodlevel(String foodlevel) {
		this.foodlevel = foodlevel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoopcode() {
		return coopcode;
	}

	public void setCoopcode(String coopcode) {
		this.coopcode = coopcode;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
}