package com.vt.fengci.zlnf.service;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;


/**
 * Created by john on 17/7/18.
 */
public interface ICustomerInfoService extends IBaseService<ZlnfFcustofomerin, ZlnfFcustofomerinExample, Integer>{

	ZlnfFcustofomerin queryFarmMachineyMerchant(String merchantcode);

	ZlnfFcustofomerin queryCoopInfo(String coopcode);

}
