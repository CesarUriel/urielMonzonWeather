package com.test.weather.services;

import org.springframework.http.ResponseEntity;

import com.test.weather.responses.WeatherAppResponse;

public interface WeatherService {
	
	public ResponseEntity<WeatherAppResponse> getWeatherByCityName(String city);
	
}
