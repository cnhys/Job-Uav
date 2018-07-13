package com.vt.base.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vt.base.OptResult;
import com.vt.base.model.DictRelExample;
import com.vt.base.model.DictRelKey;

public interface IDictRelService extends IBaseService<DictRelKey, DictRelExample, Integer> {
    public String queryDictRelIdByItemId(int itemId);

    /**
     * 批量插入关联字典
     *
     * @param iId
     * @param itemLike
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public OptResult insertDictItemIdRes(String iId, String itemLike);
}