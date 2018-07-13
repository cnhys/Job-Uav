package com.vt.base;

/**
 * <p> Title:系统管理常量定义类</p>
 * <p> Description:定义页面文件的包路径，文件名</p>
 * <p> Copyright: Copyright (c) 2015 </p>
 * <p> Company: InfoSys.co.,ltd </p>
 *
 * @author john
 * @version 1.0
 */
public interface ISysManageControllerPathDef {

    /**
     * EasyUI
     */
    String UI_MAIN_CONTROLLER_URL = "/";

    String UI_MAIN_FILE_PATH = "/main";

    String CURRENT_OPERATOR_MAIN_MENU_API = "/api/systemManage/queryCurrentOperatorMainMenu"; // 获取当前用户主菜单
    String CURRENT_OPERATOR_SUB_MENU_API = "/api/systemManage/queryCurrentOperatorSubMenu"; // 获取当前用户子级菜单

    /**
     * 登录页面
     */
    String USER_LOGIN_URL = "/login";
    //String USER_LOGIN_ERROR_PATH = "/login?error";
    String USER_LOGIN_FILE_PATH = "/login";

    /**
     * 表单元素后台校验
     */
    String VALIDATOR_ID_NO_API = "/validator/idNo";
    String VALIDATOR_ORG_NO_API = "/validator/orgNo";
    String VALIDATOR_USER_NAME_API = "/validator/userName";

    /**
     * 用户管理
     */
    String USER_FILTER_CONTROLLER_URL = "/user";//自定义用户查询拦截路径
    String USER_FILTER_FILE_PATH = "systemManage/user";//用户查询页面路径
    String USER_FILTER_CONTROLLER_API = "/api/systemManage/userFilter";//用户ajax查询拦截路径
    String USER_ADD_CONTROLLER_API = "/api/systemManage/userAdd";//自定义用户新增拦截路径
    String USER_UPDATE_CONTROLLER_API = "/api/systemManage/userUpdate";//自定义用户修改拦截路径
    String USER_DELETE_CONTROLLER_API = "/api/systemManage/userDelete";//自定义用户删除拦截路径
    //String USER_VIEW_CONTROLLER_PATH = "/userView";//自定义用户查看拦截路径
    String USER_FILTER_ROLEREL_API = "/api/systemManage/operRoleFilter";
    String USER_DELETE_ROLEREL_API = "/api/systemManage/insertRoleFilter";
    String USER_PASSWORD_UPDATE_CONTROLLER_API = "/api/systemManage/changePassword";//自定义用户修改密码拦截路径

	
/*	String USER_ADD_FILE_PATH = "user/operatorAdd";//用户新增页面路径
	String USER_UPDATE_FILE_PATH = "user/operatorUpdate";//用户修改页面路径
	String USER_DELETE_FILE_PATH = "user/operatorDelete";//用户删除页面路径
	String USER_VIEW_FILE_PATH = "user/operatorView";//用户查看页面路径
*/
    /**
     * 资源管理
     */
    String RESOURCE_MANAGE_URL = "/resource";//访问资源管理功能首页映射路径
    String RESOURCE_FILTER_PATH = "systemManage/resource";//资源查询页面路径
    //String RESOURCE_ADD_PATH = "/resourceAdd";//新增资源
    String RESOURCE_QUERY_API = "/api/systemManage/resourceQuery";//查询资源
    String RESOURCE_INIT_TREE_API = "/api/systemManage/initTreeData";
    String RESOURCE_METHOD_USER_BTN_QUERY_API = "/api/systemManage/queryUserBtnResources"; // 查询用户按钮资源
    String RESOURCE_QUERY_LIST_API = "/api/systemManage/resource1";//列表查询
    String RESOURCE_SAVE_API = "/api/systemManage/resourceSave";


    /**
     * 机构管理
     */
    String ORG_FILTER_CONTROLLER_URL = "/organization";//自定义机构查询拦截路径
    String ORG_FILTER_FILE_PATH = "systemManage/organization";//机构查询页面路径

    String ORG_FILTER_CONTROLLER_API = "/api/systemManage/orgFilter";
    String ORG_ADD_CONTROLLER_API = "/api/systemManage/orgAdd";//自定义机构新增拦截路径
    String ORG_UPDATE_CONTROLLER_API = "/api/systemManage/orgUpdate";//自定义机构修改拦截路径
    String ORG_DELETE_CONTROLLER_API = "/api/systemManage/orgDelete";//自定义机构删除拦截路径
    String ORG_VIEW_FROM_ORGID_API = "/api/systemManage/organizationQuery";
    String ORG_INIT_TREEDATA_API = "/api/systemManage/initOrganizationTreeData";
    //String ORG_VIEW_CONTROLLER_PATH = "/orgView";//自定义机构查看拦截路径
/*	String ORG_ADD_FILE_PATH = "org/organizationAdd";//机构新增页面路径
	String ORG_UPDATE_FILE_PATH = "org/organizationUpdate";//机构修改页面路径
	String ORG_DELETE_FILE_PATH = "org/organizationDelete";//机构删除页面路径
	String ORG_VIEW_FILE_PATH = "org/organizationView";//机构查看页面路径
*/
    /**
     * 角色管理
     */

