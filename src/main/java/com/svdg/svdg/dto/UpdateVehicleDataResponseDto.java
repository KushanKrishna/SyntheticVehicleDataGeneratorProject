package com.svdg.svdg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleDataResponseDto {
    String status;
    Integer statusCode;
    Timestamp timestamp;
    String message;
}
