package com.vt.post.vo;

import com.vt.post.model.Post;

/**
 * <p> Title: post vo </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 5, 20154:40:43 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 5, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class PostVO extends Post {
    private String higherPostName; // 上级岗位
    private String busOrgName; // 业务机构
    private String underOrgName; // 隶属机构

    public String getHigherPostName() {
        return higherPostName;
    }

    public void setHigherPostName(String higherPostName) {
        this.higherPostName = higherPostName;
    }

    public String getBusOrgName() {
        return busOrgName;
    }

    public void setBusOrgName(String busOrgName) {
        this.busOrgName = busOrgName;
    }

    public String getUnderOrgName() {
        return underOrgName;
    }

    public void setUnderOrgName(String underOrgName) {
        this.underOrgName = underOrgName;
    }


}
