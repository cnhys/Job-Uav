package com.vt.fengci.zlnf.service;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfPaymenttype;
import com.vt.fencing.model.ZlnfPaymenttypeExample;


/**
 * Created by john on 17/7/18.
 */
public interface IPaymenttypeService extends IBaseService<ZlnfPaymenttype, ZlnfPaymenttypeExample, Integer>{

	Integer queryNum(ZlnfPaymenttype record);

}
