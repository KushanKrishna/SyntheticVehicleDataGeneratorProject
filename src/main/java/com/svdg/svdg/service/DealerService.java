package com.svdg.svdg.service;

import com.svdg.svdg.model.Dealer;

import java.util.List;

public interface DealerService {
    public Dealer saveDealer(Dealer dealer);
    public List<Dealer> getAllDealers();
//    public List<Dealer> findByVehiclePk(long pk);

    public Dealer updateDealer(Dealer dealer);

}
