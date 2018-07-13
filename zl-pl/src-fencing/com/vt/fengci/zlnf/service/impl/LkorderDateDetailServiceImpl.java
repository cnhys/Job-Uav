package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfLkorderDateDetailMapper;
import com.vt.fencing.model.ZlnfLkorderDateDetail;
import com.vt.fencing.model.ZlnfLkorderDateDetailExample;
import com.vt.fengci.zlnf.service.ILkorderDateDetailService;

/**
 * 消息业务
 * Created by john on 17/7/18.
 */
@Service
public class LkorderDateDetailServiceImpl extends BaseService<ZlnfLkorderDateDetail, ZlnfLkorderDateDetailExample, Integer> implements ILkorderDateDetailService{

	private static final long serialVersionUID = 4596624225720121932L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfLkorderDateDetailMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfLkorderDateDetail, ZlnfLkorderDateDetailExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
