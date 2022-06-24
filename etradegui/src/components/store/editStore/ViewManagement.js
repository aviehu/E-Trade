import {useEffect, useState} from "react";
import get from "../../util/get";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import MyError from "../../util/MyError";
import MyAppBar from "../../dashboard/MyAppBar";
import MyDrawer from "../../dashboard/MyDrawer";
import Toolbar from "@mui/material/Toolbar";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import {useParams} from "react-router-dom";

const mdTheme = createTheme();

export default function ViewManagement() {

    const [management, setManagement] = useState(null);
    const [hasError, setHasError] = useState(false)
    const [error, setError] = useState(null)
    const [open, setOpen] = useState(true)
    const { name } = useParams();

    const getManagement = async () => {
        const res = await get(`stores/getmanagement/${name}`);
        const ans = await res.json();
        if(ans.val) {
            setManagement(ans.val)
        }
    }

    useEffect(() => {
        getManagement()
    }, [])


    const renderOwners = () => {
        return (<ul className='stores'>
            {management.owners.map((ownerName, index) => (<li key={ownerName} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{ownerName}</h5>
                    </div>
                </div>
                <div className="store-footer">
                    <h5>Permission: OWNER</h5>
                </div>
            </li>))}
        </ul>);
    }

    const renderManagers = () => {
        return (<ul className='stores'>
            {Object.keys(management.managers).map((managersName, index) => (<li key={managersName} className='store'>

                <div className='topStore' >
                    <div>
                        <h5 className='title' >{managersName}</h5>
                    </div>
                </div>

                <div className="store-footer">
                    <h5>Permission: {management.managers[managersName]}</h5>
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={`${name} - View Management`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                                <Grid item xs={12}>
                                                    <h1>Owners:</h1>
                                                </Grid>
                                                {management ? renderOwners() : "Loading..."}
                                                <Grid item xs={12}>
                                                    <h1>managers:</h1>
                                                </Grid>
                                                {management ? renderManagers() : "Loading..."}
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
    )

}
