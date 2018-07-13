package com.vt.base.exception;

/**
 * <p> Title: 报文重复异常类 </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jul 16, 20158:22:25 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jul 16, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class MsgRepeatException extends MsgException {

    /**
     * 报文重复异常类
     */
    private static final long serialVersionUID = 7112396709683049291L;

    public MsgRepeatException(String errorCode) {
        super(errorCode);
    }

    public MsgRepeatException(String errorCode, Object[] params) {
        super(errorCode, params);
    }

    public MsgRepeatException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public MsgRepeatException(String errorCode, String errorMessage, Object[] params) {
        super(errorCode, errorMessage, params);
    }
}
