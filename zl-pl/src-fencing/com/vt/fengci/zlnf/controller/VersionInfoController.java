package com.vt.fengci.zlnf.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.fencing.model.ZlnfVersionInfo;
import com.vt.fencing.request.ZlnfVersionInfoRequest;
import com.vt.fengci.zlnf.service.IVersionInfoService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class VersionInfoController extends BaseGatewayController {

	private static final long serialVersionUID = 4183703595561059207L;
	/**
     * member service
     */
    
    @Autowired
    private IVersionInfoService versionInfoService;
    
    /**
     * 获取最新版本信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/versionInfo/querynew"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult versionInfoQuerynew(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfVersionInfoRequest zlnfVersionInfoRequest  = convert(data, ZlnfVersionInfoRequest.class);
        // 2. 业务检查
        if (zlnfVersionInfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String type=zlnfVersionInfoRequest.getType();
        ZlnfVersionInfo  zlnfVersionInfo=new ZlnfVersionInfo();
    
        List<ZlnfVersionInfo> list=new ArrayList<ZlnfVersionInfo>();
    	try {
    		if(type!=null&&type!=""){
    		    zlnfVersionInfo.setApptype(type);
    			list = versionInfoService.querynew1(zlnfVersionInfo);
    		}else{
    		    zlnfVersionInfo.setApptype("1");
    			list = versionInfoService.querynew1(zlnfVersionInfo);
    		}
    		
    		if(zlnfVersionInfoRequest.getVersionnum().equals(list.get(0).getVersionnum())){
    			result.setSuccess(false);
            	result.setReturnCode("0000");
            	result.setMessage("已是最新版本");
    		}else{
    			result.setSuccess(true);
    			result.setData(list.get(0));
    			result.setReturnCode("0000");
    			result.setMessage("获取版本信息成功");
    		}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取版本信息失败");
		}
        return result;
    }

    /**
     * 获取最新版本号
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/versionInfo/querynewnum"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult versionInfoQuerynewnum(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfVersionInfoRequest zlnfVersionInfoRequest  = convert(data, ZlnfVersionInfoRequest.class);
    	// 2. 业务检查
    	if (zlnfVersionInfoRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfVersionInfo  zlnfVersionInfo=new ZlnfVersionInfo();
    	zlnfVersionInfo.setApptype("1");
    	try {
    		List<ZlnfVersionInfo> list = versionInfoService.querynew1(zlnfVersionInfo);
    		result.setSuccess(true);
			result.setData(list.get(0));
			result.setReturnCode("0000");
			result.setMessage("获取版本信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取版本信息失败");
    	}
    	return result;
    }
    
}
