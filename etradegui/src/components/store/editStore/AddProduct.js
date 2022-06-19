import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import '../../../css/Dashboard.css';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {useNavigate, useParams} from 'react-router-dom';
import post from "../../util/post";
import MyAppBar from "../../dashboard/MyAppBar";
import MyDrawer from "../../dashboard/MyDrawer";
import MyError from "../../util/MyError";

const mdTheme = createTheme();

const DashboardContent = ({setSuccessMsg}) => {
    const { name } = useParams()
    const [open, setOpen] = React.useState(true);
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const body = {
            storeName: name,
            productName: data.get("productName"),
            amount: data.get("amount"),
            price: data.get("price"),
            category: data.get("category")
        }
        try {
            const res = await post(body, 'stores/addproducttostore')
            const boolRes = await res.json()
            if(boolRes.val) {
                setSuccessMsg(`product ${data.get("productName")} has been added to your store`)
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
                <MyAppBar title={`${name} - Add Product`} open={open} toggleDrawer={() => {setOpen(!open)}}/>
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
                                                <Grid item xl={12}>
                                                    <TextField
                                                        required
                                                        fullWidth
                                                        id="productName"
                                                        label="Product Name"
                                                        name="productName"
                                                        autoComplete="productName"
                                                    />
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <TextField
                                                        required
                                                        fullWidth
                                                        name="amount"
                                                        label="Amount"
                                                        id="amount"
                                                        autoComplete="amount"
                                                    />
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <TextField
                                                        required
                                                        fullWidth
                                                        name="price"
                                                        label="Price"
                                                        id="price"
                                                        autoComplete="price"
                                                    />
                                                </Grid>
                                                <Grid item xl={12}>
                                                    <TextField
                                                        required
                                                        fullWidth
                                                        name="category"
                                                        label="Category"
                                                        id="category"
                                                        autoComplete="category"
                                                    />
                                                </Grid>
                                            </Grid>
                                            <Button
                                                type="submit"
                                                fullWidth
                                                variant="contained"
                                                sx={{mt: 3, mb: 2}}
                                            >
                                                Add Product
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

export default function Dashboard({setSuccessMsg}) {
    return <DashboardContent setSuccessMsg={setSuccessMsg}/>;
}

