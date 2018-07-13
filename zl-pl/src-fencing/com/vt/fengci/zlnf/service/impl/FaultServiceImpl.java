package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFaultMapper;
import com.vt.fencing.model.ZlnfFault;
import com.vt.fencing.model.ZlnfFaultExample;
import com.vt.fengci.zlnf.service.IFaultService;

/**
 * 故障维修
 * Created by john on 17/7/18.
 */
@Service
public class FaultServiceImpl extends BaseService<ZlnfFault, ZlnfFaultExample, Integer> implements IFaultService{

	private static final long serialVersionUID = 1257079091374085758L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFaultMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFault, ZlnfFaultExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
