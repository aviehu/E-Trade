import './css/App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignIn from "./components/Signin";
import SignUp from "./components/Signup";
import Dashboard from "./components/dashboard/Dashboard";
import Checkout from "./components/checkout/Checkout";
import CreateStore from "./components/store/CreateStore";
import Mystores from "./components/store/Mystores";
import Store from "./components/store/Store";
import EditStore from './components/store/EditStore'
import AddProduct from './components/store/AddProduct'
import AddOwner from './components/store/AddOwner'
import AddManager from './components/store/AddManager'
import RemoveOwner from './components/store/RemoveOwner'
import RemoveManager from './components/store/RemoveManager'

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
               <Route path="/store/:name" element={<Store />} />
               <Route path="/store/edit/:name" element={<EditStore/>} />
               <Route path="/store/edit/:name/addproduct" element={<AddProduct/>} />
               <Route path="/store/edit/:name/addowner" element={<AddOwner/>} />
               <Route path="/store/edit/:name/addmanager" element={<AddManager/>} />
               <Route path="/store/edit/:name/removeowner" element={<RemoveOwner/>} />
               <Route path="/store/edit/:name/removemanager" element={<RemoveManager/>} />
           </Routes>
       </Router>
    )
}

export default App;
