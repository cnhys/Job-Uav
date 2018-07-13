package com.vt.base.service;

import com.vt.base.model.Logging;
import com.vt.base.model.LoggingExample;

/**
 * @author july
 * @version V1.0
 * @Title: ISysLogService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date Jun 2, 2015 3:40:16 PM
 */

public interface ISysLogService extends IBaseService<Logging, LoggingExample, Integer> {
    /**
     * 保存资源数据
     *
     * @param resource
     */
    public void saveLog(Logging log);
}
