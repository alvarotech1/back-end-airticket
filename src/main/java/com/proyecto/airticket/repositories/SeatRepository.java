package com.proyecto.airticket.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.airticket.seat.Seat;



public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByFlightIdAndIsAvailableTrue(Integer flightId);
}
