package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfGraindepotHis;
import com.vt.fencing.model.ZlnfGraindepotHisExample;


/**
 * Created by john on 17/7/18.
 */
public interface IGraindepotHisService extends IBaseService<ZlnfGraindepotHis, ZlnfGraindepotHisExample, Integer>{

	List<String> queryCreateDate(ZlnfGraindepotHis record);

	List<ZlnfGraindepotHis> queryCreateDateNew(ZlnfGraindepotHis record);

}
