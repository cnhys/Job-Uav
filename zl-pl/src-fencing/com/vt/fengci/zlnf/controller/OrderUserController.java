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
import com.vt.fencing.model.ZlnfOrderUser;
import com.vt.fencing.model.ZlnfOrderUserExample;
import com.vt.fencing.request.ZlnfOrderUserRequest;
import com.vt.fengci.zlnf.service.IOrderUserService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class OrderUserController extends BaseGatewayController {

	private static final long serialVersionUID = -5927022864743112706L;
	/**
     * member service
     */
    
	@Autowired
    private IOrderUserService orderUserService;
    
    /**
     * 修改订单人员信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/oderuser/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult messageInfoQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfOrderUserRequest zlnfOrderUserRequest  = convert(data, ZlnfOrderUserRequest.class);
        // 2. 业务检查
        if (zlnfOrderUserRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        ZlnfOrderUserExample zlnfOrderUserExample = new ZlnfOrderUserExample();
        zlnfOrderUserExample.createCriteria().andOrdercodeEqualTo(zlnfOrderUserRequest.getOrdercode());
        List<ZlnfOrderUser> list = orderUserService.getResult(zlnfOrderUserExample);
        Date date = new Date();
        ZlnfOrderUser zlnfOrderUser = new ZlnfOrderUser();
        zlnfOrderUser.setId(list.get(0).getId());
        String sucode = zlnfOrderUserRequest.getSucode();
        if(StringUtils.isNotBlank(sucode)){
        	zlnfOrderUser.setSucode(sucode);
        }
        String suname = zlnfOrderUserRequest.getSuname();
        if(StringUtils.isNotBlank(suname)){
        	zlnfOrderUser.setSuname(suname);
        }
        String fmcode = zlnfOrderUserRequest.getFmcode();
        if(StringUtils.isNotBlank(fmcode)){
        	zlnfOrderUser.setFmcode(fmcode);
        }
        String fmname = zlnfOrderUserRequest.getFmname();
        if(StringUtils.isNotBlank(fmname)){
        	zlnfOrderUser.setFmname(fmname);
        }
        String financecode = zlnfOrderUserRequest.getFinancecode();
        if(StringUtils.isNotBlank(financecode)){
        	zlnfOrderUser.setFinancecode(financecode);
        }
        String financename = zlnfOrderUserRequest.getFinancename();
        if(StringUtils.isNotBlank(financename)){
        	zlnfOrderUser.setFinancename(financename);
        }
        zlnfOrderUser.setModifier(zlnfOrderUserRequest.getCreator());
        zlnfOrderUser.setUpdatedonutc(date);
    	try {
    		orderUserService.update(zlnfOrderUser);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("修改订单人员信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("修改订单人员信息失败");
		}
        return result;
    }
    
}
