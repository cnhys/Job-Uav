package com.vt.base.util;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;  
import cn.jpush.api.push.model.Message;  
import cn.jpush.api.push.model.Options;  
import cn.jpush.api.push.model.Platform;  
import cn.jpush.api.push.model.PushPayload;  
import cn.jpush.api.push.model.audience.Audience;  
import cn.jpush.api.push.model.audience.AudienceTarget;  
import cn.jpush.api.push.model.notification.AndroidNotification;  
import cn.jpush.api.push.model.notification.IosNotification;  
import cn.jpush.api.push.model.notification.Notification;  
  
public class MSG4Jpush {  
     protected static final Logger LOG = LoggerFactory.getLogger(MSG4Jpush.class);  
  
     // demo App defined in resources/jpush-api.conf   
    //给价格预约
    public static final String TITLE = "订单状态提醒";  
    public static final String TITLE1 = "成员申请";  
    public static final String TITLE2 = "审核消息";  
    public static final String ALERT1 = "服务商已支付定金";  //
    public static final String ALERT2 = "合作社已支付定金";  //
    public static final String ALERT3 = "请向合作社或农户获取完成码";  //
    public static final String ALERT4 = "服务商确认完成，请尽快支付余款";  //
    public static final String ALERT5 = "合租社或农户已支付余款";  //
    public static final String ALERT6 = "合作社取消订单";  //
    public static final String ALERT7 = "服务商取消订单";  //
    public static final String ALERT8 = "双方都已支付定金";  //
    public static final String ALERT9 = "您有一条新成员的申请消息";  
    public static final String ALERT10 = "审核通过";  
    public static final String ALERT11 = "审核未通过";  
    
    //极光账号
    public static final String appKey = "6288ef42f369bdca488d42f4";
    public static final String masterSecret = "45090754d340f58b7fad366f";  
    
    public  static JPushClient jpushClient=null;  
    
    public static void ddgl_send2HZS(String ordercode ,String type,String Userphone,String usercode) {
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	jpushClient = new JPushClient(masterSecret, appKey, 3);
        // 安卓客户端
    	PushPayload payload = null;
    	if("1".equals(type)){
    		payload=buildPushObject_send2HZS1(ordercode,Userphone, usercode);  
    	}else if("2".equals(type)){
    		payload=buildPushObject_send2HZS2(ordercode,Userphone, usercode);  
    	}else if("3".equals(type)){
    		payload=buildPushObject_send2HZS3(ordercode,Userphone, usercode);  
    	}else if("4".equals(type)){
    		payload=buildPushObject_send2HZS4(ordercode,Userphone, usercode);  
    	}else if("5".equals(type)){
    		payload=buildPushObject_send2HZS5(ordercode,Userphone, usercode);  
    	}else if("6".equals(type)){
    		payload=buildPushObject_send2HZS6(ordercode,Userphone, usercode);  
    	}else if("7".equals(type)){
    		payload=buildPushObject_send2HZS7(ordercode,Userphone, usercode);  
    	}else if("8".equals(type)){
    		payload=buildPushObject_send2HZS8(ordercode,Userphone, usercode);  
    	}
        try {  
            System.out.println(payload.toString());  
            PushResult result = jpushClient.sendPush(payload);  
            System.out.println(result+"................................");  
              
            LOG.info("Got result - " + result);  
              
        } catch (APIConnectionException e) {  
            LOG.error("Connection error. Should retry later. ", e);  
              
        } catch (APIRequestException e) {  
            LOG.error("Error response from JPush server. Should review and fix it. ", e);  
            LOG.info("HTTP Status: " + e.getStatus());  
            LOG.info("Error Code: " + e.getErrorCode());  
            LOG.info("Error Message: " + e.getErrorMessage());  
            LOG.info("Msg ID: " + e.getMsgId());  
        }
    }

