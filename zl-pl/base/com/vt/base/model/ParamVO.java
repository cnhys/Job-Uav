package com.vt.base.model;

import com.vt.base.StatefullRequest;

/**
 * 参数实体类
 * @author chuck_li01
 * @date 2016/2/2
 */
public class ParamVO extends StatefullRequest{
	
	private String paramName;             //参数名称
	private String paramValue;            //参数值
	private String floatUpAmt;            //  购金金价上浮值	floatUpAmt
	private String takeGoldProcFeeRate;   //  手续费率	takeGoldProcFeeRate	
	private String depositGoldProcFeeRate;//  手续费率	depositGoldProcFeeRate
	private String floatDownAmt;          //  购金金价下调值	floatDownAmt
	private String loanBidFeeRate;        //  手续费	loanBidFeeRate
	private String transferBidFeeRate;    //  手续费	transferBidFeeRate
	private String interestMgrFeeRate;    //  管理费率	interestMgrFeeRate
	private String compensationDays;      //  提前还款补偿利息天数
	private String goldLeaseFeeRate;      //租赁金手续费率
	private String goldPriceThreshold;    //浮动金价阈值
	
	public String getGoldLeaseFeeRate() {
		return goldLeaseFeeRate;
	}
	public void setGoldLeaseFeeRate(String goldLeaseFeeRate) {
		this.goldLeaseFeeRate = goldLeaseFeeRate;
	}
	public String getFloatUpAmt() {
		return floatUpAmt;
	}
	public void setFloatUpAmt(String floatUpAmt) {
		this.floatUpAmt = floatUpAmt;
	}
	public String getTakeGoldProcFeeRate() {
		return takeGoldProcFeeRate;
	}
	public void setTakeGoldProcFeeRate(String takeGoldProcFeeRate) {
		this.takeGoldProcFeeRate = takeGoldProcFeeRate;
	}
	public String getDepositGoldProcFeeRate() {
		return depositGoldProcFeeRate;
	}
	public void setDepositGoldProcFeeRate(String depositGoldProcFeeRate) {
		this.depositGoldProcFeeRate = depositGoldProcFeeRate;
	}
	public String getFloatDownAmt() {
		return floatDownAmt;
	}
	public void setFloatDownAmt(String floatDownAmt) {
		this.floatDownAmt = floatDownAmt;
	}
	public String getLoanBidFeeRate() {
		return loanBidFeeRate;
	}
	public void setLoanBidFeeRate(String loanBidFeeRate) {
		this.loanBidFeeRate = loanBidFeeRate;
	}
	public String getTransferBidFeeRate() {
		return transferBidFeeRate;
	}
	public void setTransferBidFeeRate(String transferBidFeeRate) {
		this.transferBidFeeRate = transferBidFeeRate;
	}
	public String getInterestMgrFeeRate() {
		return interestMgrFeeRate;
	}
	public void setInterestMgrFeeRate(String interestMgrFeeRate) {
		this.interestMgrFeeRate = interestMgrFeeRate;
	}
	public String getCompensationDays() {
		return compensationDays;
	}
	public void setCompensationDays(String compensationDays) {
		this.compensationDays = compensationDays;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getGoldPriceThreshold() {
		return goldPriceThreshold;
	}
	public void setGoldPriceThreshold(String goldPriceThreshold) {
		this.goldPriceThreshold = goldPriceThreshold;
	}
}
