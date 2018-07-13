package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfBiDkzlDkzlMapper;
import com.vt.fencing.model.ZlnfBiDkzlDkzl;
import com.vt.fencing.model.ZlnfBiDkzlDkzlExample;
import com.vt.fengci.zlnf.service.IBiDkzlDkzlService;

/**
 * 贷款种类业务
 * Created by john on 17/7/17.
 */
@Service
public class BiDkzlDkzlServiceImpl extends BaseService<ZlnfBiDkzlDkzl, ZlnfBiDkzlDkzlExample, Integer> implements IBiDkzlDkzlService{

	private static final long serialVersionUID = -9189867811944161784L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfBiDkzlDkzlMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfBiDkzlDkzl, ZlnfBiDkzlDkzlExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
