package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfBiDksqclMapper;
import com.vt.fencing.model.ZlnfBiDksqcl;
import com.vt.fencing.model.ZlnfBiDksqclExample;
import com.vt.fengci.zlnf.service.IBiDksqclService;

/**
 * 申请种植贷业务
 * Created by john on 17/7/17.
 */
@Service
public class BiDksqclServiceImpl extends BaseService<ZlnfBiDksqcl, ZlnfBiDksqclExample, Integer> implements IBiDksqclService{

	private static final long serialVersionUID = -6396405115322300529L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfBiDksqclMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfBiDksqcl, ZlnfBiDksqclExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
