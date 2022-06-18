import './css/App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignIn from "./components/dashboard/Signin";
import SignUp from "./components/dashboard/Signup";
import Dashboard from "./components/dashboard/Dashboard";
import Checkout from "./components/checkout/Checkout";
import CreateStore from "./components/store/userActions/CreateStore";
import Mystores from "./components/dashboard/Mystores";
import Store from "./components/store/userActions/Store";
import EditStore from './components/store/editStore/EditStore'
import AddProduct from './components/store/editStore/AddProduct'
import AddOwner from './components/store/editStore/AddOwner'
import AddManager from './components/store/editStore/AddManager'
import RemoveOwner from './components/store/editStore/RemoveOwner'
import RemoveManager from './components/store/editStore/RemoveManager'
import MyMessages from './components/dashboard/MyMessages'
import RemoveMember from './components/Admin/RemoveMember'
import CloseStore from './components/Admin/CloseStore'
import SearchMarket from './components/dashboard/SearchMarket'
import AddDiscount from './components/store/editStore/policies/AddDiscount'
import AddPolicy from './components/store/editStore/policies/AddPolicy'
import MyBids from './components/dashboard/MyBids'
import Stats from './components/Admin/Stats'

function App() {
    return (
       <Router>
           <Routes>
               <Route path="/" element={<SignIn />} />
               <Route path="/signup" element={<SignUp />} />
               <Route path="/etrade" element={<Dashboard />} />
               <Route path="/cart" element={<Checkout />} />
               <Route path="/createstore" element={<CreateStore />} />
               <Route path="/mystores" element={<Mystores />} />
               <Route path="/mymessages" element={<MyMessages />} />
               <Route path="/mybids" element={<MyBids />} />
               <Route path="/search" element={<SearchMarket />} />
               <Route path="/admin/removemember" element={<RemoveMember/>}/>
               <Route path="/admin/closestore" element={<CloseStore/>}/>
               <Route path="/store/:name" element={<Store />} />
               <Route path="/store/edit/:name" element={<EditStore/>} />
               <Route path="/store/edit/:name/addproduct" element={<AddProduct/>} />
               <Route path="/store/edit/:name/addowner" element={<AddOwner/>} />
               <Route path="/store/edit/:name/addmanager" element={<AddManager/>} />
               <Route path="/store/edit/:name/removeowner" element={<RemoveOwner/>} />
               <Route path="/store/edit/:name/removemanager" element={<RemoveManager/>} />
               <Route path="/store/edit/:name/adddiscount" element={<AddDiscount />} />
               <Route path="/store/edit/:name/addpolicy" element={<AddPolicy />} />
               <Route path="/admin/viewtraffic" element={<Stats/>}/>
           </Routes>
       </Router>
    )
}

export default App;
