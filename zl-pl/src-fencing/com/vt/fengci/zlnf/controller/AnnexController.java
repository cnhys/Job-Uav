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
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.request.ZlnfAnnexRequest;
import com.vt.fengci.zlnf.service.IAnnexService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class AnnexController extends BaseGatewayController {

	private static final long serialVersionUID = -4237840534796840027L;
	/**
     * member service
     */
    @Autowired
    private IAnnexService annexService;
    
    
    /**
     * 查询身份证图片
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/annex/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult agrementinfoContext(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfAnnexRequest zlnfAnnexRequest  = convert(data, ZlnfAnnexRequest.class);
        // 2. 业务检查
        if (zlnfAnnexRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
        ZlnfAnnexExample.Criteria criteria = zlnfAnnexExample.createCriteria();
        criteria.andDeptcodeEqualTo(zlnfAnnexRequest.getDeptcode());
        criteria.andDepttypeEqualTo("4");
        criteria.andIsdeletedEqualTo("0");
        try {
        	List<ZlnfAnnex> list = annexService.getResult(zlnfAnnexExample);
        	result.setSuccess(true);
        	result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询身份证图片成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("查询身份证图片失败");
		}
        return result;
    }

    /**
     * 查询图片
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/annex/queryByCode"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryByCode(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfAnnexRequest zlnfAnnexRequest  = convert(data, ZlnfAnnexRequest.class);
    	// 2. 业务检查
    	if (zlnfAnnexRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
    	ZlnfAnnexExample.Criteria criteria = zlnfAnnexExample.createCriteria();
    	criteria.andAnnexcodeEqualTo(zlnfAnnexRequest.getAnnexcode());
    	criteria.andIsdeletedEqualTo("0");
    	try {
    		List<ZlnfAnnex> list = annexService.getResult(zlnfAnnexExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询图片成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询图片失败");
    	}
    	return result;
    }

    /**
     * 添加图片
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/annex/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult annexCreate(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfAnnexRequest zlnfAnnexRequest  = convert(data, ZlnfAnnexRequest.class);
    	// 2. 业务检查
    	if (zlnfAnnexRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	Date date = new Date();
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	String path = zlnfAnnexRequest.getAnnexpath();
		String annexname = zlnfAnnexRequest.getAnnexname();
		String httpPath = zlnfAnnexRequest.getHttppath();
    	try {
    		String annexcode = Uuid32.getUUID32();
    		Map<String, String> map = new HashMap<>();
    		if(StringUtils.isNotBlank(path)){
    			String[] paths = path.split(",");
    			String[] annexnames = annexname.split(",");
    			String[] httpPaths = httpPath.split(",");
    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
    			zlnfAnnex.setAnnexcode(annexcode);
    			zlnfAnnex.setAnnexpath(paths[0]);
    			zlnfAnnex.setAnnexname(annexnames[0]);
    			zlnfAnnex.setHttppath(httpPaths[0]);
    			zlnfAnnex.setDepttype("8");
    			zlnfAnnex.setCreatedonutc(date);
    			annexService.create(zlnfAnnex);
    			map.put("name", annexnames[0]);
    			map.put("code", annexcode);
    			ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
    	    	ZlnfAnnexExample.Criteria criteria = zlnfAnnexExample.createCriteria();
    	    	criteria.andAnnexcodeEqualTo(annexcode);
    	    	criteria.andIsdeletedEqualTo("0");
    	    	List<ZlnfAnnex> list = annexService.getResult(zlnfAnnexExample);
    	    	if(list.size()>0){
    	    		map.put("path", list.get(0).getHttppath());
    	    	}
    		}
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("保存图片成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("保存图片失败");
    	}
    	return result;
    }

    /**
     * 添加支付图片
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/annex/add"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult annexAdd(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfAnnexRequest zlnfAnnexRequest  = convert(data, ZlnfAnnexRequest.class);
    	// 2. 业务检查
    	if (zlnfAnnexRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	Date date = new Date();
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	String path = zlnfAnnexRequest.getAnnexpath();
    	String annexname = zlnfAnnexRequest.getAnnexname();
    	String httpPath = zlnfAnnexRequest.getHttppath();
    	try {
    		String annexcode = Uuid32.getUUID32();
//    		Map<String, String> map = new HashMap<>();
    		if(StringUtils.isNotBlank(path)){
    			String[] paths = path.split(",");
    			String[] annexnames = annexname.split(",");
    			String[] httpPaths = httpPath.split(",");
    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
    			zlnfAnnex.setAnnexcode(annexcode);
    			zlnfAnnex.setDeptcode(zlnfAnnexRequest.getDeptcode());
    			zlnfAnnex.setAnnexpath(paths[0]);
    			zlnfAnnex.setAnnexname(annexnames[0]);
    			zlnfAnnex.setHttppath(httpPaths[0]);
    			zlnfAnnex.setDepttype("10");
    			zlnfAnnex.setCreatedonutc(date);
    			annexService.create(zlnfAnnex);
//    			map.put("name", annexnames[0]);
//    			map.put("code", annexcode);
//    			ZlnfAnnexExample zlnfAnnexExample = new ZlnfAnnexExample();
//    			ZlnfAnnexExample.Criteria criteria = zlnfAnnexExample.createCriteria();
//    			criteria.andAnnexcodeEqualTo(annexcode);
//    			criteria.andIsdeletedEqualTo("0");
//    			List<ZlnfAnnex> list = annexService.getResult(zlnfAnnexExample);
//    			if(list.size()>0){
//    				map.put("path", list.get(0).getHttppath());
//    			}
    		}
    		result.setSuccess(true);
//    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("保存图片成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("保存图片失败");
    	}
    	return result;
    }
}
