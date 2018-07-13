package com.vt.fengci.zlnf.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DesUtil;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfBinding;
import com.vt.fencing.model.ZlnfBindingExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.request.ZlnfBindingRequest;
import com.vt.fengci.zlnf.service.IBindingService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.yeepay.InstantPayService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class BindingController extends BaseGatewayController {

	private static final long serialVersionUID = 1693770858535967155L;
	/**
     * member service
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IBindingService bindingService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    /**
     * 添加绑定信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingCreate(String channel, String key, String data,HttpServletRequest request) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
        // 2. 业务检查
        if (zlnfBindingRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
        	ZlnfBindingExample bindingExample = new ZlnfBindingExample();
        	ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
        	criteria.andUseridEqualTo(zlnfBindingRequest.getUserid());
        	criteria.andIsdeleteEqualTo("0");
        	List<ZlnfBinding> list = bindingService.getResult(bindingExample);
        	String defaultorder = "";
        	if(list.size()>0){
        		defaultorder = "0";
        	}else{
        		defaultorder = "1";
        	}
        	String bindingtype = zlnfBindingRequest.getBindingtype();
        	if(bindingtype.equals("3")){
        		Date date = new Date();
    	        String bindingcode =Uuid32.getUUID32();
    	        ZlnfBinding ZlnfBinding = new ZlnfBinding();
    	        ZlnfBinding.setBindingcode(bindingcode);
    	        ZlnfBinding.setUserid(zlnfBindingRequest.getUserid());
    	        ZlnfBinding.setBindingnum(zlnfBindingRequest.getBindingnum());
    	        ZlnfBinding.setBindingtype(zlnfBindingRequest.getBindingtype());
    	        ZlnfBinding.setCreator(zlnfBindingRequest.getCreator());
    	        ZlnfBinding.setCreatorid(zlnfBindingRequest.getUserid());
    	        ZlnfBinding.setDefaultorder(defaultorder);
    	        ZlnfBinding.setCreatedate(date);
    	        result.setSuccess(true);
    			bindingService.create(ZlnfBinding);
    			result.setReturnCode("0000");
    			result.setMessage("添加绑定信息成功");
        	}else{
        		criteria.andBindingtypeEqualTo(zlnfBindingRequest.getBindingtype());
        		List<ZlnfBinding> list1 = bindingService.getResult(bindingExample);
        		if(list1.size()>0){
        			if(bindingtype.equals("1")){
        				result.setSuccess(false);
                    	result.setReturnCode("0001");
                    	result.setMessage("已绑定过微信信息");
        			}else{
        				result.setSuccess(false);
                    	result.setReturnCode("0001");
                    	result.setMessage("已绑定过支付宝信息");
        			}
        		}else{
        			Date date = new Date();
        	        String bindingcode =Uuid32.getUUID32();
        	        ZlnfBinding ZlnfBinding = new ZlnfBinding();
        	        ZlnfBinding.setBindingcode(bindingcode);
        	        ZlnfBinding.setUserid(zlnfBindingRequest.getUserid());
        	        ZlnfBinding.setBindingnum(zlnfBindingRequest.getBindingnum());
        	        String unionid = zlnfBindingRequest.getUnionid();
        	        if(StringUtils.isNotBlank(unionid)){
        	        	ZlnfBinding.setUnionid(unionid);
        	        }
        	        ZlnfBinding.setBindingtype(zlnfBindingRequest.getBindingtype());
        	        ZlnfBinding.setPhotopath(zlnfBindingRequest.getPhotopath());
    	        	ZlnfBinding.setNickname(zlnfBindingRequest.getNickname());
        	        ZlnfBinding.setCreator(zlnfBindingRequest.getCreator());
        	        ZlnfBinding.setCreatorid(zlnfBindingRequest.getUserid());
        	        ZlnfBinding.setDefaultorder(defaultorder);
        	        ZlnfBinding.setCreatedate(date);
        	        result.setSuccess(true);
        			bindingService.create(ZlnfBinding);
        			result.setReturnCode("0000");
        			result.setMessage("添加绑定信息成功");
        		}
        	}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加绑定信息失败");
		}
        return result;
    }

    
    /**
     * 查询绑定信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
    	// 2. 业务检查
    	if (zlnfBindingRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfBindingExample bindingExample = new ZlnfBindingExample();
    		ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
    		criteria.andUseridEqualTo(zlnfBindingRequest.getUserid());
    		criteria.andIsdeleteEqualTo("0");
    		//criteria.andIsbindcardEqualTo("1");
    		List<ZlnfBinding> list = bindingService.getResult(bindingExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询绑定信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询绑定信息失败");
    	}
    	return result;
    }

    /**
     * 绑定银行卡信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/bankcard"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingBankcard(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
    	// 2. 业务检查
    	if (zlnfBindingRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	Map<String, String> params = new HashMap<String, String>();
    	ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
    	ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
    	criteria1.andBindingnumEqualTo(zlnfBindingRequest.getBindingnum());
    	criteria1.andBindingtypeEqualTo("3");
    	criteria1.andIsdeleteEqualTo("0");
    	criteria1.andIsbindcardEqualTo("1");
    	List<ZlnfBinding> list1=bindingService.getResult(bindingExample1);
    	if(list1.size()>0){
    		result.setSuccess(false);
    		result.setReturnCode("0005");
    		result.setMessage("银行卡已绑定");
    
    	}else{
    		
        	try {
        		ZlnfBindingExample bindingExample = new ZlnfBindingExample();
            	ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
            	criteria.andUseridEqualTo(zlnfBindingRequest.getUserid());
            	criteria.andIsdeleteEqualTo("0");
            	List<ZlnfBinding> list = bindingService.getResult(bindingExample);
            	String defaultorder = "";
            	if(list.size()>0){
            		defaultorder = "0";
            	}else{
            		defaultorder = "1";
            	}
        		//身份证号，姓名，手机号，银行卡号，终端IP，设备号
        		params.put("terminaltype", "0");
        		params.put("identitytype", "0");
        		params.put("requestid", Uuid32.getUUID32());//-------------------------生成的唯一请求编号
        		params.put("cardno", zlnfBindingRequest.getBindingnum());//卡号
        		params.put("phone", zlnfBindingRequest.getPhone());//电话
        		params.put("userip", zlnfBindingRequest.getUserip());//终端IP
        		params.put("username", zlnfBindingRequest.getUsername());//姓名
        		params.put("idcardtype", "01");
        		params.put("idcardno", zlnfBindingRequest.getIdcardno());//身份证号
        		params.put("identityid", Uuid32.getUUID32());//-----------------------生成的唯一用户编号
        		params.put("terminalid", zlnfBindingRequest.getTerminalid());//设备号
        		Map<String, String> result3 = InstantPayService.bankCardCheck(zlnfBindingRequest.getBindingnum());
        		if(result3.get("error_code")==null){
        			if("1".equals(result3.get("cardtype"))){
        				Map<String, String> result1 = InstantPayService.bindCardRequest(params);
        	    		if(result1.get("error_code")==null){
        	    			Map<String, String> result2 = InstantPayService.bindCardSendSms(params.get("requestid"));
        	    			if(result2.get("error_code")==null){
        	    				Date date = new Date();
        	        	        String bindingcode =Uuid32.getUUID32();
        	        	        ZlnfBinding ZlnfBinding = new ZlnfBinding();
        	        	        ZlnfBinding.setBindingcode(bindingcode);
        	        	        ZlnfBinding.setUserid(zlnfBindingRequest.getUserid());
        	        	        ZlnfBinding.setBindingnum(zlnfBindingRequest.getBindingnum());
        	        	        ZlnfBinding.setBindingtype(zlnfBindingRequest.getBindingtype());
        	        	        ZlnfBinding.setCreator(zlnfBindingRequest.getCreator());
        	        	        ZlnfBinding.setCreatorid(zlnfBindingRequest.getUserid());
        	        	        ZlnfBinding.setDefaultorder(defaultorder);
        	        	        ZlnfBinding.setRequestid(params.get("requestid"));
        	        	        ZlnfBinding.setIddentityid(params.get("identityid"));
        	        	        ZlnfBinding.setCreatedate(date);
        	        	        ZlnfBinding.setIsbindcard("0");
        	        	        ZlnfBinding.setAuthname(zlnfBindingRequest.getUsername());
        	        	        result.setSuccess(true);
        	        			bindingService.create(ZlnfBinding);
        	        			result.setReturnCode("0000");
        	        			result.setMessage("正在验证绑定信息");
        	    			}else{
        	    				result.setSuccess(false);
        	            		result.setReturnCode("0002");
        	            		result.setMessage(result2.get("error_msg"));
        	    			}
        	    		}else{
        	    			result.setSuccess(false);
        	        		result.setReturnCode("0001");
        	        		result.setMessage(result1.get("error_msg"));
        	    		}
        			}else if("2".equals(result3.get("cardtype"))){
        				result.setSuccess(false);
                		result.setReturnCode("0003");
                		result.setMessage("不能绑定信用卡");
        			}else{
        				result.setSuccess(false);
                		result.setReturnCode("0003");
                		result.setMessage("未知的银行卡类型，不能绑定");
        			}
        		}else{
        			result.setSuccess(false);
            		result.setReturnCode("0003");
            		result.setMessage(result3.get("error_msg"));
        		}
        	} catch (Exception e) {
        		// TODO: handle exception
        		result.setSuccess(false);
        		result.setReturnCode("1111");
        		result.setMessage("验证绑定信息失败");
        	}
    		
    		
    	}
    	return result;
    }

    /**
     * 绑定银行卡验证短信信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/cardCheckSms"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingCardCheckSms(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
    	// 2. 业务检查
    	if (zlnfBindingRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	Map<String, String> params = new HashMap<String, String>();
    	try {
    		ZlnfBindingExample bindingExample = new ZlnfBindingExample();
        	ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
        	criteria.andRequestidEqualTo(zlnfBindingRequest.getRequestid());
        	criteria.andIsdeleteEqualTo("0");
        	List<ZlnfBinding> list = bindingService.getResult(bindingExample);
        	params.put("requestid", zlnfBindingRequest.getRequestid());//-------------------------生成的唯一请求编号
    		params.put("validatecode", zlnfBindingRequest.getValidatecode());//短信验证码
    		logger.debug("=================================start11");
    		Map<String, String> result1 = InstantPayService.bindCardCheckSms(params);
    		logger.debug("=================================bindCardCheckSms12");
    		if(result1.get("error_code")==null){
    			 logger.debug("=================================result1.get(error_code)13");
    			
            	if(list.size()>0){
            		 logger.debug("=================================list.size()>0 14");
            		Map<String, String> result2 = InstantPayService.bankcardBindQuery(list.get(0).getIddentityid(), "0");
            		 logger.debug("=================================bankcardBindQuery15");
            		if(result2.get("error_code")==null){
            			 logger.debug("=================================16");
            			ZlnfBinding zlnfBinding = new ZlnfBinding();
                		zlnfBinding.setId(list.get(0).getId());
                		zlnfBinding.setIsbindcard("1");
                		String cardlist = result2.get("cardlist");
                		System.out.println();
                		cardlist = cardlist.substring(1);
                		cardlist = cardlist.substring(0, cardlist.length()-1);
                		 logger.debug("=================================cardlist16"+cardlist);
                		JSONObject jsStr = JSONObject.parseObject(cardlist);
                		zlnfBinding.setBankcard(jsStr.getString("card_name"));
                		zlnfBinding.setBindid(result1.get("bindid"));
                		bindingService.update(zlnfBinding);
                		ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
                    	ZlnfFcustofomerinExample.Criteria exampleCriteria = example.createCriteria();
                    	exampleCriteria.andUsercodeEqualTo(list.get(0).getUserid());
                    	List<ZlnfFcustofomerin> list1 = customerInfoService.getResult(example);
                    	if(list1.size()>0){
                    		 logger.debug("=================================list123456.size()>0 17");
                    		if(list1.get(0).getPaypassword()!=null && !"".equals(list1.get(0).getPaypassword())){
                    			result.setSuccess(true);
                        		result.setReturnCode("0000");
                        		result.setMessage("绑定银行卡信息成功");
                    		}else{
                    			result.setSuccess(true);
                        		result.setReturnCode("0000");
                        		result.setData("paypassword");
                        		result.setMessage("绑定银行卡信息成功");
                    		}
                    	}
            		}else{
            			logger.debug("=================================list123456.size()>0 17");
            			ZlnfBinding zlnfBinding = new ZlnfBinding();
                		zlnfBinding.setId(list.get(0).getId());
                		zlnfBinding.setIsdelete("1");
                		bindingService.update(zlnfBinding);
            			result.setSuccess(false);
                		result.setReturnCode("0002");
                		result.setMessage(result2.get("error_msg"));
            		}
            	}
    		}else{
    			logger.debug("=================================list123456.size()>0 17");
    			ZlnfBinding zlnfBinding = new ZlnfBinding();
        		zlnfBinding.setId(list.get(0).getId());
        		zlnfBinding.setIsdelete("1");
        		bindingService.update(zlnfBinding);
    			result.setSuccess(false);
        		result.setReturnCode("0001");
        		result.setMessage(result1.get("error_msg"));
    		}
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("绑定银行卡信息失败");
    	}
    	return result;
    }

    /**
     * 解绑银行卡
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/unbindBankcard"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult unbindBankcard(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
    	// 2. 业务检查
    	if (zlnfBindingRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	String usercode=zlnfBindingRequest.getUsercode();
    	String paypassword=zlnfBindingRequest.getPaypassword();
    	
    	if(paypassword!=null&&paypassword!=""&&usercode!=null&&usercode!=""){
    		ZlnfFcustofomerinExample  userexample=new ZlnfFcustofomerinExample();
    		ZlnfFcustofomerinExample.Criteria usercriter=userexample.createCriteria();
    		usercriter.andUsercodeEqualTo(usercode);
    		usercriter.andIsdeletedEqualTo("0");
    		List<ZlnfFcustofomerin> ulist=customerInfoService.getResult(userexample);
    		//验证支付密码
    		if(paypassword.equals(DesUtil.decrypt(ulist.get(0).getPaypassword()))){
    			Map<String, String> params = new HashMap<String, String>();
    	    	try {
    				ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
    	    		ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
    	    		criteria1.andIddentityidEqualTo(zlnfBindingRequest.getIddentityid());
    	    		criteria1.andIsdeleteEqualTo("0");
    	    		List<ZlnfBinding> list = bindingService.getResult(bindingExample1);
    	    		params.put("identityid", zlnfBindingRequest.getIddentityid());
    				params.put("bindid", list.get(0).getBindid());
    				params.put("identitytype", "0");
    				Map<String, String> result2 = InstantPayService.unbindBankcard(params);
    	    		ZlnfBinding zlnfBinding = new ZlnfBinding();
    				zlnfBinding.setId(list.get(0).getId());
    				zlnfBinding.setIsdelete("1");
    				
    				ZlnfBindingExample bindingExample2 = new ZlnfBindingExample();
    	    		ZlnfBindingExample.Criteria criteria2 = bindingExample2.createCriteria();
    	    		criteria2.andUseridEqualTo(zlnfBindingRequest.getUsercode());
    	    		criteria2.andIsdeleteEqualTo("0");
    	    		criteria2.andBindingtypeEqualTo("3");
    				List<ZlnfBinding> bindinglist= bindingService.getResult(bindingExample2);
    				if(bindinglist.size()==1&&zlnfBindingRequest.getIddentityid().equals(bindinglist.get(0).getIddentityid())){
    					//删除支付密码
    					ZlnfFcustofomerin	fcustofomerin=new ZlnfFcustofomerin();
    					fcustofomerin.setId(ulist.get(0).getId());
    					fcustofomerin.setPaypassword("");
    					customerInfoService.update(fcustofomerin);
    				}
    				bindingService.updateDelete(zlnfBinding);
    				if(result2.get("error_code")==null){
    					result.setSuccess(true);
    		    		result.setReturnCode("0000");
    		    		result.setMessage("解绑银行卡信息成功");
    				}else{
    					String error = result2.get("error_msg");
        				if("用户与指定的银行卡无绑定关系".equals(error)){
        					result.setSuccess(true);
            	    		result.setReturnCode("0000");
            	    		result.setMessage("解绑银行卡信息成功");
        				}else{
    					result.setSuccess(false);
    		    		result.setReturnCode("0002");
    		    		result.setMessage(result2.get("error_msg"));
        				}
    				}	
    	    	} catch (Exception e) {
    	    		// TODO: handle exception
    	    		result.setSuccess(false);
    	    		result.setReturnCode("1111");
    	    		result.setMessage("解绑银行卡信息失败");
    	    	}
    		}else{
    			result.setSuccess(false);
	    		result.setReturnCode("1111");
	    		result.setMessage("支付密码不正确");
    		}
    	}else{
    	Map<String, String> params = new HashMap<String, String>();
    	try {
			ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
    		ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
    		criteria1.andIddentityidEqualTo(zlnfBindingRequest.getIddentityid());
    		criteria1.andIsdeleteEqualTo("0");
    		List<ZlnfBinding> list = bindingService.getResult(bindingExample1);
    		params.put("identityid", zlnfBindingRequest.getIddentityid());
			params.put("bindid", list.get(0).getBindid());
			params.put("identitytype", "0");
			Map<String, String> result2 = InstantPayService.unbindBankcard(params);
    		ZlnfBinding zlnfBinding = new ZlnfBinding();
			zlnfBinding.setId(list.get(0).getId());
			zlnfBinding.setIsdelete("1");
			bindingService.updateDelete(zlnfBinding);
			if(result2.get("error_code")==null){
				result.setSuccess(true);
	    		result.setReturnCode("0000");
	    		result.setMessage("解绑银行卡信息成功");
			}else{
				result.setSuccess(false);
	    		result.setReturnCode("0002");
	    		result.setMessage(result2.get("error_msg"));
			}	
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("解绑银行卡信息失败");
    	}
    	}
    	return result;
    }

    
    /**
     * 修改绑定信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingUpdate(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
    	// 2. 业务检查
    	if (zlnfBindingRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	String paypassword=zlnfBindingRequest.getPaypassword();
    	if(paypassword!=null&&paypassword!=""){
    		ZlnfFcustofomerinExample  userexample=new ZlnfFcustofomerinExample();
    		ZlnfFcustofomerinExample.Criteria usercriter=userexample.createCriteria();
    		usercriter.andUsercodeEqualTo(zlnfBindingRequest.getUserid());
    		usercriter.andIsdeletedEqualTo("0");
    		List<ZlnfFcustofomerin> ulist=customerInfoService.getResult(userexample);
    		if(paypassword.equals(DesUtil.decrypt(ulist.get(0).getPaypassword()))){
    		ZlnfBindingExample bindingExample = new ZlnfBindingExample();
    		ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
    		criteria.andBindingcodeEqualTo(zlnfBindingRequest.getBindingcode());
    		criteria.andIsdeleteEqualTo("0");
    		List<ZlnfBinding> list = bindingService.getResult(bindingExample);
    		if(list.size()>0){
    			//判断是否为最后一张银行卡  是则删除支付密码 
    			ZlnfBindingExample bindingExample2 = new ZlnfBindingExample();
	    		ZlnfBindingExample.Criteria criteria2 = bindingExample2.createCriteria();
	    		criteria2.andUseridEqualTo(zlnfBindingRequest.getUsercode());
	    		criteria2.andIsdeleteEqualTo("0");
	    		criteria2.andBindingtypeEqualTo("3");
				List<ZlnfBinding> bindinglist= bindingService.getResult(bindingExample2);
    			if(bindinglist.size()==1&&bindinglist.get(0).getBindingcode().equals(zlnfBindingRequest.getBindingcode())){
    				//删除支付密码
        			ZlnfFcustofomerin	fcustofomerin=new ZlnfFcustofomerin();
    				fcustofomerin.setId(ulist.get(0).getId());
    				fcustofomerin.setPaypassword("");
    				customerInfoService.update(fcustofomerin);
    			}
    				//删除银行卡
	    			ZlnfBinding zlnfBinding = new ZlnfBinding();
	    			zlnfBinding.setId(list.get(0).getId());
	    			zlnfBinding.setIsdelete("1");
	    			bindingService.update(zlnfBinding);
    		}else{
    			result.setSuccess(false);
        		result.setReturnCode("1111");
        		result.setMessage("查询不到绑定信息");
    		}
    		
    		}else{
    			result.setSuccess(true);
    			result.setReturnCode("0003");
    			result.setMessage("支付密码不正确");
    		}
    		
    	}else{
    	try {
    		ZlnfBindingExample bindingExample = new ZlnfBindingExample();
    		ZlnfBindingExample.Criteria criteria = bindingExample.createCriteria();
    		criteria.andBindingcodeEqualTo(zlnfBindingRequest.getBindingcode());
    		criteria.andIsdeleteEqualTo("0");
    		List<ZlnfBinding> list = bindingService.getResult(bindingExample);
    		if(list.size()>0){
    			ZlnfBinding zlnfBinding = new ZlnfBinding();
    			zlnfBinding.setId(list.get(0).getId());
    			String isdelete = zlnfBindingRequest.getIsdelete();
    			String defaultorder = zlnfBindingRequest.getDefaultorder();
    			zlnfBinding.setModifier(zlnfBindingRequest.getModifier());
    		    if(StringUtils.isNotBlank(isdelete)){
    		    	zlnfBinding.setIsdelete(isdelete);
    		    }
    		    if(StringUtils.isNotBlank(defaultorder) && defaultorder.equals("1")){
    		    	ZlnfBindingExample bindingExample1 = new ZlnfBindingExample();
    	    		ZlnfBindingExample.Criteria criteria1 = bindingExample1.createCriteria();
    	    		criteria1.andUseridEqualTo(zlnfBindingRequest.getUserid());
    	    		criteria1.andIsdeleteEqualTo("0");
    	    		List<ZlnfBinding> list1 = bindingService.getResult(bindingExample1);
    	    		if(list1.size()>0){
    	    			for(int i = 0;i<list1.size();i++){
    	    				ZlnfBinding zlnfBinding1 = new ZlnfBinding();
    	    				zlnfBinding1.setId(list1.get(i).getId());
    	    				zlnfBinding1.setDefaultorder("0");
    	    				bindingService.update(zlnfBinding1);
    	    			}
    	    		}
    		    	zlnfBinding.setDefaultorder(defaultorder);
    		    }
    			bindingService.update(zlnfBinding);
    			result.setSuccess(true);
    			result.setReturnCode("0000");
    			result.setMessage("修改绑定信息成功");
    		}else{
    			result.setSuccess(false);
        		result.setReturnCode("1111");
        		result.setMessage("查询不到绑定信息");
    		}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("修改绑定信息失败");
    	}
    	}
    	return result;
    }
    
    /**
     * 添加微信绑定手机号
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/binding/save"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult bindingSave(String channel, String key, String data,HttpServletRequest request) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfBindingRequest zlnfBindingRequest  = convert(data, ZlnfBindingRequest.class);
        // 2. 业务检查
        if (zlnfBindingRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
        	ZlnfBindingExample bindingExample = new ZlnfBindingExample();
        	ZlnfBindingExample.Criteria bindingCriteria = bindingExample.createCriteria();
        	bindingCriteria.andPhnoeEqualTo(zlnfBindingRequest.getPhnoe());
        	bindingCriteria.andIsdeleteEqualTo("0");
        	List<ZlnfBinding> bindingList = bindingService.getResult(bindingExample);
        	for(int i = 0;i<bindingList.size();i++){
        		ZlnfBinding ZlnfBinding1 = new ZlnfBinding();
        		System.out.println("bindingList.get(i).getId()----------------->"+bindingList.get(i).getId());
        		ZlnfBinding1.setId(bindingList.get(i).getId());
        		ZlnfBinding1.setIsdelete("1");
        		bindingService.update(ZlnfBinding1);
        	}
        	Date date = new Date();
	        String bindingcode =Uuid32.getUUID32();
	        ZlnfBinding ZlnfBinding = new ZlnfBinding();
	        ZlnfBinding.setBindingcode(bindingcode);
	        ZlnfBinding.setPhnoe(zlnfBindingRequest.getPhnoe());
	        ZlnfBinding.setUnionid(zlnfBindingRequest.getUnionid());
	        ZlnfBinding.setBindingnum(zlnfBindingRequest.getBindingnum());
	        ZlnfBinding.setCreatedate(date);
	        result.setSuccess(true);
			bindingService.create(ZlnfBinding);
			result.setReturnCode("0000");
			result.setMessage("添加绑定信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加绑定信息失败");
		}
        return result;
    }
    
//    
//    public static String  GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8888888888888888&secret=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa&code=00b788e3b42043c8459a57a8d8ab5d9f&grant_type=authorization_code";
//    public static String getCodeRequest(){
//        String result = null;
//        result = GetCodeRequest;
//        return result;
//    }
//    
//    public static String urlEnodeUTF8(String str){
//        String result = str;
//        try {
//            result = URLEncoder.encode(str,"UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//    
//    public static void main(String[] args) {
//        System.out.println(getCodeRequest());
//    }
}
