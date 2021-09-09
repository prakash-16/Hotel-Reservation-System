package com.bridgelabz.hotelreservationsystem.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.main.*;

public class HotelReservationTest {
	public ArrayList<HotelReservationSystem> hotelList = new ArrayList<HotelReservationSystem>();
	public HashMap<CustomerType, Rate> customerRate = new HashMap();
	

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
		HotelReservationServices.cheapestHotel(hotelList,CustomerType.REGULAR,"11Sep2021","12Sep2021");
	}
}
