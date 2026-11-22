package com.hackerrank.weather.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import com.hackerrank.weather.model.Weather;

public class WeathersServiceConfig{

	List<Weather> wds = new ArrayList<>(Arrays.asList(
			new Weather(1, new Date("2016-08-12"), 36.1189f,
					-86.6892f,"Nashville", "Tennessee",  Arrays.asList(16.1,21.6))));

	public List<Weather> getAllWDs() {
		return wds;
	}

	public Weather getWD(Integer id) throws NoSuchElementException {
		long count = wds.stream().filter(t -> t.getId().equals(id)).count();
		if (count < 1) {
			return null;
		}
		Weather wd = wds.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return wd;
	}

	public void addWD(Weather weatherData) {
		this.wds.add(weatherData);
	}

	public int deleteWD(Integer id) {
		if (getWD(id) == null) {
			return 404;
		} else {
			this.wds.removeIf(t -> t.getId().equals(id));
			return 200;
		}
	}

}
