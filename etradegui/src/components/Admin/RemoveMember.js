import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../css/Dashboard.css';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useNavigate} from 'react-router-dom';
import post from "../post";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";
import MyError from "../MyError";
import get from "../get";

const mdTheme = createTheme();

const DashboardContent = () => {
    const [open, setOpen] = React.useState(true);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)
    const [online, setOnline] = React.useState([])
    const [offline, setOffline] = React.useState([])

    React.useEffect(() => {

        async function getOffline() {
            const res = await get("users/offlinemembers")
            const ans = await res.json()
            if(ans.val) {
                setOffline(ans.val)
            } else {
                setError(ans.err)
                setHasError(true)
            }
        }

        async function getOnline() {
            const res = await get("users/onlinemembers")
            const ans = await res.json()
            if(ans.val) {
                setOnline(ans.val)
            } else {
                setError(ans.err)
                setHasError(true)
            }
        }
        getOnline()
        getOffline()
        console.log(online)
        console.log(offline)
    }, [])

    const handleRemove = async (userName) => {
        const body = {
            appointee: userName
        }
        try {
            const res = await post(body, `users/remove`)
            const boolRes = await res.json()
            if(boolRes.val) {
                navigate(`/etrade`);
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    };

    const renderOnline = () => {
        return (<ul className='stores'>
            {online.map((userName, index) => (<li key={userName} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{userName}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <Button onClick={() => handleRemove(userName)}>Remove Member</Button>
                </div>
            </li>))}
        </ul>);
    }

    const renderOffline = () => {
        return (<ul className='stores'>
            {offline.map((userName, index) => (<li key={userName} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{userName}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <Button onClick={() => handleRemove(userName)}>Remove Member</Button>
                </div>
            </li>))}
        </ul>);
    }

    const navigate = useNavigate();

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={"Remove Member"} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                        <Box component="form" noValidate sx={{mt: 3}}>
                                            <Grid container spacing={2}>
                                                <Grid item xl={12}>
                                                    <h4>Online:</h4>
                                                </Grid>
                                                {renderOnline()}
                                                <Grid item xl={12}>
                                                    <h4>offline:</h4>
                                                </Grid>
                                                {renderOffline()}
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

