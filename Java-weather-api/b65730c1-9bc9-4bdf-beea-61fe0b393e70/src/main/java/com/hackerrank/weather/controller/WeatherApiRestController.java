package com.hackerrank.weather.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hackerrank.weather.config.WeathersServiceConfig;
import com.hackerrank.weather.exceptions.WeatherDataNotFoundException;
import com.hackerrank.weather.model.Weather;




public class WeatherApiRestController {
	
	@Autowired
	private WeathersServiceConfig weatherService;
	
	@GetMapping("/select")
	@ResponseStatus( HttpStatus.OK )  //200
	public List<Weather> getAllWDs() {
		return  weatherService.getAllWDs();
	}
	
	@GetMapping("/select/{id}")
	@ResponseStatus( HttpStatus.OK )  //200
	public Weather getWD(@PathVariable Integer id) {
		Weather wd = weatherService.getWD(id);
		if(wd == null) {
			throw new WeatherDataNotFoundException(); //404
		}
		return wd;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/insert")
	//@ResponseStatus( HttpStatus.CREATED )  //200
	public void addWD(@RequestBody Weather weatherData, HttpServletResponse response) {
		weatherService.addWD(weatherData);
		response.setStatus(HttpServletResponse.SC_CREATED); //201
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
	@ResponseStatus( HttpStatus.OK )  //200
	public void deleteWD(@PathVariable Integer id) {
		int retcode = weatherService.deleteWD(id);
		if(retcode == 404) {
			throw new WeatherDataNotFoundException();
		}
	}
		
}

	

