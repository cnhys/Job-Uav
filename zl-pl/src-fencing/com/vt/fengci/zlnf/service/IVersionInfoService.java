package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfVersionInfo;
import com.vt.fencing.model.ZlnfVersionInfoExample;


/**
 * Created by john on 17/7/18.
 */
public interface IVersionInfoService extends IBaseService<ZlnfVersionInfo, ZlnfVersionInfoExample, Integer>{

	List<ZlnfVersionInfo> querynew();

	List<ZlnfVersionInfo> querynew1(ZlnfVersionInfo zlnfVersionInfo);

}
