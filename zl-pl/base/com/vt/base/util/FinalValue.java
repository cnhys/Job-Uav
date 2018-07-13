package com.vt.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import freemarker.template.Configuration;


public class FinalValue {
	
	/**
	 * 读取app-config.properties配置文件
	 */
	private static Properties prop_app_config = getProperties("app-config.properties");
	/**
	 * 极光推送AppKey
	 */
	public static final String JPUSH_APPKEY=prop_app_config.getProperty("Jpush_AppKey");
	/**
	 * 极光推送Jpush_Master_Secret
	 */
	public static final String JPUSH_MASTER_SECRET=prop_app_config.getProperty("Jpush_Master_Secret");
	
	/**
	 * 极光推送AppKey
	 */
	public static final String Android_JPUSH_APPKEY=prop_app_config.getProperty("Android_Jpush_AppKey");
	/**
	 * 极光推送Jpush_Master_Secret
	 */
	public static final String Android_JPUSH_MASTER_SECRET=prop_app_config.getProperty("Android_Jpush_Master_Secret");
	
	/**
	 * 极光推送AppKey
	 */
	public static final String PATIENTNUMBER_JPUSH_APPKEY=prop_app_config.getProperty("PatientNumber_Jpush_AppKey");
	/**
	 * 极光推送Jpush_Master_Secret
	 */
	public static final String PATIENTNUMBER_JPUSH_MASTER_SECRET=prop_app_config.getProperty("PatientNumber_Jpush_Master_Secret");
	
	/**
	 * 获取加载后的Properties对象
	 * @param propertiesName 需要加载的文件名称
	 * @return Properties对象
	 */
	private static Properties getProperties(String propertiesName){
		InputStream inStream = Configuration.class.getClassLoader().getResourceAsStream(propertiesName);
		Properties properties = new Properties();
		try {
			properties.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
