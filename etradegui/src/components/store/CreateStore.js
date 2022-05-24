import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
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
import Avatar from "@mui/material/Avatar";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Link from "@mui/material/Link";
import {Card} from "@mui/material";
import {useNavigate} from 'react-router-dom';
import post from "../post";
import MyAppBar from "../dashboard/MyAppBar";
import MyDrawer from "../dashboard/MyDrawer";
import MyError from "../MyError";

const mdTheme = createTheme();

const DashboardContent = () => {
    const [open, setOpen] = React.useState(true);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const body = {
            storeName: data.get("storeName"),
            card: data.get("cardNumber")
        }
        try {
            const res = await post(body, 'stores/openstore')
            const boolRes = await res.json()
            if(boolRes.val) {
                navigate("/etrade");
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    };

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={"Create Store"} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                            <Box component="form" noValidate onSubmit={handleSubmit} sx={{mt: 3}}>
                                                <Grid container spacing={2}>
                                                    <Grid item xl={22}>
                                                        <TextField
                                                            required
                                                            fullWidth
                                                            id="storeName"
                                                            label="Store Name"
                                                            name="storeName"
                                                            autoComplete="storeName"
                                                        />
                                                    </Grid>
                                                    <Grid item xl={22}>
                                                        <TextField
                                                            required
                                                            fullWidth
                                                            name="cardNumber"
                                                            label="Credit Card Number"
                                                            type="cardNumber"
                                                            id="cardNumber"
                                                            autoComplete="cardNumber"
                                                        />
                                                    </Grid>
                                                </Grid>
                                                <Button
                                                    type="submit"
                                                    fullWidth
                                                    variant="contained"
                                                    sx={{mt: 3, mb: 2}}
                                                >
                                                    Open Store
                                                </Button>
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

