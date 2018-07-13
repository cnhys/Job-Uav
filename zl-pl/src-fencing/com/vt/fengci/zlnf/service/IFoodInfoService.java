package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;


/**
 * Created by john on 17/7/18.
 */
public interface IFoodInfoService extends IBaseService<ZlnfFoodInfo, ZlnfFoodInfoExample, Integer>{

	java.util.List<ZlnfFoodInfo> queryEightfood(ZlnfFoodInfo record);

	List<ZlnfFoodInfo> queryEightfood1(ZlnfFoodInfo record);


}
