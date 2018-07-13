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
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfFieldInfo;
import com.vt.fencing.model.ZlnfFieldInfoExample;
import com.vt.fencing.request.ZlnfFieldInfoRequest;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IFieldInfoService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FieldInfoController extends BaseGatewayController {

	private static final long serialVersionUID = 4193011716881568078L;
	/**
     * member service
     */
    
    @Autowired
    private IFieldInfoService fieldInfoService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    /**
     * 合作社添加田地信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/fieldInfo/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFieldInfoRequest zlnfFieldInfoRequest  = convert(data, ZlnfFieldInfoRequest.class);
        // 2. 业务检查
        if (zlnfFieldInfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String fieldcode =Uuid32.getUUID32();
    	Date date = new Date();
    	String fieldname = zlnfFieldInfoRequest.getFieldname();
    	String userName = zlnfFieldInfoRequest.getUsername();
    	String usercode = zlnfFieldInfoRequest.getUsercode();
    	String crops = zlnfFieldInfoRequest.getCrops();
    	String cropscode = zlnfFieldInfoRequest.getCropscode();
    	String address = zlnfFieldInfoRequest.getAddress();
    	//添加田地信息
    	ZlnfFieldInfo zlnfFieldInfo = new ZlnfFieldInfo();
    	zlnfFieldInfo.setFieldcode(fieldcode);
    	zlnfFieldInfo.setFieldname(fieldname);
    	zlnfFieldInfo.setCrops(crops);
    	zlnfFieldInfo.setCropscode(cropscode);
    	zlnfFieldInfo.setAddress(address);
    	zlnfFieldInfo.setLng(zlnfFieldInfoRequest.getLng());
    	zlnfFieldInfo.setLat(zlnfFieldInfoRequest.getLat());
    	zlnfFieldInfo.setCoopcode(zlnfFieldInfoRequest.getCoopcode());
    	zlnfFieldInfo.setPath(zlnfFieldInfoRequest.getPath());
    	zlnfFieldInfo.setFieldacreage(zlnfFieldInfoRequest.getFieldacreage());
    	zlnfFieldInfo.setRowspacing(zlnfFieldInfoRequest.getRowspacing());
    	zlnfFieldInfo.setServiceunit(zlnfFieldInfoRequest.getServiceunit());
    	zlnfFieldInfo.setCreatedonutc(date);
    	zlnfFieldInfo.setCreator(userName);
    	zlnfFieldInfo.setCreatorcode(usercode);
    	String path = "http://api.map.baidu.com/staticimage/v2?ak=HHoloqlMKfkGlmWjaAwDSE4M1RntAOvz&width=400&height=200&markers="+zlnfFieldInfoRequest.getLng()+","+zlnfFieldInfoRequest.getLat()+"&zoom=15&markerStyles=m,,0xff0000";
    	zlnfFieldInfo.setPath(path);
    	try {
    		result.setSuccess(true);
    		fieldInfoService.create(zlnfFieldInfo);
        	result.setReturnCode("0000");
        	result.setMessage("添加田地成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加田地失败");
		}
        return result;
    }

    
    /**
     * 合作社修改田地信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/fieldInfo/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoUpdate(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFieldInfoRequest zlnfFieldInfoRequest  = convert(data, ZlnfFieldInfoRequest.class);
    	// 2. 业务检查
    	if (zlnfFieldInfoRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	Date date = new Date();
    	ZlnfFieldInfoExample zlnfFieldInfoExample = new ZlnfFieldInfoExample();
    	ZlnfFieldInfoExample.Criteria criteria = zlnfFieldInfoExample.createCriteria();
    	criteria.andIsdeletedEqualTo("0");
    	criteria.andCoopcodeEqualTo(zlnfFieldInfoRequest.getCoopcode());
    	criteria.andFieldcodeEqualTo(zlnfFieldInfoRequest.getFieldcode());
    	List<ZlnfFieldInfo> list = fieldInfoService.getResult(zlnfFieldInfoExample);
    	try {
	    	if(list.size()>0){
	    		String userName = zlnfFieldInfoRequest.getUsername();
	    		//修改田地信息
	    		ZlnfFieldInfo zlnfFieldInfo = new ZlnfFieldInfo();
	    		zlnfFieldInfo.setId(list.get(0).getId());
	    		String fieldname = zlnfFieldInfoRequest.getFieldname();
	    		if(StringUtils.isNotBlank(fieldname)){
	    			zlnfFieldInfo.setFieldname(fieldname);
	    		}
	    		String crops = zlnfFieldInfoRequest.getCrops();
	    		if(StringUtils.isNotBlank(crops)){
	    			zlnfFieldInfo.setCrops(crops);
	    		}
	    		String cropscode = zlnfFieldInfoRequest.getCropscode();
	    		if(StringUtils.isNotBlank(cropscode)){
	    			zlnfFieldInfo.setCropscode(cropscode);
	    		}
	    		String serviceunit = zlnfFieldInfoRequest.getServiceunit();
	    		if(StringUtils.isNotBlank(serviceunit)){
	    			zlnfFieldInfo.setServiceunit(serviceunit);
	    			
	    		}
	    		String address = zlnfFieldInfoRequest.getAddress();
	    		if(StringUtils.isNotBlank(address)){
	    			zlnfFieldInfo.setAddress(address);
	    		}
	    		String lng = zlnfFieldInfoRequest.getLng();
	    		if(StringUtils.isNotBlank(lng)){
	    			zlnfFieldInfo.setLng(lng);
	    		}
	    		String lat = zlnfFieldInfoRequest.getLat();
	    		if(StringUtils.isNotBlank(lat)){
	    			zlnfFieldInfo.setLat(lat);
	    		}
	    		String fieldacreage = zlnfFieldInfoRequest.getFieldacreage();
	    		if(StringUtils.isNotBlank(fieldacreage)){
	    			zlnfFieldInfo.setFieldacreage(fieldacreage);
	    		}
	    		String rowspacing = zlnfFieldInfoRequest.getRowspacing();
	    		if(StringUtils.isNotBlank(rowspacing)){
	    			zlnfFieldInfo.setRowspacing(rowspacing);
	    		}
	    		String isdeleted = zlnfFieldInfoRequest.getIsdeleted();
	    		if(StringUtils.isNotBlank(isdeleted)){
	    			zlnfFieldInfo.setIsdeleted(isdeleted);
	    		}
	    		if(zlnfFieldInfoRequest.getLng()!=null && zlnfFieldInfoRequest.getLat()!=null){
	    			String path = "http://api.map.baidu.com/staticimage/v2?ak=HHoloqlMKfkGlmWjaAwDSE4M1RntAOvz&width=400&height=200&markers="+zlnfFieldInfoRequest.getLng()+","+zlnfFieldInfoRequest.getLat()+"&zoom=14&markerStyles=m,,0xff0000";
	    	    	zlnfFieldInfo.setPath(path);
	    		}
	    		zlnfFieldInfo.setUpdatedonutc(date);
	    		zlnfFieldInfo.setModifier(userName);
	    		result.setSuccess(true);
	    		fieldInfoService.update(zlnfFieldInfo);
	    		result.setReturnCode("0000");
	    		result.setMessage("修改田地成功");
	    	}else{
	    		result.setSuccess(false);
	    		result.setReturnCode("0002");
	    		result.setMessage("田地不存在");
	    	}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("修改田地失败");
    	}
    	return result;
    }
    
    /**
     * 获取合作社田地信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/fieldInfo/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult fieldInfoQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFieldInfoRequest zlnfFieldInfoRequest  = convert(data, ZlnfFieldInfoRequest.class);
        // 2. 业务检查
        if (zlnfFieldInfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfFieldInfoExample fieldInfoExample = new ZlnfFieldInfoExample();
        ZlnfFieldInfoExample.Criteria criteria = fieldInfoExample.createCriteria();
        criteria.andCoopcodeEqualTo(zlnfFieldInfoRequest.getCoopcode());
        criteria.andIsdeletedEqualTo("0");
        String fieldcode = zlnfFieldInfoRequest.getFieldcode();
        String usercode = zlnfFieldInfoRequest.getUsercode();
        String roletype = zlnfFieldInfoRequest.getRoletype();
        if(StringUtils.isNotBlank(fieldcode)){
        	criteria.andFieldcodeEqualTo(fieldcode);
    	}
        if(!"5".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)){
        	if(StringUtils.isNotBlank(usercode)){
        		criteria.andCreatorcodeEqualTo(usercode);
        	}
        }
    	try {
    		List<ZlnfFieldInfo> list = fieldInfoService.getResult(fieldInfoExample);
    		List<Map<String, Object>> returnList = new ArrayList<>();
    		List<String> codeList = new ArrayList<>();
    		for(int i = 0;i<list.size();i++){
    			String creatorcode = list.get(i).getCreatorcode();
    			if(!creatorcode.equals(usercode)){
    				if(codeList.size()==0){
    					codeList.add(creatorcode);
    				}else{
    					Boolean bo = true;
    					for(int j = 0;j<codeList.size();j++){
    						if(creatorcode.equals(codeList.get(j))){
    							bo = false;
    							break;
    						}
    					}
    					if(bo){
    						codeList.add(creatorcode);
    					}
    				}
    			}
    		}
    		Map<String, Object> map1 = new HashMap<String, Object>();
    		ZlnfFcustofomerinExample fcustofomerinExample1 = new ZlnfFcustofomerinExample();
    		fcustofomerinExample1.createCriteria().andUsercodeEqualTo(usercode);
    		List<ZlnfFcustofomerin> custList1 = customerInfoService.getResult(fcustofomerinExample1); 
    		map1.put("username", custList1.get(0).getUsername());
			List<ZlnfFieldInfo> fieldInfoList1 = new ArrayList<>();
    		for(int i = 0;i<list.size();i++){
    			if(usercode.equals(list.get(i).getCreatorcode())){
    				fieldInfoList1.add(list.get(i));
    			}
    		}
    		map1.put("fieldInfoList", fieldInfoList1);
    		returnList.add(map1);
    		for(int i = 0;i<codeList.size();i++){
    			String creatorcode = codeList.get(i);
    			Map<String, Object> map2 = new HashMap<String, Object>();
    			ZlnfFcustofomerinExample fcustofomerinExample2 = new ZlnfFcustofomerinExample();
    			fcustofomerinExample2.createCriteria().andUsercodeEqualTo(codeList.get(i));
        		List<ZlnfFcustofomerin> custList2 = customerInfoService.getResult(fcustofomerinExample2); 
        		map2.put("username", custList2.get(0).getUsername());
    			List<ZlnfFieldInfo> fieldInfoList2 = new ArrayList<>();
    			for(int j = 0;j<list.size();j++){
    				if(creatorcode.equals(list.get(j).getCreatorcode())){
    					fieldInfoList2.add(list.get(j));
    				}
    			}
    			map2.put("fieldInfoList", fieldInfoList2);
    			returnList.add(map2);
    		}
    		result.setSuccess(true);
    		result.setData(returnList);
        	result.setReturnCode("0000");
        	result.setMessage("获取田地信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取田地信息失败");
		}
        return result;
    }
    
}
