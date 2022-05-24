import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import MenuIcon from '@mui/icons-material/Menu';
import NotificationsIcon from '@mui/icons-material/Notifications';
import Link from "@mui/material/Link";
import '../../css/Dashboard.css';
import {useEffect, useState} from "react";
import get from "../get";
import SocketProvider from "../SocketProvider";
import MessageDialog from '../MessageDialog'
import MyDrawer from "./MyDrawer";
import MyAppBar from "./MyAppBar";
import MyError from "../MyError";
import Button from "@mui/material/Button";
import post from "../post";





const mdTheme = createTheme();

const DashboardContent = () => {
    const [message, setMessage] = useState(null);
    const [stores, setStores] = useState(null);
    const [open, setOpen] = React.useState(true);

    async function getStores() {
        const { createSocket } = SocketProvider(setMessage);
        createSocket(localStorage.getItem("userName"))
        const res = await get('stores/');
        const ans = await res.json()
        const storesName = ans.val
        if(storesName) {
            const stores = storesName.map((store, id) => {
                return {
                    "id": id,
                    "title": store
                }
            })
            setStores(stores);
        }
    }

    useEffect(() => {

        getStores()
    }, [])



    const toggleDrawer = () => {
        setOpen(!open);
    };

    const renderStores = (stores) => {
        return (<ul className='stores'>
            {stores.map((store, index) => (
                <li key={store.id} className='store'>
                    <div className='topStore'>
                        <div>
                            <Link href={`/store/${store.title}`}>
                                <h5 className='title'>{store.title}</h5>
                            </Link>
                        </div>
                    </div>

                    {/*<div className="store-footer">*/}
                    {/*    <div*/}
                    {/*        className='meta-data'>By {store.userEmail} | {new Date(store.creationTime).toLocaleString()}</div>*/}
                    {/*</div>*/}
                </li>
            ))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MessageDialog message={message} open={message !== null} handleClose={() => setMessage(null)}/>
                <MyAppBar title={"Market"} open={open} toggleDrawer={toggleDrawer}/>
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
                        <Grid container spacing={3}>
                            <Grid item xs={12}>
                                <main>
                                    {stores ? renderStores(stores) : <h2>Loading...</h2>}
                                </main>
                            </Grid>
                        </Grid>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}

export default function Dashboard() {
    return <DashboardContent />;
}

