package com.test.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.test.weather.base.BaseTestClass;
import com.test.weather.builders.WeatherResponseBuilder;
import com.test.weather.client.WeatherClientImpl;
import com.test.weather.exceptions.BusinessException;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.responses.WeatherClientResponse;
import com.test.weather.services.WeatherServiceImpl;

@RunWith(SpringRunner.class)
public class WeatherServiceTest extends BaseTestClass{
	
	
	@InjectMocks
	WeatherServiceImpl weatherServiceImpl;
	
	@Mock
	WeatherClientImpl weatherClientMock;
	
	@Mock
	WeatherResponseBuilder weatherResponseBuilder;
	
	private final String CITY = "London";
	
	
	private WeatherClientResponse weatherApp;
	
	@Before
	public void setup() {
		weatherApp = createWeatherClientResponse();
		ReflectionTestUtils.setField(weatherServiceImpl, "baseUrl", baseUrl);
		ReflectionTestUtils.setField(weatherServiceImpl, "appId", appId);
		
	}

	@Test
	public void testWeather200() throws BusinessException {
		
		when(weatherClientMock.callWeatherService(anyString())).thenReturn(weatherApp);
		when(weatherResponseBuilder.buildWeatherResponse(anyString(), eq(weatherApp))).thenReturn(createWeatherAppResponse());
		
		ResponseEntity<WeatherAppResponse> weatherResponse = weatherServiceImpl.getWeatherByCityName(CITY);
		assertNotNull(weatherResponse);
		assertEquals(ResponseEntity.class, weatherResponse.getClass());
		assertNotNull(weatherResponse.getBody().getDescription());
		assertEquals(CITY, weatherResponse.getBody().getCityName());
		assertEquals(Double.class, weatherResponse.getBody().getTempC().getClass());
	}

	@Test
	public void testWeather400() throws BusinessException {

		when(weatherClientMock.callWeatherService(anyString())).thenThrow(new BusinessException(HttpStatus.BAD_REQUEST));

		ResponseEntity<WeatherAppResponse> weatherResponse = weatherServiceImpl.getWeatherByCityName(CITY);
		assertNotNull(weatherResponse);
		assertEquals(ResponseEntity.class, weatherResponse.getClass());
		assertNull(weatherResponse.getBody());
		assertEquals(weatherResponse.getStatusCodeValue(), HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void testWeather404() throws BusinessException {

		when(weatherClientMock.callWeatherService(anyString())).thenThrow(new BusinessException(HttpStatus.NOT_FOUND));
		
		ResponseEntity<WeatherAppResponse> weatherResponse = weatherServiceImpl.getWeatherByCityName(CITY);
		assertNotNull(weatherResponse);
		assertEquals(ResponseEntity.class, weatherResponse.getClass());
		assertNull(weatherResponse.getBody());
		assertEquals(weatherResponse.getStatusCodeValue(), HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void testWeather500() throws BusinessException {

		weatherApp.setSys(null);
		when(weatherClientMock.callWeatherService(anyString())).thenThrow(new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR));
		
		ResponseEntity<WeatherAppResponse> weatherResponse = weatherServiceImpl.getWeatherByCityName(CITY);
		assertNotNull(weatherResponse);
		assertEquals(ResponseEntity.class, weatherResponse.getClass());
		assertNull(weatherResponse.getBody());
		assertEquals(weatherResponse.getStatusCodeValue(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
}
