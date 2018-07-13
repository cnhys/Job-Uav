package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfCoopInfoMapper;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fengci.zlnf.service.ICoopInfoService;

/**
 * 合作社业务
 * Created by john on 17/7/17.
 */
@Service
public class CoopInfoServiceImpl extends BaseService<ZlnfCoopInfo, ZlnfCoopInfoExample, Integer> implements ICoopInfoService{

	private static final long serialVersionUID = -484036418258018785L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfCoopInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfCoopInfo, ZlnfCoopInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfCoopInfo> queryRownum(ZlnfCoopInfo zlnfCoopInfo){
		
		return mapper.queryRownum(zlnfCoopInfo);
	}

	@Override
	public List<ZlnfCoopInfo> queryCoopInfoTen(ZlnfCoopInfo zlnfCoopInfo){
		
		return mapper.queryCoopInfoTen(zlnfCoopInfo);
	}

}
