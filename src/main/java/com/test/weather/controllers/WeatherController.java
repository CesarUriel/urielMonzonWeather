package com.test.weather.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.services.WeatherServiceImpl;

@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET})
@RequestMapping(value="/weather")
public class WeatherController {
	
	@Autowired
	WeatherServiceImpl weatherServiceImpl;

	@GetMapping(path="/{city}")
	@ResponseBody
	public ResponseEntity<WeatherAppResponse> getWeatherByCity(@PathVariable(value="city") String city) {
		return weatherServiceImpl.getWeatherByCityName(city);
	}
}
