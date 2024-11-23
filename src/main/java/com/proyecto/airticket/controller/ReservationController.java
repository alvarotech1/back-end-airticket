package com.proyecto.airticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.airticket.dto.ReservationRequestDTO;
import com.proyecto.airticket.dto.ReservationResponseDTO;
import com.proyecto.airticket.exceptions.UserNotFoundException;
import com.proyecto.airticket.repositories.UserRepository;
import com.proyecto.airticket.reservation.Reservation;
import com.proyecto.airticket.service.ReservationService;
import com.proyecto.airticket.user.Users;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserRepository userRepository;

    
    @PostMapping("/create")
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO request) {
       
        Users user = userRepository.findById(request.getUserId())
                                   .orElseThrow(() -> new UserNotFoundException("User not found"));

   
        Reservation reservation = reservationService.createReservation(user, request.getFlightId(), request.getSeatId());
        
        ReservationResponseDTO response = new ReservationResponseDTO(reservation);
        
        return ResponseEntity.status(201).body(response);
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getUserReservations(@PathVariable Integer userId) {
        List<Reservation> reservations = reservationService.getUserReservations(userId);

        // convierte las reservas a DTO
        List<ReservationResponseDTO> response = reservations.stream()
                .map(reservation -> new ReservationResponseDTO(reservation, true))
                .toList();

        return ResponseEntity.ok(response);
    }
}
