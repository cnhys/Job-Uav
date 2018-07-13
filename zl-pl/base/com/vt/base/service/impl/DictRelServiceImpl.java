package com.vt.base.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.client.DictRelMapper;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.DictRelExample;
import com.vt.base.model.DictRelKey;
import com.vt.base.service.BaseService;
import com.vt.base.service.IDictRelService;


/**
 * <p> @Title:IDictRelServiceImpl </p>
 * <p> @Version: 1.0 </p>
 *
 * @author Jack.cheng
 * @Date 2015-9-8
 */
@Service(IServiceDef.DICT_ITEMREL_SERVICE)
public class DictRelServiceImpl extends BaseService<DictRelKey, DictRelExample, Integer> implements
        IDictRelService {

    private static final long serialVersionUID = 3141438482197309721L;
    @Autowired
    private SqlSession sqlSession;

    private DictRelMapper dictRelMapper;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        super.init();
        dictRelMapper = sqlSession.getMapper(DictRelMapper.class);
        logger.info("dict Service init successfully.");
    }

    @Override
    public IBaseMapper<DictRelKey, DictRelExample, Integer> getMapper() {
        // TODO Auto-generated method stub
        return dictRelMapper;
    }

    /**
     * 查询关联的条目主键，已逗号分隔
     */
    @Override
    public String queryDictRelIdByItemId(int itemId) {
        String result = "";
        DictRelExample dictRelExam = new DictRelExample();
        DictRelExample.Criteria dictCri = dictRelExam.createCriteria();
        dictCri.andItemIdEqualTo(itemId);
        List<DictRelKey> dictRelList = getResult(dictRelExam);
        if (dictRelList != null && dictRelList.size() > 0) {
            for (DictRelKey dict : dictRelList) {
                result = result + dict.getParentId() + ",";
            }
        }
        return result;
    }

    /**
     * 批量插入关联字典信息
     */
    public OptResult insertDictItemIdRes(String iId, String itemLike) {
        DictRelExample dictRelExam = new DictRelExample();
        DictRelExample.Criteria dictCri = dictRelExam.createCriteria();
        dictCri.andItemIdEqualTo(Integer.parseInt(iId));
        OptResult delOptResult = this.delete(dictRelExam);
        if (!delOptResult.isSuccess()) {
            throw new BizException("systemmanage.dictrel.save.null");
        }

        if (StringUtils.isNotBlank(iId) && StringUtils.isNotBlank(itemLike)) {
            String[] strSplit = itemLike.split(",");
            for (int i = 0; i < strSplit.length; i++) {
                DictRelKey dictRel = new DictRelKey();
                dictRel.setItemId(Integer.parseInt(iId));
                dictRel.setParentId(Integer.parseInt(strSplit[i]));
                OptResult saveResult = this.create(dictRel);
                if (!saveResult.isSuccess()) {
                    throw new BizException("systemmanage.dictrel.save.null");
                }
            }
        }
        return new OptResult(true);

    }
}
