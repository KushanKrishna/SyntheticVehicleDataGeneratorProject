package com.svdg.svdg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicleservicerecord")
public class VehicleServiceRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ServiceRecordKey;
    private String ServiceDescription;
    private Date ServiceDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleModel")
    private VehicleModel vehicleModel;

    public long getServiceRecordKey() {
        return ServiceRecordKey;
    }

    @JsonIgnore
    public void setServiceRecordKey(long serviceRecordKey) {
        ServiceRecordKey = serviceRecordKey;
    }


    public String getServiceDescription() {
        return ServiceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        ServiceDescription = serviceDescription;
    }

    public Date getServiceDate() {
        return ServiceDate;
    }

    public void setServiceDate(Date serviceDate) {
        ServiceDate = serviceDate;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    @JsonIgnore
    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Override
    public String toString() {
        return "VehicleServiceRecord{" +
                "ServiceRecordKey=" + ServiceRecordKey +
                ", ServiceDescription='" + ServiceDescription + '\'' +
                ", ServiceDate=" + ServiceDate +
                '}';
    }
}
