import React,{useState,useEffect} from 'react';
import Fundservice from '../services/Fundservice';
import Walletservice from '../services/Walletservice';
import {useHistory} from 'react-router-dom';

export const AddFund = () => {
    const [wallets, setWallets] = useState([]);
    const [walletValue,setWalletValue] = useState(0);
    const [addFundValue,setAddFundValue] = useState(0);
    const history =  useHistory();

    const changeWallet = (e) =>{
        e.preventDefault();
        setWalletValue(e.target.value);
    }
    const addFund= (e) => {
        e.preventDefault();
            if(addFundValue <= 0){
                alert("Fund value should be greater then 0");
            }else{
                Fundservice.addFundAmount(walletValue,addFundValue).then((response) => {
                    alert("Fund Added successfully");
                    history.push('/');
                }).catch((error) => {
                        alert(error.response.data.message);
                });
             }
    }
    useEffect(() => {
      Walletservice.getWallets().then((response)=>{
          setWallets(response.data);
      }).catch(error=>{
          console.err(error.response.data.message);
      })
    }, [walletValue])
  return (
    <div>
    <br/><br/>
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
            <br/>
                 <h2 className='text-center'> Add fund to wallet</h2>
                 <div className='card-body'>
                     <form>
                         <div className='form-group mb-2'>
                            <label className='form-label'>Select wallet: &nbsp;&nbsp;&nbsp;</label>
                            <select className="form-select mt-3" onChange={(e)=>changeWallet(e)}>
                                 <option>Select wallet</option>
                                {
                                    wallets.map((wallet) => (
                                        <option value={wallet.id}>{wallet.walletName} (${wallet.walletBalanceFund})</option>
                                    ))
                                }

                            </select>
                          </div>
                          <div className='form-group mb-2'>
                            <div className='form-group mb-2'>
                            <label className='form-label'>Enter Fund Amount:</label>
                                <input type="text"
                                 placeholder="Enter fund amount "
                                name="addFundValue"
                                className="form-control"
                                value={addFundValue}
                                autocomplete="off"
                                onChange={(e)=>setAddFundValue(e.target.value)}
                                />
                              </div>
                          </div>
                          <div className='text-center'>
                             <button className='btn btn-primary'  onClick={(e)=>addFund(e)}>Add fund</button>
                          </div>
                     </form>
                 </div>
            </div>
        </div>
    </div>
    
</div>
  )
}
export default AddFund;
