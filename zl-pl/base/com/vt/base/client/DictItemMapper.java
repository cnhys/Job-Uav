package com.vt.base.client;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.DictItem;
import com.vt.base.model.DictItemExample;

public interface DictItemMapper extends IBaseMapper<DictItem, DictItemExample, Integer> {
    public void updateByItemTypeCodeAndVale(DictItem dictItem);
}