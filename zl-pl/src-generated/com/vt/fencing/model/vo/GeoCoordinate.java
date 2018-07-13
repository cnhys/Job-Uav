package com.vt.fencing.model.vo;

public class GeoCoordinate {
	private Double latitude;
	private Double longitude;
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public GeoCoordinate() {
		super();
	}
	public GeoCoordinate(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "GeoCoordinate [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	

}