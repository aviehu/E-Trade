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
import imageLogo from './logo.jpg'
import {useNavigate} from 'react-router-dom';

const theme = createTheme();

export default function SignIn() {
    const navigate = useNavigate();
    const handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        //todo: check if the DB contain this email, if yes check for password.
        //todo: if all is ok redirect to Dashboard.
        console.log({
            email: data.get('email'),
            password: data.get('password'),
        });
        navigate("/etrade");
    };

    return (
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline/>
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
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}