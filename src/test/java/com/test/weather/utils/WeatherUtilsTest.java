package com.test.weather.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.weather.base.BaseTestClass;

@RunWith(SpringRunner.class)
public class WeatherUtilsTest extends BaseTestClass{
	
	String tempF = "149.07";
	String tempC = "300.3333";
	Long time19hrs = 1557360000034L;
	String time7pm = "07.00 PM";
	
	@Test
	public void testFahrenheitToCelsiusOK() throws IllegalArgumentException {
		Double celsius = WeatherUtils.fahrenheitToCelsius(Double.valueOf(tempC), decimalFormat);
		assertNotNull(celsius);
		assertEquals(Double.class, celsius.getClass());
		assertEquals(Double.valueOf(tempF), celsius);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testFahrenheitToCelsiusFahrenheitParams() throws IllegalArgumentException{
		WeatherUtils.fahrenheitToCelsius(null, decimalFormat);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testFahrenheitToCelsiusFormatParams() throws IllegalArgumentException{
		WeatherUtils.fahrenheitToCelsius(Double.valueOf(tempC), null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testFahrenheitToCelsiusNullParams() throws IllegalArgumentException{
		WeatherUtils.fahrenheitToCelsius(null, null);
	}
	
	@Test
	public void testDateFormaterOK() throws IllegalArgumentException{
		String formatedDate = WeatherUtils.dateFormater(time19hrs, format12h);
		assertNotNull(formatedDate);
		assertEquals(String.class, formatedDate.getClass());
		assertEquals(time7pm, formatedDate);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testDateFormaterFormatNull() throws IllegalArgumentException{
		WeatherUtils.dateFormater(time19hrs, null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testDateFormaterTimeNull() throws IllegalArgumentException{
		WeatherUtils.dateFormater(null, format12h);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testDateFormaterNullParamas() throws IllegalArgumentException{
		WeatherUtils.dateFormater(null, null);
	}

}
