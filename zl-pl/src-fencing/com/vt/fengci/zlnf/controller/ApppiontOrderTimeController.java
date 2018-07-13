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
import com.vt.base.util.DigestUtils;
import com.vt.fencing.model.ZlnfApppiontOrderTime;
import com.vt.fencing.model.ZlnfApppiontOrderTimeExample;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fencing.request.ZlnfApppiontOrderTimeRequest;
import com.vt.fengci.zlnf.service.IApppiontOrderTimeService;
import com.vt.fengci.zlnf.service.IFoodInfoService;

/**
 * user related functions
 * Created by john on 17/7/26.
 */
@Controller
public class ApppiontOrderTimeController extends BaseGatewayController {

	private static final long serialVersionUID = 2025499615347078682L;
	/**
     * member service
     */
    
    @Autowired
    private IApppiontOrderTimeService apppiontOrderTimeService;
    
    @Autowired
    private IFoodInfoService foodInfoService;
    
    
    /**
     * 合作社添加预约时间
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrderTime/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderTimeCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderTimeRequest zlnfApppiontOrderTimeRequest  = convert(data, ZlnfApppiontOrderTimeRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderTimeRequest == null) {
            reject("user.activate.model.convert.error");
        }
        OptResult result = OptResult.success();
        String appiontordercode =DigestUtils.generateRandomNumber(10);
    	Date date = new Date();
    	String foodname = zlnfApppiontOrderTimeRequest.getFoodname();
    	String username = zlnfApppiontOrderTimeRequest.getUsername();
    	String foodkind = zlnfApppiontOrderTimeRequest.getFoodkind();
    	String foodkindcode = zlnfApppiontOrderTimeRequest.getFoodkindcode();
    	String foodunit = zlnfApppiontOrderTimeRequest.getFoodunit();
    	//添加预约时间信息
    	ZlnfApppiontOrderTime zlnfApppiontOrderTime = new ZlnfApppiontOrderTime();
    	zlnfApppiontOrderTime.setAppiontordercode(appiontordercode);
    	zlnfApppiontOrderTime.setAppionttime(zlnfApppiontOrderTimeRequest.getAppionttime());
    	zlnfApppiontOrderTime.setSellnum(zlnfApppiontOrderTimeRequest.getSellnum());
    	zlnfApppiontOrderTime.setFoodcode(zlnfApppiontOrderTimeRequest.getFoodcode());
    	zlnfApppiontOrderTime.setFoodkind(foodkind);
    	zlnfApppiontOrderTime.setFoodkindcode(foodkindcode);
    	zlnfApppiontOrderTime.setFoodunit(foodunit);
    	zlnfApppiontOrderTime.setFoodlevel(zlnfApppiontOrderTimeRequest.getFoodlevel());
    	zlnfApppiontOrderTime.setFoodlevelcode(zlnfApppiontOrderTimeRequest.getFoodlevelcode());
    	zlnfApppiontOrderTime.setFoodname(foodname);
    	zlnfApppiontOrderTime.setCoopcode(zlnfApppiontOrderTimeRequest.getCoopcode());
    	zlnfApppiontOrderTime.setCreator(username);
    	zlnfApppiontOrderTime.setCreatorcode(zlnfApppiontOrderTimeRequest.getUsercode());
    	zlnfApppiontOrderTime.setCreatedonutc(date);
    	zlnfApppiontOrderTime.setTimeslot(zlnfApppiontOrderTimeRequest.getTimeslot());
    	try {
    		apppiontOrderTimeService.create(zlnfApppiontOrderTime);
    		result.setSuccess(true);
        	result.setReturnCode("0000");
        	result.setMessage("添加预约时间成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(true);
        	result.setReturnCode("1111");
        	result.setMessage("添加预约时间失败");
		}
        return result;
    }
    
    
    /**
     * 修改合作社预约时间
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrderTime/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult apppiontOrderTimeUpdate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfApppiontOrderTimeRequest zlnfApppiontOrderTimeRequest  = convert(data, ZlnfApppiontOrderTimeRequest.class);
        // 2. 业务检查
        if (zlnfApppiontOrderTimeRequest == null) {
            reject("user.activate.model.convert.error");
        }
        ZlnfApppiontOrderTimeExample apppiontOrderTimeExample = new ZlnfApppiontOrderTimeExample();
        apppiontOrderTimeExample.createCriteria().andAppiontordercodeEqualTo(zlnfApppiontOrderTimeRequest.getAppiontordercode());
        List<ZlnfApppiontOrderTime> list = apppiontOrderTimeService.getResult(apppiontOrderTimeExample);
        OptResult result = OptResult.success();
        if(list.size()>0){
        	Date date = new Date();
        	String username = zlnfApppiontOrderTimeRequest.getUsername();
        	String foodkind = zlnfApppiontOrderTimeRequest.getFoodkind();
        	String foodkindcode = zlnfApppiontOrderTimeRequest.getFoodkindcode();
        	String foodunit = zlnfApppiontOrderTimeRequest.getFoodunit();
        	//添加预约时间信息
        	ZlnfApppiontOrderTime zlnfApppiontOrderTime = new ZlnfApppiontOrderTime();
        	zlnfApppiontOrderTime.setId(list.get(0).getId());
        	Date appionttime = zlnfApppiontOrderTimeRequest.getAppionttime();
        	if(appionttime!=null){
        		zlnfApppiontOrderTime.setAppionttime(appionttime);
        	}
        	String sellnum = zlnfApppiontOrderTimeRequest.getSellnum();
        	if(StringUtils.isNotBlank(sellnum)){
        		zlnfApppiontOrderTime.setSellnum(sellnum);
        	}
        	if(StringUtils.isNotBlank(foodkind)){
        		zlnfApppiontOrderTime.setFoodkind(foodkind);
        	}
        	if(StringUtils.isNotBlank(foodkindcode)){
        		zlnfApppiontOrderTime.setFoodkindcode(foodkindcode);
        	}
        	if(StringUtils.isNotBlank(foodunit)){
        		zlnfApppiontOrderTime.setFoodunit(foodunit);
        	}
        	String foodlevel = zlnfApppiontOrderTimeRequest.getFoodlevel();
        	if(StringUtils.isNotBlank(foodlevel)){
        		zlnfApppiontOrderTime.setFoodlevel(foodlevel);
        	}
        	String timeslot = zlnfApppiontOrderTimeRequest.getTimeslot();
        	if(StringUtils.isNotBlank(timeslot)){
        		zlnfApppiontOrderTime.setFoodlevel(timeslot);
        	}
        	zlnfApppiontOrderTime.setModifier(username);
        	zlnfApppiontOrderTime.setUpdatedonutc(date);
        	try {
        		apppiontOrderTimeService.update(zlnfApppiontOrderTime);
        		result.setSuccess(true);
            	result.setReturnCode("0000");
            	result.setMessage("修改预约时间成功");
    		} catch (Exception e) {
    			// TODO: handle exception
    			result.setSuccess(false);
            	result.setReturnCode("1111");
            	result.setMessage("修改预约时间失败");
    		}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0001");
        	result.setMessage("粮库不存在");
        }
        return result;
    }

    /**
     * 查询粮库已完成卖粮信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/apppiontOrderTime/queryOrderTimeRowNum"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryOrderTimeRowNum(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfApppiontOrderTimeRequest zlnfApppiontOrderTimeRequest  = convert(data, ZlnfApppiontOrderTimeRequest.class);
    	// 2. 业务检查
    	if (zlnfApppiontOrderTimeRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfApppiontOrderTime zlnfApppiontOrderTime = new ZlnfApppiontOrderTime();
    	zlnfApppiontOrderTime.setNumber(zlnfApppiontOrderTimeRequest.getNumber());
    	String foodcode = zlnfApppiontOrderTimeRequest.getFoodcode();
    	String coopcode = zlnfApppiontOrderTimeRequest.getCoopcode();
    	String usercode = zlnfApppiontOrderTimeRequest.getUsercode();
    	String roletype = zlnfApppiontOrderTimeRequest.getRoletype();
    	if(StringUtils.isNotBlank(foodcode)){
    		zlnfApppiontOrderTime.setFoodcode(foodcode);
    	}
    	if(StringUtils.isNotBlank(coopcode)){
    		zlnfApppiontOrderTime.setCoopcode(coopcode);
    	}
    	if(!"5".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)){
    		if(StringUtils.isNotBlank(usercode)){
    			zlnfApppiontOrderTime.setCreatorcode(usercode);
    		}
    	}
    	String state = zlnfApppiontOrderTimeRequest.getState();
    	if(StringUtils.isNotBlank(state)){
    		zlnfApppiontOrderTime.setState(state);
    	}
    	try {
			List<ZlnfApppiontOrderTime> apppiontOrderTimeList = apppiontOrderTimeService.queryOrderTimeRowNum(zlnfApppiontOrderTime);
			for(int i = 0;i<apppiontOrderTimeList.size();i++){
				ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
				foodInfoExample.createCriteria().andFoodcodeEqualTo(apppiontOrderTimeList.get(i).getFoodcode());
				List<ZlnfFoodInfo> foodList = foodInfoService.getResult(foodInfoExample);
				if(foodList.get(0).getFoodnamejc()!=null){
					apppiontOrderTimeList.get(i).setFoodnamejc(foodList.get(0).getFoodnamejc());
				}
				if("5".equals(roletype) || "1".equals(roletype) || "2".equals(roletype)){
    				if(usercode.equals(apppiontOrderTimeList.get(i).getCreatorcode())){
    					apppiontOrderTimeList.get(i).setIsmyorder("1");
    				}else{
    					apppiontOrderTimeList.get(i).setIsmyorder("0");
    				}
    			}
			}
			result.setSuccess(true);
			result.setData(apppiontOrderTimeList);
			result.setReturnCode("0000");
			result.setMessage("查询粮库已完成卖粮信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("查询粮库已完成卖粮信息失败");
		}
    	return result;
    }
    
}
