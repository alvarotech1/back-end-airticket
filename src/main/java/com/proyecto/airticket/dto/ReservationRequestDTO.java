package com.proyecto.airticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {
    private Integer flightId;
    private Integer seatId;
    private Integer userId;
}
