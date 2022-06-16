import * as React from 'react';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Toolbar from '@mui/material/Toolbar';
import Paper from '@mui/material/Paper';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import AddressForm from './AddressForm';
import PaymentForm from './PaymentForm';
import Review from './Review';
import {useNavigate} from 'react-router-dom';
import {useEffect, useState} from "react";
import get from "../util/get";
import post from "../util/post";
import SocketProvider from "../util/SocketProvider";
import MessageDialog from '../util/MessageDialog'
import MyError from "../util/MyError";


const steps = ['Your cart','Shipping address', 'Payment details'];

const orderNumber = 10; //todo

function getStepContent(step, products, totalPrice, setAddress, setCity, setAptNum, setCardNum, setExpDate, setCcv, expDate, setStreetNum ,setCountry , setZip, year, setYear, month, setMonth, setCardHolder, setCardHolderId, getMyBasket) {
    switch (step) {
        case 0:
            return <Review products={products} totalPrice={totalPrice} getMyBasket={getMyBasket}/>;

        case 1:
            return <AddressForm setAddress={setAddress} setCountry={setCountry} setZip={setZip} setCity={setCity} setAptNum={setAptNum} setStreetNum={setStreetNum}/>;
        case 2:
            return <PaymentForm expDate={expDate} setCardNum={setCardNum} setExpDate={setExpDate} setCcv={setCcv} year={year} setYear={setYear} month={month} setMonth={setMonth} setCardHolder={setCardHolder} setCardHolderId={setCardHolderId}/>;
        default:
            throw new Error('Unknown step');
    }
}

const theme = createTheme();

export default function Checkout() {
    const [activeStep, setActiveStep] = React.useState(0);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)
    const [totalPrice, setTotalPrice] = React.useState(0);
    const [streetNum, setStreetNum] = useState(0);
    const [address, setAddress] = useState("")
    const [city, setCity] = useState("")
    const [aptNumber, setAptNum] = useState("")
    const [cardNum, setCardNum] = useState(0);
    const [expDate, setExpDate] = useState(new Date());
    const [ccv, setCcv] = useState(0);
    const [products, setProducts] = React.useState([]);
    const navigate = useNavigate();
    const [message, setMessage] = useState(null)
    const [zip, setZip] = useState(0);
    const [country, setCountry] = useState("");
    const [year, setYear] = useState(2022);
    const [month, setMonth] = useState(1);
    const [cardHolder, setCardHolder] = useState("");
    const [cardHolderId, setCardHolderId] = useState(0);

    async function getMyBasket() {
        const { createSocket } = SocketProvider(setMessage);
        createSocket(localStorage.getItem("userName"))
        const res = await get("stores/displaycart")
        const ans = await res.json();
        setProducts(ans.val)

        const res2 = await get("stores/getcartprice")
        const ans2 = await res2.json();
        const val = ans2.val
        setTotalPrice(val);
    }

    useEffect(() => {
        getMyBasket()
    }, [])

    const handleNext = async () => {
        if(activeStep === steps.length - 1) {
            const body = {
                card: cardNum,
                expDate: expDate,
                cvv: ccv,
                city: city,
                street: address,
                stNum: streetNum,
                apartmentNum: aptNumber,
                month: month,
                year: year,
                holderName: cardHolder,
                id: cardHolderId,
                country: country,
                zip: zip,

            }
            console.log(body)
            const ans = await post(body, "stores/purchase")
            const res = await ans.json()
            if(res.val) {
                setActiveStep(activeStep + 1);
                setTimeout(() => {
                    navigate('/etrade');
                }, 5000)
            }
            else{
                setError(res.err)
                setHasError(true)
                setTimeout(() => {
                    navigate('/etrade');
                }, 5000)
            }
        } else {
            setActiveStep(activeStep + 1);
        }
    };

    const handleBack = () => {
        if(activeStep === 0){
            navigate("/etrade");
        }
        setActiveStep(activeStep - 1);
    };

    return (
        <ThemeProvider theme={theme}>
            <MessageDialog message={message} open={message !== null} handleClose={() => setMessage(null)}/>
            <CssBaseline />
            <MyError open={hasError} setOpen={setHasError} error={error}/>
            <AppBar
                position="absolute"
                color="default"
                elevation={0}
                sx={{
                    position: 'relative',
                    borderBottom: (t) => `1px solid ${t.palette.divider}`,
                }}
            >
                <Toolbar>
                    <Typography variant="h6" color="inherit" noWrap>
                        E-Trade
                    </Typography>
                </Toolbar>
            </AppBar>
            <Container component="main" maxWidth="sm" sx={{ mb: 4 }}>
                <Paper variant="outlined" sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}>
                    <Typography component="h1" variant="h4" align="center">
                        Checkout
                    </Typography>
                    <Stepper activeStep={activeStep} sx={{ pt: 3, pb: 5 }}>
                        {steps.map((label) => (
                            <Step key={label}>
                                <StepLabel>{label}</StepLabel>
                            </Step>
                        ))}
                    </Stepper>
                    <React.Fragment>
                        {activeStep === steps.length ? (
                            <React.Fragment>
                                <Typography variant="h5" gutterBottom>
                                    Thank you for your order.
                                </Typography>
                                <Typography variant="subtitle1">
                                    Your order number is {orderNumber}.<br/>
                                    We will stay in touch via e-mail.
                                </Typography>
                            </React.Fragment>
                        ) : (
                            <React.Fragment>
                                {getStepContent(activeStep, products, totalPrice, setAddress, setCity, setAptNum, setCardNum, setExpDate, setCcv, expDate, setStreetNum, setCountry, setZip, year, setYear, month, setMonth, setCardHolder, setCardHolderId, getMyBasket)}
                                <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                                    {(
                                        <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                                            Back
                                        </Button>
                                    )}
                                    <Button
                                        variant="contained"
                                        onClick={handleNext}
                                        sx={{ mt: 3, ml: 1 }}
                                    >
                                        {activeStep === steps.length - 1 ? 'Place order' : 'Next'}
                                    </Button>
                                </Box>
                            </React.Fragment>
                        )}
                    </React.Fragment>
                </Paper>
            </Container>
        </ThemeProvider>
    );
}
