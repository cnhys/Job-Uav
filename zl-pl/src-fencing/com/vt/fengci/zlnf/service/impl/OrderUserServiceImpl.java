package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfOrderUserMapper;
import com.vt.fencing.model.ZlnfOrderUser;
import com.vt.fencing.model.ZlnfOrderUserExample;
import com.vt.fengci.zlnf.service.IOrderUserService;

/**
 * 订单人员
 * Created by john on 17/7/18.
 */
@Service
public class OrderUserServiceImpl extends BaseService<ZlnfOrderUser, ZlnfOrderUserExample, Integer> implements IOrderUserService{

	private static final long serialVersionUID = -5630625873461777922L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfOrderUserMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfOrderUser, ZlnfOrderUserExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
