package com.vt.base.service.impl;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.client.PrimaryKeyTableMapper;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.PrimaryKeyTable;
import com.vt.base.model.PrimaryKeyTableExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;

/**
 * @author july
 * @version V1.0
 * @Title: DBUtilServiceImpl.java
 * @Package com.infosys.base.service.impl
 * @Description: TODO
 * @date May 28, 2015 11:44:10 AM
 */

@Service(IServiceDef.DBUTIL_SERVICE)
public class DBUtilServiceImpl extends BaseService<PrimaryKeyTable, PrimaryKeyTableExample, Integer> implements IDBUtilService {

    /**
     *
     */
    private static final long serialVersionUID = -38466810305631224L;

    /**
     * 主键集合
     */
    private static ConcurrentHashMap<String, PrimaryKeyTable> cacheMap = new ConcurrentHashMap<String, PrimaryKeyTable>();
    /**
     * 缓存大小
     */
    private int cacheSize = 50;
    /**
     * 默认值
     */
    private String defaultValue = "1";
    /**
     * sql session
     */
    @Autowired
    private SqlSession sqlSession;
    /**
     * mapper
     */
    private PrimaryKeyTableMapper mapper;
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * init
     */
    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(PrimaryKeyTableMapper.class);
        logger.info("DictType Service init successfully.");
    }

    @Override
    public IBaseMapper<PrimaryKeyTable, PrimaryKeyTableExample, Integer> getMapper() {
        return mapper;
    }

    public Long getPrimaryKey(Object obj) {

        String clsFullName = obj.getClass().getName();
        String tableName = StringUtils.substringAfterLast(clsFullName, ".");
        PrimaryKeyTable pkStyTab = cacheMap.get(tableName);
        if (null == pkStyTab) {
            PrimaryKeyTable pk = this.queryCacheEmptyTableDBValue(tableName);
            cacheMap.put(tableName, pk);
            return Long.valueOf(pk.getCurrentValue());
        } else {
            Long value = Long.valueOf(pkStyTab.getCurrentValue()) + 1;
            if (value > Long.valueOf(pkStyTab.getPkValue())) {
                pkStyTab.setCurrentValue(String.valueOf(value));
                pkStyTab.setPkValue(String.valueOf(value + cacheSize));
                mapper.updateByPrimaryKeySelective(pkStyTab);
            } else {
                pkStyTab.setCurrentValue(String.valueOf(value));
            }
            cacheMap.put(tableName, pkStyTab);
            return value;
        }
    }

    /**
     * 获取数据库最新值，有则更新并返回，无则插入数据并返回默认值
     */
    public PrimaryKeyTable queryCacheEmptyTableDBValue(String tableName) {
        PrimaryKeyTable record = new PrimaryKeyTable();
        PrimaryKeyTableExample example = new PrimaryKeyTableExample();
        example.createCriteria().andTableNameEqualTo(tableName);
        List<PrimaryKeyTable> list = mapper.selectByExample(example);
        if (list.size() > 0) {
            System.out.println("CURRENT VALUE:" + list.get(0).getPkValue());
            String currentValue = list.get(0).getPkValue();
            record.setPkValue(String.valueOf(cacheSize + Long.valueOf(currentValue)));
            record.setTableName(tableName);
            record.setCurrentValue(String.valueOf(Integer.valueOf(currentValue) + 1));
            mapper.updateByPrimaryKey(record);
        } else {
            record.setPkValue(String.valueOf(cacheSize));
            record.setTableName(tableName);
            record.setCurrentValue(defaultValue);
            mapper.insert(record);
        }
        return record;
    }

}
