package com.vt.base.exception;

import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * <p> Title: 报文处理异常类 </p>
 * <p> Description: </p>
 *
 * @作者:白松涛 Devin_Bai@infosys.com
 * @创建时间:2014-10-5上午11:21:58
 * @版本:1.00
 * @修改记录 <pre>
 * 版本	修改人	修改时间		描述
 * ----------------------------------------
 * 1.00	白松涛	2014-10-5	初始版本
 * ----------------------------------------
 * </pre>
 */
public class MsgException extends Exception {

    /**
     * 报文处理异常类
     */
    private static final long serialVersionUID = -5864597983978213541L;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误描述
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
     * 使用错误码构建报文异常
     *
     * @param errorCode
     */
    public MsgException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = ERROR_DEF.getProperty(errorCode);
    }

    /**
     * 构建报文异常
     *
     * @param errorCode 错误码
     * @param errorMsg  错误描述
     * @param params    参数
     */
    public MsgException(String errorCode, Object[] params) {
        this.errorCode = errorCode;
        this.errorMessage = ERROR_DEF.getProperty(errorCode);
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                this.errorMessage = errorMessage.replaceFirst("\\{" + i + "{1}\\}", params[i].toString());
            }
        }
    }

    /**
     * 构造方法
     *
     * @param errorCode
     * @param errorMsg
     */
    public MsgException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


    /**
     * 构造
     *
     * @param errorCode 错误码
     * @param errorMsg  错误描述
     * @param params    参数
     */
    public MsgException(String errorCode, String errorMessage, Object[] params) {
        this.errorCode = errorCode;
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                this.errorMessage = errorMessage.replaceFirst("\\{" + i + "{1}\\}", params[i].toString());
            }
        } else {
            this.errorMessage = errorMessage;
        }
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
