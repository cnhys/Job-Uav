package com.vt.fengci.zlnf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.base.mapper.IBaseMapper;
import com.vt.base.service.BaseService;
import com.vt.fencing.client.ZlnfFriendsCircleCommentMapper;
import com.vt.fencing.model.ZlnfFriendsCircleComment;
import com.vt.fencing.model.ZlnfFriendsCircleCommentExample;
import com.vt.fengci.zlnf.service.IFriendsCircleCommentService;

/**
 * 朋友圈评论业务
 * Created by john on 17/7/24.
 */
@Service
public class FriendsCircleCommentServiceImpl extends BaseService<ZlnfFriendsCircleComment, ZlnfFriendsCircleCommentExample, Integer> implements IFriendsCircleCommentService{

	private static final long serialVersionUID = -3551237156093257158L;
	/**
     * mapper
     */
    @Autowired
    private ZlnfFriendsCircleCommentMapper mapper;
	
	
	@Override
	public IBaseMapper<ZlnfFriendsCircleComment, ZlnfFriendsCircleCommentExample, Integer> getMapper() {
		// TODO Auto-generated method stub
		return mapper;
	}

}
