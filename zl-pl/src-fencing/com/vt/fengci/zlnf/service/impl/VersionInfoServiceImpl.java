package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfVersionInfoMapper;
import com.vt.fencing.model.ZlnfVersionInfo;
import com.vt.fencing.model.ZlnfVersionInfoExample;
import com.vt.fengci.zlnf.service.IVersionInfoService;

/**
 * 版本信息业务
 * Created by john on 17/7/18.
 */
@Service
public class VersionInfoServiceImpl extends BaseService<ZlnfVersionInfo, ZlnfVersionInfoExample, Integer> implements IVersionInfoService{

	private static final long serialVersionUID = 2566158220622589411L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfVersionInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfVersionInfo, ZlnfVersionInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfVersionInfo> querynew(){
		
		return mapper.querynew();
	}

	@Override
	public List<ZlnfVersionInfo> querynew1(ZlnfVersionInfo zlnfVersionInfo) {
		// TODO Auto-generated method stub
		return  mapper.querynewByType(zlnfVersionInfo);
	}

}
