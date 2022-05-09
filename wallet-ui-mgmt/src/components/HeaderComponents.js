import React from 'react'
import { Link } from 'react-router-dom';
const HeaderComponents = () => {
  return (
    <div>
        <header>
            <nav className='navbar navbar-expand-md navbar-dark bd-navbar'>
                <div>
                    <Link to="/" className='navbar-brand'>&nbsp;&nbsp;&nbsp;<b>E-wallets</b></Link>
                </div>
            </nav>
        </header>
    </div>
  )
}

export default HeaderComponents