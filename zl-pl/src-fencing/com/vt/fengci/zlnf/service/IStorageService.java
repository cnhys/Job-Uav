package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfStorage;
import com.vt.fencing.model.ZlnfStorageExample;


/**
 * Created by john on 17/7/18.
 */
public interface IStorageService extends IBaseService<ZlnfStorage, ZlnfStorageExample, Integer>{

	List<ZlnfStorage> queryList(ZlnfStorage record);

	List<ZlnfStorage> queryVariety(ZlnfStorage record);

	List<ZlnfStorage> queryRownum(ZlnfStorage record);

	List<ZlnfStorage> queryRownumRest(ZlnfStorage zlnfStorage);

	List<ZlnfStorage> queryStr(ZlnfStorage zlnfStorage);


}
