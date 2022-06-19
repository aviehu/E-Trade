import './css/App.css';
import React, {useState} from 'react';
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
import {Alert, Snackbar} from "@mui/material";


function App() {
    const [successMsg, setSuccessMsg] = useState("")

    return (
       <Router>
           <Routes>
               <Route path="/" element={<SignIn setSuccessMsg={setSuccessMsg} />} />
               <Route path="/signup" element={<SignUp setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/etrade" element={<Dashboard />} />
               <Route path="/cart" element={<Checkout />} />
               <Route path="/createstore" element={<CreateStore setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/mystores" element={<Mystores />} />
               <Route path="/mymessages" element={<MyMessages />} />
               <Route path="/mybids" element={<MyBids setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/search" element={<SearchMarket />} />
               <Route path="/admin/removemember" element={<RemoveMember setSuccessMsg={setSuccessMsg}/>}/>
               <Route path="/admin/closestore" element={<CloseStore setSuccessMsg={setSuccessMsg}/>}/>
               <Route path="/store/:name" element={<Store setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name" element={<EditStore setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/addproduct" element={<AddProduct setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/addowner" element={<AddOwner setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/addmanager" element={<AddManager setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/removeowner" element={<RemoveOwner setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/removemanager" element={<RemoveManager setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/adddiscount" element={<AddDiscount setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/store/edit/:name/addpolicy" element={<AddPolicy setSuccessMsg={setSuccessMsg}/>} />
               <Route path="/admin/viewtraffic" element={<Stats/>}/>
           </Routes>
           <Snackbar anchorOrigin={{ vertical:'bottom', horizontal:'right' }} open={successMsg.length > 0} autoHideDuration={4000} onClose={() => setSuccessMsg("")}>
               <Alert onClose={() => setSuccessMsg("")} severity="success" sx={{ width: '100%' }}>
                   {successMsg}
               </Alert>
           </Snackbar>
       </Router>
    )
}

export default App;
