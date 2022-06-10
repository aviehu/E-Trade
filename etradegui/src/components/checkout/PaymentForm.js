import * as React from 'react';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

const getMonths = () => {
    const months = [1,2,3,4,5,6,7,8,9,10,11,12]
    return months.map((m) => {
        return <MenuItem value={m}>{m}</MenuItem>
    })
}

const getYears = () => {
    const years = [2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032]
    return years.map((y) => {
        return <MenuItem value={y}>{y}</MenuItem>
    })
}

export default function PaymentForm({ setCardNum, setCcv, month, setMonth, year, setYear, setCardHolder, setCardHolderId}) {
    return (
        <React.Fragment>
            <Typography variant="h6" gutterBottom>
                Payment method
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} md={12}>
                    <TextField
                        required
                        onChange={event => {setCardHolder(event.target.value)}}
                        id="holdersName"
                        label="Card Owner's Name"
                        fullWidth
                        autoComplete="cc-number"
                        variant="standard"
                    />
                </Grid>
                <Grid item xs={12} md={12}>
                    <TextField
                        required
                        onChange={event => {setCardHolderId(event.target.value)}}
                        id="holdersId"
                        label="Card Owner's Id"
                        fullWidth
                        autoComplete="cc-number"
                        variant="standard"
                    />
                </Grid>
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
                <Grid item xl={6} xs={6}>
                    <FormControl fullWidth>
                        <InputLabel id="demo">Month</InputLabel>
                        <Select
                            labelId="demo"
                            id="demo-simple-select"
                            label="Predicate Discount"
                            value={month}
                            onChange={(event) => {
                                setMonth(event.target.value)
                            }}
                        >
                            {getMonths()}
                        </Select>
                    </FormControl>
                </Grid>
                <Grid item xl={6} xs={6}>
                    <FormControl fullWidth>
                        <InputLabel id="demo">Year</InputLabel>
                        <Select
                            labelId="demo"
                            id="demo-simple-select"
                            label="Predicate Discount"
                            value={year}
                            onChange={(event) => {
                                setYear(event.target.value)
                            }}
                        >
                            {getYears()}
                        </Select>
                    </FormControl>
                </Grid>
            </Grid>
        </React.Fragment>
    );
}
