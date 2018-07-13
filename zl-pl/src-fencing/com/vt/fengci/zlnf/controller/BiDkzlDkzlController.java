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
import com.vt.fencing.model.ZlnfBiDkzlDkzl;
import com.vt.fencing.model.ZlnfBiDkzlDkzlExample;
import com.vt.fencing.request.ZlnfBiDkzlDkzlRequest;
import com.vt.fengci.zlnf.service.IBiDkzlDkzlService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class BiDkzlDkzlController extends BaseGatewayController {

	private static final long serialVersionUID = -2418452482220842808L;
	
	/**
     * member service
     */
    @Autowired
    private IBiDkzlDkzlService biDkzlDkzlService;
    
    
    /**
     * 查询贷款种类
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDkzlDkzl/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biDkzlDkzlQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfBiDkzlDkzlRequest zlnfBiDkzlDkzlRequest  = convert(data, ZlnfBiDkzlDkzlRequest.class);
        // 2. 业务检查
        if (zlnfBiDkzlDkzlRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        ZlnfBiDkzlDkzlExample zlnfBiDkzlDkzlExample = new ZlnfBiDkzlDkzlExample();
        ZlnfBiDkzlDkzlExample.Criteria criteria = zlnfBiDkzlDkzlExample.createCriteria();
        criteria.andIsdeletedEqualTo("0");
        criteria.andDkzlEqualTo(zlnfBiDkzlDkzlRequest.getDkzl());
        List<ZlnfBiDkzlDkzl> biDkzlDkzlList = biDkzlDkzlService.getResult(zlnfBiDkzlDkzlExample);
        try {
        	if(biDkzlDkzlList.size()>0){
        		result.setSuccess(true);
        		result.setData(biDkzlDkzlList.get(0));
        		result.setReturnCode("0000");
        		result.setMessage("查询贷款类型成功");
        	}else{
        		result.setSuccess(false);
        		result.setReturnCode("0001");
        		result.setMessage("没有该贷款类型");
        	}
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("查询贷款类型失败");
		}
        return result;
    }
}
