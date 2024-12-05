package com.proyecto.airticket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.airticket.flight.Flight;
import com.proyecto.airticket.service.FlightService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flights")
@CrossOrigin
public class FlightController {

    private final FlightService flightService;

   
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/origins")
    public ResponseEntity<List<String>> getAvailableOrigins() {
        List<String> origins = flightService.getAvailableOrigins();
        return ResponseEntity.ok(origins);
    }
    
    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getAvailableDestinations(@RequestParam String origin) {
        List<String> destinations = flightService.getAvailableDestinations(origin);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate departureDate) {
        
        List<Flight> flights = flightService.searchFlightsByDate(origin, destination, departureDate);
        return ResponseEntity.ok(flights);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }
}
