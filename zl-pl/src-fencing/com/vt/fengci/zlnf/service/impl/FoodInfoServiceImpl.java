package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFoodInfoMapper;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fengci.zlnf.service.IFoodInfoService;

/**
 * 粮库业务
 * Created by john on 17/7/24.
 */
@Service
public class FoodInfoServiceImpl extends BaseService<ZlnfFoodInfo, ZlnfFoodInfoExample, Integer> implements IFoodInfoService{

	private static final long serialVersionUID = 6771336335880014687L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFoodInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFoodInfo, ZlnfFoodInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfFoodInfo> queryEightfood(ZlnfFoodInfo record) {
		
		return mapper.queryEightfood(record);
	}

	@Override
	public List<ZlnfFoodInfo> queryEightfood1(ZlnfFoodInfo record) {
		
		return mapper.queryEightfood1(record);
	}

}
