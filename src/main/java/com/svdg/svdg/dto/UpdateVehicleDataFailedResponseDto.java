package com.svdg.svdg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleDataFailedResponseDto {

    Set<String> message;
    Timestamp timestamp;
    HttpStatus status;
    int statusCode;
}
