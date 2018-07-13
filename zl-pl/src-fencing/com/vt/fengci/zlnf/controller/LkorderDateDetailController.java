package com.vt.fengci.zlnf.controller;

import java.util.Calendar;
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
import com.vt.fencing.model.ZlnfLkorderDateDetail;
import com.vt.fencing.model.ZlnfLkorderDateDetailExample;
import com.vt.fencing.model.ZlnfLkorderHour;
import com.vt.fencing.model.ZlnfLkorderHourExample;
import com.vt.fencing.request.ZlnfLkorderDateDetailRequest;
import com.vt.fengci.zlnf.service.ILkorderDateDetailService;
import com.vt.fengci.zlnf.service.ILkorderHourService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class LkorderDateDetailController extends BaseGatewayController {

	private static final long serialVersionUID = -8884567557774996183L;
	/**
     * member service
     */
    
    @Autowired
    private ILkorderDateDetailService lkorderDateDetailService;

    @Autowired
    private ILkorderHourService lkorderHourService;
    
    /**
     * 查询粮库未来7天是否接受预约
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/lkorderDateDetail/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult lkorderDateDetailQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfLkorderDateDetailRequest zlnfLkorderDateDetailRequest  = convert(data, ZlnfLkorderDateDetailRequest.class);
        // 2. 业务检查
        if (zlnfLkorderDateDetailRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
    	try {
    		Date date = new Date();
    		Calendar now = Calendar.getInstance();  
        	now.setTime(date);  
        	now.set(Calendar.DATE, now.get(Calendar.DATE) + 7);
    		ZlnfLkorderDateDetailExample lkorderDateDetailExample = new ZlnfLkorderDateDetailExample();
    		ZlnfLkorderDateDetailExample.Criteria criteria = lkorderDateDetailExample.createCriteria();
    		criteria.andOrderDateLessThanOrEqualTo(now.getTime());
    		criteria.andFoodcodeEqualTo(zlnfLkorderDateDetailRequest.getFoodcode());
    		criteria.andIsdeleteEqualTo("0");
    		criteria.andOrderDateGreaterThanOrEqualTo(date);
    		List<ZlnfLkorderDateDetail> list = lkorderDateDetailService.getResult(lkorderDateDetailExample);
    		result.setSuccess(true);
    		result.setData(list);
        	result.setReturnCode("0000");
        	result.setMessage("查询未来7天是否接受预约成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("查询未来7天是否接受预约失败");
		}
        return result;
    }

    /**
     * 查询粮库某天可预约时间段
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/lkorderHour/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult lkorderHourQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfLkorderDateDetailRequest zlnfLkorderDateDetailRequest  = convert(data, ZlnfLkorderDateDetailRequest.class);
    	// 2. 业务检查
    	if (zlnfLkorderDateDetailRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfLkorderHourExample lkorderHourExample = new ZlnfLkorderHourExample();
    		ZlnfLkorderHourExample.Criteria criteria = lkorderHourExample.createCriteria();
    		criteria.andFoodcodeEqualTo(zlnfLkorderDateDetailRequest.getFoodcode());
    		criteria.andOrderBhEqualTo(zlnfLkorderDateDetailRequest.getOrderBh());
    		criteria.andIsdeleteEqualTo("0");
    		List<ZlnfLkorderHour> list = lkorderHourService.getResult(lkorderHourExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询粮库某天可预约时间段成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询粮库某天可预约时间段失败");
    	}
    	return result;
    }

}
