package com.svdg.svdg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "referencedata")
public class ReferenceData {
    @Id
    long id;
    String type;
    String typeCode;
    String value;
}
