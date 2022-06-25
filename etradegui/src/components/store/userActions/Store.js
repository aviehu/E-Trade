import * as React from 'react';
import {createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../../css/Dashboard.css';
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import AddProductDialog from '../editStore/AddProductDialog';
import get from "../../util/get";
import Button from "@mui/material/Button";
import post from "../../util/post";
import MyAppBar from "../../dashboard/MyAppBar";
import MyDrawer from "../../dashboard/MyDrawer";
import MyError from "../../util/MyError";
import BidDialog from './BidDialog';

const mdTheme = createTheme();

const DashboardContent = ({setSuccessMsg}) => {
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
    const [myStores, setMyStores] = React.useState([]);
    const [isAdmin, setIsAdmin] = React.useState(false);
    const [management, setManagement] = React.useState(null)

    const getManagement = async () => {
        const res = await get(`stores/getmanagement/${name}`);
        const ans = await res.json();
        if(ans.val) {
            setManagement(ans.val)
        }
    }

    useEffect(() => {
        async function getStore() {
            const res = await get(`stores/info/${name}`)
            const ans = await res.json()
            const products = ans.val
            setProducts(products.first)
        }

        async function getMyStores() {
            const res = await get(`stores/ofuser`)
            const ans = await res.json()
            const myS = ans.val
            setMyStores(myS);
            console.log(myS)
        }

        async function checkAdmin() {
            const res = await get('users/isadmin')
            const ans = await res.json()
            if(ans.val) {
                setIsAdmin(ans.val);
            }
        }

        getManagement();
        checkAdmin()
        getStore()
        getMyStores()
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
            setSuccessMsg(`product ${product} has been added to your cart`)
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
            setSuccessMsg('your bid has been submited')
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
                            {myStores.includes(name) ?
                                <Grid item xs={12}>
                                    <Button onClick={() => navigate(`/store/edit/${name}`)}>Edit Store</Button>
                                </Grid>
                                :
                                null
                            }
                            {myStores.includes(name) || isAdmin ?
                                <Grid item xs={12}>
                                    <Button onClick={() => navigate(`/store/purchasehistory/${name}`)}>Purchase History</Button>
                                </Grid>
                                :
                                null
                            }
                            { (myStores.includes(name) && !Object.keys(management.managers).includes(localStorage.getItem("userName")))  || isAdmin ?
                                <Grid item xs={12}>
                                    <Button onClick={() => navigate(`/store/edit/${name}/viewmanagement`)}>View Management</Button>
                                </Grid>
                                :
                                null
                            }
                        </Grid>
                    </Container>
                </Box>
            </Box>
            {immediateDialog ? <AddProductDialog open={immediateDialog} handleAdding={handleAdding} setAmount={setAmount}/> : null}
            {bidDialog ? <BidDialog productName={product} open={bidDialog} setOpen={setBidDialog} handleBid={sendBid}  /> : null}

        </ThemeProvider>
    );
}

export default function Dashboard({setSuccessMsg}) {
    return <DashboardContent setSuccessMsg={setSuccessMsg}/>;
}

