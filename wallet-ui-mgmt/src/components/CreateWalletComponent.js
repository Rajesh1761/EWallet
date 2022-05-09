import React ,{useState} from 'react';
import Walletservice from '../services/Walletservice';
import {useHistory} from 'react-router-dom';
const CreateWalletComponent = () => {
    const [id, setId] = useState('');
    const [walletName, setWalletName] = useState('');
    const [walletBalanceFund, setWalletBalanceFund] = useState('2000');
    const history =  useHistory();

    const saveWallet = (e) =>{
        e.preventDefault();
        const wallet = {id,walletName,walletBalanceFund};
        Walletservice.createWallet(wallet).then((response)=>{
            const updatedWallet = response.data;
            alert("Wallet "+updatedWallet.walletName +" created successfully with fund value "+updatedWallet.walletBalanceFund);
            history.push('/');
        }).catch(error=>{
            console.err(error.response.data.message);
        });
    }
  return (
    <div>
        <br/><br/>
        <div className='container'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                <br/>
                     <h2 className='text-center'> Add New Wallet</h2>
                     <div className='card-body'>
                         <form>
                             <div className='form-group mb-2'>
                                <label className='form-label'>Wallet name:</label>
                                <input type="text"
                                 placeholder="Enter wallet name "
                                name="walletname"
                                className="form-control"
                                value={walletName}
                                autocomplete="off"
                                onChange={(e)=>setWalletName(e.target.value)}
                                />
                              </div>
                              <div className='form-group mb-2'>
                                <label className='form-label'>Enter wallet intial fund:</label>
                                <input type="text"
                                 placeholder="Enter inital fund "
                                name="walletBalanceFund"
                                className="form-control"
                                value={walletBalanceFund}
                                autocomplete="off"
                                onChange={(e)=>setWalletBalanceFund(e.target.value)}
                                />
                              </div>
                              <div className='text-center'>
                                 <button className='btn btn-primary' onClick={(e)=>saveWallet(e)}>Add wallet</button>
                              </div>
                         </form>
                     </div>
                </div>
            </div>
        </div>
        
    </div>
  )
}

export default CreateWalletComponent;