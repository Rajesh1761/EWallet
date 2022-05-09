package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WalletControllerTest extends BaseTestController{

    @InjectMocks
    private WalletController walletController;

    private Wallet wallet;

    private List<Wallet> walletList;

    private MockHttpServletRequest request;

    @BeforeEach
    public void setup(){
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        wallet = new Wallet();
        wallet.setId(1L);
        wallet.setWalletName("testWallet");
        wallet.setWalletBalanceFund(1000);
        walletList = new ArrayList<>();
        walletList.add(wallet);
    }

    @Test
    public void getWalletTest(){
        when(walletService.getWallets()).thenReturn(walletList);
        ResponseEntity<List<Wallet>> responseEntity = walletController.getWallets();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).get(0).getWalletName(),"testWallet");
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).get(0).getId(),1L);
    }

    @Test
    public void createWalletTest(){
        when(walletService.createWallet(wallet)).thenReturn(wallet);
        ResponseEntity<Wallet> responseEntity = walletController.createWallet(wallet);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals(Objects.requireNonNull(responseEntity.getBody()).getWalletBalanceFund(),1000);
    }

}
