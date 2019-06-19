package com.test.weather.services;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.weather.builders.WeatherResponseBuilder;
import com.test.weather.client.WeatherClientImpl;
import com.test.weather.exceptions.BusinessException;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.responses.WeatherClientResponse;
import com.test.weather.services.WeatherService;

@Service
@PropertySource("classpath:messages.properties")
public class WeatherServiceImpl implements WeatherService{
	
	private Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Value("${weather.url.base}")
	private String baseUrl;
	
	@Value("${weather.url.appId}")
	private String appId;
	
	@Value("${error.service.conectService}")
	private String errorConnectionService;
	
	@Value("${track.service.endpoint}")
	private String clientEndpoint;
	
	@Autowired
	WeatherClientImpl weatherClient;
	
	@Autowired
	WeatherResponseBuilder weatherResponseBuilder;
	
	@Override
	public ResponseEntity<WeatherAppResponse> getWeatherByCityName(String city) {
		ResponseEntity<WeatherAppResponse>  response = null;
		try {
			String url = buildEndPoint(baseUrl, city, appId);
			log.info(clientEndpoint + url);
			WeatherClientResponse responseRestTemplate = weatherClient.callWeatherService(url);
			WeatherAppResponse weatherApp = weatherResponseBuilder.buildWeatherResponse(city, responseRestTemplate);
			response = new ResponseEntity<>(weatherApp, HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(errorConnectionService);
			response = new ResponseEntity<>(e.getHttpStatus());
		} 
		return response;
	}

	public String buildEndPoint(String baseUrl, String city, String appId) {
		return MessageFormat.format(baseUrl, city, appId);
	}
	

}
