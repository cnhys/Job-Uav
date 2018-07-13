/**
 *
 */
package com.vt.base.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.validator.IdcardValidator;
import com.vt.base.validator.OrgNoValidator;
import com.vt.user.model.OperatorExample;
import com.vt.user.service.IOperatorService;

/**
 * <p> Title: 表单校验 </p>
 * <p> Description: 表单远程校验</p>
 *
 * @Author Will
 * @Time Aug 3, 2015 - 2:21:26 PM
 * @Version 1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Will	Aug 3, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Controller
public class DataValidatorController extends BaseRestController {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1673521137160924907L;

    /**
     * 用户服务
     */
    @Autowired
    @Qualifier(IServiceDef.OPERATOR_SERVICE)
    private IOperatorService operatorService;


    /**
     * 表单校验 - 身份证校验
     *
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.VALIDATOR_ID_NO_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult idNoValidate(@RequestParam("idNo") String idNo, Model model) {

        OptResult optResult = OptResult.success();
        optResult.setData(IdcardValidator.isValidatedAllIdcard(idNo));
        return optResult;
    }

    /**
     * 表单校验 - 组织机构代码校验
     *
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.VALIDATOR_ORG_NO_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult orgNoValidate(@RequestParam("orgNo") String orgNo, Model model) {

        OptResult optResult = OptResult.success();
        optResult.setData(OrgNoValidator.cheakOrgNo(orgNo));
        return optResult;
    }

    /**
     * 表单校验 - 用户名校验
     *
     * @param model model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.VALIDATOR_USER_NAME_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult userNameValidate(@RequestParam("userName") String userName, Model model) {

        OptResult optResult = OptResult.success();
        if (StringUtils.isBlank(userName)) {
            return optResult;
        }
        OperatorExample operatorExample = new OperatorExample();
        operatorExample.createCriteria().andUserNameEqualTo(userName);
        optResult.setData(operatorService.getResultCount(operatorExample) == 0);
        return optResult;
    }
}
