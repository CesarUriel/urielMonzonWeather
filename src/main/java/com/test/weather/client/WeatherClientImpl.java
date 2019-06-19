package com.test.weather.client;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.test.weather.exceptions.BusinessException;
import com.test.weather.responses.WeatherClientResponse;

@Service
public class WeatherClientImpl implements WeatherClient{
	
	private Logger log = LoggerFactory.getLogger(WeatherClientImpl.class);
	
	@Override
	public WeatherClientResponse callWeatherService(String url) throws BusinessException{
		try {
			return Optional.ofNullable(new RestTemplate().getForObject(url, WeatherClientResponse.class))
					.orElseThrow(() -> new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR));
		} catch (IllegalArgumentException | RestClientException re) {
			log.error("Error to connect to Weather Services", re);
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
