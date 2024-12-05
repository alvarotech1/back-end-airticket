package com.proyecto.airticket.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.airticket.flight.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
	@Query("SELECT DISTINCT f.origin.city FROM Flight f")
	List<String> findDistinctOrigins();

	@Query("SELECT DISTINCT f.destination.city FROM Flight f WHERE f.origin.city = :origin")
	List<String> findDestinationsByOrigin(@Param("origin") String origin);

	@Query("SELECT f FROM Flight f WHERE f.origin.city = :origin AND f.destination.city = :destination")
	List<Flight> findByOriginCityAndDestinationCity(@Param("origin") String origin, @Param("destination") String destination);

	@Query("SELECT f FROM Flight f WHERE f.origin.city = :origin AND f.destination.city = :destination AND f.departureTime = :departureDate")
	List<Flight> findByOriginCityAndDestinationCityAndDepartureDate(@Param("origin") String origin, @Param("destination") String destination, @Param("departureDate") LocalDateTime departureDate);

	@Query("SELECT f FROM Flight f WHERE f.origin.city = :origin AND f.destination.city = :destination AND f.departureTime BETWEEN :startOfDay AND :endOfDay")
	List<Flight> findFlightsByDateRange(@Param("origin") String origin, 
	                                    @Param("destination") String destination, 
	                                    @Param("startOfDay") LocalDateTime startOfDay, 
	                                    @Param("endOfDay") LocalDateTime endOfDay);

}
