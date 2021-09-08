package com.bridgelabz.hotelreservationsystem.main;

import java.util.HashMap;

public class HotelReservationSystem {

	public String hotelName;
	public int hotelRating;
	public HashMap<CustomerType, Rate> rate;

	public HotelReservationSystem(String hotelName, int hotelRating, HashMap<CustomerType, Rate> rate) {
		this.hotelName = hotelName;
		this.hotelRating = hotelRating;
		this.rate = rate;
	}

}
