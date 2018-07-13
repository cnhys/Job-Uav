package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfAgrementinfoMapper;
import com.vt.fencing.model.ZlnfAgrementinfo;
import com.vt.fencing.model.ZlnfAgrementinfoExample;
import com.vt.fengci.zlnf.service.IAgrementinfoService;

/**
 * 服务协议业务
 * Created by john on 17/7/17.
 */
@Service
public class AgrementinfoServiceImpl extends BaseService<ZlnfAgrementinfo, ZlnfAgrementinfoExample, Integer> implements IAgrementinfoService{

	private static final long serialVersionUID = 951203376205340236L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfAgrementinfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfAgrementinfo, ZlnfAgrementinfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
