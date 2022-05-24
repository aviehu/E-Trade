import * as React from 'react';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';

export default function PaymentForm({setExpDate, setCardNum, setCcv, expDate}) {
    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Payment method
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} md={6}>
                    <TextField
                        required
                        onChange={event => {setCardNum(event.target.value)}}
                        id="cardNumber"
                        label="Card number"
                        fullWidth
                        autoComplete="cc-number"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <TextField
                        required
                        onChange={(event) => { setCcv(event.target.value)}}
                        id="cvv"
                        label="CVV"
                        fullWidth
                        autoComplete="cc-csc"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DesktopDatePicker
                            label="exp date"
                            value={expDate}
                            onChange={(newValue) => {
                                setExpDate(newValue);
                            }}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>
                </Grid>
            </Grid>
        </React.Fragment>
    );
}
