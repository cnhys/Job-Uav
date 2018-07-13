package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfServiceProgram;
import com.vt.fencing.model.ZlnfServiceProgramExample;


/**
 * Created by john on 17/7/18.
 */
public interface IServiceProgramService extends IBaseService<ZlnfServiceProgram, ZlnfServiceProgramExample, Integer>{

	List<ZlnfServiceProgram> queryPeriphery(ZlnfServiceProgram record);

	List<ZlnfServiceProgram> queryProgramRownum(ZlnfServiceProgram record);

}
