package com.learning.walletmgmt.service;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.repository.WalletRepository;
import com.learning.walletmgmt.utils.WalletConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    private MockHttpServletRequest request;

    private Wallet wallet;

    private Wallet wallet1;

    @BeforeEach
    public void setup(){
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        wallet = new Wallet();
        wallet.setId(1L);
        wallet.setWalletName("testWallet");
        wallet.setWalletBalanceFund(1000);
        wallet1 = new Wallet();
        wallet1.setId(2L);
        wallet1.setWalletName("myWallet");
        wallet1.setWalletBalanceFund(4500);
    }

    @Test
    public void transferFundTest() throws Exception {
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));
        when(walletRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(wallet1));
        walletService.transferFund(1L,2L,500);
        verify(walletRepository,times(1)).save(wallet1);
    }

    @Test
    public void transferFundExceptionTest()  {
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));
        when(walletRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(wallet));

        FundTransferException thrown = assertThrows(
                FundTransferException.class,
                () -> walletService.transferFund(1L,2L,2000),
                WalletConstants.TRANSFER_FUND_MAKING_WALLET_ZERO
        );
        assertTrue(thrown.getMessage().contains(WalletConstants.PLEASE_USE_ANOTHER_AMOUNT));
    }

    @Test
    public void invalidWalletExceptionTest(){
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));
        Exception thrown = assertThrows(
                Exception.class,
                () -> walletService.transferFund(1L,3L,2000),
                WalletConstants.INVALID_WALLET_ID_PASSED
        );
        assertTrue(thrown.getMessage().contains(WalletConstants.INVALID_WALLET_ID_PASSED));
    }

    @Test
    public void withDrawFundTest() throws Exception {
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));
        walletService.withDrawFund(1L,500);
        verify(walletRepository,times(1)).save(wallet);
    }

    @Test
    public void withDrawFundExceptionTest(){
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));

        FundTransferException thrown = assertThrows(
                FundTransferException.class,
                () -> walletService.withDrawFund(1L,2000),
                WalletConstants.WITHDRAWAL_AMOUNT_MAKING_WALLET_ZERO
        );
        assertTrue(thrown.getMessage().contains(WalletConstants.PLEASE_USE_ANOTHER_AMOUNT));
    }

    @Test
    public void addFundTest() throws Exception {
        when(walletRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(wallet));
        walletService.addFund(1L,1000);
        verify(walletRepository,times(1)).save(wallet);
    }

    @Test
    public void createWalletTest(){
        walletService.createWallet(wallet);
        verify(walletRepository,times(1)).save(wallet);
    }

    @Test
    public void getWalletsTest(){
        walletService.getWallets();
        verify(walletRepository,times(1)).findAll();
    }

}
