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
import com.vt.base.util.DesUtil;
import com.vt.base.util.GetLocation;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfCoopUser;
import com.vt.fencing.model.ZlnfCoopUserExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fencing.request.ZlnfCoopInfoRequest;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICoopUserService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IDmXzqhService;
import com.vt.fengci.zlnf.service.IFoodInfoService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class CoopInfoController extends BaseGatewayController {

	private static final long serialVersionUID = -8345920263419372832L;

	/**
     * member service
     */
	@Autowired
    private ICoopInfoService coopInfoService;
	
    @Autowired
    private ICoopUserService coopUserService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
    private IAnnexService annexService;

    @Autowired
    private IDmXzqhService dmXzqhService;
    
    @Autowired
    private IFoodInfoService foodInfoService;
    
    
    /**
     * 移动端合作社激活
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopuser/edit"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopuserEdit(String channel, String key, String data) {
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
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
        criteria.andUsernameEqualTo(zlnfFcustofomerinRequest.getUsername());
        criteria.andRoletypeEqualTo("2");
        criteria.andIsdeletedEqualTo("0");
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if (count == 0) {
        	result.setSuccess(true);
        	result.setReturnCode("0001");// 用户未注册returnCode
        	result.setMessage("用户不存在");
        	result.setData(null);
        } else {
        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
            ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
            coopExample.createCriteria().andUsercodeEqualTo(list.get(0).getUsercode());
            List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
            if(coopList.size()>0){
            	ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
                coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
                List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
                if("1".equals(coopInfoList.get(0).getIsaudit())){
                	result.setSuccess(false);// 激活未成功
    				result.setReturnCode("0005");
    				result.setMessage("该合作社已激活");
                }else{
                	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
                	zlnfCoopInfo.setId(coopInfoList.get(0).getId());
                	zlnfCoopInfo.setIsaudit("1");//合作社激活状态
                	try {
                		coopInfoService.update(zlnfCoopInfo);
                		List<ZlnfFcustofomerin> list1 = customerInfoService.getResult(example);
                		ZlnfCoopUserExample coopExample1 = new ZlnfCoopUserExample();
                		coopExample1.createCriteria().andUsercodeEqualTo(list1.get(0).getUsercode());
            			List<ZlnfCoopUser> coopList1 = coopUserService.getResult(coopExample);
            			list1.get(0).setRoletype2(coopList1.get(0).getRoletype());
            			list1.get(0).setZlnfCoopUser(coopList1.get(0));
            			ZlnfCoopInfoExample coopInfoExample1 = new ZlnfCoopInfoExample();//
            			coopInfoExample1.createCriteria().andCoopcodeEqualTo(coopList1.get(0).getCoopcode());
            			List<ZlnfCoopInfo> coopInfoList1 = coopInfoService.getResult(coopInfoExample);
            			list1.get(0).setLng(coopInfoList1.get(0).getLng());
            			list1.get(0).setLat(coopInfoList1.get(0).getLat());
            			list1.get(0).setType(coopInfoList1.get(0).getType());
            			list1.get(0).setCoopname(coopInfoList1.get(0).getCoopname());
            			list1.get(0).setVip(coopInfoList1.get(0).getVip());
            			if(coopInfoList1.get(0).getVip()!=null){
            				if("1".equals(coopInfoList1.get(0).getVip())){
            					list1.get(0).setProvice(coopInfoList1.get(0).getProvice());
            					list1.get(0).setCity(coopInfoList1.get(0).getCity());
            				}
            			}
            			list1.get(0).setAddress(coopInfoList1.get(0).getAddress());
                		result.setData(list1.get(0));
                		result.setSuccess(true);// 激活成功
                		result.setReturnCode("0000");
                		result.setMessage("激活合作社成功");
        			} catch (Exception e) {
        				result.setSuccess(false);// 激活未成功
        				result.setReturnCode("1111");
        				result.setMessage("激活合作社失败");
        			}
                }
            }else{
            	result.setSuccess(true);// 激活未成功
            	result.setData(null);
				result.setReturnCode("0001");
				result.setMessage("用户不存在");
            }
        }
        return result;
    }
    
    
    /**
     * 个体农户注册
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/individualfarmer/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopuserEditNew(String channel, String key, String data) {
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
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
        criteria.andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
       // criteria.andUsernameEqualTo(zlnfFcustofomerinRequest.getUsername());
       // criteria.andRoletypeEqualTo("2");
        criteria.andIsdeletedEqualTo("0");
        criteria.andIsauditEqualTo("1");
        List<ZlnfFcustofomerin>  fcustofomerinlist=customerInfoService.getResult(example);
        OptResult result = OptResult.success();
        if(fcustofomerinlist.size()>0){
        	result.setSuccess(true);// 激活未成功
			result.setReturnCode("0000");
			result.setMessage("农户已激活请直接登录");
			result.setData(fcustofomerinlist.get(0));
        }else{
        	//注册用户
        	String usercode = Uuid32.getUUID32();
        	Date date = new Date();
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
	        	zlnfFcustofomerin.setUsercode(usercode);
	        	zlnfFcustofomerin.setMobile(zlnfFcustofomerinRequest.getMobile());
	        	String userName = zlnfFcustofomerinRequest.getUsername();
	        	zlnfFcustofomerin.setUsername(userName);
	        	zlnfFcustofomerin.setLoginname(zlnfFcustofomerinRequest.getMobile());
	        	//zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
	        	zlnfFcustofomerin.setRoletype("2");
	        	zlnfFcustofomerin.setCreatedonutc(date);
	        	zlnfFcustofomerin.setCreator(userName);
	        	zlnfFcustofomerin.setIsaudit("1");
	        	zlnfFcustofomerin.setIsdeleted("0");
	        	zlnfFcustofomerin.setNickname(userName);
	        	
        	if(StringUtils.isNotBlank(zlnfFcustofomerinRequest.getCardnumber())){
        		zlnfFcustofomerin.setCardnumber(zlnfFcustofomerinRequest.getCardnumber());
        	}
        	
        	//注册合作社
        	String coopcode = Uuid32.getUUID32();
        	ZlnfCoopInfo ZlnfCoopInfo = new ZlnfCoopInfo();
        	ZlnfCoopInfo.setCoopname(zlnfFcustofomerinRequest.getUsername());
            	ZlnfCoopInfo.setCoopcode(coopcode);
            	ZlnfCoopInfo.setVip("0");
            	ZlnfCoopInfo.setSource("0");
            	ZlnfCoopInfo.setIsaudit("1");
            	ZlnfCoopInfo.setCreatedonutc(date);
            	ZlnfCoopInfo.setType("2");
            	ZlnfCoopInfo.setCreator(userName);
            	ZlnfCoopInfo.setPhone(zlnfFcustofomerinRequest.getMobile());
            	//ZlnfCoopInfo.setEnterprise(zlnfFcustofomerinRequest.getEnterprisecreditcode());
            	
        	//注册合作社用户
        	ZlnfCoopUser zlnfCoopUser = new ZlnfCoopUser();
	        	zlnfCoopUser.setStatus("1");
	        	zlnfCoopUser.setCoopcode(coopcode);
	        	zlnfCoopUser.setRoletype("3");
	        	zlnfCoopUser.setCreatedonutc(new Date());
	        	zlnfCoopUser.setCreator(zlnfFcustofomerinRequest.getUsername());
	        	zlnfCoopUser.setIsaudit("1");
	        	zlnfCoopUser.setIsdeleted("0");
	        	zlnfCoopUser.setCreatordeptcode(usercode);
	        	zlnfCoopUser.setUsercode(usercode);
	        	zlnfCoopUser.setCoopname(zlnfFcustofomerinRequest.getUsername());
	        try {
	        	coopInfoService.create(ZlnfCoopInfo);
	        	customerInfoService.create(zlnfFcustofomerin);
	        	coopUserService.create(zlnfCoopUser);
	        	ZlnfFcustofomerinExample exampleuser = new ZlnfFcustofomerinExample();
	            ZlnfFcustofomerinExample.Criteria criteriauser = exampleuser.createCriteria();
	            criteriauser.andUsercodeEqualTo(usercode);
	            List<ZlnfFcustofomerin> userlist=customerInfoService.getResult(exampleuser);
	        	result.setSuccess(true);// 激活未成功
				result.setReturnCode("0000");
				result.setMessage("该合作社已激活");
				result.setData(userlist.get(0));
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);// 激活未成功
				result.setReturnCode("1111");
				result.setMessage("农户注册失败");
			}
        	
        	
        }
    
        return result;
    }
    
    /**
     * 注册合作社
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopInfo/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopInfoCreate(String channel, String key, String data) {
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
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        example.createCriteria().andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
//        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        String coopcode =Uuid32.getUUID32();
    	String usercode = Uuid32.getUUID32();
    	Date date = new Date();
    	//添加合作社信息
    	ZlnfCoopInfo ZlnfCoopInfo = new ZlnfCoopInfo();
    	String userName = zlnfFcustofomerinRequest.getUsername();
//    	try {
//			userName = new String(zlnfFcustofomerinRequest.getUsername().getBytes("ISO-8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    	String coopname = zlnfFcustofomerinRequest.getCoopname();
    	if("1".equals(zlnfFcustofomerinRequest.getType())){
//    		try {
//				coopname = new String(zlnfFcustofomerinRequest.getCoopname().getBytes("ISO-8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
    		//添加营业执照图片信息
    		String path = zlnfFcustofomerinRequest.getAnnexpath();
    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
        		String[] paths = path.split(",");
        		String[] annexnames = annexname.split(",");
        		String[] httpPaths = httpPath.split(",");
        		for(int i = 0;i<paths.length;i++){
        			String annexcode = Uuid32.getUUID32();
        			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
        			zlnfAnnex.setAnnexcode(annexcode);
        			zlnfAnnex.setAnnexpath(paths[i]);
        			zlnfAnnex.setAnnexname(annexnames[i]);
        			zlnfAnnex.setHttppath(httpPaths[i]);
        			if("License".equals(annexnames[i])){
        				zlnfAnnex.setDeptcode(coopcode);
        				zlnfAnnex.setDepttype("5");
        			}else{
        				zlnfAnnex.setDeptcode(usercode);
        				zlnfAnnex.setDepttype("4");
        			}
        			zlnfAnnex.setCreator(userName);
        			zlnfAnnex.setCreatedonutc(date);
        			annexService.create(zlnfAnnex);
        		}
        	}
    	}else{
    		//添加身份证照图片信息
    		String path = zlnfFcustofomerinRequest.getAnnexpath();
    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
    		if(StringUtils.isNotBlank(path)){
        		String[] paths = path.split(",");
        		String[] annexnames = annexname.split(",");
        		String[] httpPaths = httpPath.split(",");
        		for(int i = 0;i<2;i++){
        			String annexcode = Uuid32.getUUID32();
        			ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
        			zlnfAnnex.setDeptcode(usercode);
        			zlnfAnnex.setAnnexcode(annexcode);
        			zlnfAnnex.setAnnexpath(paths[i]);
        			zlnfAnnex.setAnnexname(annexnames[i]);
        			zlnfAnnex.setHttppath(httpPaths[i]);
        			zlnfAnnex.setDepttype("4");
        			zlnfAnnex.setCreator(userName);
        			zlnfAnnex.setCreatedonutc(date);
        			annexService.create(zlnfAnnex);
        		}
        	}
    	}
    	ZlnfCoopInfo.setCoopname(coopname);
    	ZlnfCoopInfo.setCoopcode(coopcode);
    	ZlnfCoopInfo.setVip("0");
    	ZlnfCoopInfo.setSource("0");
    	ZlnfCoopInfo.setIsaudit("1");
    	ZlnfCoopInfo.setCreatedonutc(date);
    	ZlnfCoopInfo.setType(zlnfFcustofomerinRequest.getType());
    	ZlnfCoopInfo.setCreator(userName);
    	ZlnfCoopInfo.setPhone(zlnfFcustofomerinRequest.getMobile());
    	ZlnfCoopInfo.setEnterprise(zlnfFcustofomerinRequest.getEnterprisecreditcode());
    	//添加用户信息
    	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
    	zlnfFcustofomerin.setUsercode(usercode);
    	zlnfFcustofomerin.setMobile(zlnfFcustofomerinRequest.getMobile());
    	zlnfFcustofomerin.setUsername(userName);
    	zlnfFcustofomerin.setLoginname(zlnfFcustofomerinRequest.getMobile());
    	zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
    	zlnfFcustofomerin.setRoletype("2");
    	zlnfFcustofomerin.setCreatedonutc(date);
    	zlnfFcustofomerin.setCreator(userName);
    	zlnfFcustofomerin.setCardnumber(zlnfFcustofomerinRequest.getCardnumber());
    	zlnfFcustofomerin.setIsaudit("1");
    	//添加合作社用户信息
    	ZlnfCoopUser zlnfCoopUser = new ZlnfCoopUser();
    	zlnfCoopUser.setUsercode(usercode);
    	zlnfCoopUser.setCoopcode(coopcode);
    	if("1".equals(zlnfFcustofomerinRequest.getType())){
    		zlnfCoopUser.setRoletype("2");
    	}else if("2".equals(zlnfFcustofomerinRequest.getType())){
    		zlnfCoopUser.setRoletype("3");
    	}
    	zlnfCoopUser.setCreatedonutc(date);
    	zlnfCoopUser.setCreator(userName);
    	zlnfCoopUser.setIsaudit("1");
    	try {
    		coopInfoService.create(ZlnfCoopInfo);
        	customerInfoService.create(zlnfFcustofomerin);
        	coopUserService.create(zlnfCoopUser);
        	ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
        	ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
        	coopExample.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
        	List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
        	fcustofomerin.setZlnfCoopUser(coopList.get(0));
        	ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();//
        	coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
        	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
        	fcustofomerin.setZlnfCoopUser(coopList.get(0));
        	fcustofomerin.setLng(coopInfoList.get(0).getLng());
        	fcustofomerin.setLat(coopInfoList.get(0).getLat());
        	fcustofomerin.setType(coopInfoList.get(0).getType());
        	fcustofomerin.setCoopname(coopInfoList.get(0).getCoopname());
        	fcustofomerin.setVip(coopInfoList.get(0).getVip());
        	if(coopInfoList.get(0).getVip()!=null){
				if("1".equals(coopInfoList.get(0).getVip())){
					fcustofomerin.setProvice(coopInfoList.get(0).getProvice());
					fcustofomerin.setCity(coopInfoList.get(0).getCity());
				}
			}
        	fcustofomerin.setAddress(coopInfoList.get(0).getAddress());
        	result.setData(fcustofomerin);
        	result.setSuccess(true);// 注册合作社成功
    		result.setReturnCode("0000");
    		if("1".equals(zlnfFcustofomerinRequest.getType())){
    			result.setMessage("注册合作社成功");
    		}else{
    			result.setMessage("注册农户成功");
    		}
		} catch (Exception e) {
			result.setSuccess(true);// 注册合作社失败
    		result.setReturnCode("1111");
    		result.setMessage("注册合作社失败");
		}	
        return result;
    }
    
    /**
     * 修改合作社个人资料
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopInfo/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopInfoUpdate(String channel, String key, String data) {
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
        //3、校验该会员是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
        criteria.andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        criteria.andRoletypeEqualTo("2");
        OptResult result = OptResult.success();
        int count = customerInfoService.getResultCount(example);
	    if(count>0){
	      	try {
	        	//修改用户基本信息
	      		Boolean isupdate=true;
	        	String rest=new String();
	        	Date date = new Date();
	        	String username = zlnfFcustofomerinRequest.getUsername();
	        	String nickname = zlnfFcustofomerinRequest.getNickname();
	        	String coopname = zlnfFcustofomerinRequest.getCoopname();
	        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
	        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
	        	zlnfFcustofomerin.setId(list.get(0).getId());
	        	if(StringUtils.isNotBlank(username)){
	        		zlnfFcustofomerin.setUsername(username);
	        		zlnfFcustofomerin.setModifier(username);
	        	}else{
	        		zlnfFcustofomerin.setModifier(list.get(0).getUsername());
	        	}
	        	String sex = zlnfFcustofomerinRequest.getSex();
	        	if(StringUtils.isNotBlank(sex)){
	        		zlnfFcustofomerin.setSex(sex);
	        	}
	        	String loginpwd = zlnfFcustofomerinRequest.getLoginpwd();
	        	if(StringUtils.isNotBlank(loginpwd)){
	        		zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(loginpwd));
	        	}
	        	String mobile = zlnfFcustofomerinRequest.getNewmobile();
	        	if(StringUtils.isNotBlank(mobile)){
	        		zlnfFcustofomerin.setMobile(mobile);
	        		zlnfFcustofomerin.setLoginname(mobile);
	        	}
	        	String photo = zlnfFcustofomerinRequest.getPhoto();
	        	if(StringUtils.isNotBlank(photo)){
	        		zlnfFcustofomerin.setPhoto(photo);
	        	}
	        	String cardnumber = zlnfFcustofomerinRequest.getCardnumber();
	        	if(StringUtils.isNotBlank(cardnumber)){
	        		zlnfFcustofomerin.setCardnumber(cardnumber);
	        	}
	        	String email = zlnfFcustofomerinRequest.getEmail();
	        	if(StringUtils.isNotBlank(email)){
	        		zlnfFcustofomerin.setEmail(email);
	        	}
	        	if(StringUtils.isNotBlank(nickname)){
	        		zlnfFcustofomerin.setNickname(nickname);
	        	}
	        	zlnfFcustofomerin.setUpdatedonutc(date);
	        	//修改合作社用户信息
	        	ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
	        	coopExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
	        	List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
	        	ZlnfCoopUser zlnfCoopUser = new ZlnfCoopUser();
	        	zlnfCoopUser.setId(coopList.get(0).getId());
	        	String roletype = zlnfFcustofomerinRequest.getRoletype2();
	        	if(StringUtils.isNotBlank(roletype)){
	        		zlnfCoopUser.setRoletype(roletype);
	        	}
	        	if(StringUtils.isNotBlank(username)){
	        		zlnfCoopUser.setModifier(username);
	        	}else{
	        		zlnfCoopUser.setModifier(list.get(0).getUsername());
	        	}
	        	zlnfCoopUser.setUpdatedonutc(date);
	        	//修改合作社信息
	        	ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
	        	coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());
	        	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
	        	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
	        	zlnfCoopInfo.setId(coopInfoList.get(0).getId());
	        	if(StringUtils.isNotBlank(coopname)){
	        		zlnfCoopInfo.setCoopname(coopname);
	        	}
	        	String phone = zlnfFcustofomerinRequest.getNewmobile();
	        	if(StringUtils.isNotBlank(phone)){
	        		zlnfCoopInfo.setPhone(phone);
	        	}
	        	String enterprise = zlnfFcustofomerinRequest.getEnterprisecreditcode();
	        	if(StringUtils.isNotBlank(enterprise)){
	        		zlnfCoopInfo.setEnterprise(enterprise);
	        		zlnfCoopInfo.setIsaudit("0");
	        	}
	        	String lng = zlnfFcustofomerinRequest.getLng();
	        	if(StringUtils.isNotBlank(lng)){
	        		zlnfCoopInfo.setLng(lng);
	        	}
	        	String lat = zlnfFcustofomerinRequest.getLat();
	        	if(StringUtils.isNotBlank(lat)){
	        		zlnfCoopInfo.setLat(lat);
	        	}
	        	if(StringUtils.isNotBlank(lng) && StringUtils.isNotBlank(lat)){
	        		String[] arr = GetLocation.getAdd(lng, lat);
	        		//获取合作社跨省修改次数
	        		//查询剩余修改次数
	        		String province=coopInfoList.get(0).getProvice();
	        		String sycs=coopInfoList.get(0).getUpdatecount();
	        		if(province!=null&&province!=""){
	        			if(!arr[0].equals(province)){
	        				if(sycs!=null&&sycs!=""){
	        					//判断是否超过次数
	        					if(Integer.valueOf(sycs)<4){
	        						zlnfCoopInfo.setUpdatecount(String.valueOf(1+Integer.valueOf(sycs)));
	        						//rest=String.valueOf(4-Integer.valueOf(sycs));
	        					}else{
	        						isupdate=false;
	        					}
	        				}else{
	        					zlnfCoopInfo.setUpdatecount("2");
	        					//rest=String.valueOf("2");
	        				}
	        			}else{
	        				if(sycs==null||sycs==""){
	        					zlnfCoopInfo.setUpdatecount("1");
	        					//rest=String.valueOf("3");
	        				}else{
	        					//rest=String.valueOf(4-Integer.valueOf(sycs));
	        				}
	        				
	        			}
	        		}else{
	        			zlnfCoopInfo.setUpdatecount("1");
	        			//rest=String.valueOf("3");
	        		}
	        		zlnfCoopInfo.setProvice(arr[0]);
	        		zlnfCoopInfo.setCity(arr[1]);
	        		DmXzqhExample dmXzqhExample1 = new DmXzqhExample();
	        		dmXzqhExample1.createCriteria().andMcEqualTo(arr[0]);
	        		List<DmXzqh> dmXzqhList1 = dmXzqhService.getResult(dmXzqhExample1);
	        		String provicecode = dmXzqhList1.get(0).getBh();
	        		zlnfCoopInfo.setProvicecode(provicecode);
	        		DmXzqhExample dmXzqhExample2 = new DmXzqhExample();
	        		dmXzqhExample2.createCriteria().andMcEqualTo(arr[1]);
	        		List<DmXzqh> dmXzqhList2 = dmXzqhService.getResult(dmXzqhExample2);
	        		zlnfCoopInfo.setCitycode(dmXzqhList2.get(0).getBh());
	        		if("0".equals(coopInfoList.get(0).getVip())){
	        			ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
	        			ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
	        			foodInfoCriteria.andIsdeletedEqualTo("0");
	        			foodInfoCriteria.andProvicecodeEqualTo(provicecode);
	        			List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
	        			Double distance = (double) 0;
	        			String foodcode = "";
	        			for(int i = 0;i<foodInfoList.size();i++){
	        				if(foodInfoList.get(i).getLng()!=null && foodInfoList.get(i).getLat()!=null){
	        					Double distance1 = GetLocation.GetDistance(Double.valueOf(lng), Double.valueOf(lat), Double.valueOf(foodInfoList.get(i).getLng()), Double.valueOf(foodInfoList.get(i).getLat()));
	        					if(i==0){
	        						distance = distance1;
	        						foodcode = foodInfoList.get(i).getFoodcode();
	        					}else{
	        						if(distance>distance1){
	        							distance = distance1;
	        							foodcode = foodInfoList.get(i).getFoodcode();
	        						}
	        					}
	        				}
	        			}
	        			zlnfCoopInfo.setCreatordeptcode(foodcode);
	        		}
	        	}
	        	String address = zlnfFcustofomerinRequest.getAddress();
	        	if(StringUtils.isNotBlank(address)){
	        		zlnfCoopInfo.setAddress(address);
	        	}
	        	if(StringUtils.isNotBlank(username)){
	        		zlnfCoopInfo.setModifier(username);
	        		zlnfCoopInfo.setCreator(username);
	        	}else{
	        		zlnfCoopInfo.setModifier(list.get(0).getUsername());
	        	}
	        	zlnfCoopInfo.setUpdatedonutc(date);
	        	if(StringUtils.isNotBlank(lng) && StringUtils.isNotBlank(lat) && coopInfoList.get(0).getProvice()!=null){
	        		String[] arr = GetLocation.getAdd(lng, lat);
	        		/*if(!arr[0].equals(coopInfoList.get(0).getProvice())){
	    				result.setSuccess(false);
	    	        	result.setReturnCode("0001");
	    	        	result.setMessage("定位与省不符");
	    	        	return result;
	    			}else{*/
	        		if(!isupdate){
	    				result.setSuccess(false);
	    	        	result.setReturnCode("0001");
	    	        	result.setMessage("修改次数已超限");
	    	        	return result;
	    			}else{
	    				customerInfoService.update(zlnfFcustofomerin);
	            		coopUserService.update(zlnfCoopUser);
	            		coopInfoService.update(zlnfCoopInfo);
	            		ZlnfFcustofomerin zlnfFcustofomerin2=customerInfoService.getResult(example).get(0);
	            		zlnfFcustofomerin2.setRestupdatenum(rest);
	            		zlnfFcustofomerin2.setCity(arr[1]);
	            		result.setSuccess(true);
	            		result.setData(zlnfFcustofomerin2);
	                	result.setReturnCode("0000");
	                	result.setMessage("修改合作社信息成功");
	    			}
	        	}else{
	        		customerInfoService.update(zlnfFcustofomerin);
	        		coopUserService.update(zlnfCoopUser);
	        		coopInfoService.update(zlnfCoopInfo);
	        		ZlnfFcustofomerin zlnfFcustofomerin2=customerInfoService.getResult(example).get(0);
            		zlnfFcustofomerin2.setRestupdatenum("3");
	        		result.setSuccess(true);
	        		result.setData(zlnfFcustofomerin2);
	            	result.setReturnCode("0000");
	            	result.setMessage("修改合作社信息成功");
	        	}
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
	        	result.setReturnCode("1111");
	        	result.setMessage("修改合作社信息失败");
			}
        }else{
        	result.setSuccess(false);
        	result.setReturnCode("0003");
        	result.setMessage("用户不存在");
        }
        return result;
    }
    
    
    
    /**
     * 获取合作社信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopInfo/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopInfoQuery(String channel, String key, String data) {
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
        ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
        ZlnfCoopUserExample coopExample = new ZlnfCoopUserExample();
    	coopExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
    	List<ZlnfCoopUser> coopList = coopUserService.getResult(coopExample);
    	coopInfoExample.createCriteria().andCoopcodeEqualTo(coopList.get(0).getCoopcode());	
        try {
        	List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
        	ZlnfCoopUserExample coopUserExample = new ZlnfCoopUserExample();
    		ZlnfCoopUserExample.Criteria coopUserCriteria = coopUserExample.createCriteria();
    		coopUserCriteria.andCoopcodeEqualTo(coopInfoList.get(0).getCoopcode());
    		coopUserCriteria.andRoletypeNotEqualTo("4");
    		coopUserCriteria.andRoletypeNotEqualTo("5");
    		coopUserCriteria.andIsdeletedEqualTo("0");
    		List<ZlnfCoopUser> coopUserList = coopUserService.getResult(coopUserExample);
    		if(coopUserList.size()>0){
    			ZlnfFcustofomerinExample custofomerinExample = new ZlnfFcustofomerinExample();
    			ZlnfFcustofomerinExample.Criteria custCriteria = custofomerinExample.createCriteria();
    			custCriteria.andIsdeletedEqualTo("0");
    			custCriteria.andUsercodeEqualTo(coopUserList.get(0).getUsercode());
    			List<ZlnfFcustofomerin> custList = customerInfoService.getResult(custofomerinExample);
    			if(custList.size()>0){
    				coopInfoList.get(0).setCreator(custList.get(0).getUsername());
    			}
    		}
    		ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
			annexCriteria.andDeptcodeEqualTo(coopInfoList.get(0).getCoopcode());
			annexCriteria.andDepttypeEqualTo("5");
			annexCriteria.andIsdeletedEqualTo("0");
			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
			if(annexList.size()>0){
				coopInfoList.get(0).setHttppath(annexList.get(0).getHttppath());
			}
			if(coopInfoList.get(0).getProvice()==null||coopInfoList.get(0).getProvice()==""){
				coopInfoList.get(0).setRestupdatenum("4");
			}else if(coopInfoList.get(0).getProvice()!=null&&coopInfoList.get(0).getProvice()!=""&&(coopInfoList.get(0).getUpdatecount()==null||coopInfoList.get(0).getUpdatecount()=="")){
				coopInfoList.get(0).setRestupdatenum("3");
			}else{
				coopInfoList.get(0).setRestupdatenum(String.valueOf(4-Integer.valueOf(coopInfoList.get(0).getUpdatecount())));
			}
			System.out.println("=========================================restupdatenum"+coopInfoList.get(0).getRestupdatenum());
    		result.setSuccess(true);
    		result.setData(coopInfoList.get(0));
        	result.setReturnCode("0000");
        	result.setMessage("获取合作社信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取合作社信息失败");
		}
        return result;
    }

    
    /**
     * 获取合作社分页信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/coopInfo/queryRownum"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult coopInfoQueryRownum(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfCoopInfoRequest zlnfCoopInfoRequest  = convert(data, ZlnfCoopInfoRequest.class);
    	// 2. 业务检查
    	if (zlnfCoopInfoRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
    	zlnfCoopInfo.setNumber(zlnfCoopInfoRequest.getNumber());
    	String coopname = zlnfCoopInfoRequest.getCoopname();
    	if(StringUtils.isNotBlank(coopname)){
    		zlnfCoopInfo.setCoopname(coopname);
    	}
    	try {
    		List<ZlnfCoopInfo> coopInfoList = coopInfoService.queryRownum(zlnfCoopInfo);
    		result.setSuccess(true);
    		result.setData(coopInfoList);
    		result.setReturnCode("0000");
    		result.setMessage("获取合作社信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取合作社信息失败");
    	}
    	return result;
    }

}
