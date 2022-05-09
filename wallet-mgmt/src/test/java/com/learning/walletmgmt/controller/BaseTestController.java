package com.learning.walletmgmt.controller;

import com.learning.walletmgmt.service.WalletService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class BaseTestController {
    @Mock
    WalletService walletService;
}
