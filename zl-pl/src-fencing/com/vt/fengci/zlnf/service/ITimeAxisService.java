package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfTimeAxis;
import com.vt.fencing.model.ZlnfTimeAxisExample;


/**
 * Created by john on 17/7/17.
 */
public interface ITimeAxisService extends IBaseService<ZlnfTimeAxis, ZlnfTimeAxisExample, Integer>{

	List<ZlnfTimeAxis> queryByOrdercode(ZlnfTimeAxis record);

}
