package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfMessageInfoMapper;
import com.vt.fencing.model.ZlnfMessageInfo;
import com.vt.fencing.model.ZlnfMessageInfoExample;
import com.vt.fengci.zlnf.service.IMessageInfoService;

/**
 * 消息业务
 * Created by john on 17/7/18.
 */
@Service
public class MessageInfoServiceImpl extends BaseService<ZlnfMessageInfo, ZlnfMessageInfoExample, Integer> implements IMessageInfoService{

	private static final long serialVersionUID = -693249805961955425L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfMessageInfoMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfMessageInfo, ZlnfMessageInfoExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

	@Override
	public List<ZlnfMessageInfo> queryMessage(ZlnfMessageInfo record) {
		// TODO Auto-generated method stub
		return mapper.queryMessage(record);
	}

}
