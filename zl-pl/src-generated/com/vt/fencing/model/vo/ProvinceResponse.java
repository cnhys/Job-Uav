package com.vt.fencing.model.vo;

import java.util.List;

public class ProvinceResponse {
	private Integer proId;
	private String proName;
	private List<CityResponse> cities;
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public List<CityResponse> getCities() {
		return cities;
	}
	public void setCities(List<CityResponse> cities) {
		this.cities = cities;
	}
	
}
