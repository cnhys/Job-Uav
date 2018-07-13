package com.vt.base;

/**
 * <p> Title:系统管理服务定义类</p>
 * <p> Description:提供系统管理模块所有服务类名的常量定义</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company:xxx.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public interface IServiceDef {
    /**
     * 报文处理服务
     */
    String MESSAGE_SERVICE = "_messageService";
    /**
     * 报文数据校验服务
     */
    String MESSAGE_VALIDATE_SERVICE = "_messageValidateService";
    /**
     * 用户管理服务类名
     */
    String OPERATOR_SERVICE = "_operatorService";
    /**
     * 机构管理服务类名
     */
    String ORGANIZATION_SERVICE = "_organizationService";
    /**
     * 登录服务
     */
    String LOGIN_SERVICE = "_loginService";

    /**
     * 资源管理服务
     */
    String RESOURCE_SERVICE = "_resourceService";
    /**
     * 数据库管理服务
     */
    String DBUTIL_SERVICE = "_dbUtilService";

    /**
     * 流水号管理服务
     */
    String SERIALNO_SERVICE = "_serialNoService";

    /**
     * Role Manage Service
     */
    String ROLE_MANAGE_SERVICE = "_roleManageService";

    /**
     * RoleRes Manage Service
     */
    String ROLERES_MANAGE_SERVICE = "_roleResManageService";

    /**
     * Post Manage Service
     */
    String POST_MANAGE_SERVICE = "_postManageService";

    /**
     * 日志管理服务
     */
    String SYSLOG_SERVICE = "_sysLogService";

    /**
     * 字典类型服务
     */
    String DICT_TYPE_SERVICE = "_dictService";
    /**
     * 字典项服务
     */
    String DICT_ITEM_SERVICE = "_dictItemService";
    String DICT_ITEMREL_SERVICE = "_dictItemRelService";
    /**
     * 系统参数服务
     */
    String SYS_PARAM_SERVICE = "_sysParamService";
    /**
     * 系统消息服务
     */
    String SYS_MESSAGE_SERVICE = "_sysMessageService";
    /**
     * 节假日服务
     */
    String HOLIDAY_SERVICE = "_holidayService";

    /**
     * 工作流服务
     */
    String WORKFLOW_SERVICE = "_workFlowService";
    String EMPLOYEE_SERVICE = "_employeeService";
    String EMPLOYEE_HIS_SERVICE = "_hisEmployeeService";
    String BIZPROCONF_SERVICE = "_bizProConfService";

    /**
     * 报文处理服务
     */
    String MSG_PROCESS_SERVICE = "_msgProcessService";

    /**
     * 报文成员级处理服务
     */
    String MSG_MEMBER_HANDLER_ICBC = "_ICBCMsgMemberHandler"; // 工行
    String MSG_MEMBER_HANDLER_PLAIN = "_PlainMsgMemberHandler"; // 朴实B2B

    /**
     * 会员管理服务
     */
    String MEMBER_MANAGER_SERVICE = "_memberManagerService"; // 会员管理
    String MEMBER_ACCOUNT_SERVICE = "_memberAccountService"; // 会员用户
    String MEMBER_ACCOUNT_HIS_SERVICE = "_memberAccountHisService"; // 会员用户历史
    String MEMBER_HISTORY_SERVICE = "_memberHistoryService"; // 会员历史
    String MEMBER_MATERIAL_SERVICE = "_memberMaterialService"; // 会员材料
    String MEMBER_AUTHORITY_SERVICE = "_memberAuthorityService"; // 会员用户权限
    String MEMBER_AUTHORITY_HIS_SERVICE = "_memberAuthorityHisService"; // 会员用户权限历史
    /**
     * 台账服务
     */
    String LEDGER_MSG_SERVICE = "_ledgerService"; //台账相关service服务
    String LEDGER_DETAILS_SERVICE = "_ledgerDetailsService"; //台账流水服务
    String LEDGER_Daily_SERVICE = "_DailyLedgerService"; //台账编号服务

    /**
     * 登记簿服务 Register Service
     */
    String REGISTER_SERVICE = "_registerService";
    /**
     * 登记簿历史服务 Register History Service
     */
    String REGISTER_HISTORY_SERVICE = "_registerHistoryService";

    /**
     * 记账服务
     */
    String ACCOUNT_SERVICE = "_accountService"; //台账流水相关service服务
    /**
     * 授信服务
     */
    String CREDIT_MSG_SERVICE = "_creditApplyService";
    /**
     * 出金服务
     */
    String WITHDRAW_MSG_SERVICE = "_withdrawApplyService";
    /**
     * 提金服务
     */
    String PICKGOLD_MSG_SERVICE = "_pickGoldApplyService";
    /**
     * 支付服务
     */
    String PAY_MSG_SERVICE = "_payApplyService";
    /**
     * 代付偿还服务
     */
    String HELYBUY_PAY_MSG_SERVICE = "_helybuyPayApplyService";
    /**
     * 黄金价格参数服务
     */
    String GOLDPRICE_PARAM_MSG_SERVICE = "_goldPriceParamService";

    /**
     * 报文处理服务
     */
    String PLAIN_MSG_HANDLER = "_plainMsgHandler"; // 朴实B2B
    String ICBC_MSG_HANDLER = "_icbcMsgHandler"; // 工行
    /**
     * 外呼报文工具service
     */
    String PLAIN_MSG_SERVICE = "_plainMsgService"; // 朴实B2B
    String ICBC_MSG_SERVICE = "_icbcMsgService"; // 工行
    /**
     * sql执行service
     */
    String SQL_SERVICE = "_sqlService";
    /**
     * 常用控件使用演示
     */
    String DEMO_ROLE_SERVICE = "_demoRoleService";
    String DEMO_RESOURCE_SERVICE = "_demoResourceService";
}
