package com.vt.fencing.user.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @ClassName: TestProperties   
 * @Description: 获取配置文件信息  
 * @date: 2017年11月25日 上午10:56:00  
 * @version: 1.0.0
 */
public class PropertiesUtils {
	
	 public static void main(String[] args) {
	        System.out.println("*********************************************");
	        // 注意路径问题
	        String properties_3 = getProperties("/config.properties", "pwd");
	        System.out.println("pwd = " + properties_3);
	    }
    
    

    
    /**
     * 根据key读取value
     * 
     * @Title: getProperties   
     *                    相对路径， properties文件需在classpath目录下， 
     *                  比如：config.properties在包com.test.config下， 
     *                  路径就是/com/test/config/config.properties 
     * @param filePath
     * @param keyWord
     * @return
     * @return String  
     * @throws
     */
    public static String getProperties(String filePath, String keyWord){
        Properties prop = new Properties();
        String value = null;
        try {
            InputStream inputStream = PropertiesUtils.class.getResourceAsStream(filePath);
            prop.load(inputStream);
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    
    
    
}