package com.vt.fencing.versions.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.service.ITradeNoService;
import com.vt.base.util.DateUtil;
import com.vt.base.util.JsonUtil;
import com.vt.fencing.IFencingConst;
import com.vt.fencing.IGatewayServiceDef;
import com.vt.fencing.model.VangoVersion;
import com.vt.fencing.model.VangoVersionExample;
import com.vt.fencing.request.VersionRequest;
import com.vt.fencing.versions.service.IVangoVersionsService;
import com.vt.user.model.Operator;
import com.vt.user.service.IOperatorService;


/**
 * 收货地址控制器 - App
 * 
 * @author wzw
 */
@Controller
public class VersionController extends BaseGatewayController{

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6026052065752059229L;
	/**
     * Leave service
     */
    @Autowired@Qualifier(IGatewayServiceDef.VERSION_SERVICE)
    private IVangoVersionsService vangoVersionsService;
	
    
    /**
     * operator service
     */
    @Autowired@Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;
    /**
     * 交易流水号服务
     */
    @Autowired
    private ITradeNoService tradeNoService;
    
    
    
	/**
     * app端_版本列表
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/api/version/select-version"}, method = {RequestMethod.POST})@ResponseBody
    public PageData<VangoVersion> queryVersionlist(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        PageRequest<VersionRequest> request = JsonUtil.transferToPageRequest(data, VersionRequest.class);
        //1. 数据转换
       
       
        //3. 处理
        PageRequest<VangoVersionExample> _request = new PageRequest<VangoVersionExample>();
        VangoVersionExample example = new VangoVersionExample();
        _request.setCondition(example);
        _request.setPage(request.getPage());
        _request.setRows(request.getRows());
        return vangoVersionsService.query(_request);
    }
	
    /**
     * PC管理_资讯列表
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/version/query-versionlist"}, method = {RequestMethod.POST})@ResponseBody
    public PageData<VangoVersion> mgrInformationList(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        PageRequest<VersionRequest> request = JsonUtil.transferToPageRequest(data, VersionRequest.class);
        //2. 检查
        if (request == null) {
            reject("information.informationdetail.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getCondition().getSecret())) {
            reject("mgr.updatesinformation.secret.empty");
        }
        if (request.getCondition().getLoginEmpId() == null || request.getCondition().getLoginEmpId() <= 0) {
            reject("mgr.updatesinformation.secret.empty");
        }
        //查询登陆用户
        Operator operator = operatorService.getById(request.getCondition().getLoginEmpId());
        if (operator == null) {
            reject("information.createopinion.user.not.exists");
        }
        //3. 处理
        PageRequest<VangoVersionExample> _request = new PageRequest<VangoVersionExample>();
        VangoVersionExample example = new VangoVersionExample();
        VangoVersionExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(request.getCondition().getVersionNum())) {
        	criteria.andVersionNumLike("%" + request.getCondition().getVersionNum() + "%");
        }
        example.setOrderByClause("VERSION_ID DESC");
        _request.setCondition(example);
        _request.setPage(request.getPage());
        _request.setRows(request.getRows());
        PageData<VangoVersion> result = vangoVersionsService.query(_request);
        return result;
    }
    
    
    
    /**
     * 后台管理_添加版本
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/version/create-version"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrAddStadium(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        VersionRequest request = convert(data, VersionRequest.class);
        //2. 检查
        if (request == null) {
            reject("version.createversion.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.createversion.secret.empty");
        }
        if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
            reject("mgr.createversion.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.createversion.user.not.exists");
        }
        //3. 处理
        //3.1 校验必输项
        
        if(StringUtils.isEmpty(request.getPath())){
        reject("mgr.deleteversion.path.not.null");
        }
        //3.2 创建版本信息
        VangoVersion  vangoVersion = new VangoVersion();
        vangoVersion.setVersionCode(tradeNoService.generateFlowNumber("SD", 8));
        vangoVersion.setVersionNum(request.getVersionNum());
        vangoVersion.setVersionDetail(request.getVersionDetail());
        vangoVersion.setPath(request.getPath());
        vangoVersion.setPlatform(request.getPlatform());
       
        /**
         * 公共字段信息创建
         */
        vangoVersion.setCreateDate(DateUtil.getCurrentDateTime());
        vangoVersion.setCreator(operator.getUserName());
        vangoVersion.setIsAudit(IFencingConst.IS_AUDIT);
        vangoVersion.setIsDeleted(IFencingConst.ISNOT_DELDETED);
        vangoVersion.setModifyDate(DateUtil.getCurrentDateTime());
        vangoVersion.setModifier(operator.getUserName());
        vangoVersion.setCreatorDeptCode("");
        return vangoVersionsService.create(vangoVersion);
    }
    
    /**
     *  后台管理端__删除版本
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/version/delete-version"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult delVersion(String channel, String key, String data) {
    	//0. 检查
    	channelKeyCheck(channel, key);
    	if (StringUtils.isEmpty(data)) {
    		reject("common.data.empty");
    	}
    	//1. 数据转换
    	VersionRequest request = convert(data, VersionRequest.class);
    	//2. 检查
    	if (request == null) {
    		reject("mgr.deleteversion.model.convert.error");
    	}
    	if (StringUtils.isEmpty(request.getSecret())) {
    		reject("mgr.deleteversion.secret.empty");
    	}
    	if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
    		reject("mgr.deleteversion.secret.empty");
    	}
    	//查询登陆用户
    	Operator operator = operatorService.getById(request.getLoginEmpId());
    	if (operator == null) {
    		reject("mgr.queryversion.secret.empty");
    	}
    	//3.业务处理
    	//3.1 交易必输项
    	if (StringUtils.isEmpty(request.getVersionId())) {
    		reject("mgr.queryversion.id.not.null");
    	}
    	
    	//3.2 查询版本实体
    	OptResult result=vangoVersionsService.remove(Integer.parseInt(request.getVersionId()));
    	return result;
    }	
    
    
    
    /**
     * 后台管理_修改版本
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/version/update-version"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrUpdateInformation(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        VersionRequest request = convert(data, VersionRequest.class);
        //2. 检查
        if (request == null) {
            reject("information.informationdetail.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.updatesinformation.secret.empty");
        }
        if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
            reject("mgr.addinformation.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.addinformation.userid.is.null");
        }
        //3. 处理
        //3.1 查询城市信息
        VangoVersion  vangoVersion = vangoVersionsService.getById(Integer.parseInt(request.getVersionId()));
        
        vangoVersion.setVersionNum(request.getVersionNum());
        vangoVersion.setVersionDetail(request.getVersionDetail());
        vangoVersion.setPath(request.getPath());
        vangoVersion.setPlatform(request.getPlatform());
       
        /**
         * 公共字段信息创建
         */
        vangoVersion.setCreateDate(DateUtil.getCurrentDateTime());
        vangoVersion.setCreator(operator.getUserName());
        vangoVersion.setIsAudit(IFencingConst.IS_AUDIT);
        vangoVersion.setIsDeleted(IFencingConst.ISNOT_DELDETED);
        vangoVersion.setModifyDate(DateUtil.getCurrentDateTime());
        vangoVersion.setModifier(operator.getUserName());
        vangoVersion.setCreatorDeptCode("");
        return vangoVersionsService.update(vangoVersion);
        
    }
    
    /**
     *  后台管理端__查看版本信息详情
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/version/view-version"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult viewInformation(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        VersionRequest request = convert(data, VersionRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.opinionlist.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.opinionlist.secret.empty");
        }
        if (request.getLoginEmpId() == null || request.getLoginEmpId() <= 0) {
            reject("mgr.opinionlist.userid.is.null");
        }
        //查询登陆用户
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.opinionlist.user.not.exists");
        }
        
        VangoVersion vangoVersion = vangoVersionsService.getById(Integer.parseInt(request.getVersionId()));
        OptResult result = OptResult.success();
        result.setData(vangoVersion);
        return result;
    }
    
    
    
    
    
    
}
