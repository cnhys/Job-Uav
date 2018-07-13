package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfCoopUserMapper;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;
import com.vt.fengci.zlnf.service.ICoopUserService;

/**
 * 合作社用户业务
 * Created by john on 17/7/17.
 */
@Service
public class CoopUserServiceImpl extends BaseService<ZlnfCoopUser, ZlnfCoopUserExample, Integer> implements ICoopUserService{

	private static final long serialVersionUID = 1420408576022901828L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfCoopUserMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfCoopUser, ZlnfCoopUserExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
