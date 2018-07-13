/**
 *
 */
package com.vt.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vt.IConst;
import com.vt.SystemConst;
import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.controller.BaseRestController;
import com.vt.base.model.SystemMessageExample;
import com.vt.base.service.ISysParamService;
import com.vt.base.service.ISystemMessageService;
import com.vt.resource.service.IResourceService;
import com.vt.user.model.Operator;

/**
 * <h1>登录控制器</h1>
 *
 * @author Tony_Zhang05
 * @version 1.0
 */
@Controller
public class LoginController extends BaseRestController {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3998998359281453734L;

    /**
     * 系统消息服务
     */
    @Autowired
    @Qualifier(IServiceDef.SYS_MESSAGE_SERVICE)
    ISystemMessageService systemMessageService;

    /**
     * 系统参数服务
     */
    @Autowired
    @Qualifier(IServiceDef.SYS_PARAM_SERVICE)
    ISysParamService sysParamService;

    @Autowired
    @Qualifier(IServiceDef.RESOURCE_SERVICE)
    private IResourceService resourceService;

    /**
     * <p> Description:显示用户查询页面</p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.USER_LOGIN_URL}, method = RequestMethod.GET)
    public String showLoginView(Model model, HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();
        // URL中包含error参数，则用户验证失败，向页面添加“登陆失败标识”
        if (parameterMap.containsKey(IConst.LOGIN_ERROR_PARAM)) {
            model.addAttribute(IConst.LOGIN_ERROR_FLAG, 1);
        }
        return ISysManageControllerPathDef.USER_LOGIN_FILE_PATH;
    }

    @RequestMapping(value = {"/easyui"}, method = RequestMethod.GET)
    public String showMainView(Model model) {


        return "easyui";
    }


    /**
     * <p> Description:显示用户查询页面</p>
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.UI_MAIN_CONTROLLER_URL}, method = RequestMethod.GET)
    public String showOperator(Model model) {

        // 获取后台未读消息数量
        SystemMessageExample systemMessageExample = new SystemMessageExample();

        Operator operator = getCurrentOperator();
        systemMessageExample.createCriteria().andReceiverIdEqualTo(operator.getOperatorId())
                .andMessageTypeEqualTo(SystemConst.SYS_MESSAGE_TYPE_INNER_MSG)
                .andStatusEqualTo(SystemConst.SYS_MESSAGE_STATUS_UNREAD);
        Integer unreadMsgCount = systemMessageService.getResultCount(systemMessageExample);
        model.addAttribute(IConst.CURRENT_UNREAD_MSG_COUNT, unreadMsgCount);

        return ISysManageControllerPathDef.UI_MAIN_FILE_PATH;
    }
}
