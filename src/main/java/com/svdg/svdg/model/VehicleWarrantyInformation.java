package com.svdg.svdg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehiclewarrantyinformation")

public class VehicleWarrantyInformation {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicleWarrantyPk;
    private String Coverage;
    private Date StartDate;
    private Date EndDate;
    private String WarrantyStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleModel")
    private VehicleModel vehicleModel;

    public long getVehicleWarrantyPk() {
        return vehicleWarrantyPk;
    }

    @JsonIgnore
    public void setVehicleWarrantyPk(long vehicleWarrantyPk) {
        this.vehicleWarrantyPk = vehicleWarrantyPk;
    }


    public String getCoverage() {
        return Coverage;
    }

    public void setCoverage(String coverage) {
        Coverage = coverage;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getWarrantyStatus() {
        return WarrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        WarrantyStatus = warrantyStatus;
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
        return "VehicleWarrantyInformation{" +
                "vehicleWarrantyPk=" + vehicleWarrantyPk +
                ", Coverage='" + Coverage + '\'' +
                ", StartDate=" + StartDate +
                ", EndDate=" + EndDate +
                ", WarrantyStatus='" + WarrantyStatus + '\'' +
                '}';
    }
}
