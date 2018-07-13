package com.vt.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vt.SystemConst;
import com.vt.base.IServiceDef;
import com.vt.base.ISysManageControllerPathDef;
import com.vt.base.OptResult;
import com.vt.base.PageData;
import com.vt.base.PageRequest;
import com.vt.base.model.SystemMessage;
import com.vt.base.model.SystemMessageExample;
import com.vt.base.service.ISystemMessageService;
import com.vt.user.model.Operator;

/**
 * 消息控制器
 * <p> Title:  </p>
 * <p> Description: </p>
 *
 * @Author Will
 * @Time Jul 20, 2015 - 3:43:28 PM
 * @Version 1.00
 * @Record <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Will	Jul 20, 2015	release
 * ----------------------------------------
 * </pre>
 */
@Controller
public class SystemMessageController extends BaseRestController {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7492745008830574977L;

    /**
     * 系统消息服务
     */
    @Autowired
    @Qualifier(IServiceDef.SYS_MESSAGE_SERVICE)
    private ISystemMessageService systemMessageService;

    /**
     * 统计未读消息
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSTEM_MESSAGE_METHOD_URL_UNREAD_MESSAGE_COUNT_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult querySystemMessageUnreadCount(Model model) {
        SystemMessageExample systemMessageExample = new SystemMessageExample();

        Operator operator = getCurrentOperator();
        systemMessageExample.createCriteria().andReceiverIdEqualTo(operator.getOperatorId())
                .andMessageTypeEqualTo(SystemConst.SYS_MESSAGE_TYPE_INNER_MSG)
                .andStatusEqualTo(SystemConst.SYS_MESSAGE_STATUS_UNREAD);
        Integer unreadMsgCount = systemMessageService.getResultCount(systemMessageExample);
        OptResult optResult = new OptResult(true);
        optResult.setData(unreadMsgCount);
        return optResult;
    }

    /**
     * 分页查询站内信
     *
     * @param queryRequest
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSTEM_MESSAGE_METHOD_URL_SYSTEM_MESSAGE_FILTER_API}, method = RequestMethod.POST)
    @ResponseBody
    public PageData<SystemMessage> querySystemMessage(PageRequest<SystemMessageExample> queryRequest, Model model) {

        Operator operator = getCurrentOperator();
        SystemMessageExample systemMessageExample = new SystemMessageExample();

        systemMessageExample.createCriteria().andReceiverIdEqualTo(operator.getOperatorId())
                .andMessageTypeEqualTo(SystemConst.SYS_MESSAGE_TYPE_INNER_MSG);
        systemMessageExample.setOrderByClause("SENT_TIME DESC");
        queryRequest.setCondition(systemMessageExample);
        PageData<SystemMessage> pageData = systemMessageService.query(queryRequest);
        return pageData;
    }

    /**
     * 查询站内信详情
     * @param systemMessage
     * @param model
     * @return
     */
    /*@RequestMapping(value = {ISysManageControllerPathDef.SYSTEM_MESSAGE_METHOD_URL_SYSTEM_MESSAGE_DETAIL}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult querySystemMessageDetail(SystemMessage systemMessage, Model model) {

    	if (systemMessage == null || systemMessage.getMessageId() == null) {
			return new OptResult(false, "system.message.id.null");
		}
    	// 获取当前用户
    	Operator operator = getCurrentOperator();
    	// 获取Msg
    	SystemMessage returnMessage = systemMessageService.getById(systemMessage.getMessageId());
    	if (operator.getOperatorId().equals(systemMessage.getReceiverId())) {
    		// 组装返回值
        	OptResult optResult = new OptResult(true);
        	optResult.setData(returnMessage);
        	return optResult;
		} else {
			OptResult optResult = new OptResult(false, "privilege.permission.denied");
        	return optResult;
		}
    }*/

    /**
     * 修改站内信为已读状态
     *
     * @param systemMessage
     * @param model
     * @return
     */
    @RequestMapping(value = {ISysManageControllerPathDef.SYSTEM_MESSAGE_METHOD_URL_CHANGE_MESSAGE_STATUS_API}, method = RequestMethod.POST)
    @ResponseBody
    public OptResult systemMessageStatusChange(SystemMessage systemMessage, Model model) {

        if (systemMessage == null || systemMessage.getMessageId() == null) {
            return new OptResult(false, "system.message.id.null");
        }
        systemMessage.setStatus(SystemConst.SYS_MESSAGE_STATUS_READ);
        OptResult optResult = systemMessageService.update(systemMessage);

        if (optResult.isSuccess()) {
            // 获取当前用户
            Operator operator = getCurrentOperator();
            // 获取Msg
            SystemMessage returnMessage = systemMessageService.getById(systemMessage.getMessageId());
            if (operator.getOperatorId().equals(returnMessage.getReceiverId())) {
                // 组装返回值
                optResult = new OptResult(true);
                optResult.setData(returnMessage);
                return optResult;
            } else {
                optResult = new OptResult(false, "privilege.permission.denied");
                return optResult;
            }
        } else {
            return optResult;
        }
    }
}
