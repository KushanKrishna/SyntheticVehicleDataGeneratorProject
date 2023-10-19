package com.svdg.svdg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVehicleDataResponseDto {

    String status;
    Integer statusCode;
    Timestamp timestamp;
    String message;


}
