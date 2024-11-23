package com.proyecto.airticket.repositories;


import com.proyecto.airticket.seat.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByFlightIdAndIsAvailableTrue(Integer flightId); // Obtener asientos disponibles por vuelo
}
