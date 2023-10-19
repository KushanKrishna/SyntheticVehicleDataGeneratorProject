package com.svdg.svdg.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.svdg.svdg.model.Dealer;

@SpringBootTest
public class DealerRepositoryTest {

    @MockBean
    private DealerRepository dealerRepository;

   @Test
    public void testFindAllDealers() {
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

        List<Dealer> retrievedDealers = dealerRepository.findAll();

        assertEquals(2, retrievedDealers.size());
        assertEquals(1L, retrievedDealers.get(0).getDealerId());
        assertEquals("ABC Motors", retrievedDealers.get(0).getDealer());
        assertEquals(2L, retrievedDealers.get(1).getDealerId());
        assertEquals("XYZ Auto", retrievedDealers.get(1).getDealer());

        verify(dealerRepository, times(1)).findAll();
    }

    @Test
    public void testSaveDealer() {
        Dealer dealer = new Dealer();
        dealer.setDealerId(1L);
        dealer.setDealer("ABC Motors");

        when(dealerRepository.save(any(Dealer.class))).thenReturn(dealer);

        Dealer savedDealer = dealerRepository.save(dealer);

        assertEquals(dealer.getDealerId(), savedDealer.getDealerId());
        assertEquals(dealer.getDealer(), savedDealer.getDealer());

        verify(dealerRepository, times(1)).save(dealer);
    }
}
