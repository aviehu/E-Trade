import * as React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import NotificationsIcon from '@mui/icons-material/Notifications';
import {mainListItems} from '../listItems';
import '../../css/Dashboard.css';
import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import AddProductDialog from './AddProductDialog';
import get from "../get";
import Button from "@mui/material/Button";
import post from "../post";

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
    ({ theme, open }) => ({
        '& .MuiDrawer-paper': {
            position: 'relative',
            whiteSpace: 'nowrap',
            width: drawerWidth,
            transition: theme.transitions.create('width', {
                easing: theme.transitions.easing.sharp,
                duration: theme.transitions.duration.enteringScreen,
            }),
            boxSizing: 'border-box',
            ...(!open && {
                overflowX: 'hidden',
                transition: theme.transitions.create('width', {
                    easing: theme.transitions.easing.sharp,
                    duration: theme.transitions.duration.leavingScreen,
                }),
                width: theme.spacing(7),
                [theme.breakpoints.up('sm')]: {
                    width: theme.spacing(9),
                },
            }),
        },
    }),
);

const mdTheme = createTheme();

const DashboardContent: React.FC = () => {
    const { name } = useParams();
    const navigate = useNavigate();
    const [open, setOpen] = React.useState(true);
    const [openDialog, setOpenDialog] = React.useState(false);
    const [amount, setAmount] = React.useState(0);
    const [product, setProduct] = React.useState("");
    const [products, setProducts] = useState(null);
    const toggleDrawer = () => {
        setOpen(!open);
    };



    useEffect(() => {
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
            setOpenDialog(false);
        }
    }

    function handleProduct(product){
        setProduct(product.title)
        setOpenDialog(true);
    }

    const renderStores = (stores) => {
        return (<ul className='stores'>
            {stores.map((store,index) => (<li key={store.id} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{store.title}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <Button onClick={() => handleProduct(store)}>Buy</Button>
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{ display: 'flex' }}>
                <CssBaseline />
                <AppBar position="absolute" open={open}>
                    <Toolbar
                        sx={{
                            pr: '24px', // keep right padding when drawer closed
                        }}
                    >
                        <IconButton
                            edge="start"
                            color="inherit"
                            aria-label="open drawer"
                            onClick={toggleDrawer}
                            sx={{
                                marginRight: '36px',
                                ...(open && { display: 'none' }),
                            }}
                        >
                            <MenuIcon />
                        </IconButton>
                        <Typography
                            component="h1"
                            variant="h6"
                            color="inherit"
                            noWrap
                            sx={{ flexGrow: 1 }}
                        >
                            E-Trade
                        </Typography>
                        <IconButton color="inherit">
                            <Badge badgeContent={4} color="secondary">
                                <NotificationsIcon />
                            </Badge>
                        </IconButton>
                    </Toolbar>
                </AppBar>
                <Drawer variant="permanent" open={open}>
                    <Toolbar
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'flex-end',
                            px: [1],
                        }}
                    >
                        <IconButton onClick={toggleDrawer}>
                            <ChevronLeftIcon />
                        </IconButton>
                    </Toolbar>
                    <Divider />
                    <List component="nav">
                        {mainListItems}
                        <Divider sx={{ my: 1 }} />
                    </List>
                </Drawer>
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
                                    {products ? renderStores(products) : <h2>Loading...</h2>}
                                </main>
                            </Grid>
                        </Grid>
                    </Container>
                </Box>
            </Box>
            {openDialog ? <AddProductDialog open={openDialog} handleAdding={handleAdding} setAmount={setAmount}/> : null}
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent />;
}

