/**
 *
 */
package com.vt.base.exception;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * <h1>业务异常定义</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -1396220406965924938L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误消息
     */
    private String errorMessage;
    /**
     * 错误定义
     */
    private static Properties ERROR_DEF = null;

    /**
     * 静态加载
     */
    static {
        try {
            ERROR_DEF = PropertiesLoaderUtils.loadProperties(new ClassPathResource("error-code.properties"));
        } catch (Exception e) {
            ERROR_DEF = new Properties();
        }
    }

    /**
     * 使用错误码构建业务异常
     *
     * @param errorCode 错误码
     */
    public BizException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = ERROR_DEF.getProperty(errorCode);
        if (StringUtils.isBlank(errorMessage)) {
            this.errorMessage = errorCode;
        }
    }

    /**
     * 使用错误码和错误消息构建业务异常
     *
     * @param errorCode
     * @param errorMessage
     */
    public BizException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
