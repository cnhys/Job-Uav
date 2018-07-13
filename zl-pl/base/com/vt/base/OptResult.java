package com.vt.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * <h1>操作结果</h1>
 * User: zhangtao
 * Date: 13-11-17
 * Time: 下午9:50
 */
public class OptResult {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private String returnCode;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回的数据项
     */
    private Object data;
    /**
     * 错误码
     */
    private static Properties returnCodes;
    /**
     * Logger
     */
    private static Logger logger = Logger.getLogger(OptResult.class);

    /**
     * 初始化错误码
     */
    static {
        try {
            logger.debug("从类路径加载[error-code.properties]文件...");
            returnCodes = PropertiesLoaderUtils.loadProperties(new ClassPathResource("error-code.properties"));
            logger.info("加载错误码文件成功！");
        } catch (IOException ex) {
            logger.error("从资源文件[error-code.properties]加载错误码文件错误!", ex);
        }
    }

    /**
     * 默认构造
     */
    public OptResult() {
    }

    /**
     * success result
     *
     * @return
     */
    public static OptResult success() {
        return new OptResult(true);
    }

    /**
     * failure result
     *
     * @param returnCode
     * @return
     */
    public static OptResult failure(String returnCode) {
        return new OptResult(false, returnCode);
    }

    /**
     * 单参数构造
     *
     * @param success
     */
    public OptResult(boolean success) {
        this.success = success;
    }

    /**
     * 双参数构造
     *
     * @param success
     * @param returnCode
     */
    public OptResult(boolean success, String returnCode) {
        this.success = success;
        this.returnCode = returnCode;
        if (returnCodes != null) {
            try {
                this.message = returnCodes.getProperty(returnCode, returnCode);
            } catch (Exception e) {
                this.message = returnCode;
            }

        }
        if(success == true){
        	this.returnCode = "0000";
        	this.message ="成功";
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        if (returnCode != null) {
            this.message =returnCodes.getProperty(returnCode);
        }
        if (StringUtils.isBlank(this.message)) {
            this.message = this.returnCode;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptResult)) return false;

        OptResult optResult = (OptResult) o;

        if (success != optResult.success) return false;
        if (returnCode != null ? !returnCode.equals(optResult.returnCode) : optResult.returnCode != null) return false;
        if (message != null ? !message.equals(optResult.message) : optResult.message != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (returnCode != null ? returnCode.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
    	if(data == null){
    		data = new HashMap<Object, Object>();
    	}
        this.data = data;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
