package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfApppiontOrderMapper;
import com.vt.fencing.model.ZlnfApppiontOrder;
import com.vt.fencing.model.ZlnfApppiontOrderExample;
import com.vt.fengci.zlnf.service.IApppiontOrderService;

/**
 * 预约价格业务
 * Created by john on 17/7/17.
 */
@Service
public class ApppiontOrderServiceImpl extends BaseService<ZlnfApppiontOrder, ZlnfApppiontOrderExample, Integer> implements IApppiontOrderService{

	private static final long serialVersionUID = 7548276852057799040L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfApppiontOrderMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfApppiontOrder, ZlnfApppiontOrderExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfApppiontOrder> queryAppiontRownum(ZlnfApppiontOrder record){
		
		return mapper.queryAppiontRownum(record);
	}

}
