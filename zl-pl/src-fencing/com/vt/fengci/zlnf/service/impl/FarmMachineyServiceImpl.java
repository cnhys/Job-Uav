package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFarmmachineyUserMapper;
import com.vt.fencing.model.ZlnfFarmmachineyUser;
import com.vt.fencing.model.ZlnfFarmmachineyUserExample;
import com.vt.fengci.zlnf.service.IFarmMachineyService;

/**
 * 农机服务商用户业务
 * Created by john on 17/7/18.
 */
@Service
public class FarmMachineyServiceImpl extends BaseService<ZlnfFarmmachineyUser, ZlnfFarmmachineyUserExample, Integer> implements IFarmMachineyService{

	private static final long serialVersionUID = -6932137898285667718L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFarmmachineyUserMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFarmmachineyUser, ZlnfFarmmachineyUserExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfFarmmachineyUser> queryUser(ZlnfFarmmachineyUser record) {

		return mapper.queryUser(record);
	}

}
