package com.vt.fengci.zlnf.controller;

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
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfMessageInfo;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IMessageInfoService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class MessageInfoController extends BaseGatewayController {

	private static final long serialVersionUID = 7759583564676854954L;
	/**
     * member service
     */
    
    @Autowired
    private IMessageInfoService zlnfMessageInfoService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    /**
     * 获取所有消息信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/messageInfo/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult messageInfoQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFcustofomerinRequest zlnfFcustofomerinRequest  = convert(data, ZlnfFcustofomerinRequest.class);
        // 2. 业务检查
        if (zlnfFcustofomerinRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
    	ZlnfMessageInfo record = new ZlnfMessageInfo();
    	record.setUsercode(zlnfFcustofomerinRequest.getUsercode());
    	try {
    		List<ZlnfMessageInfo> messageList = zlnfMessageInfoService.queryMessage(record);
    		result.setSuccess(true);
    		result.setData(messageList);
        	result.setReturnCode("0000");
        	result.setMessage("消息返回成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("消息返回失败");
		}
        return result;
    }

    
    
    @RequestMapping(value = {"/api/messageInfo/ceshi"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult ceshi(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFcustofomerinRequest zlnfFcustofomerinRequest  = convert(data, ZlnfFcustofomerinRequest.class);
    	// 2. 业务检查
    	if (zlnfFcustofomerinRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfFcustofomerinExample zlnfFcustofomerinExample = new ZlnfFcustofomerinExample();
    		ZlnfFcustofomerinExample.Criteria criteria = zlnfFcustofomerinExample.createCriteria();
    		criteria.andIsdeletedEqualTo("0");
    		List<ZlnfFcustofomerin> list = customerInfoService.getResult(zlnfFcustofomerinExample);
    		Date date =new Date();
    		for(int i = 0;i<list.size();i++){
    			ZlnfMessageInfo messageInfo = new ZlnfMessageInfo();
    			messageInfo.setMessageinfocode(Uuid32.getUUID32());
    			messageInfo.setUsercode(list.get(i).getUsercode());
    			messageInfo.setTitle("版本更新");
    			messageInfo.setShortcontent("版本更新");
    			messageInfo.setContent("版本更新");
    			messageInfo.setSendusercode(list.get(i).getUsercode());
    			messageInfo.setSendusername(list.get(i).getUsername());
    			messageInfo.setSendtime(date);
    			messageInfo.setCreatedonutc(date);
    			zlnfMessageInfoService.create(messageInfo);
    		}
    		result.setSuccess(true);
    		result.setReturnCode("0000");
    		result.setMessage("发送更新推送成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("发送更新推送失败");
    	}
    	return result;
    }

}
