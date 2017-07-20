package com.cooksys.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooksys.pojo.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="itineraries")
public class Itinerary {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="itinerary")
	private List<Flight> flights;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
}
