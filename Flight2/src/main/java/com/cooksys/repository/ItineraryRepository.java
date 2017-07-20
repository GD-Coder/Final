package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.entity.Itinerary;

@Repository
public interface ItineraryRepository  extends JpaRepository<Itinerary, Long> {

}
