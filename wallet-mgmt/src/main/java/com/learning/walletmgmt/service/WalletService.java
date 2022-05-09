package com.learning.walletmgmt.service;

import com.learning.walletmgmt.exception.FundTransferException;
import com.learning.walletmgmt.exception.WalletException;
import com.learning.walletmgmt.model.Wallet;
import com.learning.walletmgmt.repository.WalletRepository;
import com.learning.walletmgmt.utils.WalletConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WalletService {

    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    /**
     *
     * @param walletId
     * @return
     * @throws WalletException
     */
    private Wallet getWalletByID(Long walletId) throws WalletException {
        Optional<Wallet> walletByID = walletRepository.findById(walletId);
        if(walletByID.isPresent()){
            return walletByID.get();
        }
        log.info(WalletConstants.INVALID_WALLET_ID_PASSED);
        throw new WalletException(WalletConstants.INVALID_WALLET_ID_PASSED);
    }

    /**
     *  @param wallet
     * @param fundTransferAmount
     * @return
     */
    private Wallet withDrawFundAmount(Wallet wallet,
                                      double fundTransferAmount) {
        wallet.setWalletBalanceFund(wallet.getWalletBalanceFund()-fundTransferAmount);
        return updateWallet(wallet);
    }

    /**
     *
     * @param wallet1
     * @param wallet2
     * @param fundTransferAmount
     */
    @Transactional
    private void transferFundAmount(Wallet wallet1,
                                    Wallet wallet2,
                                    double fundTransferAmount) {
        wallet1.setWalletBalanceFund(wallet1.getWalletBalanceFund()-fundTransferAmount);
        wallet2.setWalletBalanceFund(wallet2.getWalletBalanceFund()+fundTransferAmount);
        updateWallet(wallet1);
        updateWallet(wallet2);
    }

    /**
     *
     * @param wallet1Id
     * @param wallet2Id
     * @param fundTransferAmount
     * @throws WalletException
     */
    public void transferFund(long wallet1Id,
                             long wallet2Id,
                             double fundTransferAmount) throws WalletException {
        Wallet wallet1= getWalletByID(wallet1Id);
        Wallet wallet2= getWalletByID(wallet2Id);
        if(wallet1.getWalletBalanceFund()-fundTransferAmount < 0){
            log.info(WalletConstants.TRANSFER_FUND_MAKING_WALLET_ZERO);
            throw new FundTransferException(WalletConstants.TRANSFER_FUND_MAKING_WALLET_ZERO);
        }else{
            transferFundAmount(wallet1,wallet2,fundTransferAmount);
        }
    }

    /**
     *
     * @param wallet1Id
     * @param fundTransferAmount
     * @throws WalletException
     */
    public void withDrawFund(long wallet1Id,
                             double fundTransferAmount) throws WalletException {
        Wallet wallet= getWalletByID(wallet1Id);
        if(wallet.getWalletBalanceFund()-fundTransferAmount < 0){
            log.info(WalletConstants.WITHDRAWAL_AMOUNT_MAKING_WALLET_ZERO);
            throw new FundTransferException(WalletConstants.WITHDRAWAL_AMOUNT_MAKING_WALLET_ZERO);
        }else{
            withDrawFundAmount(wallet,fundTransferAmount);
        }
    }

    /**
     *
     * @param wallet1Id
     * @param fundAmount
     * @return returning updated wallet values
     * @throws WalletException
     */
    public Wallet addFund(long wallet1Id,
                        double fundAmount) throws WalletException {
        Wallet wallet= getWalletByID(wallet1Id);
        wallet.setWalletBalanceFund(wallet.getWalletBalanceFund()+fundAmount);
        return updateWallet(wallet);
    }

    /**
     *
     * @param wallet
     * @return updated wallet as save() returns updated Objects from DB
     */
    private Wallet updateWallet(Wallet wallet){
        return walletRepository.save(wallet);
    }

    /**
     *
     * @param wallet
     * @return updated wallet as save() returns updated Objects from DB
     */
    public Wallet createWallet(Wallet wallet){
        return walletRepository.save(wallet);
    }

    /**
     *
     * @return save() returns Lists of all wallets
     */
    public List<Wallet> getWallets(){
        return walletRepository.findAll();
    }


}
