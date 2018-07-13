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
import com.vt.fencing.model.ZlnfAgrementinfo;
import com.vt.fencing.model.ZlnfAgrementinfoExample;
import com.vt.fencing.request.ZlnfAgrementinfoRequest;
import com.vt.fengci.zlnf.service.IAgrementinfoService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class AgrementinfoController extends BaseGatewayController {

	private static final long serialVersionUID = -2584380757987522124L;

	/**
     * member service
     */
    @Autowired
    private IAgrementinfoService agrementinfoService;
    
    
    /**
     * 返回协议数据
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/agrementinfo/context"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult agrementinfoContext(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfAgrementinfoRequest zlnfAgrementinfoRequest  = convert(data, ZlnfAgrementinfoRequest.class);
        // 2. 业务检查
        if (zlnfAgrementinfoRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        ZlnfAgrementinfoExample example = new ZlnfAgrementinfoExample();
        example.createCriteria().andTypeEqualTo(zlnfAgrementinfoRequest.getType());
        OptResult result = OptResult.success();
        List<ZlnfAgrementinfo> list = new ArrayList<ZlnfAgrementinfo>();
        try {
        	list = agrementinfoService.getResult(example);
        	result.setSuccess(true);// 激活成功
        	result.setData(list.get(0));
    		result.setReturnCode("0000");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);// 激活未成功
			result.setReturnCode("1111");
		}
        return result;
    }
}
