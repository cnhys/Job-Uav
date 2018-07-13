package com.vt.base.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.client.SqlMapper;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.ISqlService;

/**
 * @author
 * @version V1.0
 * @Title: SqlServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date Jun 3, 2015 2:59:05 PM
 */
@Service(IServiceDef.SQL_SERVICE)
public class SqlServiceImpl extends BaseService<Param, ParamExample, String> implements ISqlService {

    private static final long serialVersionUID = -6525870141883165165L;
    @Autowired
    private SqlSession sqlSession;

    private SqlMapper sqlMapper;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        super.init();
        sqlMapper = sqlSession.getMapper(SqlMapper.class);
        logger.info("Sql Service init successfully.");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IBaseMapper getMapper() {
        return sqlMapper;
    }

    /**
     * 执行指定的sql语句
     *
     * @param value
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Override
    public PageData<Map> getBySql(PageRequest<String> pageRequest) {
        // 构建查询条件
        RowBounds bounds = new RowBounds(pageRequest.getStart(), pageRequest.getLimit());
        List<Map> list = sqlMapper.getBySql(pageRequest.getCondition(), bounds);
        int count = sqlMapper.getBySqlCount(pageRequest.getCondition());
        // 组装分页数据
        PageData<Map> result = new PageData<Map>();
        result.setTotal(count);
        result.setStart(pageRequest.getStart());
        result.setLimit(pageRequest.getLimit());
        result.setData(list);
        result.setSuccess(true);
        return result;
    }

    public void insertBySql(String sql) {
        sqlMapper.insertBySql(sql);
    }

    public void updateBySql(String sql) {
        sqlMapper.updateBySql(sql);
    }

    public void deleteBySql(String sql) {
        sqlMapper.deleteBySql(sql);
    }
}
