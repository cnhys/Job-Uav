package com.vt.base;

import java.math.BigDecimal;

public class BussConst {

    public static Integer ONE = new Integer("1");//常量1
    public static Integer ZERO = new Integer("0");//常量0
    public static BigDecimal ONE_HUNDRED = new BigDecimal("100");//常量100
    
    public static Integer annualYieldDays = new Integer("36500");//年化收益率天数-因后台传人的利率是乘以100后

    public static String OUTCOME_PREV = "prev"; //上一环节
    public static String OUTCOME_START = "start"; //上一环节
    public static String OUTCOME_INVALID = "I";//无效
    public static String INCOME_AGREE = "Y";//同意
    public static String INCOME_REJECT = "N";//不同意
    public static String INCOME_INVALID = "I";//无效

    public static String APPROVE_CONTINUE = "Y";//流程审批继续
    public static String APPROVE_OVER = "N";//流程审批结束

    public static String EMP_MANAGE = "F001";

    public static String PRC_INS_COMPLETE = "COMPLETE";//流程结束
    public static String PRC_INS_EXECUTION = "EXECUTION";//流程执行中

    public static String PRC_INS_FLAG = "prcInsFlag";//流程结束标志
    public static String PRC_INS_ID = "prcInsId";//流程实例ID
    
    /**
     * 消息状态
     */
    public static String MESSAGE_UNREAD="01"; //未读
    public static String MESSAGE_READ="02";   //已读
    public static String MESSAGE_DELETE="03"; //已删除
    
    /**
     * 消息交易类型
     */
    public static String TRADE_ZHUCE="注册";//注册
    public static String TRADE_BIAODIFABU="标的发布";//标的发布
    public static String TRADE_TOUBIAO="投标";//投标
    public static String TRADE_MANBIAO="满标";//满标
    public static String TRADE_LIUBIAO="流标";//流标
    public static String TRADE_FANGKUAN="放款";//放款
    public static String TRADE_BIAODIZHUANRANG="标的转让";//标的转让
    public static String TRADE_HUANKUAN="还款";//还款
    public static String TRADE_HUANHUIKUAN="还款/回款";//还款/回款
    public static String TRADE_HUANQING="还清";//还清
    public static String TRADE_GOUJIN="购金";//购金
    public static String TRADE_TIJIN="提金";//提金
    public static String TRADE_TIJINFAHUO="提金发货";//提金发货
    public static String TRADE_HUIGOU="回购";//回购
    public static String TRADE_CUNJIN="存金";//存金
    public static String TRADE_CHONGZHI="充值";//充值
    public static String TRADE_TIXIAN="提现";//提现
    public static String TRADE_HUANKUANTIXING="还款提醒";//还款提醒
    public static String TRADE_JINJIACAIJI="金价采集";//提现
    public static String TRADE_CUNJIN_DELETE="存金";//存金交易删除
    
    /**
     * 消息交易提醒人为云商平台
     */
    public static String MESSAGE_RECEIVER_YUNSHANG="0000";
    public static String MESSAGE_RECEIVER_GUANLIYUAN="0001";
    
    /**
     * 还款方式
     */
    public final static String REPAYMENT_DEBX="01"; //等额本息
    public final static String REPAYMENT_XXHB="02"; //先息后本
    public final static String REPAYMENT_YCJQ="03"; //一次结清
    
    /**
     * 标的类型
     */
    public final static String BIDTYPE_JSJ="01";	//金生金
    public final static String BIDTYPE_JSQ="02";	//金生钱
    public final static String BIDTYPE_QSQ="03";	//钱生钱
    
    /**
     * 还款状态
     */
    public static String REPAYMENT_STATUS_BIGIN="01"; //未还款
    public static String REPAYMENT_STATUS_OVER="02";  //已还款
    public static final String REPAYMENT_STATUS_TRANSFERED = "03";//已转让
    
    /**
     * 币种
     */
    public static String CURRENCY_RMB="01"; //人民币
    public static String CURRENCY_GOLD="02";//黄金
    
    /**
     *  期限类型
     */
    public static String TERM_TYPE_MONTH="01"; //月份
    public static String TERM_TYPE_DAY="02";   //天
    
    /**
     * 标的转让标志
     */
    
    public static String LOAN_BID_TRANSFER_YES="01";//是转让标的
    public static String LOAN_BID_TRANSFER_NO="02";//不是转让标的
    /**
     * 网站用户
     */
    public static final String USER_TYPE_MEM = "01";
    /**
     * 云商用户
     */
    public static final String USER_TYPE_CLOUD_BIZ = "02";
    /**
     * 管理用户
     */
    public static final String USER_TYPE_SYSTEM = "01";
    /**
     * 提现_已提交
     */
    public static final String WITHDRAW_SUBMITTED = "01";
    
