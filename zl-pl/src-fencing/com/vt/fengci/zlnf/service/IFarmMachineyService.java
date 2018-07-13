package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfFarmmachineyUser;
import com.vt.fencing.model.ZlnfFarmmachineyUserExample;


/**
 * Created by john on 17/7/18.
 */
public interface IFarmMachineyService extends IBaseService<ZlnfFarmmachineyUser, ZlnfFarmmachineyUserExample, Integer>{

	List<ZlnfFarmmachineyUser> queryUser(ZlnfFarmmachineyUser record);

}
