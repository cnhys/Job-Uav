package com.vt.base.controller;

import org.apache.commons.lang3.StringUtils;
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
import com.vt.base.model.Param;
import com.vt.base.model.ParamExample;
import com.vt.base.service.IDBUtilService;
import com.vt.base.service.ISysParamService;
import com.vt.base.util.StringUtil;


/**
 * @author july
 * @version V1.0
 * @Title: SysParamController.java
 * @Package com.vt.base.controller
 * @Description: TODO
 * @date Jun 3, 2015 3:12:56 PM
 */
@Controller
public class ParamController extends BaseRestController {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -7449850231030829614L;

    @Autowired
    @Qualifier(IServiceDef.DBUTIL_SERVICE)
    private IDBUtilService dbUtilService;
    /**
     * 参数管理服务
     */
    @Autowired
    @Qualifier(IServiceDef.SYS_PARAM_SERVICE)
    private ISysParamService sysParamService;

    /**
     * <p> Description:显示系统参数查询页面</p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSPARAM_QUERY_CONTROLLER_URL}, method = RequestMethod.GET)
    public String showParam(Model model) {
        return ISysManageControllerPathDef.SYSPARAM_FILTER_FILE_PATH;
    }

    /**
     * <p> Description:系统参数查询</p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSPARAM_QUERY_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<Param> processFormData(Param param, PageRequest<ParamExample> request, Model model) {
        //封装查询条件
        ParamExample example = new ParamExample();
        ParamExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(param.getParamDesc())) {

            criteria.andParamDescLike(StringUtil.dbQueryLike(param.getParamDesc()));
        }

        if (StringUtils.isNotBlank(param.getParamName())) {
            criteria.andParamNameLike(StringUtil.dbQueryLike(param.getParamName()));
        }
        request.setCondition(example);
        //查询系统参数列表
        PageData<Param> paramList = sysParamService.query(request);

        return paramList;
    }

    /**
     * <p> Description:新增系统参数</p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSPARAM_ADD_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult addParam(Param param, Model model) {
        //新增系统参数
        Param paramValue = sysParamService.getById(param.getParamName());
        if (paramValue != null) {
            return OptResult.failure("system.param.exist");
        }
        //param.setStatus(SystemConst.SYS_PARAM_STATUS_VALIDATE); // 生效
        OptResult result = sysParamService.create(param);
        return result;
    }

    /**
     * <p> Description:修改系统参数</p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSPARAM_UPDATE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult updateParam(Param param, Model model) {
        //修改参数
        OptResult result = sysParamService.update(param);
        return result;
    }


    /**
     * <p> Description:删除系统参数</p>
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSPARAM_DELETE_CONTROLLER_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult deleteParam(Param param, Model model) {
        //删除系统参数
        OptResult result = sysParamService.delete(param.getParamName());
        return result;
    }
    

    
    
    


}
