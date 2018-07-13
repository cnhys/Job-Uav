package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFarmMachineyMerchantMapper;
import com.vt.fencing.model.ZlnfFarmMachineyMerchant;
import com.vt.fencing.model.ZlnfFarmMachineyMerchantExample;
import com.vt.fengci.zlnf.service.IFarmMachineyMerchantService;

/**
 * 农机服务商业务
 * Created by john on 17/7/18.
 */
@Service
public class FarmMachineyMerchantServiceImpl extends BaseService<ZlnfFarmMachineyMerchant, ZlnfFarmMachineyMerchantExample, Integer> implements IFarmMachineyMerchantService{

	private static final long serialVersionUID = 6648243763163829741L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFarmMachineyMerchantMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFarmMachineyMerchant, ZlnfFarmMachineyMerchantExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfFarmMachineyMerchant> queryRownumFarm(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant){
		
		return mapper.queryRownumFarm(zlnfFarmMachineyMerchant);
	}

	@Override
	public List<ZlnfFarmMachineyMerchant> selectLesstanTwenty(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant){
		
		return mapper.selectLesstanTwenty(zlnfFarmMachineyMerchant);
	}

	@Override
	public List<ZlnfFarmMachineyMerchant> selectLesstanTen(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant){
		
		return mapper.selectLesstanTen(zlnfFarmMachineyMerchant);
	}
}
