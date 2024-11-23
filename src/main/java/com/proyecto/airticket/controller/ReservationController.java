package com.proyecto.airticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.airticket.dto.ReservationRequest;
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
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {
       
        Users user = userRepository.findById(request.getUserId())
                                   .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

   
        Reservation reservation = reservationService.createReservation(user, request.getFlightId(), request.getSeatId());

        
        return ResponseEntity.status(201).body(reservation);
    }

    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getUserReservations(@PathVariable Integer userId) {
        // Obtener las reservas del usuario
        List<Reservation> reservations = reservationService.getUserReservations(userId);

        // Devolver la lista de reservas del usuario
        return ResponseEntity.ok(reservations);
    }
}
