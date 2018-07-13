package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfApppiontOrderTimeMapper;
import com.vt.fencing.model.ZlnfApppiontOrderTime;
import com.vt.fencing.model.ZlnfApppiontOrderTimeExample;
import com.vt.fengci.zlnf.service.IApppiontOrderTimeService;

/**
 * 预约时间业务
 * Created by john on 17/7/17.
 */
@Service
public class ApppiontOrderTimeServiceImpl extends BaseService<ZlnfApppiontOrderTime, ZlnfApppiontOrderTimeExample, Integer> implements IApppiontOrderTimeService{

	private static final long serialVersionUID = 5233105519426757759L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfApppiontOrderTimeMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfApppiontOrderTime, ZlnfApppiontOrderTimeExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfApppiontOrderTime> queryOrderTimeRowNum(ZlnfApppiontOrderTime record){
		
		return mapper.queryOrderTimeRowNum(record);
	}

	@Override
	public List<ZlnfApppiontOrderTime> queryTodaySellNum(ZlnfApppiontOrderTime apppiontOrderTimeExample1) {
		// TODO Auto-generated method stub
		return mapper.queryTodaySellNum(apppiontOrderTimeExample1);
	} 

}
