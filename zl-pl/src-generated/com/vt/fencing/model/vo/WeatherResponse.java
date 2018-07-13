package com.vt.fencing.model.vo;

public class WeatherResponse {
	private String liveTemperature;
	private String weatherCondition;
	
	
	public String getLiveTemperature() {
		return liveTemperature;
	}
	public void setLiveTemperature(String liveTemperature) {
		this.liveTemperature = liveTemperature;
	}
	public String getWeatherCondition() {
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}
	
}
