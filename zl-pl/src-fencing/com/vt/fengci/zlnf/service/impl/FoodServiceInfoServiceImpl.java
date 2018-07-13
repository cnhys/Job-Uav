package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFoodServiceInfoMapper;
import com.vt.fencing.model.ZlnfFoodServiceInfo;
import com.vt.fencing.model.ZlnfFoodServiceInfoExample;
import com.vt.fengci.zlnf.service.IFoodServiceInfoService;

/**
 * 粮库业务
 * Created by john on 17/7/24.
 */
@Service
public class FoodServiceInfoServiceImpl extends BaseService<ZlnfFoodServiceInfo, ZlnfFoodServiceInfoExample, Integer> implements IFoodServiceInfoService{

	private static final long serialVersionUID = -6870882256193791163L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFoodServiceInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFoodServiceInfo, ZlnfFoodServiceInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
