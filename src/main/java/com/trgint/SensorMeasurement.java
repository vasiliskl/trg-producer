package com.trgint;

public class SensorMeasurement {

	private Long sensorId;
	private Double latitude;
	private Double longitude;
	private Double humidity;
	private Double temperature;
	private Double pressure;
	
	public Long getSensorId() {
		return sensorId;
	}
	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}
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
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
}
