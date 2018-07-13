package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfUserdiscussMapper;
import com.vt.fencing.model.ZlnfUserdiscuss;
import com.vt.fencing.model.ZlnfUserdiscussExample;
import com.vt.fengci.zlnf.service.IUserDiscussService;

/**
 * 服务协议业务
 * Created by john on 17/7/17.
 */
@Service
public class UserDiscussImpl extends BaseService<ZlnfUserdiscuss, ZlnfUserdiscussExample, Integer> implements IUserDiscussService{

	private static final long serialVersionUID = 951203376205340236L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfUserdiscussMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfUserdiscuss, ZlnfUserdiscussExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