    String ROLE_MANAGE_FILTER_URL = "/role"; // role info filter url
    String ROLE_MANAGE_FILTER_PATH = "systemManage/role"; // role filter html path

    String ROLE_MANAGE_FILTER_API = "/api/systemManage/roleFilter";
    String ROLEREL_MANAGE_FILTER_API = "/api/systemManage/roleRelFilter"; // role info filter url
    String ROLE_MANAGE_ADD_API = "/api/systemManage/roleAdd"; // role add url
    String ROLE_MANAGE_UPDATE_API = "/api/systemManage/roleUpdate"; // role update url
    String ROLE_MANAGE_VIEW_API = "/api/systemManage/roleView"; // role view url
    String ROLE_MANAGE_DELETE_API = "/api/systemManage/roleDelete"; // role delete url
    String ROLE_MANAGE_UPDATESTATUS_API = "/api/systemManage/roleUpdateSatus"; // role delete url
    String ROLE_MANAGE_POPUP_API = "/api/systemManage/rolePopup"; // role popup url
    String ROLE_ALL_URL_LINKURES_API = "/api/systemManage/queryAllRole";
    String ROLE_RES_FOR_RESTREE_API = "/api/systemManage/queryResIdByRoleId";
    // METHOD URL
    String ROLE_METHOD_URL_ISROLECODENOTEXIST_API = "/api/systemManage/isRoleCodeNotExist"; // judge roleCode is exist or not
    String ROLE_METHOD_URL_QUERYMGRROLES_API = "/api/systemManage/queryMgrRoles"; // query manage role list
    String ROLE_METHOD_URL_QUERYROLERESREL_API = "/api/systemManage/queryRoleResRel"; // query roleResRel list
    String ROLE_METHOD_URL_SAVEROLERESRELS_API = "/api/systemManage/saveRoleResRels"; // save roleResRel
    String ROLE_METHOD_URL_LINKRESOURCE_API = "/api/systemManage/queryLinkResource";
	
	
	
/*	String ROLE_MANAGE_ADD_PATH = "role/roleAdd"; // role add html path
	String ROLE_MANAGE_UPDATE_PATH = "role/roleUpdate"; // role update html path
	String ROLE_MANAGE_VIEW_PATH = "role/roleView"; // role view html path
*/

    /**
     * post manage
     */
    // PAGE URL
    String POST_MANAGE_FILTER_URL = "/post"; // post info filter url
    String POST_MANAGE_FILTER_PATH = "systemManage/post"; // post filter html path

    String POST_MANAGE_FILTER_API = "/api/systemManage/postFilter";//ajax岗位列表
    String POST_MANAGE_ADD_API = "/api/systemManage/postAdd"; // post add url
    String POST_MANAGE_UPDATE_API = "/api/systemManage/postUpdate"; // post update url
    String POST_MANAGE_VIEW_API = "/api/systemManage/postView"; // post view url
    String POST_MANAGE_DELETE_API = "/api/systemManage/postDelete"; // post delete url
    String POST_MANAGE_POPUP_API = "/api/systemManage/postPopup"; // post popup url

    // METHOD URL
    String POST_METHOD_URL_ISPOSTCODENOTEXIST_API = "/api/systemManage/isPostCodeNotExist"; // judge postCode is exist or not
    String POST_METHOD_URL_QUERYPOSTROLEREL_API = "/api/systemManage/queryPostRoleRel"; // query postRoleRel list
    String POST_METHOD_URL_SAVEPOSTROLERELS_API = "/api/systemManage/savePostRoleRels"; // save postRoleRels
    String POST_METHOD_URL_INITPOSTLISTDATA_API = "/api/systemManage/initPostListData";//初始化岗位下拉列表
	
	
/*	String POST_MANAGE_ADD_PATH = "post/postAdd"; // post add html path
	String POST_MANAGE_UPDATE_PATH = "post/postUpdate"; // post update html path
	String POST_MANAGE_VIEW_PATH = "post/postView"; // post view html path
*/
    /**
     * 字典管理
     */
    String BASE_DICT_QUERY_CONTROOLE_URL = "/dictionary";
    String BASE_DICT_FILTER_PATH = "systemManage/dictionary";

    String BASE_DICT_QUERY_CONTROOLE_API = "/api/systemManage/dictQuery";
    String BASE_DICT_QUERYALLPAGE_CONTROOLE_API = "/api/systemManage/dictAllQuery";
    String BASE_DICTITEM_QUERY_CONTROOLE_API = "/api/systemManage/dictItemQuery";
    String BASE_DICTITEM_QUERYREL_CONTROOLE_API = "/api/systemManage/dictItemRelQuery";
    String BASE_DICT_UPDATE_DICTTYPE_API = "/api/systemManage/dictUpdate";
    String BASE_DICT_ADD_DICTTYPE_API = "/api/systemManage/dictAdd";
    String BASE_DICT_ADD_DICTITEMREL_API = "/api/systemManage/dictItemRelAdd";
    String BASE_DICTITEM_ADD_DICT_API = "/api/systemManage/dictItemAdd";
    String BASE_DICT_QUERY_SUB_ITEMS_API = "/api/systemManage/querySubItems";
    String BASE_DICTITEM_QUERY_BY_TYPECODE_CONTROOLE_API = "/api/systemManage/dictItemQueryByTypeCode";

