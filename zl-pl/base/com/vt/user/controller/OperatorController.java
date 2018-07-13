package com.vt.user.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.vt.base.util.StringUtil;
import com.vt.org.model.Organization;
import com.vt.org.service.IOrganizationService;
import com.vt.user.model.OperRoleRel;
import com.vt.user.model.Operator;
import com.vt.user.model.OperatorExample;
import com.vt.user.service.IOperatorService;
import com.vt.user.vo.OperatorVO;

/**
 * <p>
 * Title:用户管理控制类
 * </p>
 * <p>
 * Description:提供用户查询，修改，新增等功能
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
public class OperatorController extends BaseRestController {

    private static final long serialVersionUID = -1086933192369514927L;

    @Autowired
    @Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;

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
     * Description:显示用户查询页面
     * </p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_FILTER_CONTROLLER_URL}, method = RequestMethod.GET)
    public String showOperator(Model model) {

        model.addAttribute("name", "用户查询");

        return ISysManageControllerPathDef.USER_FILTER_FILE_PATH;
    }

    /**
     * <p>
     * Description:查询出用户信息列表数据
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_FILTER_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<Operator> processFormData(Operator operator, PageRequest<Operator> page, Model model)
            throws BizException {
        PageData<Operator> result = new PageData<Operator>();

        result.setLimit(page.getLimit());
        result.setStart(page.getStart());
        // 封装查询条件
        OperatorExample example = new OperatorExample();
        OperatorExample.Criteria criteria = example.createCriteria();
        if (null != operator) {
            if (StringUtils.isNotBlank(operator.getUserName())) {
                criteria.andUserNameLike(StringUtil.dbQueryLike(operator.getUserName()));
            }
            if (null != operator.getOrgid()) {
                criteria.andOrgidEqualTo(operator.getOrgid());
            }
            if (StringUtils.isNotBlank(operator.getStatus())) {
                criteria.andStatusEqualTo(operator.getStatus());
            }
        }
        try {
            // 查询用户列表
            List<Operator> operatorList = operatorService.getResult(example);
            if (operatorList != null && operatorList.size() > 0) {
                for (Operator oper : operatorList) {
                    if (oper.getOrgid() != null) {
                        Organization org = organizationService.getById(oper.getOrgid());
                        if (org != null) {
                            oper.setField1(org.getOrgName());
                        }
                    }
                }
            }
            result.setData(operatorList);
            result.setTotal(operatorList.size());
        } catch (BizException e) {
            logger.error("查询异常", e.getMessage());
            throw new BizException("systemmanage.user.filter.null", e.getErrorMessage());
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 查询指定用户全部角色ID
     *
     * @param operId 用户ID
     * @param model
     * @return
     * @throws BizException
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_FILTER_ROLEREL_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult queryAllRoleByOperId(@RequestParam("operId") String operId, Model model) throws BizException {
        OptResult optResult = OptResult.success();
        StringBuffer sb = new StringBuffer();
        if (operId != null && !"".equals(operId)) {
            List<Integer> roleList = operatorService.getOperRoleRelListById(Integer.parseInt(operId));
            if (roleList != null) {
                for (int i = 0; i < roleList.size(); i++) {
                    sb.append(roleList.get(i).toString()).append(",");
                }
            }
        }
        optResult.setData(sb.toString());
        return optResult;
    }

    /**
     * <p>
     * Description:新增用户
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_ADD_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    @SysControllerLog(desc = "新增用户")
    public OptResult addOperator(Operator operator, Model model) throws BizException {
        OptResult result = new OptResult();
        try {
            // 新增用户
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            operator.setPassword(encoder.encodePassword("000000", ""));
            operator.setStatus("01");//正常
            result = operatorService.create(operator);

        } catch (BizException e) {
            logger.error("新增用户异常", e.getMessage());
            throw new BizException("systemmanage.user.save.null", e.getErrorMessage());
        }
        return result;
    }

    /**
     * <p>
     * Description:修改用户
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_UPDATE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult updateOperator(Operator operator, Model model) throws BizException {

        OptResult result = new OptResult();
        try {
            // 修改用户
            result = operatorService.update(operator);
        } catch (BizException e) {
            logger.error("修改用户异常", e.getMessage());
            throw new BizException("systemmanage.user.update.null", e.getErrorMessage());
        }
        return result;
    }

    /**
     * <p>
     * Description:修改用户密码
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {
            ISysManageControllerPathDef.USER_PASSWORD_UPDATE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult updateOperatorPassword(OperatorVO operator, Model model) throws BizException {

        Operator currentOperator = getCurrentOperator();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        if (!currentOperator.getPassword().equals(encoder.encodePassword(operator.getOldPassword(), ""))) {
            // 旧密码错误
            return OptResult.failure("user.password.error");
        }

        currentOperator.setPassword(encoder.encodePassword(operator.getPassword(), ""));

        try {
            // 修改用户
            OptResult result = operatorService.update(currentOperator);
            if (result.isSuccess()) {
                return OptResult.success();
            } else {
                return OptResult.failure("systemmanage.user.update.null");
            }
        } catch (BizException e) {
            logger.error("修改用户异常", e.getMessage());
            return OptResult.failure("systemmanage.user.update.null");
        }
    }

    /**
     * <p>
     * Description:删除用户
     * </p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_DELETE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult deleteOperator(Operator operator, Model model) throws BizException {
        OptResult result = new OptResult();
        // 删除用户
        try {
            result = operatorService.remove(operator.getOperatorId());
        } catch (BizException e) {
            logger.error("删除用户异常", e.getMessage());
            throw new BizException("systemmanage.user.delete.null", e.getErrorMessage());
        }

        return result;
    }

    /**
     * 权限维护
     *
     * @param operId
     * @param roleLike
     * @param model
     * @return
     * @throws BizException
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_DELETE_ROLEREL_API}, method = RequestMethod.POST)
    @ResponseBody
    public int insertOperatorRoleRel(@RequestParam("operId") String operId, @RequestParam("roleLike") String roleLike,
                                     Model model) throws BizException {
        int result = 0;
        if (operId != null && !"".equals(operId)) {
            int countRole = operatorService.countOperRoleRelByOperId(Integer.parseInt(operId));
            if (countRole > 0) {
                operatorService.deleteOperRoleRelByOperId(Integer.parseInt(operId));
            }
            if (roleLike != null && !"".equals(roleLike)) {
                String[] roleSplit = roleLike.split(",");
                for (int i = 0; i < roleSplit.length; i++) {
                    OperRoleRel operRoleRel = new OperRoleRel();
                    operRoleRel.setOperatorId(Integer.parseInt(operId));
                    operRoleRel.setRoleId(Integer.parseInt(roleSplit[i]));
                    result = operatorService.insertOperRoleRelByOperIdAndRoleId(operRoleRel);
                }
            }
        }
        return result;
    }
}
