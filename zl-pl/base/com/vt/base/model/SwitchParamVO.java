package com.vt.base.model;

import com.vt.base.StatefullRequest;
/**
 * 开关参数VO
 * @author chuck_li01
 * @date 2016/3/1
 */
public class SwitchParamVO extends StatefullRequest{
	private String buyInvestGoldSwitch;//购买投资金条
	private String takeOwnGoldSwitch;//提金-自有
	private String repurchaseGoldSwitch;//回购
	private String  depositGoldSwitch;//存金
	private String buyBidSwitch;//购买投资标的
	private String chargeSwitch;//充值
	private String loanSwitch;//满标放款
	private String repaymentSwitch;//借款人还款
	private String bidTransferSwitch;//标的转让费用
	private String advancedRepaySwitch;//提前还款
	private String withdrawSwitch;//提现
	private String takeRentGoldSwitch;//提金-租赁
	private String goldTradeSwitch;//购金回购开关 00 正常 01 未在交易时间 02 金价为0 03 金价波动较大
	
	public String getBuyInvestGoldSwitch() {
		return buyInvestGoldSwitch;
	}
	public void setBuyInvestGoldSwitch(String buyInvestGoldSwitch) {
		this.buyInvestGoldSwitch = buyInvestGoldSwitch;
	}
	public String getTakeOwnGoldSwitch() {
		return takeOwnGoldSwitch;
	}
	public void setTakeOwnGoldSwitch(String takeOwnGoldSwitch) {
		this.takeOwnGoldSwitch = takeOwnGoldSwitch;
	}
	public String getRepurchaseGoldSwitch() {
		return repurchaseGoldSwitch;
	}
	public void setRepurchaseGoldSwitch(String repurchaseGoldSwitch) {
		this.repurchaseGoldSwitch = repurchaseGoldSwitch;
	}
	public String getDepositGoldSwitch() {
		return depositGoldSwitch;
	}
	public void setDepositGoldSwitch(String depositGoldSwitch) {
		this.depositGoldSwitch = depositGoldSwitch;
	}
	public String getBuyBidSwitch() {
		return buyBidSwitch;
	}
	public void setBuyBidSwitch(String buyBidSwitch) {
		this.buyBidSwitch = buyBidSwitch;
	}
	public String getChargeSwitch() {
		return chargeSwitch;
	}
	public void setChargeSwitch(String chargeSwitch) {
		this.chargeSwitch = chargeSwitch;
	}
	public String getLoanSwitch() {
		return loanSwitch;
	}
	public void setLoanSwitch(String loanSwitch) {
		this.loanSwitch = loanSwitch;
	}
	public String getRepaymentSwitch() {
		return repaymentSwitch;
	}
	public void setRepaymentSwitch(String repaymentSwitch) {
		this.repaymentSwitch = repaymentSwitch;
	}
	public String getBidTransferSwitch() {
		return bidTransferSwitch;
	}
	public void setBidTransferSwitch(String bidTransferSwitch) {
		this.bidTransferSwitch = bidTransferSwitch;
	}
	public String getAdvancedRepaySwitch() {
		return advancedRepaySwitch;
	}
	public void setAdvancedRepaySwitch(String advancedRepaySwitch) {
		this.advancedRepaySwitch = advancedRepaySwitch;
	}
	public String getWithdrawSwitch() {
		return withdrawSwitch;
	}
	public void setWithdrawSwitch(String withdrawSwitch) {
		this.withdrawSwitch = withdrawSwitch;
	}
	public String getTakeRentGoldSwitch() {
		return takeRentGoldSwitch;
	}
	public void setTakeRentGoldSwitch(String takeRentGoldSwitch) {
		this.takeRentGoldSwitch = takeRentGoldSwitch;
	}
	public String getGoldTradeSwitch() {
		return goldTradeSwitch;
	}
	public void setGoldTradeSwitch(String goldTradeSwitch) {
		this.goldTradeSwitch = goldTradeSwitch;
	}

}
