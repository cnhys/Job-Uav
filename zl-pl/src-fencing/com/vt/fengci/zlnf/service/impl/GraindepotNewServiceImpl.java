package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfGraindepotNewMapper;
import com.vt.fencing.model.ZlnfGraindepotNew;
import com.vt.fencing.model.ZlnfGraindepotNewExample;
import com.vt.fengci.zlnf.service.IGraindepotNewService;

/**
 * 粮库业务
 * Created by john on 17/7/24.
 */
@Service
public class GraindepotNewServiceImpl extends BaseService<ZlnfGraindepotNew, ZlnfGraindepotNewExample, Integer> implements IGraindepotNewService{

	private static final long serialVersionUID = 2871158339637970374L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfGraindepotNewMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfGraindepotNew, ZlnfGraindepotNewExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfGraindepotNew> queryFoodcode(ZlnfGraindepotNew record) {
		// TODO Auto-generated method stub
		return mapper.queryFoodcode(record);
	}
	
	
	@Override
	public List<ZlnfGraindepotNew> queryDefault(ZlnfGraindepotNew record) {
		
		return mapper.queryDefault(record);
	}
	
	@Override
	public List<ZlnfGraindepotNew> queryByParameter(ZlnfGraindepotNew record) {
		
		return mapper.queryByParameter(record);
	}

}
