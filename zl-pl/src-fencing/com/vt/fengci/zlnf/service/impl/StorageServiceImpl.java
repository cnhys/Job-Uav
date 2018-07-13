package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfStorageMapper;
import com.vt.fencing.model.ZlnfStorage;
import com.vt.fencing.model.ZlnfStorageExample;
import com.vt.fengci.zlnf.service.IStorageService;

/**
 * 结算服务
 * Created by john on 17/7/18.
 */
@Service
public class StorageServiceImpl extends BaseService<ZlnfStorage, ZlnfStorageExample, Integer> implements IStorageService{

	private static final long serialVersionUID = 3147503697115976908L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfStorageMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfStorage, ZlnfStorageExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfStorage> queryList(ZlnfStorage record) {
		// TODO Auto-generated method stub
		return mapper.queryList(record);
	}

	@Override
	public List<ZlnfStorage> queryVariety(ZlnfStorage record) {
		// TODO Auto-generated method stub
		return mapper.queryVariety(record);
	}

	@Override
	public List<ZlnfStorage> queryRownum(ZlnfStorage record) {
		// TODO Auto-generated method stub
		return mapper.queryRownum(record);
	}

	@Override
	public List<ZlnfStorage> queryRownumRest(ZlnfStorage zlnfStorage) {
		// TODO Auto-generated method stub
		return mapper.queryRownumRest(zlnfStorage);
	}

	@Override
	public List<ZlnfStorage> queryStr(ZlnfStorage zlnfStorage) {
		// TODO Auto-generated method stub
		return  mapper.queryStr(zlnfStorage);
	}

}
