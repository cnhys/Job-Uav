package com.vt.fengci.zlnf.service;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfBinding;
import com.vt.fencing.model.ZlnfBindingExample;


/**
 * Created by john on 17/7/17.
 */
public interface IBindingService extends IBaseService<ZlnfBinding, ZlnfBindingExample, Integer>{

	void updateDelete(ZlnfBinding record);

}
