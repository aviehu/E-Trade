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
           </Routes>
       </Router>
    )
}

export default App;
