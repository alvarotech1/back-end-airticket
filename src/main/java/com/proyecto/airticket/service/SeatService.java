package com.proyecto.airticket.service;

import com.proyecto.airticket.exceptions.SeatNotAvailableException;
import com.proyecto.airticket.exceptions.SeatNotFoundException;
import com.proyecto.airticket.repositories.SeatRepository;
import com.proyecto.airticket.seat.Seat;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

  
    public List<Seat> getAvailableSeats(Integer flightId) {
        return seatRepository.findByFlightIdAndIsAvailableTrue(flightId);
    }

    public Seat reserveSeat(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new SeatNotFoundException("Seat not found"));
        if (!seat.isAvailable()) {
            throw new SeatNotAvailableException("The seat " + seat.getSeatNumber() + " is already reserved.");
        }
        seat.setAvailable(false);
        return seatRepository.save(seat);
    }
    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }
}
