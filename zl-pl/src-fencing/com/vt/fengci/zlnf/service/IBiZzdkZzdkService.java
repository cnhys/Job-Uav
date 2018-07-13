package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfBiZzdkZzdk;
import com.vt.fencing.model.ZlnfBiZzdkZzdkExample;
import com.vt.fencing.model.ZlnfBiZzdkZzdkWithBLOBs;


/**
 * Created by john on 17/7/17.
 */
public interface IBiZzdkZzdkService extends IBaseService<ZlnfBiZzdkZzdkWithBLOBs, ZlnfBiZzdkZzdkExample, Integer>{

	List<ZlnfBiZzdkZzdkWithBLOBs> queryList(
			ZlnfBiZzdkZzdkWithBLOBs biZzdkZzdkWithBLOBs);

}
