package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfApppiontOrderTime;
import com.vt.fencing.model.ZlnfApppiontOrderTimeExample;


/**
 * Created by john on 17/7/17.
 */
public interface IApppiontOrderTimeService extends IBaseService<ZlnfApppiontOrderTime, ZlnfApppiontOrderTimeExample, Integer>{

	List<ZlnfApppiontOrderTime> queryOrderTimeRowNum(ZlnfApppiontOrderTime record);

	List<ZlnfApppiontOrderTime> queryTodaySellNum(ZlnfApppiontOrderTime apppiontOrderTimeExample1);

}
