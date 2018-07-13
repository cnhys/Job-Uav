package com.vt.fengci.zlnf.controller;

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
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfGraindepotNew;
import com.vt.fencing.model.ZlnfGraindepotNewExample;
import com.vt.fencing.model.ZlnfStorage;
import com.vt.fencing.request.ZlnfStorageRequest;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IGraindepotNewService;
import com.vt.fengci.zlnf.service.IStorageService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class StorageController extends BaseGatewayController {

	private static final long serialVersionUID = -4507312752944345350L;

	/**
     * member service
     */
    
    @Autowired
    private IStorageService storageService;
    
    @Autowired
    private IGraindepotNewService graindepotNewService;    
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    /**
     * 添加出库信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/storage/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfStorageRequest zlnfStorageRequest  = convert(data, ZlnfStorageRequest.class);
        // 2. 业务检查
        if (zlnfStorageRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        try {
        	ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        	ZlnfFcustofomerinExample.Criteria exampleCriteria = example.createCriteria();
        	exampleCriteria.andUsercodeEqualTo(zlnfStorageRequest.getUserid());
        	exampleCriteria.andIsdeletedEqualTo("0");
        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
        	if(DesUtil.desCrypto(zlnfStorageRequest.getPaypassword()).equals(list.get(0).getPaypassword())){
        		Date date = new Date();
    	        ZlnfStorage zlnfStorage = new ZlnfStorage();
    	        zlnfStorage.setCreatedate(date);
    	        zlnfStorage.setClname(zlnfStorageRequest.getClname());
    	        zlnfStorage.setUserid(zlnfStorageRequest.getUserid());
    	        zlnfStorage.setPhone(zlnfStorageRequest.getPhone());
    	        zlnfStorage.setIdnumber(zlnfStorageRequest.getIdnumber());
    	        zlnfStorage.setLibname(zlnfStorageRequest.getLibname());
    	        zlnfStorage.setLibid(zlnfStorageRequest.getLibid());
    	        zlnfStorage.setVariety(zlnfStorageRequest.getVariety());
    	        zlnfStorage.setHtlevel(zlnfStorageRequest.getHtlevel());
    	        zlnfStorage.setGlshuifen(zlnfStorageRequest.getGlshuifen());
    	        zlnfStorage.setCnt(zlnfStorageRequest.getCnt());
    	        zlnfStorage.setVarietycode(zlnfStorageRequest.getVarietycode());
    	        zlnfStorage.setHtlevelcode(zlnfStorageRequest.getHtlevelcode());
    	        ZlnfGraindepotNewExample graindepotNewExample = new ZlnfGraindepotNewExample();
    			ZlnfGraindepotNewExample.Criteria criteria = graindepotNewExample.createCriteria();
    			/*criteria.andCreatordeptnameEqualTo(zlnfStorageRequest.getLibname());*/
    			criteria.andFoodcodeEqualTo(zlnfStorageRequest.getLibid());
    			criteria.andFoodkindEqualTo(zlnfStorageRequest.getVariety());
//    			criteria.andFoodkindEqualTo("玉米");
    			criteria.andFoodlevelEqualTo(zlnfStorageRequest.getHtlevel());
    			criteria.andWaterEqualTo(Double.valueOf(zlnfStorageRequest.getGlshuifen()));
    			List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService.getResult(graindepotNewExample);
    			zlnfStorage.setActionstate("1");
    			if(graindepotNewList.size()>0){
    				Double price = graindepotNewList.get(0).getPrice();
    				if(price>0){
    					zlnfStorage.setPrice(String.valueOf(graindepotNewList.get(0).getPrice()));
    					result.setSuccess(true);
    					storageService.create(zlnfStorage);
    					result.setReturnCode("0000");
    					result.setMessage("添加出库信息成功");
    	        	}else{
    	        		result.setSuccess(false);
    		        	result.setReturnCode("0001");
    		        	result.setMessage("该品种暂无价格，无法出售");
    	        	}
    			}else{
    				result.setSuccess(false);
    	        	result.setReturnCode("0001");
    	        	result.setMessage("该品种暂无价格，无法出售");
    			}
        	}else{
        		result.setSuccess(false);
            	result.setReturnCode("0002");
            	result.setMessage("支付密码错误");
        	}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加出库信息失败");
		}
        return result;
    }

    
    /**
     * 获取粮库信息列表
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/storage/queryBycode"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult storageQueryBycode(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfStorageRequest zlnfStorageRequest  = convert(data, ZlnfStorageRequest.class);
        // 2. 业务检查
        if (zlnfStorageRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
    	try {
    		ZlnfStorage zlnfStorage = new ZlnfStorage();
    		zlnfStorage.setIdnumber(zlnfStorageRequest.getIdnumber());
    		zlnfStorage.setLibid(zlnfStorageRequest.getLibid());
    		zlnfStorage.setNumber(zlnfStorageRequest.getNumber());
    		//获取分页信息
    		List<ZlnfStorage> storageList = storageService.queryRownum(zlnfStorage);
    		//获取余额数量
    		List<ZlnfStorage> storageList0 = storageService.queryRownumRest(zlnfStorage);
    		//获取品种、等级、水分
    		List<ZlnfStorage> storageList1 = storageService.queryStr(zlnfStorage);
    		
    	/*	List<String> list1 = new ArrayList<>();
    		for(int i = 0;i<storageList.size();i++){
    			if(i==0){
    				list1.add(storageList.get(i).getVariety());
    			}else{
    				Boolean bo = true;
    				for(int j = 0;j<list1.size();j++){
    					if(list1.get(j).equals(storageList.get(i).getVariety())){
    						bo = false;
    						break;
    					}
    				}
    				if(bo){
    					list1.add(storageList.get(i).getVariety());	
    				}
    			}
    		}
    		for(int i = 0;i<list1.size();i++){
    			Double n = (double) 0;
    			for(int j = storageList.size()-1;j>=0;j--){
    				if(list1.get(i).equals(storageList.get(j).getVariety())){
    					if("0".equals(storageList.get(j).getActionstate())){
    						n = n + Double.valueOf(storageList.get(j).getCnt());
    						storageList.get(j).setSurplus(n);
    					}else{
    						n = n - Double.valueOf(storageList.get(j).getCnt());
    						storageList.get(j).setSurplus(n);
    					}
    				}
    			}
    		}
    		for(int i = 0;i<list1.size();i++){
    			Double n = (double) 0;
    			for(int j=0;j<storageList0.size();j++){
    				if(list1.get(i).equals(storageList0.get(j).getVariety())){
    					if("0".equals(storageList0.get(j).getActionstate())){
    						n = n + Double.valueOf(storageList0.get(j).getCnt());
    						//storageList0.get(j).setSurplus(n);
    					}else{
    						n = n - Double.valueOf(storageList0.get(j).getCnt());
    						//storageList0.get(j).setSurplus(n);
    					}
    				}
    			}
    			for(int j = storageList.size()-1;j>=0;j--){
    				if(list1.get(i).equals(storageList.get(j).getVariety())){
    					storageList.get(j).setSurplus(storageList.get(j).getSurplus()+n);
    				}
    			}
    		}*/
    		
    		for(int i = 0;i<storageList1.size();i++){
    			Double n = (double) 0;
    			for(int j = storageList.size()-1;j>=0;j--){
    				if(storageList1.get(i).getVariety().equals(storageList.get(j).getVariety())
    						&&storageList1.get(i).getGlshuifen().equals(storageList.get(j).getGlshuifen())
    						&&storageList1.get(i).getHtlevel().equals(storageList.get(j).getHtlevel())){
    						if("0".equals(storageList.get(j).getActionstate())){
        						n = n + Double.valueOf(storageList.get(j).getCnt());
        						storageList.get(j).setSurplus(n);
        					}else{
        						n = n - Double.valueOf(storageList.get(j).getCnt());
        						storageList.get(j).setSurplus(n);
        					}
    				}
    			}
    		}
    		if(zlnfStorageRequest.getNumber()!=null&&zlnfStorageRequest.getNumber()!=""&&Integer.valueOf(zlnfStorageRequest.getNumber())>0){
    			for(int i=0;i<storageList0.size();i++){
    				for(int j=0;j<storageList.size();j++){
    					if(storageList0.get(i).getVariety().equals(storageList.get(j).getVariety())
    						&&storageList0.get(i).getGlshuifen().equals(storageList.get(j).getGlshuifen())
    						&&storageList0.get(i).getHtlevel().equals(storageList.get(j).getHtlevel())){
    						storageList.get(j).setSurplus(Double.valueOf(storageList0.get(i).getCnt())+storageList.get(j).getSurplus());
    					}
    				}
    			}
    			
    		}
    		
    		result.setSuccess(true);
    		result.setData(storageList);
        	result.setReturnCode("0000");
        	result.setMessage("获取出入库信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取出入库信息失败");
		}
        return result;
    }
    
    /**
     * 获取出入库信息列表
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/storage/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult storageQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfStorageRequest zlnfStorageRequest  = convert(data, ZlnfStorageRequest.class);
        // 2. 业务检查
        if (zlnfStorageRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfStorage record = new ZlnfStorage();
        record.setIdnumber(zlnfStorageRequest.getIdnumber());
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		List<ZlnfStorage> storageList1 = storageService.queryVariety(record);
    		List<ZlnfStorage> storageList = storageService.queryList(record);
    		for(int i = 0;i<storageList.size();i++){
    			ZlnfGraindepotNewExample graindepotNewExample = new ZlnfGraindepotNewExample();
    			ZlnfGraindepotNewExample.Criteria criteria = graindepotNewExample.createCriteria();
    			//criteria.andCreatordeptnameEqualTo(storageList.get(i).getLibname());
    			criteria.andFoodcodeEqualTo(storageList.get(i).getLibid());
    			criteria.andFoodkindEqualTo(storageList.get(i).getVariety());
    			criteria.andFoodlevelEqualTo(storageList.get(i).getHtlevel());
    			criteria.andWaterEqualTo(Double.valueOf(storageList.get(i).getGlshuifen()));
    			criteria.andIsdeletedEqualTo("0");
    			List<ZlnfGraindepotNew> graindepotNewList = graindepotNewService.getResult(graindepotNewExample);
    			if(graindepotNewList.size()>0){
    				storageList.get(i).setTodayprice(String.valueOf(graindepotNewList.get(0).getPrice()));
    				Double value = graindepotNewList.get(0).getPrice() * Double.valueOf(storageList.get(i).getCnt());
    				storageList.get(i).setValue(String.valueOf(value));
    			}
    		}
    		Double sumvalue = (double) 0;
    		for(int i = 0;i<storageList.size();i++){
    			if(storageList.get(i).getValue()!=null){
    				sumvalue = sumvalue + Double.valueOf(storageList.get(i).getValue());
    			}
    		}
    		List<List<ZlnfStorage>> storageLists = new ArrayList<>();
    		List<String> listStr = new ArrayList<>();
    		for(int i = 0;i<storageList.size();i++){
    			Boolean bo = true;
    			if(i==0){
    				listStr.add(storageList.get(i).getLibname());
    			}else{
    				for(int j = 0;j<listStr.size();j++){
    					if(storageList.get(i).getLibname().equals(listStr.get(j))){
    						bo = false;
    						break;
    					}
    				}
    				if(bo){
    					listStr.add(storageList.get(i).getLibname());
					}
    			}
    		}
    		for(int i = 0;i<listStr.size();i++){
    			List<ZlnfStorage> list = new ArrayList<>();
    			for(int j = 0;j<storageList.size();j++){
    				if(listStr.get(i).equals(storageList.get(j).getLibname())){
    					list.add(storageList.get(j));
    				}
    			}
    			storageLists.add(list);
    		}
    		map.put("storageLists", storageLists);
    		map.put("storageList1", storageList1);
    		map.put("sumvalue", sumvalue);
    		result.setSuccess(true);
    		result.setData(map);
        	result.setReturnCode("0000");
        	result.setMessage("获取出入库信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取出入库信息失败");
		}
        return result;
    }
    
}
