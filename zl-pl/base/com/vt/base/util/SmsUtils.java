package com.vt.base.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 短信发送工具类
 *
 * @author zhangtao
 * @version 1.0
 */
public class SmsUtils {
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(SmsUtils.class);
    
    public static void main(String[] args) {
		try {
			sendMsg("13910664411","Java Http方式短信调试已经成功!!!!!","");
			sendMsg("15210088045","Java Http方式短信调试已经成功!!!!!","");
//			sendMsg("18612084620","Java Http方式短信调试已经成功!!!!!","");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

    /**
     * 加密密码
     *
     * @param timestamp
     * @param password
     * @return
     */
    public static final String encodePassword(String timestamp, String password) {
        String result = DigestUtils.encode(password + "_" + timestamp + "_topsky");
        return result;
    }

    /**
     * 获取Unix时间戳
     *
     * @return
     */
    public static final String getCurrentTimeStamp() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        if (timestamp != null && timestamp.length() >= 10) {
            return timestamp.substring(0, 10);
        }
        return timestamp;
    }

    /**
     * 判断给定的号码是否为手机号
     *
     * @param phoneNum
     * @return
     */
    public static boolean isMobile(String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            return false;
        }
        phoneNum = StringUtils.trim(phoneNum);
        int len = 11;
        if (phoneNum.length() != len) {
            return false;
        }
        return true;
    }

    /**
     * 发送短信
     *
     * @param mobile
     * @param message
     * @return
     * @throws UnsupportedEncodingException 
     * @throws MalformedURLException 
     */
    public static final int sendMsg(String Mobile,String Content,String send_time) throws UnsupportedEncodingException, MalformedURLException{
    	URL url = null;
		String CorpID="swdzx00456";//账户名
		String Pwd="zhng456angmy";//密码
		String send_content=URLEncoder.encode(Content.replaceAll("<br/>", " "), "GBK");//发送内容
		url = new URL("http://qxt.tongxun3g.cn/WS/Send.aspx?CorpID="+CorpID+"&Pwd="+Pwd+"&Mobile="+Mobile+"&Content="+send_content+"&Cell=&SendTime="+send_time);
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ："+Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine=-9;
		}
		System.out.println("结束发送短信返回值：  "+inputLine);
		return inputLine;
    }
}
