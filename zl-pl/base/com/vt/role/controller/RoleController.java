package com.vt.role.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.IConst;
import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.SysControllerLog;
import com.vt.base.controller.BaseGatewayController;
import com.vt.base.util.BeanUtil;
import com.vt.base.util.JsonUtil;
import com.vt.base.util.ListUtil;
import com.vt.fencing.request.OperatorRequest;
import com.vt.fencing.request.RoleRequest;
import com.vt.resource.service.IResourceService;
import com.vt.role.RoleConst;
import com.vt.role.model.Role;
import com.vt.role.model.RoleExample;
import com.vt.role.model.RoleResRel;
import com.vt.role.service.IRoleService;
import com.vt.user.model.Operator;
import com.vt.user.service.IOperatorService;

/**
 * <p> Title: Role Manage Controller </p>
 * <p> Description: manage role,Contains add、update、view、delete function </p>
 *
 * @Author:Devin Bai
 * @Time:May 28, 20155:35:15 PM
 * @Version:1.00
 * @Record <pre>
 * Version 	Author 	Time		describe
 * ----------------------------------------
 * 1.00		Devin	May 28, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Controller
public class RoleController extends BaseGatewayController {

    /**
     * Role Manage Controller
     */
    private static final long serialVersionUID = -7070484659902977320L;
    /**
     * Role Service
     */
    @Autowired
    @Qualifier(IServiceDef.ROLE_MANAGE_SERVICE)
    private IRoleService roleService;
    @Autowired
    @Qualifier(IServiceDef.RESOURCE_SERVICE)
    private IResourceService resourceService;
    @Autowired
    @Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;

    /**
     * query role info
     *
     * @param role  Query conditions
     * @param model model
     * @return role infos
     */
    @RequestMapping(value = {"/mgr/role/query-rolelist"}, method = {RequestMethod.POST})@ResponseBody
    public PageData<Role> mgrRoleList(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        PageRequest<RoleRequest> request = JsonUtil.transferToPageRequest(data, RoleRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.rolelist.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getCondition().getSecret())) {
            reject("mgr.rolelist.secret.empty");
        }
        if (request.getCondition().getLoginEmpId() == null || request.getCondition().getLoginEmpId() <= 0) {
            reject("mgr.rolelist.userid.is.null");
        }
        //查询登陆用户
        Operator operator = operatorService.getById(request.getCondition().getLoginEmpId());
        if (operator == null) {
            reject("mgr.rolelist.user.not.exists");
        }
        //3. 处理
        //3.1 封装查询条件
        RoleRequest roleRequest = request.getCondition();
        Role RoleQuery = new Role();
		BeanUtil.copyProperties(roleRequest, RoleQuery);
        PageRequest<RoleExample> _request = new PageRequest<RoleExample>();
        return roleService.roleManageFilterProcess(RoleQuery, _request);
    }

    /**
     * query role info
     *
     * @param role  Query conditions
     * @param model model
     * @return role infos
     */
    @RequestMapping(value = {"/mgr/role/query-roles"}, method = {RequestMethod.POST})@ResponseBody
    public PageData<Role> mgrRoles(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        PageRequest<RoleRequest> request = JsonUtil.transferToPageRequest(data, RoleRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.roles.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getCondition().getSecret())) {
            reject("mgr.roles.secret.empty");
        }
        if (request.getCondition().getLoginEmpId() == null || request.getCondition().getLoginEmpId() <= 0) {
            reject("mgr.roles.userid.is.null");
        }
        //查询登陆用户
        Operator operator = operatorService.getById(request.getCondition().getLoginEmpId());
        if (operator == null) {
            reject("mgr.roles.user.not.exists");
        }
        RoleRequest roleRequest = request.getCondition();
        Role RoleQuery = new Role();
		BeanUtil.copyProperties(roleRequest, RoleQuery);
        PageRequest<RoleExample> _request = new PageRequest<RoleExample>();
        return roleService.roleManageFilterProcess(RoleQuery, _request);
    }


    /**
     * save role info
     *
     * @param role  to save role
     * @param model model
     * @return
     */
    @RequestMapping(value = {"/mgr/role/save-role"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgeSaveRole(String channel, String key, String data) {
    	
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        RoleRequest request = convert(data, RoleRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.saverole.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.saverole.secret.empty");
        }
        if (request.getLoginEmpId()== null || request.getLoginEmpId() <= 0) {
            reject("mgr.saverole.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.saverole.user.not.exists");
        }
        Role role = new Role();
        BeanUtil.copyProperties(request, role);
    	
        OptResult result  = OptResult.success();
        result = roleService.saveRole(role);
        if (null != request.getResIds() && !"".equals(request.getResIds())) {
            String[] resStr = request.getResIds().split(",");
            List<RoleResRel> roleResList = new ArrayList<RoleResRel>();
            for (int i = 0; i < resStr.length; i++) {
                RoleResRel roleResRel = new RoleResRel();
                roleResRel.setRoleId(role.getRoleId());
                roleResRel.setResId(Integer.parseInt(resStr[i]));
                roleResList.add(roleResRel);
            }
            roleService.saveRoleResRelsList(roleResList);
        }
        return result;
    }

    /**
     * update role
     *
     * @param role  to update role info
     * @param model model
     * @return
     */
    @RequestMapping(value = {"/mgr/role/update-role"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrUpdateRole(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        RoleRequest request = convert(data, RoleRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.updaterole.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.updaterole.secret.empty");
        }
        if (request.getLoginEmpId()== null || request.getLoginEmpId() <= 0) {
            reject("mgr.updaterole.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.updaterole.user.not.exists");
        }
        Role role = new Role();
        BeanUtil.copyProperties(request, role);
        
        if (request.getResIds() != null && !"".equals(request.getResIds())) {
        	
            roleService.deleteRoleResIdByRoleId(role.getRoleId());
            
            List<RoleResRel> roleResList = new ArrayList<RoleResRel>();
            String[] roleStr = request.getResIds().split(",");
            for (int i = 0; i < roleStr.length; i++) {
                RoleResRel roleResRel = new RoleResRel();
                roleResRel.setRoleId(role.getRoleId());
                roleResRel.setResId(Integer.parseInt(roleStr[i]));
                roleResList.add(roleResRel);
            }
            roleService.saveRoleResRelsList(roleResList);
        }
        return roleService.updateRole(role);
    }

    /**
     * delete role
     *
     * @param role  to delete role
     * @param model model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_MANAGE_DELETE_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = RoleConst.SYS_CTR_LOG_DESC_DELETEROLE)
    public OptResult deleteRole(@RequestParam("roleIds") String roleIds, Model model) throws Exception {
        if (StringUtils.isBlank(roleIds)) {
            return new OptResult(false, "systemmanage.role.delete.null");
        }
        return roleService.deleteRolesById(ListUtil.strToIntegerList(roleIds, IConst.REGEX_COMMA));
    }

    /**
     * delete role for status
     *
     * @param roleIds
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_MANAGE_UPDATESTATUS_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = RoleConst.SYS_CTR_LOG_DESC_DELETEROLE)
    public OptResult updateStatusRole(@RequestParam("roleIds") String roleIds, Model model) throws Exception {
        if (StringUtils.isBlank(roleIds)) {
            return new OptResult(false, "systemmanage.role.delete.null");
        }
        Role role = new Role();
        role.setRoleId(Integer.parseInt(roleIds));
        role.setStatus("0");
        return roleService.update(role);
    }

    /**
     * judge the role is exist or not
     *
     * @param role  tole
     * @param model model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_METHOD_URL_ISROLECODENOTEXIST_API}, method = RequestMethod.POST)
    @ResponseBody
    public String isRoleCodeNotExist(Role role, Model model) throws Exception {
        return roleService.isRoleCodeNotExist(role);
    }

    /**
     * query can manage role list
     *
     * @param role  role
     * @param model model
     * @return can manage role list
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_METHOD_URL_QUERYMGRROLES_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<Role> queryMgrRoles(Role role, Model model) throws Exception {
        return roleService.queryMgrRoles(role);
    }

    /**
     * query role resource rel list
     *
     * @param resRel query condition
     * @param model  model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_METHOD_URL_QUERYROLERESREL_API}, method = RequestMethod.POST)
    @ResponseBody
    public List<RoleResRel> queryRoleResRel(RoleResRel resRel, Model model) throws Exception {
        return roleService.queryRoleResRel(resRel);
    }

    /**
     * save role resource rel
     *
     * @param roleId to save roleId
     * @param resIds to save resIds
     * @param model  model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ROLE_METHOD_URL_SAVEROLERESRELS_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult saveRoleResRels(@RequestParam("roleId") Integer roleId, @RequestParam("resIds") String resIds, Model model) throws Exception {
        return roleService.saveRoleResRels(roleId, resIds);
    }
    /**
     * 后台管理-角色页面显示资源树
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/role/init-resourceTree"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrInitResTree(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        OperatorRequest request = convert(data, OperatorRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.initrestree.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.initrestree.secret.empty");
        }
        if (request.getOperatorId()== null || request.getOperatorId() <= 0) {
            reject("mgr.initrestree.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getOperatorId());
        if (operator == null) {
            reject("mgr.initrestree.user.not.exists");
        }
		/* 获取客户角色对应的资源树 */
        List<Map<String, Object>> list = resourceService.queryTreeResources(0);
        OptResult optResult = OptResult.success();
        optResult.setData(list);
        return optResult;
    }
    /**
     * 通过角色id查询角色信息
     * @param channel
     * @param key
     * @param data
     * @return
     */
    @RequestMapping(value = {"/mgr/role/getrole-byid"}, method = {RequestMethod.POST})@ResponseBody
    public OptResult mgrGetRoleById(String channel, String key, String data) {
        //0. 检查
        channelKeyCheck(channel, key);
        if (StringUtils.isEmpty(data)) {
            reject("common.data.empty");
        }
        //1. 数据转换
        RoleRequest request = convert(data, RoleRequest.class);
        //2. 检查
        if (request == null) {
            reject("mgr.getrole.model.convert.error");
        }
        if (StringUtils.isEmpty(request.getSecret())) {
            reject("mgr.getrole.secret.empty");
        }
        if (request.getLoginEmpId()== null || request.getLoginEmpId() <= 0) {
            reject("mgr.getrole.userid.is.null");
        }
        //查询处理登陆用户实体
        Operator operator = operatorService.getById(request.getLoginEmpId());
        if (operator == null) {
            reject("mgr.getrole.user.not.exists");
        }
        //查询角色实体
        return roleService.queryResIdByRoleId(request.getRoleId());
    }

}