    /**
     * 参数管理
     */
    String SYSPARAM_QUERY_CONTROLLER_URL = "/parameter";//系统参数询拦截路径
    String SYSPARAM_FILTER_FILE_PATH = "systemManage/parameter";//系统参数查询页面路径

    String SYSPARAM_QUERY_CONTROLLER_API = "/api/systemManage/paramFilter";//系统参数询拦截路径
    String SYSPARAM_ADD_CONTROLLER_API = "/api/systemManage/paramAdd";//系统参数新增拦截路径
    String SYSPARAM_UPDATE_CONTROLLER_API = "/api/systemManage/paramUpdate";//系统参数修改拦截路径
    String SYSPARAM_DELETE_CONTROLLER_API = "/api/systemManage/paramDelete";//系统参数删除拦截路径
    //String SYSPARAM_VIEW_CONTROLLER_PATH = "/paramView";//系统参数查看拦截路径
    String SYSPARAM_LISTGET_CONTROLLER_API = "/api/systemManage/paramListGet";//系统参数List获得
    String SYSPARAM_SAVE_CONTROLLER_API = "/api/systemManage/paramSave";//系统参数修改
    
    String SWITCH_PARAM_LISTGET_CONTROLLER_API = "/api/switchParam/paramListGet";//系统参数List获得
    String SWITCH_PARAM_SAVE_CONTROLLER_API = "/api/switchParam/paramSave";//系统参数修改
	
	
	
/*	String SYSPARAM_ADD_FILE_PATH = "base/param/paramAdd";//系统参数新增页面路径
	String SYSPARAM_UPDATE_FILE_PATH = "base/param/paramUpdate";//系统参数修改页面路径
	String SYSPARAM_DELETE_FILE_PATH = "base/param/paramDelete";//系统参数删除页面路径
	String SYSPARAM_VIEW_FILE_PATH = "base/param/paramView";//系统参数查看页面路径
*/
    /**
     * 消息管理
     */
    String SYSTEM_MESSAGE_METHOD_URL_SYSTEM_MESSAGE_FILTER_API = "/api/systemManage/systemMessageFilter"; // 站内消息查询
    //String SYSTEM_MESSAGE_METHOD_URL_SYSTEM_MESSAGE_DETAIL = "/systemMessageDetail"; // 站内消息详情
    String SYSTEM_MESSAGE_METHOD_URL_UNREAD_MESSAGE_COUNT_API = "/api/systemManage/systemMessageUnreadCount"; // 站内消息未读数量
    String SYSTEM_MESSAGE_METHOD_URL_CHANGE_MESSAGE_STATUS_API = "/api/systemManage/systemMessageStatusChange"; // 修改站内消息状态

    /**
     * 数据库管理 SQL执行功能
     */
    String SQL_QUERY_CONTROLLER_URL = "/sql";//sql执行请求路径
    String SQL_QUERY_CONTROLLER_API = "/api/systemManage/sql";//sql执行异步拦截路径
    String SQL_FILTER_FILE_PATH = "systemManage/sql";//SQL查询返回路径

    /**
     * 工作流管理
     */
    String WF_BIZPROCCONFIG_QUERY_CONTROLLER_URL = "/bizProcConf";//工作流业务流程配置查询拦截路径
    String WF_BIZPROCCONFIG_FILTER_PATH = "workflow/bizProcConf";//工作流业务流程配置查询页面路径
    String WF_BIZPROCCONFIG_QUERY_CONTROLLER_API = "/api/workflow/bizProcConfFilter";//工作流业务流程配置查询拦截路径
    String WF_BIZPROCCONFIG_ADD_CONTROLLER_API = "/api/workflow/bizProcConfAdd";//工作流业务流程配置新增拦截路径
    String WF_BIZPROCCONFIG_UPDATE_CONTROLLER_API = "/api/workflow/bizProcConfUpdate";//工作流业务流程配置修改拦截路径
    String WF_BIZPROCCONFIG_DELETE_CONTROLLER_API = "/api/workflow/bizProcConfDelete";//工作流业务流程配置删除拦截路径
    String WF_BIZPROCCONFIG_RESOURCE_CONTROLLER_API = "/api/workflow/bizProcConfResourceTree";//工作流业务流程配置资源拦截路径
    String WF_BIZPROCCONFIG_PROCESS_CONTROLLER_API = "/api/workflow/bizProcConfProcessBox";//工作流业务流程配置流程拦截路径
    String WF_BIZPROCCONFIG_ACTIVIT_CONTROLLER_API = "/api/workflow/bizProcConfActivitBox";//工作流业务流程配置环节拦截路径
    String WF_BIZPROCCONFIG_ROLE_CONTROLLER_API = "/api/workflow/bizProcConfRoleBox";//工作流业务流程配置角色拦截路径

}
