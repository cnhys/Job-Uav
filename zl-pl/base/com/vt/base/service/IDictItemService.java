package com.vt.base.service;

import java.util.List;

import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;

/**
 * @author july
 * @version V1.0
 * @Title: IDictItemService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date Jun 3, 2015 11:15:52 AM
 */

public interface IDictItemService extends IBaseService<DictItem, DictItemExample, Integer> {

    /**
     * 根据字典码获取字典信息
     *
     * @param dictTypeCode 字典码
     * @return
     */
    public List<DictItem> queryDictItemsByDictTypeCode(String dictTypeCode);

    /**
     * 根据字典码、字典项值获取下级字典项
     *
     * @param dictTypeCode
     * @param dictItemValue
     * @return
     */
    public List<DictItem> querySubDictItemsByDictTypeCodeAndItemValue(String dictTypeCode, String dictItemValue);

    /**
     * 字典编辑处理
     *
     * @param dictItem
     */
    public void updateByItemTypeCodeAndVale(DictItem dictItem);
}
