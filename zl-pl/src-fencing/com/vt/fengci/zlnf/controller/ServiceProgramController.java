package com.vt.fengci.zlnf.controller;

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
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.model.ZlnfFarmServiceMachiney;
import com.vt.fencing.model.ZlnfFarmServiceMachineyExample;
import com.vt.fencing.model.ZlnfServiceProgram;
import com.vt.fencing.model.ZlnfServiceProgramExample;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fencing.model.ZlnfUserdiscuss;
import com.vt.fencing.model.ZlnfUserdiscussExample;
import com.vt.fencing.request.ZlnfFarmServiceMachineyRequest;
import com.vt.fencing.request.ZlnfServiceProgramRequest;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.IFarmServiceMachineyService;
import com.vt.fengci.zlnf.service.IServiceProgramService;
import com.vt.fengci.zlnf.service.ITakeawayOrderService;
import com.vt.fengci.zlnf.service.IUserDiscussService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class ServiceProgramController extends BaseGatewayController {

	private static final long serialVersionUID = 6095773601642669150L;
	/**
     * member service
     */
    
    @Autowired
    private IServiceProgramService serviceProgramService;

    @Autowired
    private IFarmServiceMachineyService farmServiceMachineyService;
   
    @Autowired
    private IDictInfoService dictInfoService;
    
    @Autowired
    private ITakeawayOrderService takeawayOrderService;
    
    @Autowired
    private IUserDiscussService userDiscussService;
    
    /**
     * 添加农机服务信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/serviceProgram/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult serviceProgramCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfServiceProgramRequest zlnfServiceProgramRequest  = convert(data, ZlnfServiceProgramRequest.class);
        // 2. 业务检查
        if (zlnfServiceProgramRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
        	Date date = new Date();
        	String creator = zlnfServiceProgramRequest.getCreator();
        	String foodkind = zlnfServiceProgramRequest.getFoodkind();
        	String foodkindcode = zlnfServiceProgramRequest.getFoodkindcode();
        	String serrotypen = zlnfServiceProgramRequest.getSerrotypen();
        	String agmatyprn = zlnfServiceProgramRequest.getAgmatyprn();
//        	try {
//        		servicetype = new String(.getBytes("ISO-8859-1"), "UTF-8");
//        	} catch (UnsupportedEncodingException e1) {
//        		// TODO Auto-generated catch block
//        		e1.printStackTrace();
//        	}
	        String servicecode = Uuid32.getUUID32();
	        ZlnfServiceProgram zlnfServiceProgram = new ZlnfServiceProgram();
	        zlnfServiceProgram.setServicecode(servicecode);
	        zlnfServiceProgram.setFoodkind(foodkind);
	        zlnfServiceProgram.setFoodkindcode(foodkindcode);
	        zlnfServiceProgram.setServiceprice(zlnfServiceProgramRequest.getServiceprice());
	        zlnfServiceProgram.setServiceunit(zlnfServiceProgramRequest.getServiceunit());
	        zlnfServiceProgram.setMerchantcode(zlnfServiceProgramRequest.getMerchantcode());
	        zlnfServiceProgram.setSerrotypen(serrotypen);
	        zlnfServiceProgram.setSerrotypencode(zlnfServiceProgramRequest.getSerrotypencode());
	        zlnfServiceProgram.setAgmatyprn(agmatyprn);
	        zlnfServiceProgram.setAgmatyprncode(zlnfServiceProgramRequest.getAgmatyprncode());
	        String distance = zlnfServiceProgramRequest.getDistance();
	        if(StringUtils.isNotBlank(distance)){
	        	zlnfServiceProgram.setDistance(distance);
	        }
	        zlnfServiceProgram.setCreator(creator);
	        zlnfServiceProgram.setCreatedonutc(date);
    		serviceProgramService.create(zlnfServiceProgram);
    		String farmbrands = zlnfServiceProgramRequest.getFarmbrand();
//    		String farmmodels = zlnfServiceProgramRequest.getFarmmodel();
    		String useryears = zlnfServiceProgramRequest.getUseryear();
    		String rowspacings = zlnfServiceProgramRequest.getRowspacing();
    		String farmrowss = zlnfServiceProgramRequest.getFarmrows();
    		String farmnums = zlnfServiceProgramRequest.getFarmnum();
    		String[] farmbrand = farmbrands.split(",");
//    		String[] farmmodel = farmmodels.split(",");
    		String[] useryear = useryears.split(",");
    		String[] rowspacing = rowspacings.split(",");
    		String[] farmrows = farmrowss.split(",");
    		String[] farmnum = farmnums.split(",");
    		for(int i = 0;i<farmbrand.length;i++){
    			String machinecode = Uuid32.getUUID32();
    			ZlnfFarmServiceMachiney zlnfFarmServiceMachiney = new ZlnfFarmServiceMachiney();
    			zlnfFarmServiceMachiney.setMachinecode(machinecode);
    			zlnfFarmServiceMachiney.setServicecode(servicecode);
    			zlnfFarmServiceMachiney.setFarmbrand(farmbrand[i]);
//    			zlnfFarmServiceMachiney.setFarmmodel(farmmodel[i]);
    			zlnfFarmServiceMachiney.setUseryear(useryear[i]);
    			zlnfFarmServiceMachiney.setRowspacing(Double.valueOf(rowspacing[i]));
    			zlnfFarmServiceMachiney.setFarmrows(Double.valueOf(farmrows[i]));
    			zlnfFarmServiceMachiney.setFarmnum(Integer.valueOf(farmnum[i]));
    			zlnfFarmServiceMachiney.setCreatedonutc(date);
    			zlnfFarmServiceMachiney.setCreator(creator);
    			farmServiceMachineyService.create(zlnfFarmServiceMachiney);
    		}
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("添加农机服务信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("添加农机服务信息失败");
		}
        return result;
    }
    
    
    
    /**
     * 修改农机服务信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/serviceProgram/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult serviceProgramUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfServiceProgramRequest zlnfServiceProgramRequest  = convert(data, ZlnfServiceProgramRequest.class);
        // 2. 业务检查
        if (zlnfServiceProgramRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
        	Date date = new Date();
        	String creator = zlnfServiceProgramRequest.getCreator();
        	String foodkind = zlnfServiceProgramRequest.getFoodkind();
        	String foodkindcode = zlnfServiceProgramRequest.getFoodkindcode();
        	String serrotypen = zlnfServiceProgramRequest.getSerrotypen();
        	String agmatyprn = zlnfServiceProgramRequest.getAgmatyprn();
        	ZlnfServiceProgramExample serviceProgramExample = new ZlnfServiceProgramExample();
        	ZlnfServiceProgramExample.Criteria criteria = serviceProgramExample.createCriteria();
        	criteria.andServicecodeEqualTo(zlnfServiceProgramRequest.getServicecode());
        	criteria.andIsdeletedEqualTo("0");
        	List<ZlnfServiceProgram> list = serviceProgramService.getResult(serviceProgramExample);
	        ZlnfServiceProgram zlnfServiceProgram = new ZlnfServiceProgram();
	        zlnfServiceProgram.setId(list.get(0).getId());
	        zlnfServiceProgram.setIsaudit("0");
        	if(StringUtils.isNotBlank(foodkind)){
        		zlnfServiceProgram.setFoodkind(foodkind);
        	}
        	if(StringUtils.isNotBlank(foodkindcode)){
        		zlnfServiceProgram.setFoodkindcode(foodkindcode);
        	}
	        String serviceprice = String.valueOf(zlnfServiceProgramRequest.getServiceprice());
	        if(StringUtils.isNotBlank(serviceprice)){
	        	zlnfServiceProgram.setServiceprice(zlnfServiceProgramRequest.getServiceprice());
	        	zlnfServiceProgram.setIsaudit("1");
        	}
	        if(StringUtils.isNotBlank(serrotypen)){
	        	zlnfServiceProgram.setSerrotypen(serrotypen);
	        }
	        String serrotypencode = zlnfServiceProgramRequest.getSerrotypencode();
	        if(StringUtils.isNotBlank(serrotypencode)){
	        	zlnfServiceProgram.setSerrotypencode(serrotypencode);
	        }
	        if(StringUtils.isNotBlank(agmatyprn)){
	        	zlnfServiceProgram.setAgmatyprn(agmatyprn);
	        }
	        String agmatyprncode = zlnfServiceProgramRequest.getAgmatyprncode();
	        if(StringUtils.isNotBlank(agmatyprncode)){
	        	zlnfServiceProgram.setAgmatyprncode(agmatyprncode);
	        }
	        String distance = zlnfServiceProgramRequest.getDistance();
	        if(StringUtils.isNotBlank(distance)){
	        	zlnfServiceProgram.setDistance(distance);
	        }
	        zlnfServiceProgram.setModifier(creator);
	        zlnfServiceProgram.setUpdatedonutc(date);
    		if(!"1".equals(zlnfServiceProgramRequest.getType())){
	    		ZlnfFarmServiceMachineyExample zlnfFarmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
	    		ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = zlnfFarmServiceMachineyExample.createCriteria();
	    		machineyCriteria.andServicecodeEqualTo(zlnfServiceProgramRequest.getServicecode());
	    		machineyCriteria.andIsdeletedEqualTo("0");
	    		List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(zlnfFarmServiceMachineyExample);
	    		for(int i = 0;i<machineyList.size();i++){
	    			ZlnfFarmServiceMachiney zlnfFarmServiceMachiney = new ZlnfFarmServiceMachiney();
	    			zlnfFarmServiceMachiney.setId(machineyList.get(i).getId());
	    			zlnfFarmServiceMachiney.setIsdeleted("1");
	    			farmServiceMachineyService.update(zlnfFarmServiceMachiney);
	    		}
    			String farmbrands = zlnfServiceProgramRequest.getFarmbrand();
//    			String farmmodels = zlnfServiceProgramRequest.getFarmmodel();
    			String useryears = zlnfServiceProgramRequest.getUseryear();
    			String rowspacings = zlnfServiceProgramRequest.getRowspacing();
    			String farmrowss = zlnfServiceProgramRequest.getFarmrows();
    			String farmnums = zlnfServiceProgramRequest.getFarmnum();
    			String[] farmbrand = farmbrands.split(",");
//    			String[] farmmodel = farmmodels.split(",");
    			String[] useryear = useryears.split(",");
    			String[] rowspacing = rowspacings.split(",");
    			String[] farmrows = farmrowss.split(",");
    			String[] farmnum = farmnums.split(",");
    			for(int i = 0;i<farmbrand.length;i++){
    				String machinecode = Uuid32.getUUID32();
    				ZlnfFarmServiceMachiney zlnfFarmServiceMachiney = new ZlnfFarmServiceMachiney();
    				zlnfFarmServiceMachiney.setMachinecode(machinecode);
    				zlnfFarmServiceMachiney.setServicecode(zlnfServiceProgramRequest.getServicecode());
    				zlnfFarmServiceMachiney.setFarmbrand(farmbrand[i]);
//    				zlnfFarmServiceMachiney.setFarmmodel(farmmodel[i]);
    				zlnfFarmServiceMachiney.setUseryear(useryear[i]);
    				zlnfFarmServiceMachiney.setRowspacing(Double.valueOf(rowspacing[i]));
    				zlnfFarmServiceMachiney.setFarmrows(Double.valueOf(farmrows[i]));
    				zlnfFarmServiceMachiney.setFarmnum(Integer.valueOf(farmnum[i]));
    				zlnfFarmServiceMachiney.setCreatedonutc(date);
    				zlnfFarmServiceMachiney.setCreator(creator);
    				farmServiceMachineyService.create(zlnfFarmServiceMachiney);
    			}
    			zlnfServiceProgram.setIsaudit("0");
    		}
    		serviceProgramService.update(zlnfServiceProgram);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("修改农机服务信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("修改农机服务信息失败");
		}
        return result;
    }

    
    /**
     * 查询农机服务信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/serviceProgram/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult serviceProgramQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfServiceProgramRequest zlnfServiceProgramRequest  = convert(data, ZlnfServiceProgramRequest.class);
    	// 2. 业务检查
    	if (zlnfServiceProgramRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {//
    		
    		ZlnfServiceProgram zlnfServiceProgram = new ZlnfServiceProgram();
    		zlnfServiceProgram.setMerchantcode(zlnfServiceProgramRequest.getMerchantcode());
    		zlnfServiceProgram.setNumber(zlnfServiceProgramRequest.getNumber());
    		String serrotypencode = zlnfServiceProgramRequest.getSerrotypencode();
	        if(StringUtils.isNotBlank(serrotypencode)){
	        	zlnfServiceProgram.setSerrotypencode(serrotypencode);
	        }
	        Map<String, Object> map = new HashMap<>();
	        ZlnfServiceProgramExample serviceProgramExample = new ZlnfServiceProgramExample();
	        ZlnfServiceProgramExample.Criteria programCriteria = serviceProgramExample.createCriteria();
	        programCriteria.andIsdeletedEqualTo("0");
	        programCriteria.andMerchantcodeEqualTo(zlnfServiceProgramRequest.getMerchantcode());
	        programCriteria.andSerrotypencodeEqualTo(serrotypencode);
	        int num = 0;
	        List<ZlnfServiceProgram> list1 = serviceProgramService.getResult(serviceProgramExample);
	        num = list1.size();
//	        for(int i = 0;i<list1.size();i++){
//	        	ZlnfFarmServiceMachineyExample zlnfFarmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
//    			ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = zlnfFarmServiceMachineyExample.createCriteria();
//    			machineyCriteria.andServicecodeEqualTo(list1.get(i).getServicecode());
//    			machineyCriteria.andIsdeletedEqualTo("0");
//    			List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(zlnfFarmServiceMachineyExample);
//    			num = num + machineyList.size();
//	        }
    		List<ZlnfServiceProgram> list = serviceProgramService.queryProgramRownum(zlnfServiceProgram);
    		for(int i = 0;i<list.size();i++){
    			ZlnfFarmServiceMachineyExample zlnfFarmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
    			ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = zlnfFarmServiceMachineyExample.createCriteria();
    			machineyCriteria.andServicecodeEqualTo(list.get(i).getServicecode());
    			machineyCriteria.andIsdeletedEqualTo("0");
    			List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(zlnfFarmServiceMachineyExample);
    			for(int j = 0;j<machineyList.size();j++){
    				ZlnfDictInfoExample dictInfoExample1 = new ZlnfDictInfoExample();
    				ZlnfDictInfoExample.Criteria criteria1 = dictInfoExample1.createCriteria();
    				criteria1.andIsdeletedEqualTo("0");
    				criteria1.andDictcodeEqualTo(machineyList.get(j).getFarmbrand());
    				List<ZlnfDictInfo> dictInfoList1 = dictInfoService.getResult(dictInfoExample1);
    				if(dictInfoList1.size()>0){
    					machineyList.get(j).setFarmbrandname(dictInfoList1.get(0).getDictname());
    				}
    			}
    			list.get(i).setMachineyList(machineyList);
    			//查询评价信息
    			int fraction = 0;
    			int fraction1 = 0;
    			int fraction2 = 0;
    			int size = 0;
    			int size1 = 0;
    			ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
    			ZlnfTakeawayOrderExample.Criteria orderCriteria = takeawayOrderExample.createCriteria();
    			orderCriteria.andServicecodeEqualTo(list.get(i).getServicecode());
    			orderCriteria.andIsdeletedEqualTo("0");
    			List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
    			for(int j = 0;j<orderList.size();j++){
    				ZlnfUserdiscussExample userdiscussExample = new ZlnfUserdiscussExample();
    				ZlnfUserdiscussExample.Criteria userdiscussCriteria = userdiscussExample.createCriteria();
    				userdiscussCriteria.andServercodeEqualTo(orderList.get(j).getOrdercode());
    				userdiscussCriteria.andIsdeletedEqualTo("0");
    				userdiscussCriteria.andEvaluatetypeEqualTo("2");
    				List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(userdiscussExample);
    				for(int k = 0;k<userdiscussList.size();k++){
    					fraction = fraction + Integer.valueOf(userdiscussList.get(k).getDislevel());
    					size++;
    				}
    			}
    			if(size>0){
    				fraction2 = fraction(fraction, size);
    			}
    			orderCriteria.andMerchantcodeEqualTo(list.get(i).getMerchantcode());
    			List<ZlnfTakeawayOrder> orderList1 = takeawayOrderService.getResult(takeawayOrderExample);
    			for(int j = 0;j<orderList1.size();j++){
    				ZlnfUserdiscussExample userdiscussExample = new ZlnfUserdiscussExample();
    				ZlnfUserdiscussExample.Criteria userdiscussCriteria = userdiscussExample.createCriteria();
    				userdiscussCriteria.andServercodeEqualTo(orderList1.get(j).getOrdercode());
    				userdiscussCriteria.andIsdeletedEqualTo("0");
    				userdiscussCriteria.andEvaluatetypeEqualTo("2");
    				List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(userdiscussExample);
    				for(int k = 0;k<userdiscussList.size();k++){
    					fraction1 = fraction1 + Integer.valueOf(userdiscussList.get(k).getDislevel());
    					size1++;
    				}
    			}
    			if(size1>0){
    				fraction1 = fraction(fraction1, size1);
    			}
    			list.get(i).setFraction(fraction1);
    			if(fraction1>fraction2){
    				list.get(i).setFractionType("高");
    			}else if(fraction1<fraction2){
    				list.get(i).setFractionType("低");
    			}else{
    				list.get(i).setFractionType("相等");
    			}
    		}
    		map.put("num", num);
    		map.put("list", list);
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("获取农机服务信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取农机服务信息失败");
    	}
    	return result;
    }
 
    
    /**
     * 农机服务周边信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/serviceProgram/queryPeriphery"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult serviceProgramQueryPeriphery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfServiceProgramRequest zlnfServiceProgramRequest  = convert(data, ZlnfServiceProgramRequest.class);
    	// 2. 业务检查
    	if (zlnfServiceProgramRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfServiceProgram zlnfServiceProgram = new ZlnfServiceProgram();
    		zlnfServiceProgram.setUsercode(zlnfServiceProgramRequest.getUsercode());
    		zlnfServiceProgram.setNumber(zlnfServiceProgramRequest.getNumber());
    		List<ZlnfServiceProgram> list = serviceProgramService.queryPeriphery(zlnfServiceProgram);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("获取周边服务信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取周边服务信息失败");
    	}
    	return result;
    }

    
    /**
     * 删除农机服务机械信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmServiceMachineyService/delete"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmServiceMachineyServiceDelete(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFarmServiceMachineyRequest zlnfFarmServiceMachineyRequest  = convert(data, ZlnfFarmServiceMachineyRequest.class);
    	// 2. 业务检查
    	if (zlnfFarmServiceMachineyRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfFarmServiceMachineyExample zlnfFarmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
    		ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = zlnfFarmServiceMachineyExample.createCriteria();
    		machineyCriteria.andIsdeletedEqualTo("0");
    		machineyCriteria.andMachinecodeEqualTo(zlnfFarmServiceMachineyRequest.getMachinecode());
    		List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(zlnfFarmServiceMachineyExample);
    		ZlnfFarmServiceMachiney zlnfFarmServiceMachiney = new ZlnfFarmServiceMachiney();
    		zlnfFarmServiceMachiney.setId(machineyList.get(0).getId());
    		zlnfFarmServiceMachiney.setIsdeleted("1");
    		farmServiceMachineyService.update(zlnfFarmServiceMachiney);
    		result.setSuccess(true);
    		result.setReturnCode("0000");
    		result.setMessage("删除机械信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("删除机械信息失败");
    	}
    	return result;
    }

    
    /**
     * 删除农机服务信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/serviceProgram/delete"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult serviceProgramDelete(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfServiceProgramRequest zlnfServiceProgramRequest  = convert(data, ZlnfServiceProgramRequest.class);
    	// 2. 业务检查
    	if (zlnfServiceProgramRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfServiceProgramExample serviceProgramExample = new ZlnfServiceProgramExample();
    		ZlnfServiceProgramExample.Criteria criteria = serviceProgramExample.createCriteria();
    		criteria.andIsdeletedEqualTo("0");
    		criteria.andServicecodeEqualTo(zlnfServiceProgramRequest.getServicecode());
    		List<ZlnfServiceProgram> list = serviceProgramService.getResult(serviceProgramExample);
    		ZlnfServiceProgram zlnfServiceProgram = new ZlnfServiceProgram();
    		zlnfServiceProgram.setId(list.get(0).getId());
    		zlnfServiceProgram.setIsdeleted("1");
    		serviceProgramService.update(zlnfServiceProgram);
    		for(int i = 0;i<list.size();i++){
    			ZlnfFarmServiceMachineyExample zlnfFarmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
    			ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = zlnfFarmServiceMachineyExample.createCriteria();
    			machineyCriteria.andServicecodeEqualTo(list.get(i).getServicecode());
    			machineyCriteria.andIsdeletedEqualTo("0");
    			List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(zlnfFarmServiceMachineyExample);
    			ZlnfFarmServiceMachiney zlnfFarmServiceMachiney = new ZlnfFarmServiceMachiney();
    			zlnfFarmServiceMachiney.setId(machineyList.get(i).getId());
    			zlnfFarmServiceMachiney.setIsdeleted("1");
    			farmServiceMachineyService.update(zlnfFarmServiceMachiney);
    		}
    		result.setSuccess(true);
    		result.setReturnCode("0000");
    		result.setMessage("删除农机服务信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("删除农机服务信息失败");
    	}
    	return result;
    }
    
    public int fraction(int fraction,int num){
    	int a = Math.round(fraction/num);
    	return a;
    }
    
    
}
