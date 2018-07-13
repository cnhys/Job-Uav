package com.vt.fengci.zlnf.controller;

import java.util.ArrayList;
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
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fencing.model.ZlnfUserdiscuss;
import com.vt.fencing.model.ZlnfUserdiscussExample;
import com.vt.fencing.request.ZlnfUserdiscussRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.ITakeawayOrderService;
import com.vt.fengci.zlnf.service.IUserDiscussService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class UserDiscussController extends BaseGatewayController {

	private static final long serialVersionUID = -2584380757987522124L;

	/**
     * member service
     */
    @Autowired
    private IUserDiscussService userDiscussService;
    
    @Autowired
    private IAnnexService annexService;
    
    @Autowired
    private ITakeawayOrderService takeawayOrderService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
    private IDictInfoService dictInfoService;
    
    
    /**
     * 我的评价
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/myDiscuss/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult myDiscussQuery(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfUserdiscussRequest zlnfUserdiscussRequest  = convert(data, ZlnfUserdiscussRequest.class);
        // 2. 业务检查
        if (zlnfUserdiscussRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        try {
        	ZlnfUserdiscussExample userdiscussExample = new ZlnfUserdiscussExample();
        	ZlnfUserdiscussExample.Criteria userdiscussCriteria = userdiscussExample.createCriteria();
        	userdiscussCriteria.andCreatorcodeEqualTo(zlnfUserdiscussRequest.getUsercode());
        	userdiscussCriteria.andIsdeletedEqualTo("0");
        	List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(userdiscussExample);
        	for(int i = 0;i<userdiscussList.size();i++){
        		ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
        		ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
        		annexCriteria.andDeptcodeEqualTo(userdiscussList.get(i).getDiscusscode());
        		annexCriteria.andDepttypeEqualTo("7");
        		annexCriteria.andIsdefaultEqualTo("0");
        		List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
        		userdiscussList.get(i).setAnnexList(annexList);
        		ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
        		custofomerinExample.createCriteria().andUsercodeEqualTo(userdiscussList.get(i).getCreatorcode());
        		List<ZlnfFcustofomerin> custofomerinList = customerInfoService.getResult(custofomerinExample);
        		if(custofomerinList.get(0).getHttpphoto()!=null){
        			userdiscussList.get(i).setHttppath(custofomerinList.get(0).getHttpphoto());
        		}
        		ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
        		ZlnfDictInfoExample.Criteria dictInfoCriteria = dictInfoExample.createCriteria();
        		dictInfoCriteria.andIsdeletedEqualTo("0");
        		dictInfoCriteria.andDictcodeEqualTo(userdiscussList.get(i).getServertype());
        		List<ZlnfDictInfo> dictInfoList = dictInfoService.getResult(dictInfoExample);
        		if(dictInfoList.size()>0){
        			userdiscussList.get(i).setServertype(dictInfoList.get(0).getDictname());
        		}
        	}
        	result.setSuccess(true);// 查询成功
        	result.setData(userdiscussList);
    		result.setReturnCode("0000");
    		result.setMessage("查询我的评价成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);// 查询未成功
			result.setReturnCode("1111");
			result.setMessage("查询我的评价失败");
		}
        return result;
    }

    
    /**
     * 查看给我的评价
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/getmeDiscuss/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult getmeDiscussQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfUserdiscussRequest zlnfUserdiscussRequest  = convert(data, ZlnfUserdiscussRequest.class);
    	// 2. 业务检查
    	if (zlnfUserdiscussRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	try {
    		ZlnfUserdiscussExample userdiscussExample = new ZlnfUserdiscussExample();
    		ZlnfUserdiscussExample.Criteria userdiscussCriteria = userdiscussExample.createCriteria();
    		userdiscussCriteria.andEvaluatetypeEqualTo(zlnfUserdiscussRequest.getEvaluatetype());
    		if("1".equals(zlnfUserdiscussRequest.getEvaluatetype())){
    			userdiscussCriteria.andCoopcodeEqualTo(zlnfUserdiscussRequest.getCoopcode());
    		}else if("2".equals(zlnfUserdiscussRequest.getEvaluatetype())){
    			userdiscussCriteria.andMerchantcodeEqualTo(zlnfUserdiscussRequest.getMerchantcode());
    		}
    		userdiscussCriteria.andIsdeletedEqualTo("0");
    		List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(userdiscussExample);
    		for(int i = 0;i<userdiscussList.size();i++){
    			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
    			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
    			annexCriteria.andDeptcodeEqualTo(userdiscussList.get(i).getDiscusscode());
    			annexCriteria.andDepttypeEqualTo("7");
    			annexCriteria.andIsdefaultEqualTo("0");
    			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    			userdiscussList.get(i).setAnnexList(annexList);
    			ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
        		custofomerinExample.createCriteria().andUsercodeEqualTo(userdiscussList.get(i).getCreatorcode());
        		List<ZlnfFcustofomerin> custofomerinList = customerInfoService.getResult(custofomerinExample);
        		if(custofomerinList.get(0).getHttpphoto()!=null){
        			userdiscussList.get(i).setHttppath(custofomerinList.get(0).getHttpphoto());
        		}
        		ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
        		ZlnfDictInfoExample.Criteria dictInfoCriteria = dictInfoExample.createCriteria();
        		dictInfoCriteria.andIsdeletedEqualTo("0");
        		dictInfoCriteria.andDictcodeEqualTo(userdiscussList.get(i).getServertype());
        		List<ZlnfDictInfo> dictInfoList = dictInfoService.getResult(dictInfoExample);
        		if(dictInfoList.size()>0){
        			userdiscussList.get(i).setServertype(dictInfoList.get(0).getDictname());
        		}
    		}
    		result.setSuccess(true);// 查询成功
    		result.setData(userdiscussList);
    		result.setReturnCode("0000");
    		result.setMessage("查询给我的评价成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);// 查询未成功
    		result.setReturnCode("1111");
    		result.setMessage("查询给我的评价失败");
    	}
    	return result;
    }

    /**
     * 农机服务详情评价
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/discussProgram/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult discussProgramQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfUserdiscussRequest zlnfUserdiscussRequest  = convert(data, ZlnfUserdiscussRequest.class);
    	// 2. 业务检查
    	if (zlnfUserdiscussRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	try {
    		List<ZlnfUserdiscuss> userdiscussList1 = new ArrayList<>();
    		ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
    		ZlnfTakeawayOrderExample.Criteria orderCriteria = takeawayOrderExample.createCriteria();
    		orderCriteria.andServicecodeEqualTo(zlnfUserdiscussRequest.getProgramcode());
    		orderCriteria.andIsdeletedEqualTo("0");
    		List<ZlnfTakeawayOrder> orderList = takeawayOrderService.getResult(takeawayOrderExample);
    		for(int i = 0;i<orderList.size();i++){
    			ZlnfUserdiscussExample userdiscussExample = new ZlnfUserdiscussExample();
    			ZlnfUserdiscussExample.Criteria userdiscussCriteria = userdiscussExample.createCriteria();
    			userdiscussCriteria.andEvaluatetypeEqualTo("2");
    			userdiscussCriteria.andMerchantcodeEqualTo(zlnfUserdiscussRequest.getMerchantcode());
    			userdiscussCriteria.andServercodeEqualTo(orderList.get(i).getOrdercode());
    			userdiscussCriteria.andIsdeletedEqualTo("0");
    			List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(userdiscussExample);
    			if(userdiscussList.size()>0){
    				for(int j = 0;j<userdiscussList.size();j++){
    					userdiscussList1.add(userdiscussList.get(j));
    				}
    			}
    		}
    		for(int i = 0;i<userdiscussList1.size();i++){
    			ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
    			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
    			annexCriteria.andDeptcodeEqualTo(userdiscussList1.get(i).getDiscusscode());
    			annexCriteria.andDepttypeEqualTo("7");
    			annexCriteria.andIsdefaultEqualTo("0");
    			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    			userdiscussList1.get(i).setAnnexList(annexList);
    			ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
        		custofomerinExample.createCriteria().andUsercodeEqualTo(userdiscussList1.get(i).getCreatorcode());
        		List<ZlnfFcustofomerin> custofomerinList = customerInfoService.getResult(custofomerinExample);
        		if(custofomerinList.get(0).getHttpphoto()!=null){
        			userdiscussList1.get(i).setHttppath(custofomerinList.get(0).getHttpphoto());
        		}
        		ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
        		ZlnfDictInfoExample.Criteria dictInfoCriteria = dictInfoExample.createCriteria();
        		dictInfoCriteria.andIsdeletedEqualTo("0");
        		dictInfoCriteria.andDictcodeEqualTo(userdiscussList1.get(i).getServertype());
        		List<ZlnfDictInfo> dictInfoList = dictInfoService.getResult(dictInfoExample);
        		if(dictInfoList.size()>0){
        			userdiscussList1.get(i).setServertype(dictInfoList.get(0).getDictname());
        		}
    		}
    		result.setSuccess(true);// 查询成功
    		result.setData(userdiscussList1);
    		result.setReturnCode("0000");
    		result.setMessage("查询农机服务详情评价成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);// 查询未成功
    		result.setReturnCode("1111");
    		result.setMessage("查询农机服务详情评价失败");
    	}
    	return result;
    }
    
    
    
    /**
     * 添加评价
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/userdiscuss/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult userdiscussCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfUserdiscussRequest zlnfUserdiscussRequest  = convert(data, ZlnfUserdiscussRequest.class);
        // 2. 业务检查
        if (zlnfUserdiscussRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        try {
	        Date date = new Date();
	    	String discusscode = Uuid32.getUUID32();
	    	ZlnfUserdiscuss zlnfUserdiscuss=new ZlnfUserdiscuss();
	    	zlnfUserdiscuss.setDiscusscode(discusscode);
	    	zlnfUserdiscuss.setDiscusscontent(zlnfUserdiscussRequest.getDiscusscontent());
	    	zlnfUserdiscuss.setDiscusstime(date);
	    	zlnfUserdiscuss.setServercode(zlnfUserdiscussRequest.getServercode());
	    	zlnfUserdiscuss.setServertype(zlnfUserdiscussRequest.getServertype());
	    	zlnfUserdiscuss.setCoopcode(zlnfUserdiscussRequest.getCoopcode());
	    	zlnfUserdiscuss.setCoopname(zlnfUserdiscussRequest.getCoopname());
	    	zlnfUserdiscuss.setDislevel(zlnfUserdiscussRequest.getDislevel());
	    	zlnfUserdiscuss.setMerchantcode(zlnfUserdiscussRequest.getMerchantcode());
	    	zlnfUserdiscuss.setMerchantname(zlnfUserdiscussRequest.getMerchantname());
	    	zlnfUserdiscuss.setCreatedonutc(date);
	    	zlnfUserdiscuss.setCreator(zlnfUserdiscussRequest.getUsername());
	    	zlnfUserdiscuss.setCreatorcode(zlnfUserdiscussRequest.getUsercode());
	    	zlnfUserdiscuss.setEvaluatetype(zlnfUserdiscussRequest.getEvaluatetype());
	    	ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
	        ZlnfTakeawayOrderExample.Criteria criteria = takeawayOrderExample.createCriteria();
	        criteria.andIsdeletedEqualTo("0");
	        criteria.andOrdercodeEqualTo(zlnfUserdiscussRequest.getServercode());
	        List<ZlnfTakeawayOrder> list = takeawayOrderService.getResult(takeawayOrderExample);
	        ZlnfTakeawayOrder zlnfTakeawayOrder = new ZlnfTakeawayOrder();
	        zlnfTakeawayOrder.setId(list.get(0).getId());
	        if("1".equals(zlnfUserdiscussRequest.getEvaluatetype())){
	        	if("1".equals(list.get(0).getEvaluate())){
	        		zlnfTakeawayOrder.setEvaluate("3");
	        	}else{
	        		zlnfTakeawayOrder.setEvaluate("2");
	        	}
	        }else{
	        	if("2".equals(list.get(0).getEvaluate())){
	        		zlnfTakeawayOrder.setEvaluate("3");
	        	}else{
	        		zlnfTakeawayOrder.setEvaluate("1");
	        	}
	        }
	        takeawayOrderService.update(zlnfTakeawayOrder);
	    	//上传评价图片
	    	String path = zlnfUserdiscussRequest.getAnnexpath();
			String annexname = zlnfUserdiscussRequest.getAnnexname();
			String httpPath = zlnfUserdiscussRequest.getHttppath();
			if(StringUtils.isNotBlank(path)){
	    		String[] paths = path.split(",");
	    		String[] annexnames = annexname.split(",");
	    		String[] httpPaths = httpPath.split(",");
	    		for(int i = 0;i<paths.length;i++){
	    			String annexcode = Uuid32.getUUID32();
	    			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	    			zlnfAnnex.setDeptcode(discusscode);
	    			zlnfAnnex.setAnnexcode(annexcode);
	    			zlnfAnnex.setAnnexname(annexnames[i]);
	    			zlnfAnnex.setAnnexpath(paths[i]);
	    			zlnfAnnex.setHttppath(httpPaths[i]);
	    			zlnfAnnex.setDepttype("7");
	    			zlnfAnnex.setCreator(zlnfUserdiscussRequest.getUsername());
	    			zlnfAnnex.setCreatedonutc(date);
	    			annexService.create(zlnfAnnex);
	    		}
	    	}
	        userDiscussService.create(zlnfUserdiscuss);
	        result.setReturnCode("0000");
        	result.setMessage("添加评价成功");
        	result.setSuccess(true);// 
        	result.setData("");
		} catch (Exception e) {
			// TODO: handle exception
			result.setReturnCode("1111");
			result.setSuccess(false);// 
			result.setMessage("添加评价失败");
		}
        return result;
    }
    
}
