//package com.vt.base.util;
//
//import java.util.List;
//
//import org.slf4j.LoggerFactory;
//
//import cn.jpush.api.JPushClient;
//import cn.jpush.api.common.ClientConfig;
//import cn.jpush.api.common.resp.APIConnectionException;
//import cn.jpush.api.common.resp.APIRequestException;
//import cn.jpush.api.push.PushResult;
//import cn.jpush.api.push.model.Message;
//import cn.jpush.api.push.model.Options;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.jpush.api.push.model.audience.AudienceTarget;
//import cn.jpush.api.push.model.notification.IosNotification;
//import cn.jpush.api.push.model.notification.Notification;
//
//public class JdPush {
//
//	protected static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JdPush.class);
//	/*
//	 * private static final String ALERT = "你好！"; private static final String
//	 * TITLE = "血压预警提示"; private static final String MSG_CONTENT = "";
//	 */
//
//	/**
//	 * 极光推送 使用的是(Android_JPUSH_APPKEY)
//	 * @return
//	 */
//	public String sendPush(PushPayload payload) {
//
//		JPushClient jpushClient = new JPushClient(FinalValue.Android_JPUSH_MASTER_SECRET,
//				FinalValue.Android_JPUSH_APPKEY, 0, null, ClientConfig.getInstance());
//		PushResult result = null;
//		try {
//			result = jpushClient.sendPush(payload);
//		} catch (APIConnectionException e) {
//			e.printStackTrace();
//		} catch (APIRequestException e) {
//			e.printStackTrace();
//		}
//		return "极光推送结果：" + result;
//	}
//	
//	/**
//	 * 极光推送 使用的是(PATIENTNUMBER_JPUSH_APPKEY)
//	 * @return
//	 */
//	public String sendPushPatientNumber(PushPayload payload) {
//
//		JPushClient jpushClient = new JPushClient(FinalValue.PATIENTNUMBER_JPUSH_MASTER_SECRET,
//				FinalValue.PATIENTNUMBER_JPUSH_APPKEY, 0, null, ClientConfig.getInstance());
//		PushResult result = null;
//		try {
//			result = jpushClient.sendPush(payload);
//		} catch (APIConnectionException e) {
//			e.printStackTrace();
//		} catch (APIRequestException e) {
//			e.printStackTrace();
//		}
//		return "极光推送结果：" + result;
//	}
//
//	/**
//	 * 进行推送的关键在于构建一个 PushPayload 对象。以下示例一般的构建对象的用法。 快捷地构建推送对象：所有平台，所有设备，内容为
//	 * ALERT 的通知。
//	 * @param alert
//	 * @return
//	 */
//	public static PushPayload buildPushObject_all_all_alert(String alert) {
//		return PushPayload.alertAll(alert);
//	}
//
//	/**
//	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
//	 * @param aliasId
//	 * @param alert
//	 * @return
//	 */
//	public static PushPayload buildPushObject_all_alias_alert(String aliasId, String alert) {
//		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(aliasId))
//				.setNotification(Notification.alert(alert)).build();
//	}
//	
//	/**
//	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
//	 * @param aliasId
//	 * @param alert
//	 * @return
//	 */
//	public static PushPayload buildPushObject_all_alias_alert(String alert,String msg_content,String...aliasId) {
//		return PushPayload.newBuilder()
//				.setPlatform(Platform.all())
//				.setAudience(Audience.alias(aliasId))
//				.setMessage(Message.content(msg_content))
//				.setNotification(Notification.alert(alert)).build();
//	}
//	
//	/**
//	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
//	 * @param aliasId
//	 * @param alert
//	 * @return
//	 */
//	public static PushPayload buildPushObject_all_alias_alert(String alert,String msg_content, List aliasId) {
//		return PushPayload.newBuilder()
//				.setPlatform(Platform.all())
//				.setAudience(Audience.alias(aliasId))
//				.setMessage(Message.content(msg_content))
//				.setNotification(Notification.alert(alert)).build();
//	}
//	
//	public static void main(String[] args) {
//		JdPush jdPush=new JdPush();
//		PushPayload pm_payload = JdPush.buildPushObject_all_alias_alert("邀请你进行会诊！", "会诊邀请提示", "18312345678");
//		String jdpush = jdPush.sendPush(pm_payload);
//		System.out.println("患者端推送结果："+jdpush);
//	}
//
//	/**
//	 * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
//	 * @param tagId
//	 * @param alert
//	 * @param title
//	 * @return
//	 */
//	public static PushPayload buildPushObject_android_tag_alertWithTitle(String tagId, String alert, String title) {
//		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag(tagId))
//				.setNotification(Notification.android(alert, title, null)).build();
//	}
//
//	/**
//	 * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 -
//	 * 通知信息是ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是
//	 * MSG_CONTENT。通知是APNs 推送通道的，消息是 JPush 应用内消息通道的。APNs
//	 * 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
//	 * @param tagId
//	 * @param tagId_all
//	 * @param sound
//	 * @param from
//	 * @param JPush
//	 * @param msg_content
//	 * @param alert
//	 * @param isBuild
//	 * @return
//	 */
//	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String tagId, String tagId_all,
//			String sound, String from, String JPush, String msg_content, String alert, boolean isBuild) {
//		return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.tag_and(tagId, tagId_all))
//				.setNotification(Notification.newBuilder()
//						.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).setBadge(5)
//						.setSound(sound).addExtra(from, JPush).build())
//						.build())
//				.setMessage(Message.content(msg_content))
//				.setOptions(Options.newBuilder().setApnsProduction(isBuild).build()).build();
//	}
//
//	/**
//	 * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）交（"alias1" 与 "alias2"
//	 * 的并集），推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
//	 * @param tag1
//	 * @param tag2
//	 * @param alias1
//	 * @param alias2
//	 * @param msg_content
//	 * @param from
//	 * @param JPush
//	 * @return
//	 */
//	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String tag1, String tag2,
//			String alias1, String alias2, String msg_content, String from, String JPush) {
//		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
//				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag(tag1, tag2))
//						.addAudienceTarget(AudienceTarget.alias(alias1, alias2)).build())
//				.setMessage(Message.newBuilder().setMsgContent(msg_content).addExtra(from, JPush).build()).build();
//	}
//
//	/**
//	 * 构建推送对象：推送内容包含SMS信息
//	 */
//	public static void testSendWithSMS() {
//		JPushClient jpushClient = new JPushClient(FinalValue.Android_JPUSH_MASTER_SECRET,
//				FinalValue.Android_JPUSH_APPKEY, 0, null, ClientConfig.getInstance());
////		try {
////			SMS sms = SMS.content("Test SMS", 10);
////			PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
////			LOG.info("Got result - " + result);
////		} catch (APIConnectionException e) {
////			LOG.error("Connection error. Should retry later. ", e);
////		} catch (APIRequestException e) {
////			LOG.error("Error response from JPush server. Should review and fix it. ", e);
////			LOG.info("HTTP Status: " + e.getStatus());
////			LOG.info("Error Code: " + e.getErrorCode());
////			LOG.info("Error Message: " + e.getErrorMessage());
////		}
//	}
//
//}
