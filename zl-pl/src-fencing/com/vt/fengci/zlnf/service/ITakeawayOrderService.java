package com.vt.fengci.zlnf.service;

import java.util.List;
import java.util.Map;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;


/**
 * Created by john on 17/7/18.
 */
public interface ITakeawayOrderService extends IBaseService<ZlnfTakeawayOrder, ZlnfTakeawayOrderExample, Integer>{

	List<Map<String, Object>> queryPrice(ZlnfTakeawayOrder record);

	List<ZlnfTakeawayOrder> queryTakeawayRownum(ZlnfTakeawayOrder record);

	List<ZlnfTakeawayOrder> queryState(ZlnfTakeawayOrder record);

}
