import * as React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';

export default function AddressForm({setAddress, setStreetNum, setCity, setAptNum,setCountry, setZip}) {
    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Shipping address
            </Typography>
            <Grid container spacing={3}>

                <Grid item xs={12}>
                    <TextField
                        required
                        onChange={(event) => setAddress(event.target.value)}
                        id="street"
                        name="street"
                        label="Your Street"
                        fullWidth
                        autoComplete="shipping address-line1"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        required
                        onChange={(event) => setStreetNum(event.target.value)}
                        id="streetNum"
                        name="streetNum"
                        label="Your Street Num"
                        fullWidth
                        autoComplete="shipping address-line1"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        onChange={(event) => setCity(event.target.value)}
                        required
                        id="city"
                        name="city"
                        label="City"
                        fullWidth
                        autoComplete="shipping address-level2"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        onChange={(event) => setAptNum(event.target.value)}
                        id="aptNum"
                        name="aptNum"
                        label="Apartment Number"
                        fullWidth
                        autoComplete="shipping country"
                        variant="standard"
                    />
                </Grid>                <Grid item xs={12} sm={6}>
                    <TextField
                        onChange={(event) => setCountry(event.target.value)}
                        required
                        id="country"
                        name="country"
                        label="Country"
                        fullWidth
                        autoComplete="shipping address-level2"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} sm={6}>
                    <TextField
                        required
                        onChange={(event) => setZip(event.target.value)}
                        id="zip"
                        name="zip"
                        label="Zip"
                        fullWidth
                        autoComplete="shipping country"
                        variant="standard"
                    />
                </Grid>
            </Grid>
        </React.Fragment>
    );
}
