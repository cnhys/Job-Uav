package com.vt.org.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.annotation.SysControllerLog;
import com.vt.base.controller.BaseRestController;
import com.vt.base.exception.BizException;
import com.vt.base.service.IDBUtilService;
import com.vt.org.model.Organization;
import com.vt.org.model.OrganizationExample;
import com.vt.org.service.IOrganizationService;

/**
 * <p>
 * Title:机构管理控制类
 * </p>
 * <p>
 * Description:提供机构查询，修改，新增等功能
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company:xxx.co.,ltd
 * </p>
 *
 * @author john
 * @version 1.0
 */
@Controller
public class OrganizationController extends BaseRestController {

    private static final long serialVersionUID = 6245630130945781269L;

    /**
     * 机构管理服务
     */
    @Autowired
    @Qualifier(IServiceDef.ORGANIZATION_SERVICE)
    private IOrganizationService organizationService;

    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * <p>
     * Description:显示机构查询页面
     * </p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_FILTER_CONTROLLER_URL}, method = RequestMethod.GET)
    public String showOrganization(Model model) {
        return ISysManageControllerPathDef.ORG_FILTER_FILE_PATH;
    }

    /**
     * <p>
     * Description:机构查询
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_FILTER_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = "机构查询")
    public PageData<Organization> processFormData(Organization organization, PageRequest<Organization> page,
                                                  Model model) throws BizException {
        PageData<Organization> result = new PageData<Organization>();

        result.setLimit(page.getLimit());
        result.setStart(page.getStart());
        result.setSuccess(true);

        // 封装查询条件
        OrganizationExample example = new OrganizationExample();
        if (null != organization) {
            if (StringUtils.isNotBlank(organization.getOrgCode())) {
                example.createCriteria().andOrgCodeEqualTo(organization.getOrgCode());
            }
            if (StringUtils.isNotBlank(organization.getOrgName())) {
                example.createCriteria().andOrgNameLike(organization.getOrgName());
            }
            if (StringUtils.isNotBlank(organization.getOrgLevel())) {
                example.createCriteria().andOrgLevelEqualTo(organization.getOrgLevel());
            }
            if (StringUtils.isNotBlank(organization.getOrgType())) {
                example.createCriteria().andOrgTypeEqualTo(organization.getOrgType());
            }
            if (StringUtils.isNotBlank(organization.getOrgState())) {
                example.createCriteria().andOrgStateEqualTo(organization.getOrgType());
            }
        }
        // 查询机构列表
        try {
            List<Organization> pmOrganizationList = organizationService.getResult(example);
            result.setData(pmOrganizationList);
            result.setTotal(pmOrganizationList.size());
        } catch (BizException e) {
            logger.error("查询异常", e.getMessage());
            throw new BizException("systemmanage.org.filter.null", e.getErrorMessage());
        }
        return result;
    }

    /**
     * <p>
     * Description:新增机构
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_ADD_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult addOrganization(Organization organization, Model model) throws BizException {
        OptResult optResult;
        try {
            if (organization.getOrgId() != null && organization.getOrgId().intValue() > 0) {
                if ("1".equals(organization.getField4())) {
                    OrganizationExample orgExam = new OrganizationExample();
                    OrganizationExample.Criteria orgCri = orgExam.createCriteria();
                    orgCri.andParentOrgIdEqualTo(organization.getOrgId());
                    List<Organization> orgList = organizationService.getResult(orgExam);
                    if (orgList != null && orgList.size() > 0) {
                        optResult = OptResult.failure("systemmanage.org.filter.null");
                    } else {
                        optResult = organizationService.remove(organization.getOrgId());
                    }
                } else {
                    optResult = organizationService.update(organization);
                }
            } else {
                optResult = organizationService.create(organization);
            }
        } catch (BizException e) {
            logger.error("新增机构异常", e.getMessage());
            throw new BizException("systemmanage.org.save.null", e.getErrorMessage());
        }
        return optResult;
    }

    /**
     * <p>
     * Description:修改机构
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_UPDATE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult updateOrganization(Organization organization, Model model) throws BizException {
        OptResult result = new OptResult();
        try {
            // 修改机构
            result = organizationService.update(organization);

        } catch (BizException e) {
            logger.error("修改机构异常", e.getMessage());
            throw new BizException("systemmanage.org.update.null", e.getErrorMessage());
        }

        return result;
    }

    /**
     * <p>
     * Description:删除机构
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_DELETE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult deleteOrganization(Organization organization, Model model) {
        OptResult result = new OptResult();
        try {
            // 删除机构
            result = organizationService.remove(organization.getOrgId());
        } catch (BizException e) {
            logger.error("删除机构异常", e.getMessage());
            throw new BizException("systemmanage.org.delete.null", e.getErrorMessage());
        }
        return result;
    }

    /**
     * 机构树页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_INIT_TREEDATA_API}, method = {RequestMethod.POST,
            RequestMethod.GET})
    @ResponseBody
    public List<Map<String, Object>> initOrganizationTreeData(Integer id, Model model) {
        if (id == null) {
            id = 0;
        }
        List<Map<String, Object>> list = organizationService.buildOrgnizationTree(id);
        return list;
    }

    /**
     * 通过id查找机构
     *
     * @param organization
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.ORG_VIEW_FROM_ORGID_API}, method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> queryOrganizationByOrgId(Organization organization, Model model) {
        Organization queryOrg = organizationService.getById(organization.getOrgId());
        if (queryOrg != null) {
            if (queryOrg.getParentOrgId() != 0) {
                Organization parentOrg = organizationService.getById(queryOrg.getParentOrgId());
                queryOrg.setField4(parentOrg.getOrgName());
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", queryOrg);
        return map;
    }

}
