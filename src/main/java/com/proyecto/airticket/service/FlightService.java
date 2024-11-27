package com.proyecto.airticket.service;

import com.proyecto.airticket.exceptions.FlightNotFoundException;
import com.proyecto.airticket.flight.Flight;
import com.proyecto.airticket.repositories.FlightRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

 
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found"));
    }
}
