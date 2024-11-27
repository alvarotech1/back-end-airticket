package com.proyecto.airticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.airticket.flight.Flight;
import com.proyecto.airticket.repositories.ReservationRepository;
import com.proyecto.airticket.reservation.Reservation;
import com.proyecto.airticket.seat.Seat;
import com.proyecto.airticket.user.Users;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final SeatService seatService;
    private final FlightService flightService;

   

 
    public Reservation createReservation(Users user, Integer flightId, Integer seatId) {

        Flight flight = flightService.getFlightById(flightId);
        
    
        Seat seat = seatService.reserveSeat(seatId);
        
        
        Reservation reservation = Reservation.builder()
                .user(user)                
                .flight(flight)               
                .seat(seat)                   
                .reservationTime(LocalDateTime.now()) 
                .status("CONFIRMED")        
                .build();

     
        return reservationRepository.save(reservation);
    }

   
    public List<Reservation> getUserReservations(Integer userId) {
        return reservationRepository.findByUserId(userId);
    }
}
