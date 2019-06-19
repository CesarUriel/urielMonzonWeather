package com.test.weather.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherUtils.class);
	
	private static final String NULL_PARAMS = "Null params";
	
	public static Double fahrenheitToCelsius(Double tempF, String format) throws IllegalArgumentException{
		if (format != null && tempF != null) {
			DecimalFormat decimalFormat = new DecimalFormat(format);
			Double temp = ((tempF - 32) * 5) / 9;
			return Double.valueOf(decimalFormat.format(temp));
		} else {
			log.error(NULL_PARAMS);
			throw new IllegalArgumentException();
		}
	}

	public static String dateFormater(Long time, String format) throws IllegalArgumentException {
		if (format != null && time!= null ) {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(new Date(time));
		} else {
			log.error(NULL_PARAMS);
			throw new IllegalArgumentException();
		}

	}

}
