package com.vt.base.util;

import org.apache.commons.lang3.StringUtils;

import com.vt.IConst;

/**
 * <p> Title: 字符串处理工具类 </p>
 * <p> Description: </p>
 *
 * @Author:Devin Bai
 * @Time:Jun 30, 20152:42:00 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 30, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class StringUtil extends StringUtils {

    /**
     * 拼装数据库like查询条件
     *
     * @param source 要拼装字符串
     * @return
     */
    public static String dbQueryLike(String source) {
        return "%" + source + "%";
    }

    /**
     * 空字符默认处理
     *
     * @param str
     * @return
     */
    public static String defaultNull(String str) {
        if (isEmpty(str)) {
            return IConst.BLANK;
        }

        return str;
    }
}
