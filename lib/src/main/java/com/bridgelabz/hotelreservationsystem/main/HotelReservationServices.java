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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotelReservationServices {
	public static HashMap<String, Integer> bill = new HashMap<>();
	
	public void customerHotelRatesList(ArrayList<HotelReservationSystem> hotelList, CustomerType customerType,
			String checkInDate, String checkOutDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
		LocalDate checkInDay = LocalDate.parse(checkInDate, format);
		LocalDate checkOutDay = LocalDate.parse(checkOutDate, format);
		int noOfDays = (checkOutDay.getDayOfMonth() - checkInDay.getDayOfMonth()) + 1;
		hotelList.stream().forEach(n -> {
			if (checkInDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)
					&& checkOutDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				if (customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), noOfDays * (n.getRate().get(CustomerType.REWARD).getWeekendRates()));
				} else {
					bill.put(n.getHotelName(), noOfDays * (n.getRate().get(CustomerType.REGULAR).getWeekendRates()));
				}
			} else if (checkInDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)
					&& checkOutDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
				if (customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REWARD).getWeekdayRates())
							+ (n.getRate().get(CustomerType.REWARD).getWeekendRates())));
				} else {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REGULAR).getWeekdayRates())
							+ (n.getRate().get(CustomerType.REGULAR).getWeekendRates())));
				}
			} else if (checkInDay.getDayOfWeek().equals(DayOfWeek.FRIDAY)
					&& checkOutDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				if (customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REWARD).getWeekdayRates())
							+ (n.getRate().get(CustomerType.REWARD).getWeekendRates())));
				} else {
					bill.put(n.getHotelName(), ((n.getRate().get(CustomerType.REGULAR).getWeekdayRates())
							+ (n.getRate().get(CustomerType.REGULAR).getWeekendRates())));
				}
			} else {
				if (customerType.equals(CustomerType.REWARD)) {
					bill.put(n.getHotelName(), noOfDays * (n.getRate().get(CustomerType.REWARD).getWeekdayRates()));
				} else {
					bill.put(n.getHotelName(), noOfDays * (n.getRate().get(CustomerType.REGULAR).getWeekdayRates()));
				}
			}
		});
	}
	public void cheapestHotel(ArrayList<HotelReservationSystem> hotelList) {
		int minPrice = Collections.min(bill.values());
		for (Entry<String, Integer> mapElement : bill.entrySet()) {
			String key = (String) mapElement.getKey();
			int value = ((int) mapElement.getValue());
			if (value == minPrice) {
				hotelList.stream().forEach(n -> {
					if (n.getHotelName().equals(key)) {
						System.out.println("The cheapest hotel is " + n.getHotelName() + " with " + n.getHotelRating()
								+ " star rating " + "and the price is " + minPrice + "$.");
					}
				});
			}
		}
	}
	
	public void bestRatedHotel(ArrayList<HotelReservationSystem> hotelList) {
		int maxPrice = Collections.max(bill.values());
		for (Entry<String, Integer> mapElement : bill.entrySet()) {
			String key = (String) mapElement.getKey();
			int value = ((int) mapElement.getValue());
			if (value == maxPrice) {
				hotelList.stream().forEach(n -> {
					if (n.getHotelName().equals(key)) {
						System.out.println("The best rated hotel is " + n.getHotelName() + " and the price is " + maxPrice + "$.");
					}
				});
			}
		}	
	}
	
	public void loyaltyProgram(ArrayList<HotelReservationSystem> hotelList, String hotelName, int weekdayRates, int weekendRates) {
		hotelList.stream().forEach(n -> {
			if(n.getHotelName().equals(hotelName)) {
				n.getRate().get(CustomerType.REWARD).setWeekdayRates(weekdayRates);
				n.getRate().get(CustomerType.REWARD).setWeekendRates(weekendRates);
			}
		});
	}
	
	public boolean regexValidation(String date) throws FormatException {
		String dateRegex = "^[0-9]{2}[A-Z]{1}[a-z]{2}[0-9]{4}$";
		Pattern p = Pattern.compile(dateRegex);
		Matcher m = p.matcher(date);
		boolean result = m.matches();
		if(result == true) {
			return true;
		}
		else {
			throw new FormatException("Not a valid input");
		}
	}
}
