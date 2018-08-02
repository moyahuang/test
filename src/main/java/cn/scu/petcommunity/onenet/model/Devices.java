package cn.scu.petcommunity.onenet.model;

public class Devices {

	private  String dev_id ;
	private  String datastream_lon;
	private  String datastream_lat;
	private  String datastream_hei;
	private  String datastreams;

	public Devices() {}
	public Devices(String dev_id) { this.dev_id = dev_id; }
	public String getDev_id() {
		return dev_id;
	}

	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
	}

	public String getDatastream_lon() {
		return datastream_lon;
	}

	public void setDatastream_lon(String datastream_lon) {
		this.datastream_lon = datastream_lon;
	}

	public String getDatastream_lat() {
		return datastream_lat;
	}

	public void setDatastream_lat(String datastream_lat) {
		this.datastream_lat = datastream_lat;
	}

	public String getDatastream_hei() {
		return datastream_hei;
	}

	public void setDatastream_hei(String datastream_hei) {
		this.datastream_hei = datastream_hei;
	}

	public String getDatastreams() {
		return datastreams;
	}

	public void setDatastreams(String datastreams) {
		this.datastreams = datastreams;
	}
}
