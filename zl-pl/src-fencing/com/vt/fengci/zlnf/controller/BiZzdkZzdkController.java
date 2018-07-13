package com.vt.fengci.zlnf.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.fencing.model.ZlnfBiZzdkZzdk;
import com.vt.fencing.model.ZlnfBiZzdkZzdkExample;
import com.vt.fencing.model.ZlnfBiZzdkZzdkWithBLOBs;
import com.vt.fencing.request.ZlnfBiZzdkZzdkRequest;
import com.vt.fengci.zlnf.service.IBiZzdkZzdkService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class BiZzdkZzdkController extends BaseGatewayController {

	private static final long serialVersionUID = 3215911748097346413L;
	
	/**
     * member service
     */
    @Autowired
    private IBiZzdkZzdkService biZzdkZzdkService;
    
    
    /**
     * 查询银行产品
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biZzdkZzdk/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biZzdkZzdkQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfBiZzdkZzdkRequest zlnfBiZzdkZzdkRequest  = convert(data, ZlnfBiZzdkZzdkRequest.class);
        // 2. 业务检查
        if (zlnfBiZzdkZzdkRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        ZlnfBiZzdkZzdkExample zlnfBiZzdkZzdkExample = new ZlnfBiZzdkZzdkExample();
        ZlnfBiZzdkZzdkExample.Criteria criteria = zlnfBiZzdkZzdkExample.createCriteria();
        criteria.andDkzlEqualTo(zlnfBiZzdkZzdkRequest.getDkzl());
        ZlnfBiZzdkZzdkWithBLOBs biZzdkZzdkWithBLOBs = new ZlnfBiZzdkZzdkWithBLOBs();
    	biZzdkZzdkWithBLOBs.setDkzl(zlnfBiZzdkZzdkRequest.getDkzl());
        List<ZlnfBiZzdkZzdkWithBLOBs> biZzdkZzdkList = biZzdkZzdkService.queryList(biZzdkZzdkWithBLOBs);
        try {
        	if(biZzdkZzdkList.size()>0){
        		result.setSuccess(true);
            	result.setData(biZzdkZzdkList);
        		result.setReturnCode("0000");
        		result.setMessage("查询贷款产品成功");
        	}else{
        		result.setSuccess(false);
        		result.setReturnCode("0001");
        		result.setMessage("没有该产品");
        	}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("查询贷款产品失败");
		}
        return result;
    }

    /**
     * 查询银行产品信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biZzdkZzdk/queryByBh"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryByBh(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiZzdkZzdkRequest zlnfBiZzdkZzdkRequest  = convert(data, ZlnfBiZzdkZzdkRequest.class);
    	// 2. 业务检查
    	if (zlnfBiZzdkZzdkRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfBiZzdkZzdkExample zlnfBiZzdkZzdkExample = new ZlnfBiZzdkZzdkExample();
    	ZlnfBiZzdkZzdkExample.Criteria criteria = zlnfBiZzdkZzdkExample.createCriteria();
    	criteria.andCpbhEqualTo(zlnfBiZzdkZzdkRequest.getCpbh());
    	ZlnfBiZzdkZzdkWithBLOBs biZzdkZzdkWithBLOBs = new ZlnfBiZzdkZzdkWithBLOBs();
    	biZzdkZzdkWithBLOBs.setCpbh(zlnfBiZzdkZzdkRequest.getCpbh());
    	try {
	        List<ZlnfBiZzdkZzdkWithBLOBs> biZzdkZzdkList = biZzdkZzdkService.queryList(biZzdkZzdkWithBLOBs);
	        for(int i = 0;i<biZzdkZzdkList.size();i++){
	        	biZzdkZzdkList.get(i).setEd(isNull(biZzdkZzdkList.get(i).getEd()));
	        	biZzdkZzdkList.get(i).setQx(isNull(biZzdkZzdkList.get(i).getQx()));
	        }
    		if(biZzdkZzdkList.size()>0){
    			result.setSuccess(true);
    			result.setData(biZzdkZzdkList.get(0));
    			result.setReturnCode("0000");
    			result.setMessage("查询贷款产品成功");
    		}else{
    			result.setSuccess(false);
    			result.setReturnCode("0001");
    			result.setMessage("没有该产品");
    		}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询贷款产品失败");
    	}
    	return result;
    }

    /**
     * 查询银行产品H5页面数据
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biZzdkZzdk/context"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biZzdkZzdkContext(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiZzdkZzdkRequest zlnfBiZzdkZzdkRequest  = convert(data, ZlnfBiZzdkZzdkRequest.class);
    	// 2. 业务检查
    	if (zlnfBiZzdkZzdkRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfBiZzdkZzdkExample zlnfBiZzdkZzdkExample = new ZlnfBiZzdkZzdkExample();
    	ZlnfBiZzdkZzdkExample.Criteria criteria = zlnfBiZzdkZzdkExample.createCriteria();
    	criteria.andCpbhEqualTo(zlnfBiZzdkZzdkRequest.getCpbh());
    	ZlnfBiZzdkZzdkWithBLOBs biZzdkZzdkWithBLOBs = new ZlnfBiZzdkZzdkWithBLOBs();
    	biZzdkZzdkWithBLOBs.setCpbh(zlnfBiZzdkZzdkRequest.getCpbh());
    	try {
    		List<ZlnfBiZzdkZzdkWithBLOBs> biZzdkZzdkList = biZzdkZzdkService.queryList(biZzdkZzdkWithBLOBs);
    		if(biZzdkZzdkList.size()>0){
    			result.setSuccess(true);
    			if("1".equals(zlnfBiZzdkZzdkRequest.getType())){
    				result.setData(biZzdkZzdkList.get(0).getPtxy());
    			}else if("2".equals(zlnfBiZzdkZzdkRequest.getType())){
    				result.setData(biZzdkZzdkList.get(0).getJkbz());
    			}else{
    				result.setData(biZzdkZzdkList.get(0).getCpsm());
    			}
    			result.setReturnCode("0000");
    			result.setMessage("查询数据成功");
    		}else{
    			result.setSuccess(false);
    			result.setReturnCode("0001");
    			result.setMessage("查询没有该商品");
    		}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询数据失败");
    	}
    	return result;
    }
    
    public String isNull(String data) {
    	if(data==null){
    		data = "";
    	}
    	return data;
    }
}
