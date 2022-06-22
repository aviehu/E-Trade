import {useEffect, useState} from "react";
import get from "../../util/get";
import Button from "@mui/material/Button";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import MyError from "../../util/MyError";
import MyAppBar from "../../dashboard/MyAppBar";
import MyDrawer from "../../dashboard/MyDrawer";
import Toolbar from "@mui/material/Toolbar";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import {useParams} from "react-router-dom";

const mdTheme = createTheme();

export default function PurchaseHistory() {
    const { name } = useParams()
    const [purchases, setPurchases] = useState([]);
    const [hasError, setHasError] = useState(false);
    const [error, setError] = useState(null);
    const [open, setOpen] = useState(true)

    async function getPurchases() {
        const res = await get(`stores/storepurchasehistory/${name}`);
        const ans = await res.json();
        if(ans.val) {
            setPurchases(ans.val);
        }
    }

    useEffect(() => {
        getPurchases()
    }, [])

    const renderProds = (productsMap) => {
        const keys = Object.keys(productsMap);
        const ans = keys.map((key,) => {
            return <h5>{key}: {productsMap[key]}</h5>
        })
        return ans
    }

    const renderPurchases = () => {
        return (<ul className='stores' >
            {purchases.map((pur) => (<li className='store'>
                <div className='topStore' >
                    <div>
                        <h5 className='title' >Buyer: {pur.buyer}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Products: </h5>
                        {renderProds(pur.prods)}
                    </div>
                    <div>
                        <h5 className='title' >Total Price: {pur.price}</h5>
                    </div>
                    <div>
                        <h5 className='title' >Purchase Time: {pur.purchaseTime}</h5>
                    </div>
                    <div>
                        <h5>Purchase ID: {pur.purchaseId}</h5>
                    </div>
                </div>
            </li>))}
        </ul>);
    }

    return (
        <ThemeProvider theme={mdTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <MyAppBar title={`${name} - Purchase History`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                        { renderPurchases() }
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
