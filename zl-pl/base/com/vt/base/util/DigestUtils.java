package com.vt.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * <h1>加密工具类</h1>
 * User: zhangtao
 * Date: 13-11-23
 * Time: 上午9:49
 */
public abstract class DigestUtils {
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(DigestUtils.class);

    /**
     * 执行MD5加密
     *
     * @param source
     * @return
     */
    public static String encode(String source) {
        if (StringUtils.isBlank(source)) {
            return StringUtils.EMPTY;
        }
        source = StringUtils.trim(source);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes("UTF-8"));
            byte[] encoded = md.digest();
            int i;
            StringBuilder buf = new StringBuilder();
            for (int offset = 0; offset < encoded.length; offset++) {
                i = encoded[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("执行MD5加密失败!", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("执行MD5加密失败!", e);
        }
        return null;
    }

    /**
     * 编码为URL参数
     *
     * @param param
     * @return
     */
    public static String encodeAsURLParam(String param) {
        return encodeAsURLParam(param, "UTF-8");
    }

    /**
     * 解码URL参数
     *
     * @param param
     * @return
     */
    public static String decodeURLParam(String param) {
        if (StringUtils.isBlank(param)) {
            return param;
        }
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return param;
        }
    }

    /**
     * 使用指定字符集编码URL参数
     *
     * @param param
     * @param encoding
     * @return
     */
    public static String encodeAsURLParam(String param, String encoding) {
        if (StringUtils.isBlank(param) || StringUtils.isBlank(encoding)) {
            return param;
        }
        try {
            return URLEncoder.encode(param, encoding);
        } catch (UnsupportedEncodingException e) {
            return param;
        }
    }

    /**
     * 生成随机数字串
     * @param length
     * @return
     */
    public static String generateRandomNumber(int length) {
        if (length <=0) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RandomUtils.nextInt(10));
        }
        return sb.toString();
    }
}
