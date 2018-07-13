package com.vt.fengci.zlnf.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DesUtil;
import com.vt.base.util.DigestUtils;
import com.vt.base.util.SmsUtils;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfBinding;
import com.vt.fencing.model.ZlnfBindingExample;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;
import com.vt.fencing.model.ZlnfFarmMachineyMerchant;
import com.vt.fencing.model.ZlnfFarmMachineyMerchantExample;
import com.vt.fencing.model.ZlnfFarmmachineyUser;
import com.vt.fencing.model.ZlnfFarmmachineyUserExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfLkyhExample;
import com.vt.fencing.request.ZlnfCoopInfoRequest;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.IBindingService;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICoopUserService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IFarmMachineyMerchantService;
import com.vt.fengci.zlnf.service.IFarmMachineyService;
import com.vt.fengci.zlnf.service.ILkyhService;
import com.yeepay.InstantPayService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class CustomerInfoController extends BaseGatewayController {

	private static final long serialVersionUID = -9008967625028458097L;

	/**
     * member service
     */
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
	private IFarmMachineyMerchantService farmMachineyMerchantService;
    
    @Autowired
    private ICoopInfoService coopInfoService;
    
    @Autowired
    private ICoopUserService coopUserService;
    
    @Autowired
    private IFarmMachineyService farmMachineyService;
    
    @Autowired
    private IAnnexService annexService;
    
    @Autowired
    private IBindingService bindingService;

    @Autowired
    private ILkyhService lkyhService;
    
    /**
     * 验证旧密码是否正确
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/isOldLoginpwd"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult isOldLoginpwd(String channel, String key, String data) {
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
        criteria.andLoginpwdEqualTo(DesUtil.desCrypto(zlnfFcustofomerinRequest.getOldLoginpwd()));
        criteria.andIsdeletedEqualTo("0");
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("旧密码输入正确");
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("旧密码输入错误");
        }
        return result;
    }

    /**
     * 验证手机号是否存在
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/isExistence"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult isExistence(String channel, String key, String data) {
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
    	ZlnfFcustofomerinExample.Criteria exampleCriteria = example.createCriteria();
    	exampleCriteria.andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
    	exampleCriteria.andIsdeletedEqualTo("0");
    	int count = customerInfoService.getResultCount(example);
    	List<ZlnfFcustofomerin> list1 = customerInfoService.getResult(example);
    	Integer total=list1.size();
    	//未设置密码则删除
    	if(!"02".equals(zlnfFcustofomerinRequest.getTrade())){
    	for(int j=0;j<list1.size();j++){
    		if("2".equals(list1.get(j).getRoletype())&&(list1.get(j).getLoginpwd()==null||list1.get(j).getLoginpwd()=="")){
    			//删除用户
    			ZlnfFcustofomerin  zlnfFcustofomerin=new ZlnfFcustofomerin();
    			zlnfFcustofomerin.setId(list1.get(j).getId());
    			zlnfFcustofomerin.setIsdeleted("1");
    			customerInfoService.update(zlnfFcustofomerin);
    			//删除合作社用户
    			ZlnfCoopUserExample zlnfCoopUserExample=new ZlnfCoopUserExample();
    			ZlnfCoopUserExample.Criteria coopCriteria = zlnfCoopUserExample.createCriteria();
    			coopCriteria.andIsdeletedEqualTo("0");
    			coopCriteria.andUsercodeEqualTo(list1.get(j).getUsercode());
    			List<ZlnfCoopUser> list2 =  coopUserService.getResult(zlnfCoopUserExample);
    			ZlnfCoopUser  coopUser=new ZlnfCoopUser();
    			coopUser.setId(list2.get(0).getId());
    			coopUser.setIsdeleted("1");
    			coopUserService.update(coopUser);
    			//删除合作社
    			if(list2.size()>0){
    			ZlnfCoopInfoExample zlnfCoopInfoExamp=new ZlnfCoopInfoExample();
    			ZlnfCoopInfoExample.Criteria coopinfocriteria=zlnfCoopInfoExamp.createCriteria();
    			coopinfocriteria.andIsdeletedEqualTo("0");
    			coopinfocriteria.andCoopcodeEqualTo(list2.get(0).getCoopcode());
    			List<ZlnfCoopInfo> coopinfolist=coopInfoService.getResult(zlnfCoopInfoExamp);
    			ZlnfCoopInfo  zlnfCoopInfo=new ZlnfCoopInfo();
    			zlnfCoopInfo.setId(coopinfolist.get(0).getId());
    			zlnfCoopInfo.setIsdeleted("1");
    			coopInfoService.update(zlnfCoopInfo);
    			total--;
    			
    			}
    		}
    	}
    	}
    	Integer farmMachineyNum=0;
    	for(int i=0;i<list1.size();i++){
    		if("1".equals(list1.get(i).getRoletype())){
    			farmMachineyNum++;
    		}
    	}
    	OptResult result = OptResult.success();
    	if(total>0){
    		if("03".equals(zlnfFcustofomerinRequest.getTrade())&&farmMachineyNum==0){
    			
    			exampleCriteria.andIsauditEqualTo("1");
    			ZlnfCoopUserExample coopUserExample = new ZlnfCoopUserExample();
    			ZlnfCoopUserExample.Criteria coopCriteria = coopUserExample.createCriteria();
    			coopCriteria.andIsdeletedEqualTo("0");
    			coopCriteria.andUsercodeEqualTo(list1.get(0).getUsercode());
    			List<ZlnfCoopUser> list2 =  coopUserService.getResult(coopUserExample);
    			ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
    			ZlnfCoopInfoExample.Criteria coopInfoCriteria = coopInfoExample.createCriteria();
    			if(list2.size()>0){
    				coopInfoCriteria.andCoopcodeEqualTo(list2.get(0).getCoopcode());
        			coopInfoCriteria.andIsdeletedEqualTo("0");
        			//coopInfoCriteria.andIsauditEqualTo("1");
        			int count1 = coopInfoService.getResultCount(coopInfoExample);
        			if(count1>0){
        				result.setSuccess(true);
            			result.setReturnCode("0000");
            			result.setMessage("该用户已激活");
        			/*}else{
        				result.setSuccess(true);
            			result.setReturnCode("1111");
            			result.setMessage("该用户未激活");*/
        			}
    			}else{
    	    		result.setSuccess(false);
    	    		result.setReturnCode("1111");
    	    		result.setMessage("该用户未注册");
    	    	}
    		}else{
    			result.setSuccess(true);
    			result.setReturnCode("0000");
    			result.setMessage("该用户已注册");
    		}
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("该用户未注册");
    	}
    	return result;
    }
    
    /**
     * 发送验证码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customermember/send-valid-code"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult apisendValidCode(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        ZlnfFcustofomerinRequest request = convert(data, ZlnfFcustofomerinRequest.class);
        //2. 检查
        if (request == null) {
            reject("user.validcode.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            reject("user.validcode.mobile.empty");
        }
        if (StringUtils.isEmpty(request.getTrade())) {
            reject("user.validcode.trade.empty");
        }

        //3.生成验证码
        String validCode = DigestUtils.generateRandomNumber(6);
        logger.debug("手机号为[{}],交易为[{}],生成的验证码为[{}]",
                new Object[] {request.getMobile(), request.getTrade(), validCode});
        // 4.发送验证码
        String prefix = "";
        String message = "";
        if (StringUtils.equals("01", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在进行注册";
            message = prefix + "，验证码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。";
        } else if (StringUtils.equals("02", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在修改密码";
            message = prefix + "，验证码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。";
        }  else if (StringUtils.equals("03", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在激活账号";
            message = prefix + "，验证码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。【中粮贸易】";
        } else if (StringUtils.equals("04", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在获取动态登录密码";
            message = prefix + "，登录密码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。";
        } else if (StringUtils.equals("05", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在绑定微信";
            message = prefix + "，验证码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。";
        } else if (StringUtils.equals("06", request.getTrade())) {
            prefix = "【中粮农服】：您的手机号正在修改支付密码";
            message = prefix + "，验证码为" + validCode + "，为了您的账号安全请勿泄露，如果不是您本人操作请忽略。";
        } 
        try {
			SmsUtils.sendMsg(request.getMobile(), message, "");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // 5.返回数据
        OptResult result = OptResult.success();
        result.setData(validCode);
        return result;
    }
    
    /**
     * 用户登录接口
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoLogin(String channel, String key, String data, HttpServletRequest request) {
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
        criteria.andLoginnameEqualTo(zlnfFcustofomerinRequest.getLoginname());
        criteria.andLoginpwdEqualTo(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
        criteria.andIsdeletedEqualTo("0");
        List<ZlnfFcustofomerin> userlist = customerInfoService.getResult(example);
        OptResult result = OptResult.success();
        if(userlist.size()>0){
        	List<String> audit=new ArrayList<String>();
        	audit.add("0");
        	audit.add("1");
        	criteria.andIsauditIn(audit);
        	int count1 = customerInfoService.getResultCount(example);
        	if(count1>0){
	            	if("1".equals(userlist.get(0).getRoletype())){
	        			ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
	        			farmExample.createCriteria().andUsercodeEqualTo(userlist.get(0).getUsercode());
	        			List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample);
	        			if(farmList.size()>0&&("1".equals(farmList.get(0).getRoletype())||"6".equals(farmList.get(0).getRoletype())||"3".equals(farmList.get(0).getRoletype())||"4".equals(farmList.get(0).getRoletype()))&&"0".equals(userlist.get(0).getIsaudit())){
	        				if("0".equals(userlist.get(0).getIsaudit())){
	        					result.setSuccess(false);
		                    	result.setReturnCode("0005");
		                    	result.setMessage("用户未审核");
	        				}
	        			}else{
	        			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
	        			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
	        			annexCriteria.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
	        			annexCriteria.andDepttypeEqualTo("1");
	        			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
	        			if(annexList.size()>0){
	        				farmList.get(0).setHttppath(annexList.get(0).getHttppath());
	        			}
	        			userlist.get(0).setRoletype1(farmList.get(0).getRoletype());
	        			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
	        			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
	        			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
	        			farmList.get(0).setEnterprise(machineyList.get(0).getEnterprise());
	        			userlist.get(0).setZlnfFarmmachineyUser(farmList.get(0));
	        			userlist.get(0).setLng(machineyList.get(0).getLng());
	        			userlist.get(0).setLat(machineyList.get(0).getLat());
	        			userlist.get(0).setType(machineyList.get(0).getRegisttype());
	        			userlist.get(0).setMerchantname(machineyList.get(0).getMerchantname());
	        			userlist.get(0).setProvice(machineyList.get(0).getProvice());
	        			userlist.get(0).setCity(machineyList.get(0).getCity());
	        			userlist.get(0).setEnterprise(machineyList.get(0).getEnterprise());
	        			result.setSuccess(true);
		            	result.setData(userlist.get(0));
		            	result.setReturnCode("0000");
		            	result.setMessage("登录成功");
	        			}
	        		}else if("2".equals(userlist.get(0).getRoletype())){
	        			//检查是否为未审核的合作社成员
	        			ZlnfCoopUserExample  coopuserexample=new ZlnfCoopUserExample();
	                	ZlnfCoopUserExample.Criteria coopusercriteria=coopuserexample.createCriteria();
	                	coopusercriteria.andUsercodeEqualTo(userlist.get(0).getUsercode());
	                	coopusercriteria.andIsdeletedEqualTo("0");
	                	List<ZlnfCoopUser> coopuserList=coopUserService.getResult(coopuserexample);
	        			if(coopuserList.size()>0&&"4".equals(coopuserList.get(0).getRoletype())&&"0".equals(userlist.get(0).getIsaudit())){
	        				result.setSuccess(false);
	                    	result.setReturnCode("0005");
	                    	result.setMessage("用户未审核");
	        			}else{
	        				ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
		        			coopExample.createCriteria().andUsercodeEqualTo(userlist.get(0).getUsercode());
		        			List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
		        			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
		        			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
		        			annexCriteria.andDeptcodeEqualTo(coopList.get(0).getCoopcode());
		        			annexCriteria.andDepttypeEqualTo("5");
		        			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
		        			if(annexList.size()>0){
		        				coopList.get(0).setHttppath(annexList.get(0).getHttppath());
		        			}
		        			userlist.get(0).setRoletype2(coopList.get(0).getRoletype());
		        			userlist.get(0).setZlnfCoopUser(coopList.get(0));
		        			ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
		        			coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
		        			List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
		        			userlist.get(0).setLng(coopInfoList.get(0).getLng());
		        			userlist.get(0).setLat(coopInfoList.get(0).getLat());
		        			userlist.get(0).setType(coopInfoList.get(0).getType());
		        			userlist.get(0).setCoopname(coopInfoList.get(0).getCoopname());
		        			userlist.get(0).setVip(coopInfoList.get(0).getVip());
		//        			if(coopInfoList.get(0).getVip()!=null){
		//        				if("1".equals(coopInfoList.get(0).getVip())){
		//        					list.get(0).setProvice(coopInfoList.get(0).getProvice());
		//        					list.get(0).setCity(coopInfoList.get(0).getCity());
		//        				}
		//        			}
		        			userlist.get(0).setProvice(coopInfoList.get(0).getProvice());
		        			userlist.get(0).setCity(coopInfoList.get(0).getCity());
		        			userlist.get(0).setAddress(coopInfoList.get(0).getAddress());
		        			userlist.get(0).setEnterprise(coopInfoList.get(0).getEnterprise());
		        			result.setSuccess(true);
			            	result.setData(userlist.get(0));
			            	result.setReturnCode("0000");
			            	result.setMessage("登录成功");
	                	}
	        		}
        	}else{
        		result.setSuccess(false);
            	result.setReturnCode("0004");
            	result.setMessage("用户未通过审核");
        	}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在或密码错误");
        }
        return result;
    }

    /**
     * 动态用户登录接口
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfochange/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfochangeLogin(String channel, String key, String data) {
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
        	criteria.andIsauditEqualTo("1");
        	int count1 = customerInfoService.getResultCount(example);
        	if(count1>0){
        		try {
            		List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
            		if("1".equals(list.get(0).getRoletype())){
            			ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
            			farmExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
            			List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample);
            			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
            			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
            			annexCriteria.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
            			annexCriteria.andDepttypeEqualTo("1");
            			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
            			if(annexList.size()>0){
            				farmList.get(0).setHttppath(annexList.get(0).getHttppath());
            			}
            			list.get(0).setRoletype1(farmList.get(0).getRoletype());
            			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
            			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
            			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
            			farmList.get(0).setEnterprise(machineyList.get(0).getEnterprise());
            			list.get(0).setZlnfFarmmachineyUser(farmList.get(0));
            			list.get(0).setLng(machineyList.get(0).getLng());
            			list.get(0).setLat(machineyList.get(0).getLat());
            			list.get(0).setType(machineyList.get(0).getRegisttype());
            			list.get(0).setMerchantname(machineyList.get(0).getMerchantname());
            			list.get(0).setProvice(machineyList.get(0).getProvice());
    					list.get(0).setCity(machineyList.get(0).getCity());
    					list.get(0).setEnterprise(machineyList.get(0).getEnterprise());
            			result.setSuccess(true);
                    	result.setData(list.get(0));
                    	result.setReturnCode("0000");
                    	result.setMessage("登录成功");
            		}else if("2".equals(list.get(0).getRoletype())){
            			ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
            			coopExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
            			List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
            			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
            			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
            			annexCriteria.andDeptcodeEqualTo(coopList.get(0).getCoopcode());
            			annexCriteria.andDepttypeEqualTo("5");
            			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
            			if(annexList.size()>0){
            				coopList.get(0).setHttppath(annexList.get(0).getHttppath());
            			}
            			list.get(0).setRoletype2(coopList.get(0).getRoletype());
            			list.get(0).setZlnfCoopUser(coopList.get(0));
            			ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
            			coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
            			List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
            			list.get(0).setLng(coopInfoList.get(0).getLng());
            			list.get(0).setLat(coopInfoList.get(0).getLat());
            			list.get(0).setType(coopInfoList.get(0).getType());
            			list.get(0).setCoopname(coopInfoList.get(0).getCoopname());
            			list.get(0).setVip(coopInfoList.get(0).getVip());
//            			if(coopInfoList.get(0).getVip()!=null){
//            				if("1".equals(coopInfoList.get(0).getVip())){
//            					list.get(0).setProvice(coopInfoList.get(0).getProvice());
//            					list.get(0).setCity(coopInfoList.get(0).getCity());
//            				}
//            			}
            			list.get(0).setProvice(coopInfoList.get(0).getProvice());
    					list.get(0).setCity(coopInfoList.get(0).getCity());
            			list.get(0).setAddress(coopInfoList.get(0).getAddress());
            			list.get(0).setEnterprise(coopInfoList.get(0).getEnterprise());
            			if("1".equals(coopInfoList.get(0).getIsaudit())){
            				result.setSuccess(true);
                        	result.setData(list.get(0));
                        	result.setReturnCode("0000");
                        	result.setMessage("登录成功");
            			}else{
            				result.setSuccess(false);
                        	result.setReturnCode("0003");
                        	result.setMessage("合作社未激活");
            			}
            		}
    			} catch (Exception e) {
    				// TODO: handle exception
    				result.setSuccess(true);
                	result.setReturnCode("0000");
                	result.setMessage("登录失败");
    			}
        	}else{
        		result.setSuccess(false);
            	result.setReturnCode("0004");
            	result.setMessage("用户未通过审核");
        	}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在");
        }
        return result;
    }
    
    
    /**
     * 忘记密码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoUpdate(String channel, String key, String data) {
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
        example.createCriteria().andLoginnameEqualTo(zlnfFcustofomerinRequest.getMobile());
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setId(list.get(0).getId());
        	zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
        	try {
        		customerInfoService.update(zlnfFcustofomerin);
        		result.setData(customerInfoService.getResult(example).get(0));
        		result.setSuccess(true);
            	result.setReturnCode("0000");
            	result.setMessage("密码修改成功");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(true);
            	result.setReturnCode("1111");
            	result.setMessage("密码修改失败");
			}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在");
        }
        return result;
    }
    
    
    /**
     * 获取个人资料
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/Bycode"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoBycode(String channel, String key, String data) {
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
        example.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	try {
        		List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
        		if("1".equals(list.get(0).getRoletype())){
        			ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
        			farmExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
        			List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample);
        			list.get(0).setZlnfFarmmachineyUser(farmList.get(0));
        			list.get(0).setRoletype1(farmList.get(0).getRoletype());
        			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
        			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
        			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
        			list.get(0).setLng(machineyList.get(0).getLng());
        			list.get(0).setLat(machineyList.get(0).getLat());
        			list.get(0).setType(machineyList.get(0).getRegisttype());
        			list.get(0).setMerchantname(machineyList.get(0).getMerchantname());
        			list.get(0).setCity(machineyList.get(0).getCity());
        			list.get(0).setProvice(machineyList.get(0).getProvice());
        			list.get(0).setEnterprise(machineyList.get(0).getEnterprise());
        			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
        			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
        			annexCriteria.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
        			annexCriteria.andDepttypeEqualTo("1");
        			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
        			if(annexList.size()>0){
        				farmList.get(0).setHttppath(annexList.get(0).getHttppath());
        			}
        		}else if("2".equals(list.get(0).getRoletype())){
        			ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
        			coopExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
        			List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
        			list.get(0).setRoletype2(coopList.get(0).getRoletype());
        			list.get(0).setZlnfCoopUser(coopList.get(0));
        			ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
        			coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
        			List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
        			list.get(0).setLng(coopInfoList.get(0).getLng());
        			list.get(0).setLat(coopInfoList.get(0).getLat());
        			list.get(0).setType(coopInfoList.get(0).getType());
        			list.get(0).setCoopname(coopInfoList.get(0).getCoopname());
        			list.get(0).setVip(coopInfoList.get(0).getVip());
        			list.get(0).setCity(coopInfoList.get(0).getCity());
        			list.get(0).setProvice(coopInfoList.get(0).getProvice());
        			list.get(0).setEnterprise(coopInfoList.get(0).getEnterprise());
        			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
        			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
        			annexCriteria.andDeptcodeEqualTo(coopList.get(0).getCoopcode());
        			annexCriteria.andDepttypeEqualTo("5");
        			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
        			if(annexList.size()>0){
        				coopList.get(0).setHttppath(annexList.get(0).getHttppath());
        			}
        			if(coopInfoList.get(0).getVip()!=null){
        				if("1".equals(coopInfoList.get(0).getVip())){
        					list.get(0).setProvice(coopInfoList.get(0).getProvice());
        					list.get(0).setCity(coopInfoList.get(0).getCity());
        				}
        			}
        			list.get(0).setAddress(coopInfoList.get(0).getAddress());
        		}
        		result.setSuccess(true);
        		result.setData(list.get(0));
        		result.setReturnCode("0000");
        		result.setMessage("获取用户信息成功");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
        		result.setReturnCode("1111");
        		result.setMessage("获取用户信息失败");
			}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在或密码错误");
        }
        return result;
    }

    
    /**
     * 设置密码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/update1"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoUpate1(String channel, String key, String data) {
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
    	example.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
    	int count = customerInfoService.getResultCount(example);
    	OptResult result = OptResult.success();
    	if(count>0){
    		try {
    			List<ZlnfFcustofomerin> custofomerinList = customerInfoService.getResult(example);
    			ZlnfFcustofomerin fcustofomerin = new ZlnfFcustofomerin();
    			fcustofomerin.setId(custofomerinList.get(0).getId());
    			fcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
    			customerInfoService.update(fcustofomerin);
    			result.setSuccess(true);
    			result.setReturnCode("0000");
    			result.setMessage("设置密码成功");
    		} catch (Exception e) {
    			// TODO: handle exception
    			result.setSuccess(false);
    			result.setReturnCode("1111");
    			result.setMessage("设置密码失败");
    		}
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("0003");
    		result.setMessage("该用户不存在");
    	}
    	return result;
    }

    /**
     * 设置支付密码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/update2"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoUpate2(String channel, String key, String data) {
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
    	example.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
    	int count = customerInfoService.getResultCount(example);
    	OptResult result = OptResult.success();
    	if(count>0){
    		try {
    			List<ZlnfFcustofomerin> custofomerinList = customerInfoService.getResult(example);
    			ZlnfFcustofomerin fcustofomerin = new ZlnfFcustofomerin();
    			fcustofomerin.setId(custofomerinList.get(0).getId());
    			fcustofomerin.setPaypassword(DesUtil.desCrypto(zlnfFcustofomerinRequest.getPaypassword()));
    			customerInfoService.update(fcustofomerin);
    			result.setSuccess(true);
    			result.setReturnCode("0000");
    			result.setMessage("设置支付密码成功");
    		} catch (Exception e) {
    			// TODO: handle exception
    			result.setSuccess(false);
    			result.setReturnCode("1111");
    			result.setMessage("设置支付密码失败");
    		}
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("0003");
    		result.setMessage("该用户不存在");
    	}
    	return result;
    }
    
    /**
     * 修改身份证号
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/update3"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoUpate3(String channel, String key, String data) {
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
    	criteria.andIsdeletedEqualTo("0");
    	int count = customerInfoService.getResultCount(example);
    	OptResult result = OptResult.success();
    	if(count>0){
    		try {
	    		//修改用户基本信息
    			Map<String,Object> map=new HashMap<String,Object>();
	    		List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
	    		ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
	    		zlnfFcustofomerin.setId(list.get(0).getId());
	    		String cardnumber = zlnfFcustofomerinRequest.getCardnumber();
	    		if(StringUtils.isNotBlank(cardnumber)){
	    			zlnfFcustofomerin.setCardnumber(cardnumber);
	    			map.put("idcardnum", cardnumber);
	    		}
	    		zlnfFcustofomerin.setIsaudit("0");
	    		
				//解绑银行卡
	    		Map<String, String> params = new HashMap<String, String>();
    			ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
        		ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
        		criteria1.andUseridEqualTo(list.get(0).getUsercode());
        		criteria1.andIsdeleteEqualTo("0");
        		criteria1.andBindingtypeEqualTo("3");
        		//criteria1.andIsbindcardEqualTo("1");
        		Boolean bo = true;
        		String error = "";
        		List<ZlnfBinding> bindingList = bindingService.getResult(bindingExample1);
        		for(int i = 0;i<bindingList.size();i++){
        			params.put("identityid", bindingList.get(0).getIddentityid());
        			params.put("bindid", bindingList.get(0).getBindid());
        			params.put("identitytype", "0");
        			Map<String, String> result2 = InstantPayService.unbindBankcard(params);
            		ZlnfBinding zlnfBinding = new ZlnfBinding();
        			zlnfBinding.setId(bindingList.get(0).getId());
        			zlnfBinding.setIsdelete("1");
        			bindingService.update(zlnfBinding);
        			if(result2.get("error_code")==null){
        				
        			}else{
        				error = result2.get("error_msg");
        				if("用户与指定的银行卡无绑定关系".equals(error)){
        					
        				}else{
        					result.setSuccess(false);
            	    		result.setReturnCode("0002");
            	    		result.setMessage("修改身份证信息失败");
            	    		bo = false;
            	    		break;
        				}
        			 }
        		}
		        if(bo){
		        	//上传身份证照片
		    		String deptcode = zlnfFcustofomerinRequest.getDeptcode();
			    	ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
			    	ZlnfAnnexExample.Criteria criteria2 =  zlnfAnnexExample.createCriteria();
			    	criteria2.andDeptcodeEqualTo(deptcode);
			    	criteria2.andDepttypeEqualTo("4");
			    	criteria2.andIsdeletedEqualTo("0");
			    	List<ZlnfAnnex> annexList = annexService.getResult(zlnfAnnexExample);
			    	for(int i = 0;i<annexList.size();i++){
			    		ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
			    		zlnfAnnex.setId(annexList.get(i).getId());
			    		zlnfAnnex.setIsdeleted("1");
			    		annexService.update(zlnfAnnex);
			    	}
		    		String path = zlnfFcustofomerinRequest.getAnnexpath();
					String annexname = zlnfFcustofomerinRequest.getAnnexname();
					String httpPath = zlnfFcustofomerinRequest.getHttpPath();
					if(StringUtils.isNotBlank(path)){
			    		String[] paths = path.split(",");
			    		String[] annexnames = annexname.split(",");
			    		String[] httpPaths = httpPath.split(",");
			    		for(int i = 0;i<paths.length;i++){
			    			String annexcode = Uuid32.getUUID32();
			    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
			    			zlnfAnnex.setAnnexcode(annexcode);
			    			zlnfAnnex.setAnnexpath(paths[i]);
			    			zlnfAnnex.setAnnexname(annexnames[i]);
			    			zlnfAnnex.setHttppath(httpPaths[i]);
			    			zlnfAnnex.setDeptcode(zlnfFcustofomerinRequest.getUsercode());
		    				zlnfAnnex.setDepttype("4");
		    				zlnfAnnex.setCreator(list.get(0).getUsername());
			    			zlnfAnnex.setCreatedonutc(new Date());
			    			zlnfAnnex.setIsdeleted("0");
			    			annexService.create(zlnfAnnex);
			    		}
					}
					ZlnfAnnexExample zlnfAnnexExample1 = new ZlnfAnnexExample();
			    	ZlnfAnnexExample.Criteria criteria3 =  zlnfAnnexExample1.createCriteria();
			    	criteria3.andDeptcodeEqualTo(zlnfFcustofomerinRequest.getDeptcode());
			    	criteria3.andDepttypeEqualTo("4");
			    	criteria3.andIsdeletedEqualTo("0");
			    	List<ZlnfAnnex> annexList1 = annexService.getResult(zlnfAnnexExample1);
		        	customerInfoService.update(zlnfFcustofomerin);
		        	map.put("annexList", annexList1);
		        	result.setSuccess(true);
    	    		result.setReturnCode("0000");
    	    		result.setMessage("修改身份证信息成功");
    	    		result.setData(map);
		        }
    		} catch (Exception e) {
				// TODO: handle exception
    			result.setSuccess(false);
        		result.setReturnCode("0001");
        		result.setMessage("修改身份证号失败");
			}
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("0003");
    		result.setMessage("用户不存在");
    	}
    	return result;
    }
    
    /**
     * 修改支付密码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/update4"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult customerInfoUpdate4(String channel, String key, String data) {
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
    	ZlnfBindingExample   bindingexample=new ZlnfBindingExample();
    	ZlnfBindingExample.Criteria bindingcriteria=bindingexample.createCriteria();
    	bindingcriteria.andUseridEqualTo(zlnfFcustofomerinRequest.getUsercode());
    	bindingcriteria.andIsdeleteEqualTo("0");
    	bindingcriteria.andBindingtypeEqualTo("3");
    	//bindingcriteria.andIsbindcardEqualTo("1");
    	bindingcriteria.andBindingnumEqualTo(zlnfFcustofomerinRequest.getBankcardnumber());
    	int bindingcount=bindingService.getResultCount(bindingexample);
  
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
        criteria.andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        criteria.andCardnumberEqualTo(zlnfFcustofomerinRequest.getCardnumber());
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0&&bindingcount>0){
        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setId(list.get(0).getId());
        	zlnfFcustofomerin.setPaypassword(DesUtil.desCrypto(zlnfFcustofomerinRequest.getPaypassword()));
        	try {
        		/*	Map<String, String> params = new HashMap<String, String>();
    			ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
        		ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
        		criteria1.andUseridEqualTo(zlnfFcustofomerinRequest.getUsercode());
        		criteria1.andIsdeleteEqualTo("0");
        		criteria1.andBindingtypeEqualTo("3");
        		List<ZlnfBinding> bindingList = bindingService.getResult(bindingExample1);
        		Boolean bo = true;
        		String error = "";
        	for(int j = 0;j<bindingList.size();j++){
        			params.put("identityid", bindingList.get(0).getIddentityid());
        			params.put("bindid", bindingList.get(0).getBindid());
        			params.put("identitytype", "0");
        			Map<String, String> result2 = InstantPayService.unbindBankcard(params);
            		ZlnfBinding zlnfBinding = new ZlnfBinding();
        			zlnfBinding.setId(bindingList.get(0).getId());
        			zlnfBinding.setIsdelete("1");
        			bindingService.updateDelete(zlnfBinding);
        			if(result2.get("error_code")==null){
        				result.setSuccess(true);
        	    		result.setReturnCode("0000");
        			}else{
        				error = result2.get("error_msg");
        				if("用户与指定的银行卡无绑定关系".equals(error)){
        					result.setSuccess(true);
            	    		result.setReturnCode("0000");
        				}else{
        					result.setSuccess(false);
            	    		result.setReturnCode("0002");
            	    		bo = false;
            	    		break;
        				}
        			}
        		}
        		if(bo){*/
        			customerInfoService.update(zlnfFcustofomerin);
            		result.setData(customerInfoService.getResult(example).get(0));
            		result.setSuccess(true);
                	result.setReturnCode("0000");
                	result.setMessage("支付密码修改成功");	
        		/*}else{
        			result.setSuccess(false);
            		result.setReturnCode("0002");
            		result.setMessage(error);
        		}*/
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(true);
            	result.setReturnCode("1111");
            	result.setMessage("支付密码修改失败");
			}
        }else{
        	
        	result.setSuccess(false);
        	
        	if(count>0){
        		result.setMessage("银行卡号错误");
        		result.setReturnCode("0003");
        		
        	}else{
        		if(bindingcount>0){
        			result.setMessage("身份证号错误");
        			result.setReturnCode("0004");
        		}else{
        		result.setMessage("身份证号或银行卡号错误");
        		result.setReturnCode("0005");
        		}
        	}
        	
        }
        return result;
    }

    
    /**
     * 查询是否设置支付密码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/querypaypassword"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult querypaypassword(String channel, String key, String data) {
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
    	criteria.andIsdeletedEqualTo("0");
    	int count = customerInfoService.getResultCount(example);
    	OptResult result = OptResult.success();
    	if(count>0){
    		List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
    		if(list.get(0).getPaypassword()!=null && !"".equals(list.get(0).getPaypassword())){
    			result.setData("1");
    		}else{
    			result.setData("0");
    		}
    		result.setSuccess(true);
			result.setReturnCode("0000");
			result.setMessage("查询是否设置支付密码成功");
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("0003");
    		result.setMessage("用户不存在");
    	}
    	return result;
    }
    
    /**
     * 修改营业执照
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/updateAnnex"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult updateAnnex(String channel, String key, String data) {
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
	    	Date date = new Date();
	    	String deptcode = zlnfFcustofomerinRequest.getDeptcode();
	    	String depttype = zlnfFcustofomerinRequest.getDepttype();
	    	ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
	    	ZlnfAnnexExample.Criteria criteria =  zlnfAnnexExample.createCriteria();
	    	criteria.andDeptcodeEqualTo(deptcode);
	    	criteria.andDepttypeEqualTo(depttype);
	    	criteria.andIsdeletedEqualTo("0");
	    	List<ZlnfAnnex> annexList = annexService.getResult(zlnfAnnexExample);
	    	for(int i = 0;i<annexList.size();i++){
	    		ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	    		zlnfAnnex.setId(annexList.get(i).getId());
	    		zlnfAnnex.setIsdeleted("1");
	    		annexService.update(zlnfAnnex);
	    	}
	    	//添加营业执照图片信息
			String path = zlnfFcustofomerinRequest.getAnnexpath();
			String annexname = zlnfFcustofomerinRequest.getAnnexname();
			String httpPath = zlnfFcustofomerinRequest.getHttpPath();
			if(StringUtils.isNotBlank(path)){
	    		String[] paths = path.split(",");
	    		String[] annexnames = annexname.split(",");
	    		String[] httpPaths = httpPath.split(",");
	    		for(int i = 0;i<paths.length;i++){
	    			String annexcode = Uuid32.getUUID32();
	    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	    			zlnfAnnex.setAnnexcode(annexcode);
	    			zlnfAnnex.setAnnexpath(paths[i]);
	    			zlnfAnnex.setAnnexname(annexnames[i]);
	    			zlnfAnnex.setHttppath(httpPaths[i]);
	    			if("4".equals(depttype)){
	    				zlnfAnnex.setDeptcode(zlnfFcustofomerinRequest.getUsercode());
	    				zlnfAnnex.setDepttype(depttype);
	    			}else{
	    				if("1".equals(depttype)){
	    					ZlnfFarmMachineyMerchantExample farmMachineytExample = new ZlnfFarmMachineyMerchantExample();
	    		        	farmMachineytExample.createCriteria().andMerchantcodeEqualTo(deptcode);
	    		        	List<ZlnfFarmMachineyMerchant> farmMachineyList = farmMachineyMerchantService.getResult(farmMachineytExample);
	    		        	ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
	    		        	if(farmMachineyList.size()>0){
	    		        		zlnfFarmMachineyMerchant.setId(farmMachineyList.get(0).getId());
	    		        		zlnfFarmMachineyMerchant.setIsaudit("0");
	    		        		farmMachineyMerchantService.update(zlnfFarmMachineyMerchant);
	    		        	}
	    				}else{
	    					ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
	    		        	coopInfoExample.createCriteria().andCoopcodeEqualTo(deptcode);
	    		        	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
	    		        	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
	    		        	/*if(coopInfoList.size()>0){
	    		        		zlnfCoopInfo.setId(coopInfoList.get(0).getId());
	    		        		zlnfCoopInfo.setIsaudit("0");
	    		        		coopInfoService.update(zlnfCoopInfo);
	    		        	}*/
	    				}
	    				zlnfAnnex.setDeptcode(deptcode);
	    				zlnfAnnex.setDepttype(depttype);
	    			}
	    			zlnfAnnex.setCreator(zlnfFcustofomerinRequest.getUsername());
	    			zlnfAnnex.setCreatedonutc(date);
	    			annexService.create(zlnfAnnex);
	    		}
	    	}
			ZlnfAnnexExample zlnfAnnexExample1 = new ZlnfAnnexExample();
	    	ZlnfAnnexExample.Criteria criteria1 =  zlnfAnnexExample1.createCriteria();
	    	criteria1.andDeptcodeEqualTo(deptcode);
	    	criteria1.andDepttypeEqualTo(depttype);
	    	criteria1.andIsdeletedEqualTo("0");
	    	List<ZlnfAnnex> annexList1 = annexService.getResult(zlnfAnnexExample1);
			result.setSuccess(true);
			result.setData(annexList1);
			result.setReturnCode("0000");
			if("4".equals(depttype)){
	    		result.setMessage("修改身份证图片成功");
			}else{
	    		result.setMessage("修改营业执照图片成功");
			}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("修改图片信息失败");
    	}
    	return result;
    }
    
    /**
     * 验证手机号是否存在
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/customerInfo/isExistence1"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult isExistence1(String channel, String key, String data) {
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
    	ZlnfFcustofomerinExample.Criteria exampleCriteria = example.createCriteria();
    	exampleCriteria.andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
    	exampleCriteria.andIsdeletedEqualTo("0");
    	int count = customerInfoService.getResultCount(example);
    	OptResult result = OptResult.success();
    	Boolean bo = false;
    	if(count>0){
    		bo = true;
    	}
    	ZlnfLkyhExample lkyhExample = new ZlnfLkyhExample();
    	ZlnfLkyhExample.Criteria lkyhCriteria = lkyhExample.createCriteria();
    	lkyhCriteria.andYhsjEqualTo(zlnfFcustofomerinRequest.getMobile());
    	int count1 = lkyhService.getResultCount(lkyhExample);
    	if(count1>0){
    		bo = true;
    	}
    	if(bo){
    		result.setSuccess(true);
    		result.setReturnCode("0000");
    		result.setMessage("该用户已注册");
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("该用户未注册");
    	}
    	return result;
    }
    
}
