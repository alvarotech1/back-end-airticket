package com.proyecto.airticket.repositories;

import com.proyecto.airticket.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(Integer userId); // Obtener reservas por usuario
}
