package com.test.weather.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.test.weather.controllers.WeatherController;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.services.WeatherServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest{

	@MockBean
	WeatherServiceImpl weatherServiceImpl;

	@Autowired
	MockMvc mockMvc;
	
	private final String CITY = "Hongkong";
	private final String PATH = "/weather/Hongkong";

	@Test
	public void testWeather200() throws Exception {
		WeatherAppResponse weatherApp = new WeatherAppResponse(new Date(969685200000L), CITY, "scattered clouds",
				Double.valueOf("300.15"), Double.valueOf("148.97"), "10.42 PM", "10.42 PM");
		when(weatherServiceImpl.getWeatherByCityName(CITY))
				.thenReturn(new ResponseEntity<WeatherAppResponse>(weatherApp, HttpStatus.OK));

		this.mockMvc.perform(get(PATH)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().string(containsString("Hongkong"))).andExpect(content().json(
						"{\"currentDate\":\"2000-09-23T05:00:00.000+0000\",\"cityName\":\"Hongkong\",\"description\":\"scattered clouds\",\"tempF\":300.15,\"tempC\":148.97,\"sunrise\":\"10.42 PM\",\"sunset\":\"10.42 PM\"}"));

	}

	@Test
	public void testWeather400() throws Exception {
		when(weatherServiceImpl.getWeatherByCityName(CITY))
				.thenReturn(new ResponseEntity<WeatherAppResponse>(HttpStatus.BAD_REQUEST));

		this.mockMvc.perform(get(PATH)).andDo(print()).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
	
	@Test
	public void testWeather404() throws Exception {
		when(weatherServiceImpl.getWeatherByCityName(CITY))
				.thenReturn(new ResponseEntity<WeatherAppResponse>(HttpStatus.NOT_FOUND));

		this.mockMvc.perform(get(PATH)).andDo(print()).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}


	@Test
	public void testWeather500() throws Exception {
		when(weatherServiceImpl.getWeatherByCityName(CITY))
				.thenReturn(new ResponseEntity<WeatherAppResponse>(HttpStatus.INTERNAL_SERVER_ERROR));

		this.mockMvc.perform(get(PATH)).andDo(print()).andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

}
