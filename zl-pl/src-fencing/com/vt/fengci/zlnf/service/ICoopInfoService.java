package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;


/**
 * Created by john on 17/7/17.
 */
public interface ICoopInfoService extends IBaseService<ZlnfCoopInfo, ZlnfCoopInfoExample, Integer>{

	List<ZlnfCoopInfo> queryRownum(ZlnfCoopInfo zlnfCoopInfo);

	List<ZlnfCoopInfo> queryCoopInfoTen(ZlnfCoopInfo zlnfCoopInfo);

}
