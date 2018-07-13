package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfPaymenttypeCreditMapper;
import com.vt.fencing.model.ZlnfPaymenttypeCredit;
import com.vt.fencing.model.ZlnfPaymenttypeCreditExample;
import com.vt.fengci.zlnf.service.IPaymenttypeCreditService;

/**
 * 贷款交钱明细
 * Created by john on 17/7/18.
 */
@Service
public class PaymenttypeCreditServiceImpl extends BaseService<ZlnfPaymenttypeCredit, ZlnfPaymenttypeCreditExample, Integer> implements IPaymenttypeCreditService{

	private static final long serialVersionUID = -4512861755969262062L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfPaymenttypeCreditMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfPaymenttypeCredit, ZlnfPaymenttypeCreditExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
