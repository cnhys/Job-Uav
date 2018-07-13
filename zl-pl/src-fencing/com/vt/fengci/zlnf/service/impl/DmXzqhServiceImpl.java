package com.vt.fengci.zlnf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.DmXzqhMapper;
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;
import com.vt.fengci.zlnf.service.IDmXzqhService;

/**
 * 省市列表
 * Created by john on 17/7/18.
 */
@Service
public class DmXzqhServiceImpl extends BaseService<DmXzqh, DmXzqhExample, Integer> implements IDmXzqhService{

	private static final long serialVersionUID = 1432006607236510903L;
	/**
     * mapper
     */
    @Autowired
    private DmXzqhMapper mapper;
	
	
	@Override
	public IBaseMapper<DmXzqh, DmXzqhExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<Map<String, Object>> queryCity(DmXzqh record) {
		// TODO Auto-generated method stub
		return mapper.queryCity(record);
	}

}
