package com.vt.fengci.zlnf.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.vt.base.util.DesUtil;
import com.vt.base.util.GetLocation;
import com.vt.base.util.Uuid32;
import com.vt.fencing.model.DmXzqh;
import com.vt.fencing.model.DmXzqhExample;
import com.vt.fencing.model.ZlnfAnnex;
import com.vt.fencing.model.ZlnfAnnexExample;
import com.vt.fencing.model.ZlnfCoopInfo;
import com.vt.fencing.model.ZlnfCoopInfoExample;
import com.vt.fencing.model.ZlnfDictInfo;
import com.vt.fencing.model.ZlnfDictInfoExample;
import com.vt.fencing.model.ZlnfFarmMachineyMerchant;
import com.vt.fencing.model.ZlnfFarmMachineyMerchantExample;
import com.vt.fencing.model.ZlnfFarmServiceMachiney;
import com.vt.fencing.model.ZlnfFarmServiceMachineyExample;
import com.vt.fencing.model.ZlnfFarmmachineyUser;
import com.vt.fencing.model.ZlnfFarmmachineyUserExample;
import com.vt.fencing.model.ZlnfFcustofomerin;
import com.vt.fencing.model.ZlnfFcustofomerinExample;
import com.vt.fencing.model.ZlnfFieldInfo;
import com.vt.fencing.model.ZlnfFieldInfoExample;
import com.vt.fencing.model.ZlnfFoodInfo;
import com.vt.fencing.model.ZlnfFoodInfoExample;
import com.vt.fencing.model.ZlnfServiceProgram;
import com.vt.fencing.model.ZlnfServiceProgramExample;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fencing.model.ZlnfUserdiscuss;
import com.vt.fencing.model.ZlnfUserdiscussExample;
import com.vt.fencing.request.ZlnfFarmMachineyMerchantRequest;
import com.vt.fencing.request.ZlnfFcustofomerinRequest;
import com.vt.fengci.zlnf.service.IAnnexService;
import com.vt.fengci.zlnf.service.ICoopInfoService;
import com.vt.fengci.zlnf.service.ICustomerInfoService;
import com.vt.fengci.zlnf.service.IDictInfoService;
import com.vt.fengci.zlnf.service.IDmXzqhService;
import com.vt.fengci.zlnf.service.IFarmMachineyMerchantService;
import com.vt.fengci.zlnf.service.IFarmMachineyService;
import com.vt.fengci.zlnf.service.IFarmServiceMachineyService;
import com.vt.fengci.zlnf.service.IFieldInfoService;
import com.vt.fengci.zlnf.service.IFoodInfoService;
import com.vt.fengci.zlnf.service.IServiceProgramService;
import com.vt.fengci.zlnf.service.ITakeawayOrderService;
import com.vt.fengci.zlnf.service.IUserDiscussService;

/**
 * user related functions
 * Created by john on 17/7/17.
 */
@Controller
public class FarmMachineyMerchantController extends BaseGatewayController {

	private static final long serialVersionUID = 6914987369371169586L;

	/**
     * member service
     */
	@Autowired
	private IFarmMachineyMerchantService farmMachineyMerchantService;
	
    @Autowired
    private IFarmMachineyService farmMachineyService;
    
    @Autowired
    private ICustomerInfoService customerInfoService;
    
    @Autowired
    private IAnnexService annexService;
    
    @Autowired
    private IUserDiscussService userDiscussService;
    
    @Autowired
    private IServiceProgramService serviceProgramService;
    
    @Autowired
    private ITakeawayOrderService takeawayOrderService;
    
    @Autowired
    private IFarmServiceMachineyService farmServiceMachineyService;
    
    @Autowired
    private ICoopInfoService coopInfoService;
    
    @Autowired
    private IFieldInfoService fieldInfoService;
    
    @Autowired
    private IDictInfoService dictInfoService;
    
    @Autowired
    private IDmXzqhService dmXzqhService;
    
