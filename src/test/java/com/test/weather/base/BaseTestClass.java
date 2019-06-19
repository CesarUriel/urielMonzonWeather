package com.test.weather.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.test.weather.model.Main;
import com.test.weather.model.Sys;
import com.test.weather.model.Weather;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.responses.WeatherClientResponse;

public class BaseTestClass {

	@Value("https://samples.openweathermap.org/data/2.5/weather?id=")
	protected String baseUrl;

	@Value("&appid=b1b15e88fa797225412429c1c50c122a1")
	protected String appId;

	@Value("hh.mm a")
	protected String format12h;

	@Value("###.##")
	protected String decimalFormat;

	protected WeatherClientResponse createWeatherClientResponse() {
		Main main = new Main(Double.valueOf("300.15"));
		Weather weather = new Weather("scattered clouds");
		List<Weather> listWeather = new ArrayList<>();
		listWeather.add(weather);
		Sys sys = new Sys(1485720272L, 1485766550L);

		return new WeatherClientResponse("2172797", listWeather, main, sys);
	}
	
	protected  WeatherAppResponse createWeatherAppResponse() {
		return new WeatherAppResponse(new Date(), "London", "scattered clouds", Double.valueOf("300.15"), Double.valueOf("148.97"), "10.42 PM", "10.42 PM");
	}

}
