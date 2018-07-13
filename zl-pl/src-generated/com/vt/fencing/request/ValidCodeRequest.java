package com.vt.fencing.request;

import java.io.Serializable;

/**
 * 发送验证码请求
 * Created by zhangtao on 15/12/30.
 */
public class ValidCodeRequest implements Serializable {
    private static final long serialVersionUID = -191316542365895490L;
    private String mobile;
    private String trade;
    private String userId;//用户Id

    public ValidCodeRequest() {
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidCodeRequest)) return false;

        ValidCodeRequest that = (ValidCodeRequest) o;

        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        return !(trade != null ? !trade.equals(that.trade) : that.trade != null);

    }

    @Override
    public int hashCode() {
        int result = mobile != null ? mobile.hashCode() : 0;
        result = 31 * result + (trade != null ? trade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ValidCodeRequest{" +
                "mobile='" + mobile + '\'' +
                ", trade='" + trade + '\'' +
                '}';
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



}
