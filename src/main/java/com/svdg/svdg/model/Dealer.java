package com.svdg.svdg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "dealerinformation")
public class Dealer {
    @Id
    @Column(name = "Dealer_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long DealerId;
    private String Dealer;
    private String GSTIN;
    private String Address;
    private Double TurnoverRatio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleModel")
    private VehicleModel vehicleModel;

    public long getDealerId() {
        return DealerId;
    }

    @JsonIgnore
    public void setDealerId(long dealerId) {
        DealerId = dealerId;
    }


    public String getDealer() {
        return Dealer;
    }

    public void setDealer(String dealer) {
        Dealer = dealer;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getTurnoverRatio() {
        return TurnoverRatio;
    }

    public void setTurnoverRatio(double turnoverRatio) {
        TurnoverRatio = turnoverRatio;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    @JsonIgnore
    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Dealer() {
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "DealerId=" + DealerId +
                ", Dealer='" + Dealer + '\'' +
                ", GSTIN='" + GSTIN + '\'' +
                ", Address='" + Address + '\'' +
                ", TurnoverRatio=" + TurnoverRatio +
                ", vehicleModel=" + vehicleModel +
                '}';
    }
}
