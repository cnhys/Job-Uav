package com.vt.fengci.zlnf.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.DigestUtils;
import com.vt.fencing.model.ZlnfBiDksqcl;
import com.vt.fencing.model.ZlnfBiDksqclExample;
import com.vt.fencing.model.ZlnfPaymenttypeCredit;
import com.vt.fencing.model.ZlnfTakeawayOrder;
import com.vt.fencing.model.ZlnfTakeawayOrderExample;
import com.vt.fencing.request.ZlnfBiDksqclRequest;
import com.vt.fencing.request.ZlnfTakeawayOrderRequest;
import com.vt.fengci.zlnf.service.IBiDksqclService;
import com.vt.fengci.zlnf.service.IPaymenttypeCreditService;

/**
 * user related functions
 * Created by john on 17/7/19.
 */
@Controller
public class BiDksqclController extends BaseGatewayController {

	private static final long serialVersionUID = 4016246844920221281L;
	/**
     * member service
     */
    @Autowired
    private IBiDksqclService biDksqclService;

    @Autowired
    private IPaymenttypeCreditService paymenttypeCreditService;
    
    
    /**
     * 添加贷款申请
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDksqcl/create"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biDksqclCreate(String channel, String key, String data) {
        // 0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("user.activate.data.empty");
        }
        // 1. 转换数据
        ZlnfBiDksqclRequest zlnfBiDksqclRequest  = convert(data, ZlnfBiDksqclRequest.class);
        // 2. 业务检查
        if (zlnfBiDksqclRequest == null) {
            reject("user.activate.model.convert.error");
        }
        //3、根据type返回协议数据
        OptResult result = OptResult.success();
        String uuid = DigestUtils.generateRandomNumber(10);
        Date date = new Date();
        ZlnfBiDksqcl zlnfBiDksqcl = new ZlnfBiDksqcl();
        zlnfBiDksqcl.setZged(zlnfBiDksqclRequest.getZged());
        zlnfBiDksqcl.setQx(zlnfBiDksqclRequest.getQx());
        zlnfBiDksqcl.setNhll(zlnfBiDksqclRequest.getNhll());
        zlnfBiDksqcl.setLlY(zlnfBiDksqclRequest.getLlY());
        zlnfBiDksqcl.setXm(zlnfBiDksqclRequest.getXm());
        zlnfBiDksqcl.setXb(zlnfBiDksqclRequest.getXb());
        zlnfBiDksqcl.setSfzh(zlnfBiDksqclRequest.getSfzh());
        zlnfBiDksqcl.setSfzyxq(zlnfBiDksqclRequest.getSfzyxq());
        zlnfBiDksqcl.setSfzyxz(zlnfBiDksqclRequest.getSfzyxz());
        zlnfBiDksqcl.setLxfs(zlnfBiDksqclRequest.getLxfs());
        zlnfBiDksqcl.setCsrq(zlnfBiDksqclRequest.getCsrq());
        zlnfBiDksqcl.setJysp(zlnfBiDksqclRequest.getJysp());
        zlnfBiDksqcl.setHyzk(zlnfBiDksqclRequest.getHyzk());
        zlnfBiDksqcl.setDkyt(zlnfBiDksqclRequest.getDkyt());
        zlnfBiDksqcl.setJyhy(zlnfBiDksqclRequest.getJyhy());
        zlnfBiDksqcl.setJyfw(zlnfBiDksqclRequest.getJyfw());
        zlnfBiDksqcl.setJynx(zlnfBiDksqclRequest.getJynx());
        zlnfBiDksqcl.setJyyl(zlnfBiDksqclRequest.getJyyl());
        zlnfBiDksqcl.setJyzds(zlnfBiDksqclRequest.getJyzds());
        zlnfBiDksqcl.setJydzxxs(zlnfBiDksqclRequest.getJydzxxs());
        zlnfBiDksqcl.setJyxxdzxx(zlnfBiDksqclRequest.getJyxxdzxx());
        zlnfBiDksqcl.setYfyzljyjl(zlnfBiDksqclRequest.getYfyzljyjl());
        zlnfBiDksqcl.setYjzzl(zlnfBiDksqclRequest.getYjzzl());
        zlnfBiDksqcl.setDklx(zlnfBiDksqclRequest.getDklx());
        zlnfBiDksqcl.setSnpjmcz(zlnfBiDksqclRequest.getSnpjmcz());
        zlnfBiDksqcl.setSnzzmj(zlnfBiDksqclRequest.getSnzzmj());
        zlnfBiDksqcl.setSnpjdj(zlnfBiDksqclRequest.getSnpjdj());
        zlnfBiDksqcl.setYzlhznx(zlnfBiDksqclRequest.getYzlhznx());
        zlnfBiDksqcl.setYzzhyh(zlnfBiDksqclRequest.getYzzhyh());
        zlnfBiDksqcl.setQyxydm(zlnfBiDksqclRequest.getQyxydm());
        zlnfBiDksqcl.setSwdjh(zlnfBiDksqclRequest.getSwdjh());
        zlnfBiDksqcl.setScyyzz(zlnfBiDksqclRequest.getScyyzz());
        zlnfBiDksqcl.setScfj(zlnfBiDksqclRequest.getScfj());
        zlnfBiDksqcl.setTdlzzm(zlnfBiDksqclRequest.getTdlzzm());
        zlnfBiDksqcl.setZlhddtjh(zlnfBiDksqclRequest.getZlhddtjh());
        zlnfBiDksqcl.setHzszc(zlnfBiDksqclRequest.getHzszc());
        zlnfBiDksqcl.setHzsfddbr(zlnfBiDksqclRequest.getHzsfddbr());
        zlnfBiDksqcl.setSjkzrsfzz(zlnfBiDksqclRequest.getSjkzrsfzz());
        zlnfBiDksqcl.setSjkzrsfzf(zlnfBiDksqclRequest.getSjkzrsfzf());
        zlnfBiDksqcl.setPosfzz(zlnfBiDksqclRequest.getPosfzz());
        zlnfBiDksqcl.setPosfzf(zlnfBiDksqclRequest.getPosfzf());
        zlnfBiDksqcl.setJhz(zlnfBiDksqclRequest.getJhz());
        zlnfBiDksqcl.setSqje(zlnfBiDksqclRequest.getSqje());
        zlnfBiDksqcl.setCpmc(zlnfBiDksqclRequest.getCpmc());
        zlnfBiDksqcl.setLczt("1");
        zlnfBiDksqcl.setIsdeleted("0");
        zlnfBiDksqcl.setPfje(zlnfBiDksqclRequest.getPfje());
        zlnfBiDksqcl.setFkqx(zlnfBiDksqclRequest.getFkqx());
        zlnfBiDksqcl.setFkll(zlnfBiDksqclRequest.getFkll());
        zlnfBiDksqcl.setSqbh(uuid);
        zlnfBiDksqcl.setCjsj(date);
        zlnfBiDksqcl.setCjr(zlnfBiDksqclRequest.getCjr());
        zlnfBiDksqcl.setCjrbm(zlnfBiDksqclRequest.getCjrbm());
        zlnfBiDksqcl.setCpbh(zlnfBiDksqclRequest.getCpbh());
        zlnfBiDksqcl.setCqyx(zlnfBiDksqclRequest.getCqyx());
        zlnfBiDksqcl.setYhzh(zlnfBiDksqclRequest.getYhzh());
        zlnfBiDksqcl.setSwdjh(zlnfBiDksqclRequest.getSwdjh());
        try {
        	biDksqclService.create(zlnfBiDksqcl);
        	result.setSuccess(true);
//        	result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("申报信息添加成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
			result.setReturnCode("1111");
			result.setMessage("申报信息添加失败");
		}
        return result;
    }

    
    
    /**
     * 查询申请贷款
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDksqcl/query"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biDksqclQuery(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiDksqclRequest zlnfBiDksqclRequest  = convert(data, ZlnfBiDksqclRequest.class);
    	// 2. 业务检查
    	if (zlnfBiDksqclRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfBiDksqclExample zlnfBiDksqclExample = new ZlnfBiDksqclExample();
    	ZlnfBiDksqclExample.Criteria criteria = zlnfBiDksqclExample.createCriteria();
    	criteria.andIsdeletedEqualTo("0");
    	criteria.andCjrbmEqualTo(zlnfBiDksqclRequest.getCjrbm());
    	criteria.andDklxEqualTo(zlnfBiDksqclRequest.getDklx());
    	criteria.andCpbhEqualTo(zlnfBiDksqclRequest.getCpbh());
    	try {
    		List<ZlnfBiDksqcl> list = biDksqclService.getResult(zlnfBiDksqclExample);
    		result.setSuccess(true);
        	result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询申请信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询申请信息失败");
    	}
    	return result;
    }

    /**
     * 根据申请编号查询
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDksqcl/queryBySqbh"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biDksqclQueryBySqbh(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiDksqclRequest zlnfBiDksqclRequest  = convert(data, ZlnfBiDksqclRequest.class);
    	// 2. 业务检查
    	if (zlnfBiDksqclRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfBiDksqclExample zlnfBiDksqclExample = new ZlnfBiDksqclExample();
    	ZlnfBiDksqclExample.Criteria criteria = zlnfBiDksqclExample.createCriteria();
    	criteria.andIsdeletedEqualTo("0");
    	criteria.andSqbhEqualTo(zlnfBiDksqclRequest.getSqbh());
    	try {
    		List<ZlnfBiDksqcl> list = biDksqclService.getResult(zlnfBiDksqclExample);
    		result.setSuccess(true);
    		result.setData(list);
    		result.setReturnCode("0000");
    		result.setMessage("查询申请信息成功");
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询申请信息失败");
    	}
    	return result;
    }

    
    /**
     * 修改申请贷款流程状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDksqcl/update"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult biDksqclUpdate(String channel, String key, String data) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiDksqclRequest zlnfBiDksqclRequest  = convert(data, ZlnfBiDksqclRequest.class);
    	// 2. 业务检查
    	if (zlnfBiDksqclRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	//3、根据type返回协议数据
    	OptResult result = OptResult.success();
    	ZlnfBiDksqclExample zlnfBiDksqclExample = new ZlnfBiDksqclExample();
    	ZlnfBiDksqclExample.Criteria criteria = zlnfBiDksqclExample.createCriteria();
    	criteria.andIsdeletedEqualTo("0");
    	criteria.andSqbhEqualTo(zlnfBiDksqclRequest.getSqbh());
    	try {
    		List<ZlnfBiDksqcl> list = biDksqclService.getResult(zlnfBiDksqclExample);
    		if(list.size()>0){
    			Date date = new Date();
    			ZlnfBiDksqcl zlnfBiDksqcl = new ZlnfBiDksqcl();
    			zlnfBiDksqcl.setId(list.get(0).getId());
    			zlnfBiDksqcl.setLczt("3");
    			biDksqclService.update(zlnfBiDksqcl);
    			String billcode = DigestUtils.generateRandomNumber(10);
    			ZlnfPaymenttypeCredit paymenttypeCredit = new ZlnfPaymenttypeCredit();
    			paymenttypeCredit.setOrdercode(zlnfBiDksqclRequest.getSqbh());
    			paymenttypeCredit.setBillcode(billcode);
    			paymenttypeCredit.setPaydate(date);
    			paymenttypeCredit.setPaytype(zlnfBiDksqclRequest.getType());
    			paymenttypeCredit.setRefund(Double.valueOf(zlnfBiDksqclRequest.getTotalFee()));
    			if(!"3".equals(zlnfBiDksqclRequest.getType())){
    				paymenttypeCredit.setPaycode(zlnfBiDksqclRequest.getSerialNum());
    			}
    			paymenttypeCreditService.create(paymenttypeCredit);
    			result.setSuccess(true);
        		result.setData(list);
        		result.setReturnCode("0000");
        		result.setMessage("修改贷款流程状态成功");
    		}else{
    			result.setSuccess(true);
        		result.setReturnCode("0001");
        		result.setMessage("没有该条记录");
    		}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("申报信息添加失败");
    	}
    	return result;
    }
    
    /**
     * 查看支付状态
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/biDksqcl/queryStatus"}, method = {RequestMethod.POST})
    @ResponseBody
    public OptResult queryStatus(String channel, String key, String data,HttpServletResponse response) {
    	// 0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("user.activate.data.empty");
    	}
    	// 1. 转换数据
    	ZlnfBiDksqclRequest zlnfBiDksqclRequest  = convert(data, ZlnfBiDksqclRequest.class);
    	// 2. 业务检查
    	if (zlnfBiDksqclRequest == null) {
    		reject("user.activate.model.convert.error");
    	}
    	OptResult result = OptResult.success();
    	try {
    		ZlnfBiDksqclExample zlnfBiDksqclExample = new ZlnfBiDksqclExample();
        	ZlnfBiDksqclExample.Criteria criteria = zlnfBiDksqclExample.createCriteria();
        	criteria.andIsdeletedEqualTo("0");
        	criteria.andSqbhEqualTo(zlnfBiDksqclRequest.getSqbh());
        	List<ZlnfBiDksqcl> biDksqclList = biDksqclService.getResult(zlnfBiDksqclExample);
	    	Boolean bo = false;
	    	if(biDksqclList.size()>0){
	    		String status = biDksqclList.get(0).getLczt();
	    		if("3".equals(status)){
					bo = true;
				}
	    		result.setSuccess(true);
	    		result.setData(bo);
	    		result.setReturnCode("0000");
	    		result.setMessage("查询支付状态成功");
	    	}else{
	    		result.setSuccess(false);
	    		result.setReturnCode("0001");
	    		result.setMessage("查询不到该记录");
	    	}
    	} catch (Exception e) {
    		// TODO: handle exception
    		result.setSuccess(false);
    		result.setReturnCode("1111");
    		result.setMessage("查询支付状态失败");
    	}
    	return result;
    }

}
