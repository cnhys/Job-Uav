package com.vt.fengci.zlnf.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfApppiontOrder;
import com.vt.fencing.model.ZlnfApppiontOrderExample;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fencing.model.ZlnfGraindepotNew;
import com.vt.fencing.model.ZlnfGraindepotNewExample;
import com.vt.fencing.request.ZlnfApppiontOrderRequest;
import com.vt.fengci.zlnf.service.IApppiontOrderService;
import com.vt.fengci.zlnf.service.IFoodInfoService;
//import com.vt.fengci.zlnf.service.IGraindepotHisService;
import com.vt.fengci.zlnf.service.IGraindepotNewService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class ApppiontOrderController extends BaseGatewayController {

	private static final long serialVersionUID = -1884042170244964227L;
	/**
     * member service
     */
    
    @Autowired
    private IApppiontOrderService apppiontOrderService;
    
    @Autowired
    private IFoodInfoService foodInfoService;
    
//    @Autowired
//    private IGraindepotHisService graindepotHisService;
    
    @Autowired
    private IGraindepotNewService graindepotNewService;
    
    
    /**
     * 新增预约价格信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrder/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderRequest zlnfApppiontOrderRequest  = convert(data, ZlnfApppiontOrderRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
    	Date date = new Date();
    	String foodkind = zlnfApppiontOrderRequest.getFoodkind();
    	String foodkindcode = zlnfApppiontOrderRequest.getFoodkindcode();
    	String creator = zlnfApppiontOrderRequest.getCreator();
    	String orderlevel = zlnfApppiontOrderRequest.getOrderlevel();
    	Calendar now = Calendar.getInstance();  
    	now.setTime(date);  
    	now.set(Calendar.DATE, now.get(Calendar.DATE) + zlnfApppiontOrderRequest.getAppiontdateInt());
    	//添加预约售价信息
    	String foodcodes = zlnfApppiontOrderRequest.getFoodcode();
    	String foodnames = zlnfApppiontOrderRequest.getFoodname();
    	String[] foodcode = foodcodes.split(",");
    	String[] foodname = foodnames.split(",");
    	try {
	    	if(foodcode.length>0){
	    		for(int i = 0;i<foodcode.length;i++){
	    			ZlnfApppiontOrder zlnfApppiontOrder = new ZlnfApppiontOrder();
	            	String ordercode =Uuid32.getUUID32();
	            	zlnfApppiontOrder.setOrdercode(ordercode);
	            	zlnfApppiontOrder.setFoodcode(foodcode[i]);
	            	zlnfApppiontOrder.setState("1");
	            	zlnfApppiontOrder.setFoodkind(foodkind);
	            	zlnfApppiontOrder.setFoodkindcode(foodkindcode);
	            	zlnfApppiontOrder.setAppionttime(date);
	            	zlnfApppiontOrder.setOrderlevel(orderlevel);
	            	zlnfApppiontOrder.setFoodlevelcode(zlnfApppiontOrderRequest.getFoodlevelcode());
	            	zlnfApppiontOrder.setProductname(zlnfApppiontOrderRequest.getProductname());
	            	zlnfApppiontOrder.setWater(zlnfApppiontOrderRequest.getWater());
	            	zlnfApppiontOrder.setFoodname(foodname[i]);
	            	zlnfApppiontOrder.setRemaintime(now.getTime());
	            	zlnfApppiontOrder.setCoopcode(zlnfApppiontOrderRequest.getCoopcode());
	            	zlnfApppiontOrder.setCreatedonutc(date);
	            	zlnfApppiontOrder.setCreator(creator);
	            	zlnfApppiontOrder.setCreatorcode(zlnfApppiontOrderRequest.getCreatorcode());
	            	apppiontOrderService.create(zlnfApppiontOrder);
	    		}
	    	}
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("添加预约价格成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加预约价格失败");
		}
        return result;
    }
    
    /**
     * 修改预约价格信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrder/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderRequest zlnfApppiontOrderRequest  = convert(data, ZlnfApppiontOrderRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        Date date = new Date();
        String foodkind = zlnfApppiontOrderRequest.getFoodkind();
        String foodkindcode = zlnfApppiontOrderRequest.getFoodkindcode();
        String creator = zlnfApppiontOrderRequest.getCreator();
        String orderlevel = zlnfApppiontOrderRequest.getOrderlevel();
        String status = zlnfApppiontOrderRequest.getStatus();
        ZlnfApppiontOrderExample apppiontOrderExample = new ZlnfApppiontOrderExample();
        apppiontOrderExample.createCriteria().andOrdercodeEqualTo(zlnfApppiontOrderRequest.getOrdercode());
        List<ZlnfApppiontOrder> list = apppiontOrderService.getResult(apppiontOrderExample);
        ZlnfApppiontOrder zlnfApppiontOrder = new ZlnfApppiontOrder();
        zlnfApppiontOrder.setId(list.get(0).getId());
    	zlnfApppiontOrder.setState("1");
    	if(StringUtils.isNotBlank(foodkind)){
    		zlnfApppiontOrder.setFoodkind(foodkind);
    	}
    	if(StringUtils.isNotBlank(foodkindcode)){
    		zlnfApppiontOrder.setFoodkindcode(foodkindcode);
    	}
    	if(StringUtils.isNotBlank(orderlevel)){
    		zlnfApppiontOrder.setOrderlevel(orderlevel);
    	}
    	Integer appiontdateInt = zlnfApppiontOrderRequest.getAppiontdateInt();
    	if(appiontdateInt!=null && appiontdateInt!=0){
    		Calendar now = Calendar.getInstance();  
        	now.setTime(date);  
        	now.set(Calendar.DATE, now.get(Calendar.DATE) + zlnfApppiontOrderRequest.getAppiontdateInt());
        	zlnfApppiontOrder.setRemaintime(now.getTime());
    	}
    	String productnam = zlnfApppiontOrderRequest.getProductname();
    	if(StringUtils.isNotBlank(productnam)){
    		zlnfApppiontOrder.setProductname(productnam);
    	}
    	String water = zlnfApppiontOrderRequest.getWater();
    	if(StringUtils.isNotBlank(water)){
    		zlnfApppiontOrder.setWater(water);
    	}
    	String foodcode = zlnfApppiontOrderRequest.getFoodcode();
    	if(StringUtils.isNotBlank(foodcode)){
    		zlnfApppiontOrder.setFoodcode(foodcode);
    	}
    	if("1".equals(zlnfApppiontOrderRequest.getType())){
    		Calendar now = Calendar.getInstance();  
    		now.setTime(date);  
    		now.set(Calendar.DATE, now.get(Calendar.DATE) + zlnfApppiontOrderRequest.getAppiontdateInt());
    		zlnfApppiontOrder.setRemaintime(now.getTime());
    	}
    	zlnfApppiontOrder.setUpdatedonutc(date);
    	zlnfApppiontOrder.setModifier(creator);
    	if("1".equals(status)){
    		zlnfApppiontOrder.setIsremind("0");
    	}else if("2".equals(status)){
    		zlnfApppiontOrder.setState("2");
    	}
    	try {
    		apppiontOrderService.update(zlnfApppiontOrder);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("修改预约价格信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取预约价格信息失败");
		}
        return result;
    }
    
    
    /**
     * 查询预约价格信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrder/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderRequest zlnfApppiontOrderRequest  = convert(data, ZlnfApppiontOrderRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        OptResult result = OptResult.success();
        String roletype = zlnfApppiontOrderRequest.getRoletype();
        String usercode = zlnfApppiontOrderRequest.getCreatorcode();
        ZlnfApppiontOrderExample apppiontOrderExample = new ZlnfApppiontOrderExample();
        ZlnfApppiontOrderExample.Criteria criteria = apppiontOrderExample.createCriteria();
        criteria.andIsdeletedEqualTo("0");
        if(!"5".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)){
        	criteria.andCreatorcodeEqualTo(usercode);
        }
        String ordercode = zlnfApppiontOrderRequest.getOrdercode();
    	if(StringUtils.isNotBlank(ordercode)){
    		criteria.andOrdercodeEqualTo(ordercode);
    	}
        try {
	        List<ZlnfApppiontOrder> list = apppiontOrderService.getResult(apppiontOrderExample);
	        for(int i = 0;i<list.size();i++){
	        	String date1 = sdf.format(list.get(i).getRemaintime());
	        	String date2 = sdf.format(new Date());
					Date dt1 = df.parse(date1);
					Date dt2 = df.parse(date2);
					if (dt1.getTime() < dt2.getTime()) {
						ZlnfApppiontOrder zlnfApppiontOrder = new ZlnfApppiontOrder();
				        zlnfApppiontOrder.setId(list.get(i).getId());
				    	zlnfApppiontOrder.setState("3");
				    	apppiontOrderService.update(zlnfApppiontOrder);
		            } 
	        }
	        ZlnfApppiontOrder zlnfApppiontOrder = new ZlnfApppiontOrder();
	        String state = zlnfApppiontOrderRequest.getState();
	    	if(StringUtils.isNotBlank(state)){
	    		zlnfApppiontOrder.setState(state);
	    	}
	    	if(StringUtils.isNotBlank(ordercode)){
	    		zlnfApppiontOrder.setOrdercode(ordercode);
	    	}
	    	zlnfApppiontOrder.setNumber(zlnfApppiontOrderRequest.getNumber());
	    	zlnfApppiontOrder.setCreatorcode(zlnfApppiontOrderRequest.getCreatorcode());
	        List<ZlnfApppiontOrder> list1 = apppiontOrderService.queryAppiontRownum(zlnfApppiontOrder);
	        for(int i = 0;i<list1.size();i++){
	        	ZlnfFoodInfoExample zlnfFoodInfoExample = new ZlnfFoodInfoExample();
				ZlnfFoodInfoExample.Criteria foodCriteria = zlnfFoodInfoExample.createCriteria();
				foodCriteria.andIsdeletedEqualTo("0");
				foodCriteria.andFoodcodeEqualTo(list1.get(i).getFoodcode());
				List<ZlnfFoodInfo> foodList = foodInfoService.getResult(zlnfFoodInfoExample);
				list1.get(i).setFoodname(foodList.get(0).getFoodnamejc());
				ZlnfGraindepotNewExample graindepotNewExample = new ZlnfGraindepotNewExample();
				ZlnfGraindepotNewExample.Criteria graindepotNewCriteria = graindepotNewExample.createCriteria();
				graindepotNewCriteria.andFoodcodeEqualTo(foodList.get(0).getFoodcode());
				graindepotNewCriteria.andIsdeletedEqualTo("0");
				graindepotNewCriteria.andIsappEqualTo("1");
				graindepotNewCriteria.andFoodkindcodeEqualTo(list1.get(i).getFoodkindcode());
				List<ZlnfGraindepotNew> graindepotNewList1 = graindepotNewService.getResult(graindepotNewExample);
				//判断等级，水分，价格
				//查询是否有粮食种类
				if(graindepotNewList1.size()>0){
					graindepotNewCriteria.andFoodlevelcodeEqualTo(list1.get(i).getFoodlevelcode());
					List<ZlnfGraindepotNew> graindepotNewList2 = graindepotNewService.getResult(graindepotNewExample);
					//查询是否有对应的粮食等级
					if(graindepotNewList2.size()>0){
						graindepotNewCriteria.andWaterEqualTo(Double.valueOf(list1.get(i).getWater()));
						List<ZlnfGraindepotNew> graindepotNewList3 = graindepotNewService.getResult(graindepotNewExample);
						//查询是否有对应的水分
						if(graindepotNewList3.size()>0){
							list1.get(i).setNowprice(String.valueOf(graindepotNewList3.get(0).getPrice()));
							list1.get(i).setNowfoodlevel(String.valueOf(graindepotNewList3.get(0).getFoodlevel()));
							list1.get(i).setNowwater(String.valueOf(graindepotNewList3.get(0).getWater()));
						}else{
							//如果没有水分，显示水分最接近的
							list1.get(i).setNowprice(String.valueOf(graindepotNewList2.get(0).getPrice()));
							list1.get(i).setNowfoodlevel(String.valueOf(graindepotNewList2.get(0).getFoodlevel()));
							Double n = (double) 0;
							int m = 0;
							for(int j = 0;j<graindepotNewList2.size();j++){
								Double waterAbs = Math.abs(Double.valueOf(list1.get(i).getWater())-graindepotNewList2.get(j).getWater());
								if(j==0){
									n = waterAbs;
									m = j;
								}else{
									if(waterAbs<n){
										n = waterAbs;
										m = j;
									}
								}
							}
							list1.get(i).setNowwater(String.valueOf(graindepotNewList2.get(m).getWater()));
						}
					}else{
						//如果没有对应的等级则显示价格最近的
						Double n = (double) 0;
						int m = 0;
						for(int j = 0;j<graindepotNewList1.size();j++){
							Double priceAbs = Math.abs(Double.valueOf(list1.get(i).getProductname())-graindepotNewList1.get(j).getPrice());
							if(j==0){
								n = priceAbs;
								m = j;
							}else{
								if(priceAbs<n){
									n = priceAbs;
									m = j;
								}
							}
						}
						list1.get(i).setNowprice(String.valueOf(graindepotNewList1.get(m).getPrice()));
						list1.get(i).setNowfoodlevel(String.valueOf(graindepotNewList1.get(m).getFoodlevel()));
						list1.get(i).setNowwater(String.valueOf(graindepotNewList1.get(m).getWater()));
					}
				}
				if("5".equals(roletype) || "1".equals(roletype) || "2".equals(roletype)){
    				if(usercode.equals(list.get(i).getCreatorcode())){
    					list.get(i).setIsmyorder("1");
    				}else{
    					list.get(i).setIsmyorder("0");
    				}
    			}
	        }
    		result.setData(list1);
        	result.setReturnCode("0000");
        	result.setMessage("获取预约价格信息列表成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取预约价格信息列表失败");
		}
        return result;
    }
    
    /**
     * 删除预约价格信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrder/delete"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderDelete(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderRequest zlnfApppiontOrderRequest  = convert(data, ZlnfApppiontOrderRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
    	ZlnfApppiontOrderExample apppiontOrderExample = new ZlnfApppiontOrderExample();
        apppiontOrderExample.createCriteria().andOrdercodeEqualTo(zlnfApppiontOrderRequest.getOrdercode());
        List<ZlnfApppiontOrder> list = apppiontOrderService.getResult(apppiontOrderExample);
        ZlnfApppiontOrder zlnfApppiontOrder = new ZlnfApppiontOrder();
        zlnfApppiontOrder.setId(list.get(0).getId());
        zlnfApppiontOrder.setIsdeleted("1");
        try {
            apppiontOrderService.update(zlnfApppiontOrder);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("删除预约价格信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获删除预约价格信息失败");
		}
        return result;
    }
}
