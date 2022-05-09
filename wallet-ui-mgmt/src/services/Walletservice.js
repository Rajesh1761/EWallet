import axios from 'axios';

const WALLET_BASE_REST_END_POINT = 'http://localhost:8080/wallets';

class Walletservice{
    getWallets(){
        return axios.get(WALLET_BASE_REST_END_POINT);
    }
    createWallet(wallet){
        return axios.post(WALLET_BASE_REST_END_POINT,wallet);
    }
}
export default new Walletservice();