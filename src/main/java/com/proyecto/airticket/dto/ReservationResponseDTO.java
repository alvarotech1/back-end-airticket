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
    private String firstname;
    private String lastname;
    private String email;
    private String originCity;
    private String originCountry;
    private String flightOrigin;
    private String flightDestination;
    private String destinationCity;
    private String destinationCountry;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    private String seatNumber;
    private String seatClass;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationTime;
    private String status;

    // Constructor para la creación de reservas
    public ReservationResponseDTO(Reservation reservation) {
        this.firstname = reservation.getUser().getFirstname();
        this.lastname = reservation.getUser().getLastname();
        this.email = reservation.getUser().getEmail();
        this.id = reservation.getId();
        this.originCity = reservation.getFlight().getOrigin().getCity();
        this.originCountry = reservation.getFlight().getOrigin().getCountry();
        this.flightOrigin = reservation.getFlight().getOrigin().getName();
        this.flightDestination = reservation.getFlight().getDestination().getName();
        this.destinationCity = reservation.getFlight().getDestination().getCity();
        this.destinationCountry = reservation.getFlight().getDestination().getCountry();
        this.departureTime = reservation.getFlight().getDepartureTime();
        this.arrivalTime = reservation.getFlight().getArrivalTime();
        this.seatNumber = reservation.getSeat().getSeatNumber();
        this.seatClass = reservation.getSeat().getSeatClass();
        this.reservationTime = reservation.getReservationTime();
        this.status = reservation.getStatus();
    }

    // Constructor para las Get de las reservas
    public ReservationResponseDTO(Reservation reservation, boolean isResponse) {
        if (isResponse) {
        	  this.id = reservation.getId();
        	  this.originCity = reservation.getFlight().getOrigin().getCity();
              this.originCountry = reservation.getFlight().getOrigin().getCountry();
              this.flightOrigin = reservation.getFlight().getOrigin().getName();
              this.flightDestination = reservation.getFlight().getDestination().getName();
              this.destinationCity = reservation.getFlight().getDestination().getCity();
              this.destinationCountry = reservation.getFlight().getDestination().getCountry();
              this.departureTime = reservation.getFlight().getDepartureTime();
              this.arrivalTime = reservation.getFlight().getArrivalTime();
              this.seatNumber = reservation.getSeat().getSeatNumber();
              this.seatClass = reservation.getSeat().getSeatClass();
              this.reservationTime = reservation.getReservationTime();
              this.status = reservation.getStatus();
        }
    }
}
