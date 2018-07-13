package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfMessageInfo;
import com.vt.fencing.model.ZlnfMessageInfoExample;


/**
 * Created by john on 17/7/18.
 */
public interface IMessageInfoService extends IBaseService<ZlnfMessageInfo, ZlnfMessageInfoExample, Integer>{

	List<ZlnfMessageInfo> queryMessage(ZlnfMessageInfo record);

}
