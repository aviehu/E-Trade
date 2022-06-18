import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import {createTheme, ThemeProvider} from '@mui/material/styles';
import imageLogo from '../logo.jpg'
import {useNavigate} from 'react-router-dom';
import {useEffect} from "react";
import post from '../util/post'
import MyError from "../util/MyError";
import get from "../util/get";

const theme = createTheme();

export default function SignIn() {
    const [error, setError] = React.useState("")
    const [hasError, setHasError] = React.useState(false)
    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const email = data.get('email');
        const body = {
            email: data.get('email'),
            password: data.get('password'),
        }
        try {
            const res = await post(body, 'users/login')
            const boolRes = await res.json()
            if(boolRes.val) {
                localStorage.setItem("userName", email)
                navigate("/etrade")
            } else {
                setError(boolRes.err)
                setHasError(true)
            }
        } catch (e) {

        }
    };

    useEffect(() => {
        async function getUserName() {
            const res = await fetch('http://localhost:8080/users/entersystem')
            const obj = await res.json()
            const userName = obj.val
            localStorage.setItem("userName", userName)
        }
        getUserName()
    }, [])

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
                <MyError open={hasError} setOpen={setHasError} error={error}/>
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar variant="square" sx={{width: 100, height: 100}} alt="eTradeLogo" src={imageLogo}/>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email Address"
                            name="email"
                            autoComplete="email"
                            autoFocus
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            autoComplete="current-password"
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{mt: 3, mb: 2}}
                        >
                            Sign In
                        </Button>
                        <Grid container justifyContent="flex-start">
                            <Link href="/signup" variant="body2">
                                {"Don't have an account? sign up to E-Trade"}
                            </Link>
                        </Grid>
                        <Grid container justifyContent="flex-start">
                            <Link onClick={async () => await get('users/enterasguest')} href="/etrade" variant="body2" sx={{mt: 5}}>
                                {"Don't want to sign up? login as a guest"}
                            </Link>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}
