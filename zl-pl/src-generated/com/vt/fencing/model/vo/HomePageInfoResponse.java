package com.vt.fencing.model.vo;

import java.util.List;

public class HomePageInfoResponse {
	//禁飞区域
	private Integer zoneId;
	private Integer areaType;
	private Double longitude;
	private Double latitude;
	private Double radius;
	
	private List<GeoCoordinate> GeoCoordinates;
	
	
	public Double getRadius() {
		return radius;
	}
	public void setRadius(Double radius) {
		this.radius = radius;
	}
	public List<GeoCoordinate> getGeoCoordinates() {
		return GeoCoordinates;
	}
	public void setGeoCoordinates(List<GeoCoordinate> geoCoordinates) {
		GeoCoordinates = geoCoordinates;
	}
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	
}
