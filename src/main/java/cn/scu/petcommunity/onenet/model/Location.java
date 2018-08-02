package cn.scu.petcommunity.onenet.model;

import java.util.HashMap;
import java.util.Map;

public class Location {
	private  double ele;
	private  double lat;//纬度
	private  double lon;//经度
	private String date;//日期
	private  boolean eleSet;

	public Location() {}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public Location(double ele, double lat, double lon, boolean eleSet) {
		this.ele = ele;
		this.lat = lat;
		this.lon = lon;
		this.eleSet = eleSet;
	}

	public Map<String,Double> toMap() {
		Map<String,Double> result = new HashMap<String,Double>();
		if (eleSet) {
			result.put("ele", ele);
		}
		result.put("lon", lon);
		result.put("lat", lat);
		result.put("ele", ele);
		return result;
	}

	public Location(double lon,double lat, double ele) {
		this.ele = ele;
		this.lat = lat;
		this.lon = lon;
	}
}
