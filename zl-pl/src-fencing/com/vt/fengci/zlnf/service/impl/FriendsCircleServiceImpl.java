package com.vt.fengci.zlnf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFriendsCircleMapper;
import com.vt.fencing.model.ZlnfFriendsCircle;
import com.vt.fencing.model.ZlnfFriendsCircleExample;
import com.vt.fengci.zlnf.service.IFriendsCircleService;

/**
 * 朋友圈业务
 * Created by john on 17/7/24.
 */
@Service
public class FriendsCircleServiceImpl extends BaseService<ZlnfFriendsCircle, ZlnfFriendsCircleExample, Integer> implements IFriendsCircleService{

	private static final long serialVersionUID = -3551237156093257158L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFriendsCircleMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFriendsCircle, ZlnfFriendsCircleExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}
	
	@Override
	public List<ZlnfFriendsCircle> queryFriendRownum(ZlnfFriendsCircle zlnfFriendsCircle){
		
		return mapper.queryFriendRownum(zlnfFriendsCircle);
	}

	@Override
	public List<ZlnfFriendsCircle> queryMyComment(ZlnfFriendsCircle zlnfFriendsCircle){
		
		return mapper.queryMyComment(zlnfFriendsCircle);
	}

}
