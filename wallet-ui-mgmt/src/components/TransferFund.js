import React,{useState,useEffect} from 'react';
import Fundservice from '../services/Fundservice';
import Walletservice from '../services/Walletservice';
import {useHistory} from 'react-router-dom';

export const TransferFund = () => {
    const [wallets1, setWallets1] = useState([]);
    const [wallets2, setWallets2] = useState([]);
    const [wallet1Value,setWallet1Value] = useState(0);
    const [wallet2Value,setWallet2Value] = useState(0);
    const [transferFundValue,setTransferValue] = useState(0);
    const history =  useHistory();

    const changeWallet1 = (e) =>{
        e.preventDefault();
        setWallet1Value(e.target.value);
    }
    const changeWallet2= (e) =>{
        e.preventDefault();
        setWallet2Value(e.target.value);
    }
    const transferFund= (e) => {
        e.preventDefault();
        if(wallet1Value === wallet2Value){
            alert("Cannot transfer fund to same wallet");
        }else{
            if(transferFundValue <= 0){
                alert("Fund transfer value should be greater then 0");
            }else{
                Fundservice.transferFund(wallet1Value,wallet2Value,transferFundValue).then((response) => {
                    alert("Fund Transfer successfully");
                    history.push('/');
                }).catch((error) => {
                        alert(error.response.data.message);
                });
            }
        }
    }
    useEffect(() => {
      Walletservice.getWallets().then((response)=>{
          setWallets1(response.data);
          setWallets2(response.data);
      }).catch(error=>{
          console.err(error);
      })
    }, [wallet1Value,wallet2Value])
  return (
    <div>
    <br/><br/>
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
            <br/>
                 <h2 className='text-center'> Transfer fund wallet to wallet</h2>
                 <div className='card-body'>
                     <form>
                         <div className='form-group mb-2'>
                            <label className='form-label'>From wallet: &nbsp;&nbsp;&nbsp;</label>
                            <select className="form-select mt-3" onChange={(e)=>changeWallet1(e)}>
                                 <option>Select wallet 1</option>
                                {
                                    wallets1.map((wallet) => (
                                        <option value={wallet.id}>{wallet.walletName}-{wallet.walletBalanceFund}</option>
                                    ))
                                }

                            </select>
                          </div>
                          <div className='form-group mb-2'>
                            <label className='form-label'>To wallet: &nbsp;&nbsp;&nbsp;</label>
                            <select className="form-select mt-3" onChange={(e)=>changeWallet2(e)}>
                                    <option>Select wallet 2</option>
                                {
                                    wallets2.map((wallet) => (
                                        <option value={wallet.id}>{wallet.walletName}-{wallet.walletBalanceFund}</option>
                                    ))
                                }
                            </select>
                          </div>
                          <div className='form-group mb-2'>
                            <div className='form-group mb-2'>
                            <label className='form-label'>Enter Transfer Fund Amount:</label>
                                <input type="text"
                                 placeholder="Enter fund amount "
                                name="transferFundValue"
                                className="form-control"
                                value={transferFundValue}
                                autocomplete="off"
                                onChange={(e)=>setTransferValue(e.target.value)}
                                />
                              </div>
                          </div>
                          <div className='text-center'>
                             <button className='btn btn-primary'  onClick={(e)=>transferFund(e)}>Transfer fund</button>
                          </div>
                     </form>
                 </div>
            </div>
        </div>
    </div>
    
</div>
  )
}
export default TransferFund;
