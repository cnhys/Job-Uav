package com.vt;

/**
 * <h1>常量定义接口</h1>
 * User: zhangtao Date: 13-11-24 Time: 下午6:42
 */
public interface IConst {
    /**
     * 默认字符集
     */
    String DEFAULT_CHARSET = "UTF-8";
    /**
     * 上下文路径
     */
    String CONTEXT_PATH = "ctxPath";
    /**
     * 列表页大小
     */
    int PAGE_SIZE = 10;
    /**
     * 当前登录用户的Key
     */
    String CURRENT_USER = "_CURRENT_USER";
    /**
     * 当前登录人所在公司
     */
    String CURRENT_COMPANY = "_CURRENT_COMPANY";
    /**
     * 当前登录人所在部门
     */
    String CURRENT_ORG = "_CURRENT_ORG";
    /**
     * 当前登录人的所有角色
     */
    String CURRENT_ROLES = "_CURRENT_ROLES";
    /**
     * 当前登录人的所有菜单
     */
    String CURRENT_MENUS = "_CURRENT_MENUS";
    /**
     * 当前登录人菜单模型
     */
    String CURRENT_MENU_MODEL = "_CURRENT_MENU_MODEL";
    /**
     * 放到session中的key
     */
    String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
    /**
     * 当前用户未读站内信数量
     */
    String CURRENT_UNREAD_MSG_COUNT = "_CURRENT_UNREAD_MSG_COUNT";
    /**
     * Session状态
     */
    String SESSION_STATUS = "_SESSION_STATUS";
    /**
     * Session状态：有效
     */
    String SESSION_STATUS_VALID = "1";
    /**
     * Session状态：无效
     */
    String SESSION_STATUS_INVALID = "0";
    /**
     * 默认公司ID
     */
    Integer DEFAULT_COMPANY_ID = 1;
    /**
     * 状态_已保存
     */
    String STATUS_SAVE = "1";
    /**
     * 状态_已生效
     */
    String STATUS_EFFECTIVE = "2";
    /**
     * 状态_已拒绝
     */
    String STATUS_REJECTED = "3";
    /**
     * 状态_已发货
     */
    String STATUS_SENT = "4";
    /**
     * 状态_已签收
     */
    String STATUS_SIGNED = "5";
    /**
     * 结果_成功
     */
    String RESULT_SUCCESS = "1";
    /**
     * 结果_失败
     */
    String RESULT_FAILURE = "2";
    /**
     * 默认的用户代理
     */
    String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.2) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13";

    /**
     * Regular expression
     */
    String REGEX_COMMA = ","; // comma
    String REGEX_LINE = "-"; // line
    String REGEX_VERTICAL_LINE = "\\|"; // vertical line

    /**
     * 空字符串
     */
    String BLANK = "";

    /**
     * 状态
     */
    String STATUS_YES = "01"; // 生效
    String STATUS_NO = "02"; // 失效

    String CURRENT_BUSINESS_DAY = "_CURRENT_BUSINESS_DAY";

    /**
     * 登陆失败标志(用于login.html校验是否登陆失败)
     */
    String LOGIN_ERROR_FLAG = "_LOGIN_ERR";

    /**
     * 登陆失败URL中所包含参数
     */
    String LOGIN_ERROR_PARAM = "error";

    /**
     * 应用版本号(前端，防止缓存)
     */
    String VERSON = "version";

    /**
     * 全部字典标识
     */
    String DICT_ALL = "all";
}
