package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFarmServiceMachineyMapper;
import com.vt.fencing.model.ZlnfFarmServiceMachiney;
import com.vt.fencing.model.ZlnfFarmServiceMachineyExample;
import com.vt.fengci.zlnf.service.IFarmServiceMachineyService;

/**
 * 农机机械表
 * Created by john on 17/7/18.
 */
@Service
public class FarmServiceMachineyServiceImpl extends BaseService<ZlnfFarmServiceMachiney, ZlnfFarmServiceMachineyExample, Integer> implements IFarmServiceMachineyService{

	private static final long serialVersionUID = 2055698176984271860L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFarmServiceMachineyMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFarmServiceMachiney, ZlnfFarmServiceMachineyExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
