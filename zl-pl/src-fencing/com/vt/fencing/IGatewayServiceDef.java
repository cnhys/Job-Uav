package com.vt.fencing;

/**
 * Gateway接口定义
 * Created by xuenjun on 16/11/18.
 */
public interface IGatewayServiceDef {
	 
	/**
     * 会员服务服务
     */
    String MEMBER_SERVICE = "_vangoMemberService";
    
    /**
     *会员Redis服务
     */
    String MEMBER_REDIS_SERVICE = "_vangoMemberRedisService";

    /**
     *版本服务
     */
    String VERSION_SERVICE = "_vangoVersionsService";

    /**
     *关于我们服务
     */
    String ABOUTUS_SERVICE = "_vangoAboutsusService";
    
    /**
     *修改激活合作社状态
     */
    String COOP_SERVICE = "_coopService";
    
    /**
     *验证合作社是否存在
     */
    String CUST_SERVICE = "_custService";

}
