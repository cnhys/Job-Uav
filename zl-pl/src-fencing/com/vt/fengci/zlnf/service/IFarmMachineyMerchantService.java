package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfFarmMachineyMerchant;
import com.vt.fencing.model.ZlnfFarmMachineyMerchantExample;


/**
 * Created by john on 17/7/18.
 */
public interface IFarmMachineyMerchantService extends IBaseService<ZlnfFarmMachineyMerchant, ZlnfFarmMachineyMerchantExample, Integer>{

	List<ZlnfFarmMachineyMerchant> queryRownumFarm(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant);

	List<ZlnfFarmMachineyMerchant> selectLesstanTwenty(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant);

	List<ZlnfFarmMachineyMerchant> selectLesstanTen(ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant);

}
