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
import Button from "@mui/material/Button";
import post from "../post";

const mdTheme = createTheme();

const DashboardContent = () => {
    const [bids, setBids] = React.useState(null);
    const [open, setOpen] = React.useState(true);
    const toggleDrawer = () => {
        setOpen(!open);
    };

    async function getMyBids() {
        const res = await get(`users/mybids`)
        const ans = await res.json()
        setBids(ans.val)
    }

    useEffect(() => {
        getMyBids()
    }, [])

    const handleAnswer = async (approve, storeName, bidId) => {
        const body = {
            approve,
            bidId
        }
        const res = await post(body, `stores/offerreview/${storeName}`)
        await res.json()
        await getMyBids()
    }

    const renderBids = () => {
        return (<ul className='stores'>
            {bids.map((bid) => (<li key={bid.bidId} className='store'>
                <div className='topStore'>
                    <div>
                        <h5 className='title'>{!bid.approvedByBidder ? `Counter Offer On: ${bid.productName}` : `${bid.productName}`}</h5>
                    </div>
                    <div>
                        <h5>{`${bid.amount} $`}</h5>
                    </div>
                    {bid.rejected ?
                        <div>
                            <h5>{`The bid was rejected`}</h5>
                        </div> : null
                    }
                </div>

                <div className="store-footer">
                    <Button disabled={bid.rejected || bid.approvedByBidder} onClick={() => handleAnswer(true, bid.storeName, bid.bidId)}>Accept</Button>
                    <Button disabled={bid.rejected || bid.approvedByBidder} onClick={() => handleAnswer(false, bid.storeName, bid.bidId)}>Reject</Button>
                </div>
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
                                    {bids ? renderBids() : <h2>Loading...</h2>}
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

