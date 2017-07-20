package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	Location findById(long id);
	
	Location findByCityIgnoreCase(String cityName);
	
}
