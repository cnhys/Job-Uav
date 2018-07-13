package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfApppiontOrder;
import com.vt.fencing.model.ZlnfApppiontOrderExample;


/**
 * Created by john on 17/7/17.
 */
public interface IApppiontOrderService extends IBaseService<ZlnfApppiontOrder, ZlnfApppiontOrderExample, Integer>{

	List<ZlnfApppiontOrder> queryAppiontRownum(ZlnfApppiontOrder record);

}
