package com.vt.base;

/**
 * 有状态请求基类
 * Created by zhangtao on 16/1/2.
 */
public class StatefullRequest {
    protected Integer userId;
    protected String secret;

    public StatefullRequest() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatefullRequest)) return false;

        StatefullRequest that = (StatefullRequest) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return !(secret != null ? !secret.equals(that.secret) : that.secret != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (secret != null ? secret.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StatefullRequest{" +
                "userId=" + userId +
                ", secret='" + secret + '\'' +
                '}';
    }
}
