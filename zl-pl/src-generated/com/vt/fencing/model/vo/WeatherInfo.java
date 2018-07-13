package com.vt.fencing.model.vo;

public class WeatherInfo {
	private String liveTemperature;
	private String weatherCondition;
	private String temperature;
	private String city;
	private String wind;
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
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	@Override
	public String toString() {
		return "WeatherInfo [liveTemperature=" + liveTemperature + ", weatherCondition=" + weatherCondition
				+ ", temperature=" + temperature + ", city=" + city + ", wind=" + wind + "]";
	}
	
}
