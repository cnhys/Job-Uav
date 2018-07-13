package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfTimeAxisMapper;
import com.vt.fencing.model.ZlnfTimeAxis;
import com.vt.fencing.model.ZlnfTimeAxisExample;
import com.vt.fengci.zlnf.service.ITimeAxisService;

/**
 * 时间轴业务
 * Created by john on 17/7/17.
 */
@Service
public class TimeAxisServiceImpl extends BaseService<ZlnfTimeAxis, ZlnfTimeAxisExample, Integer> implements ITimeAxisService{

	private static final long serialVersionUID = -3767813387098063443L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfTimeAxisMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfTimeAxis, ZlnfTimeAxisExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfTimeAxis> queryByOrdercode(ZlnfTimeAxis record) {
		// TODO Auto-generated method stub
		return mapper.queryByOrdercode(record);
	}

}
