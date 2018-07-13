package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfPaymenttypeMapper;
import com.vt.fencing.model.ZlnfPaymenttype;
import com.vt.fencing.model.ZlnfPaymenttypeExample;
import com.vt.fengci.zlnf.service.IPaymenttypeService;

/**
 * 交易明细
 * Created by john on 17/7/18.
 */
@Service
public class PaymenttypeServiceImpl extends BaseService<ZlnfPaymenttype, ZlnfPaymenttypeExample, Integer> implements IPaymenttypeService{

	private static final long serialVersionUID = 8420194852446584969L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfPaymenttypeMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfPaymenttype, ZlnfPaymenttypeExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public Integer queryNum(ZlnfPaymenttype record) {
		// TODO Auto-generated method stub
		return mapper.queryNum(record);
	}

}
