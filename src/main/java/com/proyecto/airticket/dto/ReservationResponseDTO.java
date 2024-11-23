package com.proyecto.airticket.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.airticket.reservation.Reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
	    private Integer id;
	    private String seatNumber;
	    private String seatClass;
	    private String flightOrigin;
	    private String flightDestination;
	    private String status;
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime reservationTime;
	    
	    public ReservationResponseDTO(Reservation reservation) {
	        this.id = reservation.getId();
	        this.seatNumber = reservation.getSeat().getSeatNumber();
	        this.seatClass = reservation.getSeat().getSeatClass();
	        this.flightOrigin = reservation.getFlight().getOrigin().getName();
	        this.flightDestination = reservation.getFlight().getDestination().getName();
	        this.status = reservation.getStatus();
	        this.reservationTime = reservation.getReservationTime();
	    }
}
