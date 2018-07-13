package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfLkorderHourMapper;
import com.vt.fencing.model.ZlnfLkorderHour;
import com.vt.fencing.model.ZlnfLkorderHourExample;
import com.vt.fengci.zlnf.service.ILkorderHourService;

/**
 * 粮库预约时间段
 * Created by john on 17/7/18.
 */
@Service
public class LkorderHourServiceImpl extends BaseService<ZlnfLkorderHour, ZlnfLkorderHourExample, Integer> implements ILkorderHourService{

	private static final long serialVersionUID = 7998955227792433869L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfLkorderHourMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfLkorderHour, ZlnfLkorderHourExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
