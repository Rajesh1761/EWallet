package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController extends BaseController{

    public WalletController(WalletService walletService) {
        super(walletService);
    }
    /**
     *
     * @return Lists of all wallets as DB
     */
    @GetMapping
    public ResponseEntity<List<Wallet>> getWallets(){
         return ResponseEntity.ok(walletService.getWallets());
    }

    /**
     *
     * @param wallet
     * @return updated wallet as DB returns updated Objects
     */
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.createWallet(wallet));
    }

}
