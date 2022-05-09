import axios from 'axios';

const FUND_TRANSFER_REST_END_POINT = 'http://localhost:8080/fund';

class Fundservice{
    transferFund(wallet1ID,wallet2ID,fundTransferAmount){
        return axios.get(FUND_TRANSFER_REST_END_POINT+'/transfer?'+'wallet1ID='+wallet1ID+'&wallet2ID='+wallet2ID+'&fundTransferAmount='+fundTransferAmount);
    }
    withDrawFundAmount(walletID,fundTransferAmount){
        return axios.get(FUND_TRANSFER_REST_END_POINT+'/withdraw?'+'walletID='+walletID+'&fundTransferAmount='+fundTransferAmount);
    }
    addFundAmount(walletID,fundAmount){
        return axios.get(FUND_TRANSFER_REST_END_POINT+'/addFund?'+'walletID='+walletID+'&fundAmount='+fundAmount);
    }
}
export default new Fundservice();