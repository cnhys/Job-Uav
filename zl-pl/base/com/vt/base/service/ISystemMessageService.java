package com.vt.base.service;

import com.vt.base.OptResult;
import com.vt.base.exception.BizException;
import com.vt.base.model.SystemMessage;
import com.vt.base.model.SystemMessageExample;

/**
 * 系统消息表service服务类
 *
 * @author joy.qiao
 * @time Jul 13, 2015
 */
public interface ISystemMessageService extends IBaseService<SystemMessage, SystemMessageExample, Integer> {

    /**
     * 构建短信消息
     * 1.	根据会员ID（会员编号）从会员服务中获取会员信息，该信息中含有会员的手机号（MOBILE）；
     * 2.	根据当前环节需要反馈的内容（需求文档中有说明），拼接业务数据，组装为实际需发送的内容；
     *
     * @param receiverId     接收ID，取步骤1中的会员信息的ID
     * @param receiverMobile 接收手机号，取步骤1中的会员的手机号
     * @param content        发送内容，取步骤2中的发送内容
     * @return
     * @throws BizException
     */
    public OptResult buildMobleMessage(Integer receiverId, String receiverMobile, String content) throws BizException;
}