    @Autowired
    private IFoodInfoService foodInfoService;
    
    
    /**
     * 注册农机服务商
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult armMachineyMerchantCreate(String channel, String key, String data) {
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
        //3、校验该用户是否存在
        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
        example.createCriteria().andMobileEqualTo(zlnfFcustofomerinRequest.getMobile());
        int count = customerInfoService.getResultCount(example);
        OptResult result = OptResult.success();
        if(count>0){
        	result.setData(false);
        	result.setReturnCode("0002");
        }else{
        	Date date = new Date();
        	String merchantcode = Uuid32.getUUID32();
        	String usercode = Uuid32.getUUID32();
        	//添加农机服务商信息
        	ZlnfFarmMachineyMerchant ZlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
        	ZlnfFarmMachineyMerchant.setMerchantcode(merchantcode);
        	String userName = zlnfFcustofomerinRequest.getUsername();
        	String farmmachineyname = zlnfFcustofomerinRequest.getFarmmachineyname();
        	String businesslicence = "";
        	if("1".equals(zlnfFcustofomerinRequest.getType())){
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
            				zlnfAnnex.setDeptcode(merchantcode);
            				zlnfAnnex.setDepttype("1");
            				businesslicence = annexcode;
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
        	ZlnfFarmMachineyMerchant.setMerchantname(farmmachineyname);
        	ZlnfFarmMachineyMerchant.setEnterprise(zlnfFcustofomerinRequest.getEnterprisecreditcode());
        	ZlnfFarmMachineyMerchant.setBusinesslicence(businesslicence);
        	ZlnfFarmMachineyMerchant.setRegisttype(zlnfFcustofomerinRequest.getType());
        	ZlnfFarmMachineyMerchant.setLinkname(userName);
        	ZlnfFarmMachineyMerchant.setPhone(zlnfFcustofomerinRequest.getMobile());
        	ZlnfFarmMachineyMerchant.setCreator(userName);
        	ZlnfFarmMachineyMerchant.setCreatedonutc(date);
        	//添加用户信息
        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
        	zlnfFcustofomerin.setUsercode(usercode);
        	zlnfFcustofomerin.setMobile(zlnfFcustofomerinRequest.getMobile());
        	zlnfFcustofomerin.setUsername(userName);
        	zlnfFcustofomerin.setRoletype("1");
        	zlnfFcustofomerin.setLoginname(zlnfFcustofomerinRequest.getMobile());
        	zlnfFcustofomerin.setLoginpwd(DesUtil.desCrypto(zlnfFcustofomerinRequest.getLoginpwd()));
        	zlnfFcustofomerin.setCreatedonutc(date);
        	zlnfFcustofomerin.setCreator(userName);
        	zlnfFcustofomerin.setCardnumber(zlnfFcustofomerinRequest.getCardnumber());
        	zlnfFcustofomerin.setIsaudit("1");
        	//添加农机服务商用户信息
        	ZlnfFarmmachineyUser zlnfFarmmachineyUser = new ZlnfFarmmachineyUser();
        	zlnfFarmmachineyUser.setUsercode(usercode);
        	zlnfFarmmachineyUser.setFarmmachineyname(farmmachineyname);
        	if("1".equals(zlnfFcustofomerinRequest.getType())){
        		zlnfFarmmachineyUser.setRoletype("2");
        	}else if("2".equals(zlnfFcustofomerinRequest.getType())){
        		zlnfFarmmachineyUser.setRoletype("5");
        	}
        	zlnfFarmmachineyUser.setIsaudit("1");
        	zlnfFarmmachineyUser.setFarmmachineycode(merchantcode);
        	zlnfFarmmachineyUser.setCreatedonutc(new Date());
        	try {
        		farmMachineyMerchantService.create(ZlnfFarmMachineyMerchant);
            	customerInfoService.create(zlnfFcustofomerin);
            	farmMachineyService.create(zlnfFarmmachineyUser);
            	ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
            	ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
        		farmExample.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
        		List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample);
        		ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
    			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
    			annexCriteria.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
    			annexCriteria.andDepttypeEqualTo("1");
    			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
    			if(annexList.size()>0){
    				farmList.get(0).setHttppath(annexList.get(0).getHttppath());
    			}
    			fcustofomerin.setRoletype1(farmList.get(0).getRoletype());
    			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
    			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
    			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
    			farmList.get(0).setEnterprise(machineyList.get(0).getEnterprise());
    			fcustofomerin.setZlnfFarmmachineyUser(farmList.get(0));
    			fcustofomerin.setLng(machineyList.get(0).getLng());
    			fcustofomerin.setLat(machineyList.get(0).getLat());
    			fcustofomerin.setType(machineyList.get(0).getRegisttype());
    			fcustofomerin.setMerchantname(machineyList.get(0).getMerchantname());
            	result.setData(fcustofomerin);
            	result.setSuccess(true);// 注册农机服务商成功
        		result.setReturnCode("0000");
        		result.setMessage("注册农机服务商成功");
			} catch (Exception e) {
				result.setSuccess(true);// 注册农机服务商失败
        		result.setReturnCode("1111");
        		result.setMessage("注册农机服务商失败");
			}
        	
        }
        return result;
    }
    
    
    /**
     * 修改农机服务商资料
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyMerchantUpdate(String channel, String key, String data) {
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
        System.out.println("==============================================>1");
        //3、校验该会员是否存在
        OptResult result = OptResult.success();
        try {
	        ZlnfFcustofomerinExample example = new ZlnfFcustofomerinExample();
	        ZlnfFcustofomerinExample.Criteria criteria = example.createCriteria();
	        criteria.andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
	        criteria.andRoletypeEqualTo("1");
	        int count = customerInfoService.getResultCount(example);
	        System.out.println("==============================================>2");
	        if(count>0){
	        	Boolean isupdate=true;
	        	String rest=new String();
	        	Date date = new Date();
	        	String username = zlnfFcustofomerinRequest.getUsername();
	        	String nickname = zlnfFcustofomerinRequest.getNickname();
	        	String farmmachineyname = zlnfFcustofomerinRequest.getFarmmachineyname();
	        	//修改用户基本信息
	        	List<ZlnfFcustofomerin> list = customerInfoService.getResult(example);
	        	ZlnfFcustofomerin zlnfFcustofomerin = new ZlnfFcustofomerin();
	        	System.out.println("==============================================>3");
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
	        	System.out.println("==============================================>4");
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
	        	//修改农机服务商用户信息
	        	System.out.println("==============================================>5");
	        	ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
	        	farmExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
	        	List<ZlnfFarmmachineyUser> farmlist = farmMachineyService.getResult(farmExample);
	        	System.out.println("==============================================>6");
	        	ZlnfFarmmachineyUser zlnfFarmmachineyUser = new ZlnfFarmmachineyUser();
	        	zlnfFarmmachineyUser.setId(farmlist.get(0).getId());
	        	String roletype = zlnfFcustofomerinRequest.getRoletype1();
	        	if(StringUtils.isNotBlank(roletype)){
	        		zlnfFarmmachineyUser.setRoletype(roletype);
	        	}
	        	if(StringUtils.isNotBlank(farmmachineyname)){
	        		zlnfFarmmachineyUser.setFarmmachineyname(farmmachineyname);
	        	}
	        	if(StringUtils.isNotBlank(username)){
	        		zlnfFarmmachineyUser.setModifier(username);
	        	}else{
	        		zlnfFarmmachineyUser.setModifier(list.get(0).getUsername());
	        	}
	        	zlnfFarmmachineyUser.setUpdatedonutc(date);
	        	System.out.println("==============================================>7");
	        	//修改农机服务商信息
	        	ZlnfFarmMachineyMerchantExample farmMachineytExample = new ZlnfFarmMachineyMerchantExample();
	        	farmMachineytExample.createCriteria().andMerchantcodeEqualTo(farmlist.get(0).getFarmmachineycode());
	        	List<ZlnfFarmMachineyMerchant> farmMachineyList = farmMachineyMerchantService.getResult(farmMachineytExample);
	        	ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
	        	System.out.println("==============================================>8");
	        	zlnfFarmMachineyMerchant.setId(farmMachineyList.get(0).getId());
	        	if(StringUtils.isNotBlank(farmmachineyname)){
	        		zlnfFarmMachineyMerchant.setMerchantname(farmmachineyname);
	        	}
	        	String enterprisecreditcode = zlnfFcustofomerinRequest.getEnterprisecreditcode();
	        	if(StringUtils.isNotBlank(enterprisecreditcode)){
	        		zlnfFarmMachineyMerchant.setEnterprise(enterprisecreditcode);
	        		zlnfFarmMachineyMerchant.setIsaudit("0");
	        	}
	        	String lng = zlnfFcustofomerinRequest.getLng();
	        	if(StringUtils.isNotBlank(lng)){
	        		zlnfFarmMachineyMerchant.setLng(lng);
	        	}
	        	String lat = zlnfFcustofomerinRequest.getLat();
	        	if(StringUtils.isNotBlank(lat)){
	        		zlnfFarmMachineyMerchant.setLat(lat);
	        	}
	        	if("2".equals(farmlist.get(0).getRoletype())){
	        		if(StringUtils.isNotBlank(username)){
	        			zlnfFarmMachineyMerchant.setLinkname(username);
	        		}
	        	}
	        	System.out.println("==============================================>9");
	        	if(StringUtils.isNotBlank(lng) && StringUtils.isNotBlank(lat)){
	        		String[] arr = GetLocation.getAdd(lng, lat);
	        		//获取服务商跨省修改次数
	        		//查询剩余修改次数
	        		String province=farmMachineyList.get(0).getProvice();
	        		String sycs=farmMachineyList.get(0).getUpdatecount();
	        		if(province!=null&&province!=""){
	        			if(!arr[0].equals(province)){
	        				if(sycs!=null&&sycs!=""){
	        					//判断是否超过次数
	        					if(Integer.valueOf(sycs)<4){
	        						zlnfFarmMachineyMerchant.setUpdatecount(String.valueOf(1+Integer.valueOf(sycs)));
	        						//rest=String.valueOf(4-Integer.valueOf(sycs));
	        					}else{
	        						isupdate=false;
	        					}
	        				}else{
	        					zlnfFarmMachineyMerchant.setUpdatecount("2");
	        					//rest=String.valueOf("2");
	        				}
	        			}else{
	        				if(sycs==null||sycs==""){
	        					zlnfFarmMachineyMerchant.setUpdatecount("1");
	        					//rest=String.valueOf("3");
	        				}else{
	        					//rest=String.valueOf(4-Integer.valueOf(sycs));
	        				}
	        				
	        			}
	        		}else{
	        			zlnfFarmMachineyMerchant.setUpdatecount("1");
	        			//rest=String.valueOf("3");
	        		}
	        		zlnfFarmMachineyMerchant.setProvice(arr[0]);
	        		zlnfFarmMachineyMerchant.setCity(arr[1]);
	        		DmXzqhExample dmXzqhExample1 = new DmXzqhExample();
	        		dmXzqhExample1.createCriteria().andMcEqualTo(arr[0]);
	        		List<DmXzqh> dmXzqhList1 = dmXzqhService.getResult(dmXzqhExample1);
	        		String provicecode = dmXzqhList1.get(0).getBh();
	        		zlnfFarmMachineyMerchant.setProvicecode(provicecode);
	        		DmXzqhExample dmXzqhExample2 = new DmXzqhExample();
	        		dmXzqhExample2.createCriteria().andMcEqualTo(arr[1]);
	        		List<DmXzqh> dmXzqhList2 = dmXzqhService.getResult(dmXzqhExample2);
	        		zlnfFarmMachineyMerchant.setCitycode(dmXzqhList2.get(0).getBh());
	        		ZlnfFoodInfoExample foodInfoExample = new ZlnfFoodInfoExample();
		    		ZlnfFoodInfoExample.Criteria foodInfoCriteria = foodInfoExample.createCriteria();
		    		foodInfoCriteria.andIsdeletedEqualTo("0");
		    		foodInfoCriteria.andProvicecodeEqualTo(provicecode);
		    		List<ZlnfFoodInfo> foodInfoList = foodInfoService.getResult(foodInfoExample);
		    		Double distance = (double) 0;
		    		String foodcode = "";
		    		System.out.println("==============================================>10");
		    		System.out.println("==============================================>foodInfoList"+foodInfoList);
		    		for(int i = 0;i<foodInfoList.size();i++){
		    			if(foodInfoList.get(i).getLng()!=null && foodInfoList.get(i).getLat()!=null){
		    				Double distance1 = GetLocation.GetDistance(Double.valueOf(lng), Double.valueOf(lat), Double.valueOf(foodInfoList.get(i).getLng()), Double.valueOf(foodInfoList.get(i).getLat()));
			    			System.out.println("==============================================>i"+i);
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
		    		System.out.println("==============================================>11");
		    		zlnfFarmMachineyMerchant.setFoodcode(foodcode);
	        	}
	        	String address = zlnfFcustofomerinRequest.getAddress();
	        	if(StringUtils.isNotBlank(address)){
	        		zlnfFarmMachineyMerchant.setAddress(address);
	        	}
	        	if(StringUtils.isNotBlank(username)){
	        		zlnfFarmMachineyMerchant.setModifier(username);
	        	}else{
	        		zlnfFarmMachineyMerchant.setModifier(list.get(0).getUsername());
	        	}
	        	zlnfFarmMachineyMerchant.setUpdatedonutc(date);
	        	System.out.println("==============================================>12");
	        	//修改营业执照
	        	ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
	        	ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
	        	annexCriteria.andDeptcodeEqualTo(farmlist.get(0).getFarmmachineycode());
	        	annexCriteria.andDepttypeEqualTo("1");
	        	List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
	        	String path = zlnfFcustofomerinRequest.getAnnexpath();
	    		String annexname = zlnfFcustofomerinRequest.getAnnexname();
	    		String httpPath = zlnfFcustofomerinRequest.getHttpPath();
	    		System.out.println("==============================================>13");
	    		if(StringUtils.isNotBlank(path)){
	    			for(int i = 0;i<annexList.size();i++){
	    				File file = new File(annexList.get(i).getAnnexpath());
	            		file.delete();
	            		String[] paths = path.split(",");
	            		String[] annexnames = annexname.split(",");
	            		String[] httpPaths = httpPath.split(",");
	            		ZlnfAnnex zlnfAnnex = new ZlnfAnnex();
	            		zlnfAnnex.setId(annexList.get(i).getId());
	            		for(int j = 0;j<paths.length;j++){
	            			if(annexList.get(i).getAnnexname().equals(annexnames[j])){
	            				zlnfAnnex.setAnnexpath(paths[j]);
	            				zlnfAnnex.setHttppath(httpPaths[j]);
	            			}
	            		}
	            		if(StringUtils.isNotBlank(username)){
	            			zlnfAnnex.setModifier(username);
	                	}else{
	                		zlnfAnnex.setModifier(list.get(0).getUsername());
	                	}
	            		zlnfAnnex.setUpdatedonutc(date);
	        			annexService.update(zlnfAnnex);
	    			}
	        	}
	    		System.out.println("==============================================>14");
            	if(StringUtils.isNotBlank(lng) && StringUtils.isNotBlank(lat) /*&& list.get(0).getProvice()!=null*/){
	        		String[] arr = GetLocation.getAdd(lng, lat);
	        		/*if(!arr[0].equals(farmMachineyList.get(0).getProvice())){
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
	    				System.out.println("==============================================>15");
	    				customerInfoService.update(zlnfFcustofomerin);
	            		farmMachineyService.update(zlnfFarmmachineyUser);
	            		farmMachineyMerchantService.update(zlnfFarmMachineyMerchant);
	            		ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
	                	ZlnfFarmmachineyUserExample farmExample1 = new ZlnfFarmmachineyUserExample();
	                	farmExample1.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
	            		List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample1);
	            		ZlnfAnnexExample annexExample1 = new ZlnfAnnexExample();
	        			ZlnfAnnexExample.Criteria annexCriteria1 = annexExample1.createCriteria();
	        			annexCriteria1.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
	        			annexCriteria1.andDepttypeEqualTo("1");
	        			List<ZlnfAnnex> annexList1 = annexService.getResult(annexExample1);
	        			if(annexList.size()>0){
	        				farmList.get(0).setHttppath(annexList1.get(0).getHttppath());
	        			}
	        			fcustofomerin.setRoletype1(farmList.get(0).getRoletype());
	        			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
	        			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
	        			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
	        			farmList.get(0).setEnterprise(machineyList.get(0).getEnterprise());
	        			fcustofomerin.setZlnfFarmmachineyUser(farmList.get(0));
	        			fcustofomerin.setLng(machineyList.get(0).getLng());
	        			fcustofomerin.setLat(machineyList.get(0).getLat());
	        			fcustofomerin.setType(machineyList.get(0).getRegisttype());
	        			fcustofomerin.setMerchantname(machineyList.get(0).getMerchantname());
	        			fcustofomerin.setRestupdatenum(rest);
	            		result.setSuccess(true);
	            		result.setData(fcustofomerin);
	                	result.setReturnCode("0000");
	                	result.setMessage("修改农机服务商信息成功");
	                	System.out.println("==============================================>16");
	    			}
	        	}else{
	        		System.out.println("==============================================>17");
	        		customerInfoService.update(zlnfFcustofomerin);
	        		farmMachineyService.update(zlnfFarmmachineyUser);
	        		farmMachineyMerchantService.update(zlnfFarmMachineyMerchant);
	        		ZlnfFcustofomerin fcustofomerin = customerInfoService.getResult(example).get(0);
	            	ZlnfFarmmachineyUserExample farmExample1 = new ZlnfFarmmachineyUserExample();
	            	farmExample1.createCriteria().andUsercodeEqualTo(fcustofomerin.getUsercode());
	        		List<ZlnfFarmmachineyUser> farmList = farmMachineyService.getResult(farmExample1);
	        		ZlnfAnnexExample annexExample1 = new ZlnfAnnexExample();
	    			ZlnfAnnexExample.Criteria annexCriteria1 = annexExample1.createCriteria();
	    			annexCriteria1.andDeptcodeEqualTo(farmList.get(0).getFarmmachineycode());
	    			annexCriteria1.andDepttypeEqualTo("1");
	    			List<ZlnfAnnex> annexList1 = annexService.getResult(annexExample1);
	    			if(annexList.size()>0){
	    				farmList.get(0).setHttppath(annexList1.get(0).getHttppath());
	    			}
	    			System.out.println("==============================================>18");
	    			fcustofomerin.setRoletype1(farmList.get(0).getRoletype());
	    			ZlnfFarmMachineyMerchantExample farmMachineyMerchantExample = new ZlnfFarmMachineyMerchantExample();
	    			farmMachineyMerchantExample.createCriteria().andMerchantcodeEqualTo(farmList.get(0).getFarmmachineycode());
	    			List<ZlnfFarmMachineyMerchant> machineyList = farmMachineyMerchantService.getResult(farmMachineyMerchantExample);
	    			farmList.get(0).setEnterprise(machineyList.get(0).getEnterprise());
	    			fcustofomerin.setZlnfFarmmachineyUser(farmList.get(0));
	    			fcustofomerin.setLng(machineyList.get(0).getLng());
	    			fcustofomerin.setLat(machineyList.get(0).getLat());
	    			fcustofomerin.setType(machineyList.get(0).getRegisttype());
	    			fcustofomerin.setMerchantname(machineyList.get(0).getMerchantname());
	    			fcustofomerin.setRestupdatenum("3");
	    			System.out.println("==============================================>19");
	        		result.setSuccess(true);
	        		result.setData(fcustofomerin);
	            	result.setReturnCode("0000");
	            	result.setMessage("修改农机服务商信息成功");
	        	}
	        }else{
	        	result.setSuccess(false);
	        	result.setReturnCode("0003");
	        	result.setMessage("用户不存在");
	        }
        } catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("修改农机服务商信息失败");
		}
        return result;
    }
    
    /**
     * 获取农机服务商信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult armMachineyMerchantQuery(String channel, String key, String data) {
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
        ZlnfFarmMachineyMerchantExample farmMachineytExample = new ZlnfFarmMachineyMerchantExample();
    	if(zlnfFcustofomerinRequest.getFarmmachineyname()!=null){
    		farmMachineytExample.createCriteria().andRegisttypeEqualTo("1");
    		try {
    			String farmmachineyname = new String(zlnfFcustofomerinRequest.getFarmmachineyname().getBytes("ISO-8859-1"), "UTF-8");
    			String farmmachineyname1 = "%"+farmmachineyname+"%";
    			farmMachineytExample.createCriteria().andMerchantnameLike(farmmachineyname1);
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	}
    	if(zlnfFcustofomerinRequest.getUsercode()!=null){
    		ZlnfFarmmachineyUserExample farmExample = new ZlnfFarmmachineyUserExample();
        	farmExample.createCriteria().andUsercodeEqualTo(zlnfFcustofomerinRequest.getUsercode());
        	List<ZlnfFarmmachineyUser> farmlist = farmMachineyService.getResult(farmExample);
        	farmMachineytExample.createCriteria().andMerchantcodeEqualTo(farmlist.get(0).getFarmmachineycode());
    	}
    	try {
    		List<ZlnfFarmMachineyMerchant> farmMachineyList = farmMachineyMerchantService.getResult(farmMachineytExample);
    		ZlnfAnnexExample annexExample = new ZlnfAnnexExample();
			ZlnfAnnexExample.Criteria annexCriteria = annexExample.createCriteria();
			annexCriteria.andDeptcodeEqualTo(farmMachineyList.get(0).getMerchantcode());
			annexCriteria.andDepttypeEqualTo("1");
			annexCriteria.andIsdeletedEqualTo("0");
			List<ZlnfAnnex> annexList = annexService.getResult(annexExample);
			if(annexList.size()>0){
				farmMachineyList.get(0).setHttppath(annexList.get(0).getHttppath());
			}
			if(farmMachineyList.get(0).getProvice()==null||farmMachineyList.get(0).getProvice()==""){
				farmMachineyList.get(0).setRestupdatenum("4");
			}else if(farmMachineyList.get(0).getProvice()!=null&&farmMachineyList.get(0).getProvice()!=""&&(farmMachineyList.get(0).getUpdatecount()==null||farmMachineyList.get(0).getUpdatecount()=="")){
				farmMachineyList.get(0).setRestupdatenum("3");
			}else{
				farmMachineyList.get(0).setRestupdatenum(String.valueOf(4-Integer.valueOf(farmMachineyList.get(0).getUpdatecount())));
			}
    		result.setSuccess(true);
    		result.setData(farmMachineyList.get(0));
        	result.setReturnCode("0000");
        	result.setMessage("获取农机服务商信息成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
        	result.setReturnCode("1111");
        	result.setMessage("获取农机服务商信息失败");
		}
        return result;
    }
    
    
    /**
     * 获取服务商分页信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/queryRownumFarm"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyMerchantRownumQueryRownumFarm(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFarmMachineyMerchantRequest zlnfFarmMachineyMerchantRequest  = convert(data, ZlnfFarmMachineyMerchantRequest.class);
    	// 2. 业务检查
    	if (zlnfFarmMachineyMerchantRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
    	zlnfFarmMachineyMerchant.setNumber(zlnfFarmMachineyMerchantRequest.getNumber());
    	String merchantname = zlnfFarmMachineyMerchantRequest.getMerchantname();
    	if(StringUtils.isNotBlank(merchantname)){
    		zlnfFarmMachineyMerchant.setMerchantname(merchantname);
    	}
    	try {
    		List<ZlnfFarmMachineyMerchant> farmMachineyMerchantList = farmMachineyMerchantService.queryRownumFarm(zlnfFarmMachineyMerchant);
    		result.setSuccess(true);
    		result.setData(farmMachineyMerchantList);
    		result.setReturnCode("0000");
    		result.setMessage("获取农机服务商信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取农机服务商信息失败");
    	}
    	return result;
    }

    /**
     * 获取合作社附近20个服务商信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/selectLesstanTwenty"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyMerchantSelectLesstanTwenty(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFarmMachineyMerchantRequest zlnfFarmMachineyMerchantRequest  = convert(data, ZlnfFarmMachineyMerchantRequest.class);
    	// 2. 业务检查
    	if (zlnfFarmMachineyMerchantRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfFieldInfoExample fieldInfoExample = new ZlnfFieldInfoExample();
    		ZlnfFieldInfoExample.Criteria coopInfoCriteria = fieldInfoExample.createCriteria();
    		coopInfoCriteria.andIsdeletedEqualTo("0");
    		coopInfoCriteria.andCoopcodeEqualTo(zlnfFarmMachineyMerchantRequest.getCoopcode());
    		String roletype = zlnfFarmMachineyMerchantRequest.getRoletype();
    		if(!"5".equals(roletype) && !"1".equals(roletype) && !"2".equals(roletype)){
    			coopInfoCriteria.andCreatorcodeEqualTo(zlnfFarmMachineyMerchantRequest.getUsercode());
    		}
    		List<ZlnfFieldInfo> fieldInfoList = fieldInfoService.getResult(fieldInfoExample);
    		for(int i = 0;i<fieldInfoList.size();i++){
    			ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
				ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
				criteria.andIsdeletedEqualTo("0");
				criteria.andDictcodeEqualTo(fieldInfoList.get(i).getCrops());
				List<ZlnfDictInfo> dictInfoList2 = dictInfoService.getResult(dictInfoExample);
				if(dictInfoList2.size()>0){
					fieldInfoList.get(i).setCrops(dictInfoList2.get(0).getDictname());
				}
				if("5".equals(roletype) || "1".equals(roletype) || "2".equals(roletype)){
    				if(zlnfFarmMachineyMerchantRequest.getUsercode().equals(fieldInfoList.get(i).getCreatorcode())){
    					fieldInfoList.get(i).setIsmyorder("1");
    				}else{
    					fieldInfoList.get(i).setIsmyorder("0");
    				}
    			}
    		}
    		ZlnfCoopInfoExample coopInfoExample = new ZlnfCoopInfoExample();
    		coopInfoExample.createCriteria().andCoopcodeEqualTo(zlnfFarmMachineyMerchantRequest.getCoopcode());
    		List<ZlnfCoopInfo> coopInfoList = coopInfoService.getResult(coopInfoExample);
    		ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
    		zlnfFarmMachineyMerchant.setUsercode(zlnfFarmMachineyMerchantRequest.getUsercode());
    		zlnfFarmMachineyMerchant.setLng(zlnfFarmMachineyMerchantRequest.getLng());
    		zlnfFarmMachineyMerchant.setLat(zlnfFarmMachineyMerchantRequest.getLat());
    		List<ZlnfFarmMachineyMerchant> farmMachineyMerchantList = farmMachineyMerchantService.selectLesstanTwenty(zlnfFarmMachineyMerchant);
    		for(int i = 0;i<farmMachineyMerchantList.size();i++){
    			//获取服务商评价星级
    			ZlnfUserdiscussExample zlnfUserdiscussExample = new ZlnfUserdiscussExample();
    			zlnfUserdiscussExample.createCriteria().andMerchantcodeEqualTo(farmMachineyMerchantList.get(i).getMerchantcode());
    			List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(zlnfUserdiscussExample);
    			int count = 0;
    			int sum = 0;
    			Double dislevelInt = (double) 0;
    			for(int j = 0;j<userdiscussList.size();j++){
    				count = count + Integer.valueOf(userdiscussList.get(j).getDislevel());
    				sum++;
    			}
    			if(count!=0 && sum!=0){
    				dislevelInt = Double.valueOf(count/sum);
    			}
    			farmMachineyMerchantList.get(i).setDislevel(Integer.valueOf(String.valueOf(Math.round(dislevelInt))));
    			//获取服务商提供服务
    			ZlnfServiceProgramExample serviceProgramExample = new ZlnfServiceProgramExample();
    			ZlnfServiceProgramExample.Criteria criteria = serviceProgramExample.createCriteria();
    			criteria.andIsdeletedEqualTo("0");
    			criteria.andMerchantcodeEqualTo(farmMachineyMerchantList.get(i).getMerchantcode());
    			List<ZlnfServiceProgram> serviceProgramList = serviceProgramService.getResult(serviceProgramExample);
    			String agmatyprns = "";
    			List<String> listStr = new ArrayList<>();
    			for(int j = 0;j<serviceProgramList.size();j++){
    				Boolean bo = true;
    				if(listStr.size()>0){
    					for(int k = 0;k<listStr.size();k++){
    						if(listStr.get(k).equals(serviceProgramList.get(j).getAgmatyprn())){
    							bo = false;
    							break;
    						}
    					}
    					if(bo){
    						listStr.add(serviceProgramList.get(j).getAgmatyprn());
    					}
    				}
    				if(j == 0){
    					listStr.add(serviceProgramList.get(j).getAgmatyprn());
    				}
    			}
    			for(int j = 0;j<listStr.size();j++){
    				agmatyprns = agmatyprns + listStr.get(j) + ",";
    			}
    			if(agmatyprns.length()>0){
    				farmMachineyMerchantList.get(i).setAgmatyprns(agmatyprns.substring(0, agmatyprns.length()-1));
    			}else{
    				farmMachineyMerchantList.get(i).setAgmatyprns(agmatyprns);
    			}
    			//获取订单完成数量
    			ZlnfTakeawayOrderExample takeawayOrderExample = new ZlnfTakeawayOrderExample();
    			ZlnfTakeawayOrderExample.Criteria criteriaTake = takeawayOrderExample.createCriteria();
    			criteriaTake.andIsdeletedEqualTo("0");
    			criteriaTake.andMerchantcodeEqualTo(farmMachineyMerchantList.get(i).getMerchantcode());
    			criteriaTake.andStatusEqualTo("11");
    			List<ZlnfTakeawayOrder> takeawayOrderList = takeawayOrderService.getResult(takeawayOrderExample);
    			farmMachineyMerchantList.get(i).setOrderCount(String.valueOf(takeawayOrderList.size()));
    			//查询距离合作社距离
    			Double distancedb =GetLocation.GetDistance(Double.valueOf(coopInfoList.get(0).getLng()), Double.valueOf(coopInfoList.get(0).getLat()), Double.valueOf(farmMachineyMerchantList.get(i).getLng()), Double.valueOf(farmMachineyMerchantList.get(i).getLat()));
    			farmMachineyMerchantList.get(i).setDistance(String.valueOf(distancedb));
    		}
    		Map<String, Object> map = new HashMap<>();
    		map.put("farmMachineyMerchantList", farmMachineyMerchantList);
    		map.put("fieldInfoList", fieldInfoList);
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("获取农机服务商信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取农机服务商信息失败");
    	}
    	return result;
    }

    /**
     * 获取服务商附近10个服务商，10个合作社信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/farmMachineyMerchant/selectLesstanTen"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult farmMachineyMerchantSelectLesstanTen(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfFarmMachineyMerchantRequest zlnfFarmMachineyMerchantRequest  = convert(data, ZlnfFarmMachineyMerchantRequest.class);
    	// 2. 业务检查
    	if (zlnfFarmMachineyMerchantRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	ZlnfFarmMachineyMerchant zlnfFarmMachineyMerchant = new ZlnfFarmMachineyMerchant();
    	zlnfFarmMachineyMerchant.setUsercode(zlnfFarmMachineyMerchantRequest.getUsercode());
    	ZlnfCoopInfo zlnfCoopInfo = new ZlnfCoopInfo();
    	zlnfCoopInfo.setUsercode(zlnfFarmMachineyMerchantRequest.getUsercode());
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		//获取农机服务商信息
    		List<ZlnfFarmMachineyMerchant> farmMachineyMerchantList = farmMachineyMerchantService.selectLesstanTen(zlnfFarmMachineyMerchant);
    		for(int i = 0;i<farmMachineyMerchantList.size();i++){
    			//获取服务商评价星级
    			ZlnfUserdiscussExample zlnfUserdiscussExample = new ZlnfUserdiscussExample();
    			zlnfUserdiscussExample.createCriteria().andMerchantcodeEqualTo(farmMachineyMerchantList.get(i).getMerchantcode());
    			List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(zlnfUserdiscussExample);
    			int count = 0;
    			int sum = 0;
    			Double dislevelInt = (double) 0;
    			for(int j = 0;j<userdiscussList.size();j++){
    				count = count + Integer.valueOf(userdiscussList.get(j).getDislevel());
    				sum++;
    			}
    			if(count!=0 && sum!=0){
    				dislevelInt = Double.valueOf(count/sum);
    			}
    			farmMachineyMerchantList.get(i).setDislevel(Integer.valueOf(String.valueOf(Math.round(dislevelInt))));
    			//获取服务商提供服务
    			ZlnfServiceProgramExample serviceProgramExample = new ZlnfServiceProgramExample();
    			ZlnfServiceProgramExample.Criteria criteria = serviceProgramExample.createCriteria();
    			criteria.andIsdeletedEqualTo("0");
    			criteria.andMerchantcodeEqualTo(farmMachineyMerchantList.get(i).getMerchantcode());
    			List<ZlnfServiceProgram> serviceProgramList = serviceProgramService.getResult(serviceProgramExample);
    			int serviceCount = serviceProgramList.size();
    			int machineyCount = 0;
    			for(int j = 0;j<serviceProgramList.size();j++){
    				ZlnfFarmServiceMachineyExample farmServiceMachineyExample = new ZlnfFarmServiceMachineyExample();
    				ZlnfFarmServiceMachineyExample.Criteria machineyCriteria = farmServiceMachineyExample.createCriteria();
    				machineyCriteria.andIsdeletedEqualTo("0");
    				machineyCriteria.andServicecodeEqualTo(serviceProgramList.get(j).getServicecode());
    				List<ZlnfFarmServiceMachiney> machineyList = farmServiceMachineyService.getResult(farmServiceMachineyExample);
    				for(int m = 0;m<machineyList.size();m++){
    					machineyCount = machineyCount + Integer.valueOf(machineyList.get(m).getFarmnum());
    				}
    			}
    			farmMachineyMerchantList.get(i).setServiceCount(String.valueOf(serviceCount));
    			farmMachineyMerchantList.get(i).setMachineyCount(String.valueOf(machineyCount));
    		}
    		//获取合作社信息
    		List<ZlnfCoopInfo> coopInfoList = coopInfoService.queryCoopInfoTen(zlnfCoopInfo);
    		for(int i = 0;i<coopInfoList.size();i++){
    			//获取服务商评价星级
    			ZlnfUserdiscussExample zlnfUserdiscussExample = new ZlnfUserdiscussExample();
    			zlnfUserdiscussExample.createCriteria().andBeevaluatedEqualTo(coopInfoList.get(i).getCoopcode());
    			List<ZlnfUserdiscuss> userdiscussList = userDiscussService.getResult(zlnfUserdiscussExample);
    			int count = 0;
    			int sum = 0;
    			Double dislevelInt = (double) 0;
    			for(int j = 0;j<userdiscussList.size();j++){
    				count = count + Integer.valueOf(userdiscussList.get(j).getDislevel());
    				sum++;
    			}
    			if(count!=0 && sum!=0){
    				dislevelInt = Double.valueOf(count/sum);
    			}
    			coopInfoList.get(i).setDislevel(Integer.valueOf(String.valueOf(Math.round(dislevelInt))));
    			ZlnfFieldInfoExample fieldInfoExample = new ZlnfFieldInfoExample();
    			ZlnfFieldInfoExample.Criteria fieldInfoCriteria = fieldInfoExample.createCriteria();
    			fieldInfoCriteria.andIsdeletedEqualTo("0");
    			fieldInfoCriteria.andCoopcodeEqualTo(coopInfoList.get(i).getCoopcode());
    			List<ZlnfFieldInfo> fieldList = fieldInfoService.getResult(fieldInfoExample);
    			for(int j = 0;j<fieldList.size();j++){
        			ZlnfDictInfoExample dictInfoExample = new ZlnfDictInfoExample();
    				ZlnfDictInfoExample.Criteria criteria = dictInfoExample.createCriteria();
    				criteria.andIsdeletedEqualTo("0");
    				criteria.andDictcodeEqualTo(fieldList.get(j).getCrops());
    				List<ZlnfDictInfo> dictInfoList2 = dictInfoService.getResult(dictInfoExample);
    				if(dictInfoList2.size()>0){
    					fieldList.get(j).setCrops(dictInfoList2.get(0).getDictname());
    				}
        		}
    			int fieldCount = fieldList.size();
    			int acreageCount = 0;
    			for(int j = 0;j<fieldList.size();j++){
    				if(fieldList.get(j).getFieldacreage()!=null){
    					int a = Integer.valueOf(fieldList.get(j).getFieldacreage());
    					acreageCount = acreageCount + a;
    				}
    			}
    			coopInfoList.get(i).setFieldCount(String.valueOf(fieldCount));
    			coopInfoList.get(i).setAcreageCount(String.valueOf(acreageCount));
    		}
    		map.put("farmMachineyMerchantList", farmMachineyMerchantList);
    		map.put("coopInfoList", coopInfoList);
    		result.setSuccess(true);
    		result.setData(map);
    		result.setReturnCode("0000");
    		result.setMessage("获取农机服务商信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("获取农机服务商信息失败");
    	}
    	return result;
    }

}
