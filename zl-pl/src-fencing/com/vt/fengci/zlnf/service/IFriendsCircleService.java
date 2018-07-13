package com.vt.fengci.zlnf.service;

import java.util.List;

import com.vt.base.service.IBaseService;
import com.vt.fencing.model.ZlnfFriendsCircle;
import com.vt.fencing.model.ZlnfFriendsCircleExample;


/**
 * Created by john on 17/7/18.
 */
public interface IFriendsCircleService extends IBaseService<ZlnfFriendsCircle, ZlnfFriendsCircleExample, Integer>{

	List<ZlnfFriendsCircle> queryFriendRownum(ZlnfFriendsCircle zlnfFriendsCircle);

	List<ZlnfFriendsCircle> queryMyComment(ZlnfFriendsCircle zlnfFriendsCircle);

}
