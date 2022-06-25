import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../css/Dashboard.css';
import Button from "@mui/material/Button";
import {useNavigate, useParams} from 'react-router-dom';
import post from "../util/post";
import get from "../util/get";
import {useEffect, useState} from "react";
import MyAppBar from "./MyAppBar";
import MyDrawer from "./MyDrawer";
import {TextField} from "@mui/material";
import MyError from "../util/MyError";

const mdTheme = createTheme();

const DashboardContent = () => {
    const { name } = useParams()
    const [products, setProducts] = useState([]);
    const [open, setOpen] = React.useState(true);
    const [search, setSearch] = useState("")
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)

    const navigate = useNavigate();

    async function handleKeyword() {
        const body = {
            search: search
        }
        const res = await post(body, "stores/searchbykw")
        const ans = await res.json()
        if(ans.val) {
            setProducts(ans.val)
        } else {
            setError(ans.err)
            setHasError(true)
        }
    }

    async function handleCategory() {
        const body = {
            search: search
        }
        const res = await post(body, "stores/searchbycat")
        const ans = await res.json()
        if(ans.val) {
            setProducts(ans.val)
        } else {
            setError(ans.err)
            setHasError(true)
        }
    }

    async function handleName() {
        const body = {
            search: search
        }
        const res = await post(body, "stores/searchbyname")
        const ans = await res.json()
        if(ans.val) {
            setProducts(ans.val)
        } else {
            setError(ans.err)
            setHasError(true)
        }
    }


    const renderProducts = (products) => {
        return (<ul className='stores'>
            {products.map((product,index) => (<li key={product.productName} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >Name: {product.productName}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Purchase Type: {product.purchaseOption}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Amount In Stock: {product.amount}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Base Price: {product.price}$</h5>
                    </div>
                    <div>
                        <h5 className='title' >Store: {product.storeName}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <Button onClick={() => navigate(`/store/${product.storeName}`)}>Go To Shop</Button>
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
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
                                                        {products ? renderProducts(products) : <h2>Loading...</h2>}
                                                    </main>
                                                </Grid>
                                            </Grid>
                                        </Container>
                                        <Box component="form" noValidate sx={{mt: 3}}>
                                            <Grid container spacing={2}>
                                                <Grid item xl={12}>
                                                    <TextField label={"Search Word"} onChange={(event) => {setSearch(event.target.value)}}></TextField>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={handleName}>Search By Name</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={handleKeyword}>Search By Keyword</Button>
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <Button onClick={handleCategory}>Search By Category</Button>
                                                </Grid>

                                            </Grid>
                                        </Box>
                                    </Box>
                                </Container>
                            </ThemeProvider>
                        </Grid>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent/>;
}

