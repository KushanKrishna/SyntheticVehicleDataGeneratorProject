package com.svdg.svdg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.svdg.svdg.model.Dealer;
import com.svdg.svdg.model.VehicleOwnershipRecord;
import com.svdg.svdg.model.VehicleServiceRecord;
import com.svdg.svdg.model.VehicleWarrantyInformation;

import java.util.Date;
import java.util.List;

public class UpdateVehicleDataRequestDto {
    private long Vehicle_PK;
    private String VIN;
    private String Vehicle_Reg_Num;// Unique
    private Date Vehicle_Reg_Date;
    private String Vehicle_Engine_Num;
    private String Vehicle_Brand;
    private String Vehicle_Model;
    private String Vehicle_Exterior_Color;
    private String Vehicle_Interior_Color;
    private Long Vehicle_Type_ID;
    private String Vehicle_Emission_Class;
    private Double Vehicle_Mileage;
    private Long Vehicle_Engine_Type;
    private Long Vehicle_Transmission_Type;
    private Double Vehicle_Price;
    private Long Vehicle_Feature_ID;
    private Integer Vehicle_Year;
    private Long Vehicle_Condition_ID;
    private Long Vehicle_Location_ID;
    private Long Vehicle_Status_ID;
    private List<Dealer> dealerList;
    private List<VehicleServiceRecord> vehicleServiceRecordlist;
    private VehicleOwnershipRecord vehicleOwnershipRecord;
    private VehicleWarrantyInformation vehicleWarrantyInformation;

    public UpdateVehicleDataRequestDto(long vehicle_PK, String VIN, String vehicle_Reg_Num, Date vehicle_Reg_Date, String vehicle_Engine_Num, String vehicle_Brand, String vehicle_Model, String vehicle_Exterior_Color, String vehicle_Interior_Color, Long vehicle_Type_ID, String vehicle_Emission_Class, Double vehicle_Mileage, Long vehicle_Engine_Type, Long vehicle_Transmission_Type, Double vehicle_Price, Long vehicle_Feature_ID, Integer vehicle_Year, Long vehicle_Condition_ID, Long vehicle_Location_ID, Long vehicle_Status_ID, List<Dealer> dealerList, List<VehicleServiceRecord> vehicleServiceRecordlist, VehicleOwnershipRecord vehicleOwnershipRecord, VehicleWarrantyInformation vehicleWarrantyInformation) {
        Vehicle_PK = vehicle_PK;
        this.VIN = VIN;
        Vehicle_Reg_Num = vehicle_Reg_Num;
        Vehicle_Reg_Date = vehicle_Reg_Date;
        Vehicle_Engine_Num = vehicle_Engine_Num;
        Vehicle_Brand = vehicle_Brand;
        Vehicle_Model = vehicle_Model;
        Vehicle_Exterior_Color = vehicle_Exterior_Color;
        Vehicle_Interior_Color = vehicle_Interior_Color;
        Vehicle_Type_ID = vehicle_Type_ID;
        Vehicle_Emission_Class = vehicle_Emission_Class;
        Vehicle_Mileage = vehicle_Mileage;
        Vehicle_Engine_Type = vehicle_Engine_Type;
        Vehicle_Transmission_Type = vehicle_Transmission_Type;
        Vehicle_Price = vehicle_Price;
        Vehicle_Feature_ID = vehicle_Feature_ID;
        Vehicle_Year = vehicle_Year;
        Vehicle_Condition_ID = vehicle_Condition_ID;
        Vehicle_Location_ID = vehicle_Location_ID;
        Vehicle_Status_ID = vehicle_Status_ID;
        this.dealerList = dealerList;
        this.vehicleServiceRecordlist = vehicleServiceRecordlist;
        this.vehicleOwnershipRecord = vehicleOwnershipRecord;
        this.vehicleWarrantyInformation = vehicleWarrantyInformation;
    }

