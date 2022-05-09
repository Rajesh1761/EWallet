import './App.css';
import { BrowserRouter as Router,Route,Switch } from 'react-router-dom';
import { ListOfWallets } from './components/ListOfWallets';
import HeaderComponents from './components/HeaderComponents';
import FooterComponents from './components/FooterComponents';
import CreateWalletComponent from './components/CreateWalletComponent';
import { TransferFund } from './components/TransferFund';
import { WithdrawFund } from './components/WithdrawFund';
import AddFund from './components/AddFund';

function App() {
  return (
    <div >
      <Router>
          <HeaderComponents/>
            <div className='container'>
                <Switch>
                  <Route exact path='/' component={ListOfWallets}></Route>
                  <Route path='/add-wallet' component={CreateWalletComponent}></Route>
                  <Route path='/add-fund' component={AddFund}></Route>
                  <Route path='/withdraw-fund' component={WithdrawFund}></Route>
                  <Route path='/transfer-fund' component={TransferFund}></Route>
                </Switch>
            </div>
          <FooterComponents/>
      </Router>
    </div>
  );
}

export default App;
