import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../css/Dashboard.css';
import {useEffect, useState} from "react";
import get from "../get";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";

const mdTheme = createTheme();

const DashboardContent = () => {

    const [notifications, setNotifications] = useState([]);
    const [open, setOpen] = useState(true);

    useEffect(() => {
        async function getNotifications() {
            const note = await get('users/messages')
            const notes = await note.json()
            if(notes.val) {
                console.log(notes.val)
                setNotifications(notes.val)
            }
        }
        getNotifications()

    }, [])



    const toggleDrawer = () => {
        setOpen(!open);
    };

    const renderNotifications = (notifications) => {
        return (<ul className='stores'>
            {notifications.map((notification, index) => (
                <li key={notification.id} className='store'>
                    <div className='topStore'>
                        <div>
                            <h5 className='title'>From: {notification.sentFrom}</h5>
                            <h5 className='title'>{notification.message}</h5>
                        </div>
                    </div>

                    <div className="store-footer">
                        <div
                            className='meta-data'>Sent At {notification.sentAt} </div>
                    </div>
                </li>
            ))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyAppBar title={"My Messages"} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                    {notifications ? renderNotifications(notifications) : <h2>Loading...</h2>}
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

