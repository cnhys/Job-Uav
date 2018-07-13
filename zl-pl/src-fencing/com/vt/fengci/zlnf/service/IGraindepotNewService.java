package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfGraindepotNew;
import com.vt.fencing.model.ZlnfGraindepotNewExample;


/**
 * Created by john on 17/7/18.
 */
public interface IGraindepotNewService extends IBaseService<ZlnfGraindepotNew, ZlnfGraindepotNewExample, Integer>{

	List<ZlnfGraindepotNew> queryFoodcode(ZlnfGraindepotNew record);

	List<ZlnfGraindepotNew> queryDefault(ZlnfGraindepotNew record);

	List<ZlnfGraindepotNew> queryByParameter(ZlnfGraindepotNew record);

}
