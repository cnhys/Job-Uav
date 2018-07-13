package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFcustofomerinMapper;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fengci.zlnf.service.ICustomerInfoService;

/**
 * 用户业务
 * Created by john on 17/7/18.
 */
@Service
public class CustomerInfoServiceImpl extends BaseService<ZlnfFcustofomerin, ZlnfFcustofomerinExample, Integer> implements ICustomerInfoService{

	private static final long serialVersionUID = 7684215648604221459L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFcustofomerinMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFcustofomerin, ZlnfFcustofomerinExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public ZlnfFcustofomerin queryFarmMachineyMerchant(String merchantcode) {
		// TODO Auto-generated method stub
		return mapper.queryFarmMachineyMerchant(merchantcode);
	}

	@Override
	public ZlnfFcustofomerin queryCoopInfo(String coopcode) {
		// TODO Auto-generated method stub
		return mapper.queryCoopInfo(coopcode);
	}
}
