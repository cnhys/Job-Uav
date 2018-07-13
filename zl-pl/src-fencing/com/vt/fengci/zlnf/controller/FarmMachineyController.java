package com.vt.fengci.zlnf.controller;

import java.io.File;
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
import com.vt.base.util.DesUtil;
import com.vt.base.util.SmsUtils;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfFarmMachineyMerchant;
import com.vt.fencing.model.ZlnfFarmMachineyMerchantExample;
import com.vt.fencing.model.ZlnfFarmmachineyUser;
import com.vt.fencing.model.ZlnfFarmmachineyUserExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfOrderUser;
import com.vt.fencing.model.ZlnfOrderUserExample;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IFarmMachineyMerchantService;
import com.vt.fengci.zlnf.service.IFarmMachineyService;
import com.vt.fengci.zlnf.service.IOrderUserService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FarmMachineyController extends BaseGatewayController {

	private static final long serialVersionUID = 2087891244069760060L;

	/**
     * member service
     */
	@Autowired
	private IFarmMachineyMerchantService farmMachineyMerchantService;
	
    @Autowired
    private IFarmMachineyService farmMachineyService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
    private IAnnexService annexService;
    
    @Autowired
    private IOrderUserService orderUserService;
    
    
    
    /**
     * 注册农机服务商用户
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachiney/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyCreate(String channel, String key, String data) {
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
        criteria.andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
        criteria.andIsdeletedEqualTo("0");
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	result.setData(false);
        	result.setReturnCode("0002");
        }else{
        	Date date = new Date();
        	//添加用户信息
        	String usercode = Uuid32.getUUID32();
        	String annexcode = Uuid32.getUUID32();
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setUsercode(usercode);
        	zlnfFcustofomerin.setMobile(zlnfFcustofomerinRequest.getMobile());
        	String userName = zlnfFcustofomerinRequest.getUsername();
        	zlnfFcustofomerin.setUsername(userName);
        	zlnfFcustofomerin.setLoginname(zlnfFcustofomerinRequest.getMobile());
        	zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
        	zlnfFcustofomerin.setRoletype("1");
        	zlnfFcustofomerin.setCreatedonutc(new Date());
        	zlnfFcustofomerin.setCreator(userName);
        	//添加农机服务商信息
        	ZlnfFarmmachineyUser zlnfFarmmachineyUser = new ZlnfFarmmachineyUser();
        	zlnfFarmmachineyUser.setUsercode(usercode);
        	ZlnfFarmMachineyMerchantExample farmchineyExample = new ZlnfFarmMachineyMerchantExample();
        	farmchineyExample.createCriteria().andMerchantcodeEqualTo(zlnfFcustofomerinRequest.getFarmmachineycode());
			List<ZlnfFarmMachineyMerchant> farmchineyList = farmMachineyMerchantService.getResult(farmchineyExample);
        	zlnfFarmmachineyUser.setFarmmachineyname(farmchineyList.get(0).getMerchantname());
        	zlnfFarmmachineyUser.setRoletype(zlnfFcustofomerinRequest.getRoletype1());
        	zlnfFarmmachineyUser.setFarmmachineycode(zlnfFcustofomerinRequest.getFarmmachineycode());
        	zlnfFarmmachineyUser.setCreatedonutc(date);
        	//添加身份证照图片信息
        	String path = zlnfFcustofomerinRequest.getAnnexpath();
    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
        		String[] paths = path.split(",");
        		String[] annexnames = annexname.split(",");
        		String[] httpPaths = httpPath.split(",");
        		for(int i = 0;i<2;i++){
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
    		String farmmachineycode = zlnfFcustofomerinRequest.getFarmmachineycode();
    		ZlnfFcustofomerin custInfo = customerInfoService.queryFarmMachineyMerchant(farmmachineycode);
    		ddgl_send2HZS1("9",custInfo.getMobile());
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
            	farmMachineyService.create(zlnfFarmmachineyUser);
            	ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
            	ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
        		farmExample.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
        		List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample);
        		fcustofomerin.setZlnfFarmmachineyUser(farmList.get(0));
        		ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
        		farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
        		List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
        		fcustofomerin.setLng(machineyList.get(0).getLng());
        		fcustofomerin.setLat(machineyList.get(0).getLat());
        		fcustofomerin.setType(machineyList.get(0).getRegisttype());
        		fcustofomerin.setRoletype1(farmList.get(0).getRoletype());
            	result.setData(fcustofomerin);
            	result.setSuccess(true);
        		result.setReturnCode("0000");
        		result.setMessage("注册农机服务商用户成功");
			} catch (Exception e) {
				result.setSuccess(true);// 注册农机服务商失败
        		result.setReturnCode("1111");
        		result.setMessage("注册农机服务商用户失败");
			}
        	
        }
        return result;
    }
    
    private void ddgl_send2HZS1(String string, String mobile) {
		// TODO Auto-generated method stub
		
	}

	/**
     * 修改农机服务商个人资料
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachiney/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyUpdate(String channel, String key, String data) {
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
        criteria.andRoletypeEqualTo("1");
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
        	}
        	if(StringUtils.isNotBlank(nickname)){
        		zlnfFcustofomerin.setNickname(nickname);
        	}
        	zlnfFcustofomerin.setCreatedonutc(date);
        	//修改农机服务商信息
        	ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
        	farmExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        	List<ZlnfFarmmachineyUser> farmlist = farmMachineyService.getResult(farmExample);
        	ZlnfFarmmachineyUser zlnfFarmmachineyUser = new ZlnfFarmmachineyUser();
        	zlnfFarmmachineyUser.setId(farmlist.get(0).getId());
        	String roletype = zlnfFcustofomerinRequest.getRoletype1();
        	if(StringUtils.isNotBlank(roletype)){
        		zlnfFarmmachineyUser.setRoletype(roletype);
        	}
        	String status = zlnfFcustofomerinRequest.getStatus();
        	if(StringUtils.isNotBlank(status)){
        		zlnfFarmmachineyUser.setStatus(status);
        	}
        	if(StringUtils.isNotBlank(isdeleted)){
        		zlnfFarmmachineyUser.setIsdeleted(isdeleted);
        	}
        	if(StringUtils.isNotBlank(isaudit)){
        		zlnfFarmmachineyUser.setIsaudit(isaudit);
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
        	if(StringUtils.isNotBlank(username)){
        		zlnfFarmmachineyUser.setModifier(username);
        	}else{
        		zlnfFarmmachineyUser.setModifier(list.get(0).getUsername());
        	}
        	zlnfFarmmachineyUser.setCreatedonutc(date);
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
			//修改农机服务商信息
        	ZlnfFarmMachineyMerchantExample farmMachineytExample = new ZlnfFarmMachineyMerchantExample();
        	farmMachineytExample.createCriteria().andMerchantcodeEqualTo(farmlist.get(0).getFarmmachineycode());
        	List<ZlnfFarmMachineyMerchant> farmMachineyList = farmMachineyMerchantService.getResult(farmMachineytExample);
        	ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
        	zlnfFarmMachineyMerchant.setId(farmMachineyList.get(0).getId());
        	if("2".equals(farmlist.get(0).getRoletype())){
        		if(StringUtils.isNotBlank(username)){
        			zlnfFarmMachineyMerchant.setLinkname(username);
        			farmMachineyMerchantService.update(zlnfFarmMachineyMerchant);
        		}
        	}
        	try {
        		customerInfoService.update(zlnfFcustofomerin);
        		farmMachineyService.update(zlnfFarmmachineyUser);
        		result.setSuccess(true);
        		result.setData(customerInfoService.getResult(example).get(0));
            	result.setReturnCode("0000");
            	result.setMessage("修改农机服务商用户信息成功");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
	        	result.setReturnCode("1111");
	        	result.setMessage("修改农机服务商用户信息失败");
			}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在");
        }
        return result;
    }
    
    /**
     * 获取农机服务商成员信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachiney/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult ffarmMachineyQuery(String channel, String key, String data) {
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
    	OptResult result = OptResult.success();
    	try {
    		Map<String, Object> map = new HashMap<>();
    		String ordercode = zlnfFcustofomerinRequest.getOrdercode();
    		ZlnfOrderUserExample orderUserExample = new ZlnfOrderUserExample();
			orderUserExample.createCriteria().andOrdercodeEqualTo(ordercode);
			System.out.println("-------------------------------->2");
			List<ZlnfOrderUser> orderuserList = orderUserService.getResult(orderUserExample);
			if(orderuserList.size()>0){
				map.put("orderuser", orderuserList.get(0));
				System.out.println("-------------------------------->2");
			}
	    	ZlnfFarmmachineyUser zlnfFarmmachineyUser = new ZlnfFarmmachineyUser();
	    	zlnfFarmmachineyUser.setFarmmachineycode(zlnfFcustofomerinRequest.getFarmmachineycode());
	    	String roletype = zlnfFcustofomerinRequest.getRoletype1();
        	if(StringUtils.isNotBlank(roletype)){
        		zlnfFarmmachineyUser.setRoletype(roletype);
        	}
	    	List<ZlnfFarmmachineyUser> farmmachineyUserList = farmMachineyService.queryUser(zlnfFarmmachineyUser);
	    	System.out.println("-------------------------------->3");
	    	List<ZlnfFcustofomerin> fcustofomerinList = new ArrayList<>();
	    	for(int i = 0;i<farmmachineyUserList.size();i++){
	    		ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
	    		ZlnfFcustofomerinExample.Criteria criteria = fcustofomerinExample.createCriteria();
	    		criteria.andUsercodeEqualTo(farmmachineyUserList.get(i).getUsercode());
	    		if (!StringUtils.isEmpty(ordercode) && !StringUtils.isEmpty(roletype)) {
	    			criteria.andIsauditEqualTo("1");
	    	    }
	    		List<ZlnfFcustofomerin> fcustofomerinList1 = customerInfoService.getResult(fcustofomerinExample);
	    		if(fcustofomerinList1.size()>0){
	    			fcustofomerinList1.get(0).setRoletype1(farmmachineyUserList.get(i).getRoletype());
	    			fcustofomerinList.add(fcustofomerinList1.get(0));
	    		}
	    	}
	    	System.out.println("-------------------------------->4");
	    	map.put("fcustofomerinList", fcustofomerinList);
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("获取农机服务商成员信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取农机服务商成员信息失败");
    	}
    	return result;
    }

}
