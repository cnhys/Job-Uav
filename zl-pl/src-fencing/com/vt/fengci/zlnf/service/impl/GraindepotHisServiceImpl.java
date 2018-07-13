package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfGraindepotHisMapper;
import com.vt.fencing.model.ZlnfGraindepotHis;
import com.vt.fencing.model.ZlnfGraindepotHisExample;
import com.vt.fengci.zlnf.service.IGraindepotHisService;

/**
 * 粮库业务
 * Created by john on 17/7/24.
 */
@Service
public class GraindepotHisServiceImpl extends BaseService<ZlnfGraindepotHis, ZlnfGraindepotHisExample, Integer> implements IGraindepotHisService{

	private static final long serialVersionUID = 3801399117150279965L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfGraindepotHisMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfGraindepotHis, ZlnfGraindepotHisExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<String> queryCreateDate(ZlnfGraindepotHis record) {
		// TODO Auto-generated method stub
		return mapper.queryCreateDate(record);
	}

	@Override
	public List<ZlnfGraindepotHis> queryCreateDateNew(ZlnfGraindepotHis record) {
		// TODO Auto-generated method stub
		return mapper.queryCreateDateNew(record);
	}

}
