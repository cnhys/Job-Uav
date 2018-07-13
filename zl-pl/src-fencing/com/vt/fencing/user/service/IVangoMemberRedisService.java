package com.vt.fencing.user.service;

import java.io.Serializable;

import com.vt.fencing.model.VangoMember;

/**
 * <h1>万国会员Redis服务定义</h1>
 * 主要用于设置和获取用户相关信息的服务.本服务主要的数据存储使用Redis来实现
 * Created by john on 16/11/18.
 */
public interface IVangoMemberRedisService extends Serializable{
	
    /**
     * <h1>设置用户ID所对应的密钥</h1>
     * <p>使用的结构为:key = "usersecret:${userId}", value = "${secret}"</p>
     * @param userId
     * @param secret
     */
    void setUserSecret(Integer userId, String secret);

    /**
     * <h1>根据用户ID获取对应的secret</h1>
     * @param userId
     * @return
     */
    String getUserSecret(Integer userId);
    
    
    

}
