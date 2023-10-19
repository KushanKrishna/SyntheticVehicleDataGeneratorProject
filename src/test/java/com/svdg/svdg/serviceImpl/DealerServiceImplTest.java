package com.svdg.svdg.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.Dealer;
import com.svdg.svdg.repository.DealerRepository;
import com.svdg.svdg.service.DealerService;

@SpringBootTest
public class DealerServiceImplTest {
    @Autowired
    private DealerService dealerService;

    @MockBean
    private DealerRepository dealerRepository;

    @BeforeEach
    public void setUp() {
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
        when(dealerRepository.save(any(Dealer.class))).thenReturn(dealer1);
    }

    @Test
    public void testSaveDealer() {
        Dealer dealer = new Dealer();
        dealer.setDealerId(1L);
        dealer.setDealer("ABC Motors");

        Dealer savedDealer = dealerService.saveDealer(dealer);
        assertEquals(dealer.getDealerId(), savedDealer.getDealerId());
        assertEquals(dealer.getDealer(), savedDealer.getDealer());

        verify(dealerRepository, times(1)).save(dealer);
    }

    @Test
    public void testGetAllDealers() {
        List<Dealer> retrievedDealers = dealerService.getAllDealers();

        assertEquals(2, retrievedDealers.size());
        assertEquals(1L, retrievedDealers.get(0).getDealerId());
        assertEquals("ABC Motors", retrievedDealers.get(0).getDealer());
        assertEquals(2L, retrievedDealers.get(1).getDealerId());
        assertEquals("XYZ Auto", retrievedDealers.get(1).getDealer());

        verify(dealerRepository, times(1)).findAll();
    }
}

