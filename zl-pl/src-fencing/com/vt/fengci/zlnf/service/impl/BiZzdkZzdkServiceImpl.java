package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfBiZzdkZzdkMapper;
import com.vt.fencing.model.ZlnfBiZzdkZzdk;
import com.vt.fencing.model.ZlnfBiZzdkZzdkExample;
import com.vt.fencing.model.ZlnfBiZzdkZzdkWithBLOBs;
import com.vt.fengci.zlnf.service.IBiZzdkZzdkService;

/**
 * 银行产品业务
 * Created by john on 17/7/17.
 */
@Service
public class BiZzdkZzdkServiceImpl extends BaseService<ZlnfBiZzdkZzdkWithBLOBs, ZlnfBiZzdkZzdkExample, Integer> implements IBiZzdkZzdkService{

	private static final long serialVersionUID = -1892519990441936568L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfBiZzdkZzdkMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfBiZzdkZzdkWithBLOBs, ZlnfBiZzdkZzdkExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfBiZzdkZzdkWithBLOBs> queryList(ZlnfBiZzdkZzdkWithBLOBs biZzdkZzdkWithBLOBs) {
		// TODO Auto-generated method stub
		return mapper.queryList(biZzdkZzdkWithBLOBs);
	}

}
