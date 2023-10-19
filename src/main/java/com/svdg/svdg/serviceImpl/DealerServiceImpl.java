package com.svdg.svdg.serviceImpl;

import com.svdg.svdg.model.Dealer;
import com.svdg.svdg.model.VehicleModel;
import com.svdg.svdg.repository.DealerRepository;
import com.svdg.svdg.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealerServiceImpl implements DealerService {
    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public Dealer saveDealer(Dealer dealer) {
        return this.dealerRepository.save(dealer);
    }

    @Override
    public List<Dealer> getAllDealers() {
        return this.dealerRepository.findAll();
    }


    @Override
    public Dealer updateDealer(Dealer dealer) {
        if(this.dealerRepository.existsById(dealer.getDealerId())){
            return this.dealerRepository.save(dealer);
        }
        else return null;
    }
}
