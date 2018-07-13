package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfAnnexMapper;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fengci.zlnf.service.IAnnexService;

/**
 * 图片业务
 * Created by john on 17/7/17.
 */
@Service
public class AnnexServiceImpl extends BaseService<ZlnfAnnex, ZlnfAnnexExample, Integer> implements IAnnexService{

	private static final long serialVersionUID = -1989505881823687945L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfAnnexMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfAnnex, ZlnfAnnexExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