    /**
     * 提现_已提交银行进行处理
     */
    public static final String WITHDRAW_BANK = "04";
    /**
     * 提现_成功
     */
    public static final String WITHDRAW_SUCCESS = "02";
    /**
     * 提现_失败
     */
    public static final String WITHDRAW_FAILURE = "03";
    
    /**
     * 卡类型
     */
    public static final String CARD_TYPE_PRI="01";//对私
    public static final String CARD_TYPE_PUB="02";//对公
    
    /**
     * 是否支持快捷方式
     */
    public static final String SUPPORT_EXPRESS_YES="01";//支持
    public static final String SUPPORT_EXPRESS_NO="02"; //不支持
    
    /**
     * 绑定类型
     */
    public static final String BIND_TYPE_KUAIJIE="01";
    public static final String BIND_TYPE_WANGGUAN="02";
    
    /**
     * 证件类型     
     */
    public static final String ID_TYPE_IDENTIFICATION="01";//身份证
    public static final String ID_TYPE_ORGANIZATION="02";//组织机构代码
    
    /**
     * 绑定状态
     */
    public static final String BIND_STATUS_UNCONFIRMED="01";//待确认
    public static final String BIND_STATUS_SUBMIT="02";		//已提交
    public static final String BIND_STATUS_CONFIRMED="03";  //已确认
    public static final String BIND_STATUS_FAIL="04";       //失败
    
    /**
     * 充值状态
     */
    public static final String CHARGE_STATUS_SUBMIT="01"; //已提交
    public static final String CHARGE_STATUS_SUCCESS="02";//充值成功
    public static final String CHARGE_STATUS_FAIL="03";   //充值失败
    
    /**
     * 
     * 提租赁金 100 ，1000 id
     */
    public static final Integer REASE_GOLD100 = -1;
    public static final Integer REASE_GOLD1000 = -2;
    
    /**
     * 锁定类型_非交易时间
     */
    public static final String LOCK_TYPE_NON_TRADE_TIME = "01";
    /**
     * 锁定类型_金价为0
     */
    public static final String LOCK_TYPE_ZERO_PRICE = "02";
    /**
     * 锁定类型_价格波动
     */
    public static final String LOCK_TYPE_PRICE_MARGIN_TO_LARGE = "03";
    
    /**
     * 充值渠道
     */
    public static final String CHARGE_CHANNEL_KUAIJIE="01";
    public static final String CHARGE_CHANNEL_WANGGUAN="02";
    
    /**
     * 充值类型_个人
     */
    public static final String PAY_TYPE_PERSONAL = "01";
    /**
     * 充值类型_公司
     */
    public static final String PAY_TYPE_COMPANY = "02";
    
    /**
     * 黄金头寸云商操作
     */
    public static final String GOLD_POSITION_SUBMIT="01";//录入
    public static final String GOLD_POSITION_PASS="02";  //审核通过
    public static final String GOLD_POSITION_REJECT="03";//审核拒绝
    public static final String GOLD_POSITION_DELETE="04";//删除中
    public static final String GOLD_POSITION_DELETE_SUCCESS="05";//删除成功
    public static final String GOLD_POSITION_DELETE_FAILURE="06";//删除失败

    /**
     * 存金状态
     */
    public static String STORE_GOLD_SUBMIT="01";//已录入
    public static String STORE_GOLD_PASS="02";//审核通过
    public static String STORE_GOLD_REJECT="03";//审核拒绝
    public static String STORE_GOLD_DELETE="04";//删除中
    public static String STORE_GOLD_DELETE_SUCCESS="05";//删除成功
    public static String STORE_GOLD_DELETE_FAILURE="06";//删除失败
    
    /**
     * 贷后管理状态
     */
    public static String AFTER_LOAN_INSERT="01";//录入状态
    public static String AFTER_LOAN_PASS="02";  //审核通过
    public static String AFTER_LOAN_REJECT="03";//审核拒绝
    
    /**
     * 贷后管理类型
     */
    public static String  AFTER_LOAN_TYPE_YYZZ="1";//营业执照
    public static String  AFTER_LOAN_TYPE_QYJYCS="2";//企业经营场所
    public static String  AFTER_LOAN_TYPE_WTHT="3";//委托合同
    public static String  AFTER_LOAN_TYPE_DBHT="4";//担保合同
    public static String  AFTER_LOAN_TYPE_DYHT="5";//抵押合同
    public static String  AFTER_LOAN_TYPE_JKHT="6";//借款合同

    
    
}
