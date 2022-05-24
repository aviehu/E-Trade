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
import post from "../post";
import get from "../get";
import {useEffect, useState} from "react";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";

const mdTheme = createTheme();

const DashboardContent = () => {
    const { name } = useParams()
    const [products, setProducts] = useState(null);
    const [open, setOpen] = React.useState(true);

    async function getStore() {
        const res = await get(`stores/info/${name}`)
        const ans = await res.json()
        const products = ans.val
        const fixedProducts = products.map((productName, id) => {
            return {
                "id": id,
                "title": productName
            }
        })
        setProducts(fixedProducts)
    }

    useEffect(() => {
        getStore()
    }, [])


    const navigate = useNavigate();

    async function handleProduct(product) {
        const body = {
            productName: product.title,
        }
        const res = await post(body, `stores/removeproductfromstore/${name}`)
        const ans = await res.json()
        if(ans.val) {
            await getStore()
        }
    }

    const renderProducts = (products) => {
        return (<ul className='stores'>
            {products.map((product,index) => (<li key={product.id} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{product.title}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <Button onClick={async () => await handleProduct(product)}>Remove</Button>
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
                                                        {products ? renderProducts(products) : <h2>Loading...</h2>}
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

