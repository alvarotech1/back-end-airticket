package com.proyecto.airticket.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.airticket.exceptions.FlightNotFoundException;
import com.proyecto.airticket.flight.Flight;
import com.proyecto.airticket.repositories.FlightRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

 
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    public List<String> getAvailableOrigins() {
        return flightRepository.findDistinctOrigins();
    }
    
    public List<Flight> searchFlightsByDate(String origin, String destination, LocalDate departureDate) {
        LocalDateTime startOfDay = departureDate.atStartOfDay();
        LocalDateTime endOfDay = departureDate.atTime(23, 59, 59);
        return flightRepository.findFlightsByDateRange(origin, destination, startOfDay, endOfDay);
    }


    
    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
    }

	public List<String> getAvailableDestinations(String origin) {
		 return flightRepository.findDestinationsByOrigin(origin);
	}

	 public Flight saveFlight(Flight flight) {
	        return flightRepository.save(flight);
	    }
	
}
