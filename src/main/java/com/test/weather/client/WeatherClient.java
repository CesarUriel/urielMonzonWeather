package com.test.weather.client;

import com.test.weather.exceptions.BusinessException;
import com.test.weather.responses.WeatherClientResponse;

public interface WeatherClient {
	
	public WeatherClientResponse callWeatherService(String url) throws BusinessException;

}
