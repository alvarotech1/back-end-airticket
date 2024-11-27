package com.proyecto.airticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.airticket.exceptions.SeatNotFoundException;
import com.proyecto.airticket.seat.Seat;
import com.proyecto.airticket.service.SeatService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/seats")
@AllArgsConstructor
public class SeatController {

	private final SeatService seatService;

	@GetMapping("/flight/{flightId}")
	public ResponseEntity<List<Seat>> getSeatsByFlight(@PathVariable Integer flightId) {
		List<Seat> seats = seatService.getAvailableSeats(flightId);
		   if (seats.isEmpty()) {
	            throw new SeatNotFoundException("No seats found for flight with ID " + flightId);
	        }
		return ResponseEntity.ok(seats);
	}

}
