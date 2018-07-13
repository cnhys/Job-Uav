package com.vt.fengci.zlnf.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import WechatUtil.RefundUtil;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DigestUtils;
import com.vt.base.util.MSG4Jpush;
import com.vt.base.util.SmsUtils;
import com.vt.base.util.Uuid32;
import com.vt.base.util.ZHBRefundUtil;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfFieldInfo;
import com.vt.fencing.model.ZlnfFieldInfoExample;
import com.vt.fencing.model.ZlnfOrderUser;
import com.vt.fencing.model.ZlnfOrderUserExample;
import com.vt.fencing.model.ZlnfPaymenttype;
import com.vt.fencing.model.ZlnfPaymenttypeExample;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fencing.model.ZlnfTimeAxis;
import com.vt.fencing.model.ZlnfTimeAxisExample;
import com.vt.fencing.request.ZlnfPaymenttypeRequest;
import com.vt.fencing.request.ZlnfTakeawayOrderRequest;
import com.vt.fencing.request.ZlnfTimeAxisRequest;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.IFieldInfoService;
import com.vt.fengci.zlnf.service.IOrderUserService;
import com.vt.fengci.zlnf.service.IPaymenttypeService;
import com.vt.fengci.zlnf.service.ITakeawayOrderService;
import com.vt.fengci.zlnf.service.ITimeAxisService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class TakeawayOrderController extends BaseGatewayController {

	private static final long serialVersionUID = 1756620551151636927L;
	/**
     * member service
     */
    
    @Autowired
    private ITakeawayOrderService takeawayOrderService;
    
    @Autowired
    private IDictInfoService dictInfoService;
    
    @Autowired
    private IFieldInfoService fieldInfoService;

    @Autowired
    private IOrderUserService orderUserService;
    
    @Autowired
    private ICoopInfoService coopInfoService;

    @Autowired
    private IPaymenttypeService paymenttypeService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;

    @Autowired
    private ITimeAxisService timeAxisService;
    
    /**
     * 获取订单信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
        zlnfTakeawayOrder.setNumber(zlnfTakeawayOrderRequest.getNumber());
        String ordercode = zlnfTakeawayOrderRequest.getOrdercode();
        String status = zlnfTakeawayOrderRequest.getStatus();
        String parentcode = zlnfTakeawayOrderRequest.getParentcode();
        String usercode = zlnfTakeawayOrderRequest.getUsercode();
        String merchantcode = zlnfTakeawayOrderRequest.getMerchantcode();
        String roletype = zlnfTakeawayOrderRequest.getRoletype();
        String coopcode = zlnfTakeawayOrderRequest.getCoopcode();
        if(StringUtils.isNotBlank(ordercode)){
        	zlnfTakeawayOrder.setOrdercode(ordercode);
    	}
        if(StringUtils.isNotBlank(status)){
        	if("15".equals(status)){
        		zlnfTakeawayOrder.setHmpay("1");
        	}else if("16".equals(status)){
        		zlnfTakeawayOrder.setEvaluate(status);
        		zlnfTakeawayOrder.setStatus("11");
        	}else{
        		zlnfTakeawayOrder.setStatus(status);
        	}
        }
        if(StringUtils.isNotBlank(parentcode)){
        	zlnfTakeawayOrder.setParentcode(parentcode);
        }
        if(StringUtils.isNotBlank(usercode) && !"5".equals(roletype) && !"6".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)){
        	zlnfTakeawayOrder.setUsercode(usercode);
        }
        if(StringUtils.isNotBlank(merchantcode)){
        	zlnfTakeawayOrder.setMerchantcode(merchantcode);
        	zlnfTakeawayOrder.setRoletype(roletype);
        }
        if(StringUtils.isNotBlank(coopcode)){
        	zlnfTakeawayOrder.setCoopcode(coopcode);
        }
    	try {
    		List<ZlnfTakeawayOrder> list = takeawayOrderService.queryTakeawayRownum(zlnfTakeawayOrder);
    		for(int i = 0;i<list.size();i++){
    			ZlnfDictInfoExample dictInfoExample1 = new ZlnfDictInfoExample();
    			ZlnfDictInfoExample.Criteria dictInfoCriteria1 = dictInfoExample1.createCriteria();
    			dictInfoCriteria1.andDictcodeEqualTo(list.get(i).getServicetype());
    			List<ZlnfDictInfo> dictInfo1 = dictInfoService.getResult(dictInfoExample1);
    			list.get(i).setServicename(dictInfo1.get(0).getDictname());
    			ZlnfDictInfoExample dictInfoExample2 = new ZlnfDictInfoExample();
    			ZlnfDictInfoExample.Criteria dictInfoCriteria2 = dictInfoExample2.createCriteria();
    			dictInfoCriteria2.andDictcodeEqualTo(dictInfo1.get(0).getParentcode());
    			List<ZlnfDictInfo> dictInfo2 = dictInfoService.getResult(dictInfoExample2);
    			list.get(i).setServicebigtype(dictInfo2.get(0).getDictname());
    			ZlnfFieldInfoExample fieldInfoExample = new ZlnfFieldInfoExample();
    			fieldInfoExample.createCriteria().andFieldcodeEqualTo(list.get(i).getFieldcode());
    			List<ZlnfFieldInfo> fieldInfoList = fieldInfoService.getResult(fieldInfoExample);
    			if(fieldInfoList.size()>0){
    				list.get(i).setZlnfFieldInfo(fieldInfoList.get(0));
    			}
    			ZlnfOrderUserExample orderUserExample = new ZlnfOrderUserExample();
    			orderUserExample.createCriteria().andOrdercodeEqualTo(list.get(i).getOrdercode());
    			List<ZlnfOrderUser> orderUserList = orderUserService.getResult(orderUserExample);
    			Boolean bo = false;
    			if(orderUserList.size()>0){
    				if(orderUserList.get(0).getCustname()!=null){
    					list.get(i).setCustname(orderUserList.get(0).getCustname());
    					bo = true;
    				}
    				if(orderUserList.get(0).getCustmobile()!=null){
    					list.get(i).setCustmobile(orderUserList.get(0).getCustmobile());
    				}
    				if(orderUserList.get(0).getSucode()!=null){
    					ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    					fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getSucode());
    					List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    					orderUserList.get(0).setSumobile(custList.get(0).getMobile());
    				}
    				if(orderUserList.get(0).getFmcode()!=null){
    					ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    					fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getFmcode());
    					List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    					orderUserList.get(0).setFmmobile(custList.get(0).getMobile());
    				}
    				if(orderUserList.get(0).getFinancecode()!=null){
    					ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    					fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getFinancecode());
    					List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    					orderUserList.get(0).setFinancenmobile(custList.get(0).getMobile());
    				}
    			}
    			list.get(i).setIscust(bo);
    			list.get(i).setOrderUser(orderUserList.get(0));
    			if(list.get(i).getMerchantcode()!=null){
    				ZlnfFcustofomerin fcustofomerin = customerInfoService.queryFarmMachineyMerchant(list.get(i).getMerchantcode());
    				list.get(i).setMerchantusername(fcustofomerin.getUsername());
    				list.get(i).setMerchantmobile(fcustofomerin.getMobile());
    			}
    			if(list.get(i).getCoopcode()!=null){
    				ZlnfFcustofomerin fcustofomerin = customerInfoService.queryCoopInfo(list.get(i).getCoopcode());
    				if(fcustofomerin.getUsername()!=null){
    					list.get(i).setCoopusername(fcustofomerin.getUsername());
    				}
    				if(fcustofomerin.getMobile()!=null){
    					list.get(i).setCoopmobile(fcustofomerin.getMobile());
    				}
    			}
    			if("5".equals(roletype) || "1".equals(roletype) || "2".equals(roletype)){
    				if(usercode.equals(list.get(i).getUsercode())){
    					list.get(i).setIsmyorder("1");
    				}else{
    					list.get(i).setIsmyorder("0");
    				}
    			}
    		}
    		result.setSuccess(true);
    	    result.setData(list);
        	result.setReturnCode("0000");
        	result.setMessage("获取订单信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取订单信息失败");
		}
        return result;
    }
    
    
    /**
     * 添加农机服务订单信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult takeawayOrderCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        Date date = new Date();
    	String username = zlnfTakeawayOrderRequest.getUsername();
    	String coopname = zlnfTakeawayOrderRequest.getCoopname();
    	String duration = zlnfTakeawayOrderRequest.getDuration();
    	String coopcode = zlnfTakeawayOrderRequest.getCoopcode();
        OptResult result = OptResult.success();
        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
        String ordercode = DigestUtils.generateRandomNumber(10);
        String orderusercode = Uuid32.getUUID32();
        zlnfTakeawayOrder.setOrdercode(ordercode);
        zlnfTakeawayOrder.setUsername(username);
        zlnfTakeawayOrder.setUsercode(zlnfTakeawayOrderRequest.getUsercode());
        zlnfTakeawayOrder.setDuration(duration);
        zlnfTakeawayOrder.setCroparea(zlnfTakeawayOrderRequest.getCroparea());
        zlnfTakeawayOrder.setServiceunit(zlnfTakeawayOrderRequest.getServiceunit());
        zlnfTakeawayOrder.setExpecttime(zlnfTakeawayOrderRequest.getExpecttime());
        zlnfTakeawayOrder.setPlanpay(zlnfTakeawayOrderRequest.getPlanpay());
        zlnfTakeawayOrder.setStatus("1");
        zlnfTakeawayOrder.setFieldcode(zlnfTakeawayOrderRequest.getFieldcode());
        zlnfTakeawayOrder.setCrops(zlnfTakeawayOrderRequest.getCrops());
        zlnfTakeawayOrder.setCropscode(zlnfTakeawayOrderRequest.getCropscode());
        zlnfTakeawayOrder.setCreatedonutc(date);
        zlnfTakeawayOrder.setRemarks(zlnfTakeawayOrderRequest.getRemarks());
        zlnfTakeawayOrder.setCreator(username);
        zlnfTakeawayOrder.setServicetype(zlnfTakeawayOrderRequest.getServicetype());
        zlnfTakeawayOrder.setCoopname(coopname);
        zlnfTakeawayOrder.setCoopcode(zlnfTakeawayOrderRequest.getCoopcode());
        if(StringUtils.isNotBlank(zlnfTakeawayOrderRequest.getMerchantname())){
    		zlnfTakeawayOrder.setServiceprovider(zlnfTakeawayOrderRequest.getMerchantname());
    	}
        if(StringUtils.isNotBlank(zlnfTakeawayOrderRequest.getMerchantcode())){
        	zlnfTakeawayOrder.setServiceprovidercode(zlnfTakeawayOrderRequest.getMerchantcode());
        }
        zlnfTakeawayOrder.setRidgedistance(zlnfTakeawayOrderRequest.getRidgedistance());
        zlnfTakeawayOrder.setVoicetime(zlnfTakeawayOrderRequest.getVoicetime());
        String voice = zlnfTakeawayOrderRequest.getVoice();
		if(StringUtils.isNotBlank(voice)){
    		String[] voices = voice.split(",");
    		zlnfTakeawayOrder.setVoice(voices[0]);
    	}
		ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
    	coopInfoExample.createCriteria().andCoopcodeEqualTo(coopcode);
    	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
        ZlnfOrderUser zlnfOrderUser = new ZlnfOrderUser();
        zlnfOrderUser.setOrderusercode(orderusercode);
        zlnfOrderUser.setOrdercode(ordercode);
        if(coopInfoList.get(0).getCustomermanagename()!=null){
        	zlnfOrderUser.setCustname(coopInfoList.get(0).getCustomermanagename());
        }
        if(coopInfoList.get(0).getCustomermanagecode()!=null){
        	zlnfOrderUser.setCustcode(coopInfoList.get(0).getCustomermanagecode());
        }
        if(coopInfoList.get(0).getCustomermanagemobile()!=null){
        	zlnfOrderUser.setCustmobile(coopInfoList.get(0).getCustomermanagemobile());
        }
        zlnfOrderUser.setCreatedonutc(date);
        zlnfOrderUser.setCreator(username);
    	try {
    		takeawayOrderService.create(zlnfTakeawayOrder);
    		orderUserService.create(zlnfOrderUser);
    		ZlnfTimeAxis timeAxis = new ZlnfTimeAxis();
    		timeAxis.setCreatedonutc(date);
    		timeAxis.setTimeaxiscode(Uuid32.getUUID32());
    		timeAxis.setOrdercode(ordercode);
    		timeAxis.setStatus("1");
    		timeAxis.setUsercode(zlnfTakeawayOrderRequest.getUsercode());
    		timeAxis.setUsername(username);
    		timeAxisService.create(timeAxis);
    		result.setSuccess(true);
    		result.setData(ordercode);
        	result.setReturnCode("0000");
        	result.setMessage("添加农机服务订单成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加农机服务订单失败");
		}
        return result;
    }
    
    
    /**
     * 取消用户订单
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/cancel"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoCancel(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
        ZlnfTakeawayOrderExample.Criteria criteria = takeawayOrderExample.createCriteria();
        criteria.andIsdeletedEqualTo("0");
        criteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
        try {
	        List<ZlnfTakeawayOrder> list = takeawayOrderService.getResult(takeawayOrderExample);
	        String status = list.get(0).getStatus();
	        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
	        if("1".equals(status) || "2".equals(status)){
	        	zlnfTakeawayOrder.setId(list.get(0).getId());
	        	zlnfTakeawayOrder.setStatus("8");
	        }else if("12".equals(status)){
	        	ZlnfPaymenttypeExample paymenttypeExample = new ZlnfPaymenttypeExample();
	        	ZlnfPaymenttypeExample.Criteria paymenttypeCriteria = paymenttypeExample.createCriteria();
	        	paymenttypeCriteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
	        	paymenttypeCriteria.andPaycodeEqualTo("H"+zlnfTakeawayOrderRequest.getOrdercode());
	        	List<ZlnfPaymenttype> orderUserList = paymenttypeService.getResult(paymenttypeExample);
	        	if("1".equals(orderUserList.get(0).getPaytype())){
	        		ZHBRefundUtil.zfbRefund1(zlnfTakeawayOrderRequest.getOrdercode()+"H", 0.01);
	        	}else if("2".equals(orderUserList.get(0).getPaytype())){
	        		//申请微信退款
		        	RefundUtil.wechatRefund(zlnfTakeawayOrderRequest.getOrdercode()+"H", 1);
	        	}
	        	ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
	        	paymenttype.setId(orderUserList.get(0).getId());
	        	paymenttype.setStatus("1");
	        	paymenttypeService.update(paymenttype);
	        	zlnfTakeawayOrder.setId(list.get(0).getId());
	        	zlnfTakeawayOrder.setStatus("8");
	        }else if("13".equals(status)){
	        	ZlnfPaymenttypeExample paymenttypeExample = new ZlnfPaymenttypeExample();
	        	ZlnfPaymenttypeExample.Criteria paymenttypeCriteria = paymenttypeExample.createCriteria();
	        	paymenttypeCriteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
	        	paymenttypeCriteria.andPaycodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode()+"N");
	        	List<ZlnfPaymenttype> paymentList = paymenttypeService.getResult(paymenttypeExample);
	        	if("1".equals(paymentList.get(0).getPaytype())){
	        		ZHBRefundUtil.zfbRefund1(zlnfTakeawayOrderRequest.getOrdercode()+"N", 0.01);
	        	}else if("2".equals(paymentList.get(0).getPaytype())){
	        		//申请微信退款
		        	RefundUtil.wechatRefund(zlnfTakeawayOrderRequest.getOrdercode()+"N", 1);
	        	}
	        	ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
	        	paymenttype.setId(paymentList.get(0).getId());
	        	paymenttype.setStatus("1");
	        	paymenttypeService.update(paymenttype);
	        	zlnfTakeawayOrder.setId(list.get(0).getId());
	        	zlnfTakeawayOrder.setStatus("8");
	        }
	        takeawayOrderService.update(zlnfTakeawayOrder);
	        if("1".equals(zlnfTakeawayOrderRequest.getType())){
	        	send_2(list,"7");
	        }else if("2".equals(zlnfTakeawayOrderRequest.getType())){
	        	send_1(list,"6");
	        }
	        result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("取消订单成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("取消订单失败");
		}
        return result;
    }
    
    
    /**
     * 修改订单状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoDelete(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
        ZlnfTakeawayOrderExample.Criteria criteria = takeawayOrderExample.createCriteria();
        criteria.andIsdeletedEqualTo("0");
        criteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
        String billcode = Uuid32.getUUID32();
        Date date = new Date(); 
        try {
	        List<ZlnfTakeawayOrder> list = takeawayOrderService.getResult(takeawayOrderExample);
	        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
	        zlnfTakeawayOrder.setId(list.get(0).getId());
	        System.out.println("list.size=========================>"+list.size());
	        String status = zlnfTakeawayOrderRequest.getStatus();
	        String totalFee = zlnfTakeawayOrderRequest.getTotalFee();
	        System.out.println("status=========================>"+status);
	        if(!"3".equals(zlnfTakeawayOrderRequest.getPaytype()) && !"4".equals(zlnfTakeawayOrderRequest.getPaytype()) && !"5".equals(zlnfTakeawayOrderRequest.getPaytype())){
	        	if("2".equals(list.get(0).getStatus())){
	        		zlnfTakeawayOrder.setStatus(status);
	        		System.out.println("=========================>1");
	        		if("12".equals(status)){
	        			send_1(list,"2");
	        		}else if("13".equals(status)){
	        			send_2(list,"1");
	        		}
	        	}else if("12".equals(list.get(0).getStatus()) || "13".equals(list.get(0).getStatus())){
	        		System.out.println("=========================>2");
	        		zlnfTakeawayOrder.setStatus("3");
	        		send_1(list,"8");
	        		send_2(list,"8");
	        	}else if("5".equals(list.get(0).getStatus())){
	        		System.out.println("=========================>3");
	        		send_1(list,"5");
	        		ZlnfPaymenttypeExample paymenttypeExample = new ZlnfPaymenttypeExample();
	        		System.out.println("=========================>31");
		        	ZlnfPaymenttypeExample.Criteria paymenttypeCriteria = paymenttypeExample.createCriteria();
		        	System.out.println("=========================>32");
		        	paymenttypeCriteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
		        	System.out.println("=========================>33");
		        	paymenttypeCriteria.andPaycodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode()+"N");
		        	System.out.println("=========================>34");
		        	List<ZlnfPaymenttype> paymentList = paymenttypeService.getResult(paymenttypeExample);
		        	System.out.println("=========================>35");
		        	System.out.println("list.get(0).getDeposit()===================>"+list.get(0).getDeposit());
		        	System.out.println("totalFee===================>"+totalFee);
		        	System.out.println("Double.valueOf(list.get(0).getDeposit())===================>"+Double.valueOf(list.get(0).getDeposit()));
		        	System.out.println("Double.valueOf(totalFee)===================>"+Double.valueOf(totalFee));
		        	String price = String.valueOf(Double.valueOf(list.get(0).getDeposit())+Double.valueOf(totalFee));
		        	System.out.println("定金金额1-------------------->"+list.get(0).getDeposit());
		        	System.out.println("定金金额2-------------------->"+Double.valueOf(list.get(0).getDeposit()));
		        	System.out.println("支付金额1-------------------->"+totalFee);
		        	System.out.println("支付金额2-------------------->"+Double.valueOf(totalFee));
		        	System.out.println("支付金额-------------------->"+price);
		        	BigDecimal   b   =   new   BigDecimal(price);  
		        	Double f1 = b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		        	System.out.println("f1--------------------->"+f1);
		        	String priceStr   =   String.valueOf(f1); 
		        	System.out.println("priceStr------------------->"+priceStr);
		        	zlnfTakeawayOrder.setDeposit(priceStr);
		        	System.out.println("list.get(0).getActualpay()---------------------->"+list.get(0).getActualpay());
		        	System.out.println("bijiao------------------->"+priceStr.equals(String.valueOf(list.get(0).getActualpay())));
		        	System.out.println("priceStrDou------------------->"+Double.valueOf(priceStr));
		        	System.out.println("ActualpayDou------------------->"+Double.valueOf(list.get(0).getActualpay()));
		        	Boolean booo = Double.valueOf(priceStr)==Double.valueOf(list.get(0).getActualpay());
		        	System.out.println("bijiaoDou------------------->"+booo);
		        	if(priceStr.equals(String.valueOf(list.get(0).getActualpay()))){
		        		System.out.println("============================jinlai");
		        		if("1".equals(paymentList.get(0).getPaytype())){
			        		ZHBRefundUtil.zfbRefund1(zlnfTakeawayOrderRequest.getOrdercode()+"N", Double.valueOf(list.get(0).getFdeposit()));
		        		}else if("2".equals(paymentList.get(0).getPaytype())){
			        		//申请微信退款
				        	RefundUtil.wechatRefund(zlnfTakeawayOrderRequest.getOrdercode()+"N", Double.valueOf(list.get(0).getFdeposit())*100);//list.get(0).getDeposit()
				        	System.out.println("weixintuikuan--------------weixintuikuan");
		        		}
		        		zlnfTakeawayOrder.setStatus("11");
		        		System.out.println("Status1-----------------------"+zlnfTakeawayOrder.getStatus());
		        		ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
		        		paymenttype.setId(paymentList.get(0).getId());
		        		paymenttype.setStatus("1");
		        		paymenttypeService.update(paymenttype);
		        	}
	        	}
	        	System.out.println("Status1-----------------------"+zlnfTakeawayOrder.getStatus());
	        	takeawayOrderService.update(zlnfTakeawayOrder);
	        }else{
	        	System.out.println("=========================>4");
	        	if("3".equals(zlnfTakeawayOrderRequest.getPaytype())){
	        		zlnfTakeawayOrder.setHmpay("1");
	        	}
	        	System.out.println("=========================>5");
	        	if("2".equals(list.get(0).getStatus())){
	        		zlnfTakeawayOrder.setStatus(status);
	        		if("12".equals(status)){
	        			send_1(list,"2");
	        		}else if("13".equals(status)){
	        			send_2(list,"1");
	        		}
	        	}else if("12".equals(list.get(0).getStatus()) || "13".equals(list.get(0).getStatus())){
	        		zlnfTakeawayOrder.setStatus("3");
	        		send_1(list,"8");
	        		send_2(list,"8");
	        	}else if("5".equals(list.get(0).getStatus())){
	        		System.out.println("=========================>6");
	        		send_1(list,"5");
	        		ZlnfPaymenttypeExample paymenttypeExample = new ZlnfPaymenttypeExample();
		        	ZlnfPaymenttypeExample.Criteria paymenttypeCriteria = paymenttypeExample.createCriteria();
		        	paymenttypeCriteria.andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
		        	paymenttypeCriteria.andPaycodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode()+"N");
		        	System.out.println("=========================>61");
		        	List<ZlnfPaymenttype> paymentList = paymenttypeService.getResult(paymenttypeExample);
		        	System.out.println("=========================>62");
		        	if("1".equals(paymentList.get(0).getPaytype())){
		        		ZHBRefundUtil.zfbRefund1(zlnfTakeawayOrderRequest.getOrdercode()+"N", Double.valueOf(list.get(0).getFdeposit()));
		        		System.out.println("=========================>64");
		        	}else if("2".equals(paymentList.get(0).getPaytype())){
		        		//申请微信退款
		        		RefundUtil.wechatRefund(zlnfTakeawayOrderRequest.getOrdercode()+"N", Double.valueOf(list.get(0).getFdeposit())*100);//list.get(0).getDeposit()
		        		System.out.println("=========================>65");
		        	}
		        	ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
		        	paymenttype.setId(paymentList.get(0).getId());
		        	paymenttype.setStatus("1");
		        	System.out.println("=========================>66");
		        	paymenttypeService.update(paymenttype);
		        	System.out.println("=========================>67");
	        		zlnfTakeawayOrder.setStatus("11");
	        	}
	        	System.out.println("=========================>68");
	        	takeawayOrderService.update(zlnfTakeawayOrder);
	        }
	        System.out.println("=========================>7");
	        ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
	        paymenttype.setBillcode(billcode);
	        paymenttype.setOrdercode(zlnfTakeawayOrderRequest.getOrdercode());
	        paymenttype.setPaycode(zlnfTakeawayOrderRequest.getSerialNum());
	        paymenttype.setPaydate(date);
	        paymenttype.setPaytype(zlnfTakeawayOrderRequest.getPaytype());
	        paymenttype.setCreatedonutc(date);
	        String roletype = zlnfTakeawayOrderRequest.getRoletype();
	        paymenttype.setRoletype(roletype);
	        System.out.println("=========================>8");
	        if("1".equals(roletype)){
	        	if(list.get(0).getCoopcode()!=null){
	        		paymenttype.setRolecode(list.get(0).getCoopcode());
	        	}
	        }else{
	        	if(list.get(0).getMerchantcode()!=null){
	        		paymenttype.setRolecode(list.get(0).getMerchantcode());
	        	}
	        }
	        System.out.println("=========================>9");
	        paymenttype.setOrderpaytype(zlnfTakeawayOrderRequest.getOrderpaytype());
	        if("1".equals(zlnfTakeawayOrderRequest.getOrderpaytype())){
	        	if(list.get(0).getDeposit()!=null){
	        		//TODO 定金金额
	        		paymenttype.setRefund(Double.valueOf(list.get(0).getDeposit()));
	        	}
	        }else{
	        	if(list.get(0).getActualpay()!=null){
	        		//TODO 支付余款金额
	        		paymenttype.setRefund(Double.valueOf(list.get(0).getActualpay()));
	        	}
	        }
	        System.out.println("=========================>10");
	        if(!"5".equals(zlnfTakeawayOrderRequest.getPaytype())){
	        	paymenttypeService.create(paymenttype);
	        }
	        System.out.println("=========================>11");
        	result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("修改订单成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("============================>shibai");
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("修改订单失败");
		}
        return result;
    }

    
    private NumberFormat DecimalFormat(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
     * 查询平均价格
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/estimatedPrice"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult estimatedPrice(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
    	zlnfTakeawayOrder.setCoopcode(zlnfTakeawayOrderRequest.getCoopcode());
    	zlnfTakeawayOrder.setServicetype(zlnfTakeawayOrderRequest.getServicetype());
    	try {
    		List<Map<String, Object>> list = takeawayOrderService.queryPrice(zlnfTakeawayOrder);
    		Map<String, Object> map = new HashMap<String, Object>();
    		result.setSuccess(true);
    		if(list.get(0)!=null && !"null".equals(list.get(0))){
    			map.put("PRICE", Double.valueOf(String.valueOf(list.get(0).get("PRICE"))));
    		}else{
    			map.put("PRICE", Double.valueOf("0"));
    		}
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("查询平均价格成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询平均价格失败");
    	}
    	return result;
    }
    
    
    /**
     * 合作社获取订单完成码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/validCode"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult takeawayOrderValidCode(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
        takeawayOrderExample.createCriteria().andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
        List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
        zlnfTakeawayOrder.setId(orderList.get(0).getId());
        String validCode = DigestUtils.generateRandomNumber(6);
        zlnfTakeawayOrder.setRegvalidcode(validCode);
        zlnfTakeawayOrder.setStatus("4");
    	try {
    		takeawayOrderService.update(zlnfTakeawayOrder);
    		send_1(orderList,"3");
    		result.setSuccess(true);
    		result.setData(validCode);
        	result.setReturnCode("0000");
        	result.setMessage("发送订单完成码成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("发送订单完成码失败");
		}
        return result;
    }

    /**
     * 服务商完成订单
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/status"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult takeawayOrderStatus(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
    	takeawayOrderExample.createCriteria().andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
    	List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
    	if(zlnfTakeawayOrderRequest.getRegvalidcode().equals(orderList.get(0).getRegvalidcode())){
    		ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
        	zlnfTakeawayOrder.setId(orderList.get(0).getId());
        	zlnfTakeawayOrder.setStatus("5");
        	try {
        		takeawayOrderService.update(zlnfTakeawayOrder);
        		send_2(orderList,"4");
        		result.setSuccess(true);
        		result.setReturnCode("0000");
        		result.setMessage("完成订单成功");
        	} catch (Exception e) {
        		// TODO: handle exception
        		result.setSuccess(false);
        		result.setReturnCode("1111");
        		result.setMessage("完成订单失败");
        	}
    	}else{
    		result.setSuccess(false);
    		result.setReturnCode("0001");
    		result.setMessage("完成码输入错误");
    	}
    	return result;
    }
    
    /**
     * 获取订单语音
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/queryVoice"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult takeawayOrderqueryVoice(String channel, String key, String data,HttpServletResponse response) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
        takeawayOrderExample.createCriteria().andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
        List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
    	try {
    		result.setSuccess(true);
    		result.setData(orderList.get(0).getVoice());
        	result.setReturnCode("0000");
        	result.setMessage("下载语音成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("下载语音失败");
		}
        return result;
    }
    
    
    /**
     * 获取订单验证码
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/queryRegvalidcode"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult takeawayOrderRegvalidcode(String channel, String key, String data,HttpServletResponse response) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
        // 2. 业务检查
        if (zlnfTakeawayOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
        takeawayOrderExample.createCriteria().andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
        List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
    	try {
    		result.setSuccess(true);
    		result.setData(orderList.get(0).getRegvalidcode());
        	result.setReturnCode("0000");
        	result.setMessage("获取验证码成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取验证码失败");
		}
        return result;
    }

    /**
     * 查看支付状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/queryStatus"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryStatus(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
	    	ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
	    	takeawayOrderExample.createCriteria().andOrdercodeEqualTo(zlnfTakeawayOrderRequest.getOrdercode());
	    	List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
	    	String paytype = zlnfTakeawayOrderRequest.getPaytype();
	    	String roletype = zlnfTakeawayOrderRequest.getRoletype();
	    	Boolean bo = false;
	    	if(orderList.size()>0){
	    		String status = orderList.get(0).getStatus();
	    		if("1".equals(paytype)){
	    			if("1".equals(roletype)){
	    				if("13".equals(status) || "3".equals(status)){
	    					bo = true;
	    				}
	    			}else if("2".equals(roletype)){
	    				if("12".equals(status) || "3".equals(status)){
	    					bo = true;
	    				}
	    			}
	    		}else if("2".equals(paytype)){
	    			if("11".equals(status)){
						bo = true;
					}
	    		}
	    	}
    		result.setSuccess(true);
    		result.setData(bo);
    		result.setReturnCode("0000");
    		result.setMessage("查询支付状态成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询支付状态失败");
    	}
    	return result;
    }
    
    //给服务商推送消息
    public void send_1(List<ZlnfTakeawayOrder> list,String type){
    	if(list.get(0).getMerchantcode()!=null){
    		List<Map<String, String>> listStr = new ArrayList<>();
    		ZlnfOrderUserExample orderUserExample = new ZlnfOrderUserExample();
    		orderUserExample.createCriteria().andOrdercodeEqualTo(list.get(0).getOrdercode());
    		ZlnfFcustofomerin fcustofomerin = customerInfoService.queryFarmMachineyMerchant(list.get(0).getMerchantcode());
    		Map<String, String> m = new HashMap<>();
    		m.put("mobile", fcustofomerin.getMobile());
    		m.put("usercode", fcustofomerin.getUsercode());
    		listStr.add(m);
    		List<ZlnfOrderUser> orderUserList = orderUserService.getResult(orderUserExample);
    		if(orderUserList.get(0).getSucode()!=null){
    			ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    			fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getSucode());
    			List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    			if(custList.size()>0){
    				Map<String, String> map = new HashMap<>();
    				map.put("mobile", fcustofomerin.getMobile());
    	    		map.put("usercode", fcustofomerin.getUsercode());
    				listStr.add(map);
    			}
    		}
    		if(orderUserList.get(0).getFmcode()!=null){
    			ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    			fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getFmcode());
    			List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    			if(custList.size()>0){
    				Map<String, String> map = new HashMap<>();
    				map.put("mobile", fcustofomerin.getMobile());
    	    		map.put("usercode", fcustofomerin.getUsercode());
    				listStr.add(map);
    			}
    		}
    		if(orderUserList.get(0).getFinancecode()!=null){
    			ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
    			fcustofomerinExample.createCriteria().andUsercodeEqualTo(orderUserList.get(0).getFinancecode());
    			List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
    			if(custList.size()>0){
    				Map<String, String> map = new HashMap<>();
    				map.put("mobile", fcustofomerin.getMobile());
    	    		map.put("usercode", fcustofomerin.getUsercode());
    				listStr.add(map);
    			}
    		}
    		for(int i = 0;i<listStr.size();i++){
    			try {
    				MSG4Jpush.ddgl_send2HZS(list.get(0).getOrdercode(),type,listStr.get(i).get("mobile"),listStr.get(i).get("usercode"));
				} catch (Exception e) {
					// TODO: handle exception
					//type 2/3/5/6/8
					try {
						if("2".equals(type)){
							SmsUtils.sendMsg(listStr.get(i).get("mobile"), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，合作社已支付定金", "");
						}else if("3".equals(type)){
							SmsUtils.sendMsg(listStr.get(i).get("mobile"), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，请向合作社或农户获取完成码", "");
						}else if("5".equals(type)){
							SmsUtils.sendMsg(listStr.get(i).get("mobile"), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，合租社或农户已支付余款", "");
						}else if("6".equals(type)){
							SmsUtils.sendMsg(listStr.get(i).get("mobile"), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，合作社取消订单", "");
						}else if("8".equals(type)){
							SmsUtils.sendMsg(listStr.get(i).get("mobile"), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，双方都已支付定金", "");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
    		}
    	}
    }
    
    //给合作社推送消息
    public void send_2(List<ZlnfTakeawayOrder> list,String type){
    	if(list.get(0).getCoopcode()!=null){
    		ZlnfFcustofomerinExample fcustofomerinExample = new ZlnfFcustofomerinExample();
			fcustofomerinExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
			List<ZlnfFcustofomerin> custList = customerInfoService.getResult(fcustofomerinExample);
			if(custList.size()>0){
				try {
					MSG4Jpush.ddgl_send2HZS(list.get(0).getOrdercode(),type,custList.get(0).getMobile(),custList.get(0).getUsercode());
				} catch (Exception e) {
					// TODO: handle exception
					try {
						if("1".equals(type)){
							SmsUtils.sendMsg(custList.get(0).getMobile(), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，服务商已支付定金", "");
						}else if("4".equals(type)){
							SmsUtils.sendMsg(custList.get(0).getMobile(), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，服务商确认完成，请尽快支付余款", "");
						}else if("7".equals(type)){
							SmsUtils.sendMsg(custList.get(0).getMobile(), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，服务商取消订单", "");
						}else if("8".equals(type)){
							SmsUtils.sendMsg(custList.get(0).getMobile(), "【中粮农服】：您订单号为"+list.get(0).getOrdercode()+"的订单，双方都已支付定金", "");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
			}
    	}
    }
    
    /**
     * 查看支付状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/payment/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult paymentQuery(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfPaymenttypeExample paymenttypeExample = new ZlnfPaymenttypeExample();
    		String serialNum = zlnfTakeawayOrderRequest.getSerialNum();
    		System.out.println("/api/payment/query========serialNum===============>"+serialNum);
    		paymenttypeExample.createCriteria().andPaycodeEqualTo(serialNum);
    		List<ZlnfPaymenttype> paymentList = paymenttypeService.getResult(paymenttypeExample);
    		Boolean bo = true;
    		if(paymentList.size()>0){
    			bo = false;
    		}
    		System.out.println("/api/payment/query========paymentList.size===============>"+paymentList.size());
    		result.setSuccess(true);
    		result.setData(bo);
    		result.setReturnCode("0000");
    		result.setMessage("查询交易流水成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询交易流水失败");
    	}
    	return result;
    }

    /**
     * 合作社查看订单状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/queryStates2"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryStates2(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		Map<String, String> map = new HashMap<>();
    		List<ZlnfTakeawayOrder> orderList = new ArrayList<>();
    		//查询待确认
    		ZlnfTakeawayOrderExample takeawayOrderExample1 = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria criteria1 = takeawayOrderExample1.createCriteria();
    		criteria1.andIsdeletedEqualTo("0");
	    	criteria1.andStatusEqualTo("1");
	    	criteria1.andUsercodeEqualTo(zlnfTakeawayOrderRequest.getUsercode());
	    	orderList = takeawayOrderService.getResult(takeawayOrderExample1);
	    	if(orderList.size()>0){
	    		map.put("one", "1");
	    	}else{
	    		map.put("one", "0");
	    	}
	    	//代付定金
	    	ZlnfTakeawayOrderExample takeawayOrderExample2 = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria criteria2 = takeawayOrderExample2.createCriteria();
    		criteria2.andIsdeletedEqualTo("0");
    		criteria2.andStatusEqualTo("2' or STATUS='13");
    		criteria2.andUsercodeEqualTo(zlnfTakeawayOrderRequest.getUsercode());
	    	orderList = takeawayOrderService.getResult(takeawayOrderExample2);
	    	if(orderList.size()>0){
	    		map.put("two", "1");
	    	}else{
	    		map.put("two", "0");
	    	}
	    	//待完成
	    	ZlnfTakeawayOrderExample takeawayOrderExample3 = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria criteria3 = takeawayOrderExample3.createCriteria();
    		criteria3.andIsdeletedEqualTo("0");
	    	criteria3.andStatusEqualTo("3");
	    	criteria3.andUsercodeEqualTo(zlnfTakeawayOrderRequest.getUsercode());
	    	orderList = takeawayOrderService.getResult(takeawayOrderExample3);
	    	if(orderList.size()>0){
	    		map.put("three", "1");
	    	}else{
	    		map.put("three", "0");
	    	}
	    	//待支付尾款
	    	ZlnfTakeawayOrderExample takeawayOrderExample4 = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria criteria4 = takeawayOrderExample4.createCriteria();
    		criteria4.andIsdeletedEqualTo("0");
	    	criteria4.andStatusEqualTo("5");
	    	criteria4.andUsercodeEqualTo(zlnfTakeawayOrderRequest.getUsercode());
	    	orderList = takeawayOrderService.getResult(takeawayOrderExample4);
	    	if(orderList.size()>0){
	    		map.put("four", "1");
	    	}else{
	    		map.put("four", "0");
	    	}
	    	//待评价
	    	ZlnfTakeawayOrderExample takeawayOrderExample5 = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria criteria5 = takeawayOrderExample5.createCriteria();
    		criteria5.andIsdeletedEqualTo("0");
	    	criteria5.andEvaluateEqualTo("0' or STATUS='2");
	    	criteria5.andUsercodeEqualTo(zlnfTakeawayOrderRequest.getUsercode());
	    	orderList = takeawayOrderService.getResult(takeawayOrderExample5);
	    	if(orderList.size()>0){
	    		map.put("five", "1");
	    	}else{
	    		map.put("five", "0");
	    	}
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("查询合作社订单状态成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询合作社订单状态失败");
    	}
    	return result;
    }

    /**
     * 服务商查看订单状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/takeawayOrder/queryStates1"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryStates1(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTakeawayOrderRequest zlnfTakeawayOrderRequest  = convert(data, ZlnfTakeawayOrderRequest.class);
    	// 2. 业务检查
    	if (zlnfTakeawayOrderRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		Map<String, String> map = new HashMap<>();
    		List<ZlnfTakeawayOrder> orderList = new ArrayList<>();
    		if("2".equals(zlnfTakeawayOrderRequest.getRoletype1()) || "5".equals(zlnfTakeawayOrderRequest.getRoletype1()) || "6".equals(zlnfTakeawayOrderRequest.getRoletype1())){
    			//查询待付定金
        		ZlnfTakeawayOrderExample takeawayOrderExample1 = new ZlnfTakeawayOrderExample();
        		ZlnfTakeawayOrderExample.Criteria criteria1 = takeawayOrderExample1.createCriteria();
        		criteria1.andIsdeletedEqualTo("0");
        		criteria1.andStatusEqualTo("2' or STATUS='12");
        		criteria1.andMerchantcodeEqualTo(zlnfTakeawayOrderRequest.getMerchantcode());
        		orderList = takeawayOrderService.getResult(takeawayOrderExample1);
        		if(orderList.size()>0){
        			map.put("one", "1");
        		}else{
        			map.put("one", "0");
        		}
        		//待服务完成
        		ZlnfTakeawayOrderExample takeawayOrderExample2 = new ZlnfTakeawayOrderExample();
        		ZlnfTakeawayOrderExample.Criteria criteria2 = takeawayOrderExample2.createCriteria();
        		criteria2.andIsdeletedEqualTo("0");
        		criteria2.andStatusEqualTo("4");
        		orderList = takeawayOrderService.getResult(takeawayOrderExample2);
        		if(orderList.size()>0){
        			map.put("two", "1");
        		}else{
        			map.put("two", "0");
        		}
    		}else{
    			//查询待付定金
    			ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
    			if("1".equals(zlnfTakeawayOrderRequest.getRoletype1())){
    				zlnfTakeawayOrder.setSucode(zlnfTakeawayOrderRequest.getUsercode());
    			}else if("3".equals(zlnfTakeawayOrderRequest.getRoletype1())){
    				zlnfTakeawayOrder.setFinancecode(zlnfTakeawayOrderRequest.getUsercode());
    			}else{
    				zlnfTakeawayOrder.setFmcode(zlnfTakeawayOrderRequest.getUsercode());
    			}
        		zlnfTakeawayOrder.setIsdeleted("0");
        		zlnfTakeawayOrder.setStatus("2' or STATUS='12");
        		orderList = takeawayOrderService.queryState(zlnfTakeawayOrder);
        		if(orderList.size()>0){
        			map.put("one", "1");
        		}else{
        			map.put("one", "0");
        		}
        		//待服务完成
        		zlnfTakeawayOrder.setStatus("4");
        		orderList = takeawayOrderService.queryState(zlnfTakeawayOrder);
        		if(orderList.size()>0){
        			map.put("two", "1");
        		}else{
        			map.put("two", "0");
        		}
    		}
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("查询农机服务商订单状态成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询农机服务商订单状态失败");
    	}
    	return result;
    }

    /**
     * 查询分次付款次数
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/paymenttype/queryNum"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryNum(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfPaymenttypeRequest zlnfPaymenttypeRequest  = convert(data, ZlnfPaymenttypeRequest.class);
    	// 2. 业务检查
    	if (zlnfPaymenttypeRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
    		paymenttype.setOrdercode(zlnfPaymenttypeRequest.getOrdercode());
    		int num = paymenttypeService.queryNum(paymenttype);
    		result.setSuccess(true);
    		result.setData(num);
    		result.setReturnCode("0000");
    		result.setMessage("查询分次付款次数成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询分次付款次数失败");
    	}
    	return result;
    }

    /**
     * 添加时间轴信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/timeAxis/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult timeAxisCreate(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTimeAxisRequest zlnfTimeAxisRequest  = convert(data, ZlnfTimeAxisRequest.class);
    	// 2. 业务检查
    	if (zlnfTimeAxisRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		Boolean bo = true;
    		Boolean bo1 = false;
    		Boolean bo2 = false;
    		Date date = new Date();
    		ZlnfTimeAxis timeAxis = new ZlnfTimeAxis();
    		timeAxis.setCreatedonutc(date);
    		timeAxis.setTimeaxiscode(Uuid32.getUUID32());
    		timeAxis.setOrdercode(zlnfTimeAxisRequest.getOrdercode());
    		timeAxis.setStatus(zlnfTimeAxisRequest.getStatus());
    		timeAxis.setUsercode(zlnfTimeAxisRequest.getUsercode());
    		timeAxis.setUsername(zlnfTimeAxisRequest.getUsername());
    		if("5".equals(zlnfTimeAxisRequest.getStatus())){
    			ZlnfPaymenttype paymenttype = new ZlnfPaymenttype();
        		paymenttype.setOrdercode(zlnfTimeAxisRequest.getOrdercode());
        		int num = paymenttypeService.queryNum(paymenttype);
        		if(num!=1){
        			bo = false;
        		}
    		}
    		if("12".equals(zlnfTimeAxisRequest.getStatus())){
    			ZlnfTimeAxisExample zlnfTimeAxisExample = new ZlnfTimeAxisExample();
        		ZlnfTimeAxisExample.Criteria criteria = zlnfTimeAxisExample.createCriteria();
        		criteria.andOrdercodeEqualTo(zlnfTimeAxisRequest.getOrdercode());
        		criteria.andStatusEqualTo("13");
        		List<ZlnfTimeAxis> list = timeAxisService.getResult(zlnfTimeAxisExample);
        		if(list.size()>0){
        			bo2 = true;
        		}
    		}
    		if("13".equals(zlnfTimeAxisRequest.getStatus())){
    			ZlnfTimeAxisExample zlnfTimeAxisExample = new ZlnfTimeAxisExample();
        		ZlnfTimeAxisExample.Criteria criteria = zlnfTimeAxisExample.createCriteria();
        		criteria.andOrdercodeEqualTo(zlnfTimeAxisRequest.getOrdercode());
        		criteria.andStatusEqualTo("12");
        		List<ZlnfTimeAxis> list = timeAxisService.getResult(zlnfTimeAxisExample);
        		if(list.size()>0){
        			bo2 = true;
        		}
    		}
    		if("16".equals(zlnfTimeAxisRequest.getStatus())){
    			ZlnfTimeAxisExample zlnfTimeAxisExample = new ZlnfTimeAxisExample();
        		ZlnfTimeAxisExample.Criteria criteria = zlnfTimeAxisExample.createCriteria();
        		criteria.andOrdercodeEqualTo(zlnfTimeAxisRequest.getOrdercode());
        		criteria.andStatusEqualTo("17");
        		List<ZlnfTimeAxis> list = timeAxisService.getResult(zlnfTimeAxisExample);
        		if(list.size()>0){
        			bo1 = true;
        		}
    		}
    		if("17".equals(zlnfTimeAxisRequest.getStatus())){
    			ZlnfTimeAxisExample zlnfTimeAxisExample = new ZlnfTimeAxisExample();
        		ZlnfTimeAxisExample.Criteria criteria = zlnfTimeAxisExample.createCriteria();
        		criteria.andOrdercodeEqualTo(zlnfTimeAxisRequest.getOrdercode());
        		criteria.andStatusEqualTo("16");
        		List<ZlnfTimeAxis> list = timeAxisService.getResult(zlnfTimeAxisExample);
        		if(list.size()>0){
        			bo1 = true;
        		}
    		}
    		if(bo){
    			timeAxisService.create(timeAxis);
    		}
    		if(bo1){
    			timeAxis.setStatus("18");
    			timeAxisService.create(timeAxis);
    		}
    		if(bo2){
    			timeAxis.setStatus("3");
    			timeAxisService.create(timeAxis);
    		}
    		
    		result.setSuccess(true);
    		result.setReturnCode("0000");
    		result.setMessage("添加时间轴信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("添加时间轴信息失败");
    	}
    	return result;
    }
    
    /**
     * 查看时间轴信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/timeAxis/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult timeAxisQuery(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfTimeAxisRequest zlnfTimeAxisRequest  = convert(data, ZlnfTimeAxisRequest.class);
    	// 2. 业务检查
    	if (zlnfTimeAxisRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfTimeAxis record = new ZlnfTimeAxis();
    		record.setOrdercode(zlnfTimeAxisRequest.getOrdercode());
    		List<ZlnfTimeAxis> list = timeAxisService.queryByOrdercode(record);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询时间轴信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询时间轴信息失败");
    	}
    	return result;
    }
    
}
