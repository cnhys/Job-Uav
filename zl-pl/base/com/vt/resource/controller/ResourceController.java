package com.vt.resource.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.BeanUtil;
import com.vt.fencing.request.OperatorRequest;
import com.vt.fencing.request.ResourceRequest;
import com.vt.resource.model.Resource;
import com.vt.resource.service.IResourceService;
import com.vt.user.model.Operator;
import com.vt.user.service.IOperatorService;

@Controller
public class ResourceController extends BaseGatewayController {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2630906027362077224L;

    @Autowired
    @Qualifier(IServiceDef.RESOURCE_SERVICE)
    private IResourceService resourceService;
    
    /**
     * operator service
     */
    @Autowired@Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;

    /**
     * 保存资源信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/resource/save-resource"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult saveResource(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        ResourceRequest request = convert(data, ResourceRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.saveresource.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.saveresource.secret.empty");
        }
        if (request.getLoginEmpId()== null || request.getLoginEmpId() <= 0) {
            reject("mgr.saveresource.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.saveresource.user.not.exists");
        }
        OptResult result = OptResult.success();
        Resource resource = new Resource();
		BeanUtil.copyProperties(request, resource);
        if (request.getResId() != null && request.getResId().intValue() > 0) {
            if (request.getDelFlag() != null && "1".equals(request.getDelFlag())) {
				/* 根据资源ID删除资源信息 */
                result = resourceService.remove(request.getResId());
            } else {
				/* 修改资源信息 */
                result = resourceService.updateResource(resource);
            }
        } else {
			/* 新增资源信息 */
            result = resourceService.saveResource(resource);
        }
        result.setData(resource);
        return result;
    }
    /**
     * 根据资源ID获取资源信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/resource/query-resourcebyid"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult queryResourceByResId(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        ResourceRequest request = convert(data, ResourceRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.initmenu.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.initmenu.secret.empty");
        }
        if (request.getLoginEmpId()== null || request.getLoginEmpId() <= 0) {
            reject("mgr.initmenu.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.initmenu.user.not.exists");
        }
		/* 根据资源ID获取资源信息 */
        Resource queryResource = resourceService.getById(request.getResId());
        OptResult result = OptResult.success();
        result.setData(queryResource);
        return result;
    }
    /**
     * 后台管理-资源页面显示资源树
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/resource/init-treedata"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrInitTreeData(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        OperatorRequest request = convert(data, OperatorRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.initmenu.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.initmenu.secret.empty");
        }
        if (request.getOperatorId()== null || request.getOperatorId() <= 0) {
            reject("mgr.initmenu.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getOperatorId());
        if (operator == null) {
            reject("mgr.initmenu.user.not.exists");
        }
		/* 获取客户角色对应的资源树 */
        List<Map<String, Object>> list = resourceService.queryTreeResources(0);
        OptResult optResult = OptResult.success();
        optResult.setData(list);
        return optResult;
    }
	/**
	 * 初始化登录时，菜单栏显示
	 * @param channel
	 * @param key
	 * @param data
	 * @return
	 */
    @RequestMapping(value = {"/mgr/resource/init-menu"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrInitMenu(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        OperatorRequest request = convert(data, OperatorRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.initmenu.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.initmenu.secret.empty");
        }
        if (request.getOperatorId()== null || request.getOperatorId() <= 0) {
            reject("mgr.initmenu.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getOperatorId());
        if (operator == null) {
            reject("mgr.initmenu.user.not.exists");
        }
		/* 获取客户角色对应的资源树 */
        List<Map<String, Object>> menuMap = resourceService.queryRoleTreeResources(operator,new Integer(1001));
        OptResult optResult = OptResult.success();
        optResult.setData(menuMap);
        return optResult;
    }

    @RequestMapping(value = {ISysManageControllerPathDef.CURRENT_OPERATOR_SUB_MENU_API}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public OptResult queryCurrentOperatorSubMenu(Resource resource, Model model) {
        Operator operator = getCurrentOperator();
		/*获取顶级菜单的子级菜单 */
        List<Map<String, Object>> menuMap = resourceService.getRoleTreeResources(resource.getResId(), operator);
        OptResult optResult = OptResult.success();
        optResult.setData(menuMap);
        return optResult;
    }

}
