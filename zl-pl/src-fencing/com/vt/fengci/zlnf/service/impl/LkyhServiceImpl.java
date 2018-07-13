package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfLkyhMapper;
import com.vt.fencing.model.ZlnfLkyh;
import com.vt.fencing.model.ZlnfLkyhExample;
import com.vt.fengci.zlnf.service.ILkyhService;

/**
 * 粮库预约时间段
 * Created by john on 17/7/18.
 */
@Service
public class LkyhServiceImpl extends BaseService<ZlnfLkyh, ZlnfLkyhExample, Integer> implements ILkyhService{

	private static final long serialVersionUID = -5712870545357872290L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfLkyhMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfLkyh, ZlnfLkyhExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
