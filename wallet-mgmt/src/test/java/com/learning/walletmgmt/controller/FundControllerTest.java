package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.utils.WalletConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class FundControllerTest extends BaseTestController {

    @InjectMocks
    private FundController fundController;

    /**
     *
     * @throws Exception
     */
    @Test
    public void transferFundTest() throws Exception {
        doNothing().when(walletService).transferFund(1L,2L,4500);
        fundController.transferFund(1L,2L,4500);
        verify(walletService,times(1)).transferFund(1L,2L,4500);
    }

    @Test
    public void transferFundExceptionTest(){
        FundTransferException thrown = assertThrows(
                FundTransferException.class,
                () -> fundController.transferFund(1L,1L,100),
                WalletConstants.CANNOT_TRANSFER_FUND
        );
        assertTrue(thrown.getMessage().contains("same wallet"));
    }

    @Test
    public void withDrawFundTest() throws Exception {
        doNothing().when(walletService).withDrawFund(1L,250);
        fundController.withDrawFund(1L,250);
        verify(walletService,times(1)).withDrawFund(1L,250);
    }

    @Test
    public void addFundTest() throws Exception {
        Wallet dummyWallet = Wallet.builder().id(1l).walletName("Gpay")
                .walletBalanceFund(100).build();
        when(walletService.addFund(1l,100)).thenReturn(dummyWallet);
        fundController.addFund(1L,100);
        verify(walletService,times(1)).addFund(1L,100);
    }
}
