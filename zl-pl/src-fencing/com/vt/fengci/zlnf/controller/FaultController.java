package com.vt.fengci.zlnf.controller;

import java.util.Date;

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
import com.vt.fencing.model.ZlnfFault;
import com.vt.fencing.request.ZlnfFaultRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.IFaultService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FaultController extends BaseGatewayController {

	private static final long serialVersionUID = -7555631670668686853L;
	/**
     * member service
     */
    
    @Autowired
    private IFaultService faultService;
    
    @Autowired
    private IAnnexService annexService;
    
    /**
     * 添加故障维修
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/fault/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult faultCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfFaultRequest zlnfFaultRequest  = convert(data, ZlnfFaultRequest.class);
        // 2. 业务检查
        if (zlnfFaultRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String faultcode =Uuid32.getUUID32();
    	Date date = new Date();
    	//添加故障信息
    	ZlnfFault zlnfFault = new ZlnfFault();
    	zlnfFault.setFaultcode(faultcode);
    	zlnfFault.setOrdercode(zlnfFaultRequest.getOrdercode());
    	zlnfFault.setUsercode(zlnfFaultRequest.getUsercode());
    	zlnfFault.setUsername(zlnfFaultRequest.getUsername());
    	zlnfFault.setMobile(zlnfFaultRequest.getMobile());
    	zlnfFault.setDescribe(zlnfFaultRequest.getDescribe());
    	zlnfFault.setStatus("1");
    	zlnfFault.setCreator(zlnfFaultRequest.getUsername());
    	zlnfFault.setCreatedonutc(date);
    	String path = zlnfFaultRequest.getAnnexpath();
		String annexname = zlnfFaultRequest.getAnnexname();
		String httpPath = zlnfFaultRequest.getHttpPath();
		if(StringUtils.isNotBlank(path)){
    		String[] paths = path.split(",");
    		String[] annexnames = annexname.split(",");
    		String[] httpPaths = httpPath.split(",");
    		for(int i = 0;i<paths.length;i++){
    			if("voice".equals(annexnames[i])){
    				zlnfFault.setSoundrecording(paths[i]);
    				zlnfFault.setVoicetime(zlnfFaultRequest.getVoicetime());
    			}else{
    				String annexcode = Uuid32.getUUID32();
    				ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
    				zlnfAnnex.setDeptcode(faultcode);
    				zlnfAnnex.setAnnexcode(annexcode);
    				zlnfAnnex.setAnnexname(annexnames[i]);
    				zlnfAnnex.setAnnexpath(paths[i]);
    				zlnfAnnex.setHttppath(httpPaths[i]);
    				zlnfAnnex.setDepttype("6");
    				zlnfAnnex.setCreator(zlnfFaultRequest.getUsername());
    				zlnfAnnex.setCreatedonutc(date);
    				annexService.create(zlnfAnnex);
    			}
    		}
    	}
    	try {
    		faultService.create(zlnfFault);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("添加故障维修成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("添加故障维修失败");
		}
        return result;
    }
    
}
