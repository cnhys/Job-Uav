package com.vt.fencing.user.util;

import java.io.Serializable;
import java.util.Date;

/**
 * PC/移动端前端用户
 * Created by zhangtao on 15/12/30.
 */
public class MemberSessionUtil implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3011086661243722135L;
	
	private Integer userId;
    private String secret;
    private String mobile;
    private String password;
    private String name;
    private String memberStatus;
    private Date regDate;

    /**
     * default construction
     */
    public MemberSessionUtil() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the memberStatus
	 */
	public String getMemberStatus() {
		return memberStatus;
	}

	/**
	 * @param memberStatus the memberStatus to set
	 */
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