    public static void ddgl_send2HZS1(String type,String Userphone) {
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	jpushClient = new JPushClient(masterSecret, appKey, 3);
    	// 安卓客户端
    	PushPayload payload = null;
    	if("9".equals(type)){
    		payload=buildPushObject_send2HZS9(Userphone);  
    	}else if("10".equals(type)){
    		payload=buildPushObject_send2HZS10(Userphone);  
    	}else if("11".equals(type)){
    		payload=buildPushObject_send2HZS11(Userphone);  
    	}
    	try {  
    		System.out.println(payload.toString());  
    		PushResult result = jpushClient.sendPush(payload);  
    		System.out.println(result+"................................");  
    		
    		LOG.info("Got result - " + result);  
    		
    	} catch (APIConnectionException e) {  
    		LOG.error("Connection error. Should retry later. ", e);  
    		
    	} catch (APIRequestException e) {  
    		LOG.error("Error response from JPush server. Should review and fix it. ", e);  
    		LOG.info("HTTP Status: " + e.getStatus());  
    		LOG.info("Error Code: " + e.getErrorCode());  
    		LOG.info("Error Message: " + e.getErrorMessage());  
    		LOG.info("Msg ID: " + e.getMsgId());  
    	}
    }

    public static void ddgl_send2HZS3(String type,String Userphone) {
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	jpushClient = new JPushClient(masterSecret, appKey, 3);
    	// 安卓客户端
    	PushPayload payload = null;
    	if("9".equals(type)){
    		payload=buildPushObject_send2HZS9(Userphone);  
    	}else if("10".equals(type)){
    		payload=buildPushObject_send2HZS10(Userphone);  
    	}else if("11".equals(type)){
    		payload=buildPushObject_send2HZS11(Userphone);  
    	}
    	try {  
    		System.out.println(payload.toString());  
    		PushResult result = jpushClient.sendPush(payload);  
    		System.out.println(result+"................................");  
    		
    		LOG.info("Got result - " + result);  
    		
    	} catch (APIConnectionException e) {  
    		LOG.error("Connection error. Should retry later. ", e);  
    	} catch (APIRequestException e) {  
    		LOG.error("Error response from JPush server. Should review and fix it. ", e);  
    		LOG.info("HTTP Status: " + e.getStatus());  
    		LOG.info("Error Code: " + e.getErrorCode());  
    		LOG.info("Error Message: " + e.getErrorMessage());  
    		LOG.info("Msg ID: " + e.getMsgId());  
    	}
    }
    
    //发送给合作社
    public static PushPayload buildPushObject_send2HZS1(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
        return PushPayload.newBuilder()  
                .setPlatform(Platform.android())  
                //.setAudience(Audience.all())
                .setAudience(Audience.newBuilder()  
                        .addAudienceTarget(AudienceTarget.alias(Userphone)) 
                        .build())
                .setNotification(Notification.newBuilder()  
                        .setAlert(ALERT1)  
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
                        .build()) 
                .build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS2(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT2)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS3(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT3)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS4(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT4)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS5(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT5)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS6(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT6)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS7(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT7)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS8(String ordercode,String Userphone,String usercode) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT8)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE).addExtra("type", "4").addExtra("ordercode",ordercode).addExtra("usercode",usercode).build())
    							.build()) 
    							.build();  
    }  
    
    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS9(String Userphone) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT9)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE1).addExtra("type", "4").build())
    							.build()) 
    							.build();  
    }  
    
    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS10(String Userphone) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT10)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE2).addExtra("type", "4").build())
    							.build()) 
    							.build();  
    }  
    
    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS11(String Userphone) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT11)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE2).addExtra("type", "4").build())
    							.build()) 
    							.build();  
    }  

    //发送给农机服务商
    public static PushPayload buildPushObject_send2HZS12(String Userphone) {  
    	//System.out.println(ordercode+"................................"+Usercode+"................................"+Userphone); 
    	return PushPayload.newBuilder()  
    			.setPlatform(Platform.android())  
    			//.setAudience(Audience.all())
    			.setAudience(Audience.newBuilder()  
    					.addAudienceTarget(AudienceTarget.alias(Userphone)) 
    					.build())
    					.setNotification(Notification.newBuilder()  
    							.setAlert(ALERT11)  
    							.addPlatformNotification(AndroidNotification.newBuilder().setTitle(TITLE2).addExtra("type", "4").build())
    							.build()) 
    							.build();  
    }  
    
  
	public static void main(String[] args) {
		System.out.println("*************");
		//testSendPush("6288ef42f369bdca488d42f4","45090754d340f58b7fad366f");
		ddgl_send2HZS("e6aeb3e56dbf48a1b8f9fbadce0cecd1","1","11111111111","");
	}
}  
