package com.vt.fengci.zlnf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfFriendsCircle;
import com.vt.fencing.model.ZlnfFriendsCircleComment;
import com.vt.fencing.model.ZlnfFriendsCircleCommentExample;
import com.vt.fencing.model.ZlnfFriendsCircleExample;
import com.vt.fencing.request.ZlnfFriendsCircleCommentRequest;
import com.vt.fencing.request.ZlnfFriendsCircleRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IFriendsCircleCommentService;
import com.vt.fengci.zlnf.service.IFriendsCircleService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FriendsCircleController extends BaseGatewayController {

	private static final long serialVersionUID = 6814763661293999491L;
	/**
     * member service
     */
    
    @Autowired
    private IFriendsCircleService friendsCircleService;
    
    @Autowired
    private IFriendsCircleCommentService friendsCircleCommentService;
    
    @Autowired
    private IAnnexService annexService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    /**
     * 添加朋友圈信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircle/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFriendsCircleRequest zlnfFriendsCircleRequest  = convert(data, ZlnfFriendsCircleRequest.class);
        // 2. 业务检查
        if (zlnfFriendsCircleRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String friendcode =Uuid32.getUUID32();
        String annexcode =Uuid32.getUUID32();
    	Date date = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String str = format.format(date);
		//先把字符串转为util.Date对象
    	String content = zlnfFriendsCircleRequest.getContent();
    	String creator = zlnfFriendsCircleRequest.getCreator();
    	//添加朋友圈信息
    	ZlnfFriendsCircle zlnfFriendsCircle = new ZlnfFriendsCircle();
    	zlnfFriendsCircle.setFriendcode(friendcode);
    	zlnfFriendsCircle.setContent(content);
    	zlnfFriendsCircle.setCreator(creator);
    	zlnfFriendsCircle.setCreatorcode(zlnfFriendsCircleRequest.getCreatorcode());
    	zlnfFriendsCircle.setCreatedonutc(str);
    	try {
	    	//添加朋友圈图片信息
			String path = zlnfFriendsCircleRequest.getAnnexpath();
			String annexname = zlnfFriendsCircleRequest.getAnnexname();
			String httpPath = zlnfFriendsCircleRequest.getHttpPath();
			if(StringUtils.isNotBlank(path)){
	    		String[] paths = path.split(",");
	    		String[] annexnames = annexname.split(",");
	    		String[] httpPaths = httpPath.split(",");
	    		for(int i = 0;i<paths.length;i++){
	    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	    			zlnfAnnex.setDeptcode(friendcode);
	    			zlnfAnnex.setAnnexcode(annexcode);
	    			zlnfAnnex.setAnnexname(annexnames[i]);
	    			zlnfAnnex.setAnnexpath(paths[i]);
	    			zlnfAnnex.setHttppath(httpPaths[i]);
	    			zlnfAnnex.setDepttype("3");
	    			zlnfAnnex.setCreator(creator);
	    			zlnfAnnex.setCreatedonutc(date);
	    			annexService.create(zlnfAnnex);
	    		}
	    	}
    		result.setSuccess(true);
    		friendsCircleService.create(zlnfFriendsCircle);
        	result.setReturnCode("0000");
        	result.setMessage("添加朋友圈成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加朋友圈失败");
		}
        return result;
    }
    
    
    /**
     * 添加朋友圈评论信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircleComment/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleCommentCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFriendsCircleCommentRequest zlnfFriendsCircleCommentRequest  = convert(data, ZlnfFriendsCircleCommentRequest.class);
        // 2. 业务检查
        if (zlnfFriendsCircleCommentRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String commentcode =Uuid32.getUUID32();
    	Date date = new Date();
    	String content = zlnfFriendsCircleCommentRequest.getContent();
    	System.out.println("content================================>"+content);
    	String headportrait = "";
    	if("1".equals(zlnfFriendsCircleCommentRequest.getType())){
    		ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
            example.createCriteria().andUsercodeEqualTo(zlnfFriendsCircleCommentRequest.getCreatorcode());
            List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
            headportrait = list.get(0).getHttpphoto();
    	}
    	String creator = zlnfFriendsCircleCommentRequest.getCreator();
    	//添加朋友圈评论信息
    	ZlnfFriendsCircleComment zlnfFriendsCircleComment = new ZlnfFriendsCircleComment();
    	zlnfFriendsCircleComment.setCommentcode(commentcode);
    	zlnfFriendsCircleComment.setFriendcode(zlnfFriendsCircleCommentRequest.getFriendcode());
    	zlnfFriendsCircleComment.setType(zlnfFriendsCircleCommentRequest.getType());
    	zlnfFriendsCircleComment.setContent(content);
    	zlnfFriendsCircleComment.setHeadportrait(headportrait);
    	zlnfFriendsCircleComment.setCreator(creator);
    	zlnfFriendsCircleComment.setCreatorcode(zlnfFriendsCircleCommentRequest.getCreatorcode());
    	zlnfFriendsCircleComment.setCreatedonutc(date);
    	try {
    		result.setSuccess(true);
    		friendsCircleCommentService.create(zlnfFriendsCircleComment);
        	result.setReturnCode("0000");
        	result.setMessage("添加朋友圈评论成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加朋友圈评论失败");
		}
        return result;
    }
    
    /**
     * 获取朋友圈分页信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircle/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFriendsCircleRequest zlnfFriendsCircleRequest  = convert(data, ZlnfFriendsCircleRequest.class);
        // 2. 业务检查
        if (zlnfFriendsCircleRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
	        Map<String, Object> map = new HashMap<String, Object>();
	        //查询当前用户头像，昵称，朋友圈背景图片
	        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
	        example.createCriteria().andUsercodeEqualTo(zlnfFriendsCircleRequest.getCreatorcode());
	        List<ZlnfFcustofomerin> customerInfoList = customerInfoService.getResult(example);
	        map.put("httppath", customerInfoList.get(0).getHttpphoto());
	        map.put("friendscircle", customerInfoList.get(0).getFriendscircle());
	        map.put("nickname", customerInfoList.get(0).getNickname());
	        //根据条件查询朋友圈列表
	        ZlnfFriendsCircle zlnfFriendsCircle = new ZlnfFriendsCircle();
	        zlnfFriendsCircle.setNumber(zlnfFriendsCircleRequest.getNumber());
	        if(!"".equals(zlnfFriendsCircleRequest.getFriendcode()) && zlnfFriendsCircleRequest.getFriendcode()!=null){
	        	zlnfFriendsCircle.setFriendcode(zlnfFriendsCircleRequest.getFriendcode());
	        }
	        if("1".equals(zlnfFriendsCircleRequest.getType())){
	        	zlnfFriendsCircle.setCreatorcode(zlnfFriendsCircleRequest.getCreatorcode());
	        }
	        List<ZlnfFriendsCircle> list = friendsCircleService.queryFriendRownum(zlnfFriendsCircle);
	        if(list.size()>0){
	        	for(int i = 0;i<list.size();i++){
	        		//查看发布朋友圈用户头像，名称
	        		ZlnfFcustofomerinExample custofomerExample = new ZlnfFcustofomerinExample();
	        		custofomerExample.createCriteria().andUsercodeEqualTo(list.get(i).getCreatorcode());
	        		List<ZlnfFcustofomerin> customerInfoList1 = customerInfoService.getResult(custofomerExample);
	        		if(customerInfoList1.size()>0){
	        			list.get(i).setHttppath(customerInfoList1.get(0).getHttpphoto());
	        			list.get(i).setCreator(customerInfoList1.get(0).getUsername());
	        		}
	        		//查询评论信息
	            	ZlnfFriendsCircleCommentExample friendsCircleCommentExample = new ZlnfFriendsCircleCommentExample();
	            	ZlnfFriendsCircleCommentExample.Criteria commentCriteria = friendsCircleCommentExample.createCriteria();
	            	commentCriteria.andFriendcodeEqualTo(list.get(i).getFriendcode());
	            	commentCriteria.andIsdeletedEqualTo("0");
	            	List<ZlnfFriendsCircleComment> commentList = friendsCircleCommentService.getResult(friendsCircleCommentExample);
	            	//点赞集合
	            	List<ZlnfFriendsCircleComment> commentList1 = new ArrayList<>();
	            	//评论集合
	            	List<ZlnfFriendsCircleComment> commentList2 = new ArrayList<>();
	            	int n = 0;
	            	int m = 0;
	            	int k = 0;
	            	Boolean isgood = false;
	            	//查询十个点赞
	            	for(int j = 0;j<commentList.size();j++){
	            		if("1".equals(commentList.get(j).getType())){
	            			if(n<10){
	            				ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
	            				custofomerinExample.createCriteria().andUsercodeEqualTo(commentList.get(j).getCreatorcode());
	            		        List<ZlnfFcustofomerin> customerInfoList2 = customerInfoService.getResult(custofomerinExample);
	            		        commentList.get(j).setHeadportrait(customerInfoList2.get(0).getHttpphoto());
	            		        commentList.get(j).setCreator(customerInfoList2.get(0).getUsername());
	            				commentList1.add(commentList.get(j));
	            				n++;
	            			}
	            			m++;
	            			if(commentList.get(j).getCreatorcode().equals(zlnfFriendsCircleRequest.getCreatorcode())){
	            				isgood = true;
	            			}
	            		}
	            	}
	            	list.get(i).setIsgood(isgood);
	            	list.get(i).setCommentList1(commentList1);
	            	list.get(i).setGoodnum(m);
	            	//查询评论列表，是否是详情页面，如果是展示全部评论，如果不是只展示两条
	            	if(!"".equals(zlnfFriendsCircleRequest.getFriendcode()) && zlnfFriendsCircleRequest.getFriendcode()!=null){
	            		for(int j = 0;j<commentList.size();j++){
	            			ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
            				custofomerinExample.createCriteria().andUsercodeEqualTo(commentList.get(j).getCreatorcode());
            		        List<ZlnfFcustofomerin> customerInfoList2 = customerInfoService.getResult(custofomerinExample);
            		        commentList.get(j).setCreator(customerInfoList2.get(0).getUsername());
		            		if("2".equals(commentList.get(j).getType())){
		            			commentList2.add(commentList.get(j));
		            		}
		            	}
	    	        }else{
	    	        	for(int j = 0;j<commentList.size();j++){
	    	        		ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
            				custofomerinExample.createCriteria().andUsercodeEqualTo(commentList.get(j).getCreatorcode());
            		        List<ZlnfFcustofomerin> customerInfoList2 = customerInfoService.getResult(custofomerinExample);
            		        commentList.get(j).setCreator(customerInfoList2.get(0).getUsername());
		            		if("2".equals(commentList.get(j).getType())){
		            			if(k<2){
		            				commentList2.add(commentList.get(j));
		            				k++;
		            			}
		            		}
		            	}
	    	        }
	            	list.get(i).setCommentList2(commentList2);
	            	//获取朋友圈图片
	            	ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
	            	ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
	            	annexCriteria.andDepttypeEqualTo("3");
	            	annexCriteria.andDeptcodeEqualTo(list.get(i).getFriendcode());
	            	List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
	            	list.get(i).setAnnexList(annexList);
	            	//返回时间戳
	            	String datestr = list.get(i).getCreatedonutc();
	            	if(datestr!=null){
	            		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            		Date date = null;
	            		try {
	            			date = format.parse(datestr);
	            			Long createdonutclong = date.getTime();
	            			list.get(i).setCreatedonutclong(createdonutclong);
	            		} catch (ParseException e) {
	            			e.printStackTrace();
	            		}   
	            	}
	            }
	        }
    		map.put("list", list);
    		result.setSuccess(true);
    		result.setData(map);
        	result.setReturnCode("0000");
        	result.setMessage("获取朋友圈信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取朋友圈信息失败");
		}
        return result;
    }

    /**
     * 获取我评论的朋友圈分页信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircle/queryMyComment"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleQueryMyComment(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFriendsCircleRequest zlnfFriendsCircleRequest  = convert(data, ZlnfFriendsCircleRequest.class);
    	// 2. 业务检查
    	if (zlnfFriendsCircleRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
    		example.createCriteria().andUsercodeEqualTo(zlnfFriendsCircleRequest.getCreatorcode());
    		List<ZlnfFcustofomerin> customerInfoList = customerInfoService.getResult(example);
    		map.put("httppath", customerInfoList.get(0).getHttpphoto());
    		map.put("friendscircle", customerInfoList.get(0).getFriendscircle());
    		map.put("nickname", customerInfoList.get(0).getNickname());
    		ZlnfFriendsCircle zlnfFriendsCircle = new ZlnfFriendsCircle();
    		zlnfFriendsCircle.setNumber(zlnfFriendsCircleRequest.getNumber());
    		if(!"".equals(zlnfFriendsCircleRequest.getCreatorcode()) && zlnfFriendsCircleRequest.getCreatorcode()!=null){
    			zlnfFriendsCircle.setCreatorcode(zlnfFriendsCircleRequest.getCreatorcode());
    		}
    		List<ZlnfFriendsCircle> list = friendsCircleService.queryMyComment(zlnfFriendsCircle);
    		if(list.size()>0){
    			for(int i = 0;i<list.size();i++){
    				ZlnfFriendsCircleCommentExample friendsCircleCommentExample = new ZlnfFriendsCircleCommentExample();
    				ZlnfFriendsCircleCommentExample.Criteria commentCriteria = friendsCircleCommentExample.createCriteria();
    				commentCriteria.andFriendcodeEqualTo(list.get(i).getFriendcode());
    				commentCriteria.andIsdeletedEqualTo("0");
    				List<ZlnfFriendsCircleComment> commentList = friendsCircleCommentService.getResult(friendsCircleCommentExample);
    				List<ZlnfFriendsCircleComment> commentList1 = new ArrayList<>();
	            	List<ZlnfFriendsCircleComment> commentList2 = new ArrayList<>();
	            	int n = 0;
	            	int m = 0;
	            	int k = 0;
	            	Boolean isgood = false;
	            	for(int j = 0;j<commentList.size();j++){
	            		if("1".equals(commentList.get(j).getType())){
	            			if(n<10){
	            				commentList1.add(commentList.get(j));
	            				n++;
	            			}
	            			m++;
	            			if(commentList.get(j).getCreatorcode().equals(zlnfFriendsCircleRequest.getCreatorcode())){
	            				isgood = true;
	            			}
	            		}
	            	}
	            	list.get(i).setIsgood(isgood);
	            	list.get(i).setCommentList1(commentList1);
	            	list.get(i).setGoodnum(m);
	            	for(int j = 0;j<commentList.size();j++){
	            		if("2".equals(commentList.get(j).getType())){
	            			if(k<2){
	            				commentList2.add(commentList.get(j));
	            				k++;
	            			}
	            		}
	            	}	
	            	list.get(i).setCommentList2(commentList2);
    				ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
    				ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
    				annexCriteria.andDepttypeEqualTo("3");
    				annexCriteria.andDeptcodeEqualTo(list.get(i).getFriendcode());
    				List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    				list.get(i).setAnnexList(annexList);
    			}
    		}
    		map.put("list", list);
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("获取朋友圈信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取朋友圈信息失败");
    	}
    	return result;
    }
    
    /**
     * 朋删除友圈评论信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircleComment/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleCommentUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFriendsCircleCommentRequest zlnfFriendsCircleCommentRequest  = convert(data, ZlnfFriendsCircleCommentRequest.class);
        // 2. 业务检查
        if (zlnfFriendsCircleCommentRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
	        ZlnfFriendsCircleCommentExample friendsCircleCommentExample = new ZlnfFriendsCircleCommentExample();
	    	friendsCircleCommentExample.createCriteria().andCommentcodeEqualTo(zlnfFriendsCircleCommentRequest.getCommentcode());
	    	List<ZlnfFriendsCircleComment> commentList = friendsCircleCommentService.getResult(friendsCircleCommentExample);
	    	ZlnfFriendsCircleComment friendsCircleComment = new ZlnfFriendsCircleComment();
	    	friendsCircleComment.setId(commentList.get(0).getId());
	    	friendsCircleComment.setIsdeleted("1");
	    	friendsCircleCommentService.update(friendsCircleComment);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("删除朋友圈评论信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("删除朋友圈评论信息失败");
		}
        return result;
    }
    
    
    /**
     * 删除朋友圈信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/friendsCircle/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult friendsCircleUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFriendsCircleRequest zlnfFriendsCircleRequest  = convert(data, ZlnfFriendsCircleRequest.class);
        // 2. 业务检查
        if (zlnfFriendsCircleRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfFriendsCircleExample friendsCircleExample = new ZlnfFriendsCircleExample();
        friendsCircleExample.createCriteria().andFriendcodeEqualTo(zlnfFriendsCircleRequest.getFriendcode());
        List<ZlnfFriendsCircle> list = friendsCircleService.getResult(friendsCircleExample);
        try {
	        if(list.size()>0){
	        	ZlnfFriendsCircleCommentExample friendsCircleCommentExample = new ZlnfFriendsCircleCommentExample();
	        	friendsCircleCommentExample.createCriteria().andFriendcodeEqualTo(list.get(0).getFriendcode());
	        	List<ZlnfFriendsCircleComment> commentList = friendsCircleCommentService.getResult(friendsCircleCommentExample);
	        	for(int i = 0;i<commentList.size();i++){
	        		ZlnfFriendsCircleComment friendsCircleComment = new ZlnfFriendsCircleComment();
	        		friendsCircleComment.setId(commentList.get(i).getId());
	        		friendsCircleComment.setIsdeleted("1");
	        		friendsCircleCommentService.update(friendsCircleComment);
	        	}
	        	ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
	        	ZlnfAnnexExample.Criteria criteria = annexExample.createCriteria();
	        	criteria.andDepttypeEqualTo("3");
	        	criteria.andDeptcodeEqualTo(list.get(0).getFriendcode());
	        	List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
	        	for(int i = 0;i<annexList.size();i++){
	        		ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	        		zlnfAnnex.setId(annexList.get(i).getId());
	        		zlnfAnnex.setIsdeleted("1");
	        		annexService.update(zlnfAnnex);
	        	}
	        }
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("删除朋友圈信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("删除朋友圈信息失败");
		}
        return result;
    }
    
}
