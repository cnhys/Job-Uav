package com.vt.resource.client;

import java.util.List;

import com.vt.base.mapper.IBaseMapper;
import com.vt.resource.model.Resource;
import com.vt.resource.model.ResourceExample;

public interface ResourceMapper extends IBaseMapper<Resource, ResourceExample, Integer> {

    public List<Resource> queryResourcesList(String[] resIds);
}