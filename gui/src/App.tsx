import React from 'react';
import './App.css';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import Signin from "./components/Signin";
import SignUp from "./components/Signup";
import Checkout from "./components/checkout/Checkout";
import Dashboard from "./components/dashboard/Dashboard";

function App() {
  return (
      <Dashboard></Dashboard>
  );
}

export default App;
