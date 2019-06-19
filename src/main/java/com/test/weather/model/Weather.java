package com.test.weather.model;

public class Weather {
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Weather(String description) {
		this.description = description;
	}

	public Weather() {
	}
}
