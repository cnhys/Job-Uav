package com.vt.base.service.impl;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.client.LoggingMapper;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.Logging;
import com.vt.base.model.LoggingExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.ISysLogService;

/**
 * @author july
 * @version V1.0
 * @Title: SysLogServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date Jun 2, 2015 3:40:40 PM
 */
@Service(IServiceDef.SYSLOG_SERVICE)
public class SysLogServiceImpl extends BaseService<Logging, LoggingExample, Integer> implements ISysLogService {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 6705780312618391529L;
    @Autowired
    private SqlSession sqlSession;

    private LoggingMapper loggingMapper;

    /**
     * 主键生成服务
     */
    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        super.init();
        loggingMapper = sqlSession.getMapper(LoggingMapper.class);
        logger.info("system logging Service init successfully.");
    }

    @Override
    public void saveLog(Logging log) {
        create(log);
    }

    @Override
    public IBaseMapper<Logging, LoggingExample, Integer> getMapper() {
        return loggingMapper;
    }


}
