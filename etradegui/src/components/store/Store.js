import * as React from 'react';
import {createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../css/Dashboard.css';
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import AddProductDialog from './AddProductDialog';
import get from "../get";
import Button from "@mui/material/Button";
import post from "../post";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";
import MyError from "../MyError";
import BidDialog from './BidDialog';

const mdTheme = createTheme();

const DashboardContent = () => {
    const { name } = useParams();
    const navigate = useNavigate();
    const [open, setOpen] = React.useState(true);
    const [immediateDialog, setImmediateDialog] = React.useState(false);
    const [bidDialog, setBidDialog] = React.useState(false);
    const [amount, setAmount] = React.useState(0);
    const [product, setProduct] = React.useState("");
    const [products, setProducts] = useState(null);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)

    useEffect(() => {
        async function getStore() {
            const res = await get(`stores/info/${name}`)
            const ans = await res.json()
            const products = ans.val
            setProducts(products)
        }
        getStore()
    }, [])

    async function handleAdding(){
        const body = {
            productName: product,
            storeName: name,
            quantity: amount
        }
        const res = await post(body, 'stores/addproducttocart')
        const ans = await res.json()
        if(ans.val) {
            navigate("/etrade");
            setImmediateDialog(false);
        } else {
            setError(ans.err)
            setHasError(true)
        }
    }

    function handleImmediate(product){
        setProduct(product.productName)
        setImmediateDialog(true);
    }

    function handleBid(product){
        setProduct(product.productName)
        setBidDialog(true);
    }

    async function sendBid(body) {
        const res = await post(body, `stores/addbid/${name}`)
        const ans = await res.json()
        if(ans.val) {
            navigate("/etrade");
            setBidDialog(false);
        } else {
            setError(ans.err)
            setHasError(true)
        }
    }

    const renderProducts = () => {
        return (<ul className='stores'>
            {products.map((prod,index) => (<li className='store'>
                <div className='topStore' >
                    <div>
                        <h5 className='title' >Name: {prod.productName}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Purchase Type: {prod.purchaseOption}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Amount In Stock: {prod.amount}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Base Price: {prod.price}$</h5>
                    </div>
                </div>

                <div className="store-footer">
                    {prod.purchaseOption === "IMMEDIATE" ?
                        <Button onClick={() => handleImmediate(prod)}>Buy</Button> :
                        <Button onClick={() => handleBid(prod)}>Bid</Button>
                    }
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{ display: 'flex' }}>
                <CssBaseline />
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={name} open={open} toggleDrawer={() => {setOpen(!open)}}/>
                <MyDrawer open={open} setOpen={setOpen}/>
                <Box
                    component="main"
                    sx={{
                        backgroundColor: (theme) =>
                            theme.palette.mode === 'light'
                                ? theme.palette.grey[100]
                                : theme.palette.grey[900],
                        flexGrow: 1,
                        height: '100vh',
                        overflow: 'auto',
                    }}
                >
                    <Toolbar />
                    <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                        <Grid container spacing={3}>
                            <Grid item xs={12}>
                                <main>
                                    {products ? renderProducts() : <h2>Loading...</h2>}
                                </main>
                            </Grid>
                        </Grid>
                    </Container>
                </Box>
            </Box>
            {immediateDialog ? <AddProductDialog open={immediateDialog} handleAdding={handleAdding} setAmount={setAmount}/> : null}
            {bidDialog ? <BidDialog productName={product} open={bidDialog} setOpen={setBidDialog} handleBid={sendBid}  /> : null}
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent />;
}

