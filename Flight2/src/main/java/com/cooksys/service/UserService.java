package com.cooksys.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Itinerary;
import com.cooksys.entity.User;
import com.cooksys.pojo.Flight;
import com.cooksys.repository.FlightRepository;
import com.cooksys.repository.ItineraryRepository;
import com.cooksys.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	@Autowired
	ItineraryRepository itinRepo;
	@Autowired
	FlightRepository flightRepo;
	
	public User getId(long id){
		return repo.findById(id);
	}
	
	public User get(String username, String password){
		return repo.findByUsernameAndPassword(username, password);
	}
	
	public User post(String username, String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		repo.saveAndFlush(user);
		return user;
	}
	
	public void book(long user_id, ArrayList<Flight> itinerary) {
		Itinerary booked = new Itinerary();
		User user = repo.findById(user_id);
		booked.setFlights(itinerary);
		booked.setUser(user);
		itinRepo.saveAndFlush(booked);
		for(Flight f : itinerary){
			System.out.println(f.getOffset());
			Flight temp = f;
			System.out.println(booked.getId());
			temp.setItinerary(booked);
			flightRepo.saveAndFlush(temp);
		}
	}
	
	public ArrayList<Itinerary> allItinerary(long id){
		User user = repo.findById(id);
		return new ArrayList<Itinerary>(user.getItineraries());
	}

}
