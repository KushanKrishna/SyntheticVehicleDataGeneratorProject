package com.svdg.svdg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.Dealer;
import com.svdg.svdg.repository.DealerRepository;

@SpringBootTest
public class DealerServiceTest {

    @Autowired
    private DealerService dealerService;

    @MockBean
    private DealerRepository dealerRepository;

    @Test
    public void testSaveDealer() {
        Dealer dealer = new Dealer();
        dealer.setDealerId(1L);
        dealer.setDealer("ABC Motors");

        when(dealerRepository.save(dealer)).thenReturn(dealer);

        Dealer savedDealer = dealerService.saveDealer(dealer);

        assertEquals(dealer.getDealerId(), savedDealer.getDealerId());
        assertEquals(dealer.getDealer(), savedDealer.getDealer());

        verify(dealerRepository, times(1)).save(dealer);
    }

    @Test
    public void testGetAllDealers() {
        Dealer dealer1 = new Dealer();
        dealer1.setDealerId(1L);
        dealer1.setDealer("ABC Motors");

        Dealer dealer2 = new Dealer();
        dealer2.setDealerId(2L);
        dealer2.setDealer("XYZ Auto");

        List<Dealer> dealerList = new ArrayList<>();
        dealerList.add(dealer1);
        dealerList.add(dealer2);

        when(dealerRepository.findAll()).thenReturn(dealerList);

        List<Dealer> retrievedDealers = dealerService.getAllDealers();

        assertEquals(dealerList.size(), retrievedDealers.size());
        assertEquals(dealerList.get(0).getDealerId(), retrievedDealers.get(0).getDealerId());
        assertEquals(dealerList.get(1).getDealerId(), retrievedDealers.get(1).getDealerId());

        verify(dealerRepository, times(1)).findAll();
    }
}
