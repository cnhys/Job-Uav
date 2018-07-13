package com.vt.fencing.model;

import java.util.Date;

public class AaIrregularities {
	private Integer alertLevel;
	private String ruleTitle;
	private Date ruleDate;
	private String ruleContent;
	
	public AaIrregularities() {
		super();
	}

	public AaIrregularities(Integer alertLevel, String ruleTitle, Date ruleDate, String ruleContent) {
		super();
		this.alertLevel = alertLevel;
		this.ruleTitle = ruleTitle;
		this.ruleDate = ruleDate;
		this.ruleContent = ruleContent;
	}

	public Integer getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(Integer alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getRuleTitle() {
		return ruleTitle;
	}

	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}

	public Date getRuleDate() {
		return ruleDate;
	}

	public void setRuleDate(Date ruleDate) {
		this.ruleDate = ruleDate;
	}

	public String getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(String ruleContent) {
		this.ruleContent = ruleContent;
	}
	
	
}
