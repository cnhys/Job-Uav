package com.vt.fencing.versions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.client.VangoVersionMapper;
import com.vt.fencing.model.VangoVersion;
import com.vt.fencing.model.VangoVersionExample;
import com.vt.fencing.versions.service.IVangoVersionsService;

/**
 * 请假服务实现
 * Created by john on 16/11/18.
 */
@Service(IGatewayServiceDef.VERSION_SERVICE)
public class VangoVersionsServiceImpl extends BaseService<VangoVersion, VangoVersionExample, Integer> implements IVangoVersionsService{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1420408576022901828L;
	/**
     * mapper
     */
    @Autowired
    private VangoVersionMapper mapper;
	
	
	@Override
	public IBaseMapper<VangoVersion, VangoVersionExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
