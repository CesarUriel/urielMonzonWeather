package com.test.weather.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.weather.base.BaseTestClass;
import com.test.weather.builders.WeatherResponseBuilder;
import com.test.weather.exceptions.BusinessException;
import com.test.weather.responses.WeatherAppResponse;
import com.test.weather.responses.WeatherClientResponse;

@RunWith(SpringRunner.class)
public class WeatherResponseBuilderTest extends BaseTestClass{
	
	private WeatherClientResponse weatherApp = null;
	
	private WeatherAppResponse weatherAppResponse = null;
	
	@Mock
	private WeatherResponseBuilder weatherAppResponseMock;
	
	@Before
	public void setup() {
		weatherApp = createWeatherClientResponse();
		weatherAppResponse = createWeatherAppResponse();
	}
	
	@Test
	public void testSuccessBuilder() throws BusinessException {
		when(weatherAppResponseMock.buildWeatherResponse("", weatherApp)).thenReturn(weatherAppResponse);
		WeatherAppResponse responseBuilded = weatherAppResponseMock.buildWeatherResponse("", weatherApp);
		assertNotNull(responseBuilded);
		assertEquals(String.class, responseBuilded.getCityName().getClass());
		assertEquals(Double.valueOf("300.15"), responseBuilded.getTempF());
		assertEquals("10.42 PM", responseBuilded.getSunrise());
	}
	
	@Test(expected= BusinessException.class)
	public void testIllegalArgumentExceptionBuilder() throws BusinessException {
		weatherApp.setSys(null);
		when(weatherAppResponseMock.buildWeatherResponse("", weatherApp)).thenThrow(BusinessException.class);
		weatherAppResponseMock.buildWeatherResponse("", weatherApp);
	}

}
