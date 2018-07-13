package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfServiceProgramMapper;
import com.vt.fencing.model.ZlnfServiceProgram;
import com.vt.fencing.model.ZlnfServiceProgramExample;
import com.vt.fengci.zlnf.service.IServiceProgramService;

/**
 * 农机服务
 * Created by john on 17/7/18.
 */
@Service
public class ServiceProgramServiceImpl extends BaseService<ZlnfServiceProgram, ZlnfServiceProgramExample, Integer> implements IServiceProgramService{

	private static final long serialVersionUID = 7515510425407977712L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfServiceProgramMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfServiceProgram, ZlnfServiceProgramExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfServiceProgram> queryPeriphery(ZlnfServiceProgram record){
		
		return mapper.queryPeriphery(record);
	}

	@Override
	public List<ZlnfServiceProgram> queryProgramRownum(ZlnfServiceProgram record){
		
		return mapper.queryProgramRownum(record);
	}

}
