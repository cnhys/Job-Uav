package com.vt.fengci.zlnf.controller;

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
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.request.DmXzqhRequest;
import com.vt.fencing.request.ZlnfDictInfoRequest;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.IDmXzqhService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class DictInfoController extends BaseGatewayController {

	private static final long serialVersionUID = -5010734435933294320L;
	/**
     * member service
     */
    
    @Autowired
    private IDictInfoService dictInfoService;

    @Autowired
    private IDmXzqhService dmXzqhService;
    
    /**
     * 获取字典表父类
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/dictInfo/queryParent"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult dictInfoQueryParent(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfDictInfoRequest zlnfDictInfoRequest  = convert(data, ZlnfDictInfoRequest.class);
        // 2. 业务检查
        if (zlnfDictInfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
        ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
        criteria.andDictlevelEqualTo("0");
        criteria.andTypeEqualTo(zlnfDictInfoRequest.getType());
    	try {
    		List<ZlnfDictInfo> list = dictInfoService.getResult(dictInfoExample);
    		result.setSuccess(true);
    		result.setData(list);
        	result.setReturnCode("0000");
        	result.setMessage("获取字典表父类信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("取字典表父类信息失败");
		}
        return result;
    }
    
    /**
     * 获取字典表子类
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/dictInfo/queryChildren"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult dictInfoQueryChildren(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfDictInfoRequest zlnfDictInfoRequest  = convert(data, ZlnfDictInfoRequest.class);
        // 2. 业务检查
        if (zlnfDictInfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
        ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
        criteria.andDictlevelEqualTo("1");
        criteria.andTypeEqualTo(zlnfDictInfoRequest.getType());
        criteria.andParentcodeEqualTo(zlnfDictInfoRequest.getParentcode());
    	try {
    		List<ZlnfDictInfo> list = dictInfoService.getResult(dictInfoExample);
    		result.setSuccess(true);
    		result.setData(list);
        	result.setReturnCode("0000");
        	result.setMessage("获取字典表子类信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("获取字典表子类信息失败");
		}
        return result;
    }

    
    /**
     * 获取市
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/DmXzqh/queryCity"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryCity(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	DmXzqhRequest dmXzqhRequest  = convert(data, DmXzqhRequest.class);
    	// 2. 业务检查
    	if (dmXzqhRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	DmXzqh dmXzqh = new DmXzqh();
    	String mc = dmXzqhRequest.getMc();
    	if(StringUtils.isNotBlank(mc)){
    		dmXzqh.setMc(mc);
    	}
    	try {
    		List<Map<String, Object>> list = dmXzqhService.queryCity(dmXzqh);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("获取市成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取市失败");
    	}
    	return result;
    }

    /**
     * 获取省
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/DmXzqh/selectProvice"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult selectProvice(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	DmXzqhRequest dmXzqhRequest  = convert(data, DmXzqhRequest.class);
    	// 2. 业务检查
    	if (dmXzqhRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	DmXzqhExample dmXzqhExample = new DmXzqhExample();
    	DmXzqhExample.Criteria dmXzqhCriteria = dmXzqhExample.createCriteria();
    	dmXzqhCriteria.andScbsEqualTo(Long.valueOf("1"));
    	try {
    		List<DmXzqh> list = dmXzqhService.getResult(dmXzqhExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("获取省成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取省失败");
    	}
    	return result;
    }

    /**
     * 获取市
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/DmXzqh/selectCity"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult selectCity(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	DmXzqhRequest dmXzqhRequest  = convert(data, DmXzqhRequest.class);
    	// 2. 业务检查
    	if (dmXzqhRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	DmXzqhExample dmXzqhExample = new DmXzqhExample();
    	DmXzqhExample.Criteria dmXzqhCriteria = dmXzqhExample.createCriteria();
    	dmXzqhCriteria.andScbsEqualTo(Long.valueOf("2"));
    	dmXzqhCriteria.andFbhEqualTo(dmXzqhRequest.getFbh());
    	try {
    		List<DmXzqh> list = dmXzqhService.getResult(dmXzqhExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("获取市成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(true);
    		result.setReturnCode("1111");
    		result.setMessage("获取市失败");
    	}
    	return result;
    }
    
    
    
}
