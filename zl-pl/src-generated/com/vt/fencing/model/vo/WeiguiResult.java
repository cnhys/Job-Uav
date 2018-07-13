package com.vt.fencing.model.vo;

public class WeiguiResult {
	private String ruleTitle;
	private String ruleDate;
	private String ruleContent;

	

	public WeiguiResult() {
		super();
	}
	public WeiguiResult(String ruleTitle, String ruleDate, String ruleContent) {
		super();
		this.ruleTitle = ruleTitle;
		this.ruleDate = ruleDate;
		this.ruleContent = ruleContent;
	}
	
	
	public String getRuleDate() {
		return ruleDate;
	}
	public void setRuleDate(String ruleDate) {
		this.ruleDate = ruleDate;
	}
	public String getRuleTitle() {
		return ruleTitle;
	}
	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}
	public String getRuleContent() {
		return ruleContent;
	}
	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}

}
