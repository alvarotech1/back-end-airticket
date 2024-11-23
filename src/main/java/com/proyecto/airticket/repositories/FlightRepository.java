package com.proyecto.airticket.repositories;

import com.proyecto.airticket.flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
