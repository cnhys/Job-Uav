package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfDictInfoMapper;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fengci.zlnf.service.IDictInfoService;

/**
 * 字典表
 * Created by john on 17/7/18.
 */
@Service
public class DictInfoServiceImpl extends BaseService<ZlnfDictInfo, ZlnfDictInfoExample, Integer> implements IDictInfoService{

	private static final long serialVersionUID = -1396820444358068650L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfDictInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfDictInfo, ZlnfDictInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
