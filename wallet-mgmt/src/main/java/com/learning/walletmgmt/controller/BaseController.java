package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.service.WalletService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Added cross- origin, so it can be accessed by UI
 */
@CrossOrigin("*")
@Component
public class BaseController {

    WalletService walletService;

    public BaseController(WalletService walletService) {
        this.walletService = walletService;
    }
}
