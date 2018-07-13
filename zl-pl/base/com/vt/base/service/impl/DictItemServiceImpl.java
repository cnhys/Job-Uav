package com.vt.base.service.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
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
import com.vt.base.client.DictItemMapper;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.IDictItemService;

/**
 * @author july
 * @version V1.0
 * @Title: DictItemServiceImpl.java
 * @Package com.vt.base.service.impl
 * @Description: TODO
 * @date Jun 3, 2015 11:17:10 AM
 */
@Service(IServiceDef.DICT_ITEM_SERVICE)
public class DictItemServiceImpl extends BaseService<DictItem, DictItemExample, Integer> implements IDictItemService {

    /**
     *
     */
    private static final long serialVersionUID = -3098306364507627087L;
    @Autowired
    private SqlSession sqlSession;

    private DictItemMapper dictItemMapper;

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
        dictItemMapper = sqlSession.getMapper(DictItemMapper.class);
        logger.info("dict Service init successfully.");
    }

    @Override
    public IBaseMapper<DictItem, DictItemExample, Integer> getMapper() {
        return dictItemMapper;
    }

    @Override
    @CacheEvict(value = "serviceCache", allEntries = true)
    public OptResult create(DictItem entity) {
        return super.create(entity);
    }

    @Override
    @CacheEvict(value = "serviceCache", allEntries = true)
    public OptResult update(DictItem entity) {
        return super.update(entity);
    }

    @Override
    @CacheEvict(value = "serviceCache", allEntries = true)
    public OptResult remove(Integer entityId) {
        return super.remove(entityId);
    }

    @Override
    @Cacheable(value = "serviceCache", key = "#entityId +'dictItemGetById'")
    public DictItem getById(Integer entityId) {
        return super.getById(entityId);
    }

    @Override
    public PageData<DictItem> query(PageRequest<DictItemExample> condition) {
        return super.query(condition);
    }

    @Override
    public List<DictItem> getResult(DictItemExample condition) {
        return super.getResult(condition);
    }

    @Override
    public int getResultCount(DictItemExample condition) {
        return super.getResultCount(condition);
    }

    /**
     * 根据字典类型查询字典条目
     */
    @Cacheable(value = "serviceCache", key = "#dictTypeCode +'queryDictItemsByDictTypeCode'")
    public List<DictItem> queryDictItemsByDictTypeCode(String dictTypeCode) {
        if (StringUtils.isBlank(dictTypeCode)) {
            return null;
        }
        DictItemExample example = new DictItemExample();
        example.createCriteria().andDictTypeCodeEqualTo(dictTypeCode);
        return getResult(example);
    }

    /**
     * 根据字典类型、字典值查询子条目
     */
    @Cacheable(value = "serviceCache", key = "#dictTypeCode + #dictItemValue + 'querySubDictItemsByDictTypeCodeAndItemValue'")
    public List<DictItem> querySubDictItemsByDictTypeCodeAndItemValue(String dictTypeCode, String dictItemValue) {
        DictItemExample dictItemExample = new DictItemExample();
        dictItemExample.createCriteria().andDictTypeCodeEqualTo(dictTypeCode).andDictItemValueEqualTo(dictItemValue);
        List<DictItem> items = getResult(dictItemExample);

        if (items != null && items.size() != 0) {
            DictItemExample subDictItemExample = new DictItemExample();
            subDictItemExample.createCriteria().andParentIdEqualTo(items.get(0).getItemId());
            List<DictItem> subItems = getResult(subDictItemExample);
            return subItems;
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 根据字典类型、字典值更新字典条目
     */
    @CacheEvict(value = "serviceCache", allEntries = true)
    public void updateByItemTypeCodeAndVale(DictItem dictItem) {
        dictItemMapper.updateByItemTypeCodeAndVale(dictItem);
    }
}
