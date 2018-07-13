package com.vt.base.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.client.ParamMapper;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.ISysParamService;

/**
 * @author july
 * @version V1.0
 * @Title: SysParamServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date Jun 3, 2015 2:59:05 PM
 */
@Service(IServiceDef.SYS_PARAM_SERVICE)
public class SysParamServiceImpl extends BaseService<Param, ParamExample, String> implements ISysParamService {

    /**
     *
     */
    private static final long serialVersionUID = -6525870141883165165L;
    @Autowired
    private SqlSession sqlSession;

    private ParamMapper paramMapper;

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
        paramMapper = sqlSession.getMapper(ParamMapper.class);
        logger.info("system param Service init successfully.");
    }

    @Override
    public IBaseMapper<Param, ParamExample, String> getMapper() {
        return paramMapper;
    }

    @Override
    @CacheEvict(value = "serviceCache", key = "'sysParam'")
    public OptResult create(Param entity) {
        //allEntries=true
        return super.create(entity);
    }

    @Override
    @CacheEvict(value = "serviceCache", key = "'sysParam'")
    public OptResult update(Param entity) {
        return super.update(entity);
    }

    @Override
    @CacheEvict(value = "serviceCache", key = "'sysParam'")
    public OptResult remove(String entityId) {
        return super.remove(entityId);
    }

    @CacheEvict(value = "serviceCache", key = "'sysParam'")
    public OptResult delete(String entityId) {
        return super.remove(entityId);
    }

    @Override
    @Cacheable(value = "serviceCache", key = "'sysParam'")
    public Param getById(String entityId) {
        return super.getById(entityId);
    }

    @Override
    /*@Cacheable(value="serviceCache",key="'sysParam'" )*/
    public PageData<Param> query(PageRequest<ParamExample> condition) {
        return super.query(condition);
    }

    @Override
    @Cacheable(value = "serviceCache", key = "'sysParam'")
    public List<Param> getResult(ParamExample condition) {
        return super.getResult(condition);
    }

    @Override
    public int getResultCount(ParamExample condition) {
        return super.getResultCount(condition);
    }

    @Override
    public String getParamValueByParamName(String paramName) {
        Param param = paramMapper.selectByPrimaryKey(paramName);
        if (null != param && null != param.getParamValue()) {
            return param.getParamValue();
        } else {
            return null;
        }
    }

    @Override
    public void updateParamValueByPrimaryKey(Param param) {
        paramMapper.updateParamValueByPrimaryKey(param);
    }

    @Override
    public int insert(Param param) {
        return paramMapper.insert(param);
    }

	@Override
	public void updateParamBatch(List<Param> list) {
		paramMapper.updateParamBatch(list);
	}

}
