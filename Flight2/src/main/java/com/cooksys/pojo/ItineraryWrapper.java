package com.cooksys.pojo;

import java.util.ArrayList;

public class ItineraryWrapper {
	
	private long user_id;
	
	private ArrayList<Flight> itinerary;

	public ArrayList<Flight> getItinerary() {
		return itinerary;
	}

	public void setItinerary(ArrayList<Flight> itinerary) {
		this.itinerary = itinerary;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	

}
