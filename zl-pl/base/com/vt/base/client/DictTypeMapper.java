package com.vt.base.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.DictType;
import com.vt.base.model.DictTypeExample;


public interface DictTypeMapper extends IBaseMapper<DictType, DictTypeExample, Integer> {
    public void updateDictTypeByCode(DictType dictType);
}