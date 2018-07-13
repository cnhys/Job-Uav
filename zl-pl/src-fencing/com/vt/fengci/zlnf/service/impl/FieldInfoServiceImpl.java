package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFieldInfoMapper;
import com.vt.fencing.model.ZlnfFieldInfo;
import com.vt.fencing.model.ZlnfFieldInfoExample;
import com.vt.fengci.zlnf.service.IFieldInfoService;

/**
 * 田地业务
 * Created by john on 17/7/18.
 */
@Service
public class FieldInfoServiceImpl extends BaseService<ZlnfFieldInfo, ZlnfFieldInfoExample, Integer> implements IFieldInfoService{

	private static final long serialVersionUID = 6771336335880014687L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFieldInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFieldInfo, ZlnfFieldInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
