package com.vt.base.service.impl;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vt.SystemConst;
import com.vt.base.IServiceDef;
import com.vt.base.OptResult;
import com.vt.base.client.SystemMessageMapper;
import com.vt.base.exception.BizException;
import com.vt.base.mapper.IBaseMapper;
import com.vt.base.model.SystemMessage;
import com.vt.base.model.SystemMessageExample;
import com.vt.base.service.BaseService;
import com.vt.base.service.ISystemMessageService;
import com.vt.base.util.DateUtil;
import com.vt.base.util.StringUtil;

/**
 * 系统消息表service服务类
 *
 * @author joy.qiao
 * @time Jul 13, 2015
 */
@Service(IServiceDef.SYS_MESSAGE_SERVICE)
public class SystemMessageServiceImpl extends BaseService<SystemMessage, SystemMessageExample, Integer> implements ISystemMessageService {

    /**
     *
     */
    private static final long serialVersionUID = 2144732216034481203L;
    @Autowired
    private SqlSession sqlSession;

    private SystemMessageMapper mapper;

    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        super.init();
        mapper = sqlSession.getMapper(SystemMessageMapper.class);
        logger.info("system message Service init successfully.");
    }

    @Override
    public IBaseMapper<SystemMessage, SystemMessageExample, Integer> getMapper() {
        return mapper;
    }

    @Override
    public OptResult buildMobleMessage(Integer receiverId, String receiverMobile, String content) throws BizException {
        /* 1. 校验 */
        if (receiverId == null) {
            return OptResult.failure("SystemMessage.buildMobleMessage.receiverId.null");
        }
        if (StringUtil.isBlank(receiverMobile)) {
            return OptResult.failure("SystemMessage.buildMobleMessage.receiverMobile.null");
        }
        if (StringUtil.isBlank(content)) {
            return OptResult.failure("SystemMessage.buildMobleMessage.content.null");
        }
		
		/* 2. 构建消息 */
        SystemMessage systemMessage = new SystemMessage();
        // 2.1 发送ID，取 “-1” 表示系统
        systemMessage.setSenderId(SystemConst.SYS_SEND_ID);
        // 2.2 receiverId – 取步骤1中的会员信息的ID
        systemMessage.setReceiverId(receiverId);
        // 2.3 receiverMobile – 取步骤1中的会员的手机号
        systemMessage.setReceiverMobile(receiverMobile);
        // 2.4 messageType – 取SystemConst中的常量SYS_MESSAGE_TYPE_MOBILE，手机
        systemMessage.setMessageType(SystemConst.SYS_MESSAGE_TYPE_MOBILE);
        // 2.5 createTime – 取当前时间
        systemMessage.setCreateTime(DateUtil.getCurrentDateTime());
        // 2.6 status – 取SystemConst中的常量SYS_MESSAGE_STATUS_NOT_SEND，未发送
        systemMessage.setStatus(SystemConst.SYS_MESSAGE_STATUS_NOT_SEND);
        // 2.7 content – 取步骤2中的发送内容
        systemMessage.setContent(content);
		
		/* 3. 保存消息 */
        OptResult result = create(systemMessage);

        return result;
    }

}
