package com.vt.fengci.zlnf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfTakeawayOrderMapper;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fengci.zlnf.service.ITakeawayOrderService;

/**
 * 订单业务
 * Created by john on 17/7/18.
 */
@Service
public class TakeawayOrderServiceImpl extends BaseService<ZlnfTakeawayOrder, ZlnfTakeawayOrderExample, Integer> implements ITakeawayOrderService{

	private static final long serialVersionUID = 2566158220622589411L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfTakeawayOrderMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfTakeawayOrder, ZlnfTakeawayOrderExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<Map<String, Object>> queryPrice(ZlnfTakeawayOrder record){
		
		return mapper.queryPrice(record);
	}

	@Override
	public List<ZlnfTakeawayOrder> queryTakeawayRownum(ZlnfTakeawayOrder record){
		
		return mapper.queryTakeawayRownum(record);
	}

	@Override
	public List<ZlnfTakeawayOrder> queryState(ZlnfTakeawayOrder record){
		
		return mapper.queryState(record);
	}

}
