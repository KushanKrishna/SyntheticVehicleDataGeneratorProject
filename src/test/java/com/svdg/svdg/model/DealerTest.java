package com.svdg.svdg.model;

import com.svdg.svdg.model.Dealer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {

    @Mock
    VehicleModel vehicleModel;

    @Test
    void testGettersAndSetters() {

        Dealer dealer = new Dealer();
        long dealerId = 1L;
        long vehiclePK = 100L;
        String dealerName = "ABC Motors";
        String GSTIN = "123456789l";
        String address = "123 Main St";
        double turnoverRatio = 0.85;

        dealer.setDealerId(dealerId);
        dealer.setVehicleModel(vehicleModel);
        dealer.setDealer(dealerName);
        dealer.setGSTIN(GSTIN);
        dealer.setAddress(address);
        dealer.setTurnoverRatio(turnoverRatio);

        assertEquals(dealerId, dealer.getDealerId());
        assertEquals(vehicleModel, dealer.getVehicleModel());
        assertEquals(dealerName, dealer.getDealer());
        assertEquals(GSTIN, dealer.getGSTIN());
        assertEquals(address, dealer.getAddress());
        assertEquals(turnoverRatio, dealer.getTurnoverRatio());
    }
}
