package com.learning.walletmgmt;

import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.repository.WalletRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class WalletMgmtApplication implements CommandLineRunner {

	private WalletRepository walletRepository;

	public WalletMgmtApplication(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(WalletMgmtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Loading some dummy wallets in DB");
		List<Wallet> walletList = new ArrayList<>();
		walletList.add(Wallet.builder().id(1l).walletName("Gpay").walletBalanceFund(2000.0).build());
		walletList.add(Wallet.builder().id(2l).walletName("ApplePay").walletBalanceFund(2000.0).build());
		walletList.add(Wallet.builder().id(3l).walletName("WhatsAppPay").walletBalanceFund(2000.0).build());
		walletList.add(Wallet.builder().id(4l).walletName("payTm").walletBalanceFund(2000.0).build());
		walletRepository.saveAll(walletList);
		log.info("{} Dummy wallet loaded successfully",walletList.size());
	}
}
