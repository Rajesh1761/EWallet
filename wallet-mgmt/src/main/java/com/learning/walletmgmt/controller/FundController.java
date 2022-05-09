package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.exception.WalletException;
import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.service.WalletService;
import com.learning.walletmgmt.utils.WalletConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund")
public class FundController extends BaseController {

    /**
     *
     * @param walletService
     */
    public FundController(WalletService walletService) {
        super(walletService);
    }

    /**
     *
     * @param wallet1Id
     * @param wallet2Id
     * @param fundTransferAmount
     * @throws WalletException
     */
    @GetMapping("/transfer")
    public void transferFund(@RequestParam("wallet1ID") long wallet1Id,
                             @RequestParam("wallet2ID") long wallet2Id,
                             @RequestParam("fundTransferAmount") double fundTransferAmount)
            throws WalletException {
        if(wallet1Id == wallet2Id){
            throw new FundTransferException(WalletConstants.CANNOT_TRANSFER_FUND);
        }
        walletService.transferFund(wallet1Id,wallet2Id,fundTransferAmount);
    }

    /**
     *
     * @param wallet1Id
     * @param fundTransferAmount
     * @throws WalletException
     */
    @GetMapping("/withdraw")
    public void withDrawFund(@RequestParam("walletID") long wallet1Id,
                             @RequestParam("fundTransferAmount") double fundTransferAmount)
            throws WalletException {
        walletService.withDrawFund(wallet1Id,fundTransferAmount);
    }

    /**
     *
     * @param wallet1Id
     * @param fundAmount
     * @throws WalletException
     * @return
     */
    @GetMapping("/addFund")
    public ResponseEntity<Wallet> addFund(@RequestParam("walletID") long wallet1Id,
                                          @RequestParam("fundAmount") double fundAmount)
            throws WalletException {
        return ResponseEntity.ok(walletService.addFund(wallet1Id,fundAmount));
    }
}
