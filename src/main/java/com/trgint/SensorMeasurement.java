package com.trgint;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * @author Vasilis Kleanthous
 * This class implements the input of the REST endpoints and represents the data coming from sensors.
 *
 */
public class SensorMeasurement {

	@NotNull
	private Long sensorId;
	@Digits(integer=2, fraction=6)
	private Double latitude;
	@Digits(integer=2, fraction=6)
	private Double longitude;
	@Digits(integer=2, fraction=2)
	private Double humidity;
	@Digits(integer=2, fraction=1)
	private Double temperature;
	@Digits(integer=2, fraction=1)
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
