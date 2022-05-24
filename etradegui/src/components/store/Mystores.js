import * as React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Link from "@mui/material/Link";
import '../../css/Dashboard.css';
import {useEffect} from "react";
import get from "../get";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";

const mdTheme = createTheme();

const DashboardContent = () => {
    const [stores, setStores] = React.useState(null);
    const [open, setOpen] = React.useState(true);
    const toggleDrawer = () => {
        setOpen(!open);
    };

    useEffect(() => {
        async function getMyStores() {
            const res = await get(`stores/ofuser`)
            const ans = await res.json()
            const stores = ans.val
            const fixedStores = stores.map((storeName, id) => {
                return {
                    "id": id,
                    "title": storeName
                }
            })
            setStores(fixedStores)
        }
        getMyStores()
    }, [])

    const renderStores = (stores) => {
        return (<ul className='stores'>
            {stores.map((store,index) => (<li key={store.id} className='store'>

                <div className='topStore'>
                    <div>
                        <Link href={`/store/edit/${store.title}`}>
                            <h5 className='title'>{store.title}</h5>
                        </Link>
                    </div>
                </div>

                {/*<div className="store-footer">*/}
                {/*    <div className='meta-data'>By {store.userEmail} | { new Date(store.creationTime).toLocaleString()}</div>*/}
                {/*</div>*/}
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{ display: 'flex' }}>
                <CssBaseline />
                <MyAppBar title={"My Stores"} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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

