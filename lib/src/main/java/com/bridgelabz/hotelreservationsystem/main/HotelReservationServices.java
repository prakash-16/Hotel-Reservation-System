package com.bridgelabz.hotelreservationsystem.main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HotelReservationServices {
	public static int i = 0;
	public static HashMap<String,Integer> bill = new HashMap<>();

	public static void cheapestHotel(ArrayList<HotelReservationSystem> hotelList, CustomerType customerType,
			String checkInDate, String checkOutDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate checkInDay = LocalDate.parse(checkInDate, format);
		LocalDate checkOutDay = LocalDate.parse(checkOutDate, format);
		int noOfDays = (checkOutDay.getDayOfMonth() - checkInDay.getDayOfMonth()) + 1;
		hotelList.stream().forEach(n -> {
			if(checkInDay.getDayOfWeek().equals(DayOfWeek.SATURDAY) && checkOutDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				if(customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), noOfDays*(n.getRate().get(CustomerType.REWARD).getWeekendRates()));
				}
				else {
					bill.put(n.getHotelName(), noOfDays*(n.getRate().get(CustomerType.REGULAR).getWeekendRates()));
				}
			}
			else if(checkInDay.getDayOfWeek().equals(DayOfWeek.SUNDAY) && checkOutDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				if(customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REWARD).getWeekdayRates()) + (n.getRate().get(CustomerType.REWARD).getWeekendRates())));
				}
				else {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REGULAR).getWeekdayRates()) + (n.getRate().get(CustomerType.REGULAR).getWeekendRates())));
				}
			}
			else if(checkInDay.getDayOfWeek().equals(DayOfWeek.FRIDAY) && checkOutDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				if(customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REWARD).getWeekdayRates()) + (n.getRate().get(CustomerType.REWARD).getWeekendRates())));
				}
				else {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REGULAR).getWeekdayRates()) + (n.getRate().get(CustomerType.REGULAR).getWeekendRates())));
				}
			}
			else {
				if(customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), noOfDays*(n.getRate().get(CustomerType.REWARD).getWeekdayRates()));
				}
				else {
					bill.put(n.getHotelName(), noOfDays*(n.getRate().get(CustomerType.REGULAR).getWeekdayRates()));
				}
			}
		});
		int minPrice = Collections.min(bill.values());
		for(Entry<String, Integer> mapElement : bill.entrySet()) {
			String key = (String)mapElement.getKey();
			int value = ((int)mapElement.getValue());
			if(value == minPrice) {
				System.out.println("The cheapest hotel is " + key + " and the price is " + minPrice + "$.");
			}
		}
  }
	
}
