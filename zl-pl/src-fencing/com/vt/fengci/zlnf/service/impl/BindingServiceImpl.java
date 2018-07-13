package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfBindingMapper;
import com.vt.fencing.model.ZlnfBinding;
import com.vt.fencing.model.ZlnfBindingExample;
import com.vt.fengci.zlnf.service.IBindingService;

/**
 * 绑定业务
 * Created by john on 17/7/17.
 */
@Service
public class BindingServiceImpl extends BaseService<ZlnfBinding, ZlnfBindingExample, Integer> implements IBindingService{

	private static final long serialVersionUID = 4971972562642602843L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfBindingMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfBinding, ZlnfBindingExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public void updateDelete(ZlnfBinding record) {
		// TODO Auto-generated method stub
		mapper.updateDelete(record);
	}

}
