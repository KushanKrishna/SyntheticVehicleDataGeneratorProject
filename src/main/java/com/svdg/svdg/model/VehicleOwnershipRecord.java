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
@Table(name = "VehicleOwnershipRecord")
public class VehicleOwnershipRecord {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OwnershipRecordKey;
    private String OwnedBy;
    private Date PurchaseDate;
    private Date SaleDate;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleModel")
    private VehicleModel vehicleModel;

    public Long getOwnershipRecordKey() {
        return OwnershipRecordKey;
    }

    @JsonIgnore
    public void setOwnershipRecordKey(Long ownershipRecordKey) {
        OwnershipRecordKey = ownershipRecordKey;
    }

    public String getOwnedBy() {
        return OwnedBy;
    }

    public void setOwnedBy(String ownedBy) {
        OwnedBy = ownedBy;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public Date getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(Date saleDate) {
        SaleDate = saleDate;
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
        return "VehicleOwnershipRecord{" +
                "OwnershipRecordKey=" + OwnershipRecordKey +
                ", OwnedBy='" + OwnedBy + '\'' +
                ", PurchaseDate=" + PurchaseDate +
                ", SaleDate=" + SaleDate +
                '}';
    }
}
