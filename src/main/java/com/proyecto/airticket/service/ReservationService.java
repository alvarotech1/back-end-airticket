package com.proyecto.airticket.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.airticket.exceptions.ReservationNotFoundException;
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
    
    public Optional<Reservation> getReservationById(Integer reservationId) {
        return reservationRepository.findById(reservationId);
    }
    @Transactional
    public void cancelReservation(Integer reservationId) {
        // Encuentra la reserva
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        // Actualiza los asientos asociados con la reserva
        Seat seat = reservation.getSeat();
        seat.setAvailable(true);  // Marca el asiento como disponible
        seatService.saveSeat(seat);  // Guarda el estado actualizado del asiento

        // Actualiza la disponibilidad de los asientos en el vuelo
        Flight flight = reservation.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        flightService.saveFlight(flight);  // Guarda el vuelo con el nuevo número de asientos disponibles

        // Elimina la reserva
        reservationRepository.delete(reservation);
    }

    // Obtener el ID del vuelo asociado con una reserva
    public Integer getFlightIdByReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return reservation.getFlight().getId();
    }
}
