package com.example.java_ai_function_callback;

public class WeatherResponse {

	private final double temp;

	private final double feels_like;

	private final double temp_min;

	private final double temp_max;

	private final int pressure;

	private final int humidity;

	private final SpringAiJavaFunctionCallbackApplication.Unit unit;

	public WeatherResponse(double temp, double feels_like, double temp_min,
			double temp_max, int pressure, int humidity, SpringAiJavaFunctionCallbackApplication.Unit unit) {
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
		this.humidity = humidity;
		this.unit = unit;
	}

	public double getTemp() {
		return temp;
	}

	public double getFeels_like() {
		return feels_like;
	}

	public double getTemp_min() {
		return temp_min;
	}

	public double getTemp_max() {
		return temp_max;
	}

	public int getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public SpringAiJavaFunctionCallbackApplication.Unit getUnit() {
		return unit;
	}

}
