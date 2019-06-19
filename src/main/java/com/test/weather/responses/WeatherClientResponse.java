package com.test.weather.responses;

import java.util.List;

import com.test.weather.model.Main;
import com.test.weather.model.Sys;
import com.test.weather.model.Weather;

public class WeatherClientResponse{
	
	private String id;
	private List<Weather> weather;
	private Main main;
	private Sys sys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	
	public WeatherClientResponse() {
	}
	
	public WeatherClientResponse(String id, List<Weather> weather, Main main, Sys sys) {
		this.id = id;
		this.weather = weather;
		this.main = main;
		this.sys = sys;
	}

}
