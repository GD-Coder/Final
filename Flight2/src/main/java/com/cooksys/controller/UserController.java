package com.cooksys.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Itinerary;
import com.cooksys.entity.User;
import com.cooksys.pojo.ItineraryWrapper;
import com.cooksys.pojo.RequestWrapper;
import com.cooksys.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping("/{id}")
	public User getId(@PathVariable long id){
		return userService.getId(id);
	}
	
	@CrossOrigin
	@RequestMapping("/username/{user}")
	public User get(@PathVariable String user, @RequestBody String password){
		return userService.get(user, password);
	}
	
	@CrossOrigin
	@RequestMapping
	public User post(@RequestBody RequestWrapper wrapper){
		return userService.post(wrapper.getUsername(), wrapper.getPassword());
	}
	
	@CrossOrigin
	@RequestMapping("/itinerary/book")
	public void postItinerary(@RequestBody ItineraryWrapper wrapper){
		userService.book(wrapper.getUser_id(), wrapper.getItinerary());
	}
	
	@CrossOrigin
	@RequestMapping("/itinerary/all/{id}")
	public ArrayList<Itinerary> getItineraries(@PathVariable long id){
		return userService.allItinerary(id);
	}
	

}
