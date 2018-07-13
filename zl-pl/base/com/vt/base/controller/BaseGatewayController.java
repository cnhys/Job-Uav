package com.vt.base.controller;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.vt.base.exception.BizException;
import com.vt.base.util.BeanUtil;
import com.vt.base.util.JsonUtil;

/**
 * gateway基础控制器
 * Created by zhangtao on 15/12/30.
 */
public class BaseGatewayController extends BaseRestController {
    private static final long serialVersionUID = -145902710412250688L;
    /**
     * 密钥配置
     */
    private Properties secret;
    /**
     * logger
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * init
     */
    @PostConstruct
    public void init() {
        secret = new Properties();
        InputStream is = null;
        try {
            is = new ClassPathResource("secret.properties").getInputStream();
            secret.load(is);
            logger.debug("加载密钥文件成功!");
        } catch (Exception e) {
            logger.error("加载密钥文件失败!", e);
            throw new IllegalStateException("加载密钥文件失败!", e);
        } finally {
            if (is != null) {
                IOUtils.closeQuietly(is);
            }
        }
    }
    /**
     * 渠道和密钥检查
     * @param channel
     * @param key
     */
    protected void channelKeyCheck(String channel, String key) {
        if (StringUtils.isEmpty(channel)) {
            reject("common.channel.empty");
        }
        if (StringUtils.isEmpty(key)) {
            reject("common.key.empty");
        }
        String localKey = secret.getProperty(channel);
        if (StringUtils.isEmpty(localKey)) {
            reject("common.channel.not.exists");
        }
        if (!StringUtils.equals(localKey, key)) {
            reject("common.channel.key.mismatch");
        }
    }

    /**
     * 拒绝交易
     * @param errorCode
     */
    protected void reject(String errorCode) {
        throw new BizException(errorCode);
    }

    /**
     * <h1>如果参数为空,则拒绝交易</h1>
     * <p>
     *     错误码格式:errorCodePrefix + "." + propertyName + ".empty"
     * </p>
     * @param source
     * @param errorCodePrefix
     * @param propertyNames
     */
    protected void rejectIfEmpty(String errorCodePrefix, Object source, String... propertyNames) {
        if (source == null) {
            return;
        }
        if (propertyNames == null || propertyNames.length == 0) {
            return;
        }
        if (StringUtils.isEmpty(errorCodePrefix)) {
            return;
        }
        for (String propertyName : propertyNames) {
            Object value = BeanUtil.getValue(source, propertyName);
            if (value == null) {
                reject(errorCodePrefix + "." + propertyName + ".empty");
            }
            if (value instanceof String) {
                String str = (String)value;
                if (StringUtils.isEmpty(str)) {
                    reject(errorCodePrefix + "." + propertyName + ".empty");
                }
            }
        }
    }

    /**
     * <h1>如果参数为NULL,则拒绝交易</h1>
     * <p>
     *     错误码格式:errorCodePrefix + "." + propertyName + ".null"
     * </p>
     * @param errorCodePrefix
     * @param source
     * @param propertyNames
     */
    protected void rejectIfNull(String errorCodePrefix, Object source, String... propertyNames) {
        if (source == null) {
            return;
        }
        if (propertyNames == null || propertyNames.length == 0) {
            return;
        }
        for (String propertyName : propertyNames) {
            Object value = BeanUtil.getValue(source, propertyName);
            if (value == null) {
                reject(errorCodePrefix + "." + propertyName + ".null");
            }
        }
    }

    /**
     * 将JSON数据转换为模型对象
     * @param data
     * @param clz
     * @param <T>
     * @return
     */
    protected <T> T convert(String data, Class<T> clz) {
        return JsonUtil.fromJson(data, clz);
    }
}
