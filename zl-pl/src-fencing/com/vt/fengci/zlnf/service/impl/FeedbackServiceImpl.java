package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFeedbackMapper;
import com.vt.fencing.model.ZlnfFeedback;
import com.vt.fencing.model.ZlnfFeedbackExample;
import com.vt.fengci.zlnf.service.IFeedbackService;

/**
 * 反馈意见业务
 * Created by john on 17/7/18.
 */
@Service
public class FeedbackServiceImpl extends BaseService<ZlnfFeedback, ZlnfFeedbackExample, Integer> implements IFeedbackService{

	private static final long serialVersionUID = -4861161840144762381L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFeedbackMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFeedback, ZlnfFeedbackExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
