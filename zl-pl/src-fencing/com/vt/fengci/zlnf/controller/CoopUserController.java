package com.vt.fengci.zlnf.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DesUtil;
import com.vt.base.util.SmsUtils;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.request.ZlnfCoopUserRequest;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICoopUserService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class CoopUserController extends BaseGatewayController {

	private static final long serialVersionUID = 8318828520852645142L;
	/**
     * member service
     */
    @Autowired
    private ICoopUserService coopUserService;
    
    @Autowired
    private ICoopInfoService coopInfoService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
    private IAnnexService annexService;
    
    
    /**
     * 注册合作社用户
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopuser/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopuserCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFcustofomerinRequest zlnfFcustofomerinRequest  = convert(data, ZlnfFcustofomerinRequest.class);
        // 2. 业务检查
        if (zlnfFcustofomerinRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        example.createCriteria().andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	result.setSuccess(false);
        	result.setReturnCode("0002");
        }else{
        	Date date = new Date();
        	//添加用户信息
        	String usercode = Uuid32.getUUID32();
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setUsercode(usercode);
        	zlnfFcustofomerin.setMobile(zlnfFcustofomerinRequest.getMobile());
        	String userName = zlnfFcustofomerinRequest.getUsername();
//        	try {
//				userName = new String(zlnfFcustofomerinRequest.getUsername().getBytes("ISO-8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        	zlnfFcustofomerin.setUsername(userName);
        	zlnfFcustofomerin.setLoginname(zlnfFcustofomerinRequest.getMobile());
        	zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
        	zlnfFcustofomerin.setRoletype("2");
        	zlnfFcustofomerin.setCreatedonutc(date);
        	zlnfFcustofomerin.setCreator(userName);
        	zlnfFcustofomerin.setCardnumber(zlnfFcustofomerinRequest.getCardnumber());
        	//添加合作社信息
        	ZlnfCoopUser zlnfCoopUser = new ZlnfCoopUser();
        	zlnfCoopUser.setUsercode(usercode);
        	zlnfCoopUser.setCoopcode(zlnfFcustofomerinRequest.getCoopcode());
        	zlnfCoopUser.setRoletype(zlnfFcustofomerinRequest.getRoletype2());
        	zlnfCoopUser.setCreatedonutc(date);
        	zlnfCoopUser.setCreator(userName);
        	//添加身份证照图片信息
    		String path = zlnfFcustofomerinRequest.getAnnexpath();
    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
        		String[] paths = path.split(",");
        		String[] annexnames = annexname.split(",");
        		String[] httpPaths = httpPath.split(",");
        		for(int i = 0;i<2;i++){
        			String annexcode = Uuid32.getUUID32();
        			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
        			zlnfAnnex.setDeptcode(usercode);
        			zlnfAnnex.setAnnexcode(annexcode);
        			zlnfAnnex.setAnnexname(annexnames[i]);
        			zlnfAnnex.setAnnexpath(paths[i]);
        			zlnfAnnex.setHttppath(httpPaths[i]);
        			zlnfAnnex.setDepttype("4");
        			zlnfAnnex.setCreator(userName);
        			zlnfAnnex.setCreatedonutc(date);
        			annexService.create(zlnfAnnex);
        		}
        	}
    		//推送消息
    		String coopcode = zlnfFcustofomerinRequest.getCoopcode();
    		ZlnfFcustofomerin custInfo = customerInfoService.queryCoopInfo(coopcode);
    		try {
    			ddgl_send2HZS1("9",custInfo.getMobile());
			} catch (Exception e) {
				// TODO: handle exception
				try {
					SmsUtils.sendMsg(custInfo.getMobile(), "【中粮农服】：您有一条新成员的申请消息,请及时确认", "");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
        	try {
            	customerInfoService.create(zlnfFcustofomerin);
            	coopUserService.create(zlnfCoopUser);
            	ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
            	ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
            	coopExample.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
            	List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
            	ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
            	coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
            	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
            	fcustofomerin.setZlnfCoopUser(coopList.get(0));
            	fcustofomerin.setLng(coopInfoList.get(0).getLng());
            	fcustofomerin.setLat(coopInfoList.get(0).getLat());
            	fcustofomerin.setType(coopInfoList.get(0).getType());
            	fcustofomerin.setRoletype2(coopList.get(0).getRoletype());
            	fcustofomerin.setEnterprise(coopInfoList.get(0).getEnterprise());
            	ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
    			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
    			annexCriteria.andDeptcodeEqualTo(coopList.get(0).getCoopcode());
    			annexCriteria.andDepttypeEqualTo("5");
    			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    			if(annexList.size()>0){
    				coopList.get(0).setHttppath(annexList.get(0).getHttppath());
    			}
            	result.setData(fcustofomerin);
            	result.setSuccess(true);
        		result.setReturnCode("0000");
        		result.setMessage("注册合作社用户成功");
			} catch (Exception e) {
				result.setSuccess(true);
        		result.setReturnCode("1111");
        		result.setMessage("注册合作社用户失败");
			}
        }
        return result;
    }
    
    private void ddgl_send2HZS1(String string, String mobile) {
		// TODO Auto-generated method stub
		
	}

	/**
     * 修改合作社个人资料
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopuser/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopuserUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFcustofomerinRequest zlnfFcustofomerinRequest  = convert(data, ZlnfFcustofomerinRequest.class);
        // 2. 业务检查
        if (zlnfFcustofomerinRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
        criteria.andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        criteria.andRoletypeEqualTo("2");
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	//修改用户基本信息
        	Date date = new Date();
        	String username = zlnfFcustofomerinRequest.getUsername();
        	String nickname = zlnfFcustofomerinRequest.getNickname();
        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setId(list.get(0).getId());
        	if(StringUtils.isNotBlank(username)){
        		zlnfFcustofomerin.setUsername(username);
        		zlnfFcustofomerin.setModifier(username);
        	}else{
        		zlnfFcustofomerin.setModifier(list.get(0).getUsername());
        	}
        	String sex = zlnfFcustofomerinRequest.getSex();
        	if(StringUtils.isNotBlank(sex)){
        		zlnfFcustofomerin.setSex(sex);
        	}
        	String loginpwd = zlnfFcustofomerinRequest.getLoginpwd();
        	if(StringUtils.isNotBlank(loginpwd)){
        		zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(loginpwd));
        	}
        	String mobile = zlnfFcustofomerinRequest.getNewmobile();
        	if(StringUtils.isNotBlank(mobile)){
        		zlnfFcustofomerin.setMobile(mobile);
        		zlnfFcustofomerin.setLoginname(mobile);
        	}
        	//修改头像,朋友圈背景
        	String path = zlnfFcustofomerinRequest.getAnnexpath();
    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
    			Boolean bo = false;
    			Boolean bo1 = false;//
    			String photo = "";
    			String httpphoto = "";
    			String friendscircle = "";
    			String httpfriend = "";
    			String[] paths = path.split(",");
    			String[] annexnames = annexname.split(",");
    			String[] httpPaths = httpPath.split(",");
    			for(int j = 0;j<paths.length;j++){
    				if("headPortrait".equals(annexnames[j])){
    					photo = paths[j];
    					httpphoto = httpPaths[j];
    					bo = true;
    				}
    				if("friendscircle".equals(annexnames[j])){
    					friendscircle = paths[j];
    					httpfriend = httpPaths[j];
    					bo1 = true;
    				}
    			}
    			if(bo){
    				if(list.get(0).getPhoto()!=null){
    					File file = new File(list.get(0).getPhoto());
    					file.delete();
    				}
    				zlnfFcustofomerin.setPhoto(photo);
    				zlnfFcustofomerin.setHttpphoto(httpphoto);
    			}
    			if(bo1){
    				if(list.get(0).getFriendscircle()!=null){
    					File file = new File(list.get(0).getFriendscircle());
    					file.delete();
    				}
    				zlnfFcustofomerin.setFriendscircle(friendscircle);
    				zlnfFcustofomerin.setHttpfriend(httpfriend);
    			}
    		}
        	String cardnumber = zlnfFcustofomerinRequest.getCardnumber();
        	if(StringUtils.isNotBlank(cardnumber)){
        		zlnfFcustofomerin.setCardnumber(cardnumber);
        	}
        	String email = zlnfFcustofomerinRequest.getEmail();
        	if(StringUtils.isNotBlank(email)){
        		zlnfFcustofomerin.setEmail(email);
        	}
        	String isdeleted = zlnfFcustofomerinRequest.getIsdeleted();
        	if(StringUtils.isNotBlank(isdeleted)){
        		zlnfFcustofomerin.setIsdeleted(isdeleted);
        	}
        	String isaudit = zlnfFcustofomerinRequest.getIsaudit();
        	if(StringUtils.isNotBlank(isaudit)){
        		zlnfFcustofomerin.setIsaudit(isaudit);
        		if("1".equals(isaudit)){
        			try {
        				ddgl_send2HZS1("10",list.get(0).getMobile());
					} catch (Exception e) {
						// TODO: handle exception
						try {
							SmsUtils.sendMsg(list.get(0).getMobile(), "您的申请已经审核通过", "");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
        		}else if("2".equals(isaudit)){
        			try {
        				ddgl_send2HZS1("11",list.get(0).getMobile());
					} catch (Exception e) {
						// TODO: handle exception
						try {
							SmsUtils.sendMsg(list.get(0).getMobile(), "您的申请审核未通过", "");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
        		}
        	}
        	if(StringUtils.isNotBlank(nickname)){
        		zlnfFcustofomerin.setNickname(nickname);
        	}
        	zlnfFcustofomerin.setUpdatedonutc(date);
        	//修改合作社信息
        	ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
        	coopExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        	List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
        	ZlnfCoopUser zlnfCoopUser = new ZlnfCoopUser();
        	zlnfCoopUser.setId(coopList.get(0).getId());
        	String roletype = zlnfFcustofomerinRequest.getRoletype2();
        	if(StringUtils.isNotBlank(roletype)){
        		zlnfCoopUser.setRoletype(roletype);
        	}
        	String status = zlnfFcustofomerinRequest.getStatus();
        	if(StringUtils.isNotBlank(status)){
        		zlnfCoopUser.setStatus(status);
        	}
        	if(StringUtils.isNotBlank(isdeleted)){
        		zlnfCoopUser.setIsdeleted(isdeleted);
        	}
        	if(StringUtils.isNotBlank(isaudit)){
        		zlnfCoopUser.setIsaudit(isaudit);
        	}
        	if(StringUtils.isNotBlank(username)){
        		zlnfCoopUser.setModifier(username);
        		zlnfCoopUser.setCreator(username);
        	}else{
        		zlnfCoopUser.setModifier(list.get(0).getUsername());
        		zlnfCoopUser.setCreator(list.get(0).getUsername());
        	}
        	zlnfCoopUser.setUpdatedonutc(date);
        	//修改身份证
        	ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
        	ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
        	annexCriteria.andDeptcodeEqualTo(list.get(0).getUsercode());
        	annexCriteria.andDepttypeEqualTo("4");
        	List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    		if(StringUtils.isNotBlank(path)){
    			for(int i = 0;i<annexList.size();i++){
    				File file = new File(annexList.get(i).getAnnexpath());
            		file.delete();
            		String[] paths = path.split(",");
            		String[] annexnames = annexname.split(",");
            		String[] httpPaths = httpPath.split(",");
            		ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
            		zlnfAnnex.setId(annexList.get(i).getId());
            		for(int j = 0;j<paths.length;j++){
            			if(!"headPortrait".equals(annexnames[j]) && !"friendscircle".equals(annexnames[j])){
            				if(annexList.get(i).getAnnexname().equals(annexnames[j])){
            					zlnfAnnex.setAnnexpath(paths[j]);
            					zlnfAnnex.setHttppath(httpPaths[j]);
            				}
            			}
            		}
            		if(StringUtils.isNotBlank(username)){
            			zlnfAnnex.setModifier(username);
                	}else{
                		zlnfAnnex.setModifier(list.get(0).getUsername());
                	}
            		zlnfAnnex.setUpdatedonutc(date);
        			annexService.update(zlnfAnnex);
    			}
        	}
    		//修改合作社信息
        	ZlnfCoopInfoExample coopInfoExample1 = new ZlnfCoopInfoExample();
        	coopInfoExample1.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
        	List<ZlnfCoopInfo> coopInfoList1 = coopInfoService.getResult(coopInfoExample1);
        	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
        	zlnfCoopInfo.setId(coopInfoList1.get(0).getId());
        	String enterprise = zlnfFcustofomerinRequest.getEnterprisecreditcode();
        	if(StringUtils.isNotBlank(enterprise)){
        		zlnfCoopInfo.setEnterprise(enterprise);
        		zlnfCoopInfo.setIsaudit("0");
        	}else{
        		zlnfCoopInfo.setIsaudit("1");
        	}
        	try {
        		customerInfoService.update(zlnfFcustofomerin);
        		coopUserService.update(zlnfCoopUser);
        		coopInfoService.update(zlnfCoopInfo);
        		result.setSuccess(true);
        		ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
        		ZlnfCoopUserExample coopExample1 = new ZlnfCoopUserExample();
        		coopExample1.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
    			List<ZlnfCoopUser> coopList1 = coopUserService.getResult(coopExample1);
    			fcustofomerin.setRoletype2(coopList1.get(0).getRoletype());
    			fcustofomerin.setZlnfCoopUser(coopList1.get(0));
    			ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
    			coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList1.get(0).getCoopcode());
    			List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
    			fcustofomerin.setLng(coopInfoList.get(0).getLng());
    			fcustofomerin.setLat(coopInfoList.get(0).getLat());
    			fcustofomerin.setType(coopInfoList.get(0).getType());
    			fcustofomerin.setCoopname(coopInfoList.get(0).getCoopname());
    			fcustofomerin.setVip(coopInfoList.get(0).getVip());
        		result.setData(fcustofomerin);
            	result.setReturnCode("0000");
            	result.setMessage("修改个人资料成功");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
	        	result.setReturnCode("1111");
	        	result.setMessage("修改个人资料失败");
			}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在");
        }
        return result;
    }

    /**
     * 获取合作社成员信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopuser/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult ffarmMachineyQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	 // 1. 转换数据
    	ZlnfCoopUserRequest zlnfCoopUserRequest  = convert(data, ZlnfCoopUserRequest.class);
        // 2. 业务检查
        if (zlnfCoopUserRequest == null) {
            reject("user.activate.model.convert.error");
        }
    	OptResult result = OptResult.success();
    	try {
    		ZlnfCoopUserExample coopUserExample = new ZlnfCoopUserExample();
    		ZlnfCoopUserExample.Criteria criteria = coopUserExample.createCriteria();
    		criteria.andCoopcodeEqualTo(zlnfCoopUserRequest.getCoopcode());
    		criteria.andIsdeletedEqualTo("0");
	    	List<ZlnfCoopUser> coopUserList = coopUserService.getResult(coopUserExample);
	    	List<ZlnfFcustofomerin> fcustofomerinList = new ArrayList<>();
	    	for(int i = 0;i<coopUserList.size();i++){
	    		ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
	    		ZlnfFcustofomerinExample.Criteria custCriteria = fcustofomerinExample.createCriteria();
	    		custCriteria.andUsercodeEqualTo(coopUserList.get(i).getUsercode());
	    		custCriteria.andIsdeletedEqualTo("0");
	    		List<ZlnfFcustofomerin> fcustofomerinList1 = customerInfoService.getResult(fcustofomerinExample);
	    		fcustofomerinList1.get(0).setRoletype2(coopUserList.get(i).getRoletype());
	    		fcustofomerinList.add(fcustofomerinList1.get(0));
	    	}
    		result.setSuccess(true);
    		result.setData(fcustofomerinList);
    		result.setReturnCode("0000");
    		result.setMessage("获取合作社成员信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取合作社成员信息失败");
    	}
    	return result;
    }
    
}
