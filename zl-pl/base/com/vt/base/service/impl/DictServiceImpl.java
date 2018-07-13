package com.vt.base.service.impl;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.client.DictTypeMapper;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.DictType;
import com.vt.base.model.DictTypeExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.IDictService;

/**
 * @author july
 * @version V1.0
 * @Title: DictServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date May 29, 2015 9:51:46 AM
 */
@Service(IServiceDef.DICT_TYPE_SERVICE)
public class DictServiceImpl extends BaseService<DictType, DictTypeExample, Integer> implements IDictService {

    /**
     *
     */
    private static final long serialVersionUID = -8728363247648850876L;
    @Autowired
    private SqlSession sqlSession;

    private DictTypeMapper dictMapper;

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
        dictMapper = sqlSession.getMapper(DictTypeMapper.class);
        logger.info("dict Service init successfully.");
    }

    @Override
    public IBaseMapper<DictType, DictTypeExample, Integer> getMapper() {
        return dictMapper;
    }

    /**
     * 更新字典类型
     */
    @Override
    public OptResult updateDictTypeByDictId(DictType dictType) {
        if (dictType == null) {
            throw new BizException("systemmanage.dictType.update.null");
        }
        if (dictType.getTypeId() == null) {
            throw new BizException("systemmanage.dictType.update.id.null");
        }
        return update(dictType);
    }

    /**
     * 根据代码更新字典类型
     */
    @Override
    public void updateDictTypeByCode(DictType dictType) {
        dictMapper.updateDictTypeByCode(dictType);
    }

}
