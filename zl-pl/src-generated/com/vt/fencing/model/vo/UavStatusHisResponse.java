package com.vt.fencing.model.vo;

public class UavStatusHisResponse {
	private Double uavGpsLat;
	private Double uavGpsLon;
	private Integer inNoflyingZone;
	private String address;
	public Double getUavGpsLat() {
		return uavGpsLat;
	}
	public void setUavGpsLat(Double uavGpsLat) {
		this.uavGpsLat = uavGpsLat;
	}
	public Double getUavGpsLon() {
		return uavGpsLon;
	}
	public void setUavGpsLon(Double uavGpsLon) {
		this.uavGpsLon = uavGpsLon;
	}
	public Integer getInNoflyingZone() {
		return inNoflyingZone;
	}
	public void setInNoflyingZone(Integer inNoflyingZone) {
		this.inNoflyingZone = inNoflyingZone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
