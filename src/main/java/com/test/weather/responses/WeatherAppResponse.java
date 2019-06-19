package com.test.weather.responses;

import java.io.Serializable;
import java.util.Date;

public class WeatherAppResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9083525042287419021L;
	
	private Date currentDate;
	private String cityName;
	private String description;
	private Double tempF;
	private Double tempC;
	private String sunrise;
	private String sunset;
	
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getTempF() {
		return tempF;
	}
	public void setTempF(Double tempF) {
		this.tempF = tempF;
	}
	public Double getTempC() {
		return tempC;
	}
	public void setTempC(Double tempC) {
		this.tempC = tempC;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	
	//Constructors
	public WeatherAppResponse(Date currentDate, String cityName, String description, Double tempF, Double tempC,
			String sunrise, String sunset) {
		super();
		this.currentDate = currentDate;
		this.cityName = cityName;
		this.description = description;
		this.tempF = tempF;
		this.tempC = tempC;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}

}
