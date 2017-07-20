package com.cooksys.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.Dijkstra;
import com.cooksys.component.FlightGenerator;
import com.cooksys.entity.Itinerary;
import com.cooksys.entity.Location;
import com.cooksys.entity.User;
import com.cooksys.pojo.Flight;
import com.cooksys.repository.LocationRepository;
import com.cooksys.repository.UserRepository;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;
	Dijkstra dijkstra;
	@Autowired
	LocationRepository repo;

	private ArrayList<Flight> flightList = new ArrayList<>();
	
	public ArrayList<Flight> getDailyFlightList()
	{
		return flightList;
	}
	
	public ArrayList<Flight> getShortestPath(String origin, String destination){
		if(containsOrigin(flightList, origin) && containsDestination(flightList, destination)){
			ArrayList<String> cities = 
					new ArrayList<>(repo.findAll().stream().map(Location::getCity).collect(Collectors.toList()));
			dijkstra = new Dijkstra(cities, flightList);
			dijkstra.execute(origin);
			return new ArrayList<>(dijkstra.getPath(destination, flightList));
		
		}
		
		return null;
	}
	
	//The fixedDelay parameter determines how often a new day is generated as expressed in milliseconds
	@Scheduled(fixedDelay=50000)
	private void refreshFlights()
	{
		flightList = generator.generateNewFlightList();
	}
	
	public static boolean containsOrigin(ArrayList<Flight> check, String origin){
		for(Flight f : check){
			if(f != null && f.getOrigin().equals(origin))
				return true;
		}
		return false;
	}
	
	public static boolean containsDestination(ArrayList<Flight> check, String destination){
		for(Flight f : check){
			if(f != null && f.getDestination().equals(destination))
				return true;
		}
		return false;
	}

	
}
