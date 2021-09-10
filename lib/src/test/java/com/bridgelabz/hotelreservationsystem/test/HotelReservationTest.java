package com.bridgelabz.hotelreservationsystem.test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.main.*;


public class HotelReservationTest {
	public ArrayList<HotelReservationSystem> hotelList = new ArrayList<HotelReservationSystem>();
	public HashMap<CustomerType, Rate> customerRate = new HashMap();
	HotelReservationServices hotel = new HotelReservationServices();

	@Before
	public void addHotel() {
		
		customerRate = new HashMap();
		customerRate.put(CustomerType.REGULAR, new Rate(110, 90));
		customerRate.put(CustomerType.REWARD, new Rate(80, 80));
		HotelReservationSystem lakewood = new HotelReservationSystem("Lakewood", 3, customerRate);

		customerRate = new HashMap();
		customerRate.put(CustomerType.REGULAR, new Rate(160, 60));
		customerRate.put(CustomerType.REWARD, new Rate(110, 50));
		HotelReservationSystem bridgewood = new HotelReservationSystem("Bridgewood", 4, customerRate);

		customerRate = new HashMap();
		customerRate.put(CustomerType.REGULAR, new Rate(220, 160));
		customerRate.put(CustomerType.REWARD, new Rate(100, 40));
		HotelReservationSystem ridgewood = new HotelReservationSystem("Ridgewood", 5, customerRate);

		hotelList.add(lakewood);
		hotelList.add(bridgewood);
		hotelList.add(ridgewood);
	}
	@Test
	public void checkForCheapestHotel() {
		hotel.customerHotelRatesList(hotelList,CustomerType.REGULAR,"11Sep2021","12Sep2021");
		hotel.cheapestHotel(hotelList);
	}
	@Test
	public void checkForBestratedHotel() {
		hotel.customerHotelRatesList(hotelList,CustomerType.REGULAR,"11Sep2021","12Sep2021");
		hotel.bestRatedHotel(hotelList);
	}
	@Before
	public void checkLoyaltyProgramIsTakingInput() {
		hotel.loyaltyProgram(hotelList,"Lakewood",80,80);
		hotel.loyaltyProgram(hotelList,"Bridgewood",110,50);
		hotel.loyaltyProgram(hotelList,"Ridgewood",100,40);
	}
	
//	@Test
	public void checkCheapBestRatedHotel() throws FormatException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Date format (ddMMMyyyy)");
		System.out.println("Enter CheckIn date: ");
		String checkInDate = sc.nextLine();
		System.out.println("Enter CheckOut date: ");
		String checkOutDate = sc.nextLine();
		System.out.println("Enter type of customer: ");
		String customerType = sc.nextLine();
		customerType = customerType.toUpperCase();
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
			boolean checkInResult = hotel.regexValidation(checkInDate);
			boolean checkOutResult = hotel.regexValidation(checkOutDate);
			if((checkInResult == true) && (checkOutResult == true)) {
				format.parse(checkInDate);
				format.parse(checkOutDate);
				if(customerType.equals("REGULAR")) {
					HotelReservationServices hotel = new HotelReservationServices();
					hotel.customerHotelRatesList(hotelList,CustomerType.REGULAR,"11Sep2021","12Sep2021");
					hotel.cheapestHotel(hotelList);
				}
			}
		}catch(FormatException e) {
			throw new FormatException("Not a valid input");
		}
	}
	
}
