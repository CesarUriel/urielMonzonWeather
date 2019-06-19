package com.test.weather.builders;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.test.weather.exceptions.BusinessException;
import com.test.weather.model.Sys;
import com.test.weather.model.Weather;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.responses.WeatherClientResponse;
import com.test.weather.utils.WeatherUtils;

@Component
public class WeatherResponseBuilder {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherResponseBuilder.class);
	
	@Value("${weather.format.12h}")
	private String format12h;
	
	@Value("${weather.format.decimal}")
	private String decimalFormat;

	public WeatherAppResponse buildWeatherResponse(String city, WeatherClientResponse clientResponse) throws BusinessException {
		String sunrise = "";
		String sunset = "";
		Double tempC = null;
		
		Optional<Sys> sysOpt = Optional.ofNullable(clientResponse.getSys());
		if (sysOpt.isPresent()) {
			sunrise = WeatherUtils.dateFormater(clientResponse.getSys().getSunrise(), format12h);
			sunset = WeatherUtils.dateFormater(clientResponse.getSys().getSunset(), format12h);
			try {
				tempC = WeatherUtils.fahrenheitToCelsius(clientResponse.getMain().getTemp(), decimalFormat);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new WeatherAppResponse(new Date(), city, getWeatherDescription(clientResponse.getWeather()),
				clientResponse.getMain().getTemp(), tempC, sunrise, sunset);

	}
	
	private String getWeatherDescription(@Nullable List<Weather> listWeather) {
		return Optional.ofNullable(listWeather)
		.map(list -> list.get(0))
		.map(weather -> weather.getDescription())
		.orElse("");
	}

}
