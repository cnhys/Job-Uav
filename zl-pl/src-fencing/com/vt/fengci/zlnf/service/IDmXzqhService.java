package com.vt.fengci.zlnf.service;

import java.util.List;
import java.util.Map;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;


/**
 * Created by john on 17/7/18.
 */
public interface IDmXzqhService extends IBaseService<DmXzqh, DmXzqhExample, Integer>{

	List<Map<String, Object>> queryCity(DmXzqh record);

}
