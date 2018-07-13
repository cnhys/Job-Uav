package com.vt;

/**
 * 系统管理常量类
 *
 * @author joy.qiao
 * @time Jul 9, 2015
 */
public class SystemConst {

    /**
     * 账务日期在参数表中的paramName
     */
    public static String BUSINESS_DAY = "BUSINESS_DAY";
    /**
     * 结算系统状态在参数表中的paramName
     */
    public static String SYSTEM_STATUS = "SYSTEM_STATUS";

    /**
     * 营业日的paramName
     */
    public static String CURRENT_DAY = "BUSINESS_DAY";
    /**
     * 结算系统状态-已签到
     */
    public static String SYSTEM_STATUS_TRADABLE = "02";
    /**
     * 结算系统状态-未签到或已签退
     */
    public static String SYSTEM_STATUS_NOT_TRADABLE = "01";

    /**
     * 系统保留额度在参数表中的paramName
     */
    public static String RESERVED_LIMIT = "RESERVED_LIMIT";

    /**
     * 费用类型在参数表中的paramName - 人民币代付额度提取手续费
     */
    public static String P_FEE_TYPE_RMB_DFEDTQ = "RMB_DFEDTQ";
    /**
     * 费用类型在参数表中的paramName - 人民币提前偿还手续费
     */
    public static String P_FEE_TYPE_RMB_TQCH = "RMB_TQCH";
    /**
     * 费用类型在参数表中的paramName - 黄金支付手续费
     */
    public static String P_FEE_TYPE_GOLD_ZF = "GOLD_ZF";
    /**
     * 费用类型在参数表中的paramName - 黄金代付额度提取手续费
     */
    public static String P_FEE_TYPE_GOLD_DFEDTQ = "GOLD_DFEDTQ";
    /**
     * 费用类型在参数表中的paramName - 黄金提前偿还手续费
     */
    public static String P_FEE_TYPE_GOLD_TQCH = "GOLD_TQCH";
    /**
     * NC服务Ip
     */
    public static String P_ICBC_NC_SERVER_ADDR = "ICBC_POST_ADDRESS";
    /**
     * 消息状态
     */
    public static String SYS_MESSAGE_STATUS_NOT_SEND = "01"; //未发送
    public static String SYS_MESSAGE_STATUS_SECCUSS = "02"; //发送成功
    public static String SYS_MESSAGE_STATUS_FAIL = "03"; //发送失败
    public static String SYS_MESSAGE_STATUS_UNREAD = "04"; // 未读
    public static String SYS_MESSAGE_STATUS_READ = "05"; // 已读
    /**
     * 消息类型_手机
     */
    public static final String SYS_MESSAGE_TYPE_MOBILE = "01";
    /**
     * 消息类型_Email
     */
    public static final String SYS_MESSAGE_TYPE_EMAIL = "02";
    /**
     * 消息类型_系统
     */
    public static final String SYS_MESSAGE_TYPE_SYSTEM = "03";
    /**
     * 消息类型_站内信
     */
    public static final String SYS_MESSAGE_TYPE_INNER_MSG = "04";
    /**
     * 系统消息时发送人ID
     */
    public static final Integer SYS_SEND_ID = -1;

    /**
     * 用户状态 - 生效
     */
    public static final String USER_NORMAL = "1";

    /**
     * 用户状态 - 失效
     */
    public static final String USER_INVALID = "2";

    /**
     * 经办角色对应角色ID
     */
    public static final Integer ROLE_ID_JB = 1201;

    /**
     * 生效
     */
    public static final String SYS_PARAM_STATUS_VALIDATE = "1";

    /**
     * 失效
     */
    public static final String SYS_PARAM_STATUS_INVALIDATE = "2";
    /**
     * 支付回调b2b URL
     */
    public static final String CALLBACK_B2B_URL = "CALLBACK_B2B_URL";

    public static final String WF_STATUS_TOAUDIT = "01"; // 待审核
    public static final String WF_STATUS_TOAPPROVE = "02"; // 待审批
    public static final String WF_STATUS_PASS = "03"; // 通过
    public static final String WF_STATUS_REJECT = "04"; // 拒绝
}
