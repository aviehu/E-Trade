import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../../css/Dashboard.css';
import Button from "@mui/material/Button";
import {useNavigate, useParams} from 'react-router-dom';
import post from "../../util/post";
import get from "../../util/get";
import {useEffect, useState} from "react";
import MyAppBar from "../../dashboard/MyAppBar";
import ChangePurchaseType from './ChangePurchaseType'
import EditProduct from "./EditProduct";
import MyDrawer from "../../dashboard/MyDrawer";
import StoreBids from './StoreBids';
import StoreAwaitingApproval from "./StoreAwaitingApproval";

const mdTheme = createTheme();

const DashboardContent = ({setSuccessMsg}) => {
    const { name } = useParams()
    const [products, setProducts] = useState(null);
    const [open, setOpen] = React.useState(true);
    const [productToChange, setProductToChange] = useState(null)
    const [typeToChange, setTypeToChange] = useState("IMMEDIATE")
    const [openDialog, setOpenDialog] = useState(false)
    const [openEditDialog, setOpenEditDialog] = useState(false)
    const [amount, setAmount] = useState(0)
    const [price, setPrice] = useState(0)
    const [isOpen, setIsOpen] = useState(false);
    const [isFounder, setIsFounder] = useState(false);


    async function getFounder() {
        const res = await get(`stores/founder/${name}`)
        const ans = await res.json();
        if(ans.val) {
            setIsFounder(ans.val === localStorage.getItem('userName'))
        }
    }

    async function getStore() {
        const res = await get(`stores/info/${name}`)
        const ans = await res.json()
        const products = ans.val
        setProducts(products.first)
        setIsOpen(products.second)
    }

    useEffect(() => {
        getFounder()
        getStore()
    }, [])


    const navigate = useNavigate();

    async function handleEdit(){
        const body = {
            productName: productToChange.productName,
            amount,
            price
        }
        const res = await post(body, `stores/editproduct/${name}`)
        const ans = await res.json()
        if(ans.val) {
            setSuccessMsg(`product ${productToChange.productName} has been edited`)
            await getStore()
            setOpenEditDialog(false)
        }
    }

    async function handleChange() {
        const body = {
            productName: productToChange.productName,
            purchaseOption: typeToChange
        }
        const res = await post(body, `stores/changepurchaseoption/${name}`)
        const ans = await res.json()
        if(ans.val) {
            setSuccessMsg(`product ${productToChange.productName} purchase type has been change to ${typeToChange}`)
            await getStore()
            setOpenDialog(false)
        }
    }

    async function handleClose() {
        const res = await get(`stores/closestore/${name}`)
        const ans = await res.json();
        if(ans.val) {
            setSuccessMsg(`Store ${name} has been closed`)
            navigate('/etrade')
        }
    }

    async function handleOpen() {
        const res = await get(`stores/openstore/${name}`)
        const ans = await res.json();
        if(ans.val) {
            setSuccessMsg(`Store ${name} has been reopened`)
            navigate('/etrade')
        }
    }


    function changePurchaseType(product) {
        setProductToChange(product)
        setOpenDialog(true)
    }

    async function handleProduct(product) {
        const body = {
            productName: product.title,
        }
        const res = await post(body, `stores/removeproductfromstore/${name}`)
        const ans = await res.json()
        if(ans.val) {
            setSuccessMsg(`product - ${product.title} has been removed from the store`)
            await getStore()
        }
    }

    const renderProducts = (products) => {
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
                    <Button onClick={async () => await handleProduct(prod)}>Remove</Button>
                    <Button onClick={ () => {
                        setProductToChange(prod);
                        setAmount(prod.amount);
                        setPrice(prod.price);
                        setOpenEditDialog(true);
                    } }>Edit</Button>
                    <Button onClick={async () => await changePurchaseType(prod)}>Change Purchase Type</Button>
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyAppBar title={`${name} - Edit`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                    <Toolbar/>
                    <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                        {/*Here the form of the create store*/}
                        <Grid container spacing={3}>
                            <ThemeProvider theme={mdTheme}>
                                <Container component="main" maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                                    <CssBaseline/>
                                    <Box
                                        sx={{
                                            marginTop: 8,
                                            display: 'flex',
                                            flexDirection: 'column',
                                            alignItems: 'center',
                                        }}
                                    >
                                        <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
                                            <Grid container spacing={3}>
                                                <Grid item xs={12}>
                                                    <main>
                                                        <h2>Products:</h2>
                                                        {products ? renderProducts(products) : <h2>Loading...</h2>}
                                                        <h2>Bids:</h2>
                                                        <StoreBids setSuccessMsg={setSuccessMsg} storeName={name}/>
                                                        <h2>Appointed Owners:</h2>
                                                        <StoreAwaitingApproval setSuccessMsg={setSuccessMsg} storeName={name}/>
                                                    </main>
                                                </Grid>
                                            </Grid>
                                        </Container>
                                        <Box component="form" noValidate sx={{mt: 3}}>
                                            <Grid container spacing={2}>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/addproduct`)}>Add Product</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/addowner`)}>Add Store Owner</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/addmanager`)}>Add Store Manager</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/removeowner`)}>Remove Store Owner</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/removemanager`)}>Remove Store Manager</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/adddiscount`)}>Add Discount</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={() => navigate(`/store/edit/${name}/addpolicy`)}>Add Policy</Button>
                                                </Grid>
                                                {
                                                    isOpen  && isFounder ?
                                                    <Grid item xl={12}>
                                                        <Button onClick={handleClose}>Close Store</Button>
                                                    </Grid> : null
                                                }

                                                {
                                                    !isOpen && isFounder ?
                                                    <Grid item xl={12}>
                                                        <Button onClick={handleOpen}>Open Store</Button>
                                                    </Grid> : null
                                                }

                                            </Grid>
                                        </Box>
                                    </Box>
                                </Container>
                            </ThemeProvider>
                        </Grid>
                    </Container>
                </Box>
            </Box>
            {openDialog ? <ChangePurchaseType open={openDialog} type={typeToChange} setType={setTypeToChange} handleChange={handleChange} /> : null}
            {openEditDialog ? <EditProduct open={openEditDialog} handleChange={handleEdit} amount={amount} setAmount={setAmount} price={price} setPrice={setPrice}/> : null}

        </ThemeProvider>
    );
}

export default function Dashboard({setSuccessMsg}) {
    return <DashboardContent setSuccessMsg={setSuccessMsg}/>;
}

