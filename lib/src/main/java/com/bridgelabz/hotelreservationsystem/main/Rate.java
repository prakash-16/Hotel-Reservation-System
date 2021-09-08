package com.bridgelabz.hotelreservationsystem.main;

public class Rate {

	private int weekendRates;
	private int weekdayRates;
	
	public Rate(int weekdayRates, int weekendRates) {
		this.weekdayRates = weekdayRates;
		this.weekendRates = weekendRates;
	}

	public int getWeekdayRates() {
		return weekdayRates;
	}

	public void setWeekdayRates(int weekdayRates) {
		this.weekdayRates = weekdayRates;
	}

	public int getWeekendRates() {
		return weekendRates;
	}

	public void setWeekendRates(int weekendRates) {
		this.weekendRates = weekendRates;
	}

}
