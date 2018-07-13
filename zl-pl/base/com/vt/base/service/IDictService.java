package com.vt.base.service;

import com.vt.base.OptResult;
import com.vt.base.model.DictType;
import com.vt.base.model.DictTypeExample;

/**
 * @author july
 * @version V1.0
 * @Title: IDictService.java
 * @Package com.vt.base.service
 * @Description: TODO
 * @date May 29, 2015 9:52:10 AM
 */

public interface IDictService extends IBaseService<DictType, DictTypeExample, Integer> {
    public OptResult updateDictTypeByDictId(DictType dictType);

    /**
     * 字典类型编辑
     *
     * @param dictType
     */
    public void updateDictTypeByCode(DictType dictType);
}