    public long getVehicle_PK() {
        return Vehicle_PK;
    }
    @JsonIgnore
    public void setVehicle_PK(long vehicle_PK) {
        Vehicle_PK = vehicle_PK;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getVehicle_Reg_Num() {
        return Vehicle_Reg_Num;
    }

    public void setVehicle_Reg_Num(String vehicle_Reg_Num) {
        Vehicle_Reg_Num = vehicle_Reg_Num;
    }

    public Date getVehicle_Reg_Date() {
        return Vehicle_Reg_Date;
    }

    public void setVehicle_Reg_Date(Date vehicle_Reg_Date) {
        Vehicle_Reg_Date = vehicle_Reg_Date;
    }

    public String getVehicle_Engine_Num() {
        return Vehicle_Engine_Num;
    }

    public void setVehicle_Engine_Num(String vehicle_Engine_Num) {
        Vehicle_Engine_Num = vehicle_Engine_Num;
    }

    public String getVehicle_Brand() {
        return Vehicle_Brand;
    }

    public void setVehicle_Brand(String vehicle_Brand) {
        Vehicle_Brand = vehicle_Brand;
    }

    public String getVehicle_Model() {
        return Vehicle_Model;
    }

    public void setVehicle_Model(String vehicle_Model) {
        Vehicle_Model = vehicle_Model;
    }

    public String getVehicle_Exterior_Color() {
        return Vehicle_Exterior_Color;
    }

    public void setVehicle_Exterior_Color(String vehicle_Exterior_Color) {
        Vehicle_Exterior_Color = vehicle_Exterior_Color;
    }

    public String getVehicle_Interior_Color() {
        return Vehicle_Interior_Color;
    }

    public void setVehicle_Interior_Color(String vehicle_Interior_Color) {
        Vehicle_Interior_Color = vehicle_Interior_Color;
    }

    public Long getVehicle_Type_ID() {
        return Vehicle_Type_ID;
    }

    public void setVehicle_Type_ID(Long vehicle_Type_ID) {
        Vehicle_Type_ID = vehicle_Type_ID;
    }

    public String getVehicle_Emission_Class() {
        return Vehicle_Emission_Class;
    }

    public void setVehicle_Emission_Class(String vehicle_Emission_Class) {
        Vehicle_Emission_Class = vehicle_Emission_Class;
    }

    public Double getVehicle_Mileage() {
        return Vehicle_Mileage;
    }

    public void setVehicle_Mileage(Double vehicle_Mileage) {
        Vehicle_Mileage = vehicle_Mileage;
    }

    public Long getVehicle_Engine_Type() {
        return Vehicle_Engine_Type;
    }

    public void setVehicle_Engine_Type(Long vehicle_Engine_Type) {
        Vehicle_Engine_Type = vehicle_Engine_Type;
    }

    public Long getVehicle_Transmission_Type() {
        return Vehicle_Transmission_Type;
    }

    public void setVehicle_Transmission_Type(Long vehicle_Transmission_Type) {
        Vehicle_Transmission_Type = vehicle_Transmission_Type;
    }

    public Double getVehicle_Price() {
        return Vehicle_Price;
    }

    public void setVehicle_Price(Double vehicle_Price) {
        Vehicle_Price = vehicle_Price;
    }

    public Long getVehicle_Feature_ID() {
        return Vehicle_Feature_ID;
    }

    public void setVehicle_Feature_ID(Long vehicle_Feature_ID) {
        Vehicle_Feature_ID = vehicle_Feature_ID;
    }

    public Integer getVehicle_Year() {
        return Vehicle_Year;
    }

    public void setVehicle_Year(Integer vehicle_Year) {
        Vehicle_Year = vehicle_Year;
    }

    public Long getVehicle_Condition_ID() {
        return Vehicle_Condition_ID;
    }

    public void setVehicle_Condition_ID(Long vehicle_Condition_ID) {
        Vehicle_Condition_ID = vehicle_Condition_ID;
    }

    public Long getVehicle_Location_ID() {
        return Vehicle_Location_ID;
    }

    public void setVehicle_Location_ID(Long vehicle_Location_ID) {
        Vehicle_Location_ID = vehicle_Location_ID;
    }

    public Long getVehicle_Status_ID() {
        return Vehicle_Status_ID;
    }

    public void setVehicle_Status_ID(Long vehicle_Status_ID) {
        Vehicle_Status_ID = vehicle_Status_ID;
    }

    public List<Dealer> getDealerList() {
        return dealerList;
    }

    public void setDealerList(List<Dealer> dealerList) {
        this.dealerList = dealerList;
    }

    public List<VehicleServiceRecord> getVehicleServiceRecordlist() {
        return vehicleServiceRecordlist;
    }

    public void setVehicleServiceRecordlist(List<VehicleServiceRecord> vehicleServiceRecordlist) {
        this.vehicleServiceRecordlist = vehicleServiceRecordlist;
    }

    public VehicleOwnershipRecord getVehicleOwnershipRecord() {
        return vehicleOwnershipRecord;
    }

    public void setVehicleOwnershipRecord(VehicleOwnershipRecord vehicleOwnershipRecord) {
        this.vehicleOwnershipRecord = vehicleOwnershipRecord;
    }

    public VehicleWarrantyInformation getVehicleWarrantyInformation() {
        return vehicleWarrantyInformation;
    }

    public void setVehicleWarrantyInformation(VehicleWarrantyInformation vehicleWarrantyInformation) {
        this.vehicleWarrantyInformation = vehicleWarrantyInformation;
    }

    @Override
    public String toString() {
        return "UpdateVehicleDataRequestDto{" +
                "Vehicle_PK=" + Vehicle_PK +
                ", VIN='" + VIN + '\'' +
                ", Vehicle_Reg_Num='" + Vehicle_Reg_Num + '\'' +
                ", Vehicle_Reg_Date=" + Vehicle_Reg_Date +
                ", Vehicle_Engine_Num='" + Vehicle_Engine_Num + '\'' +
                ", Vehicle_Brand='" + Vehicle_Brand + '\'' +
                ", Vehicle_Model='" + Vehicle_Model + '\'' +
                ", Vehicle_Exterior_Color='" + Vehicle_Exterior_Color + '\'' +
                ", Vehicle_Interior_Color='" + Vehicle_Interior_Color + '\'' +
                ", Vehicle_Type_ID=" + Vehicle_Type_ID +
                ", Vehicle_Emission_Class='" + Vehicle_Emission_Class + '\'' +
                ", Vehicle_Mileage=" + Vehicle_Mileage +
                ", Vehicle_Engine_Type=" + Vehicle_Engine_Type +
                ", Vehicle_Transmission_Type=" + Vehicle_Transmission_Type +
                ", Vehicle_Price=" + Vehicle_Price +
                ", Vehicle_Feature_ID=" + Vehicle_Feature_ID +
                ", Vehicle_Year=" + Vehicle_Year +
                ", Vehicle_Condition_ID=" + Vehicle_Condition_ID +
                ", Vehicle_Location_ID=" + Vehicle_Location_ID +
                ", Vehicle_Status_ID=" + Vehicle_Status_ID +
                ", dealerList=" + dealerList +
                ", vehicleServiceRecordlist=" + vehicleServiceRecordlist +
                ", vehicleOwnershipRecord=" + vehicleOwnershipRecord +
                ", vehicleWarrantyInformation=" + vehicleWarrantyInformation +
                '}';
    }
}
